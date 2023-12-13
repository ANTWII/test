package restAssured;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.testng.annotations.Test;
import restAssured.Config.USSDTest_Config;

import java.util.Map;
import java.util.Random;

public class test22 extends USSDTest_Config {
    public static long generate10Digit() {
        long min = 1_000_000_000L; // 10-digit number starts from 1,000,000,000
        long max = 9_999_999_999L; // 10-digit number ends at 9,999,999,999
        Random rand = new Random();
        return rand.nextLong() % (max - min + 1) + min;
    }
    long NewSession=generate10Digit();

    @Test
    public void  myFirstTest() {

        Map<String,String> reusableParam= Map.of("phone_number","233 577553195","pincode","7755");
        String requestBody1="{\n" +
                "\t\"phone_number\": \"233 577553195\",\n" +
                "\t\"pincode\": \"7755\"\n" +
                "}";

        JSONObject requestBody=new JSONObject();
        requestBody.put("ussdString","*1234#");
        requestBody.put("ussdServiceOp","1");
        requestBody.put("sessionID",NewSession);
        requestBody.put("msisdn","+233577553195");
        requestBody.put("network","06");
        USSDTest_Config.setup();

        RestAssured.given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                //.get("https://videogamedb.uk/api/videogame")
                //.post("   /api/v1/ussd/validate-pincode?phone_number={phone_number}&pincode={pincode}")
                .post()
                .then()
                //.statusCode(200)
                //.body("authenticated", Matchers.equalTo(true))
               // .body("user_data.payment_methods[0].type", Matchers.equalTo("MOMO"))
                .log().all();

    }
}
