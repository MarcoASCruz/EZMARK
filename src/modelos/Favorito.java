package modelos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Favorito")
public class Favorito {
	private int id;
	private String url;
	private String titulo;
	private String descricao;
	private String[] tags;
	private int idPasta;

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
	@Column(name="idPasta")
	public int getIdPasta() {
		return idPasta;
	}
	public void setIdPasta(int idPasta) {
		this.idPasta = idPasta;
	}
	
}
