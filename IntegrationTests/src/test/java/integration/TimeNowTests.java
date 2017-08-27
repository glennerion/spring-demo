package integration;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.junit.Test;

import com.google.gson.Gson;
import integration.utils.TestProperties;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.get;
import static io.restassured.path.json.JsonPath.from;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertTrue;


/**
 * Integration tests for the /time/now GET route.
 */
public class TimeNowTests {
    private static final Gson gson = new Gson();

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm:ss a");
    private String testUrl= (String) TestProperties.getTestProperties().get("testUrl");

    @Test
    public void testTimeIsClose() {
        LocalTime now = LocalTime.now();
        Response response = get(testUrl + "/time/now").
          then().contentType(ContentType.JSON).extract().response();
        String jsonResponse = response.asString();
        assertThat(from(jsonResponse).get("timezone"), equalTo("America/Edmonton"));

        // the call takes some time, typically 2 seconds, so need to make sure that the returned time is within a limit.
        LocalTime jsonTime = LocalTime.parse(from(jsonResponse).get("time"), formatter);
        long seconds = ChronoUnit.SECONDS.between(jsonTime, now);
        assertTrue("the time should be within 5 seconds of the returned time", Math.abs(seconds) < 5);
    }


}
