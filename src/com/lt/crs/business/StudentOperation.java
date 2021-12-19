package com.lt.crs.business;

import com.lt.crs.dao.StudentDAOImpl;
import com.lt.crs.dao.StudentDAOInterface;

public class StudentOperation implements StudentInterface{
	
	StudentDAOInterface stud=null;

	@Override
	public void addCourse(int studentId) {
		// TODO Auto-generated method stub
		stud=new StudentDAOImpl();
		stud.addCourse(studentId);
		
	}

	@Override
	public void dropCourse(int studentId) {
		// TODO Auto-generated method stub
		stud=new StudentDAOImpl();
		stud.dropCourse(studentId);
	}

	@Override
	public void viewEnrolledCourse(int studentId) {
		// TODO Auto-generated method stub
		stud=new StudentDAOImpl();
		stud.viewEnrolledCourses(studentId);
	
		
	}

	@Override
	public void viewReportCard(int studentId) {
		// TODO Auto-generated method stub
		stud=new StudentDAOImpl();
		stud.viewReportCard(studentId);
		
	}

}
