package com.lt.crs.app;

import java.util.Scanner;

import com.lt.crs.business.StudentInterface;
import com.lt.crs.business.StudentOperation;

public class CRSStudent {
	static Scanner sc=new Scanner(System.in);
	StudentInterface studSer=null;
	

	public void studentMenu(int studentId) {
		// TODO Auto-generated method stub
		
System.out.println("*********************Now you are in the STUDENT  MENU ************************");
		
		Boolean studentMenu=true;
		while(studentMenu) {
		
		System.out.println("\n 1. Enroll Courses \n2.Drop Course \n3.View Enrolled Courses \n4.View Report Card \n 5. Logout");
		int studentSelect=sc.nextInt();
		switch(studentSelect) {
		case 1:
			studSer=new StudentOperation();
			studSer.addCourse(studentId);
			break;
		case 2:
			studSer=new StudentOperation();
			studSer.dropCourse(studentId);
			break;
		case 3:
			studSer=new StudentOperation();
			studSer.viewEnrolledCourse(studentId);
			break;
		case 4:
			
			studSer=new StudentOperation();
			studSer.viewReportCard(studentId);
			break;
		
		case 5:
			System.out.println(" Are you sure you want to log out Y or N: ");
			char ask=sc.next().charAt(0);
			if(ask=='y' || ask=='Y') {studentMenu=false;}
			
		
		}

		
		
		
		
		}
		}
	
	
	}