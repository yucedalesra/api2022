package get_Requests;

import baseUrls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.GoRestDataPojo;
import pojos.GoRestResponseBodyPojo;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;


public class Get13Pojo extends GoRestBaseUrl {

       /*
        Given

        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        And  https://gorest.co.in/public/v1/users/2508
            Response body should be like
         {
                      "meta": null,
                       "data": {
                                 "id": 2508,
                                 "name": "Amish Arora",
                                 "email": "amish_arora@stark-champlin.co",
                                 "gender": "male",
                                 "status": "active"
    }
}
    */

    @Test
    public void getPojo01(){
        // 1- Set the URL
        spec.pathParams("first","users","second",2508);

        // 2- Set the expected data
        GoRestDataPojo goRestDataPojo=new GoRestDataPojo(2508,"Amish Arora","amish_arora@stark-champlin.co","male","active");

        GoRestResponseBodyPojo goRestResponseBodyPojo = new GoRestResponseBodyPojo(null,goRestDataPojo);

        // 3. Send the get request get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        // 4. Do Assertion

        GoRestResponseBodyPojo actualPojo = response.as(GoRestResponseBodyPojo.class);
        assertEquals(200,response.getStatusCode());

        assertEquals(goRestResponseBodyPojo.getMeta(),actualPojo.getMeta());
        assertEquals(goRestResponseBodyPojo.getData().getId(),actualPojo.getData().getId());
        assertEquals(goRestResponseBodyPojo.getData().getName(),actualPojo.getData().getName());
        assertEquals(goRestResponseBodyPojo.getData().getEmail(),actualPojo.getData().getEmail());
        assertEquals(goRestResponseBodyPojo.getData().getGender(),actualPojo.getData().getGender());
        assertEquals(goRestResponseBodyPojo.getData().getStatus(),actualPojo.getData().getStatus());

    }
}

