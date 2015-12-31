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

public class PastaDAO extends BasicDAO {
	public Pasta buscar (int idPasta) throws SQLException{
		criarQuery("SELECT id, publica FROM pasta WHERE pasta.id = ?");
		ps.setInt(1, idPasta);
		ResultSet res =  (ResultSet) ps.executeQuery();	
		res =  (ResultSet) ps.executeQuery();
		res.next();
		Pasta pasta = new Pasta();
		pasta.setPublica(res.getBoolean("publica"));
		return pasta;
	}
	public Pasta buscarPastaCompleta (int idPasta) throws Exception{
		openConection();
		setQuery("SELECT id, nome, num_estrela, descricao FROM pasta WHERE id = ?");
		ps.setInt(1, idPasta);
		ResultSet res =  (ResultSet) ps.executeQuery();
		res.next();
		Pasta pastaRaiz = new Pasta();
		pastaRaiz.setId(res.getInt("id"));
		pastaRaiz.setNome(res.getString("nome"));
		pastaRaiz.setNumEstrela(res.getInt("num_estrela"));
		pastaRaiz.setImagem(res.getInt("id"));
		pastaRaiz.setDescricao(res.getString("descricao"));
		close();
		
		pastaRaiz.setPastas(buscarTodosOsFilhos(idPasta));
		pastaRaiz.setFavoritos(new FavoritoDAO().buscarFavoritosFilhos(idPasta));
		
		return pastaRaiz;
	}
	
	private List<Pasta> buscarTodosOsFilhos (int idPasta) throws Exception{
		List<Pasta> pastas = buscarPastasFilhas(idPasta, 1);
		for (Pasta pasta : pastas) {
			pasta.setPastas(buscarTodosOsFilhos(pasta.getId()));
			pasta.setFavoritos(new FavoritoDAO().buscarFavoritosFilhos(pasta.getId()));
		}
		return pastas;
	}
	
	
	
	public List<Hierarquia> buscarHierarquia(int idUsuario) throws Exception{
		criarQuery("SELECT id, id_pasta_pai, nome FROM pasta JOIN user_has_pasta AS up ON (pasta.id = up.pasta_id) WHERE up.user_id = ?");
		ps.setInt(1, idUsuario);
		ResultSet res =  (ResultSet) ps.executeQuery();	
		List<Hierarquia> hierarquia = new ArrayList<Hierarquia>();
		while (res.next()){
			Hierarquia h = new Hierarquia();
			h.setId(res.getString("id"));
			h.setParent(res.getString("id_pasta_pai"));
			h.setText(res.getString("nome"));
			hierarquia.add(h);
		}
		return hierarquia;
	}

	public List<Pasta> buscarPastasFilhas(int idPasta, int idUsuario)throws Exception{
		openConection();
		setQuery("SELECT id, nome, num_estrela, publica, descricao, id_pasta_pai FROM pasta JOIN user_has_pasta AS up ON (pasta.id = up.pasta_id) WHERE id_pasta_pai = ? AND id != ? AND up.user_id = ?");
		ps.setInt(1, idPasta);
		ps.setInt(2, idPasta);
		ps.setInt(3, idUsuario);
		ResultSet res =  (ResultSet) ps.executeQuery();	
		List<Pasta> pastas = new ArrayList<Pasta>();
		while (res.next()){
			Pasta p = new Pasta();
			p.setId(res.getInt("id"));
			p.setNome(res.getString("nome"));
			p.setNumEstrela(res.getInt("num_estrela"));
			p.setImagem(res.getInt("id"));
			p.setPublica(res.getBoolean("publica"));
			p.setDescricao(res.getString("descricao"));
			p.setPai(res.getInt("id_pasta_pai"));
			p.setTags(buscarTags(p.getId()));
			pastas.add(p);
		}
		close();
		return pastas;
	}
	
	public List<String> buscarTags(int idPasta) throws Exception{
		setQuery("SELECT tag.nome AS `nome` FROM pasta LEFT JOIN pasta_tag ON (pasta.id = pasta_tag.id_pasta) 	LEFT JOIN tag ON (tag.nome = pasta_tag.tag_nome) WHERE pasta.id = ?");
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
	

	public Pasta adicionar(Pasta pasta, int idUsuario)throws Exception{
		openConection();
		beginTransaction();
		criarQuery("INSERT INTO pasta (`id_pasta_pai`,	`nome`,	`data_criacao`,	`num_estrela`,`publica`, descricao) VALUES (?,?,?,?,?,?)");
		ps.setInt(1, pasta.getPai());
		ps.setString(2, pasta.getNome());
		ps.setTimestamp(3, pasta.getDataCriacao());
		ps.setInt(4, pasta.getNumEstrela());
		ps.setBoolean(5, pasta.isPublica());
		ps.setString(6, pasta.getDescricao());
		ps.executeUpdate();
		ps = (PreparedStatement) c.prepareStatement("SELECT LAST_INSERT_ID() id FROM pasta");
		ResultSet res =  (ResultSet) ps.executeQuery();	
		res =  (ResultSet) ps.executeQuery();	
		if (res.next()){
			pasta.setId(res.getInt("id"));
			for (String tag : pasta.getTags()) {
				adicionarTag(tag, pasta.getId());
			}
		}
		vincularUsuario(idUsuario, pasta.getId());
		commitTransaction();
		close();
		return pasta;
	}
	
	public void vincularUsuario(int idUsuario, int idPasta) throws Exception{
		criarQuery("INSERT INTO user_has_pasta (user_id,pasta_id) VALUES (?,?)");
		ps.setInt(1, idUsuario);
		ps.setInt(2, idPasta);
		ps.executeUpdate();
		if (c.getAutoCommit()){
			close();
		}
	}
	
	public Pasta alterar(Pasta pasta)throws Exception{
		openConection();
		beginTransaction();
		int idPasta = pasta.getId();
		removerTodasTags(idPasta);
		for (String tag : pasta.getTags()) {
			adicionarTag(tag, idPasta);
		}
		setQuery("UPDATE pasta SET nome = ?, descricao = ?, num_estrela = ? WHERE id = ?");
		ps.setString(1, pasta.getNome());
		ps.setString(2, pasta.getDescricao());
		ps.setInt(3, pasta.getNumEstrela());
		ps.setInt(4, idPasta);
		ps.executeUpdate();
		commitTransaction();
		close();
		return pasta;
	}
	
	public void adicionarTag(String tag, int idPasta) throws Exception{
		TagDAO tagDao = new TagDAO(c);
		String tagIdentificacao = tagDao.buscar(tag);
		if(tagIdentificacao == null){
			tagDao.adicionar(tag);
		}
		criarQuery("INSERT INTO pasta_tag (id_pasta, tag_nome) VALUES (?, ?)");
		ps.setInt(1, idPasta);
		ps.setString(2, tag);
		ps.executeUpdate();
		if (c.getAutoCommit()){
			close();
		}
	}

	public void remover(int idPasta, int idUsuario)throws Exception{
		List<Pasta> pastasFilhas = buscarPastasFilhas(idPasta, 1);
		for (Pasta pastaFilha : pastasFilhas) {
			remover(pastaFilha.getId(), idUsuario);
		}
		openConection();
		beginTransaction();
		
		setQuery("DELETE pasta_tag FROM pasta_tag JOIN user_has_pasta AS up ON (pasta_tag.id_pasta = up.pasta_id) WHERE id_pasta=? AND up.user_id = ?");
		ps.setInt(1, idPasta);
		ps.setInt(2, idUsuario);
		ps.executeUpdate();
		
		setQuery("DELETE pasta, up FROM pasta JOIN user_has_pasta AS up ON (pasta.id = up.pasta_id) WHERE id=? and up.user_id =?");
		ps.setInt(1, idPasta);
		ps.setInt(2, idUsuario);
		ps.executeUpdate();
		
		commitTransaction();
		close();
	}
	
	public void atualizarImagem(byte[] img, int id) throws Exception{
		criarQuery("UPDATE pasta SET `imagem` = ? WHERE id = ?");
		ps.setBytes(1, img);
		ps.setInt(2, id);
		ps.executeUpdate();
		close();
	}

	public byte[] getImagem(int id) throws Exception{
		criarQuery("SELECT imagem FROM pasta WHERE id = ?");
		ps.setInt(1, id);
		ResultSet res =  (ResultSet) ps.executeQuery();	
		res =  (ResultSet) ps.executeQuery();
		res.next();
		byte[] imagem = res.getBytes("imagem");
		close();
		return imagem;
	}
	
	

	public void removerTag(String tag, int idPasta) throws Exception{
		criarQuery("DELETE FROM pasta_tag WHERE id_pasta = ? AND tag_nome = ?");
		ps.setInt(1, idPasta);
		ps.setString(2, tag);
		ps.executeUpdate();
		close();
	}

	public void removerTodasTags(int idPasta) throws Exception{
		setQuery("DELETE FROM pasta_tag WHERE id_pasta = ?");
		ps.setInt(1, idPasta);
		ps.executeUpdate();
		if (c.getAutoCommit()){
			close();
		}
	}

	public List<Pasta> pesquisar(String pasta) throws Exception{
		openConection();
		setQuery("SELECT id, id_pasta_pai, pasta.nome, data_criacao, num_estrela, publica, descricao FROM pasta LEFT JOIN pasta_tag ON (pasta.id = pasta_tag.id_pasta) LEFT JOIN tag ON (pasta_tag.tag_nome = tag.nome) WHERE pasta.nome LIKE CONCAT(?) OR tag.nome LIKE CONCAT(?)");
		String pastaComLike = "%" + pasta + "%";
		ps.setString(1, pastaComLike);
		ps.setString(2, pastaComLike);
		beginTransaction();
		ResultSet res =  (ResultSet) ps.executeQuery();	
		List<Pasta> pastas = new ArrayList<Pasta>();
		while (res.next()){
			Pasta p = new Pasta();
			p.setId(res.getInt("id"));
			p.buildImagemUrl();
			p.setPai(res.getInt("id_pasta_pai"));
			p.setNome(res.getString("nome"));
			p.setNumEstrela(res.getInt("num_estrela"));
			p.setPublica(res.getBoolean("publica"));
			p.setDescricao(res.getString("descricao"));
			p.setTags(buscarTags(p.getId()));
			pastas.add(p);
		}
		commitTransaction();
		close();
		return pastas;
	}
	
	public void adicionarHome(int idUsuario)throws Exception{
		openConection();
		CallableStatement cs =c.prepareCall("{CALL createFileHome(?)}");
		cs.setInt(1, idUsuario);
		cs.execute();
		if (cs != null) { 
			cs.close();
		}
	}
	
	public Pasta buscarHome(int idUsuario) throws Exception{
		openConection();
		setQuery("SELECT id FROM pasta join user_has_pasta AS up ON (up.pasta_id = pasta.id) WHERE up.user_id = ? AND pasta.id_pasta_pai = pasta.id;");
		ps.setInt(1, idUsuario);
		ResultSet res =  (ResultSet) ps.executeQuery();
		Pasta pasta = new Pasta();
		if (res.next()){
			pasta.setId(res.getInt("id"));
		}
		close();
		return pasta;
	}
	
	public void compartilhar(int idPasta) throws Exception {
		compartilharPasta(idPasta);
		compartilharFilhos(idPasta);
	}
	
	private void compartilharFilhos(int idPasta) throws Exception {
		List<Pasta> pastas = buscarPastasFilhas(idPasta, 1);
		for (Pasta pasta : pastas) {
			compartilharFilhos(pasta.getId());
			compartilharPasta(pasta.getId());
		}
	}
	
	private void compartilharPasta(int idPasta) throws Exception {
		criarQuery("UPDATE pasta SET `publica` = ? WHERE id = ?");
		ps.setBoolean(1, true);
		ps.setInt(2, idPasta);
		ps.executeUpdate();
		close();
	}
}
