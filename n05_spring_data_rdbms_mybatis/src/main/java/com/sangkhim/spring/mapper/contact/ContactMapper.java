package com.sangkhim.spring.mapper.contact;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sangkhim.spring.domain.contact.Contact;

@Repository
public interface ContactMapper {
	
	public List<Contact> getAll();
	
	public Contact getById(int contactId);
	
	public int insert(Contact contact);
	
	public int update(Contact contact);
	
	public int deleteById(int contactId);
	
}