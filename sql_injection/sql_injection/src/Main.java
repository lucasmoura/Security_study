
public class Main 
{
	
	public static void main(String[] args)
	{
		
		System.out.println("Starting application...");
		
		SQL_Injection sql_Injection = new SQL_Injection();
		
		int validate = sql_Injection.connectToDatabase();
		
		if(validate != SQL_Injection.DATABASE_CONNECTION_SUCCEEDED)
		{
			System.out.println("Error while creating database: "+validate);
			System.exit(-1);
		}
		
		boolean query_validate = sql_Injection.doQuery(1);
		
		if(!query_validate)
		{
			System.out.println("Error while executing required query");
			System.exit(-2);
		}
			
	}

}
