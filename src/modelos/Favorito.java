package modelos;

public class Favorito {
	private String url;
	private String titulo;
	private String descricao;
	private String[] tags;
	
	private Favorito(){}
	public Favorito(String url, String titulo, String descricao) {
		this.url = url;
		this.titulo = titulo;
		this.descricao = descricao;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
