package com.org.digital;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@RequestMapping(value = "/application") 
public class DashboardAction { 

@GetMapping(value="/dashboardPage", method = RequestMethods.GET );
public String executeDashboard(HttpServletRequset request , HttpServletResponse response) throws Exception {

try{
	DashboardAction	dashboardAction =	DashboardAction.getDashboardActionInstance(request,response)
	String resultView =	dashboardAction.success(request,response);
	if(resultView.equalsIgnoreCase("success"));	
	return "dashboardPage";
	} catch ( Exception e) {
	e.printStackTrace();
}
return "";
}
 }