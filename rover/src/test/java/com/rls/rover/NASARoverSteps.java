package com.rls.rover;


import org.hamcrest.Matchers;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;

import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;


/**
 * @author Robin Stone
 * 
 * Steps in a scenario
 *
 */

public class NASARoverSteps extends Steps {
	
	private NASARover rover;  

	@Given("a NASA rover <id>")  
	public void givenAnNASARover(@Named("id")int id) {  
		rover = new NASARover(id);  
	}  

	@When("I send the NASA rover a command message <message>")  
	public void whenISendAMessage(@Named("message") List<String> message) {  
		rover.onMessage(message.toArray(new String[]{}));
	}  

	@Then("the final NASA rover location should be <location>")  
	public void thenTheFinalNASARoverLocationShouldBelocation(@Named("location")String location) {  
		assertThat(rover.getCurrentLocation().toString(), Matchers.is(location));  
	}  
	
	

}
