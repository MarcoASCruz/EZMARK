package servicos;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import modelos.Favorito;

import org.json.JSONObject;

@Path("/acessoRapido")
public class AcessoRapido {
	@GET
	@Produces("application/json")
	public Response getAll() {
		
		JSONObject jsonResult = new JSONObject();
		Favorito f1 = new Favorito("TEste.com", "teste", "testeeeeeeeeeeeeee");
		Favorito f2 = new Favorito("TEste2.com", "teste2", "testeeeeeeeeeeeeee2");
		List<Favorito> favoritos = new ArrayList<Favorito>();
		favoritos.add(f1);
		favoritos.add(f2);
		
		jsonResult.put("favoritos", favoritos);
		
		return Response.status(200).entity(jsonResult.toString()).build();
	}
}
