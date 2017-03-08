package com.project.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import com.project.bean.Customer;


public class CustomerDaoImpl implements CustomerDao {

	//private CreateConnection createCon = new CreateConnection();
	
	Configuration cfg=null;
	private SessionFactory factory=null;
	public  CustomerDaoImpl() {
	
		cfg=new AnnotationConfiguration();
		cfg.configure("hibernate.cfg.xml");
		factory=cfg.buildSessionFactory();
	}
	
	
	
	//function to insert all customer details in database
	@Override
	public int insert(Customer customer) {
		Transaction tx=null;
		Session session=null;
		
		try{
		 session=factory.openSession();
		 tx=null;
		tx=session.beginTransaction();
		session.save(customer);
		Customer c= (Customer)session.get(Customer.class, customer.getEmail());
		tx.commit();
		session.close();
		return c.getCustomerId();
		
		
		}
		catch(Exception ex){
			tx.rollback();
			return -1;
		}
		finally{
			session.close();
		}
		
		
		
		
		

	}

	
	//validation function for customer to check email and password
	@Override
	public Customer validation(String email, String password)  {
		Transaction tx=null;
		Session session=null;
		Customer customer =null;
		try{
	session=factory.openSession();
	 tx=null;
		tx=session.beginTransaction();
		 customer = (Customer)session.get(Customer.class, email);
		
	}
	catch(Exception ex){
		tx.rollback();
		return null;
	}
	finally{
		session.close();
	}
		if((customer.getEmail()).equalsIgnoreCase(email)&& ((customer.getPassword()).equals(password)))
				{
					
					tx.commit();
					session.close();
					return customer;
					
				}
		else return null;
		
		
	}

	
	//update function for customer to update details 
	@Override
	public boolean update(Customer customer) {
		Transaction tx=null;
		Session session=null;
		try
		{
		 session=factory.openSession();
		
		tx=session.beginTransaction();
		session.saveOrUpdate(customer);
		tx.commit();
		session.close();
		return true;
	}
		catch(Exception ex){
			tx.rollback();
			return false;
		}
		finally{
			session.close();
		}
	

	}



}
