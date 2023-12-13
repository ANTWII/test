package restAssured;
//STATIC PACKAGES
import static io.restassured.RestAssured.*;
import   static    io.restassured.matcher.RestAssuredMatchers.*;
import    static  org.hamcrest.Matchers.*;
//STATIC PACKAGES
import io.restassured.RestAssured;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.Objects;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.containsStringIgnoringCase;

public class HeaderMapps {


    public static final String  BASE_URL="https://api.github.com";

    Map<String, String> expectedHeaders = Map.of("content-encoding", "gzip",
            "access-control-allow-origin", "*","Server","GitHub.com");


    Map<String, Matcher> expectedOBJHeaders = Map.of("cache-control",
            Matchers.containsString("public"),"Cache-Control", Matchers.containsStringIgnoringCase("max-age=60"));
    @Test
    public void usingMapsToTestHeaders() {
        RestAssured.get(BASE_URL)
                .prettyPeek()

                .then()
               .headers(
                        "content-encoding", "gzip",
                        "access-control-allow-origin",              "*",
                        "cache-control",  Matchers.containsString("public"),"Cache-Control", Matchers.containsStringIgnoringCase("max-age=60"))
                .headers(expectedOBJHeaders)
                .headers(expectedHeaders);
    }
}
