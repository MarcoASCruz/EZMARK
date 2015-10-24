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
		validarTag(tag);
		criarQuery("INSERT INTO  tag (nome) VALUES (?)");
		ps.setString(1, tag);
		ps.executeUpdate();
		if(c.getAutoCommit()){
			close();
		}
		return tag;
	}
	private void validarTag(String tag) throws Exception{
		if(tag == null || tag.isEmpty()){
			throw new Exception("Tag vazia ou nulla");
		}
	}
	
	public String buscar(String tag) throws Exception{
		criarQuery("SELECT nome FROM Tag WHERE nome = ?");
		ps.setString(1, tag);
		ResultSet res =  (ResultSet) ps.executeQuery();	
		res =  (ResultSet) ps.executeQuery();
		String result = null;
		if (res.next()){
			result = res.getString("nome");
		}
		if(c.getAutoCommit()){
			close();
		}
		return result;
	} 
}
