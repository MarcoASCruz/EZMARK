var GerenciadorServicos = (function(){
	var instancia;
	
	function iniciarServicos(){
		function serviceShowError(data) {
			var error = data.responseJSON;
			console.log(data);
			Element.ToastError(error.content)
			console.log(error);
		}
		
		return {
			buscarArquivos: function(idPasta, onSuccess){
				mapper.services.buscarArquivos(
					idPasta
					,
					onSuccess
					,
					serviceShowError
				)
			}
			,
			buscarFavorito: function(idFavorito, onSuccess) {
				mapper.services.buscarArquivos(
					idFavorito
					,
					onSuccess
					,
					serviceShowError
				)
			}
			,
			adicionarPasta: function(pasta, onSuccess) {
				mapper.services.adicionarPasta(
					pasta
					,
					onSuccess
					,
					serviceShowError
				)
			}
			,
			alterarPasta: function(pasta, onSuccess) {
				mapper.services.alterarPasta(
					pasta
					,
					onSuccess
					,
					serviceShowError
				)
			}
			,
			removerPasta: function(id, onSuccess) {
				mapper.services.removerPasta(
					id
					,
					onSuccess
					,
					serviceShowError
				)
			}
			,
			adicionarTagPasta: function(idPasta, tagNome, onSuccess) {
				mapper.services.adicionarTagPasta(
					idPasta
					,
					tagNome
					,
					onSuccess
					,
					serviceShowError
				)
			}
			,
			removerTagPasta: function(idPasta, idTag, onSuccess) {
				mapper.services.removerTagPasta(
					idPasta
					,
					idTag
					,
					onSuccess
					,
					serviceShowError
				)
			}
			,
			adicionarFavorito: function(favorito, onSuccess) {
				mapper.services.adicionarFavorito(
					favorito
					,
					onSuccess
					,
					serviceShowError
				)
			}
			,
			alterarFavorito: function(favorito, onSuccess) {
				mapper.services.alterarFavorito(
					favorito
					,
					onSuccess
					,
					serviceShowError
				)
			}
			,
			removerFavorito: function(id, onSuccess) {
				mapper.services.removerFavorito(
					id
					,
					onSuccess
					,
					serviceShowError
				)
			}
			,
			removerArquivos: function(arquivos, onSuccess) {
				mapper.services.removerArquivos(
					arquivos
					,
					onSuccess
					,
					serviceShowError
				)
			}
			,
			adicionarTagFavorito: function(idFavorito, tagNome, onSuccess) {
				mapper.services.adicionarTagFavorito(
					idFavorito
					,
					tagNome
					,
					onSuccess
					,
					serviceShowError
				)
			}
			,
			removerTagFavorito: function(idFavorito, idTag, onSuccess) {
				mapper.services.removerTagFavorito(
					idFavorito
					,
					idTag
					,
					onSuccess
					,
					serviceShowError
				)
			}
			,
			atualizarAcesso: function(id, onSuccess) {
				mapper.services.atualizarAcesso(
					id
					,
					onSuccess
					,
					serviceShowError
				)
			}
			,
			buscarAcessoRapido: function(onSuccess){
				mapper.services.buscarAcessoRapido(
					onSuccess
					,
					serviceShowError
				)
			}
			,
			adicionarAcessoRapido: function(id, onSuccess){
				mapper.services.adicionarAcessoRapido(
					id
					,
					onSuccess
					,
					serviceShowError
				)
			}
			,
			removerAcessoRapido: function(id, onSuccess){
				mapper.services.removerAcessoRapido(
					id
					,
					onSuccess
					,
					serviceShowError
				)
			}
			,
			recemAcessados: function(onSuccess) {
				mapper.services.recemAcessados(
					onSuccess
					,
					serviceShowError
				)
			}
			,
			maisAcessados: function(onSuccess) {
				mapper.services.maisAcessados(
					onSuccess
					,
					serviceShowError
				)
			}
			,
			recemAdicionados: function(onSuccess) {
				mapper.services.recemAdicionados(
					onSuccess
					,
					serviceShowError
				)
			}
			,
			buscarHierarquiaDePastas: function(onSuccess) {
				mapper.services.buscarHierarquia(
					onSuccess
					,
					serviceShowError
				)
			}
			,
			//form, precisa ter a imagem e o id do favorito destino
			uploadImagemFavorito: function(form, onSuccess) {
				mapper.services.uploadImagemFavorito(
					form
					,
					onSuccess
					,
					serviceShowError
				)
			}
			,
			//form, precisa ter a imagem e o id do pasta destino
			uploadImagemPasta: function(form, onSuccess) {
				mapper.services.uploadImagemPasta(
					form
					,
					onSuccess
					,
					serviceShowError
				)
			}
			,
			pesquisa: function(form, onSuccess) {
				mapper.services.pesquisa(
					form
					,
					onSuccess
					,
					serviceShowError
				)
			}
			,
			logOut: function(onSuccess) {
				mapper.services.logOut(
					onSuccess
					,
					serviceShowError
				)
			}
			,
			obterUsuario: function(onSuccess) {
				mapper.services.obterUsuario(
					onSuccess
					,
					serviceShowError
				)
			}
			,
			adicionarUsuario: function(usuario, onSuccess, onError) {
				mapper.services.adicionarUsuario(
					usuario
					,
					onSuccess
					, 
					onError
				)
			}
			,
			buscarHome: function(onSuccess) {
				mapper.services.buscarHome(
					onSuccess
					,
					serviceShowError
				)
			}
			,
			compartilhar: function(idPasta, onSuccess) {
				mapper.services.compartilhar(
					idPasta
					,
					onSuccess
					,
					serviceShowError
				)
			}
			,
			moverArquivos: function(idPastaDestino, arquivos, onSuccess) {
				mapper.services.moverArquivos(
					idPastaDestino
					,
					arquivos
					,
					onSuccess
					,
					serviceShowError
				)
			}
			,
			autoOrganizar: function(idPasta, onSuccess, onError) {
				mapper.services.autoOrganizar(
					idPasta
					,
					onSuccess
					,
					function(data){
						onError();
						serviceShowError(data);
					}
				)
			}
		}
	}
	
	return {
		obterInstancia: function(){
			if (!instancia){
				instancia = iniciarServicos();
			}
			return instancia;
		}
	}
})();