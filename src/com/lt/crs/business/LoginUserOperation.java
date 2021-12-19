package com.lt.crs.business;

import com.lt.crs.dao.LoginUserDAOImpl;

public class LoginUserOperation implements LoginUserInterface{
	com.lt.crs.dao.LoginUserDAOInterface user=null;
public void userLogin(int userId,String userPassword) {
		user=new LoginUserDAOImpl();
		user.userLogin(userId, userPassword);
		
	
		
		
	}

}
