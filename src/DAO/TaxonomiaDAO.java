package DAO;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;













import modelos.Hierarquia;
import modelos.Pasta;
import modelos.Tag;

public class TaxonomiaDAO extends BasicDAO {
	public Pasta buscar (int idPasta) throws Exception{
		openConection();
		setQuery("SELECT id, nome, num_estrela, descricao FROM taxonomia WHERE id = ?");
		ps.setInt(1, idPasta);
		ResultSet res =  (ResultSet) ps.executeQuery();
		res.next();
		Pasta pasta = new Pasta();
		pasta.setId(res.getInt("id"));
		pasta.setNome(res.getString("nome"));
		pasta.setNumEstrela(res.getInt("num_estrela"));
		//pasta.setImagem(res.getInt("id"));
		pasta.setDescricao(res.getString("descricao"));
		pasta.setTags(buscarTags(pasta.getId()));
		close();
		return pasta;
	}
	public List<Pasta> buscarTodas () throws Exception{
		int idHome = buscarHome().getId(); 
		openConection();
		setQuery("SELECT id, id_pasta_pai, nome, num_estrela, descricao FROM taxonomia WHERE id_pasta_pai = ?");
		ps.setInt(1, idHome);
		ResultSet res =  (ResultSet) ps.executeQuery();	
		List<Pasta> pastas = new ArrayList<Pasta>();
		while (res.next()){
			Pasta p = new Pasta();
			p.setId(res.getInt("id"));
			p.setNome(res.getString("nome"));
			p.setNumEstrela(res.getInt("num_estrela"));
			//p.setImagem(res.getInt("id"));
			p.setDescricao(res.getString("descricao"));
			p.setPai(res.getInt("id_pasta_pai"));
			p.setTags(buscarTags(p.getId()));
			pastas.add(p);
		}
		close();
		return pastas;
	}
	public Pasta buscarHome() throws Exception{
		openConection();
		setQuery("SELECT id FROM taxonomia WHERE taxonomia.id_pasta_pai = taxonomia.id;");
		ResultSet res =  (ResultSet) ps.executeQuery();
		Pasta pasta = new Pasta();
		if (res.next()){
			pasta.setId(res.getInt("id"));
		}
		close();
		return pasta;
	}
	public List<String> buscarTags(int idPasta) throws Exception{
		setQuery("SELECT tag.nome AS `nome` FROM taxonomia LEFT JOIN taxonomia_tag ON (taxonomia.id = taxonomia_tag.id_taxonomia) 	LEFT JOIN tag ON (tag.nome = taxonomia_tag.tag_nome) WHERE taxonomia.id = ?");
		ps.setInt(1, idPasta);
		ResultSet res =  (ResultSet) ps.executeQuery();	
		res =  (ResultSet) ps.executeQuery();
		List<String> tags = new ArrayList<String>();
		res.next();
		if(res.getString("nome") != null){
			do {
				tags.add(res.getString("nome"));
			} while (res.next());
		}
		else {
			tags = null;
		}
		return tags;
	}
	
}
