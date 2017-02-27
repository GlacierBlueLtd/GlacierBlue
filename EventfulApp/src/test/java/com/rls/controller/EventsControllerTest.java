package com.rls.controller;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static com.jayway.jsonpath.matchers.JsonPathMatchers.*;


import java.util.ArrayList;
import java.util.List;

import com.jayway.jsonpath.Configuration;
import com.rls.pojo.Event;
import com.rls.service.EventService;
import static org.mockito.BDDMockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ComponentScan(basePackages = { "com.rls" })
@WebMvcTest(EventsController.class)
public class EventsControllerTest {

    @MockBean
	private EventService evs;
    
   
    @Autowired
    private MockMvc mockMvc;
      
    private static String createEventXML(Event e) {
		StringBuffer testXML = new StringBuffer();
		testXML.append("<event>");
		testXML.append("\n");
		testXML.append("<title>" + e.getTitle() + "</title>");
		testXML.append("\n");
		testXML.append("<url>" + e.getUrl() + "</url>");
		testXML.append("\n");
		testXML.append("<description>" + e.getDescription() + "</description>");
		testXML.append("\n");
		testXML.append("<start_time>" + e.getStartTime() + "</start_time>");
		testXML.append("\n");
		testXML.append("<stop_time>" + e.getStopTime() + "</stop_time>");
		testXML.append("\n");
		testXML.append("</event>");
		testXML.append("\n");
		return testXML.toString();
	}
	
	private static String createEventsXml(List<Event> events) {
		StringBuffer testXML = new StringBuffer();
		testXML.append("<?xml version=" + '"' + "1.0"  + '"' + " encoding=" + '"' +  "UTF-8" + '"' + "?>");
		testXML.append("<events>");
			
		String body = events.stream().map(e->createEventXML(e))
				.collect(StringBuffer::new, 
					     StringBuffer::append, StringBuffer::append).toString();
		
		testXML.append(body);
		testXML.append("</events>");
		return testXML.toString();
	}
	
    
    @Test
    public void  testEvents() throws Exception {
    	List<Event> events = new ArrayList<Event>();
    	events.add(new Event("title1", "url1", "description1", "11:30", "1:30"));
    	events.add(new Event("title2", "url2", "description2", "11:31", "1:31"));
    	
    	when(this.evs.getEvents(anyString())
            ).thenReturn(
              events);
    	
    	String uri =  "/events/criteria?category=music&location=london";
    	MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(uri).accept(MediaType.APPLICATION_JSON))
    			                   .andExpect(MockMvcResultMatchers.status().isOk())
    			                   .andExpect(content().contentType("application/json;charset=UTF-8"))
    			                   .andReturn();
    	String content = result.getResponse().getContentAsString();
    	Object json = Configuration.defaultConfiguration().jsonProvider().parse(content);
    	assertThat(json, hasJsonPath("$[0].title", equalTo("title1")));
    	assertThat(json, hasJsonPath("$[0].url", equalTo("url1")));
    	assertThat(json, hasJsonPath("$[0].description", equalTo("description1")));
    	assertThat(json, hasJsonPath("$[0].startTime", equalTo("11:30")));
    	assertThat(json, hasJsonPath("$[0].stopTime", equalTo("11:30")));
    	
    	assertThat(json, hasJsonPath("$[1].title", equalTo("title2")));
    	assertThat(json, hasJsonPath("$[1].url", equalTo("url2")));
    	assertThat(json, hasJsonPath("$[1].description", equalTo("description2")));
    	assertThat(json, hasJsonPath("$[1].startTime", equalTo("11:31")));
    	assertThat(json, hasJsonPath("$[1].stopTime", equalTo("1:31")));
    	
    }
		                  
    	
    	
    	
    

   
}