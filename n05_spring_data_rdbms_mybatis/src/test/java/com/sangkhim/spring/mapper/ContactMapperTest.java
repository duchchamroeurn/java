package com.sangkhim.spring.mapper;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.sangkhim.spring.mapper.contact.ContactMapper;
import com.sangkhim.spring.base.test.AbstractMapperTest;
import com.sangkhim.spring.domain.contact.Contact;

public class ContactMapperTest extends AbstractMapperTest {
	
	private static Logger logger = LoggerFactory.getLogger(ContactMapperTest.class);

	@Autowired
	private ContactMapper contactMapper;

	@Test
	public void getAll() {
		List<Contact> list = contactMapper.getAll();
		logger.debug("{}", list);
		assertNotNull(list);
	}

}