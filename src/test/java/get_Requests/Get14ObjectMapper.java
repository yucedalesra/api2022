package get_Requests;

import baseUrls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.JsonUtil;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get14ObjectMapper extends JsonPlaceHolderBaseUrl {

      /*
        Given
           https://jsonplaceholder.typicode.com/todos/198
        When
         I send GET Request to the URL
      Then
         Status code is 200
         And response body is like {
                               "userId": 10,
                               "id": 198,
                               "title": "quis eius est sint explicabo",
                               "completed": true
                             }
     */

    @Test
    public void get01ObjectMapper(){
        // 1- Set the URL
        spec.pathParams("first","todos","second",198);

        // 2- Set the expected data
        String expectedData= " {\n" +
                "     \"userId\": 10,\n" +
                "     \"id\": 198,\n" +
                "     \"title\": \"quis eius est sint explicabo\",\n" +
                "     \"completed\": true\n" +
                "   }";
       HashMap<String, Object> expectedDataMap= JsonUtil.convertJsonToJavaObject(expectedData, HashMap.class);

        // 3. Send the get request get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");


        // 4. Do Assertio
       HashMap<String, Object> actualDataMap= JsonUtil.convertJsonToJavaObject(response.asString(),HashMap.class);

        assertEquals(200,response.getStatusCode());
        assertEquals(expectedDataMap.get("userId"),actualDataMap.get("userId"));
        assertEquals(expectedDataMap.get("title"),actualDataMap.get("title"));
        assertEquals(expectedDataMap.get("completed"),actualDataMap.get("completed"));
    }
}
