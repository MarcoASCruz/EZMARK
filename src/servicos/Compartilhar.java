package servicos;


public class Compartilhar {
	private static String enderecoServidor = "http://localhost:8080/";
	private static String app = "GerenciadorDeFavoritos/";
	private static String metodoExibicao = "ExibirPastaPublica";
	
	public static String gerarLink(int idPasta){
		return enderecoServidor + app + metodoExibicao + "?id=" + idPasta;
	}
}
