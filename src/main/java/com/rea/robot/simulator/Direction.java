package com.rea.robot.simulator;

public enum Direction {
    NORTH(0, new Location(0, 1)),
    EAST(90, new Location(1, 0)),
    SOUTH(180, new Location(0, -1)),
    WEST(270, new Location(-1, 0));

    private int degrees;
    private Location location;

    Direction(int degrees, Location location) {
        this.degrees = degrees;
        this.location = location;
    }

    /**
     * Returns the degrees for this Direction object
     */
    public int getDegrees() {
        return this.degrees;
    }

    /**
     * Returns the Direction by the given degrees
     */
    public static Direction getDirectionByDegrees(int degrees) {
        for (Direction direction : Direction.values()) {
            if (direction.degrees == degrees) {
                return direction;
            }
        }
        return null;
    }

    /**
     * Returns the coordinate difference for a single movement in this direction
     */
    public Location getLocation() {
        return this.location;
    }

    /**
     * Lockup the Direction by the given name, if not found return null
     */
    public static Direction getDirectionByName(String name) {
        for (Direction direction : Direction.values()) {
            if (direction.name().equalsIgnoreCase(name)) {
                return direction;
            }
        }
        return null;
    }
}
