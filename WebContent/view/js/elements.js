
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

	var Bloco = function (title){
		var bloco = new ObjectHtml();
		bloco.createElement = function(){
			return $('<div>');
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
	}
}
