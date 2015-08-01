package modelos;

import java.sql.Timestamp;
import java.util.List;

public class Favorito {
	private int id;
	private String url;
	private String titulo;
	private String descricao;
	private Timestamp dataAcesso;
	private Timestamp dataCriacao;
	private List<Tag> tags;
	private int pai;
	private int numEstrela;
	private String imagem;
	private boolean acessoRapido;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public Timestamp getDataAcesso() {
		return dataAcesso;
	}
	public void setDataAcesso(Timestamp dataAcesso) {
		this.dataAcesso = dataAcesso;
	}
	public Timestamp getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Timestamp dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public List<Tag> getTags() {
		return tags;
	}
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	public int getPai() {
		return pai;
	}
	public void setPai(int pai) {
		this.pai = pai;
	}
	public int getNumEstrela() {
		return numEstrela;
	}
	public void setNumEstrela(int numEstrela) {
		this.numEstrela = numEstrela;
	}
	public String getImagem() {
		return imagem;
	}
	public void setImagem(int id) {
		this.imagem = "/GerenciadorDeFavoritos/servicos/arquivo/favorito/img/" + id;
	}
	public boolean isAcessoRapido() {
		return acessoRapido;
	}
	public void setAcessoRapido(boolean acessoRapido) {
		this.acessoRapido = acessoRapido;
	}
	
}
