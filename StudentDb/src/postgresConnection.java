import java.sql.*;

import javax.swing.*;
public class postgresConnection {
    
	 Connection con=null;
	public static Connection dbConnector()
	{
		try {
			Class dbDriver=Class.forName("org.postgresql.Driver");
			String jdbcURL="jdbc:postgresql://localhost:5432/Student Registration";
			
			Connection	con=DriverManager.getConnection(jdbcURL,"postgres","1234");
			//JOptionPane.showMessageDialog(null, "Connection successfull");
			
			return con;
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
	
}
