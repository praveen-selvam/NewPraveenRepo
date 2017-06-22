package com.javasampleapproach.restcontrolleradvice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javasampleapproach.restcontrolleradvice.exception.CustomNotFoundException;
import com.javasampleapproach.restcontrolleradvice.model.Customer;
import com.javasampleapproach.restcontrolleradvice.service.CustomerService;

@RestController
public class WebController {
	
	@Autowired
	CustomerService customerService;
	
	@RequestMapping(value = "/customer/{name}")
	public Customer findCustomerByName(@PathVariable("name") String name) {

		Customer cust = customerService.findCustomerByName(name);

		if (null == cust) {
			throw new CustomNotFoundException("Not found customer with name is " + name);
		}

		return cust;
	}
}