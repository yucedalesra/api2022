package get_Requests;

import baseUrls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.GoRestTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Get10 extends GoRestBaseUrl {

      /*
        Given
            https://gorest.co.in/public/v1/users/2986
        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        And
            Response body should be like
                {
                 https://gorest.co.in/public/v1/users/2965
        "meta": null,
        "data": {
        "id": 2965,
        "name": "Mr. Gita Menon",
        "email": "gita_menon_mr@bayer.com",
        "gender": "female",
        "status": "inactive"
                            }
                }
     */

    @Test
    public void get01(){
        //1. Step: Set the Url
        spec.pathParams("first","users","second",2965);

        //2. Step: Set the expected data
        GoRestTestData dataKey = new GoRestTestData(); // Gerekli method'un cagirilmasi icin obje olusturuyorum
        Map<String , String> dataKeyMap = dataKey.dataKeyMap("Mr. Gita Menon", "gita_menon_mr@bayer.com","female","inactive"); // Ic Map'i olusturan method
        Map<String , Object> expectedData = dataKey.expectedDataMap(null,dataKeyMap); // Ust Map'i olusturan method


        //3. Step: Send the request and Get the Response
        Response response = given().spec(spec).when().get("/{first}/{second}");

        Map<String, Object> actualDataMap = response.as(HashMap.class); // De-Serialization ==>> Json formatindan Java Objesine cevirme

        //4. Step: Do Assertion
        assertEquals(expectedData.get("meta"),actualDataMap.get("meta"));
        assertEquals(dataKeyMap.get("name"),((Map)actualDataMap.get("data")).get("name")); // Once "data" elementine ulasip, buradan aldigim objeyi Map formatina cast ediyorum
        assertEquals(dataKeyMap.get("email"),((Map)actualDataMap.get("data")).get("email"));
        assertEquals(dataKeyMap.get("gender"),((Map)actualDataMap.get("data")).get("gender"));
        assertEquals(dataKeyMap.get("status"),((Map)actualDataMap.get("data")).get("status"));




    }

}