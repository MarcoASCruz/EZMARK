package servicos;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import DAO.UsuarioDAO;
import modelos.Usuario;

public class Servico {
	public Usuario obterUsuarioLogado() throws Exception{
		Authentication usuarioAutenticado = SecurityContextHolder.getContext().getAuthentication();
		UsuarioDAO userDAO = new UsuarioDAO();
		Usuario usuario = userDAO.obter(usuarioAutenticado.getName());
		return usuario;
	}
}
