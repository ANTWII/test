package restAssured;
//STATIC PACKAGES
import static io.restassured.RestAssured.*;
import   static    io.restassured.matcher.RestAssuredMatchers.*;
import    static  org.hamcrest.Matchers.*;
//STATIC PACKAGES
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;
import static org.hamcrest.Matchers.*;
import org.hamcrest.number.OrderingComparison;
import static io.restassured.RestAssured.given;

public class PlualsightGithub {


    public static final String  base_URl="https://api.github.com";


    @Test
    public void peek_Test() {



        RestAssured.given()
                .when()
                .get(base_URl)
                .peek()
                .then()
                .statusCode(200)
              //  .statusCode(anyOf(equalTo(200),equalTo(201)))
                .contentType(ContentType.JSON)
                .header("Cache-Control", Matchers.containsStringIgnoringCase("max-age=60"))
                .header("etag", Matchers.notNullValue())
              //.header("etag",not(emptyString()))

                .time(Matchers.lessThan(2L), TimeUnit.SECONDS)

                 .header("x-ratelimit-limit", Integer::parseInt, Matchers.equalTo(60))
                .header("date", date -> LocalDate.parse(date, DateTimeFormatter.RFC_1123_DATE_TIME),
                        OrderingComparison.comparesEqualTo(LocalDate.now()))
                .header("x-ratelimit-limit",

                        response -> Matchers.greaterThan(response.header("x-ratelimit-remaining")));


    }

    @Test
    public void prettypeek_Test() {



        RestAssured.given()
                .when()
                .get(base_URl)
                .prettyPeek()
                .then()
                .statusCode(200)
              //  .statusCode(anyOf(equalTo(200), equalTo(201)))

                //.statusCode(anyOf(equalTo(200),equalTo(201)))
                .time(Matchers.lessThan(2L), TimeUnit.SECONDS)
                .header("etag", Matchers.notNullValue())
              // .header("etag",not(emptyString()))
                .header("Cache-Control", Matchers.containsStringIgnoringCase("max-age=60"))
                .header("x-ratelimit-limit", Integer::parseInt, Matchers.equalTo(60))
                .header("date", date -> LocalDate.parse(date, DateTimeFormatter.RFC_1123_DATE_TIME),
                        OrderingComparison.comparesEqualTo(LocalDate.now()))
                .header("x-ratelimit-limit",

                        response -> Matchers.greaterThan(response.header("x-ratelimit-remaining")));


    }
}
