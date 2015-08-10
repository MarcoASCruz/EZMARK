package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import modelos.Favorito;
import modelos.Tag;

public class TagDAO extends BasicDAO {
	TagDAO(){}
	TagDAO(Connection connection){
		super(connection);
	}
	public String adicionar(String tag) throws Exception{
		try{
			criarQuery("INSERT INTO  tag (nome) VALUES (?)");
			ps.setString(1, tag);
			ps.executeUpdate();
			return tag;
		}
		catch(Exception e){
			throw e;
		}
		finally{
			if(c.getAutoCommit()){
				close();
			}
		}
	}
	
	public String buscar(String tag) throws Exception{
		try{
			criarQuery("SELECT nome FROM Tag WHERE nome = ?");
			ps.setString(1, tag);
			ResultSet res =  (ResultSet) ps.executeQuery();	
			res =  (ResultSet) ps.executeQuery();
			String result = null;
			if (res.next()){
				result = res.getString("nome");
			}
			return result;
		}
		catch(Exception e){
			throw e;
		}
		finally{
			if(c.getAutoCommit()){
				close();
			}
		}
	} 
}
