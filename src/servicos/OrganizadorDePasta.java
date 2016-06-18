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

import DAO.FavoritoDAO;
import DAO.PastaDAO;
import DAO.TaxonomiaDAO;
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
	
	public Object executar(int idPastaPai, int idUsuario) throws Exception{
		List<Pasta> taxonomia = obterTaxonomia();
		FavoritoDAO favoritoDAO = new FavoritoDAO();
		List<Favorito> favoritos = favoritoDAO.buscarFavoritosFilhos(idPastaPai);
		
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
		
		//implementa associações
		for (Integer idPasta : associacoes.keySet()) {
			//criar pasta
			TaxonomiaDAO taxonomiaDAO = new TaxonomiaDAO();
			Pasta pasta = null;
			if(idPasta == 0){
				Pasta pastaOutros = new Pasta();
				pastaOutros.setNome("Outros");
				pasta = pastaOutros;
			}
			else{
				pasta = taxonomiaDAO.buscar(idPasta);
				pasta.setId(0);
			}
			pasta.setPai(idPastaPai);
			pasta = new PastaDAO().adicionar(pasta, idUsuario);
			
			//associar favoritos
			for (Favorito favorito : associacoes.get(idPasta)) {
				favoritoDAO.mover(pasta.getId(), favorito.getId());
			}
		}
		
		return associacoes;
	}	
	
	private List<Pasta> obterTaxonomia() throws Exception{
		List<Pasta> taxonomia = new TaxonomiaDAO().buscarTodas();
		return taxonomia;
	}
	
	private String extrairCategorias(Favorito favorito) throws IOException{
		MineradorPagWeb minerador = new MineradorPagWeb(favorito.getUrl());
		
		String categoriasExtraidas = null;
		String keywords = minerador.procurarKeywords();
		
		if(keywords != null){
			categoriasExtraidas = keywords;
		}
		else{
			String descricao = minerador.procurarDescricao();
			if(descricao != null){
				categoriasExtraidas = descricao;
			}
			else{
				categoriasExtraidas = "Outros";
			}	
		}
		return categoriasExtraidas;
	}
	
	
	private int obterPastaAssociada(List<Pasta> taxonomia, Favorito favorito, String keywords) {
		int idPastaComMaisAssociacoes = 0;
		float rankingAssociacoes = 0;
		System.out.println(favorito.getUrl());
		for (Pasta pasta : taxonomia) {
			int quantAssociacoesPorNomePasta = procurarPalavra(pasta.getNome(), keywords); 
			int quantAssociacoesTagsDeFavNaTaxonomia = favoritoPossuiTagsDaTaxonomia(favorito.getTags(), pasta);
			int quantAssociacoesPorTagTaxonomia = procurarPalavras(pasta.getTags(), keywords);
			float quantAssociacoes = quantidadeAssociacoes(quantAssociacoesPorNomePasta, quantAssociacoesTagsDeFavNaTaxonomia, quantAssociacoesPorTagTaxonomia);
			System.out.println(quantAssociacoes);
			if(quantAssociacoes > 0)
			{
				if (rankingAssociacoes == 0){
					rankingAssociacoes = quantAssociacoes;
					idPastaComMaisAssociacoes = pasta.getId();
				}
				else{
					if (quantAssociacoes > rankingAssociacoes){
						rankingAssociacoes = quantAssociacoes;
						idPastaComMaisAssociacoes = pasta.getId();
					}
				}
			}
		}
		return idPastaComMaisAssociacoes;
	}
	private int favoritoPossuiTagsDaTaxonomia(List<String> tagsFavorito, Pasta pastaTaxonomia){
		int quantAssociacoes = 0;
		if(pastaTaxonomia.getTags() == null){
			return quantAssociacoes;
		}
		quantAssociacoes += procurarPalavras(tagsFavorito, String.join(", ", pastaTaxonomia.getTags())); 
		quantAssociacoes += procurarPalavras(tagsFavorito, pastaTaxonomia.getNome());
		return quantAssociacoes;
	}
	//case-insensitive
	private int procurarPalavras(List<String> palavras, String alvo){
		int resultado = 0;
		
		if(palavras == null || alvo == null){
			return resultado;
		}
		
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
	private float quantidadeAssociacoes(int quantAssociacoesPorNomePasta, int quantAssociacoesTagsDeFavNaTaxonomia, int quantAssociacoesPorTagTaxonomia){
		int pesoAssociacaoPorNome = 5;
		int pesoAssociacaoPorTagsDeFavNaTaxonomia = 3;
		int pesoAssociacaoPorTagTaxonomia = 2;
		float somatorioPesos = pesoAssociacaoPorNome + pesoAssociacaoPorTagsDeFavNaTaxonomia + pesoAssociacaoPorTagTaxonomia;
		float quantAssociacoes = ((quantAssociacoesPorNomePasta * pesoAssociacaoPorNome) + (quantAssociacoesTagsDeFavNaTaxonomia * pesoAssociacaoPorTagsDeFavNaTaxonomia) + (quantAssociacoesPorTagTaxonomia * pesoAssociacaoPorTagTaxonomia))/somatorioPesos;
		return quantAssociacoes;
	}
}
