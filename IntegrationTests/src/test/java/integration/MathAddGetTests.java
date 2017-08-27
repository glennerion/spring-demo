package integration;

import java.util.Properties;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import org.junit.Test;

import integration.utils.TestProperties;

/**
 * Integration tests for the /math/add GET route.
 */
public class MathAddGetTests {

    private String testUrl= (String) TestProperties.getTestProperties().get("testUrl");

    @Test
    public void testMathAddWorksWithValidEntries() {
        get(testUrl + "/math/add?n1=10&n2=7").
                then().body(is("17.0"));
        get(testUrl + "/math/add?n1=10&n2=7.1").
                then().body(is("17.1"));
    }

    @Test
    public void testMathAddFailsWhenParamMissing() {
        get(testUrl + "/math/add?n1=10").
                then().statusCode(400);
    }
}
