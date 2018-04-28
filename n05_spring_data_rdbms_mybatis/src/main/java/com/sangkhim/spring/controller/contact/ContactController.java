package com.sangkhim.spring.controller.contact;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.sangkhim.spring.base.FileUploadUtils;
import com.sangkhim.spring.domain.contact.Contact;
import com.sangkhim.spring.service.contact.ContactService;

@Controller
public class ContactController {

	@Autowired
	private ContactService contactService;

	@RequestMapping({"/", "/contact_list"})
	public String index(ModelMap model, HttpSession session, Principal principal) throws Exception {
		List<Contact> list = contactService.getAll();
		model.addAttribute("data", list);
		return "contact_list";
	}
	
	@RequestMapping("/add_new_contact")
	public String addNewContact(ModelMap model, HttpSession session, Principal principal) throws Exception {
		
		Contact contact = new Contact();
		model.addAttribute("contact", contact);		
		
		return "add_new_contact";
	}
	
	@RequestMapping("/update_contact/{contactId}")
	public String updateContact(ModelMap model, @PathVariable int contactId) throws Exception {
		
		Contact contact = contactService.getById(contactId);
		model.addAttribute("contact", contact);		
		
		return "update_contact";
	}
	
	@RequestMapping(value = "/save_contact", method = RequestMethod.POST)
	public String saveContact(@ModelAttribute("contact") Contact contact) throws Exception {
		int result;
		MultipartFile file = contact.getFile();
		if(file != null && file.getSize() > 0) {			
			String photo = FileUploadUtils.saveFileUploaded(file);
			contact.setPhoto(photo);
		}
		if(contact.getId() == null){
			result = contactService.insert(contact);
		}else{
			result = contactService.update(contact);
		}
		if(result > 0){
			return "redirect:contact_list";
		}else{
			return "redirect:add_new_contact";
		}
	}
	
	@RequestMapping("/delete_contact/{contactId}")
	public String deleteContact(@PathVariable int contactId) throws Exception {
		contactService.deleteById(contactId);		
		return "redirect:../contact_list";
	}

}