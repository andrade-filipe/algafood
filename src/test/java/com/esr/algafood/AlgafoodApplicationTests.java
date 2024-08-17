package com.esr.algafood;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CadastroCozinhaIT {

    @LocalServerPort
    private int port;

    @Autowired
    private Flyway flyway;

    @BeforeEach
    public void setup(){
        enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/cozinhas";

        flyway.migrate();
    }

    @DisplayName("SHOULD return status 200 WHEN consulting Cozinha")
    @Test
    public void ConsultarCozinha(){
        given()
            .accept(ContentType.JSON)
        .when()
            .get()
        .then()
            .statusCode(HttpStatus.OK.value());
    }

    @DisplayName("SHOULD have 4 Cozinhas WHEN consulting /cozinha")
    @Test
    public void ConsultarQuatroCozinhas(){
        given()
            .accept(ContentType.JSON)
        .when()
            .get()
        .then()
            .body("", hasSize(4))
            .body("nome", hasItems("Brasileira", "Tailandesa"));
    }

    @Test
    @DisplayName("SHOULD return status 201 WHEN registering Cozinha")
    public void CadastroDeCozinha() {
        given()
            .body("{ \"nome\": \"Chinesa\" }")
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
        .when()
            .post()
        .then()
            .statusCode(HttpStatus.CREATED.value());
    }


// AULA DE TESTES, CODIGO APAGADO
//    @Autowired
//    private CadastroCozinhaService cadastroCozinha;
//
//    @Test
//    @DisplayName("GIVEN a Cozinha WHEN registering SHOULD be successful ")
//    public void CadastroCozinhaComSucesso(){
//        Cozinha cozinha = new Cozinha();
//        cozinha.setNome("Cozinha");
//
//        cozinha = cadastroCozinha.salvar(cozinha);
//
//        assertThat(cozinha).isNotNull();
//        assertThat(cozinha.getId()).isNotNull();
//    }
//
//    @Test
//    @DisplayName("SHOULD fail WHEN Cozinha.nome is null WHILE inserting")
//    public void CozinhaSemNome() {
//        Cozinha novaCozinha = new Cozinha();
//        novaCozinha.setNome(null);
//
//        ConstraintViolationException erroEsperado =
//            Assertions.assertThrows(ConstraintViolationException.class, () -> {
//                cadastroCozinha.salvar(novaCozinha);
//            });
//
//        assertThat(erroEsperado).isNotNull();
//    }
//
//    @Test
//    @DisplayName("SHOULD fail WHEN trying to delete a Cozinha in conflict")
//    public void CozinhaEmUso(){
//        IsBeingUsedException erroEsperado =
//            Assertions.assertThrows(IsBeingUsedException.class, () -> {
//                cadastroCozinha.excluir(1L);
//            });
//
//        assertThat(erroEsperado).isNotNull();
//    }
//
//    @Test
//    @DisplayName("SHOULD fail WHEN trying to delete a non existent Cozinha")
//    public void CozinhaInexistente(){
//        CozinhaNotFoundException erroEsperado =
//            Assertions.assertThrows(CozinhaNotFoundException.class, () -> {
//                cadastroCozinha.excluir(100L);
//            });
//
//        assertThat(erroEsperado).isNotNull();
//    }

}
