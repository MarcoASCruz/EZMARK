package servicos;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import modelos.AppResponse;
import modelos.Favorito;
import modelos.Pasta;
import flexjson.JSONDeserializer;

@Path("/arquivo")
public class Arquivo {
	@GET
	@Path("/pasta/{id}")
	@Produces("application/json")
	public Response buscarArquivos(@PathParam("id") int id) {
		AppResponse response = new AppResponse();
		try{
			DAO.PastaDAO pastaDAO = new DAO.PastaDAO();
			Pasta pasta = pastaDAO.buscarArquivos(id);
			ArrayList l = new ArrayList();
			l.add(pasta);
			response.setSuccess(true);
			response.setContent(l);
		}
		catch(Exception e){
			response.setSuccess(false);
			response.setContent(e.getMessage());
			response.setStackTrace(e.getStackTrace());
		}
		return response.buildResponse();
	}
	
	@POST
	@Path("/pasta")
	@Produces("application/json")
	public Response adcionarPasta(@FormParam("pasta") String pastaJson) {
		AppResponse response = new AppResponse();
		try{
			Pasta p = new JSONDeserializer<Pasta>().use( null, Pasta.class ).deserialize(pastaJson);
			p.setDataCriacao(new Date(java.lang.System.currentTimeMillis()));
			DAO.PastaDAO pastaDAO = new DAO.PastaDAO();
			Pasta pasta = pastaDAO.adicionar(p);
			ArrayList l = new ArrayList();
			l.add(p);
			response.setSuccess(true);
			response.setContent(l);
		}
		catch(Exception e){
			response.setSuccess(false);
			response.setContent(e.getMessage());
			response.setStackTrace(e.getStackTrace());
		}
		return response.buildResponse();
	}
	
	@PUT
	@Path("/pasta")
	@Produces("application/json")
	public Response alterarPasta(@FormParam("pasta") String pastaJson) {
		AppResponse response = new AppResponse();
		try{
			Pasta p = new JSONDeserializer<Pasta>().use( null, Pasta.class ).deserialize(pastaJson);
			DAO.PastaDAO pastaDAO = new DAO.PastaDAO();
			Pasta pasta = pastaDAO.alterar(p);
			ArrayList l = new ArrayList();
			l.add(p);
			response.setSuccess(true);
			response.setContent(l);
		}
		catch(Exception e){
			response.setSuccess(false);
			response.setContent(e.getMessage());
			response.setStackTrace(e.getStackTrace());
		}
		return response.buildResponse();
	}
	
	@DELETE
	@Path("/pasta")
	@Produces("application/json")
	public Response removerPasta(@FormParam("id") int id) {
		AppResponse response = new AppResponse();
		try{
			DAO.PastaDAO pastaDAO = new DAO.PastaDAO();
			pastaDAO.remover(id);
			response.setSuccess(true);
		}
		catch(Exception e){
			response.setSuccess(false);
			response.setContent(e.getMessage());
			response.setStackTrace(e.getStackTrace());
		}
		return response.buildResponse();
	}
	
	/*@GET
	@Path("{id}")
	@Produces("application/json")
	public Response find(@PathParam("id") int id) {
		AppResponse response = new AppResponse();
		try{
			response.setSuccess(true);
			DAO.AcessoRapido acessoRapidoDAO = new DAO.AcessoRapido();
			List<Favorito> favorito = acessoRapidoDAO.find(id);
			response.setContent(favorito.get(0));
		}
		catch(Exception e){
			response.setSuccess(false);
			response.setContent(e.getMessage());
			response.setStackTrace(e.getStackTrace());
		}
		return response.buildResponse();
	}
		
		@POST
		@Produces("application/json")
		public Response adicionarItem(@FormParam("favorito") String  favoritoJson){
			AppResponse response = new AppResponse();
			try{
				Favorito favorito = new JSONDeserializer<Favorito>().use( null, Favorito.class ).deserialize(favoritoJson);
				DAO.AcessoRapido acessoRapidoDAO = new DAO.AcessoRapido();
				acessoRapidoDAO.add(favorito);
				response.setSuccess(true);
			}
			catch(Exception e){
				response.setSuccess(false);
				response.setContent(e.getMessage());
				response.setStackTrace(e.getStackTrace());
			}
			return response.buildResponse();
		}*/
}
