package DAO;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import modelos.Favorito;
import modelos.Pasta;
import modelos.Tag;

public class FavoritoDAO extends BasicDAO {
	public Favorito buscar(int id) throws Exception{
		criarQuery("SELECT id, `id_pasta`, `url`, `titulo`,	`descricao`, data_acesso,`data_criacao`,`numEstrela`, `imagem`, `acesso_rapido` FROM favorito WHERE id = ?");
		ps.setInt(1, id);
		ResultSet res =  (ResultSet) ps.executeQuery();	
		res =  (ResultSet) ps.executeQuery();	
		Favorito f = new Favorito();
		if (res.next()){
			f.setId(res.getInt("id"));
			f.setUrl(res.getString("url"));
			f.setTitulo(res.getString("titulo"));
			f.setDescricao(res.getString("descricao"));
			f.setDataAcesso(res.getTimestamp("data_acesso"));
			f.setDataCriacao(res.getTimestamp("data_criacao"));
			f.setNumEstrela(res.getInt("numEstrela"));
			f.setImagem(res.getInt("id"));
			f.setAcessoRapido(res.getBoolean("imagem"));
			f.setTags(buscarTags(f.getId()));
		}
		close();
		return f;
	}
	
	public List<Favorito> buscarFavoritosFilhos(int idPasta)throws Exception{
		criarQuery("SELECT id, titulo, url, descricao, numEstrela FROM favorito WHERE id_pasta = ?");
		ps.setInt(1, idPasta);
		ResultSet res =  (ResultSet) ps.executeQuery();	
		List<Favorito> favoritos = new ArrayList<Favorito>();
		while (res.next()){
			Favorito f = new Favorito();
			f.setId(res.getInt("id"));
			f.setTitulo(res.getString("titulo"));
			f.setUrl(res.getString("url"));
			f.setDescricao(res.getString("descricao"));
			f.setNumEstrela(res.getInt("numEstrela"));
			f.setImagem(res.getInt("id"));
			f.setTags(buscarTags(f.getId()));
			favoritos.add(f);
		}
		close();
		return favoritos;
	}
	
	public List<String> buscarTags(int idFavorito) throws Exception{
		setQuery("SELECT tag.nome AS `nome` FROM favorito LEFT JOIN favorito_tag ON (favorito.id = favorito_tag.id_favorito) 	LEFT JOIN tag ON (tag.nome = favorito_tag.tag_nome) WHERE favorito.id = ?");
		ps.setInt(1, idFavorito);
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
	
	public Favorito adicionar(Favorito f) throws Exception{
		openConection();
		beginTransaction();
		criarQuery("INSERT INTO favorito (`id_pasta`, `url`, `titulo`,	`descricao`,`data_criacao`,`numEstrela`, `acesso_rapido`) VALUES (?,?,?,?,?,?,?)");
		ps.setInt(1, f.getPai());
		ps.setString(2, f.getUrl());
		ps.setString(3, f.getTitulo());
		ps.setString(4, f.getDescricao());
		ps.setTimestamp(5, f.getDataCriacao());
		ps.setInt(6, f.getNumEstrela());
		ps.setBoolean(7, f.isAcessoRapido());
		ps.executeUpdate();
		ps = (PreparedStatement) c.prepareStatement("SELECT LAST_INSERT_ID() id FROM favorito");
		ResultSet res =  (ResultSet) ps.executeQuery();	
		res =  (ResultSet) ps.executeQuery();	
		if (res.next()){
			f.setId(res.getInt("id"));
			for (String tag : f.getTags()) {
				adicionarTag(tag, f.getId());
			}
		}
		commitTransaction();
		close();
		return f;
	}
	
	public void adicionarTag(String tag, int idFavorito) throws Exception{
		TagDAO tagDao = new TagDAO(c);
		String tagIdentificacao = tagDao.buscar(tag);
		if(tagIdentificacao == null){
			tagDao.adicionar(tag);
		}
		criarQuery("INSERT INTO favorito_tag (id_favorito, tag_nome) VALUES (?, ?)");
		ps.setInt(1, idFavorito);
		ps.setString(2, tag);
		ps.executeUpdate();
		if (c.getAutoCommit()){
			close();
		}
	}
	
	public Favorito alterar(Favorito f) throws Exception{
		openConection();
		beginTransaction();
		int idFavorito = f.getId();
		removerTodasTags(idFavorito);
		for (String tag : f.getTags()) {
			adicionarTag(tag, idFavorito);
		}
		setQuery("UPDATE favorito SET `url`=?, `titulo`=?,	`descricao`=?, numEstrela=? WHERE id = ?");
		ps.setString(1, f.getUrl());
		ps.setString(2, f.getTitulo());
		ps.setString(3, f.getDescricao());
		ps.setInt(4, f.getNumEstrela());
		ps.setInt(5, f.getId());
		ps.executeUpdate();
		commitTransaction();
		close();
		return f;
	}
	
	public void atualizarImagem(byte[] img, int id) throws Exception{
		criarQuery("UPDATE favorito SET `imagem` = ? WHERE id = ?");
		ps.setBytes(1, img);
		ps.setInt(2, id);
		ps.executeUpdate();
		close();
	}
	
	public byte[] getIMG() throws Exception{
		criarQuery("SELECT imagem FROM favorito WHERE id = 1");
		ResultSet res =  (ResultSet) ps.executeQuery();	
		res =  (ResultSet) ps.executeQuery();
		res.next();
		byte[] imagem = res.getBytes("imagem"); 
		close();
		return imagem;
	}
	
	public byte[] getImagem(int id) throws Exception{
		criarQuery("SELECT imagem FROM favorito WHERE id = ?");
		ps.setInt(1, id);
		ResultSet res =  (ResultSet) ps.executeQuery();	
		res =  (ResultSet) ps.executeQuery();
		res.next();
		byte[] imagem = res.getBytes("imagem"); 
		close();
		return imagem;
	}
	
	public void remover(int id)throws Exception{
		openConection();
		beginTransaction();
		
		setQuery("DELETE FROM favorito_tag WHERE id_favorito=?");
		ps.setInt(1, id);
		ps.executeUpdate();
		
		criarQuery("DELETE FROM favorito WHERE id=?");
		ps.setInt(1, id);
		ps.executeUpdate();
		
		commitTransaction();
		close();
	}

	public void atualizarAcesso(int idFavorito) throws Exception {
		criarQuery("UPDATE favorito SET `data_acesso` = ?, `quant_acesso` = `quant_acesso` + 1 WHERE id = ?");
		ps.setTimestamp(1, new Timestamp(new java.util.Date().getTime()));
		ps.setInt(2, idFavorito);
		ps.executeUpdate();
		close();
	}
	
	public List<Favorito> recemAcessados(int idUsuario) throws Exception{
		criarQuery("SELECT favorito.id as idFav, url, titulo FROM favorito JOIN pasta ON (favorito.id_pasta = pasta.id) JOIN user_has_pasta AS up ON (up.pasta_id = pasta.id) WHERE up.user_id = ? ORDER BY data_acesso DESC LIMIT 10");
		ps.setInt(1, idUsuario);
		ResultSet res =  (ResultSet) ps.executeQuery();	
		res =  (ResultSet) ps.executeQuery();	
		List<Favorito> favoritos = new ArrayList<Favorito>();
		while (res.next()){
			Favorito f = new Favorito();
			f.setId(res.getInt("idFav"));
			f.setTitulo(res.getString("titulo"));
			f.setUrl(res.getString("url"));
			f.setImagem(res.getInt("idFav"));
			favoritos.add(f);
		}
		close();
		return favoritos;
	}
	
	public List<Favorito> maisAcessados(int idUsuario) throws Exception{
		criarQuery("SELECT favorito.id as idFav, url, titulo FROM favorito JOIN pasta ON (favorito.id_pasta = pasta.id) JOIN user_has_pasta AS up ON (up.pasta_id = pasta.id) WHERE up.user_id = ? ORDER BY quant_acesso DESC LIMIT 10");
		ps.setInt(1, idUsuario);
		ResultSet res =  (ResultSet) ps.executeQuery();	
		res =  (ResultSet) ps.executeQuery();	
		List<Favorito> favoritos = new ArrayList<Favorito>();
		while (res.next()){
			Favorito f = new Favorito();
			f.setId(res.getInt("idFav"));
			f.setTitulo(res.getString("titulo"));
			f.setUrl(res.getString("url"));
			f.setImagem(res.getInt("idFav"));
			favoritos.add(f);
		}
		close();
		return favoritos;
	}
	
	public List<Favorito> recemAdicionados(int idUsuario) throws Exception{
		criarQuery("SELECT favorito.id as idFav, url, titulo FROM favorito JOIN pasta ON (favorito.id_pasta = pasta.id) JOIN user_has_pasta AS up ON (up.pasta_id = pasta.id) WHERE up.user_id = ? ORDER BY favorito.data_criacao DESC LIMIT 10");
		ps.setInt(1, idUsuario);
		ResultSet res =  (ResultSet) ps.executeQuery();	
		res =  (ResultSet) ps.executeQuery();	
		List<Favorito> favoritos = new ArrayList<Favorito>();
		while (res.next()){
			Favorito f = new Favorito();
			f.setId(res.getInt("idFav"));
			f.setTitulo(res.getString("titulo"));
			f.setUrl(res.getString("url"));
			f.setImagem(res.getInt("idFav"));
			favoritos.add(f);
		}
		close();
		return favoritos;
	}
	
	
	public void removerTag(String tag, int idFavorito) throws Exception{
		criarQuery("DELETE FROM favorito_tag WHERE id_favorito = ? AND tag_nome = ?");
		ps.setInt(1, idFavorito);
		ps.setString(2, tag);
		ps.executeUpdate();
		close();
	}
	public void removerTodasTags(int idFavorito) throws Exception{
		setQuery("DELETE FROM favorito_tag WHERE id_favorito = ?");
		ps.setInt(1, idFavorito);
		ps.executeUpdate();
		if (c.getAutoCommit()){
			close();
		}
	}
	
	public List<Favorito> pesquisar(String favorito) throws Exception{
		openConection();
		setQuery("SELECT id, titulo, url, descricao, numEstrela FROM favorito LEFT JOIN favorito_tag ON (favorito.id = favorito_tag.id_favorito) LEFT JOIN tag ON (favorito_tag.tag_nome = tag.nome) WHERE titulo LIKE CONCAT(?) OR url LIKE CONCAT(?) OR tag.nome LIKE CONCAT(?)");
		String textoComLike = "%" + favorito + "%"; 
		ps.setString(1, textoComLike);
		ps.setString(2, textoComLike);
		ps.setString(3, textoComLike);
		beginTransaction();
		ResultSet res =  (ResultSet) ps.executeQuery();	
		List<Favorito> favoritos = new ArrayList<Favorito>();
		while (res.next()){
			Favorito f = new Favorito();
			f.setId(res.getInt("id"));
			f.addImagemUrl();
			f.setTitulo(res.getString("titulo"));
			f.setUrl(res.getString("url"));
			f.setDescricao(res.getString("descricao"));
			f.setNumEstrela(res.getInt("numEstrela"));
			f.setTags(buscarTags(f.getId()));
			favoritos.add(f);
		}
		commitTransaction();
		close();
		return favoritos;
	}

	public void mover(int idPastaDestino, int idFavorito) throws Exception {
		openConection();
		setQuery("UPDATE favorito SET id_pasta = ? WHERE id = ?");
		ps.setInt(1, idPastaDestino);
		ps.setInt(2, idFavorito);
		ps.executeUpdate();
		close();
	}
	
}
