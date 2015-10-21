package DAO;

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
	public Usuario cadastro(Usuario usuario) throws Exception{
		try{
			criarQuery("INSERT INTO user (`username`,	`passowrd`,	`enable`,	`email`,`role_id`) VALUES (?,?,?,?,?)");
			ps.setString(1, usuario.getNome());
			ps.setString(2, usuario.getSenha());
			ps.setInt(3, 1);
			ps.setString(4, usuario.getEmail());
			ps.setInt(5, 1);
			ResultSet res =  (ResultSet) ps.executeQuery();
			/**TODO
			 * Resgatar ID do usuário adicionad
			 * Criar uma pasta raiz
			 * Associar usuário a uma pasta raiz
			 */
			
			
			
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
