package robot;


public enum FacingMoving {
	// rotate
	LEFT (-1),
	RIGHT (0),
	// moving
	NORTH (1),
	WEST (2),
	SOUTH (3), 
	EAST (4);
	
	public static final Integer X_MIN = 0;
	public static final Integer Y_MIN = 0;
	public static final Integer X_MAX = 5;
	public static final Integer Y_MAX = 5;

	Robot moving(Robot robot) {
		Integer newX, newY;
		
        switch (this) {
            case NORTH:
            	newX = robot.getX();
            	newY = robot.getY() + 1;
            	if(newY > Y_MAX) {
            		throw new IllegalArgumentException("The 'X or Y' parameter must be ...");
            	}
                return new Robot(robot.getIdRobot(), newX, newY, robot.getFacing());
            case SOUTH:
            	newX = robot.getX();
            	newY = robot.getY() - 1;
            	if(newY < Y_MIN) {
            		throw new IllegalArgumentException("The 'X or Y' parameter must be ...");
            	}
                return new Robot(robot.getIdRobot(), newX, newY, robot.getFacing());
            case EAST:
            	newX = robot.getX() + 1;
            	newY = robot.getY();
            	if(newX > X_MAX) {
            		throw new IllegalArgumentException("The 'X or Y' parameter must be ...");
            	}
                return new Robot(robot.getIdRobot(), newX, newY, robot.getFacing());
            case WEST:
            	newX = robot.getX() - 1;
            	newY = robot.getY();
            	if(newX < X_MIN) {
            		throw new IllegalArgumentException("The 'X or Y' parameter must be ...");
            	}
                return new Robot(robot.getIdRobot(), newX, newY, robot.getFacing());
            default:
                throw new AssertionError("Unknown operations " + this);
        }
    }

	Robot rotate(Robot robot) {
		FacingMoving newFacing;
		int facingId;
		int newFacingId;
		FacingMoving facing = FacingMoving.valueOf(robot.getFacing());
		
        switch (this) {
            case LEFT:
            	facingId = facing.getId();
            	newFacingId = facingId + 1;
            	if(newFacingId == 5) {
            		newFacingId = 1;
            	}
            	newFacing = byId(newFacingId);
                return new Robot(robot.getIdRobot(), robot.getX(), robot.getX(), newFacing.name());
            case RIGHT:
            	facingId = facing.getId();
            	newFacingId = facingId - 1;
            	if(newFacingId == 0) {
            		newFacingId = 4;
            	}
            	newFacing = byId(newFacingId);
            	return new Robot(robot.getIdRobot(), robot.getX(), robot.getX(), newFacing.name());
            default:
                throw new AssertionError("Unknown operations " + this);
        }
    }
	
	private final int id;

	private FacingMoving(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
	
    public static FacingMoving byId(int id) {
        for (FacingMoving facing : FacingMoving.values()) {
            if (facing.id == id) {
                return facing;
            }
        }
        return null;
    }
    
}
