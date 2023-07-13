import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.requestPojo.addMaps;
import pojo.requestPojo.location;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class specBuildertest {
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

        RequestSpecification requestSpecBuilder = (RequestSpecification) new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com")
                .addQueryParam("key","qaclick123").
                setContentType(ContentType.JSON).build();




        RequestSpecification res = given().spec(requestSpecBuilder);

        ResponseSpecification responseSpecification = (ResponseSpecification) new ResponseSpecBuilder().expectStatusCode(200).
                expectContentType(ContentType.JSON).build();

        Response response = res.body(am).header("Content-Type","application/json").
        when().post("maps/api/place/add/json").
                then().spec(responseSpecification).extract().response();
        String responseString = response.asString();
        System.out.println(responseString);
    }
}
