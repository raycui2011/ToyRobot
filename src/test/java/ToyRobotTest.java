
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.rea.robot.simulator.Location;
import com.rea.robot.simulator.ToyRobot;
import com.rea.robot.simulator.SquareTable;
import com.rea.robot.simulator.Direction;
import com.rea.robot.exceptions.ToyRobotException;

@RunWith(JUnit4.class)
public class ToyRobotTest  {
	SquareTable table;
	ToyRobot testRobot;
	@Before
	public void initialise() throws ToyRobotException {
		table = new SquareTable(5,5);
		testRobot = new ToyRobot(new Location(0, 0), Direction.NORTH, table);
	}
	
	@Test
	public void testTurnLeft() throws ToyRobotException {
		assertEquals(testRobot.getFacingDirection(), Direction.NORTH);

		//This should become -90 which translates to 270 Deg
		assertEquals(testRobot.rotateLeft().getFacingDirection(), Direction.WEST);
		assertEquals(testRobot.rotateLeft().getFacingDirection(), Direction.SOUTH);
		assertEquals(testRobot.rotateLeft().getFacingDirection(), Direction.EAST);
		
		//This should flick around past 360 Deg for a second time
		assertEquals(testRobot.rotateLeft().getFacingDirection(), Direction.NORTH);
		assertEquals(testRobot.rotateLeft().getFacingDirection(), Direction.WEST);
  
  testRobot.place(new Location(4, 5), Direction.SOUTH);
  testRobot.rotateLeft().rotateLeft();
  assertEquals(testRobot.getFacingDirection(), Direction.NORTH);

  testRobot.place(new Location(4, 5), Direction.EAST);
  testRobot.rotateLeft(); 
  assertEquals(testRobot.getFacingDirection(), Direction.NORTH);
  testRobot.rotateLeft(); 
  assertEquals(testRobot.getFacingDirection(), Direction.WEST);
  testRobot.rotateLeft(); 
  assertEquals(testRobot.getFacingDirection(), Direction.SOUTH);
	}

	@Test
	public void testTurnRight() throws ToyRobotException {
		assertEquals(testRobot.getFacingDirection(), Direction.NORTH);

		assertEquals(testRobot.rotateRight().getFacingDirection(), Direction.EAST);
		assertEquals(testRobot.rotateRight().getFacingDirection(), Direction.SOUTH);
		assertEquals(testRobot.rotateRight().getFacingDirection(), Direction.WEST);
		
		//This should flick around past 360 Deg from 270 to 0
		assertEquals(testRobot.rotateRight().getFacingDirection(), Direction.NORTH);
		assertEquals(testRobot.rotateRight().getFacingDirection(), Direction.EAST);
	}

	@Test
	public void testPlace() throws ToyRobotException {
		assertEquals(testRobot.getLocation(), new Location(0, 0));
		assertEquals(testRobot.getFacingDirection(), Direction.NORTH);

		testRobot.place(new Location(4, 5), Direction.SOUTH);

		assertEquals(testRobot.getLocation(), new Location(4, 5));
		assertEquals(testRobot.getFacingDirection(), Direction.SOUTH);

		placeBoundaryFailureExpected(new Location(6, 5), Direction.SOUTH, table);

		assertEquals(testRobot.getLocation(), new Location(4, 5));
		assertEquals(testRobot.getFacingDirection(), Direction.SOUTH);

		placeBoundaryFailureExpected(new Location(-1, 5), Direction.SOUTH, table);

		assertEquals(testRobot.getLocation(), new Location(4, 5));
		assertEquals(testRobot.getFacingDirection(), Direction.SOUTH);

		placeBoundaryFailureExpected(new Location(0, -1), Direction.SOUTH, table);

		assertEquals(testRobot.getLocation(), new Location(4, 5));
		assertEquals(testRobot.getFacingDirection(), Direction.SOUTH);
		
		
		//Try on a slightly bigger table and testing -ve coordinates
		
  testRobot = new ToyRobot(new Location(0, 0), Direction.NORTH, new SquareTable(6, 6));
		testRobot.place(new Location(5, 6), Direction.EAST);
                
		assertEquals(testRobot.getLocation(), new Location(5, 6));
		assertEquals(testRobot.getFacingDirection(), Direction.EAST);
		
	}

	@Test
	public void testMoveForward() throws ToyRobotException {
		assertEquals(testRobot.getFacingDirection(), Direction.NORTH);
		assertEquals(testRobot.getLocation(), new Location(0, 0));
		
		testRobot.rotateRight().rotateRight();
		assertEquals(testRobot.getFacingDirection(), Direction.SOUTH);
		
		moveForwardBoundaryFailureExpected(); //Testing South Boundary
		assertEquals(testRobot.getLocation(), new Location(0, 0));

		testRobot.rotateRight();
		assertEquals(testRobot.getFacingDirection(), Direction.WEST);
		moveForwardBoundaryFailureExpected(); //Testing west boundary
		
		testRobot.rotateRight();
		assertEquals(testRobot.getFacingDirection(), Direction.NORTH);
		assertEquals(testRobot.getLocation(), new Location(0, 0));

		testRobot.moveForward()
				 .moveForward()
				 .moveForward()
				 .moveForward()
				 .moveForward();
		assertEquals(
				testRobot.getLocation(), 
				new Location(0, 5));

		moveForwardBoundaryFailureExpected(); // Testing North boundary
		
		assertEquals(testRobot.getLocation(), new Location(0, 5));

		testRobot.rotateRight()
				 .moveForward()
				 .moveForward()
				 .moveForward()
				 .moveForward();

		assertEquals(testRobot.getLocation(), new Location(4, 5));
		assertEquals(testRobot.getFacingDirection(), Direction.EAST);

	}
	
	@Test
	public void testReport() throws ToyRobotException {
		assertEquals(testRobot.report(), "[0,0] NORTH");
		
		assertEquals(testRobot.place(new Location(2, 1), Direction.EAST).report(), "[2,1] EAST");
	}
	
 @Test
	public void testSetFacingDirectionMethod() throws ToyRobotException {
  Direction d = Direction.EAST;
  testRobot.setFacingDirection(d);
  assertEquals(testRobot.getFacingDirection(), Direction.EAST);
  assertEquals(testRobot.report(), "[0,0] EAST");
  testRobot.moveForward();
  assertEquals(testRobot.report(), "[1,0] EAST");
	}
 
 @Test
	public void testgetActualDegreesMethod() throws ToyRobotException {
  Integer degree_one = ToyRobot.getActualDegrees(360);
  assertTrue("True", degree_one == 0);
  Integer degree_two= ToyRobot.getActualDegrees(450);
  assertTrue("True", degree_two == 90);
  Integer degree_three = ToyRobot.getActualDegrees(-90);
  assertTrue("True", degree_three == 270);
  
	}
 
 @Test
	public void testSetLocationMethod() throws ToyRobotException {
     Location l = new Location(2,3);
     testRobot.setLocation(l);
     assertEquals(testRobot.getLocation().getXValue(), 2);
     assertEquals(testRobot.getLocation().getYValue(), 3);
 }
 
 @Test
	public void testSetSurfaceMethod() throws ToyRobotException {
     SquareTable surface = new SquareTable(5,5);
     testRobot.setSurface(surface);
     assertEquals(testRobot.getSurface().getMaxColumns(), 5);
     assertEquals(testRobot.getSurface().getMaxRows(), 5);   
 }
 
	@Test
	public void testExampleA() throws ToyRobotException {
	/*	PLACE 0,0,ToyRobotException
		MOVE
		REPORT
		Output: 0,1,NORTH */

		testRobot.place(new Location(0, 0), Direction.NORTH) .moveForward();
		
		assertEquals(testRobot.report(), "[0,1] NORTH");
	}

	@Test
	public void testExampleB() throws ToyRobotException {
        /*PLACE 0,0,NORTH
            LEFT
            REPORT
            Output: 0,0,WEST */

		testRobot.place(new Location(0, 0), Direction.NORTH).rotateLeft();
		
		assertEquals(testRobot.report(), "[0,0] WEST");
	}
	
	@Test
	public void testExampleC() throws ToyRobotException {
	/*	PLACE 1,2,EAST
		MOVE
		MOVE
		LEFT
		MOVE
		REPORT
		Output: 3,3,NORTH */

		testRobot.place(new Location(1, 2), Direction.EAST)
				 .moveForward()
				 .moveForward()
				 .rotateLeft()
				 .moveForward();
		
		assertEquals(testRobot.report(), "[3,3] NORTH");
	}
	
 @Test
	public void testExampleD() throws ToyRobotException {
	/*	PLACE 3,2,WEST
		MOVE
		LEFT
		LEFT
		MOVE
  MOVE
		REPORT
		Output: 4,2,EAST */

		testRobot.place(new Location(3, 2), Direction.WEST)
				 .moveForward()
				 .rotateLeft()
     .rotateLeft()
				 .moveForward()
     .moveForward();
		assertEquals(testRobot.report(), "[4,2] EAST");
	}
 
 @Test
	public void testExampleE() throws ToyRobotException {
		 testRobot.place(new Location(2, 5), Direction.NORTH);
			assertEquals(testRobot.report(), "[2,5] NORTH");  
   moveForwardBoundaryFailureExpected();
   testRobot.rotateRight();
   testRobot.moveForward();
   assertEquals(testRobot.report(), "[3,5] EAST");
   testRobot.moveForward();
   assertEquals(testRobot.report(), "[4,5] EAST");
   testRobot.rotateRight();
   assertEquals(testRobot.report(), "[4,5] SOUTH");
   testRobot.moveForward();
   assertEquals(testRobot.report(), "[4,4] SOUTH");
   testRobot.rotateRight().rotateRight();
   testRobot.moveForward();
   moveForwardBoundaryFailureExpected();
	}
 
	/** Asserts that there will be a failure when moving forward. */
	private ToyRobot placeBoundaryFailureExpected(Location location, Direction direction, SquareTable surface) {
		try {
			testRobot.place(location, direction);
			fail("This should cause a boundary Failure");
		} catch (ToyRobotException e) {}
		
		return this.testRobot;
	}
	
	/** Asserts that there will be a failure when moving forward. */
	private ToyRobot moveForwardBoundaryFailureExpected() {
		try {
			testRobot.moveForward();
			fail("This should cause a boundary Failure");
		} catch (ToyRobotException e) {}
		
		return this.testRobot;
	}
}
