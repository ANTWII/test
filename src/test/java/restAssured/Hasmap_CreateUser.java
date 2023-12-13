package restAssured;
//STATIC PACKAGES
import static io.restassured.RestAssured.*;
import   static    io.restassured.matcher.RestAssuredMatchers.*;
import    static  org.hamcrest.Matchers.*;
//STATIC PACKAGES
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Hasmap_CreateUser {
    int id;

    @Test(priority = 1)
    public void CreateUser() {

        String name ="Williams";
        String job="leader";

        HashMap requestBody=new HashMap();
        requestBody.put("name",name);
        requestBody.put("job",job);


        RestAssured.baseURI="https://reqres.in/";
    //    id=
        var json=
        RestAssured.given()

                .contentType("application/json")
                .body(requestBody)
                .when()
                //.post("api/users").jsonPath().getInt("id");
                .post("api/users");
         id=json.jsonPath().getInt("id");
        Assert.assertEquals(json.getStatusCode(),201);


    }
    @Test(priority = 2,dependsOnMethods = {"CreateUser"})
    public void UpdateUser() {

        String name ="isaac";
        String job="teacher";

        HashMap requestBody=new HashMap();
        requestBody.put("name",name);
        requestBody.put("job",job);


        RestAssured.baseURI="https://reqres.in/";
        var json=
                RestAssured.given()
                       .param("id",id)
                        .contentType("application/json")
                        .body(requestBody)
                        .when()
                        .put("api/users/")
                        .prettyPeek()
                        .then()
                        .statusCode(200)
                        .log().all();




       //id=json.jsonPath().getInt("id");
       // Assert.assertEquals(json.getStatusCode(),200);


    }

    @Test(priority = 3)
    public void DeleteUser() {


        RestAssured.baseURI="https://reqres.in/";
                RestAssured.given()
                        .param("id",id)
                        .when()
                        .delete("api/users/")
                        .prettyPeek()
                        .then()
                        .statusCode(204)
                        .log().all();




        //id=json.jsonPath().getInt("id");
        // Assert.assertEquals(json.getStatusCode(),200);


    }
}
