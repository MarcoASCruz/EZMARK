<%@page import="org.json.JSONObject"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelos.Pasta"%>
<%@page import="java.util.List"%>
<% 
	JSONObject pasta = (JSONObject) request.getAttribute("pasta");
	JSONObject erro = (JSONObject) request.getAttribute("erro");
%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>EZMARK - Pasta Compartilhada</title>
	<link rel="icon" type="image/png" sizes="16x16" href="assets/img/favicon.png">
	<style> 
        	.arrastar-favorito-msg{
	        	font-size: 30px;
			    font-weight: bold;
			    color: #C7C7C7;
			    margin-top: 35px;
        	}
        	.border-dashed{
        	border-style: dashed;
        	border-color: #C7C7C7;
        	}
        	/*Ajustes no bug da modal que decrementa z-index*/
        	.lean-overlay{
        		z-index: 999 !important;
        	}
        	.modal{
        	    z-index: 1003 !important;
        	}
        
        	.container-favorito-icon .indicator{
        		height: 4px;
        	}
        	.favorito-icon{
        		cursor: pointer;
        	}
        	#acessoRapido,
        	#maisAcessados,
        	#recemAcessados,
        	#recemAdicionados{
        		min-height: 125px;
        	}
        	
	        form .cancel-on-png,
			form .cancel-off-png,
			form .star-on-png,
			form .star-off-png,
			form .star-half-png{
				font-size: 2em;
			}      	
        	div.tagsinput{
        		float: right;
        	}
        	div.tagsinput input{
        		min-width: 130px;
        	}
        	
        	
        	.side-nav .jstree  li {
        		padding: 0px;
        	}
        	
        	.side-nav .jstree li:hover, .side-nav li.active {
			    background-color: transparent; 
			}
        	
        	.side-nav.fixed .jstree a{
			    display: inline-block;
			    padding: 0 4px 0 1px;
			    margin: 0;
			}
			
			.side-nav.fixed a.jstree-clicked {
				color: white;
				background: #0097a7;
			}
			
			.side-nav .jstree i{
				font-size: 20px;
			}
        	
            .gira {
	             -webkit-transition: all 0.5s ease 0s;
	             -moz-transition: all 0.5s ease 0s;
	             -o-transition: all 0.5s ease 0s;
	             -ms-transition: all 0.5s ease 0s;
	             transition: all 0.5s ease 0s;
	             cursor:pointer;
             }
             .gira:hover {
	             -webkit-transform: rotate(45deg);
	             -moz-transform: rotate(45deg);
	             -ms-transform: rotate(45deg);
	             -o-transform: rotate(45deg);
	             transform: rotate(45deg);
             }
            .conteudo{
	            padding: 0 0.5rem;
	            margin: 0 auto;
	            max-width: 1366px;
	            width: 100%;
            }
            .titulo-favorito{
	            color: #fff;
	            font-size: 20px;
	            font-weight: 300;
            }
            .titulo-favorito-destaque {
	            color: #fff;
	            font-size: 18px;
	            font-weight: 300;
	            line-height: 1.25;
            }
            .main{
	            padding-left: 240px; 
	            padding-top: 20px;
            }
            .pdzero{
            	padding: 0!important;
            }
            .marginzero{
            	margin: 0!important;
            }
            .item-acesso-rapido{
            	min-width: 110px;
            }
            .overflow-y-auto{
            	overflow-y: auto;
            }
            .marRL5{
	            margin-right: 5px;
	            margin-left: 5px;
            }
            .marRL15{
	            margin-right: 15px;
	            margin-left: 15px;
            }
            .marT15{
            	margin-top: 15px;
            }
            .usuario-logado-bar{
	            max-width: 300px; 
	            height: 100%;
	            padding-right: 5px;
	            padding-left: 5px;
            }
            .pasta-fechada{
	            min-width: 300px;
	            min-height: 135px;
            }
            .favorito{
	            min-width: 102px;
	            max-height: 160px;
            }
            [type="checkbox"]:checked+label:before{
	            border-right-color: #00565F !important;
	            border-bottom-color: #00565F !important;
            }
            .card-complement{
	            overflow: hidden;
	            padding: 10px;
            }

            @media (max-width: 992px){
            	.main{
            		padding-left: 20px; 
            	}
                    
                
    </style>
    
    
	
	<link rel="stylesheet" type="text/css" href="/assets/lib/tagsInput/jquery.tagsinput.css" />
    <link type="text/css" rel="stylesheet" href="/assets/lib/materialize/v0.97.0/assets/css/materialize.min.css"  media="screen,projection"/>
    <link type="text/css" rel="stylesheet" href="/assets/lib/jstree/dist/themes/default/style.min.css" />
    <link rel="stylesheet" type="text/css" href="/assets/lib/raty-2.7.0/jquery.raty.css" />
    <link rel="stylesheet" type="text/css" href="/assets/css/elements.css" />
    
</head>
<body>
	<body style="background-color: #fcfcfc">
	    <!--Menu Lateral Fixo-->
	    <ul id="slide-out" class="side-nav fixed" style="padding: 15px;"> 
	        <div id="treeView" class=""></div>
	    </ul>
	    <!--Fim do Menu Lateral Fixo-->
	    <!--Barra de Navegação/Superior-->
	    <div class="navbar-fixed">
	        <nav>
	            <div class="nav-wrapper cyan darken-2">
	                <a href="#!" class="brand-logo" style="padding-left: 270px; color: #e0f7fa; font-weight: bold;">EZMARK</a>
	                <a href="#" data-activates="mobile-demo" class="button-collapse"><i class="mdi-navigation-menu"></i></a>
	                
	                <!--Fim da Barra de Navegação Superior -->  
	                <!--Menu Lateral quando acionado o modo Mobile-->  
	                <ul class="side-nav" id="mobile-demo">
	                    <!--Usuário Logado Menu Lateral Mobile--> 
	                    <div class="row">
	                        <div class="col s3">
	                            <img class="responsive-img circle left marRL5 marT15" style="max-width: 30px;" src="assets/img/userIcon.png">
	                        </div>
	                        <div class="col s9 truncate">
	                            <spam class="titulo-favorito marRL5 grey-text text-darken-4">Usuário</spam>
	                        </div>
	                        <div class="col s12 pdzero">
	                            <a class="btn-flat waves-effect pdzero" href='#'>
	                            <span class="mdi-action-settings marRL5" style="line-height: 41px; font-size: 1.5rem;"></span>Confiurações
	                            </a>
	                        </div>
	                        <div class="col s12 pdzero">
	                            <a class="btn-flat waves-effect pdzero" href='#'>
	                            <span class="mdi-action-exit-to-app marRL5" style="line-height: 41px; font-size: 1.5rem;"></span>Logout
	                            </a>
	                        </div>
	                        <li class="divider"></li>
	                    </div>
	                    <!--Fim do Usuário Logado Menu Lateral Mobile--> 
	                    <!--Formulário de Pesquisa Menu Lateral Mobile--> 
	                    <form>
	                        <div class="row">
	                            <div class="col s12 pdzero">
	                                <a class="btn-flat waves-effect pdzero" href='#'>
	                                <span class="mdi-editor-format-list-bulleted marRL5" style="line-height: 41px; font-size: 1.5rem;"></span>Ordenar
	                                </a>
	                            </div>
	                            <div class="col s2">
	                                <span class="mdi-action-search marRL5 grey-text text-darken-4" style="line-height: 41px; font-size: 1.5rem;"></span>
	                            </div>
	                            <div class="col s10">
	                                <input class="grey-text text-darken-4" type="text" placeholder="Pesquisar">
	                            </div>
	                            <div class="col s10">
	                                <input type="checkbox" id="check-tagsm" />
	                                <label class="grey-text text-darken-4" for="check-tagsm">Favoritos</label>
	                            </div>
	                            <div class="col s10">
	                                <input type="checkbox" id="check-tagsmm" />
	                                <label class="grey-text text-darken-4" for="check-tagsmm">Pastas</label>
	                            </div>
	                            <div class="col s10">
	                                <input type="checkbox" id="check-tagsmmm" />
	                                <label class="grey-text text-darken-4" for="check-tagsmmm">Tags</label>
	                            </div>
	                        </div>
	                    </form>
	                    <!--Fim do Formulário de Pesquisa Menu Lateral Mobile-->
	                </ul>
	                <!--Fim do Menu Lateral quando acionado o modo Mobile-->   
	            </div>
	        </nav>
	    </div>
	    <!--Fim da Barra de Navegação-->   
	    <!--Corpo do conteúdo da página-->  
	    <main class="main">   
	        <div id="conteudo" class="conteudo"> 
	           
	            <div id="conteudoBlocos" class="row">
	            </div>
	        </div>
	    </main>
	</body>
</body>

    <script type="text/javascript" src="/assets/lib/jquery-1.11.2/jquery.min.js"></script>
    <script src="/assets/lib/tagsInput/jquery.tagsinput.js"></script>

    <script type="text/javascript" src="/assets/lib/materialize/v0.97.0/assets/js/materialize.min.js"></script>
    <!--Inicializa o Menu lateral quando em modo Mobile-->
    <script src="/assets/lib/jstree/dist/jstree.min.js"></script>
    <script src="/assets/lib/raty-2.7.0/jquery.raty.js"></script>
    <script src="/assets/js/elements.js"></script>

<script>
		var limparContainer = function (container) {
		    container.empty();
		}
		var erroMsg = (<%=erro%>);
		if (erroMsg){
			console.log("erro: ", erroMsg);
			var containerBlocos = $('#conteudoBlocos');
		    limparContainer(containerBlocos); 
		    var blocoMensagem = Element.BlocoMensagem(erroMsg.titulo, erroMsg.erro);
		    containerBlocos.append(blocoMensagem.getElement());
		}else{
			var pastaHome = (<%=pasta%>).pasta[0];
			console.log(pastaHome)
			
			var arvore = undefined;
		    var contexto = {
		    	home: undefined
		    	,
				pastaAtual: undefined
		    }
		    
		    var init = function(){
		    	var buscarHome = function(){
		    		var idHome = pastaHome.id;
		        	contexto.home = idHome;
		        	atualizarPasta(idHome);
		    	}
		    	var buscarHierarquiaDePastas = function(){
		    		var criarHierarquia = function(){
		    			var hierarquia = new Array();
			    		
		    			var adicionarSubpastas = function(subpastas){
			    			$.each(subpastas, function(index, pasta){
			    				if(pasta.pastas.length > 0){
			    					adicionarSubpastas(pasta.pastas);
			    				}
			    				hierarquia.push({
					    			id: pasta.id,
					    			parent: pasta.pai,
					    			text: pasta.nome
				    			})
			    			})
			    		}
		    			
		    			hierarquia.push({
			    			id: pastaHome.id,
			    			parent: "#",
			    			text: pastaHome.nome
		    			})
		    			
			    		adicionarSubpastas(pastaHome.pastas)
		    			return hierarquia;
		    		}
		   			arvore = Element.Arvore(criarHierarquia(), atualizarPasta);
		   			$('#treeView').append(arvore.getElement());
		   		}
			    buscarHome();
			    buscarHierarquiaDePastas();
		    }
		    
			var atualizarPasta = function(idPasta){
				var buscarArquivos = function(onSucess){
					idPasta = idPasta ? idPasta : contexto.pastaAtual;
							
					var procurarPasta = function(pastas){
						$.each(pastas, function(index, pasta){
		    				if(pasta.id == idPasta){
		    					onSucess(pasta);
		    				}
		    				procurarPasta(pasta.pastas);
		    			})
		    		}
					
					if (idPasta == pastaHome.id){
						onSucess(pastaHome);
					}
					else{
						return 	procurarPasta(pastaHome.pastas);
					}
				}
				buscarArquivos(
					function(data){
						var pastas = data.pastas;
					    var favoritos = data.favoritos;
						
					    var containerBlocos = $('#conteudoBlocos');
					    limparContainer(containerBlocos);
						inserirPastas(pastas, containerBlocos);
						inserirFavoritos(favoritos, containerBlocos);
						
						if (idPasta){
							contexto.pastaAtual = Number(idPasta);
						}
					}
				);
			}
			
			
			var inserirPastas = function (pastas, container) {
		        var acoes = {
		            onClick: function (pasta) {
		                arvore.selecionarItem(pasta.id);
		            } 
		        }
		        var length = pastas.length;
		        for (var i = 0; i < length; i++) {
		            var bloco = Element.Pasta(pastas[i], acoes, false);
		            container.append(bloco.getElement());
		        }
		    }
			var inserirFavoritos = function (favoritos, container) {
		        var acoes = {
		            onClick: function (favorito) {
		            	abrirLinkFav(favorito.url, favorito.id);
		            }
		        }
		        var length = favoritos.length;
		        for (var i = 0; i < length; i++) {
		            var bloco = Element.Favorito(favoritos[i], acoes, false);
		            container.append(bloco.getElement());
		        }
		    }
			
			var abrirLinkFav = function (url, idFav) {
	            window.open('http://' + url);
	        }
		
    		init();
		}
	</script>

</html>