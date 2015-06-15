var ObjectHtml = function(){
	this.element = undefined;
	this.getElement = function () {
		if (this.element == undefined) {
			this.element = this.createElement();
		}
		return this.element;
	}
	this.createElement = function () {
		return $('<div>');
	}
}


var Materialize = function () {

	var self = this;
		
	this.Toast = function(mensagem){
		 dialog = Materialize.toast(mensagem);
		 return dialog;
	}
	
	
	this.Modal = function(id){
		var modal = new ObjectHtml();
		var id = this.id;
		var tituloModal = 'titulo teste';
        var idIn1 = '001';
        var idIn2 = '002';
        var idIn3 = '003';
			
		modal.createElement = function(){
			
		var janela = $('<div id="' + id + '" class="modal modal-fixed-footer">');
		var conteudo = $('<div class="modal-content">');
		var titulo = $('<h4>');
		var formulario = $('<form>');
		var conteudo_formulario = $('<div class="row">');
		var nome = $('<div class="input-field m12 s6">' +
                '<i class="mdi-action-stars prefix"></i>'+
                '<input id="' + idIn1 + '" type="text" class="validate">'+
                '<label for="'+ idIn1 + '">Nome</label>'+
            	'</div>');
		var url = $('<div class="input-field m12 s6">' +
                '<i class="mdi-action-stars prefix"></i>'+
                '<input id="' + idIn2 + '" type="text" class="validate">'+
                '<label for="'+ idIn2 + '">Nome</label>'+
				'</div>');
		var descr = $('<div class="input-field m12 s6">' +
                '<i class="mdi-action-stars prefix"></i>'+
                '<input id="' + idIn3 + '" type="text" class="validate">'+
                '<label for="'+ idIn3 + '">Nome</label>'+
            	'</div>');
		
		
//		var tags = $('<div class="input-field col s12">'+
//                  '<input id="tags" type="text" class="validate">'+
//                '</div>');

		janela.append (conteudo);
		conteudo.append (titulo);
		titulo.append (tituloModal);
		conteudo.append (formulario);
		formulario.append (conteudo_formulario);
		conteudo_formulario.append (nome);
		conteudo_formulario.append (url);
		conteudo_formulario.append (descr);
			
		return janela;	
		}		
		return modal;
	}

	this.FavAcessoRapido = function(){
        //icon, nome, descricao, id
		var favAR = new ObjectHtml();
		
//		var iconUrl = icon;
//        var nomeFavorito = nome;
//        var descricao = this.descricao;
//        var id = this.id;
		
		
        var iconUrl = 'https://cdn2.iconfinder.com/data/icons/flatte-social-networks-part-2/80/12_-_Star-512.png';
        var nomeFavorito = 'Teste de nome';
        var descricao = 'dasodiadiadsioaoisdoiaodaosd oiadoia jidjas';
        var id = '1';
        
        favAR.createElement = function () {
        var bloco = $('<div class="favorito col s4 m1 cyan darken-2 z-depth-1 drag" style="margin: 5px 0.3em; position: relative; left: 0px; top: 0px;">');
        var div_icon= $('<div class="col s12 pdzero" style="margin-top: 5px;">');
        var icon = $('<img class="responsive-img" src="' + iconUrl +  '">');
        var div_detalhes = $('<div class="col s12 cyan-text text-lighten-5 pdzero">');
        var detalhes_favorito = $('<div class="col s12 pdzero">');
        var titulo_favorito = $('<div class="col s10 pdzero titulo-favorito-destaque">');
        var div_dropdown = $('<div class="col s2 pdzero">');
        var btDropdown = $('<a class="dropdown-button cyan-text text-lighten-5" href="#" data-activates="dropdown' + id +'">');
        var btDropdown_icon = $('<i class="mdi-navigation-more-vert"></i>');
        var dropdown_content = $('<ul id="dropdown' + id +'" class="dropdown-content">');
        var dropdownSelecionar = $('<li><a>Selecionar</a></li>');
        var dropdownEditar = $('<li><a>Editar</a></li>');
        var dropdownExcluir = $('<li><a>Excluir</a></li>');
        
        bloco.append (div_icon);
        div_icon.append (icon);
        bloco.append (div_detalhes);
        div_detalhes.append (detalhes_favorito);
        detalhes_favorito.append (titulo_favorito);
        titulo_favorito.append (nomeFavorito);
        detalhes_favorito.append (div_dropdown);
        div_dropdown.append(btDropdown);
        btDropdown.append (btDropdown_icon);
        div_dropdown.append(dropdown_content);
        dropdown_content.append (dropdownSelecionar);
        dropdown_content.append (dropdownEditar);
        dropdown_content.append (dropdownExcluir);
        
        return bloco;
        }
        return favAR;
	}
	
	this.Bloco = function (icon, nome,descricao, tipo, iconTipo, numEstrela, id) {
		
        var bloco = new ObjectHtml();

//        var iconUrl = icon;
//        var nomeFavorito = nome;
//        var descricao = this.descricao;
//        var TomBloco = tipo//Caracteriza a Cor do Favorito, da Pasta ou da Pasta compartilhada os números sao respectivamente 2,3 e 4
//        var iconTipoBloco = iconTipo// Ícone que define caracteriza favorito, pasta ou pasta compartilhada
        
        var iconUrl = 'https://cdn2.iconfinder.com/data/icons/flatte-social-networks-part-2/80/12_-_Star-512.png';
        var nomeFavorito = nome;
        var TomBloco = tipo//Caracteriza a Cor do Favorito, da Pasta ou da Pasta compartilhada os números sao respectivamente 2,3 e 4
        var iconTipoBloco = iconTipo// Ícone que define caracteriza favorito, pasta ou pasta compartilhada

        var iconFav = $('<i class="mdi-action-stars yellow-text text-accent-2"></i>');
        var iconFold = $('<i class="mdi-file-folder yellow-text text-darken-3"></i>');
        var iconShar = $('<i class="mdi-file-folder-shared yellow-text text-darken-3"></i>');
        
		bloco.createElement = function () {
		var card = $('<div class="col s12 m3 pasta-fechada">');
		if (TomBloco == '2'){ 
			card.addClass = 'drag';
        }
		var painel = $('<div class="card-panel card-complement cyan darken-' + TomBloco + '">');
		var conteudo = $('<div class="card-content white-text">');
		var div_icon = $('<div class=" col s4 pdzero">');
		var icon = $('<img class="responsive-img" src="' + iconUrl +  '">');
		var div_dadosFavorito = $('<div class="col s8 cyan-text text-lighten-5 pdzero">');
		var div_tituloFavorito = $('<div class="col s12 titulo-favorito">');
		var titulo_favorito = $('<div class="col s10 truncate pdzero">');
		var tituloFavorito = $('<i class="' + iconTipoBloco + '"></i>');
		var div_opcoes = $('<div class="col s2">');
		var btOpcoes = $('<a class="dropdown-button cyan-text text-lighten-5" data-activates="dropdown' + id +'">');
        var icon_btOpcoes = $('<i class="mdi-navigation-more-vert"></i>');
        var dropdown_content = $('<ul id="dropdown' + id + '" class="dropdown-content">');
        var dropdownSelecionar = $('<li><a>Selecionar</a></li>');
        var dropdownEditar = $('<li><a>Editar</a></li>');
        var dropdownExcluir = $('<li><a>Excluir</a></li>');
		
		var div_estrelas = $('<div class="col s12">');
		for (i=0; i<numEstrela; i++){
		var estrela = $('<i class="mdi-action-grade"></i>' + estrela);
		div_estrelas.append (estrela);

		}
		var div_descricao = $('<div class="col s12 truncate">');
		var div_tags = $('<div class="col s12 truncate">');
		var icon_tag = $('<i class="mdi-maps-local-offer"></i>');
		
		
		card.append (painel);
		painel.append (conteudo);
		conteudo.append (div_icon);
		div_icon.append (icon);
		conteudo.append (div_dadosFavorito);
		div_dadosFavorito.append (div_tituloFavorito);
		div_tituloFavorito.append (titulo_favorito);
		titulo_favorito.append (tituloFavorito);
        titulo_favorito.append (nomeFavorito);
		div_tituloFavorito.append (div_opcoes);
		div_opcoes.append (btOpcoes);
		div_opcoes.append (dropdown_content);
		btOpcoes.append (icon_btOpcoes);
        dropdown_content.append (dropdownSelecionar);
        dropdown_content.append (dropdownEditar);
        dropdown_content.append (dropdownExcluir);
		div_dadosFavorito.append (div_estrelas);
		
		div_dadosFavorito.append (div_descricao);
		div_descricao.append(descricao);
		div_dadosFavorito.append (div_tags);
		div_tags.append (icon_tag); 
			return card;
        }
        return bloco;
    }

	var self = this;
		
	this.Toast = function(mensagem){
		 dialog = Materialize.toast(mensagem);
		 return dialog;
	}
	
	
	this.Modal = function(id){
		var modal = new ObjectHtml();
		var id = this.id;
		var tituloModal = 'titulo teste';
        var idIn1 = '001';
        var idIn2 = '002';
        var idIn3 = '003';
			
		modal.createElement = function(){
			
		var janela = $('<div id="' + id + '" class="modal modal-fixed-footer">');
		var conteudo = $('<div class="modal-content">');
		var titulo = $('<h4>');
		var formulario = $('<form>');
		var conteudo_formulario = $('<div class="row">');
		var nome = $('<div class="input-field m12 s6">' +
                '<i class="mdi-action-stars prefix"></i>'+
                '<input id="' + idIn1 + '" type="text" class="validate">'+
                '<label for="'+ idIn1 + '">Nome</label>'+
            	'</div>');
		var url = $('<div class="input-field m12 s6">' +
                '<i class="mdi-action-stars prefix"></i>'+
                '<input id="' + idIn2 + '" type="text" class="validate">'+
                '<label for="'+ idIn2 + '">Nome</label>'+
				'</div>');
		var descr = $('<div class="input-field m12 s6">' +
                '<i class="mdi-action-stars prefix"></i>'+
                '<input id="' + idIn3 + '" type="text" class="validate">'+
                '<label for="'+ idIn3 + '">Nome</label>'+
            	'</div>');
		
		
//		var tags = $('<div class="input-field col s12">'+
//                  '<input id="tags" type="text" class="validate">'+
//                '</div>');

		janela.append (conteudo);
		conteudo.append (titulo);
		titulo.append (tituloModal);
		conteudo.append (formulario);
		formulario.append (conteudo_formulario);
		conteudo_formulario.append (nome);
		conteudo_formulario.append (url);
		conteudo_formulario.append (descr);
			
		return janela;	
		}		
		return modal;
	}

	this.FavAcessoRapido = function(){
        //icon, nome, descricao, id
		var favAR = new ObjectHtml();
		
//		var iconUrl = icon;
//        var nomeFavorito = nome;
//        var descricao = this.descricao;
//        var id = this.id;
		
		
        var iconUrl = 'https://cdn2.iconfinder.com/data/icons/flatte-social-networks-part-2/80/12_-_Star-512.png';
        var nomeFavorito = 'Teste de nome';
        var descricao = 'dasodiadiadsioaoisdoiaodaosd oiadoia jidjas';
        var id = '1';
        
        favAR.createElement = function () {
        var bloco = $('<div class="favorito col s4 m1 cyan darken-2 z-depth-1 drag" style="margin: 5px 0.3em; position: relative; left: 0px; top: 0px;">');
        var div_icon= $('<div class="col s12 pdzero" style="margin-top: 5px;">');
        var icon = $('<img class="responsive-img" src="' + iconUrl +  '">');
        var div_detalhes = $('<div class="col s12 cyan-text text-lighten-5 pdzero">');
        var detalhes_favorito = $('<div class="col s12 pdzero">');
        var titulo_favorito = $('<div class="col s10 pdzero titulo-favorito-destaque">');
        var div_dropdown = $('<div class="col s2 pdzero">');
        var btDropdown = $('<a class="dropdown-button cyan-text text-lighten-5" href="#" data-activates="dropdown' + id +'">');
        var btDropdown_icon = $('<i class="mdi-navigation-more-vert"></i>');
        var dropdown_content = $('<ul id="dropdown' + id +'" class="dropdown-content">');
        var dropdownSelecionar = $('<li><a>Selecionar</a></li>');
        var dropdownEditar = $('<li><a>Editar</a></li>');
        var dropdownExcluir = $('<li><a>Excluir</a></li>');
        
        bloco.append (div_icon);
        div_icon.append (icon);
        bloco.append (div_detalhes);
        div_detalhes.append (detalhes_favorito);
        detalhes_favorito.append (titulo_favorito);
        titulo_favorito.append (nomeFavorito);
        detalhes_favorito.append (div_dropdown);
        div_dropdown.append(btDropdown);
        btDropdown.append (btDropdown_icon);
        div_dropdown.append(dropdown_content);
        dropdown_content.append (dropdownSelecionar);
        dropdown_content.append (dropdownEditar);
        dropdown_content.append (dropdownExcluir);
        
        return bloco;
        }
        return favAR;
	}
	
	this.Bloco = function (icon, nome,descricao, tipo, iconTipo, numEstrela, id) {
		
        var bloco = new ObjectHtml();

//        var iconUrl = icon;
//        var nomeFavorito = nome;
//        var descricao = this.descricao;
//        var TomBloco = tipo//Caracteriza a Cor do Favorito, da Pasta ou da Pasta compartilhada os números sao respectivamente 2,3 e 4
//        var iconTipoBloco = iconTipo// Ícone que define caracteriza favorito, pasta ou pasta compartilhada
        
        var iconUrl = 'https://cdn2.iconfinder.com/data/icons/flatte-social-networks-part-2/80/12_-_Star-512.png';
        var nomeFavorito = nome;
        var TomBloco = tipo//Caracteriza a Cor do Favorito, da Pasta ou da Pasta compartilhada os números sao respectivamente 2,3 e 4
        var iconTipoBloco = iconTipo// Ícone que define caracteriza favorito, pasta ou pasta compartilhada

        var iconFav = $('<i class="mdi-action-stars yellow-text text-accent-2"></i>');
        var iconFold = $('<i class="mdi-file-folder yellow-text text-darken-3"></i>');
        var iconShar = $('<i class="mdi-file-folder-shared yellow-text text-darken-3"></i>');
        
		bloco.createElement = function () {
		var card = $('<div class="col s12 m3 pasta-fechada">');
		if (TomBloco == '2'){ 
			card.addClass = 'drag';
        }
		var painel = $('<div class="card-panel card-complement cyan darken-' + TomBloco + '">');
		var conteudo = $('<div class="card-content white-text">');
		var div_icon = $('<div class=" col s4 pdzero">');
		var icon = $('<img class="responsive-img" src="' + iconUrl +  '">');
		var div_dadosFavorito = $('<div class="col s8 cyan-text text-lighten-5 pdzero">');
		var div_tituloFavorito = $('<div class="col s12 titulo-favorito">');
		var titulo_favorito = $('<div class="col s10 truncate pdzero">');
		var tituloFavorito = $('<i class="' + iconTipoBloco + '"></i>');
		var div_opcoes = $('<div class="col s2">');
		var btOpcoes = $('<a class="dropdown-button cyan-text text-lighten-5" data-activates="dropdown' + id +'">');
        var icon_btOpcoes = $('<i class="mdi-navigation-more-vert"></i>');
        var dropdown_content = $('<ul id="dropdown' + id + '" class="dropdown-content">');
        var dropdownSelecionar = $('<li><a>Selecionar</a></li>');
        var dropdownEditar = $('<li><a>Editar</a></li>');
        var dropdownExcluir = $('<li><a>Excluir</a></li>');
		
		var div_estrelas = $('<div class="col s12">');
		for (i=0; i<numEstrela; i++){
		var estrela = $('<i class="mdi-action-grade"></i>' + estrela);
		div_estrelas.append (estrela);

		}
		var div_descricao = $('<div class="col s12 truncate">');
		var div_tags = $('<div class="col s12 truncate">');
		var icon_tag = $('<i class="mdi-maps-local-offer"></i>');
		
		
		card.append (painel);
		painel.append (conteudo);
		conteudo.append (div_icon);
		div_icon.append (icon);
		conteudo.append (div_dadosFavorito);
		div_dadosFavorito.append (div_tituloFavorito);
		div_tituloFavorito.append (titulo_favorito);
		titulo_favorito.append (tituloFavorito);
        titulo_favorito.append (nomeFavorito);
		div_tituloFavorito.append (div_opcoes);
		div_opcoes.append (btOpcoes);
		div_opcoes.append (dropdown_content);
		btOpcoes.append (icon_btOpcoes);
        dropdown_content.append (dropdownSelecionar);
        dropdown_content.append (dropdownEditar);
        dropdown_content.append (dropdownExcluir);
		div_dadosFavorito.append (div_estrelas);
		
		div_dadosFavorito.append (div_descricao);
		div_descricao.append(descricao);
		div_dadosFavorito.append (div_tags);
		div_tags.append (icon_tag); 
			return card;
        }
        return bloco;
    }
};
	var self = this;
		
	this.Toast = function(mensagem){
		 dialog = Materialize.toast(mensagem);
		 return dialog;
	}
	
	
	this.Modal = function(id){
		var modal = new ObjectHtml();
		var id = this.id;
		var tituloModal = 'titulo teste';
        var idIn1 = '001';
        var idIn2 = '002';
        var idIn3 = '003';
			
		modal.createElement = function(){
			
		var janela = $('<div id="' + id + '" class="modal modal-fixed-footer">');
		var conteudo = $('<div class="modal-content">');
		var titulo = $('<h4>');
		var formulario = $('<form>');
		var conteudo_formulario = $('<div class="row">');
		var nome = $('<div class="input-field m12 s6">' +
                '<i class="mdi-action-stars prefix"></i>'+
                '<input id="' + idIn1 + '" type="text" class="validate">'+
                '<label for="'+ idIn1 + '">Nome</label>'+
            	'</div>');
		var url = $('<div class="input-field m12 s6">' +
                '<i class="mdi-action-stars prefix"></i>'+
                '<input id="' + idIn2 + '" type="text" class="validate">'+
                '<label for="'+ idIn2 + '">Nome</label>'+
				'</div>');
		var descr = $('<div class="input-field m12 s6">' +
                '<i class="mdi-action-stars prefix"></i>'+
                '<input id="' + idIn3 + '" type="text" class="validate">'+
                '<label for="'+ idIn3 + '">Nome</label>'+
            	'</div>');
		
		
//		var tags = $('<div class="input-field col s12">'+
//                  '<input id="tags" type="text" class="validate">'+
//                '</div>');

		janela.append (conteudo);
		conteudo.append (titulo);
		titulo.append (tituloModal);
		conteudo.append (formulario);
		formulario.append (conteudo_formulario);
		conteudo_formulario.append (nome);
		conteudo_formulario.append (url);
		conteudo_formulario.append (descr);
			
		return janela;	
		}		
		return modal;
	}

	this.FavAcessoRapido = function(){
        //icon, nome, descricao, id
		var favAR = new ObjectHtml();
		
//		var iconUrl = icon;
//        var nomeFavorito = nome;
//        var descricao = this.descricao;
//        var id = this.id;
		
		
        var iconUrl = 'https://cdn2.iconfinder.com/data/icons/flatte-social-networks-part-2/80/12_-_Star-512.png';
        var nomeFavorito = 'Teste de nome';
        var descricao = 'dasodiadiadsioaoisdoiaodaosd oiadoia jidjas';
        var id = '1';
        
        favAR.createElement = function () {
        var bloco = $('<div class="favorito col s4 m1 cyan darken-2 z-depth-1 drag" style="margin: 5px 0.3em; position: relative; left: 0px; top: 0px;">');
        var div_icon= $('<div class="col s12 pdzero" style="margin-top: 5px;">');
        var icon = $('<img class="responsive-img" src="' + iconUrl +  '">');
        var div_detalhes = $('<div class="col s12 cyan-text text-lighten-5 pdzero">');
        var detalhes_favorito = $('<div class="col s12 pdzero">');
        var titulo_favorito = $('<div class="col s10 pdzero titulo-favorito-destaque">');
        var div_dropdown = $('<div class="col s2 pdzero">');
        var btDropdown = $('<a class="dropdown-button cyan-text text-lighten-5" href="#" data-activates="dropdown' + id +'">');
        var btDropdown_icon = $('<i class="mdi-navigation-more-vert"></i>');
        var dropdown_content = $('<ul id="dropdown' + id +'" class="dropdown-content">');
        var dropdownSelecionar = $('<li><a>Selecionar</a></li>');
        var dropdownEditar = $('<li><a>Editar</a></li>');
        var dropdownExcluir = $('<li><a>Excluir</a></li>');
        
        bloco.append (div_icon);
        div_icon.append (icon);
        bloco.append (div_detalhes);
        div_detalhes.append (detalhes_favorito);
        detalhes_favorito.append (titulo_favorito);
        titulo_favorito.append (nomeFavorito);
        detalhes_favorito.append (div_dropdown);
        div_dropdown.append(btDropdown);
        btDropdown.append (btDropdown_icon);
        div_dropdown.append(dropdown_content);
        dropdown_content.append (dropdownSelecionar);
        dropdown_content.append (dropdownEditar);
        dropdown_content.append (dropdownExcluir);
        
        return bloco;
        }
        return favAR;
	}
	
	this.Bloco = function (icon, nome,descricao, tipo, iconTipo, numEstrela, id) {
		
        var bloco = new ObjectHtml();

//        var iconUrl = icon;
//        var nomeFavorito = nome;
//        var descricao = this.descricao;
//        var TomBloco = tipo//Caracteriza a Cor do Favorito, da Pasta ou da Pasta compartilhada os números sao respectivamente 2,3 e 4
//        var iconTipoBloco = iconTipo// Ícone que define caracteriza favorito, pasta ou pasta compartilhada
        
        var iconUrl = 'https://cdn2.iconfinder.com/data/icons/flatte-social-networks-part-2/80/12_-_Star-512.png';
        var nomeFavorito = nome;
        var TomBloco = tipo//Caracteriza a Cor do Favorito, da Pasta ou da Pasta compartilhada os números sao respectivamente 2,3 e 4
        var iconTipoBloco = iconTipo// Ícone que define caracteriza favorito, pasta ou pasta compartilhada

        var iconFav = $('<i class="mdi-action-stars yellow-text text-accent-2"></i>');
        var iconFold = $('<i class="mdi-file-folder yellow-text text-darken-3"></i>');
        var iconShar = $('<i class="mdi-file-folder-shared yellow-text text-darken-3"></i>');
        
		bloco.createElement = function () {
		var card = $('<div class="col s12 m3 pasta-fechada">');
		if (TomBloco == '2'){ 
			card.addClass = 'drag';
        }
		var painel = $('<div class="card-panel card-complement cyan darken-' + TomBloco + '">');
		var conteudo = $('<div class="card-content white-text">');
		var div_icon = $('<div class=" col s4 pdzero">');
		var icon = $('<img class="responsive-img" src="' + iconUrl +  '">');
		var div_dadosFavorito = $('<div class="col s8 cyan-text text-lighten-5 pdzero">');
		var div_tituloFavorito = $('<div class="col s12 titulo-favorito">');
		var titulo_favorito = $('<div class="col s10 truncate pdzero">');
		var tituloFavorito = $('<i class="' + iconTipoBloco + '"></i>');
		var div_opcoes = $('<div class="col s2">');
		var btOpcoes = $('<a class="dropdown-button cyan-text text-lighten-5" data-activates="dropdown' + id +'">');
        var icon_btOpcoes = $('<i class="mdi-navigation-more-vert"></i>');
        var dropdown_content = $('<ul id="dropdown' + id + '" class="dropdown-content">');
        var dropdownSelecionar = $('<li><a>Selecionar</a></li>');
        var dropdownEditar = $('<li><a>Editar</a></li>');
        var dropdownExcluir = $('<li><a>Excluir</a></li>');
		
		var div_estrelas = $('<div class="col s12">');
		for (i=0; i<numEstrela; i++){
		var estrela = $('<i class="mdi-action-grade"></i>' + estrela);
		div_estrelas.append (estrela);

		}
		var div_descricao = $('<div class="col s12 truncate">');
		var div_tags = $('<div class="col s12 truncate">');
		var icon_tag = $('<i class="mdi-maps-local-offer"></i>');
		
		
		card.append (painel);
		painel.append (conteudo);
		conteudo.append (div_icon);
		div_icon.append (icon);
		conteudo.append (div_dadosFavorito);
		div_dadosFavorito.append (div_tituloFavorito);
		div_tituloFavorito.append (titulo_favorito);
		titulo_favorito.append (tituloFavorito);
        titulo_favorito.append (nomeFavorito);
		div_tituloFavorito.append (div_opcoes);
		div_opcoes.append (btOpcoes);
		div_opcoes.append (dropdown_content);
		btOpcoes.append (icon_btOpcoes);
        dropdown_content.append (dropdownSelecionar);
        dropdown_content.append (dropdownEditar);
        dropdown_content.append (dropdownExcluir);
		div_dadosFavorito.append (div_estrelas);
		
		div_dadosFavorito.append (div_descricao);
		div_descricao.append(descricao);
		div_dadosFavorito.append (div_tags);
		div_tags.append (icon_tag); 
			return card;
        }
        return bloco;
    }
	
	var self = this;
		
	this.Toast = function(mensagem){
		 dialog = Materialize.toast(mensagem);
		 return dialog;
	}
	
	
	this.Modal = function(id){
		var modal = new ObjectHtml();
		var id = this.id;
		var tituloModal = 'titulo teste';
        var idIn1 = '001';
        var idIn2 = '002';
        var idIn3 = '003';
			
		modal.createElement = function(){
			
		var janela = $('<div id="' + id + '" class="modal modal-fixed-footer">');
		var conteudo = $('<div class="modal-content">');
		var titulo = $('<h4>');
		var formulario = $('<form>');
		var conteudo_formulario = $('<div class="row">');
		var nome = $('<div class="input-field m12 s6">' +
                '<i class="mdi-action-stars prefix"></i>'+
                '<input id="' + idIn1 + '" type="text" class="validate">'+
                '<label for="'+ idIn1 + '">Nome</label>'+
            	'</div>');
		var url = $('<div class="input-field m12 s6">' +
                '<i class="mdi-action-stars prefix"></i>'+
                '<input id="' + idIn2 + '" type="text" class="validate">'+
                '<label for="'+ idIn2 + '">Nome</label>'+
				'</div>');
		var descr = $('<div class="input-field m12 s6">' +
                '<i class="mdi-action-stars prefix"></i>'+
                '<input id="' + idIn3 + '" type="text" class="validate">'+
                '<label for="'+ idIn3 + '">Nome</label>'+
            	'</div>');
		
		
//		var tags = $('<div class="input-field col s12">'+
//                  '<input id="tags" type="text" class="validate">'+
//                '</div>');

		janela.append (conteudo);
		conteudo.append (titulo);
		titulo.append (tituloModal);
		conteudo.append (formulario);
		formulario.append (conteudo_formulario);
		conteudo_formulario.append (nome);
		conteudo_formulario.append (url);
		conteudo_formulario.append (descr);
			
		return janela;	
		}		
		return modal;
	}

	this.FavAcessoRapido = function(){
        //icon, nome, descricao, id
		var favAR = new ObjectHtml();
		
//		var iconUrl = icon;
//        var nomeFavorito = nome;
//        var descricao = this.descricao;
//        var id = this.id;
		
		
        var iconUrl = 'https://cdn2.iconfinder.com/data/icons/flatte-social-networks-part-2/80/12_-_Star-512.png';
        var nomeFavorito = 'Teste de nome';
        var descricao = 'dasodiadiadsioaoisdoiaodaosd oiadoia jidjas';
        var id = '1';
        
        favAR.createElement = function () {
        var bloco = $('<div class="favorito col s4 m1 cyan darken-2 z-depth-1 drag" style="margin: 5px 0.3em; position: relative; left: 0px; top: 0px;">');
        var div_icon= $('<div class="col s12 pdzero" style="margin-top: 5px;">');
        var icon = $('<img class="responsive-img" src="' + iconUrl +  '">');
        var div_detalhes = $('<div class="col s12 cyan-text text-lighten-5 pdzero">');
        var detalhes_favorito = $('<div class="col s12 pdzero">');
        var titulo_favorito = $('<div class="col s10 pdzero titulo-favorito-destaque">');
        var div_dropdown = $('<div class="col s2 pdzero">');
        var btDropdown = $('<a class="dropdown-button cyan-text text-lighten-5" href="#" data-activates="dropdown' + id +'">');
        var btDropdown_icon = $('<i class="mdi-navigation-more-vert"></i>');
        var dropdown_content = $('<ul id="dropdown' + id +'" class="dropdown-content">');
        var dropdownSelecionar = $('<li><a>Selecionar</a></li>');
        var dropdownEditar = $('<li><a>Editar</a></li>');
        var dropdownExcluir = $('<li><a>Excluir</a></li>');
        
        bloco.append (div_icon);
        div_icon.append (icon);
        bloco.append (div_detalhes);
        div_detalhes.append (detalhes_favorito);
        detalhes_favorito.append (titulo_favorito);
        titulo_favorito.append (nomeFavorito);
        detalhes_favorito.append (div_dropdown);
        div_dropdown.append(btDropdown);
        btDropdown.append (btDropdown_icon);
        div_dropdown.append(dropdown_content);
        dropdown_content.append (dropdownSelecionar);
        dropdown_content.append (dropdownEditar);
        dropdown_content.append (dropdownExcluir);
        
        return bloco;
        }
        return favAR;
	}
	
	this.Favorito = function (icon, nome,descricao, tipo, iconTipo, numEstrela, id) {
		
        var bloco = new ObjectHtml();

//        var iconUrl = icon;
        var nomeFavorito = nome;
//        var TomBloco = tipo//Caracteriza a Cor do Favorito, da Pasta ou da Pasta compartilhada os números sao respectivamente 2,3 e 4
//        var iconTipoBloco = iconTipo// Ícone que define caracteriza favorito, pasta ou pasta compartilhada
<<<<<<< HEAD
        
=======
//        var id = '09';
>>>>>>> branch 'master' of https://github.com/MarcoASCruz/GerenciadorDeFavoritos.git
        var iconUrl = 'https://cdn2.iconfinder.com/data/icons/flatte-social-networks-part-2/80/12_-_Star-512.png';
<<<<<<< HEAD
        var nomeFavorito = nome;
        var TomBloco = tipo//Caracteriza a Cor do Favorito, da Pasta ou da Pasta compartilhada os números sao respectivamente 2,3 e 4
        var iconTipoBloco = iconTipo// Ícone que define caracteriza favorito, pasta ou pasta compartilhada
=======
//        var nomeFavorito = 'Teste de nome';
//        var descricao = 'dasodiadiadsioaoisdoiaodaosd oiadoia jidjas';
//        var TomBloco = '2'//Caracteriza a Cor do Favorito, da Pasta ou da Pasta compartilhada os números sao respectivamente 2,3 e 4
//        var iconTipoBloco = 'mdi-navigation-more-vert'// Ícone que define caracteriza favorito, pasta ou pasta compartilhada
//        var numEstrela = 3;
>>>>>>> branch 'master' of https://github.com/MarcoASCruz/GerenciadorDeFavoritos.git

        var iconFav = $('<i class="mdi-action-stars yellow-text text-accent-2"></i>');
        var iconFold = $('<i class="mdi-file-folder yellow-text text-darken-3"></i>');
        var iconShar = $('<i class="mdi-file-folder-shared yellow-text text-darken-3"></i>');
        
		bloco.createElement = function () {
		var card = $('<div class="col s12 m3 pasta-fechada drag">');
		var painel = $('<div class="card-panel card-complement cyan darken-'2">');
		var conteudo = $('<div class="card-content white-text">');
		var div_icon = $('<div class=" col s4 pdzero">');
		var icon = $('<img class="responsive-img" src="' + iconUrl +  '">');
		var div_dadosFavorito = $('<div class="col s8 cyan-text text-lighten-5 pdzero">');
		var div_tituloFavorito = $('<div class="col s12 titulo-favorito">');
		var titulo_favorito = $('<div class="col s10 truncate pdzero">');
		var tituloFavorito = $('<i class="mdi-action-stars yellow-text text-accent-2"></i>');
		var div_opcoes = $('<div class="col s2">');
		var btOpcoes = $('<a class="dropdown-button cyan-text text-lighten-5" data-activates="dropdown' + id +'">');
        var icon_btOpcoes = $('<i class="mdi-navigation-more-vert"></i>');
        var dropdown_content = $('<ul id="dropdown' + id + '" class="dropdown-content">');
        var dropdownSelecionar = $('<li><a>Selecionar</a></li>');
        var dropdownEditar = $('<li><a>Editar</a></li>');
        var dropdownExcluir = $('<li><a>Excluir</a></li>');
		
		var div_estrelas = $('<div class="col s12">');
		for (i=0; i<numEstrela; i++){
		var estrela = $('<i class="mdi-action-grade"></i>' + estrela);
		div_estrelas.append (estrela);

		}
		var div_descricao = $('<div class="col s12 truncate">');
		var div_tags = $('<div class="col s12 truncate">');
		var icon_tag = $('<i class="mdi-maps-local-offer"></i>');
		
		
		card.append (painel);
		painel.append (conteudo);
		conteudo.append (div_icon);
		div_icon.append (icon);
		conteudo.append (div_dadosFavorito);
		div_dadosFavorito.append (div_tituloFavorito);
		div_tituloFavorito.append (titulo_favorito);
		titulo_favorito.append (tituloFavorito);
        titulo_favorito.append (nomeFavorito);
		div_tituloFavorito.append (div_opcoes);
		div_opcoes.append (btOpcoes);
		div_opcoes.append (dropdown_content);
		btOpcoes.append (icon_btOpcoes);
        dropdown_content.append (dropdownSelecionar);
        dropdown_content.append (dropdownEditar);
        dropdown_content.append (dropdownExcluir);
		div_dadosFavorito.append (div_estrelas);
		
		div_dadosFavorito.append (div_descricao);
		div_descricao.append(descricao);
		div_dadosFavorito.append (div_tags);
		div_tags.append (icon_tag); 
			return card;
        }
        return bloco;
    }
	
	this.Pasta = function (icon, nome,descricao, tipo, iconTipo, numEstrela, id) {
		
        var bloco = new ObjectHtml();

        var iconUrl = icon;
        var nomeFavorito = nome;
//        var TomBloco = tipo//Caracteriza a Cor do Favorito, da Pasta ou da Pasta compartilhada os números sao respectivamente 2,3 e 4
//        var iconTipoBloco = iconTipo// Ícone que define caracteriza favorito, pasta ou pasta compartilhada
        var iconUrl = 'https://cdn2.iconfinder.com/data/icons/flatte-social-networks-part-2/80/12_-_Star-512.png';
//        var nomeFavorito = 'Teste de nome';
//        var descricao = 'dasodiadiadsioaoisdoiaodaosd oiadoia jidjas';
//        var TomBloco = '2'//Caracteriza a Cor do Favorito, da Pasta ou da Pasta compartilhada os números sao respectivamente 2,3 e 4
//        var iconTipoBloco = 'mdi-navigation-more-vert'// Ícone que define caracteriza favorito, pasta ou pasta compartilhada


        var iconFav = $('<i class="mdi-action-stars yellow-text text-accent-2"></i>');
        var iconFold = $('<i class="mdi-file-folder yellow-text text-darken-3"></i>');
        var iconShar = $('<i class="mdi-file-folder-shared yellow-text text-darken-3"></i>');
        
		bloco.createElement = function () {
		var card = $('<div class="col s12 m3 pasta-fechada">');
		var painel = $('<div class="card-panel card-complement cyan darken-3">');
		var conteudo = $('<div class="card-content white-text">');
		var div_icon = $('<div class=" col s4 pdzero">');
		var icon = $('<img class="responsive-img" src="' + iconUrl +  '">');
		var div_dadosFavorito = $('<div class="col s8 cyan-text text-lighten-5 pdzero">');
		var div_tituloFavorito = $('<div class="col s12 titulo-favorito">');
		var titulo_favorito = $('<div class="col s10 truncate pdzero">');
		var tituloFavorito = $('<i class="mdi-file-folder yellow-text text-darken-3"></i>');
		var div_opcoes = $('<div class="col s2">');
		var btOpcoes = $('<a class="dropdown-button cyan-text text-lighten-5" data-activates="dropdown' + id +'">');
        var icon_btOpcoes = $('<i class="mdi-navigation-more-vert"></i>');
        var dropdown_content = $('<ul id="dropdown' + id + '" class="dropdown-content">');
        var dropdownSelecionar = $('<li><a>Selecionar</a></li>');
        var dropdownEditar = $('<li><a>Editar</a></li>');
        var dropdownExcluir = $('<li><a>Excluir</a></li>');
		
		var div_estrelas = $('<div class="col s12">');
		for (i=0; i<numEstrela; i++){
		var estrela = $('<i class="mdi-action-grade"></i>' + estrela);
		div_estrelas.append (estrela);

		}
		var div_descricao = $('<div class="col s12 truncate">');
		var div_tags = $('<div class="col s12 truncate">');
		var icon_tag = $('<i class="mdi-maps-local-offer"></i>');
		
		
		card.append (painel);
		painel.append (conteudo);
		conteudo.append (div_icon);
		div_icon.append (icon);
		conteudo.append (div_dadosFavorito);
		div_dadosFavorito.append (div_tituloFavorito);
		div_tituloFavorito.append (titulo_favorito);
		titulo_favorito.append (tituloFavorito);
        titulo_favorito.append (nomeFavorito);
		div_tituloFavorito.append (div_opcoes);
		div_opcoes.append (btOpcoes);
		div_opcoes.append (dropdown_content);
		btOpcoes.append (icon_btOpcoes);
        dropdown_content.append (dropdownSelecionar);
        dropdown_content.append (dropdownEditar);
        dropdown_content.append (dropdownExcluir);
		div_dadosFavorito.append (div_estrelas);
		
		div_dadosFavorito.append (div_descricao);
		div_descricao.append(descricao);
		div_dadosFavorito.append (div_tags);
		div_tags.append (icon_tag); 
			return card;
        }
        return bloco;
    }
	
	
};
var materialize = new Materialize();

 
var Element = {
	Bloco: function(icon, nome,descricao, tipo, iconTipo, numEstrela, id){
		return new materialize.Bloco(icon, nome,descricao, tipo, iconTipo, numEstrela, id);
	},
    FavAcessoRapido: function(icon, nome, descricao, id){
		return new materialize.FavAcessoRapido();
	},
    Modal: function(columnTitleArray, contentArray){
		return new materialize.Modal();
	}
}{
<<<<<<< HEAD
	Bloco: function(icon, nome,descricao, tipo, iconTipo, numEstrela, id){
=======
	Favorito: function(icon, nome,descricao, tipo, iconTipo, numEstrela, id){
		return new materialize.Bloco(icon, nome,descricao, tipo, iconTipo, numEstrela, id);
	},
	Pasta: function(icon, nome,descricao, tipo, iconTipo, numEstrela, id){
>>>>>>> branch 'master' of https://github.com/MarcoASCruz/GerenciadorDeFavoritos.git
		return new materialize.Bloco(icon, nome,descricao, tipo, iconTipo, numEstrela, id);
	},
    FavAcessoRapido: function(icon, nome, descricao, id){
		return new materialize.FavAcessoRapido(icon, nome, descricao, id);
	},
    Modal: function(columnTitleArray, contentArray){
		return new materialize.Modal();
	}
}
