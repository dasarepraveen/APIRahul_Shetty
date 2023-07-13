import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class test {
    static String output=null;

    public static void main(String[] args){
        RestAssured.baseURI="https://rahulshettyacademy.com";

        String response  = given().log().all().body(body.returnBody()).header("Content-Type","application/json")
                .queryParam("key","testabcddasare")
                .when().post("maps/api/place/add/json").
                then().log().all().assertThat().statusCode(200).assertThat().
                body("scope",equalTo("APP")).header("Server","Apache/2.4.41 (Ubuntu)").extract().
                response().asString();

        JsonPath jp = new JsonPath(response);
        output = jp.get("place_id");
        System.out.println(output);

        String output2 = given().log().all().body("{\n" +
                        "\"place_id\":\""+output+"\",\n" +
                        "\"address\":\"Amargol\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}\n").header("Content-Type","application/json").queryParam("key","qaclick123")
                .when().put("maps/api/place/update/json").then().statusCode(200).assertThat().
                body("msg",equalTo("Address successfully updated"))
                .extract().response().asString();
        System.out.println("op2 is" +output2);

        given().log().all().header("Content-Type","application/json").queryParam("key","qaclick123").
                queryParam("place_id",output)
                .when().get("maps/api/place/get/json").then().assertThat().
                body("address",equalTo("Amargol"));

    }
}
