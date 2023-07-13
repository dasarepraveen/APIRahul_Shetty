import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojo.requestPojo.addMaps;
import pojo.requestPojo.location;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class serializeTest {
    public static void main(String[] args){
        addMaps am = new addMaps();
        am.setAccuracy("50");
        am.setAddress("Eno ondu");
        am.setLanguage("Kannad");
        location l = new location();
        l.setLat(-38.38);
        l.setLng(33.42);
        am.setLocation(l);
        am.setName("Explore");
        am.setPhone_number("984582020");
        List<String> mylist = new ArrayList<>();
        mylist.add("shoes");
        mylist.add("shops");
        am.setTypes(mylist);
        am.setWebsite("https://www.abcd.com");
        RestAssured.baseURI="https://rahulshettyacademy.com";
        Response res = given().queryParam("key","qaclick123").
        body(am).header("Content-Type","application/json").
        when().post("maps/api/place/add/json").
                then().statusCode(200).extract().response();
        String responseString = res.asString();
        System.out.println(responseString);
    }
}
