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
		buscarFavorito: function(id, onSuccess, onError){
			requisicaoParametros = {
				url: "/GerenciadorDeFavoritos/servicos/arquivo/favorito/" + id
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
		adicionarPasta: function(pasta, onSuccess, onError){
			requisicaoParametros = {
				url: "/GerenciadorDeFavoritos/servicos/arquivo/pasta"
				,
				method: "POST"
				,
				data:{
					pasta: JSON.stringify(pasta)
				}
				,
				success: onSuccess
				,
				error: onError
			}
			executarRequisicao(requisicaoParametros);
		}
		,
		alterarPasta: function(pasta, onSuccess, onError){
			requisicaoParametros = {
				url: "/GerenciadorDeFavoritos/servicos/arquivo/pasta"
				,
				method: "PUT"
				,
				data:{
					pasta: JSON.stringify(pasta)
				}
				,
				success: onSuccess
				,
				error: onError
			}
			executarRequisicao(requisicaoParametros);
		}
		,
		removerPasta: function(id, onSuccess, onError){
			requisicaoParametros = {
				url: "/GerenciadorDeFavoritos/servicos/arquivo/pasta"
				,
				method: "DELETE"
				,
				data:{
					id: id
				}
				,
				success: onSuccess
				,
				error: onError
			}
			executarRequisicao(requisicaoParametros);
		}
		,
		adicionarFavorito: function(favorito, onSuccess, onError){
			requisicaoParametros = {
				url: "/GerenciadorDeFavoritos/servicos/arquivo/favorito"
				,
				method: "POST"
				,
				data:{
					favorito: JSON.stringify(favorito)
				}
				,
				success: onSuccess
				,
				error: onError
			}
			executarRequisicao(requisicaoParametros);
		}
		,
		alterarFavorito: function(favorito, onSuccess, onError){
			requisicaoParametros = {
				url: "/GerenciadorDeFavoritos/servicos/arquivo/favorito"
				,
				method: "PUT"
				,
				data:{
					favorito: JSON.stringify(favorito)
				}
				,
				success: onSuccess
				,
				error: onError
			}
			executarRequisicao(requisicaoParametros);
		}
		,
		removerFavorito: function(id, onSuccess, onError){
			requisicaoParametros = {
				url: "/GerenciadorDeFavoritos/servicos/arquivo/favorito"
				,
				method: "DELETE"
				,
				data:{
					id: id
				}
				,
				success: onSuccess
				,
				error: onError
			}
			executarRequisicao(requisicaoParametros);
		}
		,
		atualizarAcesso: function(id, onSuccess, onError){
			requisicaoParametros = {
				url: "/GerenciadorDeFavoritos/servicos/arquivo/favorito/atualizarAcesso"
				,
				method: "PUT"
				,
				data:{
					id: id
				}
				,
				success: onSuccess
				,
				error: onError
			}
			executarRequisicao(requisicaoParametros);
		}
		,
		recemAcessados: function(onSuccess, onError){
			requisicaoParametros = {
				url: "/GerenciadorDeFavoritos/servicos/arquivo/favorito/recemAcessados"
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
		maisAcessados: function(onSuccess, onError){
			requisicaoParametros = {
				url: "/GerenciadorDeFavoritos/servicos/arquivo/favorito/maisAcessados"
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
		recemAdicionados: function(onSuccess, onError){
			requisicaoParametros = {
				url: "/GerenciadorDeFavoritos/servicos/arquivo/favorito/recemAdicionados"
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
		buscarHierarquia: function(onSuccess, onError){
			requisicaoParametros = {
				url: "/GerenciadorDeFavoritos/servicos/arquivo/pasta/hierarquia"
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
		uploadImagemFavorito: function(form, onSuccess, onError){
			requisicaoParametros = {
				url: "/GerenciadorDeFavoritos/servicos/arquivo/favorito/img"
				,
				type: "POST"
				,	
				contentType: false,       // The content type used when sending data to the server.
				cache: false,             // To unable request pages to be cached
				processData:false,        // To send DOMDocument or non processed data file it is set to false
				data: form
				,
				success: onSuccess
				,
				error: onError
			}
			executarRequisicao(requisicaoParametros);
		}
		,
		uploadImagemPasta: function(form, onSuccess, onError){
			requisicaoParametros = {
				url: "/GerenciadorDeFavoritos/servicos/arquivo/pasta/img"
				,
				type: "POST"
				,	
				contentType: false,       // The content type used when sending data to the server.
				cache: false,             // To unable request pages to be cached
				processData:false,        // To send DOMDocument or non processed data file it is set to false
				data: form
				,
				success: onSuccess
				,
				error: onError
			}
			executarRequisicao(requisicaoParametros);
		}	
		,
		adicionarTagPasta: function(idPasta, tagNome, onSuccess, onError){
			requisicaoParametros = {
				url: "/GerenciadorDeFavoritos/servicos/arquivo/pasta/tag"
				,
				method: "POST"
				,
				data:{
					idPasta: idPasta,
					tagNome: tagNome
				}
				,
				success: onSuccess
				,
				error: onError
			}
			executarRequisicao(requisicaoParametros);
		}
		,
		removerTagPasta: function(idPasta, idTag, onSuccess, onError){
			requisicaoParametros = {
				url: "/GerenciadorDeFavoritos/servicos/arquivo/pasta/tag"
				,
				method: "DELETE"
				,
				data:{
					idPasta: idPasta,
					idTag: idTag
				}
				,
				success: onSuccess
				,
				error: onError
			}
			executarRequisicao(requisicaoParametros);
		}
		,
		adicionarTagFavorito: function(idFavorito, tagNome, onSuccess, onError){
			requisicaoParametros = {
				url: "/GerenciadorDeFavoritos/servicos/arquivo/favorito/tag"
				,
				method: "POST"
				,
				data:{
					idFavorito: idFavorito,
					tagNome: tagNome
				}
				,
				success: onSuccess
				,
				error: onError
			}
			executarRequisicao(requisicaoParametros);
		}
		,
		removerTagFavorito: function(idFavorito, idTag, onSuccess, onError){
			requisicaoParametros = {
				url: "/GerenciadorDeFavoritos/servicos/arquivo/favorito/tag"
				,
				method: "DELETE"
				,
				data:{
					idFavorito: idFavorito,
					idTag: idTag
				}
				,
				success: onSuccess
				,
				error: onError
			}
			executarRequisicao(requisicaoParametros);
		}
		,
		pesquisa: function(valorPesquisa, onSuccess, onError){
			requisicaoParametros = {
				url: "/GerenciadorDeFavoritos/servicos/arquivo/pesquisar/" + valorPesquisa
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
		logOut: function(onSuccess, onError){
			requisicaoParametros = {
				url: "/GerenciadorDeFavoritos/j_spring_security_logout"
				,
				method: "POST"
				,
				success: onSuccess
				,
				error: onError
			}
			executarRequisicao(requisicaoParametros);
		}
		,
		obterUsuario: function(onSuccess, onError){
			requisicaoParametros = {
				url: "/GerenciadorDeFavoritos/servicos/usuario/obterNome"
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
		adicionarUsuario: function(usuario, onSuccess, onError){
			requisicaoParametros = {
				url: "/GerenciadorDeFavoritos/servicos/usuario/cadastro"
				,
				method: "POST"
				,
				data:{
					usuario: JSON.stringify(usuario)
				}
				,
				success: onSuccess
				,
				error: onError
			}
			executarRequisicao(requisicaoParametros);
		}
	}
	
	var executarRequisicao = function(options){
		$.ajax(options);
	}
	
}
var mapper = new Mapper();

