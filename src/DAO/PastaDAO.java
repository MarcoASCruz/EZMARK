package DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import modelos.Favorito;
import modelos.Pasta;

public class PastaDAO extends BasicDAO {
	public Pasta buscarArquivos(int idPasta) throws Exception{
		try{
			Pasta p = new Pasta();
			p.setPastas(buscarPastasFilhas(idPasta));
			p.setFavoritos(buscarFavoritosFilhos(idPasta));
			return p;
		}
		catch(Exception e){
			throw e;
		}
	}
	private List<Pasta> buscarPastasFilhas(int idPasta)throws Exception{
		try{
			criarQuery("SELECT * FROM PASTA WHERE id_pasta_pai = ? AND id != ?");
			ps.setInt(1, idPasta);
			ps.setInt(2, idPasta);
			ResultSet res =  (ResultSet) ps.executeQuery();	
			List<Pasta> pastas = new ArrayList<Pasta>();
			while (res.next()){
				Pasta p = new Pasta();
				p.setId(res.getInt("id"));
				p.setNome(res.getString("nome"));
				p.setNumEstrela(res.getInt("num_estrela"));
				p.setImagem(res.getBytes("imagem"));
				p.setPublica(res.getBoolean("publica"));
				//descrição
				//tag?
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
	
	private List<Favorito> buscarFavoritosFilhos(int idPasta)throws Exception{
		try{
			criarQuery("SELECT * FROM Favorito WHERE id_pasta = ?");
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
				f.setImagem(res.getBytes("imagem"));
				//tag?
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

	public Pasta adicionar(Pasta pasta)throws Exception{
		try{
			criarQuery("INSERT INTO pasta (`id_pasta_pai`,	`nome`,	`data_criacao`,	`num_estrela`,`publica`,`imagem`) VALUES (?,?,?,?,?,?)");
			ps.setInt(1, pasta.getPai());
			ps.setString(2, pasta.getNome());
			ps.setDate(3, pasta.getDataCriacao());
			ps.setInt(4, pasta.getNumEstrela());
			ps.setBoolean(5, pasta.isPublica());
			ps.setBytes(6, pasta.getImagem());
			ps.executeUpdate();
			ps = (PreparedStatement) c.prepareStatement("SELECT LAST_INSERT_ID() id FROM pasta");
			ResultSet res =  (ResultSet) ps.executeQuery();	
			res =  (ResultSet) ps.executeQuery();	
			if (res.next()){
				pasta.setId(res.getInt("id"));
			}
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
			criarQuery("UPDATE pasta SET id_pasta_pai=?, nome = ?,	num_estrela= ?, publica = ?, imagem = ? WHERE id = ?");
			ps.setInt(1, pasta.getPai());
			ps.setString(2, pasta.getNome());
			ps.setInt(3, pasta.getNumEstrela());
			ps.setBoolean(4, pasta.isPublica());
			ps.setBytes(5, pasta.getImagem());
			ps.setInt(6, pasta.getId());
			ps.executeUpdate();
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
			criarQuery("DELETE FROM pasta WHERE id=?");
			ps.setInt(1, idPasta);
			ps.executeUpdate();
		}
		catch(Exception e){
			throw e;
		}
		finally{
			close();
		}
	}
}
