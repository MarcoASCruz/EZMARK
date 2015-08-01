package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BasicDAO {
	Connection c = null;
	PreparedStatement ps = null;
	
	protected void criarQuery(String query) throws SQLException{
		c = new MyConnection().getConnection();
		ps = (PreparedStatement) c.prepareStatement(query);	
	} 
	
	protected void setQuery(String query) throws SQLException{
		ps = (PreparedStatement) c.prepareStatement(query);	
	}
	
	public void close()  throws Exception{
		c.close();
		ps.close();
	}
}
