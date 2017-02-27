package com.rls.pojo;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

public class EventTest  {

	
	
	@Test
	public void testConstructor() {
		Event test = new Event("title", "/test/testresource", "description", "11:30", "1:30");
		
		assertThat(test.getTitle(), equalTo("title"));
		assertThat(test.getUrl(), equalTo("/test/testresource"));
		assertThat(test.getDescription(), equalTo("description"));
		assertThat(test.getStartTime(), equalTo("11:30"));
		assertThat(test.getStopTime(), equalTo("1:30"));
	}
	
	
	@Test
	public void testSetters() {
		Event test = new Event();
		test.setTitle("title");
		test.setUrl("/test/testresource");
		test.setDescription("description");
		test.setStartTime("11:30");
		test.setStopTime("1:30");
		
		assertThat(test.getTitle(), equalTo("title"));
		assertThat(test.getUrl(), equalTo("/test/testresource"));
		assertThat(test.getDescription(), equalTo("description"));
		assertThat(test.getStartTime(), equalTo("11:30"));
		assertThat(test.getStopTime(), equalTo("1:30"));
	}
}
