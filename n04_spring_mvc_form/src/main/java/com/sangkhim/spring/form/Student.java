package com.sangkhim.spring.form;

import java.util.Date;

import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

public class Student {
	
	@NotBlank
	private String name;
	@NotBlank
	private String hobby;
	@Range(min=3, max=30)
	private String age;
	@Past
	@DateTimeFormat(pattern="dd/MM/YY")
	private Date dob;
	private MultipartFile file;
	private Address address;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		return "Student [name=" + name + ", hobby=" + hobby + ", age=" + age + ", dob=" + dob + ", file=" + file
				+ ", address=" + address + "]";
	}

}
