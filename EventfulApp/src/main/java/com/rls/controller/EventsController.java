package com.rls.controller;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.rls.pojo.Event;
import com.rls.service.EventService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class EventsController {
	
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private DateTimeFormatter requiredFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
  	private final String baseSearchURL = "http://api.eventful.com/rest/events/search?&app_key={app_key}&oAuth_Consumer_Key={oAuth_Consumer_Key}&oAuth_Consumer_Secret={oAuth_Consumer_Secret}";

    @Autowired
    private EventService service;
    
    public EventsController() {	
	}
    
   
    private Map<String, String> createParamsMapWithSecurityKeys() {
    	Map <String, String> params = new HashMap<String, String>();
    	// hard coded for now
    	params.put("app_key", "VS5wP2WxVMnmmPtf");
    	params.put("oAuth_Consumer_Key", "9168150488011375d4df");
    	params.put("oAuth_Consumer_Secret", "be3d7ab42d7b7f2bacbf");
    	return params;
    }
    
    private String addToMapAndcreateQueryParameter(Map<String, String> params, String key, String value) {
    	params.put(key, value);
    	return "&" + key + "={" + key +  "}";
    }
	
	@RequestMapping(value = "/events/criteria", method=RequestMethod.POST,  produces="application/json")
    public ResponseEntity<List<Event>> getEventsByCriteria(@RequestParam("category") String category, @RequestParam("location") String location) throws Exception {
	    RestTemplate restTemplate = new RestTemplate();
        Map<String, String> params = createParamsMapWithSecurityKeys();
        StringBuffer urlBuffer = new StringBuffer();
        urlBuffer.append(baseSearchURL);
        urlBuffer.append(addToMapAndcreateQueryParameter(params, "category", category));
        urlBuffer.append(addToMapAndcreateQueryParameter(params, "location", location));
        urlBuffer.append(addToMapAndcreateQueryParameter(params, "sort_order", "date"));       	
    	String responseXML = restTemplate.getForObject(urlBuffer.toString(), String.class, params);
    	List<Event> events = service.getEvents(responseXML);
        return new ResponseEntity<List<Event>>(events, HttpStatus.OK);
    }
}
