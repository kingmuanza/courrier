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
        <link href="css/select2.css" rel="stylesheet" type="text/css"/>
        <link href="css/buttons.dataTables.min.css" rel="stylesheet" type="text/css"/>
        <!-- Fin CSS -->

        <!-- ANGULAR -->
        <script src="js/angular.js" type="text/javascript"></script>
        <script src="js/angular-route.js" type="text/javascript"></script>
        <!-- FIN ANGULAR -->

        <script>
            var app = angular.module('workspace', ['ngRoute']);

            app.config(function ($routeProvider) {
                $routeProvider
                        .when('/', {templateUrl: 'TousSuggestionsServlet'})
                        .when('/nouveau', {templateUrl: 'nouveauCourrier'})
                        .when('/id/:id', {templateUrl: function (params) {
                                return 'nouveauCourrier?id=' + params.id;
                            }})
                        .when('/creation/:id', {templateUrl: function (params) {
                                return 'NouveauCourrierServlet2?id=' + params.id;
                            }})
                        .when('/tous', {templateUrl: 'TousSuggestionsServlet'})
                        .when('/entrants', {templateUrl: 'EntrantCourrierServlet'})
                        .when('/transmis', {templateUrl: 'TransmisCourrierServlet'})
                        .when('/ignores', {templateUrl: 'IgnoreCourrierServlet'})
                        .when('/prets', {templateUrl: 'PretCourrierServlet'})
                        .when('/voir', {templateUrl: 'VoirCourrierServlet'})
                        .when('/voir/:id', {templateUrl: function (params) {
                                return 'VoirCourrierServlet?id=' + params.id;
                            }})
                        .when('/prise', {templateUrl: 'PriseCourrierServlet'})
                        .when('/prise/:id', {templateUrl: function (params) {
                                return 'PriseCourrierServlet?id=' + params.id;
                            }})

                        .when('/datatable', {templateUrl: 'jspDataTable.jsp'})
                        .when('/treetable', {templateUrl: 'jspTreeTable.jsp'})
                        .when('/chart', {templateUrl: 'jspChart.jsp'})
                        .when('/visualiser', {templateUrl: 'jspVisualiser.jsp'})
                        .when('/formulaire', {templateUrl: 'jspVisualiser.jsp'})
                        .when('/save', {templateUrl: 'jspSaveFile.jsp'})
                        .otherwise({redirectTo: '/'});
            });



            $(document).ready(function () {
                //Réception et affichage de l'image
                function readURL(input) {
                    var filesAmount = input.files.length;
                    $('#nb_fichiers').html(filesAmount + " fichier(s) ajouté(s)")
                    if (input.files && input.files[0]) {
                        var reader = new FileReader();

                        reader.onload = function (e) {
                            $('#imageAffichee').attr('src', e.target.result);

                        }

                        reader.readAsDataURL(input.files[0]);
                    }
                    if (input.files && input.files[1]) {
                        var reader = new FileReader();

                        reader.onload = function (e) {
                            $('#imageAffichee1').attr('src', e.target.result);

                        }

                        reader.readAsDataURL(input.files[1]);
                    }

                }

                $("#imageRecue").change(function () {

                    console.log("Image recue !!!");
                    $('#non').hide();
                    readURL(this);
                });
                //FIN !!! Réception et affichage de l'image
            });
        </script>


    </head>
    <body class="metro " ng-app="workspace">

        <div class="keama_haut">
            <div class="fg-white keama_haut_texte">
                ${entreprise.nom}
                <h1 class="keama_h1 fg-blue">SUGGESTION <sup><span id="spinner" class="mif-spinner mif-ani-spin fg-white"></span></sup></h1>

            </div>
        </div>
        <div class=" ">
            <div id="menu"  class="app-bar navy" data-role="appbar">
                <a class="app-bar-element branding mif-apps"> <span onclick="toggleMetroCharm('#left-charm')" class="mif-apps mif-2x"></span></a>
                <span class="app-bar-divider"></span>
                <ul class="app-bar-menu">
                    <li><a onclick="showDialog('#nouveau_courrier')">Nouveau</a></li>
                    <li><a href="#mes">Mes suggestions</a></li>
                    <li><a href="#tous">Tous</a></li>
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
                            <h1 class="fg-white">Nouvelle suggestion</h1>
                            <p class="fg-white" style="padding-bottom: 20px;">
                                Vous pouvez partager une idée avec vos collaborateurs.
                            </p>
                            <form method="post" action="CreationSuggestionServlet" class="grid condensed">
                                <div class="row cells2">
                                    <div class="cell colspan2">
                                        <label class="fg-white">Domaine</label>
                                        <div class="input-control text full-size">
                                            <input name="titre">
                                            <input name="employe" type="hidden" value="${utilisateur.employe.idemploye}">
                                        </div>
                                    </div>
                                </div>
                                <div class="row cells6">
                                    <div class="cell colspan6">
                                        <label  class="fg-white">Contenu</label>
                                        <div class="input-control textarea full-size" data-role="input" data-text-auto-resize="true">
                                            <textarea name="contenu" placeholder="Contenu...">${courrier.note}</textarea>
                                        </div>
                                    </div>
                                </div>
                                <div style="padding-top: 20px;">
                                    <input type="submit" class="button ribbed-green fg-white" value="Continuer">
                                    <a href="reunion" class="button ribbed-orange fg-white">
                                        Annuler
                                    </a>
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
