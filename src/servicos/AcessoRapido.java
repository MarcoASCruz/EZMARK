package servicos;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import modelos.AppResponse;
import modelos.Favorito;

import org.json.JSONObject;

@Path("/acessoRapido")
public class AcessoRapido {
	@GET
	@Produces("application/json")
	public Response getAll() {
		AppResponse response = new AppResponse();
		try{
			response.setSuccess(true);
			response.setContent("OK");
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
	public Response adicionarItem() {
		AppResponse response = new AppResponse();
		try{
			DAO.AcessoRapido acessoRapidoDAO = new DAO.AcessoRapido();
			//acessoRapidoDAO.add(new Favorito());
			response.setSuccess(true);
			response.setContent("OK");
		}
		catch(Exception e){
			response.setSuccess(false);
			response.setContent(e.getMessage());
			response.setStackTrace(e.getStackTrace());
		}
		return response.buildResponse();
	}
	
}
