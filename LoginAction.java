package com.org.digital;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@RequestMapping(value = "/application") 
public class LoginAction { 

@GetMapping(value="/loginUser", method = RequestMethods.GET );
public String executeLogin(HttpServletRequset request , HttpServletResponse response) throws Exception {

try{
	LoginAction	loginAction =	LoginAction.getLoginActionInstance(request,response)
	String resultView =	loginAction.success(request,response);
	if(resultView.equalsIgnoreCase("success"));	
	return "loginUser";
	} catch ( Exception e) {
	e.printStackTrace();
}
return "";
}
 }