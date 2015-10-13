package servicos;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import DAO.UsuarioDAO;
import modelos.AppResponse;

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
}