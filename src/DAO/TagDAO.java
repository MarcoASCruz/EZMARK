package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import modelos.Favorito;
import modelos.Tag;

public class TagDAO extends BasicDAO {
	public Tag adicionar(Tag t) throws Exception{
		try{
			criarQuery("INSERT INTO  tag (nome) VALUES (?)");
			ps.setString(1, t.getNome());
			ps.executeUpdate();
			ps = (PreparedStatement) c.prepareStatement("SELECT LAST_INSERT_ID() id FROM tag");
			ResultSet res =  (ResultSet) ps.executeQuery();	
			res =  (ResultSet) ps.executeQuery();	
			if (res.next()){
				t.setId(res.getInt("id"));
			}
			return t;
		}
		catch(Exception e){
			throw e;
		}
		finally{
			close();
		}
	}
	
	public int buscar(Tag t) throws Exception{
		try{
			criarQuery("SELECT id, nome FROM Tag WHERE nome = ?");
			ps.setString(1, t.getNome());
			ResultSet res =  (ResultSet) ps.executeQuery();	
			res =  (ResultSet) ps.executeQuery();
			int result = 0;
			if (res.next()){
				result = res.getInt("id");
			}
			return result;
		}
		catch(Exception e){
			throw e;
		}
		finally{
			close();
		}
	} 
}
