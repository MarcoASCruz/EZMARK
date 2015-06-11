package servicos;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import modelos.AppResponse;
import modelos.Favorito;

import org.json.JSONObject;

import com.sun.jersey.json.impl.JSONUnmarshallerImpl;

import flexjson.JSONDeserializer;

@Path("/acessoRapido")
public class AcessoRapido {
	@GET
	@Produces("application/json")
	public Response findAll() {
		AppResponse response = new AppResponse();
		try{
			DAO.AcessoRapidoDAO acessoRapidoDAO = new DAO.AcessoRapidoDAO();
			List<Favorito> favoritos = acessoRapidoDAO.buscarFavoritos();
			response.setSuccess(true);
			response.setContent(favoritos);
		}
		catch(Exception e){
			response.addException(e);
		}
		return response.buildResponse();
	}
	
	@POST
	@Produces("application/json")
	public Response adicionarItem(@FormParam("id") int  idFavorito){
		AppResponse response = new AppResponse();
		try{
			Favorito favorito = new Favorito();
			favorito.setId(idFavorito);
			DAO.AcessoRapidoDAO acessoRapidoDAO = new DAO.AcessoRapidoDAO();
			acessoRapidoDAO.adicionar(favorito);
			response.setSuccess(true);
		}
		catch(Exception e){
			response.addException(e);
		}
		return response.buildResponse();
	}
	
	@DELETE
	@Produces("application/json")
	public Response remover(@FormParam("id") int  idFavorito){
		AppResponse response = new AppResponse();
		try{
			DAO.AcessoRapidoDAO acessoRapidoDAO = new DAO.AcessoRapidoDAO();
			acessoRapidoDAO.remover(idFavorito);
			response.setSuccess(true);
		}
		catch(Exception e){
			response.addException(e);
		}
		return response.buildResponse();
	}
	
}
