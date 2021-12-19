package com.lt.crs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import com.lt.crs.utilsDB.DBUtils;

public class AdminDAOImpl implements AdminDAOInterface{
	DBUtils db=null;
	Connection con=null;
	PreparedStatement ps=null;
	PreparedStatement ps1=null;
	PreparedStatement ps2=null;
static Scanner sc=new Scanner(System.in);
	@Override
	public void addProfessor() {
		db=new DBUtils();
		con=db.getConnection();
		
		// TODO Auto-generated method stub
		
		
		
		try {
			System.out.println("Do you want to add an existing professor to the user_role table Y or N ");			
			char addOrNot=sc.next().charAt(0);
			if(addOrNot=='y' || addOrNot=='Y') {
				
				System.out.println("Enter the existing  professor ID you want to add to the user_role");
		
				// TODO Auto-generated method stub
				int profId=sc.nextInt();
				
				ps=con.prepareStatement("select professor_id  from professor where professor_id=?");
				ps.setInt(1, profId);
				ps.execute();
				
				ResultSet rs=ps.executeQuery();
			if(rs.next()) {
			
				int profID=rs.getInt(1);
				
				
				ps.close();
				

				ps=con.prepareStatement("insert into user_role values(?,2)");
				ps.setInt(1, profID);
				ps.executeUpdate();
				System.out.println("Successfully added the professor ID"+profID+" to the user_role");
				ps.close();

				

			}
			else {System.out.println( "Sorry No such professor data is available in professor table");}

				
			}
			else {
				System.out.println("Enter Professor Name");
				String professorName=sc.next();
				System.out.println("Enter Department of the Professor");
				String  professorDept=sc.next();
				System.out.println("Enter the password for the professor");
				String professorPassword=sc.next();
			
			
			
		ps=con.prepareStatement("insert into professor (professor_name,professor_password,professor_department) values (?,?,?)",java.sql.Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, professorName);
		ps.setString(2,professorPassword);
		ps.setString(3,professorDept);
		ps.executeUpdate();
		ResultSet rs=ps.getGeneratedKeys();
	if(rs.next()) {
		System.out.println("Hello Admin the Professor ID for the added professor  is : "+ rs.getInt(1)+"and now adding to the USER_ROLE table");
		int profID=rs.getInt(1);
		
		ps.close();

		ps=con.prepareStatement("insert into user_role values(?,2)");
		ps.setInt(1, profID);
		ps.executeUpdate();
		System.out.println("Successfully added the professor ID"+profID+" to the user_role");
		ps.close();
	}
	
		con.close();
			}
	
		}
		catch(Exception e) {try {e.printStackTrace();ps.close();con.close();}catch(Exception e1) {e.printStackTrace();}	}


		
	}
	
	//Remove Professor
	@Override
	public void removeProfessor() {
		
		db=new DBUtils();
		con=db.getConnection();
	
		
		try {
			
			System.out.println("Enter the professor ID you want to remove");
	
			// TODO Auto-generated method stub
			int profId=sc.nextInt();
			
			ps=con.prepareStatement("select * from course_professor_student where professor_id=?");
			ps.setInt(1,profId);
			ResultSet rsp=ps.executeQuery();
			if(rsp.next()) {
				System.out.println("Sorry you cant remove the Professor who has been assigned to teach course");
				
			}
			else {
			
			ps=con.prepareStatement("select professor_id  from professor where professor_id=?");
			ps.setInt(1, profId);
			ps.execute();
			
			ResultSet rs=ps.executeQuery();
		if(rs.next()) {
		
			int profID=rs.getInt(1);
			
			
			ps.close();

			ps=con.prepareStatement("delete from  user_role where user_id=? and role_id=2");
			ps.setInt(1, profID);
			ps.executeUpdate();
			System.out.println("Successfully deleted the professor ID"+profID+" from the user_role");
			ps.close();
		}
		else {System.out.println( "Sorry No such professor data is available in professor table");}
		
			con.close();

		}
			}
			catch(Exception e) {try {e.printStackTrace();ps.close();con.close();}catch(Exception e1) {e.printStackTrace();}	}

		
	}
	
	
	
	

	//ADD Student
	@Override
	public void addStudent() {
		// TODO Auto-generated method stub
		db=new DBUtils();
		con=db.getConnection();
	
		try {
		ps=con.prepareStatement("select * from student");
		ResultSet rs=ps.executeQuery();
	
		while(rs.next()) {
			try {
			System.out.println("Do you want to approve student whose student ID is : "+rs.getInt(1));
			
			ps2=con.prepareStatement("select * from user_role where user_id=? and role_id=3");
			ps2.setInt(1, rs.getInt(1));
			ResultSet rs1=ps2.executeQuery();
			
			if(rs1.next())
			{ System.out.println("strudent ID "+rs.getInt(1)+" is already approved");}
			else {
			char addOrNot=sc.next().charAt(0);
			if(addOrNot=='y' || addOrNot=='Y') {
			ps1=con.prepareStatement("insert into user_role values(?,3)");
			ps1.setInt(1, rs.getInt(1));
			ps1.executeUpdate();
			}
			else {System.out.println("Admin rejected the student with student ID"+rs.getInt(1));}}}
			catch(Exception e) {e.printStackTrace();}
			
		}
		
		
		
		
		}
		catch(Exception e) {try {e.printStackTrace();ps.close();ps1.close();con.close();}catch(Exception e1) {e.printStackTrace();}	}

		
}
		
		
	
	
	
	//Remove Student
	@Override
	public void removeStudent() {
		// TODO Auto-generated method stub
		db=new DBUtils();
		con=db.getConnection();
		

		try {
			
			System.out.println("Enter the Student ID you want to remove");
	
			// TODO Auto-generated method stub
			int studId=sc.nextInt();
			
			ps=con.prepareStatement("select * from course_professor_student where student_id=?");
			ps.setInt(1,studId);
			ResultSet rsp=ps.executeQuery();
			if(rsp.next()) {
				System.out.println("Sorry you cant remove the Student  who has been assigned to take the  course");
				
			}
			else {
			
			ps=con.prepareStatement("select student_id  from student where student_id=?");
			ps.setInt(1, studId);
			ps.execute();
			
			ResultSet rs=ps.executeQuery();
		if(rs.next()) {
		
			int studID=rs.getInt(1);
			
			
			ps.close();

			ps=con.prepareStatement("delete from  user_role where user_id=? and role_id=3");
			ps.setInt(1, studID);
			ps.executeUpdate();
			System.out.println("Successfully deleted the Student ID"+studID+" from the user_role");
			ps.close();
		}
		else {System.out.println( "Sorry No such student  data is available in student  table");}
		
			con.close();

			}		
			}
			catch(Exception e) {try {e.printStackTrace();ps.close();con.close();}catch(Exception e1) {e.printStackTrace();}	}

			
		
	}
	
	
	//ADD Course

	@Override
	public void addCourse() {
		// TODO Auto-generated method stub
		db=new DBUtils();
		con=db.getConnection();
	
		try {

			System.out.println("Enter Course Name");
			String courseName=sc.next();
			System.out.println("Enter Course Cost");
			double courseCost=sc.nextDouble();
			
		ps=con.prepareStatement("select * from course where course_name=? ");
		ps.setString(1, courseName.toLowerCase());;
	ResultSet rs=ps.executeQuery();
		if(!rs.next()) {
			ps.close();
			ps=con.prepareStatement("insert into course(course_name,course_cost) values(?,?)");
			ps.setString(1, courseName.toLowerCase());;
			ps.setDouble(2,courseCost);
			
			ps.executeUpdate();
			System.out.println(" Course Added Successfully3.");
		}
		else {
			System.out.println("Course Already present So not able to add course which is already existed ");
		}
		}
	
		
		
		
		catch(Exception e) {try {e.printStackTrace();ps.close();ps1.close();con.close();}catch(Exception e1) {e.printStackTrace();}	}


		
		
	
	}

	@Override
	public void removeCourse() {
		// TODO Auto-generated method stub
		db=new DBUtils();
		con=db.getConnection();
	
		try {
			ps=con.prepareStatement("select course_id,professor_id,count(*) from course_professor_student group by 1,2");
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				int coId=rs.getInt(1);
				int profId=rs.getInt(2);
				int count=rs.getInt(3);
				if(count<3) {
					System.out.println("Removing the course with students less than the desired student");
					ps=con.prepareStatement("select * from course_professor_student where course_id=? and professor_id=?");
					ps.setInt(1,coId);
					ps.setInt(2,profId);
					ResultSet rs1=ps.executeQuery();
					System.out.println("Append all the corresponding students ID in the list "
							+ "and send them notification regard choose of another course");
					while(rs1.next()) {
						
						System.out.println("student ID: "+rs1.getInt(3));
					}
					//Delete Query
					
				}
				
				
				
				
			}
		
			
		}
	
		
		
		
		catch(Exception e) {try {e.printStackTrace();ps.close();ps1.close();con.close();}catch(Exception e1) {e.printStackTrace();}	}


		
		
		
	}
}