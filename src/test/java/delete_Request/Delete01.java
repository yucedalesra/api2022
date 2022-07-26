package delete_Request;

import baseUrls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Delete01 extends JsonPlaceHolderBaseUrl {
     /*
        Given
            https://jsonplaceholder.typicode.com/todos/198
        When
         I send DELETE Request to the Url
      Then
         Status code is 200
         And Response body is { }
     */

    @Test
    public void delete01(){
      //  1- Set the URL
        spec.pathParams("first","todos","second",198);



    // 2- Set the expected data
        Map<String,Object> expectedDataMap = new HashMap<>();

    // 3- Send the delete request, get the response

     Response response= given().spec(spec).when().delete("/{first}/{second}");
     response.prettyPrint();

      // 4- Do Assertion
        // 1.YOL
       Map<String, Object> actualMap= response.as(HashMap.class);
       response.then().assertThat().statusCode(200);
       assertEquals(actualMap,expectedDataMap);

       // 2.YOL
        assertTrue(actualMap.size()==0);
        assertTrue(actualMap.isEmpty()); // tavsiye edilen

        // Delete request yapmadan once "Post Request" yapip o datayi silmeliyiz

    }
}
