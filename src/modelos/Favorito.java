package modelos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="favorito")
public class Favorito {
	private int id;
	private String url;
	private String titulo;
	private String descricao;
	private String[] tags;
	//private Pasta pai;
	private int numEstrela;
	private byte[] imagem;

	public byte[] getImagem() {
		return imagem;
	}
	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}
	public int getNumEstrela() {
		return numEstrela;
	}
	public void setNumEstrela(int numEstrela) {
		this.numEstrela = numEstrela;
	}
	@Id
	@GeneratedValue
	@Column(name="id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="url", nullable=false)
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Column(name="titulo")
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	@Column(name="descricao")
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	//@ManyToOne
    //@JoinColumn(name="id_pasta")
	/*public Pasta getPai() {
		return pai;
	}
	public void setIdPasta(Pasta pai) {
		this.pai = pai;
	}*/
	
}
