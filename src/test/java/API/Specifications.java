package API;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specifications {
    /**
     * Укажем спецификацию для запроса и ответа.
     */
    public static RequestSpecification requestSpec(String url) {        //передает в аргумент ссылку
// здесь мы напишим по какой ссылки нам нужно обращаться и какой тип данных нам стоит ожидать
        return new RequestSpecBuilder()
                .setBaseUri(url)                 //то какая ссылка будет базовой
                .setContentType(ContentType.JSON)       //указываем какой тип данных
                .build();           //Вызвать метод по типу которого вся собрали
    }

    //нужно указать какой ответ нам стоит получить
    public static ResponseSpecification responseSpecOK200() {        //мы можем сделать заготовки для ответов:status code
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();               //If status code is different then 200 then all falls at the beginning and not use all of the resurses
    }
    public static ResponseSpecification responseSpecError400() {        //мы можем сделать заготовки для ответов:status code
        return new ResponseSpecBuilder()
                .expectStatusCode(400)
                .build();               //If status code is different then 200 then all falls at the beginning and not use all of the resurses
    }
    public static ResponseSpecification responseSpecUniqque(int status) {        //мы можем сделать заготовки для ответов:status code
        return new ResponseSpecBuilder()
                .expectStatusCode(status)
                .build();               //If status code is different then 200 then all falls at the beginning and not use all of the resurses
    }
    public static void installSpecification(RequestSpecification request, ResponseSpecification response) {
        //теперь из библиотеки rest assured нужно достать
        RestAssured.requestSpecification = request;
        RestAssured.responseSpecification = response;
    }
}
