package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.annotations.NamedQueries;

import modelos.Favorito;

public class AcessoRapidoDAO extends BasicDAO{
	public List<Favorito> buscarFavoritos(int idUsuario) throws Exception{
		criarQuery("SELECT favorito.id, favorito.imagem, titulo, url FROM favorito JOIN pasta ON (favorito.id_pasta = pasta.id) JOIN user_has_pasta AS up ON (up.pasta_id = pasta.id) WHERE acesso_rapido = ? AND up.user_id = ?");
		ps.setBoolean(1, true);
		ps.setInt(1, idUsuario);
		ResultSet res =  (ResultSet) ps.executeQuery();	
		List<Favorito> favoritos = new ArrayList<Favorito>();
		while (res.next()){
			Favorito f = new Favorito();
			f.setId(res.getInt("id"));
			f.setTitulo(res.getString("titulo"));
			f.setUrl(res.getString("url"));
			f.setImagem(res.getInt("id"));
			favoritos.add(f);
		}
		close();
		return favoritos;
	}
	public Favorito adicionar(Favorito f, int idUser) throws Exception{
		criarQuery("UPDATE favorito JOIN pasta ON (favorito.id_pasta = pasta.id) JOIN user_has_pasta AS up ON (up.pasta_id = pasta.id) SET acesso_rapido = TRUE WHERE favorito.id = ? AND up.user_id = ?");
		ps.setInt(1, f.getId());
		ps.setInt(2, idUser);
		ps.executeUpdate();
		f.setAcessoRapido(true);
		close();
		return f;
	}
	
	public void remover(int idFavorito, int idUser) throws Exception{
		criarQuery("UPDATE favorito JOIN pasta ON (favorito.id_pasta = pasta.id) JOIN user_has_pasta AS up ON (up.pasta_id = pasta.id) SET acesso_rapido = FALSE WHERE favorito.id = ? AND up.user_id = ?");
		ps.setInt(1, idFavorito);
		ps.setInt(2, idUser);
		ps.executeUpdate();
		close();
	}
}
