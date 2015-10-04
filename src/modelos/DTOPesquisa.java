package modelos;

import java.util.List;

public class DTOPesquisa {
	public List<Pasta> pastas;
	public List<Favorito> favoritos;
	
	public DTOPesquisa(List<Pasta> pastas, List<Favorito> favoritos) {
		super();
		this.pastas = pastas;
		this.favoritos = favoritos;
	}

	public List<Pasta> getPastas() {
		return pastas;
	}

	public void setPastas(List<Pasta> pastas) {
		this.pastas = pastas;
	}

	public List<Favorito> getFavoritos() {
		return favoritos;
	}

	public void setFavoritos(List<Favorito> favoritos) {
		this.favoritos = favoritos;
	}
	
	
}
