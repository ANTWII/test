package restAssured.Config;
import static org.hamcrest.Matchers.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.annotations.*;

import java.util.Map;

public class AirtimeTopUp extends USSDTest_Config{



    UssdPojo data = new UssdPojo();

    String  Session=USSDTest_Config.generate10DigitRandomNumber();

    @BeforeClass
    public void _setup(){
        if (Session==null)
        {

            Session=    USSDTest_Config.NewSession;
            Session  =  data.getSessionID();
        }

    }


    @Test(priority=1, description="1",groups ={"regression"} ,alwaysRun = true,dataProvider="Airtime",dataProviderClass = CustomDataProvider.class,retryAnalyzer = MyRetry.class)
    public void HomeScreen_Airtime(Map<String, String> _data){



        data.setUssdString("*1234#");
        data.setUssdServiceOp("1");
        data.setSessionID(Session);
        data.setMsisdn(_data.get("phoneNumber"));
        data.setNetwork(_data.get("Telco"));


        RestAssured.given()

                .body(data)
                .when()
                .post()
                .prettyPeek()
                .then()
                .body("message", Matchers.containsString("Great! You are a step away"))
                .body("message", Matchers.containsString("Enter Fido PIN"))
                .body("message", Matchers.containsString("Account Services"));


    }

    @Test(priority = 2,groups ={"regression"} ,dataProvider="Airtime",dataProviderClass = CustomDataProvider.class)

    public void EnterPin_Airtime(Map<String, String> _data) {


        data.setUssdString("1");
        data.setUssdServiceOp("18");
        data.setSessionID(Session);
        data.setMsisdn(_data.get("phoneNumber"));
        data.setNetwork(_data.get("Telco"));


        RestAssured.given()


                .body(data)
                .when()
                .post()
                .prettyPeek()
                .then()
                .assertThat()
                .body(containsString("Enter your Fido PIN"))
                .body(containsString("Back to Menu"));


    }


    @Test(priority = 3,groups ={"regression"} ,dataProvider="Airtime",dataProviderClass = CustomDataProvider.class)

    public void AirtimeTopUp(Map<String, String> _data) {


        data.setUssdString(_data.get("generatepincode"));
        data.setUssdServiceOp("18");
        data.setSessionID(Session);
        data.setMsisdn(_data.get("phoneNumber"));
        data.setNetwork(_data.get("Telco"));


        RestAssured.given()


                .body(data)
                .when()
                .post()
                .prettyPeek()
                .then()
                .assertThat()
                .body(containsString("Welcome to Fido"))
                .body(containsString("Select an option"))
                .body(containsString("Loan Services"))
                .body(containsString("Airtime Top-up"))
                .body(containsString("Data Bundles"))
                .body(containsString("Bill Payment"))
                .body(containsString("Account Services"))
                .log().all();




    }

    @Test(priority = 4,groups ={"regression"} ,dataProvider="Airtime",dataProviderClass = CustomDataProvider.class)

    public void AirtimeReceiver(Map<String, String> _data) {


        data.setUssdString("2");
        data.setUssdServiceOp("18");
        data.setSessionID(Session);
        data.setMsisdn(_data.get("phoneNumber"));
        data.setNetwork(_data.get("Telco"));


        RestAssured.given()


                .body(data)
                .when()
                .post()
                .prettyPeek()
                .then()
                .assertThat()
                .body(containsString("Select Airtime Top-Up receiver"))
                .body(containsString(" Self"))
              .body(containsString("Other"));





    }

    @Test(priority = 5,groups ={"regression"} ,dataProvider="Airtime",dataProviderClass = CustomDataProvider.class)

    public void AirtimeAmount(Map<String, String> _data) {


        data.setUssdString("1");
        data.setUssdServiceOp("18");
        data.setSessionID(Session);
        data.setMsisdn(_data.get("phoneNumber"));
        data.setNetwork(_data.get("Telco"));


        RestAssured.given()


                .body(data)
                .when()
                .post()
                .prettyPeek()
                .then()
                .assertThat()
                .body(containsString("Enter Airtime amount and confirm Top-up for"));






    }

    @Test(priority = 6,groups ={"regression"} ,dataProvider = "Airtime",dataProviderClass = CustomDataProvider.class)
    public void SelectPaymentWallet(Map<String, String> _data) {


        data.setUssdString(_data.get("PaymentAmount"));
        data.setUssdServiceOp("18");
        data.setSessionID(Session);
        data.setMsisdn(_data.get("phoneNumber"));
        data.setNetwork(_data.get("Telco"));


        RestAssured.given()

                .body(data)
                .when()
                .post()
                .then()
                .body(containsString("Select the method of payment"))
                .body(containsString("TopUp"))
                .body(containsString("GHS"))
                .body(containsString("Account"))
                .body(containsString("ending with"))
                .log().all();


    }
            @Test
            (priority = 7,groups ={"regression"} ,
            dataProvider = "Airtime",
            dataProviderClass = CustomDataProvider.class)

    public void ConfirmPayments(Map<String, String> _data) {


        data.setUssdString("1");
        data.setUssdServiceOp("18");
        data.setSessionID(Session);
        data.setMsisdn(_data.get("phoneNumber"));
        data.setNetwork(_data.get("Telco"));


        RestAssured.given()

                .body(data)
                .when()
                .post()
                .then()
                .body(containsString("Kindly wait for an authorization prompt from your network provider"))
                .log().all();

    }
    @Test(priority = 8,groups ={"regression"} ,alwaysRun = true ,dataProvider = "Airtime",dataProviderClass = CustomDataProvider.class,retryAnalyzer = MyRetry.class)
    public void TerminateUSSD(Map<String, String> _data) {


        data.setUssdString("1");
        data.setUssdServiceOp("30");
        data.setSessionID(Session);
        data.setMsisdn(_data.get("phoneNumber"));
        data.setNetwork(_data.get("Telco"));


        RestAssured.given()

                .body(data)
                .when()
                .post()
                .prettyPeek()
                .then()
                .body(containsString("Terminated"))
                .log().all();


    }

}
