package restAssured.FidoMoneyTrial;
//STATIC PACKAGES
import io.restassured.RestAssured;
import org.testng.annotations.Test;


import java.util.Map;

public  class GetScoreState {


Map<String,String> Aut= Authorization_Authentication.GenerateTokenTest();
    public  final String BearerToken =Aut.get("authToken");
    public  final String PincodeToken = Aut.get("pincodeToken");;
    public  final String UserdID = Aut.get("userID");;
   public static final String  base_URl="https://stg-gh.fido.money/api/v1";
    @Test(description = "Create a repo")
    public void GetScoreTest() {

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
    public void GetScoreTestAUT() {

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
