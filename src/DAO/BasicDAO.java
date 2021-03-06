package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
		c = null;
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
		setQuery(query);	
	}
	protected void setQuery(String query) throws SQLException{
		ps = (PreparedStatement) c.prepareStatement(query);	
	}
	protected int getLastIdInserted(String tableName) throws Exception{
		ps = (PreparedStatement) c.prepareStatement("SELECT LAST_INSERT_ID() id FROM " + tableName);
		ResultSet res =  (ResultSet) ps.executeQuery();	
		res =  (ResultSet) ps.executeQuery();
		if (res.next()){
			return res.getInt("id");
		}
		else{
			throw new Exception("Last id not found");
		}
	}
}
