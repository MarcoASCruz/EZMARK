package servicos;

import java.util.ArrayList;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
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
public class Usuario {
	@GET
	@Path("/obterNome")
	@Produces("application/json")
	public Response obterNome() {
		AppResponse response = new AppResponse();
		try{
			Authentication usuarioAutenticado = SecurityContextHolder.getContext().getAuthentication();
		    
			UsuarioDAO userDAO = new UsuarioDAO();
			modelos.Usuario usuario = userDAO.obter(usuarioAutenticado.getName());
			
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
	@GET  
	@Path("/adicionar")
	@Produces("application/json")
	public Response adicionar(){//(@FormParam("usuario") String usuarioJson) {
		AppResponse response = new AppResponse();
		try{
			//a linha correta é a do comentário, porém, a de baixo esta sendo usada nos testes
			//modelos.Usuario usuario = new JSONDeserializer<modelos.Usuario>().use( null, modelos.Usuario.class ).deserialize(usuarioJson);
			modelos.Usuario usuario = new modelos.Usuario();
			usuario.setNome("MyUser");
			usuario.setEmail("MyUser@gmail.com");
			usuario.setSenha("123");
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