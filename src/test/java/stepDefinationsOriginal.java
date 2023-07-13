//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import io.restassured.builder.ResponseSpecBuilder;
//import io.restassured.http.ContentType;
//import io.restassured.path.json.JsonPath;
//import io.restassured.response.Response;
//import io.restassured.specification.RequestSpecification;
//import io.restassured.specification.ResponseSpecification;
//import org.testng.Assert;
//import reqBody.addBody;
//import utils.requestSpec;
//
//import static io.restassured.RestAssured.given;
//
//public class stepDefinationsOriginal extends requestSpec {
//    RequestSpecification req;
//    ResponseSpecification responseSpecification;
//    Response response;
//    addBody addbody = new addBody();
//    @Given("Add place payload")
//    public void add_place_payload() {
//        req = given().spec(requestSpecifications()).body(addbody.addPlace());
//        responseSpecification = (ResponseSpecification) new ResponseSpecBuilder().expectStatusCode(200).
//                expectContentType(ContentType.JSON).build();
//
//    }
//    @When("user calls {string} with with post http req")
//    public void user_calls_with_with_post_http_req(String string) {
//        response = req.header("Content-Type","application/json").
//                when().post("maps/api/place/add/json").
//                then().spec(responseSpecification).extract().response();
//        String responseString = response.asString();
//        System.out.println(responseString);
//    }
//    @Then("the response status code should be {int}")
//    public void the_response_status_code_should_be(Integer int1) {
//        Assert.assertEquals(response.getStatusCode(),200);
//        // Write code here that turns the phrase above into concrete actions
//        //    throw new io.cucumber.java.PendingException();
//    }
//    @Then("{string} in response body is {string}")
//    public void in_response_body_is(String key, String value) {
//        System.out.println("key values are "+key +" & "+value);
//        String output = response.asString();
//        JsonPath jsonPath = new JsonPath(output);
//        Assert.assertEquals(jsonPath.get(key),value);
//        Assert.assertEquals(jsonPath.get(key),value);
//        // Write code here that turns the phrase above into concrete actions
//        //  throw new io.cucumber.java.PendingException();
//    }
//}
