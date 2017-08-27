package integration;

import org.junit.Test;

import integration.utils.TestProperties;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;



/**
 * Integration tests for the /math/add POST route.
 */
public class MathAddPostTests {

    private String testUrl= (String) TestProperties.getTestProperties().get("testUrl");
    @Test
    public void testMathAddWorksWithValidEntries() {
        given().
                formParam("n1", "10").
                formParam("n2", "7").
                when().post(testUrl + "/math/add").
                then().body(is("17.0"));
    }

    @Test
    public void testMathAddWorksWithFloats() {
        given().
                formParam("n1", "10.0").
                formParam("n2", "7.1").
                when().post(testUrl + "/math/add").
                then().body(is("17.1"));
    }

    @Test
    public void testMathAddWorksWithHex() {
        given().
                formParam("n1", "10").
                formParam("n2", "0x10").
                when().post(testUrl + "/math/add").
                then().body(is("26.0"));
    }

    @Test
    public void testMathAddWorksWithBinary() {
        given().
                formParam("n1", "10.0").
                formParam("n2", "0b110").
                when().post(testUrl + "/math/add").
                then().body(is("16.0"));
    }

    @Test
    public void testMathAddWorksWithOctal() {
        given().
                formParam("n1", "10.0").
                formParam("n2", "010").
                when().post(testUrl + "/math/add").
                then().body(is("18.0"));
    }

    @Test
    public void testReturnsAnErrorWhenNonNumericParameter() {
        given().
                formParam("n1", "10.0").
                formParam("n2", "test").
                when().post(testUrl + "/math/add").
                then().body(is("n1 and n2 must be numbers")).
                statusCode(400);;
    }
}
