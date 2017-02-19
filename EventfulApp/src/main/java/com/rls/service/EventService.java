package com.rls.service;

import java.util.List;

import com.rls.pojo.Event;

public interface EventService {
	
	public List<Event> getEvents(String responseXML);

}
