package com.sangkhim.spring.domain.contact;

import org.springframework.web.multipart.MultipartFile;

public class Contact {
	String id;
	String photo;
	String name;
	String city;
	String tel;
	String email;
	String email_md5;
	MultipartFile file;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail_md5() {
		return email_md5;
	}
	public void setEmail_md5(String email_md5) {
		this.email_md5 = email_md5;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	@Override
	public String toString() {
		return "Contact [id=" + id + ", photo=" + photo + ", name=" + name
				+ ", city=" + city + ", tel=" + tel + ", email=" + email
				+ ", email_md5=" + email_md5 + ", file=" + file + "]";
	}
		
}
