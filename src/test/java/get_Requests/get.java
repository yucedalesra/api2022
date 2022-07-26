package get_Requests;

import baseUrls.jplacaholderbaseurl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class get extends jplacaholderbaseurl {

    @Test
    public void test1(){

        specJson.pathParam("pp1",22);

        Response response = given().spec(specJson).when().get("{pp1}");
        response.prettyPrint();

    }
}
