package com.sangkhim.spring.mapper.contact;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sangkhim.spring.domain.contact.Contact;

@Repository
public interface ContactMapper extends PagingAndSortingRepository<Contact, String> {

	public List<Contact> findAll();
	
	Contact findById(String id);

	public <S extends Contact> Iterable<S> save(Iterable<S> entities);
	
	public void delete(String id);
	
}