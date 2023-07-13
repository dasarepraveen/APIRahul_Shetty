//package utils;
//
//import io.restassured.builder.ResponseSpecBuilder;
//import io.restassured.http.ContentType;
//import io.restassured.specification.ResponseSpecification;
//
//public class responseSpec {
//    ResponseSpecification responseSpecification;
//    public ResponseSpecification responseSpecification(){
//        responseSpecification = (ResponseSpecification) new ResponseSpecBuilder().expectStatusCode(200).
//                expectContentType(ContentType.JSON).build();
//        return responseSpecification;
//    }
//}