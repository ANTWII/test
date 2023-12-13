package restAssured.FidoMoneyConfig;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.*;
import org.testng.annotations.Test;
import restAssured.FidoMoneyConfig.Config_Api;
import java.util.HashMap;
import java.util.Map;

public class AuthApiClient {

    public static void authenticateAndGetToken() {
        HashMap data=new HashMap();
        data.put("phone_number","233 577553195");
        data.put("pincode","7755");
        Map<String,String> reusableParam= Map.of("phone_number","233 577553195","pincode","7755");




        Response response = RestAssured.given()
                //* Add necessary request parameters for token retrieval */
                .baseUri("https://stg-gh.fido.money")
                .contentType("application/json")
                .body(data)
                .queryParams(reusableParam)

                .when()
                .post("/api/v1/ussd/validate-pincode");



        // Extract and set the Userid from the response
        String userID = response.jsonPath().get("user_data.id");
        Config_Api.setUserID(userID);

        // Extract and set auth-token and pincode-token from the response headers


        Headers headers = response.getHeaders();
        String authToken = null;
        String pincodeToken = null;

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
            Config_Api.setAuthToken(authToken);
        }

        if (pincodeToken != null) {
            System.out.println("Setting pincodeToken: " + pincodeToken);
            Config_Api.setPincodeToken(pincodeToken);
        }
    }
}
