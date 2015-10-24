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
	public List<Favorito> buscarFavoritos() throws Exception{
		criarQuery("SELECT id, imagem, titulo, url FROM favorito WHERE acesso_rapido = ?");
		ps.setBoolean(1, true);
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
	public Favorito adicionar(Favorito f) throws Exception{
		criarQuery("UPDATE favorito SET acesso_rapido = true WHERE id = ?");
		ps.setInt(1, f.getId());
		ps.executeUpdate();
		f.setAcessoRapido(true);
		close();
		return f;
	}
	
	public void remover(int idFavorito) throws Exception{
		criarQuery("UPDATE favorito SET acesso_rapido = false WHERE id = ?");
		ps.setInt(1, idFavorito);
		ps.executeUpdate();
		close();
	}
}
