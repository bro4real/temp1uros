package com.brothers4real.tryjdbc;

import java.sql.*;
import java.util.ArrayList;

public class StudentList {
	

	public static void main(String[] args) {
		
		ArrayList<StudentDTO> students = new ArrayList<>();
		String[] grades = {"a", "b", "c", "d", "e", "f"};
		
		int rowCount = 0;
		
		String sqlQuerry = "SELECT * FROM Java_Students";
		String sqlQuerry2 = "SELECT * FROM Java_Students WHERE GRADE=?";
		
		//ResultSet rSet2 = null;
		
		try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/LessonNo22");
			Statement stmt1 = conn.createStatement();
			PreparedStatement stmt2 = conn.prepareStatement(sqlQuerry2);
			ResultSet rSet1 = stmt1.executeQuery(sqlQuerry);){	
				
			//try(ResultSet rSet1 = stmt1.executeQuery(sqlQuerry);){
				
				while(rSet1.next()){
					
					StudentDTO currSt = new StudentDTO();
					currSt.setStudentNo(rSet1.getInt(1));
					currSt.setStName(rSet1.getString(2));
					currSt.setStGrade(rSet1.getString(3));
					
					students.add(currSt);
					//rowCount++;
				}
				
				rowCount = students.size();
				
				//System.out.println(rowCount);
					
				for (String grade : grades){
					stmt2.setString(1, grade);
					try	(ResultSet rSet2 = stmt2.executeQuery();){
						int equalGradeStudents = 0;
						while(rSet2.next()){
							equalGradeStudents++;
						}		
					System.out.println("percent of " + grade 
							+ " students: " + equalGradeStudents*100/rowCount + "%");
					}
				}

			 
		} catch (SQLException se) {
			System.out.println("SQLError: " + se.getMessage() + " code: " + se.getErrorCode());
			se.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			
		} 
	}
	
}
