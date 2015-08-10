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

	this.FavAcessoRapido = function(icon, nome, id){
        //icon, nome, descricao, id
		var favAR = new ObjectHtml();
		
//		var iconUrl = icon;
        var iconUrl = icon ? icon : 'https://cdn2.iconfinder.com/data/icons/flatte-social-networks-part-2/80/12_-_Star-512.png';
        var nomeFavorito = nome;
        
        favAR.createElement = function () {
        var bloco = $('<div class="favorito col s4 m1 cyan darken-2 z-depth-1 drag" style="margin: 5px 0.3em; position: relative; left: 0px; top: 0px;">');
        var div_icon= $('<div class="col s12 pdzero" style="margin-top: 5px;">');
        var icon = $('<img class="responsive-img" src="' + iconUrl +  '">');
        var div_detalhes = $('<div class="col s12 cyan-text text-lighten-5 pdzero">');
        var detalhes_favorito = $('<div class="col s12 pdzero">');
        var titulo_favorito = $('<div class="col s10 pdzero titulo-favorito-destaque truncate">');
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
	
	this.Favorito = function (favorito) {
		
        var bloco = new ObjectHtml();
        
        var id = favorito.id;
        var tags = favorito.tags;
        var nomeFavorito = favorito.titulo;
        var descricao = favorito.descricao;
        var tipo = "2"
        var iconTipo = "mdi-navigation-more-vert";
        var numEstrela = favorito.numEstrela;
        var iconUrl = favorito.imagem ? favorito.imagem : 'https://cdn2.iconfinder.com/data/icons/flatte-social-networks-part-2/80/12_-_Star-512.png';
        

        var iconFav = $('<i class="mdi-action-stars yellow-text text-accent-2"></i>');
        var iconFold = $('<i class="mdi-file-folder yellow-text text-darken-3"></i>');
        var iconShar = $('<i class="mdi-file-folder-shared yellow-text text-darken-3"></i>');
        
		bloco.createElement = function () {
			var card = $('<div class="col s12 m3 pasta-fechada drag">');
			var painel = $('<div class="card-panel card-complement cyan darken-2">');
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
			var getTags = function(tags){
				var container = $('<div class="col s12 truncate">');
				var icone = $('<i class="mdi-maps-local-offer">');
				var criarTag = function(titulo){
					var tag = $('<a class="amber-text text-darken-2" href="#">');
					tag.append(titulo);
					return tag;
				}
				
				var adicionarTag = function(tag){
					container.append(criarTag(tag));
				}
				
				var preencherContainer = function(tags){
					var quantTags = tags.length;
					for (var i = 0; i < quantTags; i++){
						adicionarTag(tags[i]);
					}
				}

				icone.appendTo(container);
				if(tags){
					preencherContainer(tags);
				}
				
				return container;
			}
			var div_tags = getTags(tags);
		
		
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
			div_descricao.append(descricao ? descricao : "(Sem descrição)");
			div_dadosFavorito.append (div_tags);
			return card;
        }
        return bloco;
    }
	
	this.Pasta = function (pasta) {
		
        var bloco = new ObjectHtml();
        
        var id = pasta.id;
        var tags = pasta.tags;
        var nomeFavorito = pasta.nome;
        var tipo = "2"
        var iconTipo = "mdi-navigation-more-vert";
        var numEstrela = pasta.numEstrela;
        var iconUrl = pasta.imagem ? pasta.imagem : 'https://cdn1.iconfinder.com/data/icons/finance-items/512/folder_archive_directory_business_papers_computer_file_organize_information_flat_design_icon-512.png';

        
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
			
			var getTags = function(tags){
				var container = $('<div class="col s12 truncate">');
				var icone = $('<i class="mdi-maps-local-offer">');
				var criarTag = function(titulo){
					var tag = $('<a class="amber-text text-darken-2" href="#">');
					tag.append(titulo);
					return tag;
				}
				
				var adicionarTag = function(tag){
					container.append(criarTag(tag));
				}
				
				var preencherContainer = function(tags){
					var quantTags = tags.length;
					for (var i = 0; i < quantTags; i++){
						adicionarTag(tags[i]);
					}
				}

				icone.appendTo(container);
				if(tags){
					preencherContainer(tags);
				}
				
				return container;
			}	
			
			var div_tags = getTags(tags);
		
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
			div_descricao.append(pasta.descricao ? pasta.descricao : "(Sem descrição)");
			div_dadosFavorito.append (div_tags);
			return card;
        }
        return bloco;
    }
	
	
};
var materialize = new Materialize();

var Element = {
	Favorito: function(icon, nome,descricao, tipo, iconTipo, numEstrela, id){
		return new materialize.Favorito(icon, nome,descricao, tipo, iconTipo, numEstrela, id);
	},
	Pasta: function(icon, nome,descricao, tipo, iconTipo, numEstrela, id){
		return new materialize.Pasta(icon, nome,descricao, tipo, iconTipo, numEstrela, id);
	},
    FavAcessoRapido: function(icon, nome, id){
		return new materialize.FavAcessoRapido(icon, nome, id);
	},
    Modal: function(columnTitleArray, contentArray){
		return new materialize.Modal();
	}
}