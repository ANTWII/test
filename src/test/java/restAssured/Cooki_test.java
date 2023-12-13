package restAssured;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class Cooki_test {

    //Post Requests using Json Object

    @Test(priority = 1)
    public void CookieTest() {

String CoockieValue;


        RestAssured.baseURI="https://www.google.com/";

        Response res=
        RestAssured.given()


                .when()
                .get();
//Get single Cookie Info
        CoockieValue= res.getCookie("AEC");
        System.out.println("value of cookie=>" +CoockieValue);

//Get all Cookies and coockie key
        Map<String,String> AllCookies=res.getCookies();
        System.out.println("All the cookie keys with Values are=>" + AllCookies);
        System.out.println("All the cookie keys with  are=>" + AllCookies.keySet());

        //Loop to retrieve value
       for(String K:AllCookies.keySet())

       {

           CoockieValue=res.getCookie(K);
           Assert.assertNotNull(CoockieValue);
           System.out.println("value of cookie=>" +CoockieValue);
    }

}
}
