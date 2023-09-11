import API.Specifications;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReqresNOPOJOTest {
    private final static String URL = "https://reqres.in/";

    @Test
    public void checkAvatarNoPojoTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        //In RestAssure существует Response интерфейс (класс без реализации)
        Response response = given()
                .when()
                .get("api/users?page=2")
                .then().log().all()
                .body("page", equalTo(2))         //чтобы не брать весь список из возврощаемого тела и создавать list можно в body прописать параметры для assert
                .body("data.id", notNullValue())    //и сделаем проверку на то что поля пришли не пустые. notNullValue() необходимо для того чтобы сделать проверку на то что значение прошло не пустое
                .body("data.email", notNullValue())
                .body("data.first_name", notNullValue())
                .body("data.last_name", notNullValue())
                .body("data.avatar", notNullValue())
                .extract().response();
        JsonPath jsonPath  = response.jsonPath();
        List<String> emails = jsonPath.get("data.email");
        List<Integer> ids = jsonPath.get("data.id");
        List<String> avatars = jsonPath.get("data.avatar");

        for (int i = 0; i < avatars.size(); i++) {
            Assert.assertTrue(avatars.get(i).contains(ids.get(i).toString()));      //Проверяем что аватары содержат id
        }

        Assert.assertTrue(emails.stream().allMatch(x->x.endsWith("@reqres.in")));
    }

    // Test Post
    @Test
    public void successUserRegRestNoPojo(){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        Map<String, String> user = new HashMap<>(); //создаем хеш карту и помещаем ключ-значение и в нашем случае это почта и пароль (обе строки).
        user.put("email", "eve.holt@reqres.in");   //теперь заполним карту значениями через put
        user.put("password", "pistol");
        Response response = given()
                .body(user)
                .when()
                .post("api/register")
                .then().log().all()
//                .body("id", equalTo(4))
//                .body("token", equalTo("QpwL5tke4Pnpja7X4"))
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        int id = jsonPath.get("id");
        String token = jsonPath.get("token");
        Assert.assertEquals(4,id);
        Assert.assertEquals("QpwL5tke4Pnpja7X4", token);

    }

    @Test
    public void unsuccessUserRegNoPojo() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecError400());
        Map<String, String> user = new HashMap<>();
        user.put("email","sydney@fife");
        given()
                .body(user)
                .when()
                .post("api/register")
                .then().log().all()
                .body("error", equalTo("Missing password"));

    }
    @Test
    public void unsuccessUserRegNoPojo2() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecError400());
        Map<String, String> user = new HashMap<>();
        user.put("email","sydney@fife");
        Response response = given()
                .body(user)
                .when()
                .post("api/register")
                .then().log().all()
//                .body("error", equalTo("Missing password"))
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.get("error");
        Assert.assertEquals(error, "Missing password");

    }

}
