var servicos = {
	buscarArquivos: function(idPasta, onSuccess){
		mapper.services.buscarArquivos(
			idPasta
			,
			onSuccess
			,
			function(data){
				console.log(data.responseJSON);
				var modal = Element.ConfirmModal("erro", data.responseText, function(){});
				modal.show();
			}
		)
	}
}