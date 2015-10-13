package DAO;

import java.sql.ResultSet;

import modelos.Favorito;
import modelos.Usuario;

public class UsuarioDAO extends BasicDAO {
	public Usuario obter(String email) throws Exception{
		try{
			criarQuery("SELECT username FROM user WHERE email = ?");
			ps.setString(1, email);
			ResultSet res =  (ResultSet) ps.executeQuery();
			Usuario usuario = new Usuario();
			if (res.next()){
				usuario.setNome(res.getString("username"));
			}
			return usuario;
		}
		catch(Exception e){
			throw e;
		}
		finally{
			close();
		}
	}
}
