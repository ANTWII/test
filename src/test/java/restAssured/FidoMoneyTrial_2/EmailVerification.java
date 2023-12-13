package restAssured.FidoMoneyTrial_2;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.Test;
import restAssured.FidoMoneyTrial.Authorization_Authentication;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;

import static org.testng.Assert.assertNotNull;

public class EmailVerification {


    String CoockieValue;
    //Post Requests using external json file


    Map<String,String> Aut= Authorization_Authentication.GenerateTokenTest();
    public  final String BearerToken =Aut.get("authToken");
    public  final String PincodeToken = Aut.get("pincodeToken");
    public  final String UserdID = Aut.get("userID");
    public static final String  base_URl="https://stg-gh.fido.money";


    @Test(priority = 1)
    public void VerifyEmail() throws FileNotFoundException {

        //open file Location
        File f=new File("src/test/java/restAssured/FidoMoneyTrial_2/SendEmail.json");

        //open file using file reader
        FileReader fr=new FileReader(f);
        JSONTokener jt=new JSONTokener(fr);
        JSONObject requestBody=new JSONObject(jt);
requestBody.put("email","nanadollary@gmail.com");


        RestAssured.given()
                .contentType("application/json")
                .body(requestBody.toString())
                .header("Authorization", "Bearer " + BearerToken)
                .header("pincode-token",  PincodeToken)
                .pathParam("user_id",UserdID)
                .when()
                .post(base_URl+"/api/v1/users/{user_id}/email")
                .prettyPeek()
                .then()
                .statusCode(200) ;










    }

}
