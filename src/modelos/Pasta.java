package modelos;

import java.util.ArrayList;
import java.util.Collection;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Table;

public class Pasta {
	private int id;
	private int pai;
	private String nome;
	private Timestamp dataCriacao;
	private int numEstrela;
	private boolean publica;
	private String imagem;
	private List<Favorito> favoritos;
	private List<Pasta> pastas = new ArrayList<Pasta>();
	private String descricao;
	private List<String> tags = new ArrayList<String>();
	
	public Pasta(){}
	public Pasta(int id, int pai, String nome, Timestamp dataCriacao,
			int numEstrela, boolean publica, String imagem,
			List<Favorito> favoritos, List<Pasta> pastas, String descricao) {
		super();
		this.id = id;
		this.pai = pai;
		this.nome = nome;
		this.dataCriacao = dataCriacao;
		this.numEstrela = numEstrela;
		this.publica = publica;
		this.imagem = imagem;
		this.favoritos = favoritos;
		this.pastas = pastas;
		this.descricao = descricao;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	public int getPai() {
		return pai;
	}
	public void setPai(int pai) {
		this.pai = pai;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Timestamp getDataCriacao() {
		return dataCriacao;
	}
	
	public void setDataCriacao(Timestamp dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public int getNumEstrela() {
		return numEstrela;
	}
	public void setNumEstrela(int numEstrela) {
		this.numEstrela = numEstrela;
	}
	public boolean isPublica() {
		return publica;
	}
	public void setPublica(boolean publica) {
		this.publica = publica;
	}
	public String getImagem() {
		return imagem;
	}
	public void buildImagemUrl (){
		this.imagem = "/GerenciadorDeFavoritos/servicos/arquivo/pasta/img/" + id;
	}
	public void setImagem(int id) {
		this.imagem = "/GerenciadorDeFavoritos/servicos/arquivo/pasta/img/" + id;
	}
	public List<Favorito> getFavoritos() {
		return favoritos;
	}
	public void setFavoritos(List<Favorito> favoritos) {
		this.favoritos = favoritos;
	}
	public List<Pasta> getPastas() {
		return pastas;
	}
	public void setPastas(List<Pasta> pastas) {
		this.pastas = pastas;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	
	
	
}
