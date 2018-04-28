package com.sangkhim.spring.form;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Address {
	
	private String houseNo;
	private String streetNo;
	private String city;
	private String country;
	private List<MultipartFile> files;
	
	public String getHouseNo() {
		return houseNo;
	}
	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}
	public String getStreetNo() {
		return streetNo;
	}
	public void setStreetNo(String streetNo) {
		this.streetNo = streetNo;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	@JsonIgnore
	public List<MultipartFile> getFiles() {
		return files;
	}
	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}
	
	@Override
	public String toString() {
		return "Address [houseNo=" + houseNo + ", streetNo=" + streetNo
				+ ", city=" + city + ", country=" + country + "]";
	}
	
}
