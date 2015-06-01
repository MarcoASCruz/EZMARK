package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.annotations.NamedQueries;

import modelos.Favorito;

public class AcessoRapido {
	private EntityManager manager;
	private EntityManagerFactory factory;
	public AcessoRapido() {
		factory = Persistence.createEntityManagerFactory("gerenciadorDeFavoritos");
		manager = factory.createEntityManager();
	}
	
	public void add(Favorito favorito) throws Exception{
		try{
			manager.getTransaction().begin();
			manager.persist(favorito);
			manager.getTransaction().commit();
		}
		catch(Exception e){
			throw e;
		}
		finally{
			close();
		}	
	}
	
	public List<Favorito> findAll() throws Exception{
		try{
			String jpql = "SELECT f FROM Favorito f WHERE f.idPasta IS NULL";
			Query query = manager.createQuery(jpql);
			List<Favorito> favoritos = query.getResultList();
			return favoritos;
		}
		catch(Exception e){
			throw e;
		}
		finally{
			close();
		}	
	}
	
	public List<Favorito> find(int id) throws Exception{
		try{
			String jpql = "SELECT f FROM Favorito f WHERE f.id = :id";
			Query query = manager.createQuery(jpql);
			query.setParameter("id", id);
			List<Favorito> favoritos = query.getResultList();
			return favoritos;
		}
		catch(Exception e){
			throw e;
		}
		finally{
			close();
		}	
	}
	
	public void close()  throws Exception{
		manager.close();
		factory.close();
	}
}
