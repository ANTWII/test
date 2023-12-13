package restAssured;
//STATIC PACKAGES
import static io.restassured.RestAssured.*;
import   static    io.restassured.matcher.RestAssuredMatchers.*;
import    static  org.hamcrest.Matchers.*;
//STATIC PACKAGES
import io.restassured.RestAssured;
import org.json.JSONObject;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.util.Random;

import static restAssured.SessionClass.*;

public class test_Test {


    public static long generate10Digit() {
        long min = 1_000_000_000L; // 10-digit number starts from 1,000,000,000
        long max = 9_999_999_999L; // 10-digit number ends at 9,999,999,999
        Random rand = new Random();
        return rand.nextLong() % (max - min + 1) + min;
    }
    long NewSession=generate10Digit();
    public static final String _baseURI="http://52.30.107.6:4554/api/ussd";

        @Test()
        public void Home_() {


JSONObject requestBody=new JSONObject();
        requestBody.put("ussdString","*1234#");
        requestBody.put("ussdServiceOp","1");
        requestBody.put("sessionID",NewSession);
        requestBody.put("msisdn","+233577553195");
        requestBody.put("network","06");

           /* FidoUSSD_Pojo data=new FidoUSSD_Pojo();
            data.setUssdString("*1234#");
            data.setUssdServiceOp("1");
            data.setSessionID(NewSession);
            data.setMsisdn(phoneNumber);
            data.setNetwork("06");
*/

            RestAssured.given()

                    .contentType("application/json")
                    .body(requestBody.toString())
                    .when()
                    .post(_baseURI)
                    .then()
                    .statusCode(200)
                 .headers(expectedOBjectHeaders)
                    .log().all();

        }
}
