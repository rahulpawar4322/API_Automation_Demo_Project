package base;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import config.ConfigReader;

import static io.restassured.RestAssured.given;

public class BaseTest {

//    @BeforeClass
//    public void setup() {
//        RestAssured.baseURI = ConfigReader.get("baseUrl");
//    }

    public RequestSpecification requestSpec() {
        return given().baseUri(ConfigReader.get("baseUrl"))
                .header("Authorization", "Bearer " + ConfigReader.get("token"))
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON);
    }
}