package restAssured;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.Random;

public class SessionClass {

   /* public static long generate10DigitRandomNumber() {
        long min = 1_000_000_000L; // 10-digit number starts from 1,000,000,000
        long max = 9_999_999_999L; // 10-digit number ends at 9,999,999,999
        Random rand = new Random();
        return rand.nextLong() % (max - min + 1) + min;
    }

    */
    public static String generatepincode="7755";
    public static Double PaymentAmount=0.005;
    public static String phoneNumber="+233577553195";
    public static final String _baseURI="http://52.30.107.6:4554/api/ussd";

    public static  Map<String, Matcher> expectedOBjectHeaders = Map.of("Content-Type",Matchers.containsString("application/json"),"cache-control",
            Matchers.containsString("private"),
            "Cache-Control", Matchers.containsStringIgnoringCase("no-cache"),
            "Server",Matchers.containsString("Apache/2.4.52 (Ubuntu)"));
    
}
