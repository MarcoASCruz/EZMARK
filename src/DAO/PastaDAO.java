package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;







import modelos.Hierarquia;
import modelos.Pasta;
import modelos.Tag;

public class PastaDAO extends BasicDAO {
	public List<Hierarquia> buscarHierarquia() throws Exception{
		try{
			criarQuery("SELECT id, id_pasta_pai, nome FROM pasta");
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
		catch(Exception e){
			throw e;
		}
	}

	public List<Pasta> buscarPastasFilhas(int idPasta, int idUsuario)throws Exception{
		try{
			openConection();
			setQuery("SELECT id, nome, num_estrela, publica, descricao FROM pasta JOIN user_has_pasta AS up ON (pasta.id = up.pasta_id) WHERE id_pasta_pai = ? AND id != ? AND up.user_id = ?");
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
				p.setTags(buscarTags(p.getId()));
				pastas.add(p);
			}
			return pastas;
		}
		catch(Exception e){
			throw e;
		}
		finally{
			close();
		}
	}
	
	public List<String> buscarTags(int idPasta) throws Exception{
		try{
			setQuery("SELECT tag.nome AS `nome` FROM pasta LEFT JOIN pasta_tag ON (pasta.id = pasta_tag.id_pasta) 	LEFT JOIN tag ON (tag.nome = pasta_tag.tag_nome) WHERE pasta.id = ?");
			ps.setInt(1, idPasta);
			ResultSet res =  (ResultSet) ps.executeQuery();	
			res =  (ResultSet) ps.executeQuery();	
			
			List<String> tags = new ArrayList<String>();
			//if(res.next) não funciona... verificar o motivo disso...
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
		catch(Exception e){
			throw e;
		}
	}
	

	public Pasta adicionar(Pasta pasta)throws Exception{
		try{
			openConection();
			beginTransaction();
			criarQuery("INSERT INTO pasta (`id_pasta_pai`,	`nome`,	`data_criacao`,	`num_estrela`,`publica`) VALUES (?,?,?,?,?)");
			ps.setInt(1, pasta.getPai());
			ps.setString(2, pasta.getNome());
			ps.setTimestamp(3, pasta.getDataCriacao());
			ps.setInt(4, pasta.getNumEstrela());
			ps.setBoolean(5, pasta.isPublica());
			//ps.setBytes(6, pasta.getImagem());
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
			commitTransaction();
			return pasta;
		}
		catch(Exception e){
			throw e;
		}
		finally{
			close();
		}
	}
	
	public Pasta alterar(Pasta pasta)throws Exception{
		try{
			openConection();
			beginTransaction();
			int idPasta = pasta.getId();
			removerTodasTags(idPasta);
			for (String tag : pasta.getTags()) {
				adicionarTag(tag, idPasta);
			}
			setQuery("UPDATE pasta SET nome = ?, descricao = ? WHERE id = ?");
			ps.setString(1, pasta.getNome());
			ps.setString(2, pasta.getDescricao());
			ps.setInt(3, idPasta);
			ps.executeUpdate();
			commitTransaction();
			return pasta;
		}
		catch(Exception e){
			throw e;
		}
		finally{
			close();
		}
	}

	public void remover(int idPasta)throws Exception{
		try{
			List<Pasta> pastasFilhas = buscarPastasFilhas(idPasta, 1);
			for (Pasta pastaFilha : pastasFilhas) {
				remover(pastaFilha.getId());
			}
			openConection();
			beginTransaction();
			
			setQuery("DELETE pasta_tag FROM pasta_tag JOIN user_has_pasta AS up ON (pasta_tag.id_pasta = up.pasta_id) WHERE id_pasta=? AND up.user_id = ?");
			ps.setInt(1, idPasta);
			ps.setInt(2, 1);
			ps.executeUpdate();
			
			setQuery("DELETE pasta, up FROM pasta JOIN user_has_pasta AS up ON (pasta.id = up.pasta_id) WHERE id=? and up.user_id =?");
			ps.setInt(1, idPasta);
			ps.setInt(2, 1);
			ps.executeUpdate();
			
			commitTransaction();
		}
		catch(Exception e){
			throw e;
		}
		finally{
			close();
		}
	}
	
	public void atualizarImagem(byte[] img, int id) throws Exception{
		try{
			criarQuery("UPDATE pasta SET `imagem` = ? WHERE id = ?");
			ps.setBytes(1, img);
			ps.setInt(2, id);
			ps.executeUpdate();
		}
		catch(Exception e){
			throw e;
		}
		finally{
			close();
		}
	}

	public byte[] getImagem(int id) throws Exception{
		try{
			criarQuery("SELECT imagem FROM pasta WHERE id = ?");
			ps.setInt(1, id);
			ResultSet res =  (ResultSet) ps.executeQuery();	
			res =  (ResultSet) ps.executeQuery();
			res.next();
			return res.getBytes("imagem");
		}
		catch(Exception e){
			throw e;
		}
		finally{
			close();
		}
	}
	
	public void adicionarTag(String tag, int idPasta) throws Exception{
		try{
			TagDAO tagDao = new TagDAO(c);
			String tagIdentificacao = tagDao.buscar(tag);
			if(tagIdentificacao == null){
				tagDao.adicionar(tag);
			}
			criarQuery("INSERT INTO pasta_tag (id_pasta, tag_nome) VALUES (?, ?)");
			ps.setInt(1, idPasta);
			ps.setString(2, tag);
			ps.executeUpdate();
		}
		catch(Exception e){
			throw e;
		}
		finally{
			if (c.getAutoCommit()){
				close();
			}
		}
	}

	public void removerTag(String tag, int idPasta) throws Exception{
		try{
			criarQuery("DELETE FROM pasta_tag WHERE id_pasta = ? AND tag_nome = ?");
			ps.setInt(1, idPasta);
			ps.setString(2, tag);
			ps.executeUpdate();
		}
		catch(Exception e){
			throw e;
		}
		finally{
			close();
		}
	}

	public void removerTodasTags(int idPasta) throws Exception{
		try{
			setQuery("DELETE FROM pasta_tag WHERE id_pasta = ?");
			ps.setInt(1, idPasta);
			ps.executeUpdate();
		}
		catch(Exception e){
			throw e;
		}
		finally{
			if (c.getAutoCommit()){
				close();
			}
		}
	}

	public List<Pasta> pesquisar(String pasta) throws Exception{
		try{
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
			return pastas;
		}
		catch(Exception e){
			throw e;
		}
		finally{
			close();
		}
	}
}
