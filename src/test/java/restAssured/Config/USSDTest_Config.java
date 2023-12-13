package restAssured.Config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import org.hamcrest.Matchers;
import org.testng.annotations.*;

import static  io.restassured.RestAssured.DEFAULT_PATH;
import static  io.restassured.RestAssured.DEFAULT_URI;

import java.util.Random;

public class USSDTest_Config {


 public static String NewSession=null;
  public static   UssdPojo data=new UssdPojo();
@BeforeClass
    public static void setup(){

    RestAssured.requestSpecification=new RequestSpecBuilder()
            .setBaseUri("http://52.30.107.6")
            .setBasePath("/api/ussd")
            .setPort(4554)
            .setContentType("application/json")
           // .addHeader("Accept","*/*")
            .addFilter(new RequestLoggingFilter())
           // .addFilter(new ResponseLoggingFilter())
            .build();



    RestAssured.responseSpecification = new ResponseSpecBuilder()
            .expectStatusCode(200)
           // .expectHeader("Content-Type",Matchers.containsString("application/json"))
            .expectHeader("cache-control",Matchers.containsString("private"))
            .expectHeader("cache-control",Matchers.containsString("no-cache"))
            .expectHeader("Server",Matchers.containsStringIgnoringCase("Apache/2.4.52 (Ubuntu)"))
            .build();





 }
    @BeforeClass



    public static String generate10DigitRandomNumber() {
        long min = 1_000_000_000L; // 10-digit number starts from 1,000,000,000
        long max = 9_999_999_999L; // 10-digit number ends at 9,999,999,999
        Random rand = new Random();
        long randomValue = rand.nextLong() % (max - min + 1) + min;
        NewSession= Long.toString(randomValue);
        // Set global variables
        if (NewSession != null) {
            System.out.println("Setting Session: " + NewSession);
            data.setSessionID(NewSession);

        }
        return NewSession;



    }

  @AfterClass
    public void cleanup() {
        RestAssured.responseSpecification = null;
        RestAssured.requestSpecification = null;
        data.setSessionID(null);

        RestAssured.baseURI=DEFAULT_URI;
        RestAssured.basePath=DEFAULT_PATH;
       NewSession=data.getSessionID();
        System.out.println("Clearing  Session: " + NewSession);
    }









}
