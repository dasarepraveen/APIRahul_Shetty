import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;

import static io.restassured.RestAssured.given;

public class jiraTest {
    public static void main(String[] args){
        RestAssured.baseURI="http://localhost:8080/";
        //SessionFilter session = new SessionFilter();
         given().header("Content-Type","application/json").
               body("{\n" +
                       "    \"username\": \"praveendasare.123\",\n" +
                       "    \"password\": \"Jira6@\"\n" +
                       "}").log().all()
                .when().post("rest/auth/1/session").then().log().all();
        //System.out.println("response is "+response+ " session is "+session);
    }
}
