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

    //{iconeUrl:"", ... }
	this.Bloco = function (dados) {
	    var bloco = new ObjectHtml();
	    bloco.createElement = function () {
	        var container = $('<div class="col s12 m3 pasta-fechada drag">');
	        var card = $('<div class="card-panel card-complement cyan darken-2">');
		    var adicionarConteudo = function(){
		        card.append(criarConteudo());
		    }
		    var criarConteudo = function () {
		        var conteudo = $('<div class="card-content white-text">');
		        var iconeContainer = $('<div class=" col s4 pdzero">');
		        var dadosContainer = $('<div class="col s8 cyan-text text-lighten-5 pdzero">');

		        var adicionarIcone = function () {
		            iconeContainer.append(criarIcone(dados.iconeUrl));
		        }
		        var adicionarHeader = function () {
		            dadosContainer.append(criarHeader());
		        }
		        var adicionarEstrelas = function () {
		            dadosContainer.append(criarEstrelas());
		        }
		        var adicionarDescricao = function () {
		            dadosContainer.append(criarDescricao());
		        }
		        var adicionarTags = function () {
		            dadosContainer.append(criarTags(dados.tags));
		        }

		        adicionarIcone();
		        adicionarHeader();
		        adicionarEstrelas();
		        adicionarDescricao();
		        adicionarTags();

		        conteudo.append(iconeContainer);
		        conteudo.append(dadosContainer);

		        return conteudo;
		    }
		    adicionarConteudo();
		    container.append(card);
		    
		    return container;
	    }
	    var criarIcone = function (iconeUrl) {
	        var container = $('<div class=" col s4 pdzero">');
	        var icon = $('<img class="responsive-img">');
	        if (iconeUrl) {
	            icon.attr('src', iconeUrl);
	        }
	        else {
	            icon.attr('src', 'https://cdn2.iconfinder.com/data/icons/flatte-social-networks-part-2/80/12_-_Star-512.png');
	        }
	        container.append(icon);
	        return container;
	    }
	    var criarHeader = function () {
	        var container = $('<div class="col s12 titulo-favorito">'); //titulo favorito?????
	        var adicionarTitulo = function () {
	            container.append(bloco.criarTitulo());
	        }
	        var adicionarMenu = function () {
	            container.append(bloco.Menu.obterHtml());
	        }
	        adicionarTitulo();
	        adicionarMenu();
	        return container;
	    }
	    bloco.criarTitulo = function () {
	        return null;
	    }
	    bloco.Menu = new function() {
	        var container = $('<div class="col s2">');
	        var adicionarBotao = function () {
	            container.append(criarBotao());
	        }
	        var criarBotao = function () {
	            var botao = $('<a class="dropdown-button cyan-text text-lighten-5">');
	            botao.attr('data-activates', 'dropdown' + dados.id);
	            var icon = $('<i class="mdi-navigation-more-vert">');
	            botao.append(icon);
	            return botao;
	        }
	        var adicionarAcoes = function (acoes) {
	            container.append(criarAcoes(acoes));
	        }
	        var criarAcoes = function (acoes) {
	            var container = $('<ul class="dropdown-content">');
	            container.attr('id', 'dropdown' + dados.id);

	            var preencherAcoes = function () {
	                var quantAcoes = acoes.length;
	                for (var i = 0; i < quantAcoes; i++) {
	                    adicionarAcao(acoes[i])
	                }
	            }
	            var adicionarAcao = function (acao) {
	                container.append(criarAcao(acao));
	            }
	            var criarAcao = function (acao) {
	                var container = $('<li>');
	                var conteudo = $('<a href="#">');
	                conteudo.append(acao);
	                container.append(conteudo);
	                return container;
	            }
	            preencherAcoes();

	            return container;
	        }
            
	        this.init = function (acoes) {
	            adicionarBotao();
	            adicionarAcoes(acoes);
	        }
	        this.obterHtml = function () {
	            return container;
	        }
	    }
	    var criarEstrelas = function () {
	        var container = $('<div class="col s12">');
	        var quantEstrelas = dados.quantEstrelas;
	        for (i = 0; i < quantEstrelas; i++) {
	            var estrela = $('<i class="mdi-action-grade">');
	            container.append(estrela);
	        }
	        return container;
	    }
	    var criarDescricao = function () {
	        var container = $('<div class="col s12 truncate">');
	        container.append(dados.descricao ? dados.descricao : "(Sem descrição)");
	        return container;
	    }
	    var criarTags = function (tags) {
		    var container = $('<div class="col s12 truncate">');
		    var icone = $('<i class="mdi-maps-local-offer">');

		    var adicionarTag = function (tag) {
		        container.append(criarTag(tag));
		    }
		    var criarTag = function (titulo) {
		        var tag = $('<a class="amber-text text-darken-2" href="#">');
		        tag.append(titulo);
		        return tag;
		    }

		    var adicionarVirgula = function () {
		        container.append(criarVirgula());
		    }
		    var criarVirgula = function () {
		        return $('<span class="amber-text text-darken-2">').append(', ')
		    }

		    var preencherContainer = function (tags) {
		        var quantTags = tags.length;
		        var proximaTagExistir = function (indiceAtual) {
		            var existe = false;
		            if ((indiceAtual + 1) == quantTags) {
		                existe = false;
		            }
		            else {
		                existe = true;
		            }
		            return existe;
		        }
		        for (var i = 0; i < quantTags; i++) {
		            adicionarTag(tags[i]);
		            if (proximaTagExistir(i)) {
		                adicionarVirgula();
		            }
		        }
		    }
		    
		    icone.appendTo(container);
		    if (tags) {
		        preencherContainer(tags);
		    }

		    return container;
		}
	    bloco.getTags = function (tags) {
	        var container = $('<div class="col s12 truncate">');
	        var icone = $('<i class="mdi-maps-local-offer">');

	        var adicionarTag = function (tag) {
	            container.append(criarTag(tag));
	        }
	        var criarTag = function (titulo) {
	            var tag = $('<a class="amber-text text-darken-2" href="#">');
	            tag.append(titulo);
	            return tag;
	        }

	        var adicionarVirgula = function () {
	            container.append(criarVirgula());
	        }
	        var criarVirgula = function () {
	            return $('<span class="amber-text text-darken-2">').append(', ')
	        }

	        var preencherContainer = function (tags) {
	            var quantTags = tags.length;
	            var proximaTagExistir = function (indiceAtual) {
	                var existe = false;
	                if ((indiceAtual + 1) == quantTags) {
	                    existe = false;
	                }
	                else {
	                    existe = true;
	                }
	                return existe;
	            }
	            for (var i = 0; i < quantTags; i++) {
	                adicionarTag(tags[i]);
	                if (proximaTagExistir(i)) {
	                    adicionarVirgula();
	                }
	            }
	        }

	        icone.appendTo(container);
	        if (tags) {
	            preencherContainer(tags);
	        }

	        return container;
	    }
	    return bloco;
	};
	
	this.Favorito = function (favorito) {
	    var bloco = new self.Bloco({
	        id: favorito.id,
	        quantEstrelas: favorito.numEstrela,
	        descricao: favorito.descricao,
	        tags: favorito.tags,
	        iconeUrl: favorito.imagem
	    });
	    bloco.criarTitulo = function () {
	        var titulo = $('<div class="col s10 truncate pdzero">');
	        var icone = $('<i class="mdi-action-stars yellow-text text-accent-2">');
	        titulo.append(icone);
	        titulo.append(favorito.titulo);
	        return titulo;
	    }
	    bloco.Menu.init(['Selecionar', 'Editar', 'Excluir']);
        return bloco;
    }
	
	this.Pasta = function (pasta) {
	    console.log(pasta)
		var bloco = new self.Bloco({
	        id: pasta.id,
	        quantEstrelas: pasta.numEstrela,
	        descricao: pasta.descricao,
	        tags: pasta.tags,
	        iconeUrl: pasta.imagem
	    });
	    bloco.criarTitulo = function () {
	        var titulo = $('<div class="col s10 truncate pdzero">');
	        var icone = $('<i class="mdi-file-folder yellow-text text-darken-3">');
	        titulo.append(icone);
	        titulo.append(pasta.nome);
	        return titulo;
	    }
	    bloco.Menu.init(['Selecionar', 'Editar', 'Excluir']);
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