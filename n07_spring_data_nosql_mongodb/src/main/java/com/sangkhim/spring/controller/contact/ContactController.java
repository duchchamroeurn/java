package com.sangkhim.spring.controller.contact;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sangkhim.spring.domain.contact.Contact;
import com.sangkhim.spring.mapper.contact.ContactMapper;

@Controller
public class ContactController {

	@Autowired
	private ContactMapper contactMapper;

	@RequestMapping({"/", "/contact_list"})
	public String index(ModelMap model) throws Exception {
		
		List<Contact> list = contactMapper.findAll();
		model.addAttribute("data", list);
		
		return "contact_list";
	}
	
	@RequestMapping("/add_new_contact")
	public String addNewContact(ModelMap model) throws Exception {
		
		Contact contact = new Contact();
		model.addAttribute("contact", contact);		
		
		return "add_new_contact";
	}
	
	@RequestMapping("/update_contact/{contactId}")
	public String updateContact(ModelMap model, @PathVariable String contactId) throws Exception {
		
		Contact contact = contactMapper.findById(contactId);
		model.addAttribute("contact", contact);		
		
		return "update_contact";
	}
	
	@RequestMapping("/save_contact")
	public String saveContact(@ModelAttribute("contact") Contact contact) throws Exception {
		Contact result = contactMapper.save(contact);
		if(result != null) {
			return "redirect:contact_list";
		}else{
			return "redirect:add_new_contact";
		}
	}
	
	@RequestMapping("/delete_contact/{contactId}")
	public String deleteContact(@PathVariable String contactId) throws Exception {
		contactMapper.delete(contactId);		
		return "redirect:../contact_list";
	}

}