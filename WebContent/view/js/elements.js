
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

	var Bloco = function (icon, nomeFavorito, descricao, numEstrelas){
		var bloco = new ObjectHtml();
		bloco.createElement = function(){
		var card = $('<div class="col s12 m3 pasta-fechada drag">');
		var painel = $('<div class="card-panel card-complement cyan darken-2">');
		var conteudo = $('<div class="card-content white-text">');
		var div-icon = $('<div class=" col s4 pdzero">');
		var icon = $('<img class="responsive-img" src="' + icon +  '">');
		var div-dadosFavorito = $('<div class="col s8 cyan-text text-lighten-5 pdzero">');
		var div-tituloFavorito = $('<div class="col s12 titulo-favorito">');
		var titulo-favorito = $('<div class="col s10 truncate pdzero">');
		var tituloFavorito = $('<i class="mdi-action-stars yellow-text text-accent-2"></i>' + nomeFavorito);
		var div-opcoes = $('<div class="col s2">');
		var btOpcoes = $('<a class="dropdown-button cyan-text text-lighten-5" data-activates="dropdown10">');
		// Precisa adicionar as opções do dropown
		var icon-btOpcoes = $('<i class="mdi-navigation-more-vert"></i>');
		var div-estrelas = $('<div class="col s12">');
		// for numero de estrelas	
		var estrela = $('<i class="mdi-action-grade"></i>');
		var div-descricao = $('<div class="col s12 truncate">');
		var div-tags = $('<div class="col s12 truncate">');
		var icon-tag = $('<i class="mdi-maps-local-offer"></i>');
		
		
		card.append (painel);
		card.append (conteudo);
		conteudo.append (div-icon);
		div-icon.append (icon);
		conteudo.append (div-dadosFavorito);
		div-dadosFavorito.append (div-tituloFavorito)
		div-tituloFavorito.append (titulo-favorito)
		titulo-favorito.append (tituloFavorito)
		conteudo.append (div-opcoes);
		div-opcoes.append (btOpcoes);
		btOpcoes.append (icon-btOpcoes);
		conteudo.append (div-estrelas);
		div-estrelas.append (estrela);
		conteudo.append (div-descricao);
		div-descricao.append(descricao)
		conteudo.append (div-tags);
		div-tags.append (icon-tag); 
			return card;
		}
		return bloco;
	}
	
	this.Pasta= function (title){
		return "Pasta";
	} 
};
var materialize = new Materialize();

var Element = {
	Pasta: function(){ 
		return new materialize.Pasta(); 
	},
	Bloco: function(){ 
		return new materialize.Bloco(icon, nomeFavorito, descricao, numEstrelas); 
	}
}
