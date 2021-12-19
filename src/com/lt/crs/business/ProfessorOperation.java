package com.lt.crs.business;

import com.lt.crs.dao.ProfessorDAOImpl;
import com.lt.crs.dao.ProfessorDAOInterface;

public class ProfessorOperation implements ProfessorInterface{

	ProfessorDAOInterface prd=null;
	@Override
	public void viewStudent(int profId) {
		// TODO Auto-generated method stub
		prd=new ProfessorDAOImpl();
		prd.viewStudent(profId);
		
	}
	@Override
	public void addGrade(int profId) {
		// TODO Auto-generated method stub
		prd=new ProfessorDAOImpl();
		prd.addGrade(profId);
		
	}

}
