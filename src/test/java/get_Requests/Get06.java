package get_Requests;

import baseUrls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;

public class Get06 extends HerOkuAppBaseUrl {
    /*
    Given
            https://restful-booker.herokuapp.com/booking/555
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is “application/json”
        And
            Response body should be like;
          {
            "firstname": "Sally",
            "lastname": "Brown",
            "totalprice": 111,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2013-02-23",
                "checkout": "2014-10-23"
            },
            "additionalneeds": "Breakfast"
        }
     */

    @Test
    public void get01() {
        // 1.Step: Set the URL
        spec.pathParams("first", "booking", "second",101);
        // 2.Step: Set the expected data

        // 3.Step: Send the request and get the response
     Response response= given().spec(spec).when().get("/{first}/{second}");
     response.prettyPrint();

     // 4.Step: Do Assertions

        //1.YOL

      response
             .then()
             .assertThat()
             .statusCode(200)
             .contentType(ContentType.JSON)
             .body("firstname",equalTo("Sally"),
                     "lastname",equalTo("Brown"),
                     "totalprice",equalTo(111),
                     "depositpaid",equalTo(true),
                     "bookingdates.checkin",equalTo("2013-02-23"),
                     "bookingdates.checkout",equalTo("2014-10-23"));

        //2.YOL  -->> JsonPath Class kullanilir

        JsonPath json= response.jsonPath();

       assertEquals("Sally",json.getString("firstname"));
       assertEquals("Brown", json.getString("lastname"));
       assertEquals(111,json.getInt("totalprice"));
       assertEquals(true, json.getBoolean("depositpaid"));
       assertEquals("2013-02-23",json.getString("bookingdates.checkin"));
       assertEquals("2014-10-23",json.getString("bookingdates.checkout"));

       // 3.YOL -->> Soft Assertion
        // Soft Assertion icin 3 Adim izlenir;

        //1-) Soft Assert objesi olusturulur
        SoftAssert softAssert=new SoftAssert();

        // 2-) Obje araciligi ile Assert yapilir
        softAssert.assertEquals(json.getString("firstname"),"Sally","firstname uyusmadi");
        softAssert.assertEquals(json.getString("lastname"),"Brown","lastname uyusmadi");
        softAssert.assertEquals(json.getInt("totalprice"),"111","totalprice uyusmadi");
        softAssert.assertEquals(json.getBoolean("deposidpaid"),true,"deposidpaid uyusmadi");
        softAssert.assertEquals(json.getString("bookingdates.checkin"),"2013-02-23", "checkin uyusmadi");
        softAssert.assertEquals(json.getString("bookingdates.checkout"),"2014-10-23", "checkout uyusmadi");

        // 3-) assertAll() kullanilir. Aksi takdirde kod her zaman pass olur.
        softAssert.assertAll();


    }
}
