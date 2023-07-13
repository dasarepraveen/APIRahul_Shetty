package utils;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.*;
import java.util.Properties;

public class requestSpec {
    public static RequestSpecification requestSpecBuilder;
    ResponseSpecification responseSpecification;

    public RequestSpecification requestSpecifications() throws IOException {
        if(requestSpecBuilder==null){
            PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
            requestSpecBuilder = (RequestSpecification) new RequestSpecBuilder()
                    .setBaseUri(getProperties("baseUrl"))
                    .addQueryParam("key","qaclick123")
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .setContentType(ContentType.JSON).build();
            return requestSpecBuilder;
        }
        return requestSpecBuilder;

    }

    public ResponseSpecification responseSpecification(){
        responseSpecification = (ResponseSpecification) new ResponseSpecBuilder().expectStatusCode(200).
                expectContentType(ContentType.JSON).build();
        return responseSpecification;
    }

    public static String getProperties(String key) throws IOException {
        Properties properties = new Properties();
        System.out.println("baseUrl is "+System.getProperty("user.dir"));
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/test/java/utils/global.properties");
        properties.load(fis);
        return properties.getProperty(key);
    }

    public String getJsonPathResponse(Response response,String key){
        JsonPath js = new JsonPath(response.asString());
        String vv = js.get(key);
        return vv;
    }

}
