package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import modelos.Favorito;

public class FavoritoDAO extends BasicDAO {
	public Favorito adicionar(Favorito f) throws Exception{
		try{
			criarQuery("INSERT INTO favorito (`id_pasta`, `url`, `titulo`,	`descricao`,`data_criacao`,`numEstrela`, `imagem`, `acesso_rapido`) VALUES (?,?,?,?,?,?,?,?)");
			ps.setInt(1, f.getPai());
			ps.setString(2, f.getUrl());
			ps.setString(3, f.getTitulo());
			ps.setString(4, f.getDescricao());
			ps.setDate(5, f.getDataCriacao());
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
			ps.setDate(5, f.getDataCriacao());
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
}
