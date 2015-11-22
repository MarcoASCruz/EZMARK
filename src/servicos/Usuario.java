package servicos;

import java.util.ArrayList;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import modelos.Pasta;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import flexjson.JSONDeserializer;
import DAO.PastaDAO;
import DAO.UsuarioDAO;

@Path("/usuario")
public class Usuario extends Servico {
	@GET
	@Path("/obterNome")
	@Produces("application/json")
	public Response obterNome() {
		AppResponse response = new AppResponse();
		try{			
			ArrayList<modelos.Usuario> l = new ArrayList<modelos.Usuario>();
			l.add(obterUsuarioLogado());
			
			response.setSuccess(true);
			response.setContent(l);
		}
		catch(Exception e){
			response.addException(e);
		}
		return response.buildResponse();
	}
	@GET
	@Path("/buscarHome")
	@Produces("application/json")
	public Response buscarHome() {
		AppResponse response = new AppResponse();
		try{
			PastaDAO pastaDAO = new PastaDAO();
			Pasta pasta = pastaDAO.buscarHome(obterUsuarioLogado().getId());
			
			ArrayList<Pasta> l = new ArrayList<Pasta>();
			l.add(pasta);
			
			response.setSuccess(true);
			response.setContent(l);
		}
		catch(Exception e){
			response.addException(e);
		}
		return response.buildResponse();
	}
	@POST  
	@Path("/adicionar")
	@Produces("application/json")
	public Response adicionar(@FormParam("usuario") String usuarioJson) {
		AppResponse response = new AppResponse();
		try{
			modelos.Usuario usuario = new JSONDeserializer<modelos.Usuario>().use( null, modelos.Usuario.class ).deserialize(usuarioJson);
			UsuarioDAO userDAO = new UsuarioDAO();
			usuario = userDAO.adicionar(usuario);
			
			PastaDAO pastaDAO = new PastaDAO();
			pastaDAO.adicionarHome(usuario.getId());


			ArrayList<modelos.Usuario> l = new ArrayList<modelos.Usuario>();
			l.add(usuario);
			
			response.setSuccess(true);
			response.setContent(l);
		}
		catch(Exception e){
			response.addException(e);
		}
		return response.buildResponse();
	}

}