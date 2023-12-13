package restAssured.FidoMoneyConfig;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import restAssured.FidoMoneyConfig.AuthApiClient;
import restAssured.FidoMoneyConfig.Config_Api;

public class getScoreState_FidoMoney_Improved {
public  getScoreState_FidoMoney_Improved(){

}

    public   String BearerToken =Config_Api.getAuthToken();
    public   String PincodeToken = Config_Api.getPincodeToken();
    public   String UserdID = Config_Api.getUserID();

    public static final String  base_URl="https://stg-gh.fido.money/api/v1";
    @BeforeMethod
    public void setUp() {
        if (BearerToken == null || PincodeToken == null || UserdID == null) {
            // Authenticate and get the tokens if they're not already set
            AuthApiClient.authenticateAndGetToken();
            BearerToken = Config_Api.getAuthToken();
            PincodeToken = Config_Api.getPincodeToken();
            UserdID = Config_Api.getUserID();
        }
    }

    @Test(description = "Create a repo")
    public void GetScoreTest_() {






        RestAssured.given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + BearerToken)
                .header("pincode-token",  PincodeToken)
                .pathParam("user_id",UserdID)
                .when()
                .get(base_URl+"/users/{user_id}/score/state")
                .prettyPeek()
                .then()
                .statusCode(200) ;


    }

    @Test(description = "Create a repo")
    public void GetScoreTestAUT_() {

        RestAssured.given()
                .contentType("application/json")
                .auth()
                .oauth2( BearerToken)
                .header("pincode-token",  PincodeToken)
                .pathParam("user_id",UserdID)
                .when()
                .get(base_URl+"/users/{user_id}/score/state")
                .prettyPeek()
                .then()
                .statusCode(200) ;


    }


}




