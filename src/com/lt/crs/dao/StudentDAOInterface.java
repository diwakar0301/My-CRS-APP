package com.lt.crs.dao;

public interface StudentDAOInterface {

	public void addCourse(int studentId);

	public void dropCourse(int studentId);

	public void viewEnrolledCourses(int studentId);

	public void viewReportCard(int studentId);

}
