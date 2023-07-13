import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import pojo.requestPojo.login;
import pojo.requestPojo.orderDetails;
import pojo.requestPojo.orders;
import pojo.responsePojo.addProductResponse;
import pojo.responsePojo.createOrderResponse;
import pojo.responsePojo.loginResponse;
import pojo.responsePojo.gertOrderDetails;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class EcommnerAPitest {
    public static void main(String[] args){
        RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .setContentType(ContentType.JSON).build();

        login ll = new login();
        ll.setUserEmail("appledasare@gmail.com");
        ll.setUserPassword("Guruji6@");

        RequestSpecification reqlogin = given().log().all().spec(req).body(ll);

         loginResponse loginresponse  =  reqlogin.when().post("api/ecom/auth/login").
                 then().log().all().extract().response().as(loginResponse.class);
         System.out.println("token is "+loginresponse.getToken());
        System.out.println("Id is" +loginresponse.getUserId());

        //CreateProduct

        RequestSpecification reqForAddproduct = new RequestSpecBuilder().
                setBaseUri("https://rahulshettyacademy.com").addHeader("Authorization",loginresponse.getToken()).build();

        RequestSpecification reqforCreateWithBody = given().log().all().spec(reqForAddproduct)
                .param("productName","testproduct")
                .param("productAddedBy",loginresponse.getUserId())
                .param("productCategory","fashion")
                .param("productSubCategory","shirts")
                .param("productPrice","11500")
                .param("productDescription","Addias Originals")
                .param("productFor","women")
                .multiPart("productImage",new File("/Users/praveendasare/Postman/files/test.png"));

        addProductResponse addproductresponse = reqforCreateWithBody.when().post("api/ecom/product/add-product").
                then().extract().response().as(addProductResponse.class);

        System.out.println("message is "+addproductresponse.getMessage());
        System.out.println("productid is "+addproductresponse.getProductId());

        //createOrder
        RequestSpecification reqforCreateorder = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .addHeader("Authorization",loginresponse.getToken())
                .setContentType(ContentType.JSON).build();

        orderDetails orderdetails = new orderDetails();
        orderdetails.setCountry("India");
        orderdetails.setProductOrderedId(addproductresponse.getProductId());

        orders or = new orders();
        List<orderDetails> orrr = new ArrayList<orderDetails>();
        orrr.add(orderdetails);
        or.setOrders(orrr);


        RequestSpecification reqforCreateordewithBody = given().spec(reqforCreateorder).body(or);
        createOrderResponse createorderresponse  = reqforCreateordewithBody.when()
                .post("api/ecom/order/create-order").then().extract().response().as(createOrderResponse.class);
        System.out.println("order id "+createorderresponse.getOrders().get(0));
        System.out.println("product id "+createorderresponse.getProductOrderId().get(0));
        System.out.println("message id "+createorderresponse.getMessage());

        //getOrderDetails
        RequestSpecification getorderDetails = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .setContentType(ContentType.JSON).addHeader("Authorization",loginresponse.getToken())
                .addQueryParam("id",createorderresponse.getOrders().get(0)).build();

        gertOrderDetails getoderdetails = given().spec(getorderDetails)
                .when().get("api/ecom/order/get-orders-details").as(gertOrderDetails.class);

        System.out.println("id " +getoderdetails.getData().get_id());
        System.out.println("order-By_Id " +getoderdetails.getData().getOrderById());
        System.out.println("order-By_Id " +getoderdetails.getMessage());

        //delete
        RequestSpecification reqfordelete = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .addHeader("Authorization",loginresponse.getToken())
                .setContentType(ContentType.JSON).build();

        RequestSpecification resd = given().spec(reqfordelete).pathParam("productId",addproductresponse.getProductId());

        String finaloutput = resd.when().log().all().delete("api/ecom/product/delete-product/{productId}")
                .then().extract().asString();
        System.out.println(finaloutput);
    }
}
