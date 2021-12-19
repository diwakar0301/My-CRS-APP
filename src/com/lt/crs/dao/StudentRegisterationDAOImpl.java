package com.lt.crs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.lt.crs.bean.Student;
import com.lt.crs.utilsDB.DBUtils;

public class StudentRegisterationDAOImpl implements StudentRegisterationDAOInterface{
	DBUtils db=null;
	Connection con=null;
	PreparedStatement ps=null;
	
	
public void register(Student student) {
		db=new DBUtils();
		con=db.getConnection();
		try {
		ps=con.prepareStatement("insert into student (student_name,student_password,student_department) values (?,?,?)",java.sql.Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, student.getStudentName());
		ps.setString(2,student.getStudentPassword());
		ps.setString(3,student.getStudentDepartment());
		ps.executeUpdate();
		ResultSet rs=ps.getGeneratedKeys();
	if(rs.next()) {
		System.out.println("your student ID is : "+ rs.getInt(1));
	}
		ps.close();
		con.close();

	
		}
		catch(Exception e) {try {e.printStackTrace();ps.close();con.close();}catch(Exception e1) {e.printStackTrace();}	}
				
		
	}

}
