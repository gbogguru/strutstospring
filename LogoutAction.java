package com.org.digital;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@RequestMapping(value = "/application") 
public class LogoutAction { 

@GetMapping(value="/logoutPage", method = RequestMethods.GET );
public String executeLogout(HttpServletRequset request , HttpServletResponse response) throws Exception {

try{
	LogoutAction	logoutAction =	LogoutAction.getLogoutActionInstance(request,response)
	String resultView =	logoutAction.logout(request,response);
	if(resultView.equalsIgnoreCase("logout"));	
	return "logoutPage";
	} catch ( Exception e) {
	e.printStackTrace();
}
return "";
}
 }