import io.restassured.path.json.JsonPath;
import org.junit.Assert;
//import org.testng.Assert;

public class complexJsonParse {
    public static void main(String[] args){
        JsonPath jsx = new JsonPath(body.mock());
        int size = jsx.getInt("courses.size()");
        Assert.assertEquals(jsx.getInt("courses.size()"),3);
        int purchaseAmount = jsx.get("dashboard.purchaseAmount");
        System.out.println(purchaseAmount);
        String firstCourse = jsx.get("courses[0].title");
        System.out.println(firstCourse);
        int[] courselen = new int[size];

//        String secondcourse = jsx.get("courses[1].title");
//        String thirdcourse = jsx.get("courses[2].title");
//        int firstCoursePrice = jsx.get("courses[0].price");
//        int secondCoursePrice = jsx.get("courses[1].price");
//        int thirdCoursePrice = jsx.get("courses[2].price");
//        System.out.println(secondcourse+" "+thirdcourse+" "+firstCoursePrice+" "+secondCoursePrice+" "+thirdCoursePrice);
//        int rpaCopies = jsx.get("courses[2].copies");
//        System.out.println(rpaCopies);
//        int totlaprice = firstCoursePrice+secondCoursePrice+thirdCoursePrice;
//        Assert.assertEquals(purchaseAmount,);



    }
}
