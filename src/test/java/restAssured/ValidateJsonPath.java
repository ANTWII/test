package restAssured;
//STATIC PACKAGES
import static io.restassured.RestAssured.*;
import   static    io.restassured.matcher.RestAssuredMatchers.*;
import    static  org.hamcrest.Matchers.*;
//STATIC PACKAGES
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

public class ValidateJsonPath {
    public static final String  _URl="https://reqres.in/api/users";

    //Data Driven testing params
    //Data provider
    @DataProvider
    public Object[][] queryParams(){

        return new Object[][] {
               // {Map.of("q","java","per_page","1"),1} ,
                //{Map.of("q","python","per_page","2"),2} ,
                {Map.of("page","2"),200}

        };
    }
    //this is a dummy test to calculate the total price in a json object, this isnt real
    @Test()
    public void CalculateTotal() {


        Response json=
                RestAssured.given()

                        .when()
                        .get(_URl);
        //.prettyPeek();

        //JsonObject Class
        JSONObject jo=new JSONObject(json.asString());   //remener to pasrse it asString
        double totalPrice=0;
        for(int i=0;i<jo.getJSONArray("data").length();i++)
        {
            String Price_= jo.getJSONArray("data").getJSONObject(i).get("price").toString();
            totalPrice=totalPrice + Double.parseDouble(Price_);
        }
        Assert.assertEquals(totalPrice,1000);
    }
    //this will help handle dynamic json paths
    @Test(dataProvider = "queryParams")
    public void DynamicJsonPath_4(Map<String,String> Dataparams,int expectedCount) {


        Response json=
                RestAssured.given()
                        .params(Dataparams)
                        .when()
                        .get(_URl);
        //.prettyPeek();

        //JsonObject Class
        JSONObject jo=new JSONObject(json.asString());   //remener to pasrse it asString
boolean status=false;
        for(int i=0;i<jo.getJSONArray("data").length();i++)
        {
            String email_Address= jo.getJSONArray("data").getJSONObject(i).get("email").toString();
            if(email_Address.equals("tobias.funke@reqres.in"))
            {
                status=true;
                break;
            }
        }
        Assert.assertEquals(status,true);
    }

    @Test(dataProvider = "queryParams")
    public void JsonPath_3(Map<String,String> Dataparams,int expectedCount) {


        Response json=
                RestAssured.given()
                        .params(Dataparams)
                        .when()
                        .get(_URl);
                        //.prettyPeek();

       //JsonObject Class
        JSONObject jo=new JSONObject(json.asString());   //remener to pasrse it asString

         for(int i=0;i<jo.getJSONArray("data").length();i++)
        {
           String email_Address= jo.getJSONArray("data").getJSONObject(i).get("email").toString();
           System.out.println(email_Address );
        }
    }

    @Test(dataProvider = "queryParams")
    public void JsonPath_2(Map<String,String> Dataparams,int expectedCount) {


        Response json=
                RestAssured.given()
                        .params(Dataparams)
                        .when()
                        .get(_URl)
                                .prettyPeek();

        Assert.assertEquals(json.getStatusCode(),expectedCount);
        Assert.assertEquals(json.jsonPath().getString("data[1].first_name"),"Lindsay");
        Assert.assertEquals(json.header("Content-Type"),"application/json; charset=utf-8");
        Assert.assertEquals(json.jsonPath().get("support.url"),"https://reqres.in/#support-heading");
        Assert.assertEquals(json.jsonPath().get("data[5].last_name"),"Howell");


    }



    @Test(dataProvider = "queryParams")
    public void JsonPath_1(Map<String,String> Dataparams,int expectedCount) {



        RestAssured.given()
                .params(Dataparams)
                .when()
                .get(_URl)
              .prettyPeek()
                .then()
                .body("data[5].email", Matchers.equalTo("rachel.howell@reqres.in"))
               // .body("data[5].email", hasItem("rachel.howell@reqres.in"))


                .log().all();




    }
}
