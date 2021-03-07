package com.fabernovel.test.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.List;

public class Product {
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Announcement> getAnnouncements() {
		return announcements;
	}

	public void setAnnouncementList(List<Announcement> announcements) {
		this.announcements = announcements;
	}

	private String name;

	private List<Announcement> announcements;

	public Product(String name, List<Announcement> announcements) {
		this.announcements = announcements;
		this.name = name;
	}


	@Override
	public String toString() {
		ObjectMapper mapper = new ObjectMapper();

		String jsonString = "";
		try {
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			jsonString = mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonString;
	}

}

