package com.lt.crs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.lt.crs.app.CRSAdmin;
import com.lt.crs.app.CRSProfessor;
import com.lt.crs.app.CRSStudent;
import com.lt.crs.utilsDB.DBUtils;

public class LoginUserDAOImpl implements LoginUserDAOInterface{
	
	
	DBUtils db=null;
	Connection con=null;
	PreparedStatement ps=null;
	CRSAdmin admin=null;
	CRSStudent student=null;
	CRSProfessor professor=null;
public void userLogin(int userId,String userPassword) {

	db=new DBUtils();
	con=db.getConnection();
		try {
			ps=con.prepareStatement("select * from student where student_id=? and student_password=?");
			ps.setInt(1, userId);
			ps.setString(2, userPassword);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				int studentId=rs.getInt(1);
				ps.close();
		//new prepared statement 
				ps=con.prepareStatement("select * from user_role where user_id=? and role_id=3");
				ps.setInt(1, studentId);
				ResultSet rs1=ps.executeQuery();
				if(rs1.next()) {
					System.out.println("student login successfull and you are redirecting to the student menu");
					student=new CRSStudent();
					student.studentMenu(studentId);
					
					ps.close();
				}
				else {
					System.out.println("Sorry You are not authenticated or Please enter VALID CREDENTIALS");
				}
				
			}
			
			ps=con.prepareStatement("select * from professor where professor_id=? and professor_password=?");
			ps.setInt(1, userId);
			ps.setString(2, userPassword);
			ResultSet rs4=ps.executeQuery();
			if(rs4.next()) {
				int profId=rs4.getInt(1);
				ps.close();
		//new prepared statement 
				ps=con.prepareStatement("select * from user_role where user_id=? and role_id=2");
				ps.setInt(1, profId);
				ResultSet rs5=ps.executeQuery();
				if(rs5.next()) {
					System.out.println("professor login successfull and you are redirecting to the professor menu");
					professor=new CRSProfessor();
					professor.profMenu(profId);
					
					ps.close();
				}
				else {
					System.out.println("Sorry You are not authenticated or Please enter VALID CREDENTIALS");
				}
				
			}
			
			
			ps=con.prepareStatement("select * from admin where admin_id=? and admin_password=?");
			ps.setInt(1, userId);
			ps.setString(2, userPassword);
			ResultSet rs2=ps.executeQuery();
			if(rs2.next()) {
				int adminId=rs2.getInt(1);
				ps.close();
		//new prepared statement 
				ps=con.prepareStatement("select * from user_role where user_id=? and role_id=1");
				ps.setInt(1, adminId);
				ResultSet rs3=ps.executeQuery();
				if(rs3.next()) {
					System.out.println("admin login Successfull and you are redirecting to the ADMIN MENU");
					admin=new CRSAdmin();
					admin.adminMenu(adminId);
					
					
					ps.close();
					con.close();
				}
				else {
					System.out.println("Sorry You are not authenticated or Please enter VALID CREDENTIALS");
				}
				
			}
			

			
			
			System.out.println("Please Enter VALID CREDENTIALS");
		}
			catch(Exception e) {try {e.printStackTrace();ps.close();con.close();}catch(Exception e1) {e.printStackTrace();}	}
	
		
		
	}


}
