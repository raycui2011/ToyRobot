package com.rea.robot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.rea.robot.simulator.CommandProcessor;
import com.rea.robot.simulator.Location;
import com.rea.robot.simulator.SquareTable;
import com.rea.robot.exceptions.ToyRobotException;


/** The main class for executing the application */
public class RunRobot {
	
	public static void main(String[] args) {
    SquareTable table;
    CommandProcessor processor = new CommandProcessor();

    while (!processor.isCommandsFinished()) {
        System.out.println("Enter a command, Valid commands are:");
        System.out.println("\'PLACE X,Y,NORTH|SOUTH|EAST|WEST\', MOVE, LEFT, RIGHT, REPORT or EXIT");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String commandStr;

        try {
                commandStr = reader.readLine();
                if (commandStr != null) {
                        processor.executeCommand(commandStr);
                }
        } catch (IOException e) {
                System.out.println("IO Error:");
                e.printStackTrace();
        } catch (ToyRobotException e) {
                System.out.println(e.getMessage());
        }
    }
	}

}