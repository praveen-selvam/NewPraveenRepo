package com.springs;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AddController {
@RequestMapping("/add")
public ModelAndView add(HttpServletRequest request,HttpServletResponse response ){
	
	ModelAndView mv = new ModelAndView();
	
	String uname=request.getParameter("userName");
	String pass=request.getParameter("password");

	if(uname.equals("abc")&&pass.equals("123"))
	{
		mv.setViewName("display");
	}
	else
	{
		mv.setViewName("index");
	}
	
	return mv;
	
}
}
