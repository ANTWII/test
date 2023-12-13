package restAssured;
//STATIC PACKAGES
import static io.restassured.RestAssured.*;
import   static    io.restassured.matcher.RestAssuredMatchers.*;
import    static  org.hamcrest.Matchers.*;
//STATIC PACKAGES
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;
public class gameApiTest {

    @Test
    public void  myFirstTest() {

        Map<String,String>reusableParam= Map.of("phone_number","233 577553195","pincode","7755");
        String requestBody="{\n" +
                "\t\"phone_number\": \"233 577553195\",\n" +
                "\t\"pincode\": \"7755\"\n" +
                "}";
RestAssured.baseURI="https://stg-gh.fido.money";
        RestAssured.given()
                .pathParams(reusableParam)
                .contentType("application/json")
                .body(requestBody)
                .when()
             //.get("https://videogamedb.uk/api/videogame")
              .post("   /api/v1/ussd/validate-pincode?phone_number={phone_number}&pincode={pincode}")
                .then()
                .statusCode(200)
                .body("authenticated", Matchers.equalTo(true))
                .body("user_data.payment_methods[0].type", Matchers.equalTo("MOMO"))
                .log().all();

    }


    @Test
    public void mySecondTest() {

        String Phone ="233 577553195";
        String Pin="7755";

        JSONObject requestBody=new JSONObject();
       requestBody.put("phone_number",Phone);
       requestBody.put("pincode",Pin);


        RestAssured.baseURI="https://stg-gh.fido.money";
        RestAssured.given()
          .pathParams("phone_number",Phone)
           .pathParams("pincode",Pin)
                .contentType("application/json")
                .body(requestBody.toJSONString())
                .when()
                //.get("https://videogamedb.uk/api/videogame")
                .post("   /api/v1/ussd/validate-pincode?phone_number={phone_number}&pincode={pincode}")
                .then()
                .statusCode(200)
                .header("Content-Type","application/json")
                .contentType(ContentType.JSON)

                .body("authenticated", Matchers.equalTo(true))
                .body("user_data.payment_methods[0].type", Matchers.equalTo("MOMO"))
                .log().all();

    }
}