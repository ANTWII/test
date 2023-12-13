package restAssured;
//STATIC PACKAGES
import static io.restassured.RestAssured.*;
import   static    io.restassured.matcher.RestAssuredMatchers.*;
import    static  org.hamcrest.Matchers.*;
//STATIC PACKAGES
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.Map;

public class ParamClass {

//FULL URL ->https://api.github.com/search/repositories?q=java&per_page=1&page=2
    public static final String  _URl="https://api.github.com/search/repositories";

    //PARAM
    @Test
    public void param_1() {



        RestAssured.given()
                .param("q","java")
                .param("per_page","1")
                .when()
                .get(_URl)
                .prettyPeek()
                .then()
                .statusCode(200);
                //  .statusCode(anyOf(equalTo(200),equalTo(201)))
                //  .header("etag",not(emptyString())
    }
//PARAMS
    @Test
    public void paramS() {



        RestAssured.given()
                .params("q","java","per_page","1")
                .when()
                .get(_URl)
                .prettyPeek()
                .then()
                .statusCode(200);
        //  .statusCode(anyOf(equalTo(200),equalTo(201)))
        //  .header("etag",not(emptyString())
    }

    //Data Driven testing params
    //Data provider
    @DataProvider
    public Object[][] queryParams(){

           return new Object[][] {
                   {Map.of("q","java","per_page","1"),1} ,
                   {Map.of("q","python","per_page","2"),2} ,
                   {Map.of("q","c#","per_page","5"),5} ,

           };
    }
    @Test(dataProvider = "queryParams")
    public void param_Dataprovider(Map<String,String> Dataparams,int expectedCount) {


var json=
        RestAssured.given()
                .params(Dataparams)
                .when()
                .get(_URl).jsonPath()
                .prettyPeek();
        Assert.assertEquals(json.getInt("items.size()"),expectedCount);

    }
    @DataProvider
    public Object[][] queryParams2(){

        return new Object[][] {
                {Map.of("q","java","per_page","1"),200} ,
                {Map.of("q","python","per_page","2"),200} ,
                {Map.of("q","c#","per_page","5"),200} ,

        };
    }
    @Test(dataProvider = "queryParams2")
    public void param_Dataprovider2(Map<String,String> Dataparams,int expectedCount) {


        var json=
                RestAssured.given()
                        .params(Dataparams)
                        .when()
                        .get(_URl)
                        .prettyPeek();
        Assert.assertEquals(json.getStatusCode(),expectedCount);

    }


    //PATH PARAMS
    @Test
    public void Pathparam() {


        Map<String,String> reusableParam= Map.of("q","java","per_page","1");

        RestAssured.given()
                .pathParams(reusableParam)
                .when()
                .get(_URl+"?q={q}&per_page={per_page}")
                .prettyPeek()
                .then()
                .statusCode(200);
        //  .statusCode(anyOf(equalTo(200),equalTo(201)))
        //  .header("etag",not(emptyString())
    }
}

