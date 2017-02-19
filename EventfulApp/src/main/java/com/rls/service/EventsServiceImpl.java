package com.rls.service;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.rls.pojo.Event;

@Service
@Component
public class EventsServiceImpl implements EventService {
	
	private static final String ELEMENT_EVENT = "event";
	private static final String ELEMENT_EVENTS = "events";

	public List<Event> getEvents(String responseXML) {

		List<Event> events = new ArrayList<Event>();
		
		try {
			StringReader stringReader = new StringReader(responseXML);
			XMLInputFactory xif = XMLInputFactory.newInstance();
			XMLStreamReader xsr = xif.createXMLStreamReader(stringReader);

			xsr.nextTag();
			
			while (xsr.hasNext()) {
				if (xsr.isStartElement() && xsr.getLocalName().equals(ELEMENT_EVENTS)) {
					break;
				}
				xsr.next();
			}

			JAXBContext jaxbContext = JAXBContext.newInstance(Event.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

			while (xsr.hasNext()) {
				if (!xsr.isCharacters() && xsr.getLocalName().equals(ELEMENT_EVENT)) {
					JAXBElement<Event> uo = unmarshaller.unmarshal(xsr, Event.class);
					Event item = uo.getValue();
					events.add(item);

				}
				xsr.next();
			}

		} catch (Exception e) {
			return events;
		}
		return events;
	}

}
