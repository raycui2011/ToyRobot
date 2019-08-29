import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import com.rea.robot.simulator.CommandProcessor;
import com.rea.robot.simulator.Location;
import com.rea.robot.simulator.SquareTable;
import com.rea.robot.simulator.Direction;
import com.rea.robot.exceptions.ToyRobotException;

@RunWith(JUnit4.class)
public class CommandProcessorTest  {
	SquareTable table;
	CommandProcessor processor;
	
	@Before
	public void initialise() throws ToyRobotException {
		this.table = new SquareTable(5,5);
		processor = new CommandProcessor();
                
	}

	@Test
	public void testExecuteCommandWithRobotNotPlaced() throws ToyRobotException {
    try {
        processor.executeCommand("LEFT");
    } catch (ToyRobotException e) {
        assertEquals(e.getMessage(), "Cannot process any commands until the robot has been PLACED.");
    }

    try {
        processor.executeCommand("RIGHT");
    } catch (ToyRobotException e) {
        assertEquals(e.getMessage(), "Cannot process any commands until the robot has been PLACED.");
    }

    try {
        processor.executeCommand("MOVE");
    } catch (ToyRobotException e) {
        assertEquals(e.getMessage(), "Cannot process any commands until the robot has been PLACED.");
    }   

    try {
        processor.executeCommand("REPORT");
    } catch (ToyRobotException e) {
        assertEquals(e.getMessage(), "Cannot process any commands until the robot has been PLACED.");
    }

    processor.executeCommand("EXIT");
    assertTrue(processor.isCommandsFinished());
	}

	@Test
	public void testExecuteCommandWithRobotPlaced() throws ToyRobotException {
		processor.executeCommand("Place 1,2, North");
		processor.executeCommand("Right");
		processor.executeCommand("Move");
		
		assertEquals(processor.getRobot().getLocation(), new Location(2, 2));
		assertEquals(processor.getRobot().getFacingDirection(), Direction.EAST);
		
		assertFalse(processor.isCommandsFinished());
		processor.executeCommand("EXIT");
		assertTrue(processor.isCommandsFinished());
	}

}
