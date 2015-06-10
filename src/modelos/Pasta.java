package modelos;

import java.util.ArrayList;
import java.util.Collection;
import java.sql.Date;
import java.util.List;

import javax.persistence.Table;

public class Pasta {
	private int id;
	private int pai;
	private String nome;
	private Date dataCriacao;
	private int numEstrela;
	private boolean publica;
	private byte[] imagem;
	private List<Favorito> favoritos;
	private List<Pasta> pastas = new ArrayList<Pasta>();
	
	public Pasta(){}
	public Pasta(int id, int pai, String nome, Date dataCriacao,
			int numEstrela, boolean publica, byte[] imagem,
			List<Favorito> favoritos, List<Pasta> pastas) {
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
	public Date getDataCriacao() {
		return dataCriacao;
	}
	
	public void setDataCriacao(Date dataCriacao) {
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
	public byte[] getImagem() {
		return imagem;
	}
	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
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
	
	
	
	
}
