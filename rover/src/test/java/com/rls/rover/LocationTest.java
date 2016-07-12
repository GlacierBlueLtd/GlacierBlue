/**
 * 
 */
package com.rls.rover;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * @author Robin Stone
 * 
 * Test for location
 *
 */

@RunWith(Parameterized.class)
public class LocationTest {
	
	
   	public Location location;
	public int expectedX;
	public int expectedY;
	public Heading expectedHeading;
			
	
	public LocationTest(Location location, int expectedX, int expectedY, Heading expectedHeading) {
		super();
		this.location = location;
		this.expectedX = expectedX;
		this.expectedY = expectedY;
		this.expectedHeading = expectedHeading;
	}



	 @Parameters
	    public static List<Object[]> data() {
	        return Arrays.asList(new Object[][] {
	                { new Location(0,0, Heading.N), 0, 0, Heading.N }, 
	                { new Location(1,1, Heading.S), 1, 1, Heading.S},
	        });
	  }
	
	
	@Test
	public void testLocation() {
		assertThat(location, Matchers.notNullValue());
	}
	
	@Test
	public void testGetX() {
		assertThat(location.x, equalTo(expectedX));
	}

	@Test
	public void testGetY() {
		assertThat(location.y, equalTo(expectedY));
	}

}
