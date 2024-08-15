package com.esr.algafood.application.exceptionhandler;

import com.esr.algafood.domain.exception.IsBeingUsedException;
import com.esr.algafood.domain.exception.NOT_FOUND.EntityNotFoundException;
import com.esr.algafood.domain.exception.NegocioException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.fasterxml.jackson.databind.JsonMappingException.*;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String USER_GENERIC_MSG = "Ocorreu um erro interno inesperado no sistema. Tente novamente" +
        "Se persistir, entre em contato com o administrador.";

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleUncaught(Exception ex,
                                            WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ProblemType problemType = ProblemType.SYSTEM_ERROR;
        String detail = USER_GENERIC_MSG;

        ex.printStackTrace();

        Problem problem = createProblemBuilder(status, problemType, detail)
            .userMessage(detail)
            .build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }
    @ExceptionHandler(IsBeingUsedException.class)
    public ResponseEntity<?> handleIsBeingUsed(IsBeingUsedException ex,
                                               WebRequest request){

        HttpStatus status = HttpStatus.CONFLICT;
        ProblemType problemType = ProblemType.IS_BEING_USED;
        String detail = ex.getMessage();

        Problem problem = createProblemBuilder(status, problemType, detail)
            .userMessage("O recurso não pode ser excluido, pois está sendo utilizado")
            .build();

        return handleExceptionInternal(
            ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleNotFound(EntityNotFoundException ex,
                                            WebRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        ProblemType problemType = ProblemType.RESOURCE_NOT_FOUND;
        String detail = ex.getMessage();

        Problem problem = createProblemBuilder(status, problemType, detail)
            .userMessage("Recurso não foi Encontrado")
            .build();

        return handleExceptionInternal(
            ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> handleBadRequest(NegocioException ex,
                                              WebRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ProblemType problemType = ProblemType.USER_EXCEPTION;
        String detail = ex.getMessage();

        Problem problem = createProblemBuilder(status, problemType, detail)
            .userMessage("Campos Inválidos")
            .build();

        return handleExceptionInternal(
            ex, problem, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        ProblemType problemType = ProblemType.INVALID_DATA;
        String detail = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente";

        BindingResult bindingResult = ex.getBindingResult();

        List<Problem.Field> problemFields = bindingResult.getFieldErrors().stream()
            .map(fieldError -> {
                String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());

                return Problem.Field.builder()
                    .name(fieldError.getField())
                    .userMessage(message)
                    .build();
            })
            .collect(Collectors.toList());

        Problem problem = createProblemBuilder(status, problemType, detail)
            .userMessage("Um ou mais campos inválidos")
            .fields(problemFields)
            .build();

        return handleExceptionInternal(
            ex, problem, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        Throwable rootCause = ExceptionUtils.getRootCause(ex);

        if(rootCause instanceof InvalidFormatException) {
            return handleInvalidFormat((InvalidFormatException) rootCause, headers,status,request);
        } else if (rootCause instanceof PropertyBindingException){
            return handlePropertyBinding((PropertyBindingException) rootCause, headers, status, request);
        }

        ProblemType problemType = ProblemType.REQUEST_NOT_READABLE;
        String detail = "O Corpo da Requisição é Invalido, procuro por erros de sintaxe.";

        Problem problem = createProblemBuilder(status, problemType, detail)
            .userMessage(USER_GENERIC_MSG)
            .build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    private ResponseEntity<Object> handlePropertyBinding(PropertyBindingException ex,
                                                         HttpHeaders headers,
                                                         HttpStatus status,
                                                         WebRequest request) {
        ProblemType problemType = ProblemType.REQUEST_NOT_READABLE;

        String path = joinPath(ex.getPath());

        String detail = String.format("A propriedade '%s' não pode ser enviada à API," +
            " ela não existe ou deve ser removida da requisição", path);

        Problem problem = createProblemBuilder(status, problemType, detail)
            .userMessage(USER_GENERIC_MSG)
            .build();

        return handleExceptionInternal(ex, problem, headers, status, request);

    }

    private ResponseEntity<Object> handleInvalidFormat(InvalidFormatException ex,
                                                       HttpHeaders headers,
                                                       HttpStatus status,
                                                       WebRequest request) {
        ProblemType problemType = ProblemType.REQUEST_NOT_READABLE;

        String path = joinPath(ex.getPath());

        String detail = String.format("A propriedade '%s' recebeu o valor '%s', que é inválido. " +
            "Corrija e informe um valor com o tipo '%s'", path, ex.getValue(), ex.getTargetType().getSimpleName());

        Problem problem = createProblemBuilder(status, problemType, detail)
            .userMessage(USER_GENERIC_MSG)
            .build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex,
                                                        HttpHeaders headers,
                                                        HttpStatus status,
                                                        WebRequest request) {

        if(ex instanceof MethodArgumentTypeMismatchException) {
            return handleMethodArgumentTypeMismatch((MethodArgumentTypeMismatchException) ex, headers, status, request);
        }

        return super.handleTypeMismatch(ex, headers, status, request);
    }

    private ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
                                                                    HttpHeaders headers,
                                                                    HttpStatus status,
                                                                    WebRequest request) {
        ProblemType problemType = ProblemType.INVALID_PARAMETER;

        String parameter = ex.getParameter().getParameterName();
        String value = ex.getValue().toString();
        String requiredType = ex.getRequiredType().getSimpleName();

        String detail = String.format("O parâmetro de URL '%s' recebeu o valor '%s', que é de tipo inválido. " +
            "Corrija e informe um valor com o tipo '%s'", parameter, value, requiredType);

        Problem problem = createProblemBuilder(status, problemType, detail)
            .userMessage(USER_GENERIC_MSG)
            .build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex,
                                                                   HttpHeaders headers,
                                                                   HttpStatus status,
                                                                   WebRequest request) {
        ProblemType problemType = ProblemType.RESOURCE_NOT_FOUND;

        String resource = ex.getRequestURL();

        String detail = String.format("O recurso '%s', que você tentou acessar, é inexistente.", resource);

        Problem problem = createProblemBuilder(status, problemType, detail)
            .userMessage("Esse link não existe.")
            .build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }


    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex,
                                                             Object body,
                                                             HttpHeaders headers,
                                                             HttpStatus status,
                                                             WebRequest request) {

        if(body == null){
            body = Problem.builder()
                .timestamp(LocalDateTime.now())
                .title(status.getReasonPhrase())
                .status(status.value())
                .userMessage(USER_GENERIC_MSG)
                .build();
        } else if(body instanceof String){
            body = Problem.builder()
                .timestamp(LocalDateTime.now())
                .title((String) body)
                .status(status.value())
                .userMessage(USER_GENERIC_MSG)
                .build();
        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private Problem.ProblemBuilder createProblemBuilder(HttpStatus status,
                                                        ProblemType problemType,
                                                        String detail) {
        return Problem.builder()
            .timestamp(LocalDateTime.now())
            .status(status.value())
            .type(problemType.getUri())
            .title(problemType.getTitle())
            .detail(detail);

    }

    private String joinPath(List<Reference> references) {
        return references.stream()
            .map(Reference::getFieldName)
            .collect(Collectors.joining("."));
    }
}
