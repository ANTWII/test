package restAssured.Serialization_Deserialization;
import org.hamcrest.core.IsEqual;
import restAssured.Serialization_Deserialization.Serialization_Pojo;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

import org.json.JSONObject;

import org.hamcrest.Matchers;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.*;
import com.google.gson.Gson;

//Serialization (Java object to JSON):
public class Serialization {




    /*
//Serialization

    import com.google.gson.Gson;

// Create a Java object
MyObject myObject = new MyObject();
myObject.setName("John");
myObject.setAge(30);

// Serialize the Java object to JSON
Gson gson = new Gson();
String json = gson.toJson(myObject);

// Use RestAssured to send a POST request with the JSON payload
given()
    .contentType("application/json")
    .body(json)
.when()
    .post("/api/resource")
.then()
    .statusCode(200);



//Deserialization
import com.google.gson.Gson;

// Use RestAssured to send a GET request and get a JSON response
Response response = get("/api/resource");

// Deserialize the JSON response to a Java object
String jsonResponse = response.getBody().asString();
Gson gson = new Gson();
MyObject myObject = gson.fromJson(jsonResponse, MyObject.class);

// Perform assertions on the Java object
assertThat(myObject.getName()).isEqualTo("John");
assertThat(myObject.getAge()).isEqualTo(30);


//Deserialization

    import org.json.JSONObject;

// Create a Java object
MyObject myObject = new MyObject();
myObject.put("name", "John");
myObject.put("age", 30);

// Serialize the Java object to JSON
JSONObject jsonObject = new JSONObject(myObject);

// Use RestAssured to send a POST request with the JSON payload
given()
    .contentType("application/json")
    .body(jsonObject.toString())
.when()
    .post("/api/resource")
.then()
    .statusCode(200);




     */

public static void Serial (){
    Serialization_Pojo sn=new Serialization_Pojo();
    sn.setAuthToken("");
    sn.setPincodeToken("");
    sn.setUserID("");

    Gson gson=new Gson();
    String json=gson.toJson(sn);

    given()
            .contentType("application/json")
            .body(json)
            .when()
            .post("/api/resource")
            .then()
            .statusCode(200);
}


//Deserialization (JSON to Java object):

    public static void DeSerial (){
        Serialization_Pojo sn=new Serialization_Pojo();
        sn.setAuthToken("");
        sn.setPincodeToken("");
        sn.setUserID("");

        Response response = get("/api/resource");

// Deserialize the JSON response to a Java object
        String jsonResponse = response.getBody().asString();
        Gson gson  = new Gson();
        Serialization_Pojo data = gson.fromJson(jsonResponse, Serialization_Pojo.class);

// Perform assertions on the Java object
      //  assertThat(Serialization_Pojo.getAuthToken().equalsIgnoreCase(),"");

    }



    public static void JsonDeSerial (){


// Use RestAssured to send a GET request and get a JSON response
        Response response = get("/api/resource");

// Parse the JSON response into a JSONObject
        String jsonResponse = response.getBody().asString();
        JSONObject jsonObject = new JSONObject(jsonResponse);

// Create a MyObject instance and set its properties from the JSONObject
        String authToken = Serialization_Pojo.getAuthToken();
        Serialization_Pojo.setAuthToken(jsonObject.getString("name"));
        Serialization_Pojo.setUserID(jsonObject.getString("age"));

// Perform assertions on the MyObject instance
        assertThat(authToken, containsString("expectedSubstring"));
    }

}







