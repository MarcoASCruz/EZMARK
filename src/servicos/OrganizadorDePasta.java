package servicos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import DAO.FavoritoDAO;
import DAO.PastaDAO;
import modelos.Favorito;
import modelos.Pasta;

public class OrganizadorDePasta {
	
	private int idPasta = 1;
	private Favorito favorito = null;

	public OrganizadorDePasta() {
	}
	public OrganizadorDePasta(int idPasta) {
		this.idPasta = idPasta;
	}
	
	public Object get() throws Exception{
		List<Pasta> taxonomia = obterTaxonomia();
		List<Favorito> favoritos = obterFavoritos();
		
		HashMap<Integer, List<Favorito>> associacoes = new HashMap<Integer, List<Favorito>>();
		
		for (Favorito favorito : favoritos) {
			int idPastaAssociada = obterPastaAssociada(taxonomia, extrairCategorias(favorito));
			List<Favorito> favoritosAssociados = new ArrayList<Favorito>();
			if(associacoes.containsKey(idPastaAssociada)){
				favoritosAssociados = associacoes.get(idPastaAssociada);
			}
			favoritosAssociados.add(favorito);
			associacoes.put(idPastaAssociada, favoritosAssociados);	
		}
		/*HashMap<Integer, String[]> associacoes = new HashMap<Integer, String[]>();
		for (Favorito favorito : favoritos) {
			int idPastaAssociada = obterPastaAssociada(taxonomia, extrairCategorias(favorito));
			associacoes.put(idPastaAssociada, extrairCategorias(favorito));	
		}*/
		
		return associacoes;
	}	
	
	private List<Pasta> obterTaxonomia(){
		List<Pasta> taxonomia = new ArrayList<Pasta>();
		
		Pasta home = new Pasta();
		home.setId(1);
		home.setNome("home");
		
		Pasta esportes = new Pasta();
		esportes.setId(2);
		esportes.setNome("esportes");
		esportes.setPai(1);
		List<String> tags = new ArrayList<String>();
		tags.add("esporte");
		tags.add("futebol");
		tags.add("mma");
		esportes.setTags(tags);
		
		Pasta tecnologia = new Pasta();
		tecnologia.setId(3);
		tecnologia.setNome("tecnologia");
		List<String> tagsTec = new ArrayList<String>();
		tagsTec.add("informatica");
		tagsTec.add("programming");
		tagsTec.add("programacao");
		tagsTec.add("digital");
		tecnologia.setTags(tagsTec);
		tecnologia.setPai(1);
		
		taxonomia.add(home);
		taxonomia.add(esportes);
		taxonomia.add(tecnologia);
		return taxonomia;
	}
	private List<Favorito> obterFavoritos() throws Exception {
		FavoritoDAO favoritoDAO = new FavoritoDAO();
		return favoritoDAO.buscarFavoritosFilhos(idPasta);
	}
	
	private String[] extrairCategorias(Favorito favorito) throws IOException{
		String[] categoriasExtraidas = null;
		String[] keywords = procurarKeywords(favorito);
		
		if(keywords != null){
			categoriasExtraidas = keywords;
		}
		else{
			String[] descricao = procurarDescricao(favorito);
			if(descricao != null){
				categoriasExtraidas = descricao;
			}
			else{
				categoriasExtraidas = new String[]{"Outros"};
			}	
		}
		return categoriasExtraidas;
	} 
	private String[] procurarKeywords(Favorito favorito) throws IOException {
		String[] keywords = procurarCategorias(favorito.getUrl(), "meta[name=keywords]", new char[]{','});
		return keywords;
	}
	private String[] procurarDescricao(Favorito favorito) throws IOException {
		String[] keywords = procurarCategorias(
			favorito.getUrl(),
			"meta[name=description]",
			new char[]{',', '.', '!', ' '}
		);
		return keywords;
	}
	private String[] procurarCategorias(String url, String seletor, char[] caracteresInvalidos ) throws IOException {
		Document html = Jsoup.connect(url).get();
		Element elemento = html.select(seletor).first();
		String[] categorias = null;
		if (elemento != null){
			String categoriasTexto = elemento.attr("content");
			for (char caraterInvalido  : caracteresInvalidos) {
				categoriasTexto = categoriasTexto.replace(caraterInvalido, '#');
			}
			categorias = categoriasTexto.split("#");
		}
		return categorias;
	}
	
	private int obterPastaAssociada(List<Pasta> taxonomia, String[] keywords) {
		int id = 0;
		for (Pasta pasta : taxonomia) {
			for (String keyword : keywords) {
				List<String> tags = pasta.getTags();
				if(pasta.getNome().equalsIgnoreCase(keyword) || tags.stream().filter(x -> x.equalsIgnoreCase(keyword)).count() > 0){
					id = pasta.getId();
				}
			}
		}
		return id;
	}
}
