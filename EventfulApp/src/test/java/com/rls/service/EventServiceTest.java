package com.rls.service;

import static org.hamcrest.Matchers.equalTo;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import com.rls.pojo.Event;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
public class EventServiceTest {

	private static EventService service = new EventsServiceImpl();
	private String xml;
	private Integer evenrCountExpected;
	
	
	public EventServiceTest(String xml, int evenrCountExpected) {
		super();
		this.xml = xml;
		this.evenrCountExpected = evenrCountExpected;
	}

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
	
	@Parameters
	public static List<Object[]> data() {
	        return Arrays.asList(new Object[][] {
	                { createEventsXml(Arrays.asList(new Event[] {new Event("title", "url", "description", "11:30", "1:30")} )), new Integer(1)}, 
	                { createEventsXml((Arrays.asList(new Event[] {new Event("title1", "url1", "description1", "11:30", "1:30"), 
	                		                                new Event("title2", "url2", "description2", "11:31", "1:31")} ))), new Integer(2)},
	                { createEventsXml(Arrays.asList(new Event[] {})), new Integer(0)},
	                { "zzz", new Integer(0)}
	        });
	}

	@Test
	public void testValidEvents() {
		List<Event> events = service.getEvents(xml);
		assertThat(events.size(), equalTo(evenrCountExpected));
	}
	
	@Test
	public void testInValidXml() {
		
	}

}
