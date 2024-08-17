package com.esr.algafood;

import com.esr.algafood.domain.entity.Cozinha;
import com.esr.algafood.domain.repository.CozinhaRepository;
import com.esr.algafood.util.DatabaseCleaner;
import com.esr.algafood.util.ResourceUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
class CadastroCozinhaIT {

    @LocalServerPort
    private int port;

//    @Autowired
//    private Flyway flyway;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    private static final int NON_EXISTENT_COZINHA_ID = 100;

    private Cozinha cozinhaItaliana;
    private int countCozinhas;
    private String jsonSuccessCozinhaChinesa;

    @BeforeEach
    public void setup(){
        enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/cozinhas";

        databaseCleaner.clearTables();
        setupDatabase();

        jsonSuccessCozinhaChinesa = ResourceUtils.getContentFromResource(
            "/json/success/CadastroCozinhaChinesa.json");

//        flyway.migrate();
    }

    @DisplayName("SHOULD return status 200 WHEN reading Cozinha")
    @Test
    public void ConsultarCozinha(){
        given()
            .accept(ContentType.JSON)
        .when()
            .get()
        .then()
            .statusCode(HttpStatus.OK.value());
    }

    @DisplayName("SHOULD appear Cozinhas WHEN reading /cozinha")
    @Test
    public void ConsultarVariasCozinhas(){
        given()
            .accept(ContentType.JSON)
        .when()
            .get()
        .then()
            .body("", hasSize(countCozinhas))
            .body("nome", hasItems("Brasileira", "Italiana"));
    }

    @Test
    @DisplayName("SHOULD return status 201 WHEN registering Cozinha")
    public void CadastroDeCozinha() {
        given()
            .body(jsonSuccessCozinhaChinesa)
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
        .when()
            .post()
        .then()
            .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    @DisplayName("SHOULD return status 200 and correct name WHEN reading Cozinha of id 1")
    public void ConsultarCozinhaExistente(){
        given()
            .pathParam("cozinhaId", cozinhaItaliana.getId())
            .accept(ContentType.JSON)
        .when()
            .get("/{cozinhaId}")
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("nome", equalTo(cozinhaItaliana.getNome()));
    }

    @Test
    @DisplayName("SHOULD return status 404 WHEN reading a Cozinha of non existent id")
    public void ConsultarCozinhaInexistente(){
        given()
            .pathParam("cozinhaId", NON_EXISTENT_COZINHA_ID)
            .accept(ContentType.JSON)
        .when()
            .get("/{cozinhaId}")
        .then()
            .statusCode(HttpStatus.NOT_FOUND.value());
    }

    private void setupDatabase(){
        Cozinha cozinhaBrasileira = new Cozinha();
        cozinhaBrasileira.setNome("Brasileira");
        cozinhaRepository.save(cozinhaBrasileira);

        Cozinha cozinhaItaliana = new Cozinha();
        cozinhaItaliana.setNome("Italiana");
        cozinhaRepository.save(cozinhaItaliana);

        countCozinhas = (int) cozinhaRepository.count();
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
