package com.lt.crs.app;

import java.util.Scanner;

import com.lt.crs.business.ProfessorInterface;
import com.lt.crs.business.ProfessorOperation;

public class CRSProfessor {
	static Scanner sc=new Scanner(System.in);
	ProfessorInterface profser=null;
	

	public void profMenu(int profId) {
		// TODO Auto-generated method stub
		
System.out.println("*********************Now you are in the Professor  MENU ************************");
		
		Boolean profMenu=true;
		while(profMenu) {
		
		System.out.println("\n 1. View Enrolled Students \n2.Add Grades to the Student \n3.Logout");
		int profSelect=sc.nextInt();
		switch(profSelect) {
		case 1:
			profser=new ProfessorOperation();
			profser.viewStudent(profId);
			
			break;
		case 2:
			profser=new ProfessorOperation();
			profser.addGrade(profId);
			break;
		
		case 3:
			System.out.println(" Are you sure you want to log out Y or N: ");
			char ask=sc.next().charAt(0);
			if(ask=='y' || ask=='Y') {profMenu=false;}
			
		
		}

		
		
		
		
		}
		}
	

}
