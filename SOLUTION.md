CommandProcessor.java is designed to excute the command.
- The reason create a private place method here is make sure the location is invalid and also make sure that parameters should be valid before invoking the place method in ToyRob.java. 
- Before executing the command (except EXIT)need to check the ToyRob object is null or not .

Board.java is designed as a interface. The purpsoe is code can  extenable. For example, in this case it is squre table top but could be rectangular board.

SuqareTable.java is designed for the board which ToyRob can stay in. <br />
- isCoordinateWithinBounds method is used to check the location is valid location.

Location.java is used for ToyRob's coordinate.
- changePosition method will create a new Locaton object with the updated x,y value . Before ToyRob do any movement It is easy to check is valid position or not.

Direction.java
- Direction has two fields , degrees and Location location
- degrees is used to map the direction. for exampe 270 degree match WEST

ToyRob.java
- has three fields, Location location,Direction facingDirection, SquareTable surface.
- roateLeft method will rotate ToyRob -90 degrees, it will change the ToyRob's direction's degrees to current_degree - 90.
- roateRight method will rotate ToyRob 90 degrees, it will change the ToyRob's direction's degrees to current_degree + 90.
- place method will place the ToyRob by the given location, facingDirection and surface.
- moveForword method will move the ToyRob to one unit in the surface.
