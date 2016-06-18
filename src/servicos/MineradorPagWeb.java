package servicos;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class MineradorPagWeb {
	private String url;
	
	public MineradorPagWeb(String url) {
		this.url = url;
	}
	 
	public String procurarKeywords() throws IOException {
		return procurarCategorias("meta[name=keywords]");
	}
	public String procurarDescricao() throws IOException {
		return procurarCategorias("meta[name=description]");
	}
	private String procurarCategorias(String seletor) throws IOException {
		Document html = Jsoup.connect(getUrl()).get();
		Element elemento = html.select(seletor).first();
		String categorias = null;
		if (elemento != null){
			categorias = elemento.attr("content");
		}
		return categorias;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
