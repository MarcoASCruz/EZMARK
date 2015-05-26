package servicos;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

@Path("/acessoRapido")
public class AcessoRapido {
	@GET
	@Produces("application/json")
	public Response getAll() {
		
		JSONObject jsonObject = new JSONObject();
		String link = "www.teste.com.br";
		String titulo = "teste"; 
		jsonObject.put("link", link); 
		jsonObject.put("titulo", titulo);
 
		return Response.status(200).entity(jsonObject.toString()).build();
	}
}
