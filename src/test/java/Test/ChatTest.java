package Test;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ChatTest {

@Test
    public void get (){
        given().get("http://85.192.34.140:8080/")
                .then()
                .log().all();
    }
}
