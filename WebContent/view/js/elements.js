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

var Element = function () {
	var self = this;
		
	this.Toast = function(mensagem, tempo){
		 var dialog = undefined;
		 if(tempo){
			 dialog = Materialize.toast(mensagem, tempo);
		 }
		 else {
			 dialog = Materialize.toast(mensagem); 
		 }
		 return dialog;
	}
	
	
	this.Modal = function(){
		var modal = new ObjectHtml();
		var container = $('<div class="modal modal-fixed-footer">');
		var conteudo = $('<div class="modal-content">');
		var footer = $('<div class="modal-footer">');
		
		modal.createElement = function(){
			container.append(conteudo);
			container.append(footer);
			return container;
		}		
		
		modal.addConteudo = function(element){
			conteudo.append(element);
		}
		modal.addFooter = function(element){
			footer.append(element);
		}
		modal.show = function(){
			$('body').append(modal.getElement());
			modal.getElement().openModal({
				 dismissible: true,
				 complete: function() { 
					 modal.destroy();
				 }
		    });
		}
		modal.destroy = function(){
			modal.getElement().remove();
		}
		return modal;
	}

	this.FavAcessoRapido = function(iconUrl, nome, id){
		var favAR = new ObjectHtml();
		var criarIcon = function(iconeUrl){
			var icon = $('<img class="responsive-img" style="width: 77px;height: 77px;">');
			var adicionarImagemDefault = function(){
				icon.attr('src', '/GerenciadorDeFavoritos/view/img/semImagem.png');
			}
			
			if(iconeUrl){
				icon.attr('src', iconeUrl);
			}
			else{
				adicionarImagemDefault();
			}
			
	        icon.error(function(){
	        	adicionarImagemDefault();
	        })
	        return icon;
		}
		
		//var iconUrl = icon ? icon : 'https://cdn2.iconfinder.com/data/icons/flatte-social-networks-part-2/80/12_-_Star-512.png';
        var nomeFavorito = nome;
        
        favAR.createElement = function () {
        var bloco = $('<div class="favorito col s4 m1 cyan darken-2 z-depth-1 drag" style="margin: 5px 0.3em; position: relative; left: 0px; top: 0px;">');
        var div_icon= $('<div class="col s12 pdzero" style="margin-top: 10px;">');
        
        var icon = criarIcon(iconUrl);
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
	    var blocoContainer;
	    var idBloco = dados.tipo + "-" + dados.id;
	    bloco.createElement = function () {
	    	blocoContainer = $('<div class="bloco col s12 m3 pasta-fechada drag">');
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

		        adicionarEventoDeClick(card);
		        conteudo.append(iconeContainer);
		        conteudo.append(dadosContainer);

		        return conteudo;
		    }
		    adicionarConteudo();
		    blocoContainer.append(card);
		    
		    return blocoContainer;
	    }
	    var criarIcone = function (iconeUrl) {
	        var container = $('<div class="valign-wrapper">');
	        var icon = $('<img style="height:85px" class="circle responsive-img valign">');
	        if (iconeUrl) {
	            icon.attr('src', iconeUrl);
	            icon.error(function(){
	            	icon.attr('src', '/GerenciadorDeFavoritos/view/img/semImagem.png');
	            })
	        }
	        else {
	            icon.attr('src', '/GerenciadorDeFavoritos/view/img/semImagem.png');
	        }
	        container.append(icon);
	        return container;
	    }
	    
	    var criarHeader = function () {
	        var container = $('<div class="col s12 titulo-favorito">');
	        var adicionarTitulo = function () {
	            container.append(bloco.criarTitulo(dados.titulo));
	        }
	        var adicionarMenu = function () {
	        	var menu = bloco.Menu.obterHtml();
	        	container.append(menu);
	        }
	        adicionarTitulo();
	        adicionarMenu();
	        return container;
	    }
	    bloco.criarTitulo = function (titulo) {
	        return null;
	    }
	    bloco.Menu = new function() {
	        var container = $('<div class="col s2">');
	        var adicionarBotao = function () {
	            container.append(criarBotao());
	        }
	        var criarBotao = function () {
	            var botao = $('<a class="dropdown-button cyan-text text-lighten-5">');
	            botao.attr('data-activates', 'dropdown-acoes-' + idBloco);
	            var icon = $('<i class="mdi-navigation-more-vert">');
	            botao.append(icon);
	            var dropDownHabilitado = false;
	            botao.on('click', function(event){
	            	fecharDropDownAberto();
	            	if(!dropDownHabilitado){
	            		dropDownHabilitado = true;
	            		habilitarDropDown();
	            		abrirDropDown();
	            	}
	            	event.stopPropagation();
	            })
	            var habilitarDropDown = function(){
	        		botao.dropdown({constrainwidth: false});
	        	}
	        	var abrirDropDown = function(){
	        		botao.click();
	        	}
	            return botao;
	        }
	        var fecharDropDownAberto = function(){
            	$('.dropdown-content.active').css('display', 'none');
            	$('.dropdown-content.active').removeClass('active');
            }
	        var adicionarAcoes = function (acoes) {
	            container.append(criarAcoes(acoes));
	        }
	        var criarAcoes = function (acoes) {
	            var container = $('<ul class="dropdown-content">');
	            container.attr('id', 'dropdown-acoes-' + idBloco);

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
	                conteudo.append(acao.titulo);
	                container.append(conteudo);
	                container.on('click', function(event){
	                	event.stopPropagation();
	                	acao.executar(dados, bloco);
	                	fecharDropDownAberto();
	                })
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
	        var options = {
      		  readOnly: true,
      		  score: dados.quantEstrelas,
      		  starType : 'i'  
      		}
	        var estrelas = new Element.Star(options);
	        container.append(estrelas.getElement());
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
	    var adicionarEventoDeClick = function(element){
	    	element.click(function(){
	    		dados.onClick(dados)
    		});
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
	    bloco.remove = function(){
	    	blocoContainer.fadeOut(
    	        function(){
    	        	blocoContainer.remove();
    	        }
    	    );
        }
	    return bloco;
	};
	
	this.Favorito = function (favorito, acoes) {
	    var bloco = new self.Bloco({
	    	tipo: "favorito",
	    	titulo: favorito.titulo,
	        id: favorito.id,
	        quantEstrelas: favorito.numEstrela,
	        descricao: favorito.descricao,
	        tags: favorito.tags,
	        iconeUrl: favorito.imagem,
	        url: favorito.url,
	        onClick: acoes.onClick
	    });
	    bloco.criarTitulo = function (favTitulo) {
	        var titulo = $('<div class="col s10 truncate pdzero">');
	        var icone = $('<i class="mdi-action-stars yellow-text text-accent-2">');
	        titulo.append(icone);
	        titulo.append(favTitulo);
	        return titulo;
	    }
	    bloco.Menu.init([
             {
            	 titulo: 'Excluir'
        		 ,
        		 executar: acoes.remover
             }
             ,
             {
            	 titulo: 'Editar'
        		 ,
        		 executar: acoes.editar
             }
     	])
        return bloco;
    }
	
	this.Pasta = function (pasta, acoes) {
		var bloco = new self.Bloco({
			tipo: "pasta",
			titulo: pasta.nome,
	        id: pasta.id,
	        quantEstrelas: pasta.numEstrela,
	        descricao: pasta.descricao,
	        tags: pasta.tags,
	        iconeUrl: pasta.imagem,
	        onClick: acoes.onClick
	    });
	    bloco.criarTitulo = function (nomePasta) {
	        var titulo = $('<div class="col s10 truncate pdzero">');
	        var icone = $('<i class="mdi-file-folder yellow-text text-darken-3">');
	        titulo.append(icone);
	        titulo.append(nomePasta);
	        return titulo;
	    }
	    bloco.Menu.init([
             {
            	 titulo: 'Excluir'
        		 ,
        		 executar: acoes.remover
             }
             ,
             {
            	 titulo: 'Editar'
        		 ,
        		 executar: acoes.editar
             }
     	])
	    return bloco;
    }

	this.Arvore = function (dados, onClick){
		var arvore = new ObjectHtml();
		var container = $('<div>');
		arvore.createElement = function(){
			container.jstree(
				{ 
					'core' : {
						'data' : obterModelo()
						,
						check_callback: true 
					}
				}
			)
			criarEventos();
			return container;
		}
		var obterModelo = function(){
			var result = new Array();
			var quantDados = dados.length;
			for(var i = 0; i < quantDados; i++){
				dados[i].icon = "mdi-file-folder yellow-text text-darken-3";
				if(dados[i].parent == '#'){
					dados[i].state = {
						opened: true
						,
						selected: true
					}
				}
				result.push(dados[i]);	
			}
			return result;
		}
			
		var	criarEventos = function(){
			container.on('select_node.jstree', function (e, data) {
				onClick(data.selected[data.selected.length - 1]);
		  	})
		}
		arvore.criarPasta = function(id, idPai, nome){
			var icon = "mdi-file-folder yellow-text text-darken-3";
			var tree = arvore.getElement().jstree(true);
			tree.create_node(idPai , {id: id, text: nome, icon: icon});
		}
		arvore.selecionarItem = function(id){
			var tree = arvore.getElement().jstree(true);
			tree.deselect_all();
			tree.select_node(id);
		}
		arvore.removerItem = function(id){
			var tree = arvore.getElement().jstree(true);
			tree.delete_node(id);
		}
		arvore.renomearItem = function(id, titulo){
			var tree = arvore.getElement().jstree(true);
			tree.rename_node(id, titulo);
		}
		return arvore;
		
	}

	this.Loader = function(){
		var container = $('<div class="loader"> <div class="preloader-wrapper big active"> <div class="spinner-layer spinner-blue-only"> <div class="circle-clipper left"> <div class="circle"></div> </div><div class="gap-patch"> <div class="circle"></div> </div><div class="circle-clipper right"> <div class="circle"></div> </div> </div> </div> <div id="lean-overlay" class="" style="display: block; opacity: 0.5;"></div> </div>')	
		return container;
	}

	this.Star = function(options){
		var star = new ObjectHtml();
		var optionsDefault = {
		  cancel   : true,
		  starType : 'i'  
		}
		star.createElement = function(){
			var container = $('<div>');
			if(options){
				container.raty(options);
			}
			else{
				container.raty(optionsDefault);
			}
			return container;
		}
		star.getScore(){
			var quant = star.getElement().raty('score');
			if (quant == undefined){
				quant = 0;
			}
			return quant;
		}
		return star;
	}
	
	this.TagsInput = function(){
		var tagsInput = new ObjectHtml();
		var input = $('<input>');
		
		tagsInput.createElement = function(){
			var container = $('<div onunload="test()">');
			container.append(input);
			container.on('DOMNodeInsertedIntoDocument', function(){
				input.tagsInput();
			})
			return container;
		}
		tagsInput.getValues = function(){
			var tags = input.val().split(',');
			if(tags && tags != ""){
				return tags;
			}
			else{
				return null;
			}
		}
		return tagsInput;
	}
};
var Element = new Element();