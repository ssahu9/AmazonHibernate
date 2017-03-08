package com.project.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import com.project.bean.Admin;


public class AdminDaoImpl implements AdminDao {

	//private CreateConnectionnectionnection CreateConnectionnection = new CreateConnectionnectionnection();
	
	Configuration cfg=null;
	private SessionFactory factory=null;
	public AdminDaoImpl() {
	
		cfg=new AnnotationConfiguration();
		cfg.configure("hibernate.cfg.xml");
		factory=cfg.buildSessionFactory();
	}
	//validation function check for email and password used for admin sign-up 
	@Override
	public boolean validation(String email, String password) {
		
		Admin admin = new Admin(email,password);
		Session session=factory.openSession();
		Transaction tx=null;
		tx=session.beginTransaction();
		Admin admin2 = (Admin)session.get(Admin.class, email);
		
		tx.commit();
		
		session.close();
		return admin.equals(admin2);
	}

	//update function to update admin password
	@Override
	public boolean update(Admin admin) {
		
		Session session=factory.openSession();
		Transaction tx=null;
		tx=session.beginTransaction();
		session.saveOrUpdate(admin);
		tx.commit();
		session.close();
		return true;
		
			}

}
