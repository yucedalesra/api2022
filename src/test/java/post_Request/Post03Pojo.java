package post_Request;

import baseUrls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.JsonPlaceHolderPojo;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Post03Pojo extends JsonPlaceHolderBaseUrl {

     /*
         Given
            https://jsonplaceholder.typicode.com/todos
            {
            "userId": 55,
            "title": "Tidy your room",
            "completed": false
            }
        When
            I send POST Request to the Url
        Then
            Status code is 201
        And
            response body is like {
                                    "userId": 55,
                                    "title": "Tidy your room",
                                    "completed": false,
                                    "id": 201
                                    }
     */

    @Test
    public void postPojo01(){
        // 1- Set the URL
        spec.pathParam("first","todos");

        // 2- Set the expected data
        JsonPlaceHolderPojo requestBody = new JsonPlaceHolderPojo(55,"Tidy your room",false);

        // 3- Send Post Request and get the response
      Response response= given().spec(spec).contentType(ContentType.JSON).body(requestBody).when().post("/{first}");

      // 4- Do Assertion
      JsonPlaceHolderPojo actualBody = response.as(JsonPlaceHolderPojo.class);

      assertEquals(requestBody.getUserId(),actualBody.getUserId());
      assertEquals(requestBody.getTitle(),actualBody.getTitle());
      assertEquals(requestBody.getCompleted(),actualBody.getCompleted());

   //     System.out.println(actualBody.toString());
   //     System.out.println(requestBody.toString());
   //     assertEquals(requestBody.toString(),actualBody.getTitle());

    }

}
