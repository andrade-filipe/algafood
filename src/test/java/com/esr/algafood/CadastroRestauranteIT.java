package com.esr.algafood;

import com.esr.algafood.domain.entity.Cozinha;
import com.esr.algafood.domain.entity.Restaurante;
import com.esr.algafood.domain.repository.CozinhaRepository;
import com.esr.algafood.domain.repository.RestauranteRepository;
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

import java.math.BigDecimal;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CadastroRestauranteIT {
    private static final String VIOLACAO_DE_REGRA_DE_NEGOCIO_PROBLEM_TYPE = "Erro do Usuário";

    private static final String DADOS_INVALIDOS_PROBLEM_TITLE = "Dados Inválidos";

    private static final int RESTAURANTE_ID_INEXISTENTE = 100;

    @LocalServerPort
    private int port;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    private String jsonRestauranteCorreto;
    private String jsonRestauranteSemFrete;
    private String jsonRestauranteSemCozinha;
    private String jsonRestauranteComCozinhaInexistente;

    private Restaurante burgerTopRestaurante;

    @BeforeEach
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/restaurantes";

        jsonRestauranteCorreto = ResourceUtils.getContentFromResource(
            "/json/success/CadastroRestauranteNYB.json");

        jsonRestauranteSemFrete = ResourceUtils.getContentFromResource(
            "/json/error/CadastroRestauranteSemFrete.json");

        jsonRestauranteSemCozinha = ResourceUtils.getContentFromResource(
            "/json/error/CadastroRestauranteSemCozinha.json");

        jsonRestauranteComCozinhaInexistente = ResourceUtils.getContentFromResource(
            "/json/error/CadastroRestauranteComCozinhaInexistente.json");

        databaseCleaner.clearTables();
        prepararDados();
    }

    @Test
    @DisplayName("SHOULD return status code 200 WHEN reading /restaurantes")
    public void readingRestaurantes() {
        given()
            .accept(ContentType.JSON)
            .when()
            .get()
            .then()
            .statusCode(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("SHOULD return status code 201 WHEN creating /restaurantes")
    public void creatingRestaurante() {
        given()
            .body(jsonRestauranteCorreto)
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .when()
            .post()
            .then()
            .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    @DisplayName("SHOULD return status code 400 WHEN trying to create in /restaurantes WITHOUT 'taxaFrete'")
    public void creatingRestauranteWithoutTaxaFrete() {
        given()
            .body(jsonRestauranteSemFrete)
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .when()
            .post()
            .then()
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .body("title", equalTo(DADOS_INVALIDOS_PROBLEM_TITLE));
    }

    @Test
    @DisplayName("SHOULD return status code 400 WHEN trying to create in /restaurantes WITHOUT Cozinha")
    public void creatingRestauranteWithoutCozinha() {
        given()
            .body(jsonRestauranteSemCozinha)
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .when()
            .post()
            .then()
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .body("title", equalTo(DADOS_INVALIDOS_PROBLEM_TITLE));
    }

    @Test
    @DisplayName("SHOULD return status code 400 WHEN trying to create in /restaurantes WITH a invalid Cozinha.Id")
    public void creatingRestauranteInvalidCozinha() {
        given()
            .body(jsonRestauranteComCozinhaInexistente)
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .when()
            .post()
            .then()
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .body("title", equalTo(VIOLACAO_DE_REGRA_DE_NEGOCIO_PROBLEM_TYPE));
    }

    @Test
    @DisplayName("SHOULD return status code 200 WHEN reading and existing Restaurante")
    public void readingExistingRestaurante() {
        given()
            .pathParam("restauranteId", burgerTopRestaurante.getId())
            .accept(ContentType.JSON)
            .when()
            .get("/{restauranteId}")
            .then()
            .statusCode(HttpStatus.OK.value())
            .body("nome", equalTo(burgerTopRestaurante.getNome()));
    }

    @Test
    @DisplayName("SHOULD return status code 404 WHEN reading a non existing Restaurante")
    public void readingNonExistentRestaurante() {
        given()
            .pathParam("restauranteId", RESTAURANTE_ID_INEXISTENTE)
            .accept(ContentType.JSON)
            .when()
            .get("/{restauranteId}")
            .then()
            .statusCode(HttpStatus.NOT_FOUND.value());
    }

    private void prepararDados() {
        Cozinha cozinhaBrasileira = new Cozinha();
        cozinhaBrasileira.setNome("Brasileira");
        cozinhaRepository.save(cozinhaBrasileira);

        Cozinha cozinhaAmericana = new Cozinha();
        cozinhaAmericana.setNome("Americana");
        cozinhaRepository.save(cozinhaAmericana);

        burgerTopRestaurante = new Restaurante();
        burgerTopRestaurante.setNome("Burger Top");
        burgerTopRestaurante.setTaxaFrete(new BigDecimal(10));
        burgerTopRestaurante.setCozinha(cozinhaAmericana);
        restauranteRepository.save(burgerTopRestaurante);

        Restaurante comidaMineiraRestaurante = new Restaurante();
        comidaMineiraRestaurante.setNome("Comida Mineira");
        comidaMineiraRestaurante.setTaxaFrete(new BigDecimal(10));
        comidaMineiraRestaurante.setCozinha(cozinhaBrasileira);
        restauranteRepository.save(comidaMineiraRestaurante);
    }
}
