import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class SQL_Injection 
{
	
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	private static final String DB_URL = "jdbc:mysql://localhost/security";
	
	public static final int DATABASE_CONNECTION_SUCCEEDED = 0;
	public static final int JDBC_DRIVER_ERROR = -1;
	public static final int DATABASE_CONNECTION_ERROR = -2;
	
	private static final String USER = "user";
	private static final String PASS = "password";

	private Connection conn;
	
	public SQL_Injection()
	{
		conn = null;
	}
	
	public int connectToDatabase()
	{
		try 
		{
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e1) 
		{
			return JDBC_DRIVER_ERROR;
		}

	    System.out.println("Connecting to database...");
	    try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException e)
		{
			return DATABASE_CONNECTION_ERROR;
		}
	    
	    return DATABASE_CONNECTION_SUCCEEDED;
	}
	
	public boolean doQuery(int id)
	{
		
		try
		{
		
			Statement statement = conn.createStatement();
			
			ResultSet resultSet = statement.executeQuery(
			" SELECT * FROM users WHERE id = " + id);
			
			while (resultSet.next())
			{
				System.out.println("Id: "+resultSet.getInt("id"));
				System.out.println("Name: "+resultSet.getString("name"));
				System.out.println("Age: "+resultSet.getInt("age"));
				System.out.println("Sex: "+resultSet.getString("sex")+"\n");
				
			}
		
			resultSet.close();
			statement.close();
		
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		}
		finally
		{
			try
			{
				conn.close();
			} 
			
			catch(SQLException e) {}
		}
				
		return true;
	}
		
}
	
