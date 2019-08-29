package com.rea.robot.simulator;

import com.rea.robot.exceptions.ToyRobotException;


public class ToyRobot {
    private static final int LEFT = -90;
    private static final int RIGHT = 90;
    private static final int FULL_CIRCLE = 360;


    private Location location;
    private Direction facingDirection;
    private SquareTable surface;

    /** Creates a new Robot and places it on the given surface */
    public ToyRobot(Location location, Direction direction, SquareTable surface) throws ToyRobotException {
        //this.place(location, direction, surface);
        this.setFacingDirection(direction);
        this.setLocation(location);
        this.setSurface(surface);
    }

    /** Creates a new Robot and places it on the given surface. */
    public ToyRobot place(Location location, Direction direction)  throws ToyRobotException {
        surface = getSurface();
        if (!surface.isCoordinateWithinBounds(location)) {
                throw new ToyRobotException("The Coordinate"+location + " is outside the boundaries of the new surface.");
        }
        this.setFacingDirection(direction);
        this.setLocation(location);     
        return this;
    }
	
    /** Rotates the Robot 90 degrees left. */
    public ToyRobot rotateLeft() throws ToyRobotException {
        this.setFacingDirection(this.facingDirection.getDegrees() + LEFT);
        return this;
    }

    /** Rotates the Robot 90 degrees Right. */
    public ToyRobot rotateRight() throws ToyRobotException {
        this.setFacingDirection(this.facingDirection.getDegrees() + RIGHT);
        return this;
    }

    /** Moves the Robot one unit forward. */
    public ToyRobot moveForward() throws ToyRobotException {
        surface = getSurface();
        facingDirection = getFacingDirection();
        Location new_location = location.changePosition(facingDirection.getLocation());
        if (!surface.isCoordinateWithinBounds(new_location)) {
                    throw new ToyRobotException("The movement has put the robot out of boundaries");
            }
            this.setLocation(new_location);
            return this;       
    }
	
    /** Print out the Robot's current position.*/
    public String report() {
        return this.location + " " + this.facingDirection.name();
    }

    public String toString() {
        return report();
    }

    /** Sets the robot's facing direction by given degrees. This translates it to the
     *   internally stored Enum */
    private void setFacingDirection(int degrees) throws ToyRobotException {
        degrees = getActualDegrees(degrees);
        Direction facingDirection = Direction.getDirectionByDegrees(degrees);

        if (facingDirection == null) {
                throw new ToyRobotException("The degrees ("+degrees+") didn't map to a named direction.");
        }

        this.setFacingDirection(facingDirection);
    }

    /** Computes the actual degrees taking into account that negative degrees
     *   should translate correctly into positive degrees, and that any degrees over
     *   a full circle should translate back into < 360 degrees*/
    public static int getActualDegrees(int degrees) {
        if (degrees >= 0 && degrees < FULL_CIRCLE) {
                return degrees;
        }

        if (degrees < 0) {
                return getActualDegrees(degrees + FULL_CIRCLE);
        }

        return degrees % FULL_CIRCLE;
    }

    /** The coordinate location of the Robot */
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    /** The direction the Robot faces */
    public Direction getFacingDirection() {
        return this.facingDirection;
    }

    public void setFacingDirection(Direction facingDirection) {
        this.facingDirection = facingDirection;
    }

    /** The surface that the robot is moving on */
    public SquareTable getSurface() {
        return this.surface;
    }

    public void setSurface(SquareTable surface) {
        this.surface = surface;
    }
}
