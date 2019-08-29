import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.rea.robot.simulator.Location;
import com.rea.robot.simulator.SquareTable;

@RunWith(JUnit4.class)
public class LocationTest  {
	@Test
	public void testChangePosition() {
    Location location = new Location(1,2);

    assertEquals(location.getXValue(), 1);
    assertEquals(location.getYValue(), 2);

    Location new_location = new Location(3,5);

    assertEquals(new_location.getXValue(), 3);
    assertEquals(new_location.getYValue(), 5);

    Location addedLocation = location.changePosition(new_location);

    assertEquals(location.getXValue(), 1);
    assertEquals(location.getYValue(), 2);
    assertEquals(new_location.getXValue(), 3);
    assertEquals(new_location.getYValue(), 5);
    assertEquals(addedLocation.getXValue(), 4);
    assertEquals(addedLocation.getYValue(), 7);
	}
 
 @Test
	public void testEqualsMethod() {
    Location location = new Location(1,2);
    SquareTable table = new SquareTable(2,2);
    assertEquals(location.equals(location), true);
    assertFalse(location.equals(table));   
 }
 
 @Test
 public void testToStringMethod() {
     Location location = new Location(2,3);
     assertEquals(location.toString(), "[2,3]");
     
     Location loc = new Location(0,5);
     assertEquals(loc.toString(), "[0,5]");
 }
 
 @Test
 public void testGetXValueMethod() {
     Location location = new Location(3,3);
     assertEquals(location.getXValue(), 3);
     
     Location new_location = new Location(4, 5);
     Location loc = location.changePosition(new_location);
     assertEquals(loc.getXValue(), 7);
 }
 
 @Test
 public void testGetYValueMethod() {
     Location location = new Location(4,5);
     assertEquals(location.getYValue(), 5);
     
     Location new_location = new Location(1, 1);
     Location loc = location.changePosition(new_location);
     assertEquals(loc.getYValue(), 6);
 }
}
