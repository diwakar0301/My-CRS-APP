package com.lt.crs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import com.lt.crs.utilsDB.DBUtils;

public class StudentDAOImpl implements StudentDAOInterface{
	static Scanner sc=new Scanner(System.in);
	DBUtils db=null;
	Connection con=null;
	PreparedStatement ps=null;
	PreparedStatement ps1=null;
	PreparedStatement ps2=null;
	@Override
	public void addCourse(int studentId) {
		// TODO Auto-generated method stub
		db=new DBUtils();
		con=db.getConnection();
		
		try {
			ps=con.prepareStatement("select cp.course_id,c.course_name from course_professor cp "
					+ "inner join course c on c.course_id=cp.course_id inner join professor p on p.professor_id=cp.professor_id group by 1,2;");
			ResultSet rs=ps.executeQuery();
			System.out.println("\t\tCourse ID \t\tCourse Name");
			while(rs.next()) {
				
				System.out.println("\t\t\t"+rs.getInt(1)+"\t\t\t\t"+rs.getString(2).trim());
	
			}
			
	
			ps=con.prepareStatement("select distinct cps.student_id,cps.course_id,cps.professor_id"
					+ " from course_professor_student cps where cps.student_id=?");
			ps.setInt(1,studentId);
			ResultSet rs1=ps.executeQuery();
			int noOfCourse=0;
			while(rs1.next()) {
				noOfCourse+=1;
			}
			if(noOfCourse!=4) {
				System.out.println("You are eligible to select course");
				System.out.println("Enter Course ID from the above listed details");
				int couId=sc.nextInt();
				ps=con.prepareStatement("select * from course_professor where course_id=?");
				ps.setInt(1,couId);
				ResultSet rs2=ps.executeQuery();
				boolean inserted=false;
				while(rs2.next()) {
					int profId=rs2.getInt(2);
					ps=con.prepareStatement("select count(*) from course_professor_student where course_id=? and professor_id=?");
					ps.setInt(1,couId);
					ps.setInt(2,profId);
					
					ResultSet rs3=ps.executeQuery();
					int rows=0;
					if(rs3.next()) {
						rows=rs3.getInt(1);
										}
					if(rows>=10) {}
					else {
					ps=con.prepareStatement("select * from course_professor_student "
							+ "where course_id=? and student_id=?");	
					ps.setInt(1,couId);
					ps.setInt(2,studentId);
					ResultSet rs4=ps.executeQuery();
					if(rs4.next()) {
						System.out.println("Sorry you have already registered this course So please select another");
					}
					else {
						ps=con.prepareStatement("insert into course_professor_student values(?,?,?)");
						ps.setInt(1,couId);
						ps.setInt(2,profId);
						ps.setInt(3,studentId);
						ps.executeUpdate();
						System.out.println("Inserted Successfully");
						inserted=true;
						break;
					}
					
					}
					
				
				}//while
				if(inserted==false) {System.out.println(" The course ID you selected "
						+ " has no vacancy So please select another course ID");}
				
		
			}
				
			if(noOfCourse==4) {System.out.println("Sorry you have already registered four courses");}
		
			
		}
		catch(Exception e) {try {e.printStackTrace();ps.close();con.close();}catch(Exception e1) {e.printStackTrace();}	}


		}
	@Override
	public void dropCourse(int studentId) {
		// TODO Auto-generated method stub
		db=new DBUtils();
		con=db.getConnection();
		
		try {
	ps=con.prepareStatement("select *  from course_professor_student where student_id=? ");
	ps.setInt(1,studentId);
	ResultSet rs=ps.executeQuery();
	while(rs.next()){
		int couId=rs.getInt(1);
		int profId=rs.getInt(2);
		int studId=rs.getInt(3);
		System.out.println("Do you want to Drop  the course ID : "+couId);
		char yesOrNo=sc.next().charAt(0);
		if(yesOrNo=='y' || yesOrNo=='Y'){
		ps=con.prepareStatement("delete from course_professor_student where course_id=? and professor_id=? and student_id=?");
		ps.setInt(1,couId);
		ps.setInt(2,profId);
		ps.setInt(3,studId);
		ps.executeUpdate();
		System.out.println("Dropped the course ID "+couId);
		}
		
	}
	
			
		}
		catch(Exception e) {try {e.printStackTrace();ps.close();con.close();}catch(Exception e1) {e.printStackTrace();}	}

		
	}
	@Override
	public void viewEnrolledCourses(int studentId) {
		// TODO Auto-generated method stub
		db=new DBUtils();
		con=db.getConnection();
		
		try {
	ps=con.prepareStatement("select *  from course_professor_student where student_id=? ");
	ps.setInt(1,studentId);
	ResultSet rs=ps.executeQuery();
	System.out.println("Please see the below details of the courses you enrolled");
	while(rs.next()){
		int couId=rs.getInt(1);
		int profId=rs.getInt(2);
		int studId=rs.getInt(3);
		System.out.println(" 	course ID  :	"+couId+"		professor ID : 		"+profId+" 		student Id: 	"+studId);;
		
	}
	
			
		}
		catch(Exception e) {try {e.printStackTrace();ps.close();con.close();}catch(Exception e1) {e.printStackTrace();}	}

		

		
	}
	@Override
	public void viewReportCard(int studentId) {
		// TODO Auto-generated method stub

		db=new DBUtils();
		con=db.getConnection();
		
		try {
		
			ps=con.prepareStatement(" select c.course_id,c.course_name,s.student_name,sg.mark,sg.grade from "
					+ "student_grade sg inner join"
					+ " student s on sg.student_id=s.student_id inner join course c on sg.course_id=c.course_id where sg.student_id=?");
			ps.setInt(1,studentId);
			ResultSet rs=ps.executeQuery();
			System.out.println("Displaying report card for the student ID:  "+studentId);
			System.out.println("\tCourse ID \tCourse Name \tMark \tGrade");
			while(rs.next()) {
			System.out.println("\t\t"+rs.getInt(1)+"\t\t\t"+rs.getString(2)+"\t\t\t\t"+rs.getDouble(4)+"\t\t"+rs.getString(5));
			}
			
			
			
		}
		catch(Exception e) {try {e.printStackTrace();ps.close();con.close();}catch(Exception e1) {e.printStackTrace();}	}

		
	}
		
		
	
}
