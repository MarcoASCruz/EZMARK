var Mapper = function(){
	this.modelos = {
		//
	}
	
	this.services = {
		buscarArquivos: function(id, onSuccess, onError){
			requisicaoParametros = {
				url: "/GerenciadorDeFavoritos/servicos/arquivo/pasta/" + id
				,
				method: "GET"
				,
				success: onSuccess
				,
				error: onError
			}
			executarRequisicao(requisicaoParametros);
		}
		,
		adicionarPasta: function(){
			
		}
		,
		adicionarPasta: function(){
			
		}
		,
		removerPasta: function(){
			
		}
		//...
	}
	
	var executarRequisicao = function(options){
		$.ajax(options);
	}
	
}
var mapper = new Mapper();

