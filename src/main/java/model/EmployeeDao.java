package model;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class EmployeeDao {
	private Configuration con;
	private SessionFactory factory;
	private Session session;
	private Transaction t;
	public void store(Employee e)
	{
		con=new Configuration().configure("hibernate.cfg.xml");
		factory=con.buildSessionFactory();
		session=factory.openSession();
		t=session.beginTransaction();
		session.save(e);
		t.commit();
	}
	
	public List display()
	{
		con=new Configuration().configure("hibernate.cfg.xml");
		factory=con.buildSessionFactory();
		session=factory.openSession();
		t=session.beginTransaction();
		List list=session.createQuery("from Employee").list();
		return list;
	}

	public void update(Employee emp) {
		con=new Configuration().configure("hibernate.cfg.xml");
		factory=con.buildSessionFactory();
		session=factory.openSession();
		t=session.beginTransaction();
		Employee obj=session.get(Employee.class, emp.getEmpcode());
		obj.setEmpname(emp.getEmpname());
		obj.setDesignation(emp.getDesignation());
		obj.setEmail(emp.getEmail());
		session.update(obj);
		t.commit();
				
	}

	public void delete(Employee emp) {
		con=new Configuration().configure("hibernate.cfg.xml");
		factory=con.buildSessionFactory();
		session=factory.openSession();
		t=session.beginTransaction();
		Employee obj=session.get(Employee.class, emp.getEmpcode());
		session.delete(obj);
		t.commit();
		
	}

	public List search(Employee e) {
		List list=new ArrayList();
		con=new Configuration().configure("hibernate.cfg.xml");
		factory=con.buildSessionFactory();
		session=factory.openSession();
		t=session.beginTransaction();
		List lis=session.createQuery("from Employee").list();
		Iterator it=lis.iterator();
		while(it.hasNext())
		{
			Employee obj=(Employee)it.next();
			if((obj.getEmpcode()==e.getEmpcode()) || obj.getEmpname().equals(e.getEmpname()) || obj.getDesignation().equals(e.getDesignation())||obj.getEmail().equals(e.getEmail()))
			{
				Employee emp=new Employee();
				emp.setEmpcode(obj.getEmpcode());
				emp.setEmpname(obj.getEmpname());
				emp.setDesignation(obj.getDesignation());
				emp.setEmail(obj.getEmail());
				list.add(emp);
			}
		}
		return list;
	}

	public boolean login(Employee e) {
		boolean flag=false;
		con=new Configuration().configure("hibernate.cfg.xml");
		factory=con.buildSessionFactory();
		session=factory.openSession();
		t=session.beginTransaction();
		List lis=session.createQuery("from Employee").list();
		Iterator it=lis.iterator();
		while(it.hasNext())
		{
			Employee obj=(Employee)it.next();
			if((obj.getEmpcode()==e.getEmpcode()) && obj.getEmpname().equals(e.getEmpname()))
			{
				flag=true;
			}
		}
		return flag;
	}

}
