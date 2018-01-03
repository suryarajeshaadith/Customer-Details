package org.springwebapp.controller;

 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springwebapp.dao.CustomerDAO;
import org.springwebapp.entity.Customer;
import org.springwebapp.services.CustomerService;

@Controller
@RequestMapping("/customer")

public class CustomerController {
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
	public String listCustomer(Model theModel){
		
		List<Customer> theCustomer=customerService.getCustomers();
		 
		theModel.addAttribute("customer",theCustomer);
		
		return "listCustomers";
	}
	
	@GetMapping("/ShowFormToAdd")
	public String ShowForm(Model theModel){
		
		Customer theCustomer= new Customer();
		
		theModel.addAttribute("customer",theCustomer);
		
		return "Customer-Form";
		
	}
	
	@PostMapping("/saveCustomer")
	public String SaveCustomer(@ModelAttribute("customer") Customer theCustomer){
		
		customerService.saveCustomer(theCustomer);
		
		return "redirect:/customer/list ";
	}
	
	
	@GetMapping("/showFormToUpdate")
	public String ShowForm(@RequestParam("customerId")int theId,Model theModel){
		
		Customer theCustomer= customerService.getCustomer(theId);
		
		theModel.addAttribute("customer",theCustomer);
		
		return "Customer-Form";
		
	}
	
	@GetMapping("/delete")
	public String DeleteCustomer(@RequestParam("customerId")int theId,Model theModel){
		
		customerService.deleteCustomer(theId);
		//theModel.addAttribute("customer",theCustomer);
		return "redirect:/customer/list";
	}
	

}
