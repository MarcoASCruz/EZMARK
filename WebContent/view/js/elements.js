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
		
	this.BlocoMensagem = function (titulo, mensagem){
		var blocoMensagem =  new ObjectHtml();
		var containerRow = $('<div class="row">');
		var containerCol = $('<div class="col s12 m6">');
		var containerCard = $('<div class="card red darken-1">');
		var containerCardContent = $('<div class="card-content white-text">');
		var tituloBloco  = $('<span class="card-title">');
		var mensagemBloco  = $('<p>');
		
		blocoMensagem.createElement = function(){
			tituloBloco.append(titulo);
			mensagemBloco.append(mensagem);
			containerCardContent.append(tituloBloco);
			containerCardContent.append(mensagemBloco);
			containerCard.append(containerCardContent);
			containerCol.append(containerCard);
			containerRow.append(containerCol);
			return containerRow;
		}
		
		return blocoMensagem;
	}
	
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
				 complete: function() { 
					 modal.close();
				 }
		    });
		}
		modal.close = function(){
			modal.getElement().closeModal({
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

	this.FavAcessoRapido = function(favorito, onClick, onRightClick, onDelete){
		var favAR = new ObjectHtml();	
        
        favAR.createElement = function () {
	        var bloco = $('<div class="favorito-icon favorito col s4 m1 cyan darken-2 z-depth-1 drag context-menu-acessoRapido" style="margin: 5px 0.3em; position: relative; left: 0px; top: 0px;">');
	        var div_icon= $('<div class="col s12 pdzero" style="margin-top: 10px;">');
	        
	        var icon = criarIcon(favorito.imagem);
	        var div_detalhes = $('<div class="col s12 cyan-text text-lighten-5 pdzero">');
	        var detalhes_favorito = $('<div class="col s12 pdzero">');
	        var titulo_favorito = $('<div class="col s10 pdzero titulo-favorito-destaque truncate">');
	        
	        bloco.append (div_icon);
	        div_icon.append (icon);
	        bloco.append (div_detalhes);
	        div_detalhes.append (detalhes_favorito);
	        detalhes_favorito.append (titulo_favorito);
	        titulo_favorito.append (favorito.titulo);
	        detalhes_favorito.append (criarMenu());
	        
	        bloco.on('click', function(){
	        	onClick(favorito);
	        })
	        
	        bloco.on('mousedown',function(e){
	    		if(e.which == 3){
	    			onRightClick(favorito);	
	    		}
    		})
	        
	        return bloco;
        }
        var criarIcon = function(imgUrl){
			var icon = $('<img class="responsive-img" style="width: 77px;height: 77px;">');
			var adicionarImagemDefault = function(){
				icon.attr('src', '/GerenciadorDeFavoritos/view/img/semImagem.png');
			}
			
			if(imgUrl){
				icon.attr('src', imgUrl);
			}
			else{
				adicionarImagemDefault();
			}
			
	        icon.error(function(){
	        	adicionarImagemDefault();
	        })
	        return icon;
		}
        
        var criarMenu = function(){
        	var drop = new Element.MenuDropDown(favorito.id);
        	drop.adicionarAcoes([
                  {
                 	 titulo: 'Remover'
             		 ,
             		 executar: function(id){
             			onDelete(id);
             		 }
                  }
          	])
        	drop.getElement().addClass("pdzero");
        	return drop.getElement();
        }
        return favAR;
	}
	
	this.MenuDropDown = function(id) {
		var drop = new ObjectHtml();
        var container = $('<div class="col s2">');
        
        drop.createElement = function(){
        	adicionarBotao();
        	return container;
        }
        
        var adicionarBotao = function () {
            container.append(criarBotao());
        }
        var criarBotao = function () {
            var botao = $('<a class="dropdown-button cyan-text text-lighten-5">');
            botao.attr('data-activates', 'dropdown-element-' + id);
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
        drop.adicionarAcoes = function (acoes) {
            container.append(criarAcoes(acoes));
        }
        var criarAcoes = function (acoes) {
            var container = $('<ul class="dropdown-content">');
            container.attr('id', 'dropdown-element-' + id);

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
                	acao.executar(id);
                	fecharDropDownAberto();
                })
                return container;
            }
            preencherAcoes();

            return container;
        }
        return drop;
    }

    //{imgUrl:"", ... }
	this.Bloco = function (dados) {
	    var bloco = new ObjectHtml();
	    var blocoContainer;
	    var idBloco = dados.tipo + "-" + dados.id;
	    bloco.createElement = function () {
	    	blocoContainer = $('<div class="bloco col s12 m3 pasta-fechada drag">');
	        var card = $('<div class="card-panel card-complement cyan darken-2">');
		    adicionarMenuContexto(card);
	        var adicionarConteudo = function(){
		        card.append(criarConteudo());
		    }
		    var criarConteudo = function () {
		        var conteudo = $('<div class="card-content white-text">');
		        var iconeContainer = $('<div class=" col s4 pdzero">');
		        var dadosContainer = $('<div class="col s8 cyan-text text-lighten-5 pdzero">');
		        
		        var adicionarIcone = function () {
		            iconeContainer.append(criarIcone(dados.imgUrl));
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
	    var adicionarMenuContexto = function(card){
	    	card.addClass(dados.menuContexto);
	    }
	    var criarIcone = function (imgUrl) {
	        var container = $('<div class="valign-wrapper">');
	        var icon = $('<img style="height:85px" class="circle responsive-img valign">');
	        if (imgUrl) {
	            icon.attr('src', imgUrl);
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
	    	element.on('mousedown',function(e){
	    		if(e.which == 3){
	    			dados.onRightClick(dados, bloco);	
	    		}
    		})
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
	
	this.Favorito = function (favorito, acoes, temMenu) {
	    var bloco = new self.Bloco({
	    	tipo: "favorito",
	    	titulo: favorito.titulo,
	        id: favorito.id,
	        quantEstrelas: favorito.numEstrela,
	        descricao: favorito.descricao,
	        tags: favorito.tags,
	        imgUrl: favorito.imagem,
	        url: favorito.url,
	        onClick: acoes.onClick,
	        onRightClick: acoes.onRightClick,
	        menuContexto: "context-menu-favorito"
	    });
	    bloco.criarTitulo = function (favTitulo) {
	        var titulo = $('<div class="col s10 truncate pdzero">');
	        var icone = $('<i class="mdi-action-stars yellow-text text-accent-2">');
	        titulo.append(icone);
	        titulo.append(favTitulo);
	        return titulo;
	    }
	    if(temMenu != false){
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
	    }
     	var adicionarDragAndDrop = function(){
	    	bloco.getElement().attr("draggable", "true");
		    bloco.getElement().get(0).addEventListener('dragstart', function(e){
		    	e.dataTransfer.setData("idFavorito", favorito.id);
		    });
	    }
     	adicionarDragAndDrop();
        
	    return bloco;
    }
	
	this.Pasta = function (pasta, acoes, temMenu) {
		var bloco = new self.Bloco({
			tipo: "pasta",
			titulo: pasta.nome,
	        id: pasta.id,
	        quantEstrelas: pasta.numEstrela,
	        descricao: pasta.descricao,
	        tags: pasta.tags,
	        imgUrl: pasta.imagem,
	        onClick: acoes.onClick,
	        onRightClick: acoes.onRightClick,
	        menuContexto: "context-menu-pasta"
	    });
	    bloco.criarTitulo = function (nomePasta) {
	        var titulo = $('<div class="col s10 truncate pdzero">');
	        var icone = $('<i class="yellow-text text-darken-3">');
	        if (pasta.publica){
	        	icone.addClass("mdi-file-folder-shared");
	        }
	        else{
	        	icone.addClass("mdi-file-folder");
	        }
	        
	        titulo.append(icone);
	        titulo.append(nomePasta);
	        return titulo;
	    }
	    if(temMenu != false){
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
	             ,
	             {
	            	 titulo: 'Compartilhar'
	        		 ,
	        		 executar: acoes.compartilhar
	             }
	     	])
	    }
	    
	    return bloco;
    }

	this.Arvore = function (dados, onClick, servicosRightClick){
		var arvore = new ObjectHtml();
		var container = $('<div>');
		arvore.createElement = function(){
			container.jstree(
				{ 
					'core' : {
						'data' : obterModelo()
						,
						check_callback: true 
					},
					"plugins": ["contextmenu"],//["themes", "html_data", "ui", "crrm", "contextmenu"],
					"contextmenu": {
						"select_node": false,
						"items": function ($node) {
							var menu = new Object();
							menu.Create = {
			                    "label": "Criar Pasta",
			                    "action": function (obj) {
			                    	console.log(obj, $node)
			                    	var idPai = parseInt($node.id);
			                    	servicosRightClick.criarPastaFilha(idPai);
			                    }
			                }
							if (pastaTemPai($node)){
								menu.Delete = {
				                    "label": "Excluir",
				                    "action": function (obj) {
				                    	var idPasta = parseInt($node.id);
				                    	var idPai = $node.parent;
				                    	servicosRightClick.removerPasta(idPasta, idPai);
				                    }
				                }
							}
				            return menu;
				        }
				    }
				}
			)
			criarEventos();
			return container;
		}
		var pastaTemPai = function(pasta){
			var result = true;
			if(pasta.parent == "#"){
				result = false;
			}
			return result;
		}
		var obterModelo = function(){
			var result = new Array();
			var quantDados = dados.length;
			for(var i = 0; i < quantDados; i++){
				if (dados[i].publica == true){
					dados[i].icon = "mdi-file-folder-shared yellow-text text-darken-3";
				}
				else{
					dados[i].icon = "mdi-file-folder yellow-text text-darken-3";
				}
				
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
				var id = data.selected[data.selected.length - 1];
				onClick(id);
				arvore.openItem(id);
				if(e.which == 3){
	    			alert()	
	    		}
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
			tree.open_node(id);
		} 
		arvore.openItem = function(id){
			var tree = arvore.getElement().jstree(true);
			tree.open_node(id);
		}
		arvore.removerItem = function(id){
			var tree = arvore.getElement().jstree(true);
			tree.delete_node(id);
		}
		arvore.renomearItem = function(id, titulo){
			var tree = arvore.getElement().jstree(true);
			tree.rename_node(id, titulo);
		}
		arvore.mudarIconeAPartirDoPai = function(id){
			var tree = arvore.getElement().jstree(true);
			var pai = tree.get_node(id);
			var filhos = pai.children_d;
			arvore.setIcone(pai.id, "mdi-file-folder-shared yellow-text text-darken-3");
			var quantFilhos = filhos.length;
			for (var i = 0; i < quantFilhos; i++){
				arvore.setIcone(filhos[i], "mdi-file-folder-shared yellow-text text-darken-3");
			}
		}
		arvore.setIcone = function(id, icon){
			var tree = arvore.getElement().jstree(true);
			tree.set_icon(id, icon)
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
		star.getScore = function(){
			var quant = star.getElement().raty('score');
			if (quant == undefined){
				quant = 0;
			}
			return quant;
		}
		star.setScore = function(score){
			star.getElement().raty('score', score);
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
		tagsInput.add = function(tags){
            var quantTags = tags.length;
            for (var i = 0; i < quantTags; i++) {
            	input.addTag(tags[i]);
            }
		}
		return tagsInput;
	}
	
	this.Form = function(titulo){
		var form = new ObjectHtml();
		var container = $('<form>');
		var conteudo = $('<div class="row">');
		
		form.createElement = function(callback){
			container.append(conteudo);
			adicionarTitulo(titulo);
			form.element = container;
			if(callback){
				callback();
			}
		}
		
		var adicionarTitulo = function(titulo){
			conteudo.append(criarTitulo(titulo));
		}
		var criarTitulo = function(titulo){
			var container = $('<h4>');
			container.append(titulo);
			return container;
		}
		
		form.adicionarItem = function(icone, item){
			conteudo.append(criarItem(icone, item));
		}
		var criarItem = function(icone, item){
			var container = $('<div class="input-field">')
			var iconeContainer = $('<i class="prefix">');
			if(icone){
				iconeContainer.addClass(icone);
				container.append(iconeContainer);
			}
			container.append(item);
			return container;
		}
        form.preencherCampos = function(){
        	throw "Form.preencherCampos is not implemented";
        }
        return form;
	}
	
	 this.FormPasta = function(titulo){
		var form = new Element.Form(titulo);
		var estrelas = undefined;
		var tags = undefined;
		var img = undefined;
		
		form.createElement(function(){
			var criarImg = function(){
				img = new Element.FormImg(); 
				return img.getElement();
			}
			var criarNome = function(){
				return $('<input id="nomePasta" type="text" class="validate"><label for="nomePasta">Nome</label>');
			}
			var criarDescricao = function(){
				return $('<textarea id="textareaPasta" class="materialize-textarea"></textarea><label for="textareaPasta">Descrição</label>');
			}
			var criarEstrelas = function(){
				var options = {
					cancel   : true,
					starType : 'i'
				 }
			    estrelas = new Element.Star(options);
			    return estrelas.getElement();
			}
			var criarTags = function(){    
			    tags = new Element.TagsInput();
			    return tags.getElement();
			}
			form.adicionarItem(null, criarImg());
			form.adicionarItem("mdi-file-folder", criarNome());
			form.adicionarItem("mdi-action-description", criarDescricao());
			form.adicionarItem(null, criarEstrelas());
			form.adicionarItem("mdi-maps-local-offer", criarTags());
		});
        
		form.getImgData = function (){
	        var input = $("#imgUpload", img.getElement());
	        var file_data = input[0].files[0];
	    	return file_data;
        }
		form.getNome = function(){
    		return $("#nomePasta", form.getElement()).val();
    	}
        form.getDescricao = function(){
    		return $("#textareaPasta", form.getElement()).val();
    	}
        form.getQuantEstrelas = function(){
        	return estrelas.getScore();
        }
        form.getTags = function(){
        	return tags.getValues();
        }
        form.preencherCampos = function(pasta){
        	var preencherImagem = function () {
        		img.add(pasta.imgUrl);
        	}
        	var preencherTitulo = function () {
                var titulo = $('#nomePasta', form.getElement());
                preencherCampo(titulo, pasta.titulo);
            }
            var preencherDescricao = function () {
                var descricao = $('#textareaPasta', form.getElement());
                preencherCampo(descricao, pasta.descricao);
            }
            var preencherCampo = function (campo, valor) {
                ativarCampo(campo);
                adicionarValorAoCampo(campo, valor);
            }
            var ativarCampo = function (campo) {
                $('label', campo.parent()).addClass('active');
            }
            var adicionarValorAoCampo = function (campo, valor) {
                campo.val(valor);
            }
            var preencherTags = function () {
                if (pasta.tags) {
                    tags.add(pasta.tags);
                }
            }
            var preencherEstrelas = function(){
            	estrelas.setScore(pasta.quantEstrelas);
            }
            preencherImagem();
            preencherTitulo();
            preencherDescricao();
            preencherTags();
            preencherEstrelas();
        }
		return form;
        
	}
	 
	this.FormFavorito = function(titulo){
		var form = new Element.Form(titulo);
		var estrelas = undefined;
		var tags = undefined;
		var img = undefined;
		form.createElement(function(){
			var criarImg = function(){
				img = new Element.FormImg(); 
				return img.getElement();
			}
			var criarNome = function(){
				return $('<input id="nomeFav" type="text" class="validate"><label for="nomeFav">Nome</label>');
			}
			var criarUrl = function(){
				return $('<input id="url-fav" type="text" class="validate"><label for="url-fav">URL</label>');
			}
			var criarDescricao = function(){
				return $('<textarea id="textareaFav" class="materialize-textarea"></textarea><label for="textareaFav">Descrição</label>');
			}
			var criarEstrelas = function(){
				var options = {
					cancel   : true,
					starType : 'i'
				 }
			    estrelas = new Element.Star(options);
			    return estrelas.getElement();
			}
			var criarTags = function(){    
			    tags = new Element.TagsInput();
			    return tags.getElement();
			}
			form.adicionarItem(null, criarImg());
			form.adicionarItem("mdi-action-stars", criarNome());
			form.adicionarItem("mdi-content-link", criarUrl());
			form.adicionarItem("mdi-action-description", criarDescricao());
			form.adicionarItem(null, criarEstrelas());
			form.adicionarItem("mdi-maps-local-offer", criarTags());
		});
        form.getImgData = function (){
	        var input = $("#imgUpload", img.getElement());
	        var file_data = input[0].files[0];
	    	return file_data;
        }
        form.getNome = function(){
    		return $("#nomeFav", form.getElement()).val();
    	}
        form.getUrl = function(){
    		return $("#url-fav", form.getElement()).val();
    	}
        form.getDescricao = function(){
    		return $("#textareaFav", form.getElement()).val();
    	}
        form.getQuantEstrelas = function(){
        	return estrelas.getScore();
        }
        form.getTags = function(){
        	return tags.getValues();
        }
        form.preencherCampos = function(favorito){
        	var preencherImagem = function () {
        		img.add(favorito.imgUrl);
        	}
            var preencherTitulo = function () {
                var titulo = $('#nomeFav', form.getElement()); //redundante
                preencherCampo(titulo, favorito.titulo);
            }
            var preencherUrl = function () {
                var url = $('#url-fav', form.getElement());
                preencherCampo(url, favorito.url);
            }
            var preencherDescricao = function () {
                var descricao = $('#textareaFav', form.getElement());
                preencherCampo(descricao, favorito.descricao);
            }
            var preencherCampo = function (campo, valor) {
                ativarCampo(campo);
                adicionarValorAoCampo(campo, valor);
            }
            var ativarCampo = function (campo) {
                $('label', campo.parent()).addClass('active');
            }
            var adicionarValorAoCampo = function (campo, valor) {
                campo.val(valor);
            }
            var preencherTags = function () {
                if (favorito.tags) {
                    tags.add(favorito.tags);
                }
            }
            var preencherEstrelas = function(){
            	estrelas.setScore(favorito.quantEstrelas);
            }
            preencherImagem();
            preencherTitulo();
            preencherDescricao();
            preencherUrl();
            preencherTags();
            preencherEstrelas();
        }
		return form;
        
	}
	
	this.FormImg = function(){
		var formImg = new ObjectHtml();
		var container = $('<div class="row">');

		function imgPreview(input) {
			if (input.files && input.files[0]) {
				var reader = new FileReader(); 
				reader.onload = function (e) {
					//@TODO 
					//VALIDAR TAMANHO DA IMAGEM
					//REMOVER PATH DE ARQUIVOS DIFERENTES IMAGEM DO CAMPO DE INPUT 
					//USABILIDADE SE O ARQUIVO ANTERIOR FOR UMA IMAGEM NÃO DAR REFRESH.
					if (input.files[0].type.indexOf("image") > -1){
						$('#imgPreview').attr('src', e.target.result);
					}else{
						refresh();
						Element.Toast("O arquivo selecionado não é uma imagem.",3000);
					}
				} 
				reader.readAsDataURL(input.files[0]);
			}
		}
		
		
		var createInputImg = function(){
			var imgAndInputUpload = $('<div class="s12 center"><img style="height:85px; width: 85px;" class="circle responsive-img valign" id="imgPreview" src="/GerenciadorDeFavoritos/view/img/semImagem.png"></div><div class="file-field input-field"><div class="btn"><span>File</span><input type="file" single id="imgUpload" accept="image/*"></div><div class="file-path-wrapper"><input class="file-path validate" type="text" placeholder="Upload one or more files"></div></div>');
			$("#imgUpload", imgAndInputUpload).change(function(){
				console.log(this);
				imgPreview(this);
		    });
			return imgAndInputUpload;
		}
		
		formImg.createElement = function(){
			container.append(createInputImg());
			return container;
		}
		
		var refresh = function(){
			formImg.element.empty();
			formImg.element.append(formImg.createElement()); 
		}
		
		formImg.add = function(urlImage){
			$('#imgPreview', formImg.getElement()).attr('src', urlImage);
		}
		return formImg;
	}
	
	this.MenuContexto = function(options){ //container, itens, acao
	    $.contextMenu({
	        selector: options.container
        	,
	        callback: options.acao
            ,
            items: options.itens
	    });
	}
	
	this.ListaDeFavoritosRapida = function(hierarquiaPasta, buscarArquivosDePasta, selecionarFavorito){
		var lista = new ObjectHtml();
		var arquivosContainer = $('<div>');
		
		lista.createElement = function(){
			var container = $('<div class="lista-favoritos-rapida">');
			var arvoreContainer = $('<div class="arvore-pastas">');
			
			arvoreContainer.append(criarArvore());
			
			container.append(arvoreContainer);
			container.append(arquivosContainer);
			
			return container;
		}
		var criarArvore = function(){
			var arvore = Element.Arvore(
					hierarquiaPasta
					,
					atualizarArquivosDePasta
				);
   			return arvore.getElement();
		}
		var atualizarArquivosDePasta = function(idPasta){
			buscarArquivosDePasta(
					idPasta
					,
					function(data){
					    var favoritos = data.content[0].favoritos;
						
					    arquivosContainer.html(criarItens(favoritos));
					    //limparContainer(containerBlocos);
						//inserirFavoritos(favoritos, containerBlocos);
						//Pesquisa.remover();
						
						//if (idPasta){
						//	contexto.pastaAtual = Number(idPasta);
						//}
					}
				);
			}
		var criarItens = function(favoritos){
			var container = $('<ul class="collection">');
			var preencherFavoritos = function(){
				var quantFavoritos = favoritos.length;
				for (var i = 0; i < quantFavoritos; i++) {
					adicionarFavorito(favoritos[i]);
				}
			}
			var adicionarFavorito = function(favorito){
				container.append(criarFavorito(favorito))
			}
			var criarFavorito = function(favorito){
				 var container = $('<a class="collection-item avatar">');
			     var img = $('<img alt="" class="circle">').attr("src", favorito.imagem);
			     var titulo = $('<span class="title">').append(favorito.titulo);
			     var descricao = $('<p>').append(favorito.descricao);
			     container.append(img);
			     container.append(titulo);
			     container.append(descricao);
			     container.on('click', function(){
			    	 desativarItensSelecionados();
			    	 container.addClass('active');
			    	 selecionarFavorito(favorito);
			     })
			     return container;
			}
			var desativarItensSelecionados = function(){
				$('a', container).removeClass('active');
			}
			preencherFavoritos();
			return container;
		}
		return lista;
	}
	
};
var Element = new Element();
