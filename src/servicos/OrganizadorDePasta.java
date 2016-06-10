package servicos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
			int idPastaAssociada = obterPastaAssociada(taxonomia, favorito, extrairCategorias(favorito));
			List<Favorito> favoritosAssociados = new ArrayList<Favorito>();
			if(associacoes.containsKey(idPastaAssociada)){
				favoritosAssociados = associacoes.get(idPastaAssociada);
			}
			favoritosAssociados.add(favorito);
			associacoes.put(idPastaAssociada, favoritosAssociados);	
		}
		
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
		tagsTec.add("ASP.Net");
		tagsTec.add("java");
		tecnologia.setTags(tagsTec);
		tecnologia.setPai(1);
		
		Pasta jogos = new Pasta();
		jogos.setId(4);
		jogos.setNome("jogos");
		//List<String> tagsJogos = new ArrayList<String>();
		//tagsJogos.add("games");
		//jogos.setTags(tagsJogos);
		jogos.setPai(1);
		
		Pasta noticia = new Pasta();
		noticia.setId(5);
		noticia.setNome("notícia");
		noticia.setPai(1);
		List<String> tagsN = new ArrayList<String>();
		tagsN.add("informação");
		tagsN.add("news");
		tagsN.add("jornal");
		tagsN.add("jornalismo");
		tagsN.add("portal");
		noticia.setTags(tagsN);
		
		taxonomia.add(home);
		taxonomia.add(esportes);
		taxonomia.add(tecnologia);
		taxonomia.add(jogos);
		taxonomia.add(noticia);
		
		return taxonomia;
	}
	private List<Favorito> obterFavoritos() throws Exception {
		FavoritoDAO favoritoDAO = new FavoritoDAO();
		return favoritoDAO.buscarFavoritosFilhos(idPasta);
	}
	
	private String extrairCategorias(Favorito favorito) throws IOException{
		String categoriasExtraidas = null;
		String keywords = procurarKeywords(favorito);
		
		if(keywords != null){
			categoriasExtraidas = keywords;
		}
		else{
			String descricao = procurarDescricao(favorito);
			if(descricao != null){
				categoriasExtraidas = descricao;
			}
			else{
				categoriasExtraidas = "Outros";
			}	
		}
		return categoriasExtraidas;
	} 
	private String procurarKeywords(Favorito favorito) throws IOException {
		String keywords = procurarCategorias(favorito.getUrl(), "meta[name=keywords]");
		return keywords;
	}
	private String procurarDescricao(Favorito favorito) throws IOException {
		String keywords = procurarCategorias(favorito.getUrl(),"meta[name=description]");
		return keywords;
	}
	private String procurarCorpoPagina(Favorito favorito) throws IOException {
		String texto = procurarCategorias(favorito.getUrl(), "body");
		return texto;
	}
	private String procurarCategorias(String url, String seletor ) throws IOException {
		Document html = Jsoup.connect(url).get();
		Elements elementos = html.select(seletor);
		String categorias = "";
		for (Element elemento : elementos) {
			if (elemento != null){
				categorias += elemento.attr("content");	
			}
		}
		return categorias;
	}
	
	
	private int obterPastaAssociada(List<Pasta> taxonomia, Favorito favorito, String keywords) {
		int idPastaComMaisAssociacoes = 0;
		int rankinkAssociacoes = 0;
		System.out.println(favorito.getUrl());
		for (Pasta pasta : taxonomia) {
			int quantChavesPorNome = procurarPalavra(pasta.getNome(), keywords); 
			int quantTagsDeFavNaTaxonomia = favoritoPossuiTagsDaTaxonomia(favorito.getTags(), pasta.getTags());
			int quantChavesPorTagTaxonomia = procurarPalavras(pasta.getTags(), keywords);
			int quantAssociacoes = quantChavesPorNome + quantTagsDeFavNaTaxonomia + quantChavesPorTagTaxonomia;
			System.out.println(quantAssociacoes);
			if(quantAssociacoes > 0)
			{
				if (rankinkAssociacoes == 0){
					rankinkAssociacoes = quantAssociacoes;
					idPastaComMaisAssociacoes = pasta.getId();
					System.out.println(favorito.getUrl());
					System.out.println(pasta.getId());
					System.out.println(quantAssociacoes);
				}
				else{
					if (quantAssociacoes > rankinkAssociacoes){
						rankinkAssociacoes = quantAssociacoes;
						idPastaComMaisAssociacoes = pasta.getId();
						System.out.println(favorito.getUrl());
						System.out.println(pasta.getId());
						System.out.println(quantAssociacoes);
					}
				}
			}
		}
		return idPastaComMaisAssociacoes;
	}
	private int favoritoPossuiTagsDaTaxonomia(List<String> tagsFavorito, List<String> tagsTaxonomia){
		String alvo = String.join(", ", tagsTaxonomia);
		if(tagsFavorito == null || alvo == null){
			return 0;
		}
		return procurarPalavras(tagsFavorito, alvo);
	}
	//case-insensitive
	private int procurarPalavras(List<String> palavras, String alvo){
		int resultado = 0;
		for (String palavra : palavras) {
			resultado += procurarPalavra(palavra, alvo);
		}
		return resultado;
	}
	//case-insensitive
	private int procurarPalavra(String palavra, String alvo){
		Pattern p = Pattern.compile("(?i)" + palavra);
		Matcher m = p.matcher(alvo);
		int count = 0;
		while(m.find()) {
			count++;
		}
		m.reset();
		return count;
	}
}
