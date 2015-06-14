package DAO;

import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import modelos.Favorito;

public class FavoritoDAO extends BasicDAO {
	public Favorito buscar(int id) throws Exception{
		try{
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
				f.setImagem(res.getBytes("imagem"));
				f.setAcessoRapido(res.getBoolean("imagem"));
			}
			return f;
		}
		catch(Exception e){
			throw e;
		}
		finally{
			close();
		}
	}
	
	public Favorito adicionar(Favorito f) throws Exception{
		try{
			criarQuery("INSERT INTO favorito (`id_pasta`, `url`, `titulo`,	`descricao`,`data_criacao`,`numEstrela`, `imagem`, `acesso_rapido`) VALUES (?,?,?,?,?,?,?,?)");
			ps.setInt(1, f.getPai());
			ps.setString(2, f.getUrl());
			ps.setString(3, f.getTitulo());
			ps.setString(4, f.getDescricao());
			ps.setTimestamp(5, f.getDataCriacao());
			ps.setInt(6, f.getNumEstrela());
			ps.setBytes(7, f.getImagem());
			ps.setBoolean(8, f.isAcessoRapido());
			ps.executeUpdate();
			ps = (PreparedStatement) c.prepareStatement("SELECT LAST_INSERT_ID() id FROM favorito");
			ResultSet res =  (ResultSet) ps.executeQuery();	
			res =  (ResultSet) ps.executeQuery();	
			if (res.next()){
				f.setId(res.getInt("id"));
			}
			return f;
		}
		catch(Exception e){
			throw e;
		}
		finally{
			close();
		}
	}
	public Favorito alterar(Favorito f) throws Exception{
		try{
			criarQuery("UPDATE favorito SET `id_pasta`=?, `url`=?, `titulo`=?,	`descricao`=?,`data_criacao` = ?,`numEstrela` = ?, `imagem` = ?, `acesso_rapido` = ? WHERE id = ?");
			ps.setInt(1, f.getPai());
			ps.setString(2, f.getUrl());
			ps.setString(3, f.getTitulo());
			ps.setString(4, f.getDescricao());
			ps.setTimestamp(5, f.getDataCriacao());
			ps.setInt(6, f.getNumEstrela());
			ps.setBytes(7, f.getImagem());
			ps.setBoolean(8, f.isAcessoRapido());
			ps.setInt(9, f.getId());
			ps.executeUpdate();
			return f;
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
			criarQuery("UPDATE favorito SET `imagem` = ? WHERE id = ?");
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
	
	public byte[] getIMG() throws Exception{
		try{
			criarQuery("SELECT imagem FROM gerenciador_de_favoritos.favorito WHERE id = 1");
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
	
	public byte[] getImagem(int id) throws Exception{
		try{
			criarQuery("SELECT imagem FROM favorito WHERE id = ?");
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
	
	public void remover(int id)throws Exception{
		try{
			criarQuery("DELETE FROM favorito WHERE id=?");
			ps.setInt(1, id);
			ps.executeUpdate();
		}
		catch(Exception e){
			throw e;
		}
		finally{
			close();
		}
	}

	public void atualizarAcesso(int idFavorito) throws Exception {
		try{
			criarQuery("UPDATE favorito SET `data_acesso` = ?, `quant_acesso` = `quant_acesso` + 1 WHERE id = ?");
			ps.setTimestamp(1, new Timestamp(new java.util.Date().getTime()));
			ps.setInt(2, idFavorito);
			ps.executeUpdate();
		}
		catch(Exception e){
			throw e;
		}
		finally{
			close();
		}
	}
	
	public List<Favorito> recemAcessados() throws Exception{
		try{
			criarQuery("SELECT id, `url`, `titulo`, `imagem` FROM gerenciador_de_favoritos.favorito ORDER BY `data_acesso` DESC LIMIT 10");
			ResultSet res =  (ResultSet) ps.executeQuery();	
			res =  (ResultSet) ps.executeQuery();	
			List<Favorito> favoritos = new ArrayList<Favorito>();
			while (res.next()){
				Favorito f = new Favorito();
				f.setId(res.getInt("id"));
				f.setTitulo(res.getString("titulo"));
				f.setUrl(res.getString("url"));
				f.setImagem(res.getBytes("imagem"));
				favoritos.add(f);
			}
			return favoritos;
		}
		catch(Exception e){
			throw e;
		}
		finally{
			close();
		}
	}
	
	public List<Favorito> maisAcessados() throws Exception{
		try{
			criarQuery("SELECT id, `url`, `titulo`, `imagem` FROM gerenciador_de_favoritos.favorito ORDER BY `quant_acesso` DESC LIMIT 10");
			ResultSet res =  (ResultSet) ps.executeQuery();	
			res =  (ResultSet) ps.executeQuery();	
			List<Favorito> favoritos = new ArrayList<Favorito>();
			while (res.next()){
				Favorito f = new Favorito();
				f.setId(res.getInt("id"));
				f.setTitulo(res.getString("titulo"));
				f.setUrl(res.getString("url"));
				f.setImagem(res.getBytes("imagem"));
				favoritos.add(f);
			}
			return favoritos;
		}
		catch(Exception e){
			throw e;
		}
		finally{
			close();
		}
	}
	
	public List<Favorito> recemAdicionados() throws Exception{
		try{
			criarQuery("SELECT id, `url`, `titulo`, `imagem` FROM gerenciador_de_favoritos.favorito ORDER BY `data_criacao` DESC LIMIT 10");
			ResultSet res =  (ResultSet) ps.executeQuery();	
			res =  (ResultSet) ps.executeQuery();	
			List<Favorito> favoritos = new ArrayList<Favorito>();
			while (res.next()){
				Favorito f = new Favorito();
				f.setId(res.getInt("id"));
				f.setTitulo(res.getString("titulo"));
				f.setUrl(res.getString("url"));
				f.setImagem(res.getBytes("imagem"));
				favoritos.add(f);
			}
			return favoritos;
		}
		catch(Exception e){
			throw e;
		}
		finally{
			close();
		}
	}
	
}
