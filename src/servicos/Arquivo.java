package servicos;

import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.List;

import javax.imageio.ImageIO;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.commons.io.IOUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.cfg.IndexOrUniqueKeySecondPass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

//import servicos.CacheAnnotations.NoCache;

import com.sun.jersey.multipart.FormDataParam;

import DAO.FavoritoDAO;
import DAO.PastaDAO;
import DAO.TagDAO;
import DAO.UsuarioDAO;
import modelos.DTOPesquisa;
import modelos.Favorito;
import modelos.Hierarquia;
import modelos.Pasta;
import modelos.Tag;
import flexjson.JSONDeserializer;

@Path("/arquivo")
public class Arquivo extends Servico {
	@GET
	@Path("/pasta/{id}")
	@Produces("application/json")
	public Response buscarArquivos(@PathParam("id") int idPastaPai) {
		AppResponse response = new AppResponse();
		try{
			PastaDAO pastaDAO = new DAO.PastaDAO();
			FavoritoDAO favoritoDAO = new FavoritoDAO();
			Pasta p = new Pasta();
			p.setPastas(pastaDAO.buscarPastasFilhas(idPastaPai, obterUsuarioLogado().getId()));
			p.setFavoritos(favoritoDAO.buscarFavoritosFilhos(idPastaPai));
			
			ArrayList l = new ArrayList();
			l.add(p);
			
			response.setSuccess(true);
			response.setContent(l);
		}
		catch(Exception e){
			response.addException(e);
		}
		return response.buildResponse();
	}
	
	@GET
	@Path("/favorito/{id}")
	@Produces("application/json")
	public Response buscarFavorito(@PathParam("id") int id) {
		AppResponse response = new AppResponse();
		try{
			FavoritoDAO favoritoDAO = new FavoritoDAO();
			ArrayList l = new ArrayList();
			l.add(favoritoDAO.buscar(id));
			response.setSuccess(true);
			response.setContent(l);
		}
		catch(Exception e){
			response.addException(e);
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
			p.setDataCriacao(new Timestamp(new java.util.Date().getTime()));
			DAO.PastaDAO pastaDAO = new DAO.PastaDAO();
			p = pastaDAO.adicionar(p, obterUsuarioLogado().getId());
			ArrayList<Pasta> l = new ArrayList<Pasta>();
			l.add(p);
			response.setSuccess(true);
			response.setContent(l);
		}
		catch(Exception e){
			response.addException(e);
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
			response.addException(e);
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
			pastaDAO.remover(id, obterUsuarioLogado().getId());
			response.setSuccess(true);
		}
		catch(Exception e){
			response.addException(e);
		}
		return response.buildResponse();
	}
	
	@POST
	@Path("/favorito")
	@Produces("application/json")
	//Não está adicionando imagem
	public Response adicionarFavorito(@FormParam("favorito") String favoritoJson) {
		AppResponse response = new AppResponse();
		try{
			Favorito f = new JSONDeserializer<Favorito>().use( null, Favorito.class ).deserialize(favoritoJson);
			f.setDataCriacao(new Timestamp(new java.util.Date().getTime()));
			DAO.FavoritoDAO favoritoDAO = new DAO.FavoritoDAO();
			f = favoritoDAO.adicionar(f);
			ArrayList l = new ArrayList();
			l.add(f);
			response.setSuccess(true);
			response.setContent(l);
		}
		catch(Exception e){
			response.addException(e);
		}
		return response.buildResponse();
	}
	
	@PUT
	@Path("/favorito")
	@Produces("application/json")
	//Não está adicionando imagem
	public Response alterarFavorito(@FormParam("favorito") String favoritoJson) {
		AppResponse response = new AppResponse();
		try{
			Favorito f = new JSONDeserializer<Favorito>().use( null, Favorito.class ).deserialize(favoritoJson);
			f.setDataCriacao(new Timestamp(new java.util.Date().getTime()));
			DAO.FavoritoDAO favoritoDAO = new DAO.FavoritoDAO();
			f = favoritoDAO.alterar(f);
			ArrayList l = new ArrayList();
			l.add(f);
			response.setSuccess(true);
			response.setContent(l);
		}
		catch(Exception e){
			response.addException(e);
		}
		return response.buildResponse();
	}
	
	@DELETE
	@Path("/favorito")
	@Produces("application/json")
	public Response removerFavorito(@FormParam("id") int id) {
		AppResponse response = new AppResponse();
		try{
			DAO.FavoritoDAO favoritoDAO = new DAO.FavoritoDAO();
			favoritoDAO.remover(id);
			response.setSuccess(true);
		}
		catch(Exception e){
			response.addException(e);
		}
		return response.buildResponse();
	}
	
	@PUT
	@Path("/favorito/atualizarAcesso")
	@Produces("application/json")
	public Response atualizarAcesso(@FormParam("id") int idFavorito) {
		AppResponse response = new AppResponse();
		try{
			FavoritoDAO favoritoDAO = new FavoritoDAO();
			favoritoDAO.atualizarAcesso(idFavorito);
			response.setSuccess(true);
		}
		catch(Exception e){
			response.addException(e);
		}
		return response.buildResponse();
	}
	
	@GET
	@Path("/favorito/recemAcessados")
	@Produces("application/json")
	public Response recemAcessados() {
		AppResponse response = new AppResponse();
		try{
			FavoritoDAO favoritoDAO = new FavoritoDAO();
			response.setContent(favoritoDAO.recemAcessados(obterUsuarioLogado().getId()));
			response.setSuccess(true);
		}
		catch(Exception e){
			response.addException(e);
		}
		return response.buildResponse();
	}
	
	@GET
	@Path("/favorito/maisAcessados")
	@Produces("application/json")
	public Response maisAcessados() {
		AppResponse response = new AppResponse();
		try{
			FavoritoDAO favoritoDAO = new FavoritoDAO();
			response.setContent(favoritoDAO.maisAcessados(obterUsuarioLogado().getId()));
			response.setSuccess(true);
		}
		catch(Exception e){
			response.addException(e);
		}
		return response.buildResponse();
	}
	
	@GET
	@Path("/favorito/recemAdicionados")
	@Produces("application/json")
	public Response recemAdicionados() {
		AppResponse response = new AppResponse();
		try{
			FavoritoDAO favoritoDAO = new FavoritoDAO();
			response.setContent(favoritoDAO.recemAdicionados(obterUsuarioLogado().getId()));
			response.setSuccess(true);
		}
		catch(Exception e){
			response.addException(e);
		}
		return response.buildResponse();
	}
	
	@GET
	@Path("/pasta/hierarquia")
	@Produces("application/json")
	public Response buscarHierarquia() {
		AppResponse response = new AppResponse();
		try{
			PastaDAO pastaDAO = new PastaDAO();
			List<Hierarquia> hierarquia = pastaDAO.buscarHierarquia(obterUsuarioLogado().getId());
			response.setContent(hierarquia);
			response.setSuccess(true);
		}
		catch(Exception e){
			response.addException(e);
		}
		return response.buildResponse();
	}
	
	@POST
	@Path("/favorito/img")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response atualizarImagemFavorito(@FormDataParam("img") InputStream img, @FormDataParam("id") int id){
		AppResponse response = new AppResponse();
		try{
		    new FavoritoDAO().atualizarImagem(IOUtils.toByteArray(img), id);
		    return Response.ok("OK").build();
		}
		catch(Exception e){
			response.addException(e);
		}
		return response.buildResponse();
	}
	
	@POST
	@Path("/pasta/img")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response atualizarImagemPasta(@FormDataParam("img") InputStream img, @FormDataParam("id") int id){
		AppResponse response = new AppResponse();
		try{
		    new PastaDAO().atualizarImagem(IOUtils.toByteArray(img), id);
		    return Response.ok("OK").build();
		}
		catch(Exception e){
			response.addException(e);
		}
		return response.buildResponse();
	}
	
	@GET
	@Path("/pasta/img/{id}")
	@Produces("image/png")
	public Response getImagemPasta(@PathParam("id") int id) throws Exception {
	    ResponseBuilder response = Response.ok(new PastaDAO().getImagem(id)); 
		response.cacheControl(getNoCacheControl());
	    return response.build();
	}
	
	@GET
	@Path("/favorito/img/{id}")
	@Produces("image/*")
	public Response getImagemFavorito(@PathParam("id") int id) throws Exception {		
		ResponseBuilder response = Response.ok(new FavoritoDAO().getImagem(id)); 
		response.cacheControl(getNoCacheControl());
	    return response.build();
	}
	
	private CacheControl getNoCacheControl(){
		CacheControl control = new CacheControl(); 
		control.setNoCache(true);
		control.setNoStore(true);
		return control;
	}
	
	@POST
	@Path("/favorito/tag")
	@Produces("application/json")
	public Response adicionarTagFavorito(@FormParam("idFavorito") int idFavorito , @FormParam("tag") String tagNome) {
		AppResponse response = new AppResponse();
		try{

			FavoritoDAO favDAO = new FavoritoDAO();
			favDAO.adicionarTag(tagNome, idFavorito);
			
			ArrayList l = new ArrayList();
			l.add(tagNome);
			
			response.setSuccess(true);
			response.setContent(l);
		}
		catch(Exception e){
			response.addException(e);
		}
		return response.buildResponse();
	}
	
	@DELETE
	@Path("/favorito/tag")
	@Produces("application/json")
	public Response removerTagFavorito(@FormParam("idFavorito") int idFavorito , @FormParam("idTag") String tag) {
		AppResponse response = new AppResponse();
		try{
			FavoritoDAO favDAO = new FavoritoDAO();
			favDAO.removerTag(tag, idFavorito);
			response.setSuccess(true);
		}
		catch(Exception e){
			response.addException(e);
		}
		return response.buildResponse();
	}
	
	@POST
	@Path("/pasta/tag")
	@Produces("application/json")
	public Response adicionarTagPasta(@FormParam("idPasta") int idPasta , @FormParam("tag") String tagNome) {
		AppResponse response = new AppResponse();
		try{
			PastaDAO pastaDAO = new PastaDAO();
			pastaDAO.adicionarTag(tagNome, idPasta);
			
			
			ArrayList l = new ArrayList();
			l.add(tagNome);
			
			response.setSuccess(true);
			response.setContent(l);
		}
		catch(Exception e){
			response.addException(e);
		}
		return response.buildResponse();
	}
	
	@DELETE
	@Path("/pasta/tag")
	@Produces("application/json")
	public Response removerTagPasta(@FormParam("idPasta") int idPasta , @FormParam("idTag") String tag) {
		AppResponse response = new AppResponse();
		try{
			PastaDAO pastaDAO = new PastaDAO();
			pastaDAO.removerTag(tag, idPasta);
			response.setSuccess(true);
		}
		catch(Exception e){
			response.addException(e);
		}
		return response.buildResponse();
	}
	
	@GET
	@Path("/pesquisar/{conteudo}")
	@Produces("application/json")
	public Response pesquisar(@PathParam("conteudo") String conteudo) {
		AppResponse response = new AppResponse();
		try{
			PastaDAO pastaDAO = new DAO.PastaDAO();
			FavoritoDAO favoritoDAO = new FavoritoDAO();
			
			List<Pasta> pastas = pastaDAO.pesquisar(conteudo);
			List<Favorito> favoritos = favoritoDAO.pesquisar(conteudo);
			
			DTOPesquisa pesquisa = new DTOPesquisa(pastas, favoritos);
			
			ArrayList l = new ArrayList();
			l.add(pesquisa);
			
			response.setSuccess(true);
			response.setContent(l);
		}
		catch(Exception e){
			response.addException(e);
		}
		return response.buildResponse();
	}
	
	@POST
	@Path("pasta/compartilhar")
	@Produces("application/json")
	public Response compartilharPasta(@FormParam("idPasta") int idPasta) {
		AppResponse response = new AppResponse();
		try{
			if (idPasta == 0){
				throw new Exception("Id zerado");
			}
			PastaDAO pastaDAO = new PastaDAO();
			
			if (!pastaDAO.isPublica(idPasta)){
				pastaDAO.compartilhar(idPasta);
			}
			
			String linkAcesso = Compartilhar.gerarLink(idPasta); 
			
			response.setSuccess(true);
			response.setContent(linkAcesso);
		}
		catch(Exception e){
			response.addException(e);
		}
		return response.buildResponse();
	}
}
