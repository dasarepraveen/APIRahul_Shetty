package stepDefinations;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
//import org.testng.Assert;
import org.junit.Assert;
import pojo.requestPojo.addMaps;
import pojo.requestPojo.deleteAPI;
import pojo.requestPojo.location;
import reqBody.addBody;
import utils.APIResources;
import utils.requestSpec;
//import utils.responseSpec;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static io.restassured.RestAssured.given;

public class stepDefination extends requestSpec {
    RequestSpecification requestSpecBuilder;
    ResponseSpecification responseSpecbuilder;
    Response response;
    addBody addbody = new addBody();
    static String place_id;

    @Given("Add place payload with {string} {string} {string}")
    public void add_place_payload(String name,String language,String address) throws IOException {

        requestSpecBuilder = given().spec(requestSpecifications()).body(addbody.addPlace(name,language,address)).contentType(ContentType.JSON);
        responseSpecbuilder = responseSpecification();
    }
    @When("user calls {string} with with {string} http req")
    public void user_calls_with_with_post_http_req(String resource,String method) {
        //use if else condition to handle post,get,delete methods using this method variable

        APIResources apiResources = APIResources.valueOf(resource);
        System.out.println(apiResources.getResources());
        if(method.equalsIgnoreCase("post")){
            response  = requestSpecBuilder.when().post(apiResources.getResources());
//            .then().spec(responseSpecification)
//                    .extract().response();
        }
        else if(method.equalsIgnoreCase("delete")){
            response  = requestSpecBuilder.when().delete(apiResources.getResources());
        }
        else if(method.equalsIgnoreCase("get")){
            response = requestSpecBuilder.when().get(apiResources.getResources());
        }
    }

    @And("the response status code should be {int}")
    public void the_response_status_code_should_be(int actual) {
//        Assert.assertEquals(response.getStatusCode(),
//                int1);
        Assert.assertEquals(response.getStatusCode(),actual);
    }

    @And("{string} in response body is {string}")
    public void in_response_body_is(String key, String value) {
        System.out.println("response is "+response.asString());
        System.out.println("key is "+ key);
        System.out.println("expected from dynamic is "+getJsonPathResponse(response,key));
        Assert.assertEquals(getJsonPathResponse(response,key),value);
        //Assert.assertEquals(getJsonPathResponse(response,key),value);
    }

    @And("Verify PlaceID created matched with {string} using {string}")
    public void verify_place_Id_from_response(String expectedName,String resource) throws IOException {
        place_id = getJsonPathResponse(response,"place_id");
        requestSpecBuilder = given().spec(requestSpecifications()).queryParam("place_id",place_id);
        user_calls_with_with_post_http_req(resource,"GET");
        String actualName = getJsonPathResponse(response,"name");
        Assert.assertEquals(expectedName,actualName);
    }

    @Given("Delete Place Api With Paylod")
    public void Delete_Place_API() throws IOException {
        deleteAPI del = new deleteAPI();
        del.setPlace_id(place_id);
        requestSpecBuilder = given().spec(requestSpecifications());
        requestSpecBuilder.body(del);
    }
}
