package DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class MyConnection {
	private Connection concection;	

	MyConnection(){		
		try
		{	
			String database = "jdbc:mysql://127.0.0.1:3306/gerenciador_de_favoritos";
			String usuario = "root";
			String senha = "vertrigo";		
			Class.forName("com.mysql.jdbc.Driver"); 
			concection = (Connection) DriverManager.getConnection( database, usuario, senha );

		}
		catch ( Exception e ) {
			e.printStackTrace();
		}		
	}
	public void close(){	
		try	{	
			concection.close();
		}
		catch ( Exception e ) {
			e.printStackTrace();
		}
	}	
	public Connection getConnection()	{	
		return concection;		
	}

}
