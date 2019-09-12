<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>KEAMA</title>

        <!-- JavaScript Chart, Jquery, treetable, metro-->
        <script src="js/jquery.js" type="text/javascript"></script>
        <script src="js/Chart.js" type="text/javascript"></script>
        <script src="js/metro.js" type="text/javascript"></script>
        <script src="js/jquery.treetable.js" type="text/javascript"></script>
        <script src="js/jquery.dataTables.js" type="text/javascript"></script>
        <script src="js/dataTables.buttons.min.js" type="text/javascript"></script>
        <script src="js/buttons.html5.min.js" type="text/javascript"></script>
        <script src="js/buttons.print.min.js" type="text/javascript"></script>
        <script src="js/pdfmake.min.js" type="text/javascript"></script>
        <script src="js/vfs_fonts.js" type="text/javascript"></script>
        <script src="js/buttons.flash.min.js" type="text/javascript"></script>
        <script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/processing.js/1.4.1/processing-api.min.js"></script>
        <script type="text/javascript" src="https://rawgithub.com/mozilla/pdf.js/gh-pages/build/pdf.js"></script>
        <script src="js/select2.js" type="text/javascript"></script>
        <script src="js/jquery-qrcode-0.14.0.js" type="text/javascript"></script>
        <script src="js/jquery-confirm.js"></script>
        <script src="js/viewer.js" type="text/javascript"></script>
        <script src="js/TimeCircles.js?id=3" type="text/javascript"></script>
        <!-- Fin JavaScript -->

        <!-- CSS  : TreeTable1, 2; Metro, -->
        <link href="css/jquery.treetable.css" rel="stylesheet" type="text/css"/>
        <link href="css/keama.css" rel="stylesheet" type="text/css"/>
        <link href="css/jquery.treetable.theme.default.css?modified=20012009" rel="stylesheet" type="text/css"/>
        <link href="css/metro.css?id=1" rel="stylesheet" type="text/css"/>
        <link href="css/metro-colors.css" rel="stylesheet" type="text/css"/>
        <link href="css/metro-icons.css" rel="stylesheet" type="text/css"/>
        <link href="css/metro-responsive.css" rel="stylesheet" type="text/css"/>
        <link href="css/metro-rtl.css" rel="stylesheet" type="text/css"/>
        <link href="css/metro-schemes.css" rel="stylesheet" type="text/css"/>
        <link href="css/jquery.dataTables.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/buttons.dataTables.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/select2.css?id=2" rel="stylesheet" type="text/css"/>
        <link href="css/jquery-confirm.css?id=1" rel="stylesheet" type="text/css"/>
        <link href="css/viewer.css" rel="stylesheet" type="text/css"/>
        <link href="css/TimeCircles.css" rel="stylesheet" type="text/css"/>
        <!-- Fin CSS -->

        <!-- ANGULAR -->
        <script src="js/angular.js" type="text/javascript"></script>
        <script src="js/angular-route.js" type="text/javascript"></script>
        <!-- FIN ANGULAR -->

        <script>
            var app = angular.module('workspace', ['ngRoute']);

            app.config(function ($routeProvider) {
                $routeProvider
                        .when('/', {templateUrl: 'RoueTacheServlet'})
                        .when('/nouveau', {templateUrl: 'NouvelleTacheServlet'})
                        .when('/id/:id', {templateUrl: function (params) {
                                return 'NouvelleTacheServlet?id=' + params.id;
                            }})
                        .when('/tous', {templateUrl: 'TousTacheServlet'})
                        .when('/mes', {templateUrl: 'MesTachesServlet'})
                        .when('/mes/:id', {templateUrl: function (params) {
                                return 'MesTachesServlet?id=' + params.id;
                            }})
                        .when('/avenir', {templateUrl: 'AVenirTacheServlet'})
                        .when('/avenir/:id', {templateUrl: function (params) {
                                return 'AVenirTacheServlet?id=' + params.id;
                            }})
                        .when('/avenir/:id/:idtache', {templateUrl: function (params) {
                                return 'AVenirTacheServlet?id=' + params.id + '&idtache=' + params.idtache;
                            }})
                        .when('/evaluation', {templateUrl: 'EvaluationTacheServlet'})
                        .when('/evaluation/:id', {templateUrl: function (params) {
                                return 'EvaluationTacheServlet?id=' + params.id;
                            }})
                        .when('/roue', {templateUrl: 'RoueTacheServlet'})
                        .when('/roue/:id', {templateUrl: function (params) {
                                return 'RoueTacheServlet?id=' + params.id;
                            }})
                        .when('/procedures', {templateUrl: 'ProcedureServlet'})
                        .when('/procedures/:id', {templateUrl: function (params) {
                                return 'ProcedureServlet?id=' + params.id;
                            }})
                        .when('/procedures_encours', {templateUrl: 'ProcedureEffectiveServlet'})
                        .when('/procedures_encours/:id', {templateUrl: function (params) {
                                return 'ProcedureEffectiveServlet?id=' + params.id;
                            }})
                        .when('/taches_procedures', {templateUrl: 'TacheProcedureServlet'})
                        .when('/taches_procedures/:id', {templateUrl: function (params) {
                                return 'TacheProcedureServlet?id=' + params.id;
                            }})
                        .otherwise({redirectTo: '/'});
            });

            $(document).ready(function () {
                function showDialog(id) {
                    var dialog = $(id).data('dialog');
                    dialog.open();
                    //$("#keama_back").css("opacity", "0.2");

                }
            });

        </script>


    </head>
    <body class="metro " ng-app="workspace">

        <div class="keama_haut">
            <div class="fg-white keama_haut_texte">
                ${entreprise.nom}
                <h1 class="keama_h1 fg-blue">WORKSPACE <sup><span id="spinner" class="mif-spinner mif-ani-spin fg-white"></span></sup></h1>

            </div>
        </div>
        <div class=" ">
            <div id="menu"  class="app-bar navy" data-role="appbar">
                <a class="app-bar-element branding mif-apps"> <span onclick="toggleMetroCharm('#left-charm')" class="mif-apps mif-2x"></span></a>
                <span class="app-bar-divider"></span>
                <ul class="app-bar-menu">
                    <li><a onclick="showDialog('#nouveau_courrier')">Nouveau</a></li>
                    
                    <li><a href="#mes">Tâches attribuées</a></li>
                    
                    <li>
                        <a href="" class="dropdown-toggle">Tâches à exécuter</a>
                        <ul class="d-menu small-dropdown" data-role="dropdown">
                            <li><a href="#taches_procedures">Tâches procédures à exécuter</a></li>
                            <li><a href="#avenir">Autres tâches à exécuter</a></li>
                        </ul>
                    </li>
                    <li><a href="#procedures_encours">Procédures en cours</a></li>
                    <li><a href="#tous">Tous</a></li>
                    <span class="app-bar-divider"></span>
                    <li><a href="#evaluation">Evaluation</a></li>
                    <span class="app-bar-divider"></span>
                    <li><a href="#roue">Tableau de bord</a></li>
                    <li>
                        <a href="" class="dropdown-toggle">Paramètres</a>
                        <ul class="d-menu small-dropdown" data-role="dropdown">
                            <li><a class="lien" href="#procedures">Procédures</a></li>
                        </ul>
                    </li>

                </ul>
                <div class="app-bar-element place-right">
                    <span class="dropdown-toggle"><span class="mif-cog"></span> ${empty utilisateur.employe.noms ? utilisateur.login : utilisateur.employe.noms}</span>
                    <div class="app-bar-drop-container padding10 place-right no-margin-top block-shadow fg-dark" data-role="dropdown" data-no-close="true" style="width: 220px">
                        <!--                        <h2 class="text-light">Quick settings</h2>-->
                        <ul class="unstyled-list fg-dark">
                            <!--                            <li><a href="" class="fg-white1 fg-hover-yellow">Profile</a></li>
                                                        <li><a href="" class="fg-white2 fg-hover-yellow">Security</a></li>-->
                            <li><a href="deconnexion" class="fg-white fg-hover-yellow">Exit</a></li>
                        </ul>
                    </div>
                </div>

            </div>
        </div>

        <div class="" ng-view>

        </div>
        <div data-role="charm" data-position="left" id="left-charm">
            <div style="padding: 20px; padding-right: 30px; width: 400px">
                <h1 class="keama_h1 fg-blue">WORKSPACE</h1>
                <span style="cursor: pointer" onclick="window.location.href = 'start'">
                    Revenir au menu principal 
                </span>
                <div style="padding-top: 40px;  padding-bottom: 20px; width: 200px">
                    <span class="fg-blue"></span>
                </div>

                <div onclick="window.location.href = 'courrier'" class="tile rfg-white" data-role="tile" style="background: none !important">
                    <div class="tile-content  iconic">
                        <span class="icon mif-mail-read"></span>
                    </div>
                    <span class="tile-label align-center">Courriers</span>
                </div>
                <div onclick="window.location.href = 'reunion'" class="tile ribbed-gray fg-white" data-role="tile" style="background: none !important">
                    <div class="tile-content iconic">
                        <span class="icon mif-calendar"></span>
                    </div>
                    <span class="tile-label">Réunions</span>
                </div>
                <a href="tache" class="tile ribbed-gray fg-white" data-role="tile" style="background: none !important">
                    <div class="tile-content iconic">
                        <span class="icon mif-clipboard"></span>
                    </div>
                    <span class="tile-label">Tâches</span>
                </a>
                <a href="information" class="tile ribbed-gray fg-white" data-role="tile" style="background: none !important">
                    <div class="tile-content iconic">
                        <span class="icon mif-info"></span>
                    </div>
                    <span class="tile-label">Informations</span>
                </a>
                <a href="suggestion" class="tile ribbed-gray fg-white" data-role="tile" style="background: none !important">
                    <div class="tile-content iconic">
                        <span class="icon mif-bubbles"></span>
                    </div>
                    <span class="tile-label">Suggestions</span>
                </a>
                <div class="tile ribbed-gray  fg-white" data-role="tile" style="background: none !important">
                    <div class="tile-content iconic">
                        <span class="icon mif-envelop"></span>
                    </div>
                    <span class="tile-label">Messages</span>
                </div>
                <div onclick="window.location.href = 'start'" class="tile tile-wide rfg-white" data-role="tile" style="background: none !important">
                    <div class="tile-content ribbed-blue iconic">
                        <span class="icon mif-home"></span>
                    </div>
                    <p style="padding-left: 40px ;" class="tile-label align-center">Revenir au menu principal</p>
                </div>
            </div>
        </div>
        <div data-role="dialog, draggable" id="nouveau_courrier" class="grid padding40 bg-blue" data-close-button="true" data-windows-style="true" >
            <div class="row cells3">
                <div class="cell colspan2">
                    <div class="row cells12">
                        <div class="cell colspan9">
                            <h1 class="fg-white">Nouvelle Tâche</h1>
                            <p class="fg-white" style="padding-bottom: 20px;">
                                Vous pouvez créer une nouvelle tâche et la confier à un ou plusieurs participants.
                            </p>
                            <form method="post" action="NouvelleTacheServlet" class="grid condensed">
                                <div class="row cells6">
                                    <div class="cell colspan6">
                                        <label class="fg-white">Libellé</label>
                                        <div class="input-control text full-size ${!empty tache ? 'success': ''}">
                                            <input name="libelle" value="${tache.libelle}" placeholder="" >
                                            <input name="id" value="${tache.idtache}" type="hidden">
                                            <input name="employe" value="${utilisateur.employe.idemploye}" type="hidden">
                                        </div>
                                    </div>
                                </div>
                                <div class="row cells">
                                    <div class="cell" style="padding-right: 8px;">
                                        <label class="fg-white">Exécutants</label>
                                        <div class="input-control full-size" data-role="select" data-multiple="true" data-placeholder="Choisissez les personnes à convoquer">
                                            <select class="js-example-basic-multiple full-size" name="employes">
                                                <c:forEach items="${employes}" var="e">
                                                    <option value="${e.idemploye}">${e.noms} ${e.prenoms}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="row cells3">
                                    <div class="cell colspan1" style="padding-right: 10px;">
                                        <label class="fg-white">Date début</label>
                                        <c:set var="now" value="<%=new java.util.Date()%>" />
                                        <c:if test="${!empty tache}">
                                            <fmt:formatDate pattern="yyyy-MM-dd" value="${tache.dateDebut}" var="dateDebut"/>
                                            <div class="input-control text full-size" data-role="datepicker" data-preset="${dateDebut}">
                                                <input type="text" name="date_debut">
                                                <button class="button"><span class="mif-calendar"></span></button>
                                            </div>
                                        </c:if>
                                        <c:if test="${empty tache}">
                                            <fmt:formatDate pattern="yyyy-MM-dd" value="${now}" var="dateEmission"/>
                                            <div class="input-control text full-size ${!empty tache ? 'success': ''}" data-role="datepicker" data-preset="${dateEmission}">
                                                <input type="text" name="date_debut">
                                                <button class="button"><span class="mif-calendar"></span></button>
                                            </div>
                                        </c:if>
                                    </div>
                                    <div class="cell colspan1" style="padding-right: 10px;">
                                        <label class="fg-white">Date fin</label>
                                        <c:set var="now" value="<%=new java.util.Date()%>" />
                                        <c:if test="${!empty tache}">
                                            <fmt:formatDate pattern="yyyy-MM-dd" value="${tache.dateFin}" var="dateFin"/>
                                            <div class="input-control text full-size" data-role="datepicker" data-preset="${dateFin}">
                                                <input type="text" name="date_fin">
                                                <button class="button"><span class="mif-calendar"></span></button>
                                            </div>
                                        </c:if>
                                        <c:if test="${empty tache}">
                                            <fmt:formatDate pattern="yyyy-MM-dd" value="${now}" var="dateEmission"/>
                                            <div class="input-control text full-size ${!empty tache ? 'success': ''}" data-role="datepicker" data-preset="${dateEmission}">
                                                <input type="text" name="date_fin">
                                                <button class="button"><span class="mif-calendar"></span></button>
                                            </div>
                                        </c:if>
                                    </div>
                                    <div class="cell colspan1">
                                        <label class="fg-white">Attribué le :</label>
                                        <c:set var="now" value="<%=new java.util.Date()%>" />
                                        <c:if test="${!empty tache}">
                                            <fmt:formatDate pattern="yyyy-MM-dd" value="${tache.dateEmission}" var="dateEmission"/>
                                            <div class="input-control text full-size" data-role="datepicker" data-preset="${dateEmission}">
                                                <input type="text" name="date_emission" readonly>
                                                <button class="button"><span class="mif-calendar"></span></button>
                                            </div>
                                        </c:if>
                                        <c:if test="${empty tache}">
                                            <fmt:formatDate pattern="yyyy-MM-dd" value="${now}" var="dateEmission"/>
                                            <div class="input-control text full-size ${!empty tache ? 'success': ''}" data-role="datepicker" data-preset="${dateEmission}">
                                                <input type="text" name="date_emission" readonly>
                                                <button class="button"><span class="mif-calendar"></span></button>
                                            </div>
                                        </c:if>
                                    </div>
                                </div>

                                <div class="row cells6">
                                    <div class="cell colspan6">
                                        <label class="fg-white">Commentaire</label>
                                        <div class="input-control textarea full-size ${!empty tacheZ ? 'success': ''}" data-role="input" data-text-auto-resize="true">
                                            <textarea name="description">${tacheZ.note}</textarea>
                                        </div>
                                    </div>
                                </div>

                                <div class="row cells6">
                                    <div class="cell colspan6">
                                        <br>
                                        <c:if test="${empty tacheZ}">
                                            <input type="submit" value="Enregistrer" class="button ribbed-green fg-white">
                                        </c:if>
                                        <c:if test="${!empty tacheZ}">
                                            <a onclick="terminer(${tacheZ.idtacheZ});" style="cursor : pointer;" class="button ribbed-green fg-white">Terminé</a>
                                        </c:if>
                                    </div>
                                </div>

                            </form>
                        </div>
                    </div>
                </div>
                <div class="cell">
                    <div class="row cells2">
                        <div class="cell">

                        </div>
                        <div class="cell">

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
