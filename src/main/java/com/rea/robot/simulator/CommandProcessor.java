package com.rea.robot.simulator;

import java.util.Optional;
import com.rea.robot.exceptions.ToyRobotException;

public class CommandProcessor {
	private static final String COMMAND_PLACE = "PLACE";
	private static final String COMMAND_LEFT = "LEFT";
	private static final String COMMAND_RIGHT = "RIGHT";
	private static final String COMMAND_MOVE = "MOVE";
	private static final String COMMAND_REPORT = "REPORT";
	private static final String COMMAND_EXIT = "EXIT";
	
	private ToyRobot robot = null;
	SquareTable table = null;
	boolean commandsFinished = false;
	
	
/** Interprets the command and executes the action on the robot. */
	public void executeCommand(String commandStr) throws ToyRobotException {
    String[] commandSection = commandStr.split(" ", 2);

    String command = commandSection[0].toUpperCase();
    String errorMessage = "Cannot process any commands until the robot has been PLACED.";
    try {
        System.out.println(command);
        switch (command) {
             case COMMAND_PLACE:
                place(commandSection[1]);
                break;
             case COMMAND_EXIT:
                this.commandsFinished = true;
                break;
             case COMMAND_LEFT:
                robot.rotateLeft();
                break;
             case COMMAND_RIGHT:
                robot.rotateRight();
                break;
             case COMMAND_MOVE:
                robot.moveForward();
                break;
             case COMMAND_REPORT:
                System.out.println(robot.report());
                break;
             default:
                System.out.println("Invalid command.");
                break;
        }
    } catch (NullPointerException e) {
        throw new ToyRobotException(errorMessage);
    }
	}

	/** Runs the more complex PLACE command on the robot */
	private void place(String commandStr) throws ToyRobotException {
    String[] commandParams = commandStr.split(",");
    if (commandParams.length != 3) {
        throw new ToyRobotException("Exactly 3 parameters are required");
    }

    int x = convertCoordinateToInt(commandParams[0]);
    int y = convertCoordinateToInt(commandParams[1]);

    Direction direction = Direction.getDirectionByName(commandParams[2].trim());

    if (direction == null) {
        throw new ToyRobotException("Direction was not a valid direction");
    }
    
    
    robot = new ToyRobot(new Location(x, y), direction, new SquareTable(5, 5));
   
    setRobot(robot);
    table = robot.getSurface();
    if (table.isCoordinateWithinBounds(new Location(x, y))) {
        robot.place(new Location(x, y), direction);
    } else {
        throw new ToyRobotException("Location is invalid");
    }
	}

	/** This function is used to convert coordinate from String to Integer */
	private int convertCoordinateToInt(String coordinate) throws ToyRobotException {
    coordinate = coordinate.trim();

    if (coordinate.equals(""))
        throw new ToyRobotException("parameter must be set");

    try {
            return Integer.parseInt(coordinate);
    } catch (NumberFormatException e) {
            throw new ToyRobotException("parameter is not numeric");
    }
	}
	
	/** Check the command execution is finished or not */
	public boolean isCommandsFinished() {
    return this.commandsFinished;
	}
 
	public void setRobot(ToyRobot robot) {
     this.robot = robot;
 }
	/** Returns the internal Robot object,  for testing use.*/
	public ToyRobot getRobot() {
    return this.robot;
	}
}
