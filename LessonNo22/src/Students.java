import java.sql.*;
public class Students {

	static public void main(String args[]){
		
		int rowCount = 0;
		
		String sqlQuerry = "SELECT * FROM Java_Students ORDER BY grade";
		try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/LessonNo22");
			Statement stmt = conn.createStatement();
			ResultSet rSet = stmt.executeQuery(sqlQuerry);){
			
			ResultSetMetaData rsmd = rSet.getMetaData();
			
			int numOfCols = rsmd.getColumnCount();
			for (int i =1; i<=numOfCols; i++){
				System.out.print(rsmd.getColumnName(i) + "   ");
			}
			System.out.println();
			
			while(rSet.next()){
				int stNumber = rSet.getInt(1);
				String stName = rSet.getString(2);
				String grade = rSet.getString(3);
				rowCount++;
				//System.out.println(stNumber + " " + stName + " " + grade);
				System.out.print(String.format("%3d", stNumber));
				System.out.print(String.format("%8s", stName));
				System.out.print(String.format("%8s", grade));
				System.out.println();
				
			}
			
			System.out.println("total: " + rowCount);
			
		} catch (SQLException se) {
			System.out.println("SQLError: " + se.getMessage() + " code: " + se.getErrorCode());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			
		} 
		
	}
	
}
