package post_Request;

import baseUrls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.BookingResponseBodyPojo;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Post04Pojo extends HerOkuAppBaseUrl {

      /*
         Given
          1)  https://restful-booker.herokuapp.com/booking
          2)   {
                "firstname": "Ali",
                "lastname": "Can",
                "totalprice": 999,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2021-09-21",
                    "checkout": "2021-12-21"
                 }
                 "additionalneeds": "Breakfast with white tea, Dragon juice"
             }
        When
          I send POST Request to the URL
       Then
          Status code is 200
      And
          Response body is like {
                                  "bookingid": 16,
                                  "booking" :{
                                        "firstname": "Ali",
                                        "lastname": "Can",
                                        "totalprice": 999,
                                        "depositpaid": true,
                                        "bookingdates": {
                                            "checkin": "2021-09-21",
                                            "checkout": "2021-12-21"
                                        }
                                        "additionalneeds": "Breakfast with white tea, Dragon juice"
                                     }
                                  }
     */


    @Test
    public void postPojo01(){
        // 1- Set the URL
        spec.pathParam("first","booking");

        // 2- Set the expected data
        BookingDatesPojo bookingdates= new BookingDatesPojo("2021-09-21","2021-12-21");
        BookingPojo bookingPojo = new BookingPojo("Ali","Can",999,true,bookingdates,"Breakfast with white tea, Dragon juice");



        // 3- Send Post Request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(bookingPojo).when().post("/{first}");


        // 4- Do Assertion
       BookingResponseBodyPojo actualPojo= response.as(BookingResponseBodyPojo.class);

       assertEquals(200,response.getStatusCode());
       assertEquals(bookingPojo.getFirstname(),actualPojo.getBooking().getFirstname());
       assertEquals(bookingPojo.getLastname(),actualPojo.getBooking().getLastname());
       assertEquals(bookingPojo.getTotalprice(),actualPojo.getBooking().getTotalprice());
       assertEquals(bookingPojo.isDepositpaid(),actualPojo.getBooking().isDepositpaid());

       // 1. YOL
       assertEquals(bookingPojo.getBookingdates().getCheckin(),actualPojo.getBooking().getBookingdates().getCheckin());
       assertEquals(bookingPojo.getBookingdates().getCheckout(),actualPojo.getBooking().getBookingdates().getCheckout());

       // 2.YOL
        assertEquals(bookingdates.getCheckin(),actualPojo.getBooking().getBookingdates().getCheckin());
        assertEquals(bookingdates.getCheckout(),actualPojo.getBooking().getBookingdates().getCheckout());


       assertEquals(bookingPojo.getAdditionalneeds(),actualPojo.getBooking().getAdditionalneeds());
    }
}































