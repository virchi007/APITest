package Test;

import io.restassured.response.Response;
import net.minidev.json.JSONObject;
import org.testng.annotations.Test;
        import io.restassured.RestAssured;
        import io.restassured.http.ContentType;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
        import static org.hamcrest.Matchers.equalTo;

public class APITest {


        @Test
        public void testAPIRequest() {
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
            // Далее вы можете провести необходимые проверки на токен
        }
    @Test
    public void testAPIRequestWithJWT() {
        // Установите базовый URL для вашего API
        RestAssured.baseURI = "http://85.192.34.140:8080/api/user";

        // Ваш JWT-токен
        String jwtToken = "your_jwt_token_here";

        // Отправка GET-запроса с JWT-токеном в заголовке
        given()
                .header("Authorization", "Bearer " + jwtToken)
                .when()
                .get(baseURI) // Замените на путь вашего API
                .then().log().all()
                .statusCode(200) // Проверка кода ответа
                .body("key", equalTo("value")); // Пример проверки ответа

        // Далее вы можете добавить необходимые проверки на ответ
    }
    @Test
    public void testAPIRequestWithJwtToken() {
            String username = "demo";
            String password = "demo";
        // Получение JWT-токена с использованием метода TokenRetriever
        String jwtToken = TokenRetriever.getJwtToken(username, password);

        // Установите базовый URL для вашего API
        RestAssured.baseURI = "http://85.192.34.140:8080/api/user";

        // Отправка GET-запроса с JWT-токеном в заголовке
        given()
//                .auth().oauth2(jwtToken)      //тоже рабочий вариант заместо последущего либо - либо
                .header("Authorization", "Bearer " + jwtToken)
                .when()
                .get(baseURI) // Замените на путь вашего API
                .then().log().all()
                .statusCode(200); // Проверка кода ответа
//                .body("key", equalTo("value")); // Пример проверки ответа

        // Далее вы можете добавить необходимые проверки на содержимое ответа
    }
    }
//        // Установите базовый URL для вашего API
//        RestAssured.baseURI = "http://85.192.34.140:8080/api/login";
//
//        // JSON-запрос
//        JSONObject requestBody = new JSONObject();
//        requestBody.put("password", "admin");
//        requestBody.put("username", "admin");
//
//        // Отправка POST-запроса и проверка токена в ответе
//        Response response = given()
//                .contentType(ContentType.JSON)
//                .body(requestBody.toString())
//                .when()
//                .post(baseURI) // Замените на путь вашего API
//                .then()
//                .extract()
//                .response();
// //               .statusCode(200) // Проверка кода ответа
// //               .body("token", equalTo("string")); // Проверка токена в JSON-ответе
//
//    }


