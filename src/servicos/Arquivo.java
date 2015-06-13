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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.cfg.IndexOrUniqueKeySecondPass;

import com.sun.jersey.multipart.FormDataParam;

import DAO.FavoritoDAO;
import DAO.PastaDAO;
import modelos.AppResponse;
import modelos.Favorito;
import modelos.Hierarquia;
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
			Pasta pasta = pastaDAO.adicionar(p);
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
			pastaDAO.remover(id);
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
	public Response adcionarFavorito(@FormParam("favorito") String favoritoJson) {
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
			response.setContent(favoritoDAO.recemAcessados());
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
			response.setContent(favoritoDAO.maisAcessados());
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
			response.setContent(favoritoDAO.recemAdicionados());
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
			List<Hierarquia> hierarquia = pastaDAO.buscarHierarquia();
			response.setContent(hierarquia);
			response.setSuccess(true);
		}
		catch(Exception e){
			response.addException(e);
		}
		return response.buildResponse();
	}
	
	@POST
	@Path("/img")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces("image/png")
	public Response testeIMG(@FormDataParam("img") InputStream img) throws Exception {
	    byte[] imageData; 
	    ByteArrayOutputStream buffer = new ByteArrayOutputStream();

	    int nRead;
	    byte[] data = new byte[16384];
	    
	    while ((nRead = img.read(data, 0, data.length)) != -1) {
	      buffer.write(data, 0, nRead);
	    }

	    buffer.flush();
	    new FavoritoDAO().alterarIMG(buffer.toByteArray());
	    // uncomment line below to send non-streamed
	    // return Response.ok(imageData).build();

	    // uncomment line below to send streamed
	    return Response.ok("teste").build();
	}
	
	@GET
	@Path("/img")
	@Produces("image/png")
	public Response getIMG() throws Exception {
	    return Response.ok(new FavoritoDAO().getIMG()).build();
	}
}
