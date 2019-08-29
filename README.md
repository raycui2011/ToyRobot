# Toy Robot Simulator
This is a command line Java application that simulates a robot on a specifiable grid
## Getting Started
### Dependencies

1. JDK 8
2. Apache Maven 3.5.0

### Installing
#### Install JDK on Mac OS . 
* Check if JDK has been Pre-installed<br />
  - Open a terminal and run the below command
    ```
    javac -version
    ```
  - If a JDK version number is returned, then JDK has been pre-installed, please skip next step.
  
* Downloading JDK <br />
  - Goto Java SE download site @ http://www.oracle.com/technetwork/java/javase/downloads/index.html.<br />
  - Choose the operationg platform and Downloaing the installer.<br />
  
* Install and verify your installation <br />
  - Double-click the downloaded Disk Image (DMG) file. Follow the screen instructions to install JDK/JRE <br />
  - To verify your installation, open a "Terminal" and issue these commands.<br />
  ```
  // Display the JDK version
  javac -version
  javac 1.x.x_xx
       
  // Display the JRE version
  java -version
  java version "1.x.x_xx"
  Java(TM) SE Runtime Environment (build 1.x.x_xx-xxx)
  Java HotSpot(TM) Client VM (build 22.1-b02, mixed mode, sharing)

  // Display the location of Java Compiler
  which javac
  /usr/bin/javac

  // Display the location of Java Runtime
  which java
  /usr/bin/java
  ```

#### Install Apache Maven
On mac<br/>
Please open a terminal and run the below commands.
```
brew install maven
```
On Ubuntu<br/>
```
 sudo apt-get install maven
```

#### Verification

Run command `mvn -version` to verify installatioin
```
Apache Maven 3.5.0 (ff8f5e7444045639af65f6095c62210b5713f426; 2017-04-04T05:39:06+10:00)
Maven home: /usr/local/Cellar/maven/3.5.0/libexec
Java version: 1.8.0_112, vendor: Oracle Corporation
Java home: /Library/Java/JavaVirtualMachines/jdk1.8.0_112.jdk/Contents/Home/jre
Default locale: en_AU, platform encoding: UTF-8
OS name: "mac os x", version: "10.12.4", arch: "x86_64", family: "mac"
```
Ensure JAVA_HOME environment variable is set and points to your JDK installation
* Check environment variable value

```
1. echo $JAVA_HOME
2. /Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home
```
* Adding to PATH

```
export PATH=/opt/apache-maven-3.5.0/bin:$PATH
```

## Description

- The application is a simulation of a toy robot moving on a square tabletop,
  of dimensions 5 units x 5 units.
- There are no other obstructions on the table surface.
- The robot is free to roam around the surface of the table, but must be
  prevented from falling to destruction. Any movement that would result in the
  robot falling from the table must be prevented, however further valid movement
  commands must still be allowed.
 
The application that can read in commands of the following form

    PLACE X,Y,F
    MOVE
    LEFT
    RIGHT
    REPORT

- PLACE will put the toy robot on the table in position X,Y
  and facing NORTH, SOUTH, EAST or WEST.
- The origin (0,0) can be considered to be the SOUTH WEST most corner.
- The first valid command to the robot is a PLACE command, after that,
  any sequence of commands may be issued, in any order, including another
  PLACE command. The application should discard all commands in the
  sequence until a valid PLACE command has been executed.
- MOVE will move the toy robot one unit forward in the direction it is currently
  facing.
- LEFT and RIGHT will rotate the robot 90 degrees in the specified direction
  without changing the position of the robot.
- REPORT will announce the X,Y and F of the robot.
- Any move that would cause the robot to fall must be ignored.

## Example Input and Output:
    
a)

	PLACE 0,0,NORTH
    MOVE
    REPORT

	Output: 0,1,NORTH

b)

	PLACE 0,0,NORTH
	LEFT
	REPORT
	
	Output: 0,0,WEST

c)

	PLACE 1,2,EAST
	MOVE
	MOVE
	LEFT
	MOVE
	REPORT

	Output: 3,3,NORTH

## How to run and test the application.
### For compiling the applicatioin
 - Chagne directory to Robot folder then run this command
 ```
 mvn compile
 ```
 ### For running the application
 - Run this command
 ```
  mvn exec:java
 ```
 ### For running the tests
 - Tests require JUnit and Mockito <br />
 - Run this command to test the application
 ```
 mvn test
 ```











