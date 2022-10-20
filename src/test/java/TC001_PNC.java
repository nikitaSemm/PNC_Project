
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Scanner;

public class TC001_PNC {
    String URI="https://restcountries.com/v3.1/capital/";

    @Test(priority = 1)
    public void getCityData() {

        //Base url
        RestAssured.baseURI = URI;
        //Request object
        RequestSpecification request = RestAssured.given();


        while (true) {
            //Scanner object
            Scanner input = new Scanner(System.in);
            //Request a city
            System.out.println("Enter city capital");
            String city = input.nextLine().toLowerCase().trim();
            if (city.equals("quit")) {
                break;
            }
            System.out.println("Capital city is " + city);

            //Response object
            Response response = request.request(Method.GET, city);
            String responseBody = response.getBody().asString();
            System.out.println("responseBody = " + responseBody);

            //Check status code
            int statusCode = response.getStatusCode();
            System.out.println("statusCode = " + statusCode);
            Assert.assertEquals(statusCode, 200);

            //Check status line
            String statusLine = response.getStatusLine();
            System.out.println(statusLine);
            Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
            Assert.assertTrue(responseBody.contains("Paris"));


        }
    }

    @Test(priority = 2)
    public void getCityNegative() {
        String city = "qwerty";

        //Base url
        RestAssured.baseURI = URI;
        RequestSpecification request = RestAssured.given();

        //Response object
        Response response = request.request(Method.GET, city);
        String responseBody = response.getBody().asString();
        System.out.println("responseBody = " + responseBody);

        //Check status code
        int statusCode = response.getStatusCode();
        System.out.println("status code is: " + statusCode);
        if (statusCode <= 400) {
            Assert.assertTrue(false);
        } else {
            Assert.assertTrue(true);

        }
    }

    @Test(priority = 3)
    public void TestParis(){
        String city = "paris";

        //Base url
        RestAssured.baseURI=URI;
        //Request object
        RequestSpecification request=RestAssured.given();

        //Response object
        Response response = request.request(Method.GET,city);
        String responseBody=response.getBody().asString();
        Assert.assertTrue(responseBody.contains("France"));

    }
}