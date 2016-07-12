package com.rls.rover;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * @author Robin Stone
 * 
 * Test for RoverNavigator
 *
 */

@RunWith(Parameterized.class)
public class RoverNavigatorTest {

	
	public Location location;
	public Location expectedRotateLeftLocation;
	public Location expectedRotateRightLocation;
	public Location expectedMoveLocation;
	
	private RoverNavigator roverNavigator = new RoverNavigator(new Location(5,5,Heading.NONE));
	
	
	
	public RoverNavigatorTest(Location location, Location expectedRotateLeftLocation,
			Location expectedRotateRightLocation, Location expectedMoveLocation) {
		super();
		this.location = location;
		this.expectedRotateLeftLocation = expectedRotateLeftLocation;
		this.expectedRotateRightLocation = expectedRotateRightLocation;
		this.expectedMoveLocation = expectedMoveLocation;
		
	}


	@Parameters
	public static List<Object[]> data() {
	        return Arrays.asList(new Object[][] {
	                {new Location(0,0, Heading.N), new Location(0,0, Heading.W), new Location(0,0, Heading.E),  new Location(0,1, Heading.N)},
	                {new Location(0,0, Heading.E), new Location(0,0, Heading.N), new Location(0,0, Heading.S),  new Location(1,0, Heading.E)},
	                {new Location(0,0, Heading.S), new Location(0,0, Heading.E), new Location(0,0, Heading.W),  new Location(0,0, Heading.S)},
	                {new Location(0,0, Heading.W), new Location(0,0, Heading.S), new Location(0,0, Heading.N),  new Location(0,0, Heading.W)},
	                
	                {new Location(5,5, Heading.N), new Location(5,5, Heading.W), new Location(5,5, Heading.E),  new Location(5,5, Heading.N)},
	                {new Location(5,5, Heading.E), new Location(5,5, Heading.N), new Location(5,5, Heading.S),  new Location(5,5, Heading.E)},
	                {new Location(5,5, Heading.S), new Location(5,5, Heading.E), new Location(5,5, Heading.W),  new Location(5,4, Heading.S)},
	                {new Location(5,5, Heading.W), new Location(5,5, Heading.S), new Location(5,5, Heading.N),  new Location(4,5, Heading.W)},
	                
	                {new Location(1,1, Heading.N), new Location(1,1, Heading.W), new Location(1,1, Heading.E),  new Location(1,2, Heading.N)},
	                {new Location(1,1, Heading.E), new Location(1,1, Heading.N), new Location(1,1, Heading.S),  new Location(2,1, Heading.E)},
	                {new Location(1,1, Heading.S), new Location(1,1, Heading.E), new Location(1,1, Heading.W),  new Location(1,0, Heading.S)},
	                {new Location(1,1, Heading.W), new Location(1,1, Heading.S), new Location(1,1, Heading.N),  new Location(0,1, Heading.W)},
	                
	               
	        });
	 }
	

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testRotateLeft() {
		Location newLocation = roverNavigator.rotateLeft(location);
		assertThat(newLocation, equalTo(expectedRotateLeftLocation));
	}

	@Test
	public void testRotateRight() {
		Location newLocation = roverNavigator.rotateRight(location);
		assertThat(newLocation, equalTo(expectedRotateRightLocation));
	}

	@Test
	public void testMove() {
		Location newLocation = roverNavigator.move(location);
		assertThat(newLocation, equalTo(expectedMoveLocation));
	}

}
