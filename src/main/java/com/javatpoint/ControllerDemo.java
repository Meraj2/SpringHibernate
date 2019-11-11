package com.javatpoint;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import model.Employee;
import model.EmployeeDao;
import model.User;



@Controller
public class ControllerDemo {
	
	@RequestMapping("/login")
	public String login(Model m)
	{
		ApplicationContext app=new ClassPathXmlApplicationContext("ApplicationContext.xml");
		Employee e=app.getBean("info",Employee.class);
		m.addAttribute("bean",e);
		return "login";
	}
	@RequestMapping(value="/sign", method=RequestMethod.POST)
	public String sign(@ModelAttribute("bean") Employee e,HttpServletRequest request)
	{
		ApplicationContext app=new ClassPathXmlApplicationContext("ApplicationContext.xml");
		EmployeeDao ed=app.getBean("dao",EmployeeDao.class);
		boolean flag=ed.login(e);
		if(flag)
		{
			User usr=app.getBean("us",User.class);
			usr.setEmpcode(e.getEmpcode());
			usr.setEmpname(e.getEmpname());
			usr.setFlag(1);
			HttpSession session=request.getSession();
			session.setAttribute("data", usr);
			return "home";
		}
		else
		{
			return "redirect:index.jsp";
		}
	}
	
	@RequestMapping("/home")
	public String view()
	{
		return "home";
	}
	@RequestMapping(value="/add")
	public String view1(Model m)
	{
		ApplicationContext app=new ClassPathXmlApplicationContext("ApplicationContext.xml");
		Employee e=app.getBean("info",Employee.class);
		m.addAttribute("bean",e);
		return "entry";
	}
	
	@RequestMapping(value="/store" , method=RequestMethod.POST)
	public String view2(@ModelAttribute("bean")Employee e,Model m)
	{
		ApplicationContext con=new ClassPathXmlApplicationContext("ApplicationContext.xml");
		EmployeeDao obj=con.getBean("dao",EmployeeDao.class);
		obj.store(e);
		m.addAttribute("msg","Data Inserted");
		return "display";
	}
	
	@RequestMapping("/display")
	public String view3(Model m,HttpServletRequest request)
	{
		ApplicationContext app=new ClassPathXmlApplicationContext("ApplicationContext.xml");
		HttpSession session=request.getSession();
		User obj=(User)session.getAttribute("data");
		if(obj.getFlag()==1)
		{
			EmployeeDao e=app.getBean("dao",EmployeeDao.class);
		
			List list=e.display();
			m.addAttribute("list",list);
			return "display";
		}
		else
		{
			m.addAttribute("msg","Please login first");
			return "redirect:index.jsp";
		}
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(@ModelAttribute("bean") Employee emp,Model m)
	{
		ApplicationContext app=new ClassPathXmlApplicationContext("ApplicationContext.xml");
		EmployeeDao e=app.getBean("dao",EmployeeDao.class);
		e.update(emp);
		m.addAttribute("msg","Data Updated");
		return "redirect:display";
	}

	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String delete(@ModelAttribute("bean") Employee emp,Model m)
	{
		ApplicationContext app=new ClassPathXmlApplicationContext("ApplicationContext.xml");
		EmployeeDao e=app.getBean("dao",EmployeeDao.class);
		e.delete(emp);
		m.addAttribute("msg","Data deleted");
		return "redirect:display";
	}
	
	@RequestMapping(value="/search")
	public String search()
	{
		return "search";
	}
	@RequestMapping(value="/find")
	public String find(@RequestParam("t1") String str,@RequestParam("t2") String val,Model m)
	{
		ApplicationContext app=new ClassPathXmlApplicationContext("ApplicationContext.xml");
		EmployeeDao ed=app.getBean("dao",EmployeeDao.class);
		Employee e=app.getBean("info",Employee.class);
		if(str.equals("empcode"))
		{
			e.setEmpcode(Integer.valueOf(val));
		}
		else if(str.equals("empname"))
		{
			e.setEmpname(val);
		}
		else if(str.equals("designation"))
		{
			e.setDesignation(val);
		}
		else
		{
			e.setEmail(val);
		}
		List list=ed.search(e);
		m.addAttribute("list",list);
		return "display";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request)
	{
		HttpSession session=request.getSession();
		User obj=(User)session.getAttribute("data");
		obj.setFlag(0);
		return "redirect:index.jsp";
	}

}
