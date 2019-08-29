import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.rea.robot.simulator.Direction;

@RunWith(JUnit4.class)
public class DirectionTest  {
	@Test
	public void testDegrees() {
  assertEquals(Direction.NORTH.getDegrees(), 0);
		assertEquals(Direction.EAST.getDegrees(), 90);
		assertEquals(Direction.SOUTH.getDegrees(), 180);
		assertEquals(Direction.WEST.getDegrees(), 270);
		
		assertEquals(Direction.getDirectionByDegrees(Direction.SOUTH.getDegrees()), Direction.SOUTH);

		assertEquals(Direction.getDirectionByDegrees(Direction.NORTH.getDegrees()), Direction.NORTH);

		assertEquals(Direction.getDirectionByDegrees(Direction.EAST.getDegrees()), Direction.EAST);

		assertEquals(Direction.getDirectionByDegrees(Direction.WEST.getDegrees()), Direction.WEST);
	}
}
