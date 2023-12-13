package restAssured;
//STATIC PACKAGES
import static io.restassured.RestAssured.*;
import   static    io.restassured.matcher.RestAssuredMatchers.*;
import    static  org.hamcrest.Matchers.*;
//STATIC PACKAGES
import groovy.json.JsonToken;
//import org.json.simple.JSONObject;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class FourWayPost {
//Post Requests using Hashmaps
    @Test(priority = 1)
    public void Hashmap() {

        HashMap requestBody=new HashMap();
        requestBody.put("name","James");
        requestBody.put("job","carpenter");


        RestAssured.baseURI="https://reqres.in/";

                RestAssured.given()

                        .contentType("application/json")
                        .body(requestBody)
                        .when()
                        .post("api/users")
                        .then()
                        .statusCode(201)
                        .body("name", Matchers.equalTo("James"))
                        .body("job", Matchers.equalTo("carpenter"))
                        .header("Content-Type","application/json; charset=utf-8")
                        .log().all();

    }
    //Post Requests using Json Object

    @Test(priority = 2)
    public void JsonObject() {

        JSONObject requestBody=new JSONObject();
        requestBody.put("name","James");
        requestBody.put("job","carpenter");


        RestAssured.baseURI="https://reqres.in/";

        RestAssured.given()

                .contentType("application/json")
                .body(requestBody.toString())
                .when()
                .post("api/users")
                .then()
                .statusCode(201)
                .body("name", Matchers.equalTo("James"))
                .body("job", Matchers.equalTo("carpenter"))
                .header("Content-Type","application/json; charset=utf-8")
                .log().all();







    }

    //Post Requests using pojo class

    @Test(priority = 3)
    public void pojoClass() {

        Pojo_postRequest requestBody=new Pojo_postRequest();
        requestBody.setName("James");
        requestBody.setJob("carpenter");


        RestAssured.baseURI="https://reqres.in/";

        RestAssured.given()

                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("api/users")
                .then()
                .statusCode(201)
                .body("name", Matchers.equalTo("James"))
                .body("job", Matchers.equalTo("carpenter"))
                .header("Content-Type","application/json; charset=utf-8")
                .log().all();







    }


    //Post Requests using external json file

    @Test(priority = 4)
    public void Jsonfile() throws FileNotFoundException {

        //open file Location
        File f=new File("src/test/java/restAssured/Body.json");
        //File f=new File(".\\java\\restAssured/Body.json");

        //open file using file reader
        FileReader fr=new FileReader(f);
        JSONTokener jt=new JSONTokener(fr);
        JSONObject requestBody=new JSONObject(jt);


        RestAssured.baseURI="https://reqres.in/";

        RestAssured.given()

                .contentType("application/json")
                .body(requestBody.toString())
                .when()
                .post("api/users")
                .then()
                .statusCode(201)
                .body("name", Matchers.equalTo("morpheus"))
                .body("job", Matchers.equalTo("leader"))
                .header("Content-Type","application/json; charset=utf-8")
                .log().all();
    }
}
