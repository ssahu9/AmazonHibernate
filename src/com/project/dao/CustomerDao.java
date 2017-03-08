package com.project.dao;

import com.project.bean.Customer;

public interface CustomerDao {

	//insert function linked to CustomerDaoImpl to insert customer information
	int insert(Customer customer) ;

	//validation function linked to CustomerDaoImpl to check email and password
	Customer validation(String mail, String password) ;

	//update function linked to CustomerDaoImpl to update customer details
	boolean update(Customer customer) ;

}
