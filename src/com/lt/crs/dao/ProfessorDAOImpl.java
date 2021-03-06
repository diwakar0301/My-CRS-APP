package com.lt.crs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import com.lt.crs.utilsDB.DBUtils;

public class ProfessorDAOImpl implements ProfessorDAOInterface{
	
	static Scanner sc=new Scanner(System.in);
	DBUtils db=null;
	Connection con=null;
	PreparedStatement ps=null;
	PreparedStatement ps1=null;

	@Override
	public void viewStudent(int profId) {
		// TODO Auto-generated method stub
		db=new DBUtils();
		con=db.getConnection();
		
		try {
			ps=con.prepareStatement("\r\n"
					+ "select s.student_id,s.student_name,c.course_name,p.professor_name from course_professor_student cps "
					+ "inner join course c on c.course_id=cps.course_id inner join professor p "
					+ "on p.professor_id=cps.professor_id inner join student s on cps.student_id=s.student_id where cps.professor_id=?");
			ps.setInt(1,profId);
			ResultSet rs=ps.executeQuery();
			System.out.println("Displaying Enrolled Students");
			while(rs.next()) {
				
				System.out.println("Student ID : 	"+rs.getInt(1)+
						"		Student Name:		"+rs.getString(2)+"		Course Name: 		"+rs.getString(3)+" 	Professor Name :	"+rs.getString(4));
				
				
			}
			
			
			
			
		}
		catch(Exception e) {try {e.printStackTrace();ps.close();con.close();}catch(Exception e1) {e.printStackTrace();}	}

		
	}

	@Override
	public void addGrade(int profId) {
		// TODO Auto-generated method stub
		db=new DBUtils();
		con=db.getConnection();
		
		try {
			ps=con.prepareStatement("\r\n"
					+ "select s.student_id,s.student_name,c.course_name,p.professor_name,c.course_id  from course_professor_student cps "
					+ "inner join course c on c.course_id=cps.course_id inner join professor p "
					+ "on p.professor_id=cps.professor_id inner join student s on cps.student_id=s.student_id where cps.professor_id=?");
			ps.setInt(1,profId);
			ResultSet rs=ps.executeQuery();
			System.out.println("Displaying Enrolled Students");
			while(rs.next()) {
				System.out.println("Do you want to add Grade to the Following Student: ");
				System.out.println("Student ID : 	"+rs.getInt(1)+
						"		Student Name:		"+rs.getString(2)+"		Course Name: 		"+rs.getString(3)+" 	"
								+ "Professor Name :	"+rs.getString(4)+"		Course ID :	 "+rs.getInt(5)	);
				char ask=sc.next().charAt(0);
				if(ask=='y' || ask=='Y') {
					System.out.println("Adding Grade :");
					try {
						System.out.println("Enter Mark : ");
						double mark=sc.nextDouble();
						String grade=findingGrade(mark);
						
						ps1=con.prepareStatement("insert into student_grade values(?,?,?,?,?)");
						ps1.setInt(1,rs.getInt(5));
						ps1.setInt(2,profId);
						ps1.setInt(3,rs.getInt(1));
						ps1.setDouble(4,mark);
						ps1.setString(5,grade);
						ps1.executeUpdate();
						System.out.println("Grade Added Successfully");
						
											
					}catch(Exception e) {e.printStackTrace();}
				}
			
				
			}
			
			
			
			
		}
		catch(Exception e) {try {e.printStackTrace();ps.close();con.close();}catch(Exception e1) {e.printStackTrace();}	}

		
	}

	private static String findingGrade(double mark) {
		// TODO Auto-generated method stub
		if(mark>=0 && mark<50) {return "Fail";}
		else if(mark>=50 && mark<60){return "E";}
		else if(mark>=60 && mark<70){return "D";}
		else if(mark>=70 && mark<80){return "C";}
		else if(mark>=80 && mark<90){return "B";}
		else{return "A";}

	}

}
