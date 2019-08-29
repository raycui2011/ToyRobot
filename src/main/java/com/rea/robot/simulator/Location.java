package com.rea.robot.simulator;


public class Location {
	private int x, y;
	
	public Location(int x, int y) {
    this.x = x;
    this.y = y;
	}

	public Location changePosition(Location l) {
    return new Location(this.x + l.getXValue(), this.y + l.getYValue());
	}
	
	@Override
	public boolean equals(Object obj) {
    if (! (obj instanceof Location)) {
    return false;
		}
		
		Location otherCoord = (Location) obj;
			
		return  this.x == otherCoord.x &&
    this.y == otherCoord.y;
	}
	
	public int getXValue() {
        return this.x;
    }

 public int getYValue() {
    return this.y;
 }

public String toString() {
    return "["+this.x+","+this.y+"]";
	}
}
