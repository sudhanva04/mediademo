package com.MEDIADEMO.demo.domain;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "mediademo_customer")
public class Customer {
	@Id
	private String id;
	private String name;
	private UserType userType;
	private int priority;
	private String password;
	private List<String> images;
	private List<String> videos;

	public String getId() {
		return id;
	}

	
	
	public int getPriority() {
		return priority;
	}



	public void setPriority(int priority) {
		this.priority = priority;
	}



	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public List<String> getVideos() {
		return videos;
	}

	public void setVideos(List<String> videos) {
		this.videos = videos;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", userType=" + userType + ", password=" + password
				+ ", images=" + images + ", videos=" + videos + "]";
	}

}
