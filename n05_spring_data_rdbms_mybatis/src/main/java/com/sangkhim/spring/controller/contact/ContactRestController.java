package com.sangkhim.spring.controller.contact;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sangkhim.spring.domain.contact.Contact;
import com.sangkhim.spring.mapper.contact.ContactMapper;

@RestController
public class ContactRestController {

	@Autowired
	private ContactMapper contactMapper;

	@RequestMapping(value = "/contacts", method = RequestMethod.GET)
	public List<Contact> contacts() {
		List<Contact> contactList = contactMapper.getAll();
		return contactList;
	}

	@RequestMapping(value = "/contacts/{contactId}", method = RequestMethod.GET)
	public Contact contact(@PathVariable int contactId) {
		Contact contact = contactMapper.getById(contactId);
		return contact;
	}

	@RequestMapping(value = "/contacts", method = RequestMethod.POST)
	public int insert(@RequestBody Contact contact) {
		return contactMapper.insert(contact);
	}

	@RequestMapping(value = "/contacts", method = RequestMethod.PUT)
	public int update(@RequestBody Contact contact) {
		return contactMapper.update(contact);
	}

	@RequestMapping(value = "/contacts/{contactId}", method = RequestMethod.DELETE)
	public int delete(@PathVariable int contactId) {
		return contactMapper.deleteById(contactId);
	}

}