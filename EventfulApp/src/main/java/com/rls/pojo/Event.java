package com.rls.pojo;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@javax.xml.bind.annotation.XmlRootElement(name = "event")
public final class Event {
	
	@XmlElement(name="title")
	private String title;

	@XmlElement(name="url")
	private String url;

	@XmlElement(name="description")
	private String description;

	@XmlElement(name="start_time")
	private String startTime;
	
	@XmlElement(name="stop_time")
	private String stopTime;
	
	
	public Event() {
	}
	
	
	public Event(String title, String url, String description, String startTime, String stopTime) {
		super();
		this.title = title;
		this.url = url;
		this.description = description;
		this.startTime = startTime;
		this.stopTime = stopTime;
	}



	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getStopTime() {
		return stopTime;
	}

	public void setStopTime(String stopTime) {
		this.stopTime = stopTime;
	}

	
}
