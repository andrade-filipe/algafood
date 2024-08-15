package com.esr.algafood.application.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.*;

@JsonInclude(Include.NON_NULL)
@Getter
@Builder
public class Problem {

    //RFC 7807
    private Integer status;
    private LocalDateTime timestamp;
    private String type;
    private String title;
    private String detail;
    private String userMessage;
    private List<Field> fields;

    @Getter
    @Builder
    public static class Field {
        private String name;
        private String userMessage;
    }

}
