package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BasicDAO {
	Connection c = null;
	PreparedStatement ps = null;	
	
	public BasicDAO(){}
	public BasicDAO (Connection connection){
		c = connection;
	}
	
	//refatoring
	protected void openConection() throws SQLException{
		c = new MyConnection().getConnection();
	}
	
	public void close()  throws Exception{
		c.close();
		ps.close();
	}
	
	protected void beginTransaction() throws SQLException{
		c.setAutoCommit(false);
	}
	
	protected void commitTransaction() throws SQLException{
		c.commit();
	}
	
	protected void criarQuery(String query) throws SQLException{
		if (c == null){
			openConection();
		}
		ps = (PreparedStatement) c.prepareStatement(query);	
	}
	protected void setQuery(String query) throws SQLException{
		ps = (PreparedStatement) c.prepareStatement(query);	
	}
}
