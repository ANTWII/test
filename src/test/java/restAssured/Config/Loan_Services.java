package restAssured.Config;

import static org.hamcrest.Matchers.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.annotations.*;

import java.util.Map;

public class Loan_Services extends USSDTest_Config{

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


    @Test
            (priority=1, description="this test depends on ",groups ={"regression"} ,dataProvider="TestData",
                    dataProviderClass = CustomDataProvider.class,retryAnalyzer = MyRetry.class)
    public void HomeScreen(Map<String, String> _data) throws JsonProcessingException {



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

  @Test(priority = 2,groups ={"regression"} ,dependsOnMethods = {"HomeScreen"}, dataProvider = "TestData",dataProviderClass = CustomDataProvider.class)

    public void EnterPin(Map<String, String> _data) {


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

    @Test(priority = 3,groups ={"regression"} , dataProvider = "TestData",dataProviderClass = CustomDataProvider.class)
    public void SelectLoanService(Map<String, String> _data) {


        data.setUssdString(_data.get("generatepincode"));
        data.setUssdServiceOp("18");
        data.setSessionID(Session);
        data.setMsisdn(_data.get("phoneNumber"));
        data.setNetwork(_data.get("Telco"));


        RestAssured.given()


                .body(data)
                .when()
                .post()
                .then()
                .body(containsString("Loan Services"))
                .body(containsString("Airtime Top-up"))
                .body(containsString("Data Bundles"))
                .body(containsString("Bill Payment"))
                .body(containsString("Account Services"))

                .log().all();



    }

    @Test(priority = 4,groups ={"regression"}  ,dataProvider = "TestData",dataProviderClass = CustomDataProvider.class)
    public void RepaymentOptions(Map<String, String> _data) {


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
                .body(containsString("Total Due"))
                .body(containsString("Due Date"))
                .body(containsString("Repay Full Loan Amount"))
                .body(containsString("Repay Own Amount"))
                .body(containsString("Get Help"))

                .log().all();




    }

    @Test(priority = 5,groups ={"regression"} ,dataProvider = "TestData",dataProviderClass = CustomDataProvider.class)
    public void EnterAmount(Map<String, String> _data) {


        data.setUssdString("2");
        data.setUssdServiceOp("18");
        data.setSessionID(data.getSessionID());
        data.setMsisdn(_data.get("phoneNumber"));
        data.setNetwork(_data.get("Telco"));


        RestAssured.given()

                .body(data)
                .when()
                .post()
                .then()
                .body(containsString("Enter amount"))
                .body(containsString("Back to Menu"))
                .log().all();

    }

    @Test(priority = 6,groups ={"regression"}  ,dataProvider = "TestData",dataProviderClass = CustomDataProvider.class)
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
                .body(containsString("Select a preferred wallet to make a payment of"))
                .body(containsString("Back to Menu"))
                .log().all();


    }
    @Test(priority = 7,groups ={"regression"} , dataProvider = "TestData",dataProviderClass = CustomDataProvider.class)
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
                .body(containsString("Kindly enter PIN code to complete the payment"))
                .body(containsString("A payment prompt will be sent to the"))
                .log().all();

    }


    @Test(priority = 8,groups ={"regression"} ,alwaysRun = true ,dataProvider = "TestData",dataProviderClass = CustomDataProvider.class,retryAnalyzer = MyRetry.class)
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
