package Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class TokenRetriever {

    public static String getJwtToken(String username, String password) {
        // Установите базовый URL для вашего API
        RestAssured.baseURI = "http://85.192.34.140:8080/api/login";

        // Создайте объект запроса
        LoginRequest request = new LoginRequest();
        request.setPassword("admin");
        request.setUsername("admin");

        // Отправка POST-запроса с объектом запроса и извлечение ответа в объект ответа
        LoginResponse response = given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post(baseURI) // Замените на путь вашего API
                .then().log().all()
                .statusCode(200)
                .extract()
                .as(LoginResponse.class);

        // Проверка токена в ответе
        String token = response.getToken();
        return token;
    }
}
