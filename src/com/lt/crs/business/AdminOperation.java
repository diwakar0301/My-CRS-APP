package com.lt.crs.business;

import com.lt.crs.dao.AdminDAOInterface;
import com.lt.crs.dao.AdminDAOImpl;

public class AdminOperation implements AdminInterface {
	AdminDAOInterface admin=null;

	@Override
	public void addProfessor() {
		// TODO Auto-generated method stub
		admin=new AdminDAOImpl();
		admin.addProfessor();
		
	}

	@Override
	public void removeProfessor() {
		// TODO Auto-generated method stub
		admin=new AdminDAOImpl();
		admin.removeProfessor();
		
	}

	@Override
	public void addStudent() {
		// TODO Auto-generated method stub
		admin=new AdminDAOImpl();
		admin.addStudent();
		
		
		
	}

	@Override
	public void removeStudent() {
		// TODO Auto-generated method stub
		admin=new AdminDAOImpl();
		admin.removeStudent();
		
	}

	@Override
	public void addCourse() {
		// TODO Auto-generated method stub
		admin=new AdminDAOImpl();
		admin.addCourse();
		
	}

	@Override
	public void removeCourse() {
		// TODO Auto-generated method stub
		admin=new AdminDAOImpl();
		admin.removeCourse();
		
	}

	@Override
	public void reportCardGeneration() {
		// TODO Auto-generated method stub
		admin=new AdminDAOImpl();
		admin.reportCardGeneration();
		
		
	}
	

}
