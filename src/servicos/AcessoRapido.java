package servicos;

import javax.ws.rs.Consumes;
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
	}
	
}
