package robot;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/robot")
public class RobotController {

	Map<String, Robot> robots = new HashMap<String, Robot>();

	@RequestMapping(value = "/place/{x}/{y}/{facing}", method = RequestMethod.GET)
    public Robot place(@PathVariable Integer x, @PathVariable Integer y, @PathVariable String facing) throws Exception {
		FacingMoving newFacing = null;
		try {
			newFacing = FacingMoving.valueOf(facing);
		} catch (IllegalArgumentException ex) { 
			throw new IllegalArgumentException("The 'Facing' parameter must be " + getAllFacingAsString());
		}
		if(x < FacingMoving.X_MIN || x > FacingMoving.X_MAX || y < FacingMoving.Y_MIN || y > FacingMoving.Y_MAX) {
        	throw new IllegalArgumentException("The 'X or Y' parameter must be between 0 and 5.");
        }
        String idRobot = String.valueOf(new Random().nextInt(100));
        Robot robot = new Robot(idRobot, x, y , newFacing.name());
        robots.put(idRobot,  robot);
		return robot;
    }

	@RequestMapping(value = "/move/{idRobot}", method = RequestMethod.GET)
    public Robot move(@PathVariable String idRobot) throws Exception {
		Robot robot = null;
		Robot movingRobot = null;

		if(robots.containsKey(idRobot)) {
			robot = robots.get(idRobot);
		} else {
			throw new IllegalArgumentException("ROBOT MISSING");
		}
		
		FacingMoving facing = FacingMoving.valueOf(robot.getFacing());
		try {
			movingRobot = facing.moving(robot);
			robot.setX(movingRobot.getX());
			robot.setY(movingRobot.getY());
		} catch (IllegalArgumentException ex) { 
			throw new IllegalArgumentException("The 'X or Y' parameter must be between 0 and 5.");
		}
		
		return movingRobot;
	}
	
	@RequestMapping(value = "/rotate/{idRobot}/{left_right}", method = RequestMethod.GET)
    public Robot rotate(@PathVariable String idRobot, @PathVariable String left_right) throws Exception {
		Robot rotatingRobot = null;
		FacingMoving newRotatingFacing = null;
		Robot robot = null;

		if(robots.containsKey(idRobot)) {
			robot = robots.get(idRobot);
		} else {
			throw new IllegalArgumentException("ROBOT MISSING");
		}
		
		try {
			newRotatingFacing = FacingMoving.valueOf(left_right);
		} catch (IllegalArgumentException ex) { 
			throw new IllegalArgumentException("The 'Facing' parameter must be " + getAllFacingAsString());
		}
		
		try {
			rotatingRobot = newRotatingFacing.rotate(robot);
			robot.setFacing(rotatingRobot.getFacing());
		} catch (IllegalArgumentException ex) { 
			throw new IllegalArgumentException("The 'Facing' parameter must be " + getAllFacingAsString());
		}
		
		return rotatingRobot;
	}

	@RequestMapping(value = "/report/{idRobot}", method = RequestMethod.GET)
    public ReportMessage report(@PathVariable String idRobot) throws Exception {
		Robot robot = null;
		
		if(robots.containsKey(idRobot)) {
			robot = robots.get(idRobot);
		} else {
			throw new IllegalArgumentException("ROBOT MISSING");
		}
		
		return new ReportMessage(robot);
	}
	
	@ExceptionHandler({IllegalArgumentException.class, NullPointerException.class})
	void handleBadRequests(HttpServletResponse response) throws IOException {
	    response.sendError(HttpStatus.BAD_REQUEST.value());
	}
	
	private String getAllFacingAsString () {
    	return FacingMoving.NORTH.name() + "," + FacingMoving.WEST.name() + "," +
    			FacingMoving.SOUTH.name() + "," + FacingMoving.EAST.name();
    }
	
}
