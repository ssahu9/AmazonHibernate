package com.project.dao;

import java.sql.SQLException;

import com.project.bean.Admin;

public interface AdminDao {

	//validation function linked to AdminDaoImpl
	boolean validation(String mail, String password);

	//update function linked to AdminDaoImpl
	boolean update(Admin a) ;
}
