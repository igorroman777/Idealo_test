package robot;

public class Robot {

    private String idRobot;
    private Integer x;
    private Integer y;
    private String facing;

    public Robot() {
    }

	public Robot(String idRobot, Integer x, Integer y, String facing) {
		super();
		this.idRobot = idRobot;
		this.x = x;
		this.y = y;
		this.facing = facing;
	}

	public String getIdRobot() {
		return idRobot;
	}

	public void setIdRobot(String idRobot) {
		this.idRobot = idRobot;
	}

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public String getFacing() {
		return facing;
	}

	public void setFacing(String facing) {
		this.facing = facing;
	}


}
