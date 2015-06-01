/*
Dependences
Bootstrap
*/


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
	this.event = function (typeEvent, callback) {
		this.getElement().on(typeEvent, callback);
	}
	this.setId = function (value) {
		this.getElement().attr('id', value);
	}
	this.setText = function (text) {
		this.getElement().html(text);
	}
	this.addAtribbute = function (param, value) {
		this.getElement().attr(param, value);
	}
	this.addClass = function (className) {
		this.getElement().addClass(className);
	}
}

var Bootstrap = function () {
	var bootstrap = this;
	/*
		class Tabela
	*/
	this.Tabela = function (columnTitleArray, contentArray) {
        var tabela = new ObjectHtml();
        var tHeadContainer = undefined;
		var tBodyContainer = undefined;
		
		tabela.getTHead = function () {
			if(tHeadContainer == undefined){
				var tHead = $('<thead>');
				tHead.append(getLine(columnTitleArray));
				tHeadContainer = tHead;
			} 
            return tHeadContainer;
        }
        tabela.getTBody = function () {
			if(tBodyContainer == undefined){
				var tBody = $('<tbody>');
				$.each(contentArray, function (index, lineContent) {
					tBody.append(getLine(lineContent));
				});
				tBodyContainer = tBody;
			}
            return tBodyContainer;
        }
		var getLine = function(array){
            var line = $('<tr>');
            $.each(array, function (index, content) {
				var coluna = $('<th>');
				coluna.append(content)
                line.append(coluna);
            });
            return line;
		}
		tabela.createElement = function () {
			var tableContainer = $('<table class="table table-striped">');
			tableContainer.append(tabela.getTHead());
			tableContainer.append(tabela.getTBody());
            return tableContainer;
        }
        return tabela;
    }
	
	/*
		class Button
	*/
	this.Button = function (title){
		var button = new ObjectHtml();
		button.createElement = function () {
			var container = $('<a class="btn btn-default">');
			if(title){
				container.html(title);
			}
			return container;
		}
		button.addGlyphicon = function(glyphiconClass){
			var span = $('<span>');
			span.addClass(glyphiconClass);
			button.getElement().append(span);
		}
		
		return button;
	}
	
	/*
		class Modal
	*/
	this.Modal = function (title, bodyContent, footerContent) {
            var modal = new ObjectHtml();

            modal.createElement = function () {
                var getHeader = function () {
                    var container = $('<div class="modal-header">');
                    var closeButton = $('<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>');
                    container.append(closeButton);
                    if (title) {
                        container.append(title);
                    }
                    return container;
                }
                var getBody = function () {
                    var container = $('<div class="modal-body">');
                    if (bodyContent) {
                        container.append(bodyContent);
                    }
                    return container;
                }
                var getFooter = function () {
                    var container = $('<div class="modal-footer">');
                    if (footerContent) {
                        container.append(footerContent);
                    }
                    return container;
                }
                var container = $('<div class="modal fade" role="dialog">');
                var dialog = $('<div class="modal-dialog">');
                var content = $('<div class="modal-content">');
                content.append(getHeader());
                content.append(getBody());
                content.append(getFooter());
                dialog.append(content);
                container.append(dialog);
                return container;
            }

            modal.getElement = function () {
                if (this.element == undefined) {
                    this.element = this.createElement();
                }                
                return this.element;
            }
			
			modal.show = function(){
				var containerModal = $('<div id="modal">');
                containerModal.append(modal.getElement());
                $('#modal').remove();
                $('body').append(containerModal);
                modal.getElement().modal('show');
			};

            return modal;
        }
	// class ConfirmModal extends Modal
	this.ConfirmModal = function(title, message, callback){
		var getButtons = function(){
			var container = $('<div>');
			var ok = new bootstrap.Button("OK");
			ok.event('click', function(){
				callback();
			})
			ok.addAtribbute('data-dismiss',"modal");
			var cancel = new bootstrap.Button("Cancel");
			cancel.addAtribbute('data-dismiss',"modal");
			container.append(ok.getElement());
			container.append(cancel.getElement());
			return container;
		}
		var modal = new bootstrap.Modal(title, message, getButtons());
		return modal;
	}
	
	/*
		class Form
	*/
	this.Form = function(){
		var form = new ObjectHtml();
		form.createElement = function(){
			return $('<form>');
		}
		form.addInput = function(label, id, placeholder, defaultValue){
			var input = getFormInput(label, id, placeholder, defaultValue);
			form.getElement().append(input);
			return input;
		}
		var getFormInput = function(label, id, placeholder, defaultValue){
			var getInput = function(){
				var input = $('<input type="text" class="form-control">') 
				input.attr('name', id);
				input.attr('id', id);
				input.attr('placeholder', placeholder);
				if (defaultValue){
					input.val(defaultValue);
				}
				return input;
			}
			
			var group = getFormGroup();
			group.append(getLabel(label, id));
			group.append(getInput());
			return group;
		}
		
		form.addRadio = function (label, id, content){
			var input = getRadioContainer(label, id, content);
			form.getElement().append(input);
		}
		//content = [{label="", value=""},...]
		var getRadioContainer = function(label, id, content){
			var getRadio = function(){
				var getInput = function(label, value){
					var container = $('<label>');
					var input = $('<input type="radio">') 
					input.attr('name', id);
					input.attr('value', value);
					input.attr('checked', true);
					container.append(input);
					container.append(label);
					return container;
				}
				
				var container = $('<div class="radio">');
				container.attr('id', id);
				container.attr('name', id);
				$.each(content, function(index, item){
					container.append(getInput(item.label, item.value));
					container.append("<br>");
				})
				return container;
			}
			
			var group = getFormGroup();
			group.append(getLabel(label, id));
			group.append(getRadio());
			return group;				
		}
		
		form.addSelect = function(label, id, content){
			var select = getSelectContainer(label, id, content);
			form.getElement().append(select);
			
		}
		var getSelectContainer = function(label, id, content){
				var getOption = function(label, value){
					var option = $('<option>');
					option.attr('value', value);
					option.append(label);
					return option;
				}
				
				var getSelect = function(){
					var select = $('<select class="form-control">');
					select.attr("name",id);
					select.attr("id",id);
					$.each(content, function(index, item){
						select.append(getOption(item.label, item.value));
					})
					return select;
				}
				
				var container = getFormGroup();
				container.append(getLabel(label,id));
				container.append(getSelect());
				return container;
		}
		
		
		var getFormGroup = function(){
			return $('<div class="form-group">');
		}
		var getLabel = function(label, id){
			var labelContainer = $('<label>');
			labelContainer.attr('for',id);
			labelContainer.append(label)
			return labelContainer; 
		}
		return form;
	}

	/**/
	DatePicker= function () {
        var datePicker = new ObjectHtml();
        datePicker.createElement = function () {
            datePicker.html = $('<input type="text" class=" form-control">').datepicker({ format: "dd/mm/yyyy"});
            return datePicker.html;
        }
        datePicker.setDate = function (date) {
            datePicker.getHtml().datepicker('setDate', date);
        }
        datePicker.getDate = function () {
            return datePicker.getHtml().datepicker('getDate');
        }
        return datePicker;
    }
};
var Bootstrap = new Bootstrap();

var Element = {
	Button: function(title){ 
		return new Bootstrap.Button(title); 
	}
	,
	Modal: function(headerContent, bodyContent, footerContent){
		return new Bootstrap.Modal(headerContent, bodyContent, footerContent);
	}
	,
	ConfirmModal: function(title, message, callback){
		return new Bootstrap.ConfirmModal(title, message, callback);
	}
	,
	Tabela: function(columnTitleArray, contentArray){
		return new Bootstrap.Tabela(columnTitleArray, contentArray);
	}
	,
	Form: function(){
		return new Bootstrap.Form();
	}
	,
	DatePicker: function(){
		return new Bootstrap.DatePicker();
	}
}
//window.Element = new Elements();

