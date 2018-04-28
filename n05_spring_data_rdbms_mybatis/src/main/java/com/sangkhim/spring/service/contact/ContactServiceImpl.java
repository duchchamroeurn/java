package com.sangkhim.spring.service.contact;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sangkhim.spring.domain.contact.Contact;
import com.sangkhim.spring.mapper.contact.ContactMapper;

@Service
public class ContactServiceImpl implements ContactService {
	
	@Autowired
	ContactMapper contactMapper;
	
	public List<Contact> getAll() {
		return contactMapper.getAll();
	}
	
	public Contact getById(int contactId) {
		return contactMapper.getById(contactId);
	}
	
	public int insert(Contact contact) {
		return contactMapper.insert(contact);
	}
	
	public int update(Contact contact) {
		return contactMapper.update(contact);
	}
	
	public int deleteById(int contactId) {
		return contactMapper.deleteById(contactId);
	}
	
}