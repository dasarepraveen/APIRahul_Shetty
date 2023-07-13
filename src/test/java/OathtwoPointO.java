import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.Assert;
import pojo.Courses;
import pojo.getCourserespons;

import static io.restassured.RestAssured.*;

public class OathtwoPointO {
    public static void main(String[] args) throws InterruptedException {

//        WebDriverManager.chromedriver().setup();
//        WebDriver driver = new ChromeDriver();
//        driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php");
//        Thread.sleep(3000);
//        driver.findElement(By.xpath("//input[@id='identifierId']")).sendKeys("praveendasare.123@gmail.com");
//        driver.findElement(By.xpath("//span[text()=\"Next\"]")).click();
//        Thread.sleep(3000);
//        driver.findElement(By.xpath("//input[@type='password']")).sendKeys("SadashivGuruji9@");
//        driver.findElement(By.xpath("//span[text()=\"Next\"]")).click();
//        Thread.sleep(3000);
//        String codeurl = driver.getCurrentUrl();
        String codeurl ="https://rahulshettyacademy.com/getCourse.php?code=4%2F0AVHEtk4u39lw5X7Wy4BZxGI3DtT6Smp7DR5eFES2MR3tQvZEtdqG7cz_QqNtsQugLp7drw&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=1&prompt=none";
        String[] splitcode = codeurl.split("code=");
        String nextindex= splitcode[1];
        String[] getcode = nextindex.split("&scope");
        String code = getcode[0];
        System.out.println("Code is "+code);

        String accessTokenresponse = given().urlEncodingEnabled(false).queryParams("code",code).
                queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com").
                queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W").
                queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php").
                queryParams("grant_type","authorization_code").
        when().log().all().post("https://www.googleapis.com/oauth2/v4/token").asString();

        System.out.println("accesstoken is "+accessTokenresponse);
        JsonPath js = new JsonPath(accessTokenresponse);
        String accessToken = js.getString("access_token");

        getCourserespons cource = given().queryParam("accesstoken",accessToken).expect().defaultParser(Parser.JSON).
                when().get("https://rahulshettyacademy.com/getCourse.php").as(getCourserespons.class);
        System.out.println("response is "+cource.getLinkedIn());
    }
}
