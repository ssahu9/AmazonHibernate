package com.project.dao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.project.bean.Category;
import com.project.bean.Customer;
import com.project.bean.Product;
import com.project.helper.CreateConnection;
import com.project.helper.DisplayErrorMessage;
import com.sujata.bean.Employee;

public class ProductDaoImpl implements ProductDao {
	
	Configuration cfg=null;
	private SessionFactory factory=null;
	public   ProductDaoImpl() {
	
		cfg=new AnnotationConfiguration();
		cfg.configure("hibernate.cfg.xml");
		factory=cfg.buildSessionFactory();
	}
	
	// Method to add new product details in databse.
	public boolean addProduct(Product product) throws SQLException, ClassNotFoundException {
		
		
		Transaction tx=null;
		Session session=null;
		
		try{
		 session=factory.openSession();
		 tx=null;
		tx=session.beginTransaction();
		session.save(product);
		
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

	// Method to delete the particular product by using product id .

	@Override
	public Boolean deleteProduct(int pid) throws SQLException, ClassNotFoundException {
	
		Transaction tx=null;
		Session session=null;
		
		try{
		 session=factory.openSession();
		 tx=null;
		tx=session.beginTransaction();
		session.delete(pid);
		
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

	// Method to update the product details such as quantity, price, discount by
	// using product id.

	@Override
	public boolean updateProduct(Product product) throws SQLException, ClassNotFoundException {
		Transaction tx=null;
		Session session=null;
		
		try{
		 session=factory.openSession();
		 tx=null;
		tx=session.beginTransaction();
		session.update(product);
		
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

	// method to display all product of particular category
	// and if category string is null then display all product from all
	// category.
	@Override
	public List<Product> viewProduct(String pcategory) throws SQLException, ClassNotFoundException {
		
		Transaction tx=null;
		Session session=null;
		
		List<Product> prodList = new ArrayList<Product>();
		
		
		return prodList; // returning back product list to Business Logic layer.
		try{
			 session=factory.openSession();
			
			
			tx=session.beginTransaction();
			String hql = " from product_info where product_name=?";
			Query query = session.createQuery(hql);
			List<Category> list = query.list();

			for(Category category:list){
				
			}
			
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

	// searching product by using product name.
	@Override
	public Product searchProductByName(String productName) throws SQLException, ClassNotFoundException {
		con = CreateConnection.getCon();
		pstmt = con.prepareStatement("select * from product_info where product_name=?");
		pstmt.setString(1, productName);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			int pid = rs.getInt("product_id");
			String productname = rs.getString("product_name");

			String category = rs.getString("product_category");
			int productprice = rs.getInt("product_price");
			int productquantity = rs.getInt("product_quantity");
			product = new Product();
			product.setProductId(pid);
			product.setName(productname);
			product.setCategory(category);
			product.setPrice(productprice);
			product.setQuantity(productquantity);

		}
		con.close();
		return product;

	}
	

}
