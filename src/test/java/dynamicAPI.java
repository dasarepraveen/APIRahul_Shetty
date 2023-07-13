//import io.restassured.RestAssured;
//import io.restassured.path.json.JsonPath;
//import io.restassured.specification.RequestSpecification;
//import org.junit.Test;
////import org.testng.annotations.DataProvider;
////import org.testng.annotations.Test;
//ß
//import java.io.File;
//
//import static io.restassured.RestAssured.*;
//
//public class dynamicAPI {
//
//
//
//    @Test
//    public void addBookfrommExternal(){
//        File filefromjson = new File("/Users/praveendasare/eclipse-workspace/RahulShettyAPI/src/test/body.json");
//        RestAssured.baseURI="http://216.10.245.166";
//        String response = given().header("Content-Type","application/json").body(filefromjson).
//                when().post("Library/Addbook.php").then().statusCode(200).extract().response().asString();
//        System.out.println("response is "+response);
//        JsonPath js = new JsonPath(response);
//        id_is = js.get("ID");
//        System.out.println(id_is);
//    }
//
//
//
//
//    String id_is=null;
//    @Test(priority = 1 ,dataProvider = "dynamic_add_book")
//    public void addBook(String isbn,String aisle){
//        RestAssured.baseURI="http://216.10.245.166";
//        String response = given().header("Content-Type","application/json").body(body.addBookBody(isbn,aisle)).
//                when().post("Library/Addbook.php").then().statusCode(200).extract().response().asString();
//        System.out.println("response is "+response);
//        JsonPath js = new JsonPath(response);
//        id_is = js.get("ID");
//        System.out.println(id_is);
//    }
//
//    @Test(priority = 2)
//    public void DeleteBook(){
//        baseURI="http://216.10.245.166";
//         String op =given().header("Content-Type", "application/json").
//                body("{\n" +
//                        "\"ID\" : \"jhgfhdgfsx9874\"\n" +
//                        "} \n").
//         when().post("Library/DeleteBook.php").then().statusCode(200).extract().response().asString();
//         System.out.println(op);
//    }
//
//    @DataProvider(name = "dynamic_add_book")
//    public Object[][] addbooksDynamically(){
//        return new Object[][]
//                {{"asdsadx","6756745"},{"bsgfdzfax","75612434"},{"cvbdsfsdfx","567562342344"},{"jhgfhdgfsx","9874"},{"iuhgvcerghx","54094"}};
//    }
//}
