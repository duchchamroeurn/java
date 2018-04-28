package com.sangkhim.spring.domain.contact;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "contact")
public class Contact {
	String id;
	@Indexed
	String name;
	@Indexed
	String city;
	@Indexed
	String tel;
	@Indexed
	String email;
	@Transient
	String email_md5;
	
	public String getId() {
		return id;
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
		
}
