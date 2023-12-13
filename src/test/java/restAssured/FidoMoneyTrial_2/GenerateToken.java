package restAssured.FidoMoneyTrial_2;
//STATIC PACKAGES
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.*;
import java.util.HashMap;
import java.util.Map;
public class GenerateToken {



    //Test
    public static Map<String,String> GenerateTokenTest() {



        HashMap requestBody=new HashMap();
        requestBody.put("phone_number","233 577553195");
        requestBody.put("pincode","7755");
        Map<String,String> reusableParam= Map.of("phone_number","233 577553195","pincode","7755");


        RestAssured.baseURI="https://stg-gh.fido.money";

        Response response=
                given()
                        .contentType("application/json")
                        .body(requestBody)
                        .queryParams(reusableParam)

                        .when()
                        .post("/api/v1/ussd/validate-pincode");

        // Extract headers


        Headers headers = response.getHeaders();
        String authToken = null;
        String pincodeToken = null;
        String userID = response.jsonPath().get("user_data.id");

        // Find auth-token and pincode-token headers
        for (io.restassured.http.Header header : headers) {
            if (header.getName().equalsIgnoreCase("auth-token")) {
                authToken = header.getValue();
            }
            if (header.getName().equalsIgnoreCase("pincode-token")) {
                pincodeToken = header.getValue();
            }
        }

        // Set global variables
        if (authToken != null) {
            System.out.println("Setting bearerToken: " + authToken);

        }

        if (pincodeToken != null) {
            System.out.println("Setting pincodeToken: " + pincodeToken);

        }
//set userid
        if (userID != null){
            System.out.println("Setting UserID: " + userID);

        }


        Map<String, String> Aut = Map.of("pincodeToken", pincodeToken, "authToken", authToken,"userID",userID);
        return Aut;

    }

}
