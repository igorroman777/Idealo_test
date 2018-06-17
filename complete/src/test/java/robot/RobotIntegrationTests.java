package robot;

import javax.servlet.http.HttpServletResponse;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import com.jayway.restassured.RestAssured;
import static com.jayway.restassured.RestAssured.when;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
public class RobotIntegrationTests {

    @LocalServerPort
    private int port;

    @Before
    public void setup() {
    	RestAssured.port = port;
    }

    @Test
    public void getMoving() throws Exception {

        String idRobot = "1";
        
        when().get("/robot/move/{idRobot}", idRobot)
        .andReturn()
        .then().statusCode(HttpServletResponse.SC_BAD_REQUEST)
        .body("message", Matchers.is("ROBOT MISSING"));
    }


}
