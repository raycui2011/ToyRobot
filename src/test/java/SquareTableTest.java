import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.rea.robot.simulator.SquareTable;
import com.rea.robot.simulator.Location;
import com.rea.robot.exceptions.ToyRobotException;

@RunWith(JUnit4.class)
public class SquareTableTest  {
	@Test
	public void testIsCoordinateWithinBounds() throws ToyRobotException {
		SquareTable boundary = new SquareTable(4,4);
		
		assertTrue(boundary.isCoordinateWithinBounds(new Location(0, 0)));
		assertFalse(boundary.isCoordinateWithinBounds(new Location(-1, 0)));
		assertFalse(boundary.isCoordinateWithinBounds(new Location(0, -2)));
		assertTrue(boundary.isCoordinateWithinBounds(new Location(3, 0)));
		assertTrue(boundary.isCoordinateWithinBounds(new Location(0, 4)));

		assertFalse(boundary.isCoordinateWithinBounds(new Location(-2, 0)));
		assertFalse(boundary.isCoordinateWithinBounds(new Location(0, -3)));
		assertTrue(boundary.isCoordinateWithinBounds(new Location(4, 0)));
		assertFalse(boundary.isCoordinateWithinBounds(new Location(0, 5)));
	}
}
