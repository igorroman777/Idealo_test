package robot;

public class ReportMessage {

    private String message;
    private Robot robot;

    public ReportMessage() {
    }

    public ReportMessage(Robot robot) {
        this.robot = robot;
        this.message = robot.getX() + "," + robot.getY() + "," + robot.getFacing();
    }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Robot getRobot() {
		return robot;
	}

	public void setRobot(Robot robot) {
		this.robot = robot;
	}

    
}
