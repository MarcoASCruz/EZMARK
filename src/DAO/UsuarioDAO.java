package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import modelos.Favorito;
import modelos.Usuario;

public class UsuarioDAO extends BasicDAO {
	public Usuario obter(String email) throws Exception{
		try{
			criarQuery("SELECT id, username FROM user WHERE email = ?");
			ps.setString(1, email);
			ResultSet res =  (ResultSet) ps.executeQuery();
			Usuario usuario = new Usuario();
			if (res.next()){
				usuario.setNome(res.getString("username"));
				usuario.setId(res.getInt("id"));
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
	public Usuario adicionar(Usuario usuario) throws Exception{
		openConection();
		beginTransaction();
		setQuery("INSERT INTO user (username,	password, enable, email, role_id) VALUES (?,?,?,?,?)");
		ps.setString(1, usuario.getNome());
		ps.setString(2, usuario.getSenha());
		ps.setBoolean(3, true);
		ps.setString(4, usuario.getEmail());
		ps.setInt(5, 1);
		ps.executeUpdate();
		
		usuario.setId(getLastIdInserted("user"));
		
		commitTransaction();
		close();
		return usuario;
	}
}
