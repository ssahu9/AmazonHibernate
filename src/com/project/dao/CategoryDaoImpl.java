package com.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import com.project.bean.Category;


public class CategoryDaoImpl implements CategoryDao {
//	CreateConnection cd = new CreateConnection();
	
	
	Configuration cfg=null;
	private SessionFactory factory=null;
	public CategoryDaoImpl() {
	
		cfg=new AnnotationConfiguration();
		cfg.configure("hibernate.cfg.xml");
		factory=cfg.buildSessionFactory();
	}
	
	//view category function made for customers to view all categories
	public List<Category> viewCategory()  {
		
		ArrayList<Category> list = new ArrayList<Category>();
		
		Session session=factory.openSession();
		Transaction tx=null;
		tx=session.beginTransaction();
		Query query=session.createQuery("from Category");
		list=(ArrayList<Category>)query.list();
	
		tx.commit();
		session.close();
		return list;
		
	}

	
	//function to insert new category used by admin
	@Override
	public boolean insertCategory(String categoryName)  {
		
		
		Category category = new Category(categoryName);
		Session session=factory.openSession();
		Transaction tx=null;
		tx=session.beginTransaction();
		session.save(category);
		tx.commit();
		session.close();
		return true;
		
		
		
	}

	//function to delete pre-existing category used by admin
	@Override
	public boolean deleteCategory(String categoryName)  {
	
		Category category = new Category(categoryName);
		Session session=factory.openSession();
		Transaction tx=null;
		tx=session.beginTransaction();
		session.delete(category);
		tx.commit();
		session.close();
		return true;
		
	}

}
