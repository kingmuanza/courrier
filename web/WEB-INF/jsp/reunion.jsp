<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>KEAMA</title>

        <!-- JavaScript Chart, Jquery, treetable, metro-->
        <script src="js/jquery.js" type="text/javascript"></script>
        <script src="js/Chart.js" type="text/javascript"></script>
        <script src="js/metro.js?id=3" type="text/javascript"></script>
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
        <script src="js/TimeCircles.js" type="text/javascript"></script>
        <script src="js/image-picker.js?id=dk" type="text/javascript"></script>
        <!-- Fin JavaScript -->

        <!-- CSS  : TreeTable1, 2; Metro, -->
        <link href="css/jquery.treetable.css" rel="stylesheet" type="text/css"/>
        <link href="css/keama.css?id=14" rel="stylesheet" type="text/css"/>
        <link href="css/jquery.treetable.theme.default.css?modified=20012009" rel="stylesheet" type="text/css"/>
        <link href="css/metro.css?id=1" rel="stylesheet" type="text/css"/>
        <link href="css/metro-colors.css" rel="stylesheet" type="text/css"/>
        <link href="css/metro-icons.css" rel="stylesheet" type="text/css"/>
        <link href="css/metro-responsive.css" rel="stylesheet" type="text/css"/>
        <link href="css/metro-rtl.css" rel="stylesheet" type="text/css"/>
        <link href="css/metro-schemes.css?id=3" rel="stylesheet" type="text/css"/>
        <link href="css/jquery.dataTables.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/select2.css?id=2" rel="stylesheet" type="text/css"/>
        <link href="css/buttons.dataTables.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/jquery-confirm.css" rel="stylesheet" type="text/css"/>
        <link href="css/TimeCircles.css" rel="stylesheet" type="text/css"/>
        <link href="css/image-picker.css?id=yghi" rel="stylesheet" type="text/css"/>
        <!-- Fin CSS -->

        <!-- ANGULAR -->
        <script src="js/angular.js" type="text/javascript"></script>
        <script src="js/angular-route.js" type="text/javascript"></script>
        <!-- FIN ANGULAR -->

        <script>
            var app = angular.module('workspace', ['ngRoute']);

            app.config(function ($routeProvider) {
                $routeProvider
                        .when('/', {templateUrl: 'ReunionSemaineServlet'})
                        .when('/planning', {templateUrl: 'EmploiTempsServlet'})
                        .when('/semaine', {templateUrl: 'ReunionSemaineServlet'})
                        .when('/mes', {templateUrl: 'MesReunionsServlet'})
                        .when('/mes/:id', {templateUrl: function (params) {
                                return 'MesReunionsServlet?id=' + params.id;
                            }})
                        .when('/avenir', {templateUrl: 'AVenirReunionServlet'})
                        .when('/avenir/:id', {templateUrl: function (params) {
                                return 'AVenirReunionServlet?id=' + params.id;
                            }})
                        .when('/notifications', {templateUrl: 'NotificationReunionServlet'})
                        .when('/salle', {templateUrl: 'SalleReunionServlet'})
                        .when('/salle/:id', {templateUrl: function (params) {
                                return 'SalleReunionServlet?id=' + params.id;
                            }})
                        .when('/tous', {templateUrl: 'TousReunionServlet'})
                        .when('/tous/:id', {templateUrl: function (params) {
                                return 'TousReunionServlet?id=' + params.id;
                            }})
                        .when('/id/:id', {templateUrl: function (params) {
                                return 'AfficherReunionServlet?id=' + params.id;
                            }})
                        .otherwise({redirectTo: '/'});
            });



            $(document).ready(function () {
                //Réception et affichage de l'image
                var nombre_personnes = 0;
                $("select.image-picker.show-labels").imagepicker({
                    hide_select: true,
                    show_label: true,
                    limit: 50,
                    limit_reached: function () {
                        alert("Vous avez atteint la limite !!!")
                    },
                    selected: function (select, option, event) {
                        nombre_personnes++;
                        console.log("Nombre de personnes selectionnées : " + nombre_personnes);
                        console.log("Select : " + select);
                        console.log(select.picker.picker[0]);
                        console.log(select.picker.select[0]);

                    }
                });

                //Application
                $("#rechercher").keyup(function () {
                    var mot = $("#rechercher").val();
                    var oui = $("ul.thumbnails.image_picker_selector").get(0);
                    console.log(oui);
                    oui.style.display = "none";
                });


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

                function showDialog(id) {
                    var dialog = $(id).data('dialog');
                    dialog.open();
                    //$("#keama_back").css("opacity", "0.2");

                }



            });
            function selectionnerTout() {
                //alert('oui');

            }


        </script>


    </head>
    <body class="metro " ng-app="workspace" style="overflow-x: hidden">

        <div class=" ">
            <div id="menu"  class="app-bar muanza_bg_violet" data-role="appbar">
                <a class="app-bar-element branding mif-apps"> <span onclick="toggleMetroCharm('#left-charm')" class="mif-apps mif-2x"></span></a>
                <span class="app-bar-divider"></span>
                <ul class="app-bar-menu">
                    <li><a onclick="showDialog('#nouvelle_reunion')">Nouveau</a></li>
                    <li><a href="#avenir">A venir <sup class="fg-orange"><b>${reunionAvenir}</b></sup></a></li>
                    <li><a href="#mes">Mes réunions</a></li>
                    <li><a href="#semaine">Emploi du temps</a></li>
                    <li><a href="#tous">Tous</a></li>
                    <li><a href="#salle">Salle de réunion</a></li>
                    <!--<li><a href="#notifications">Notifications</a></li>-->
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

        <div class="" ng-view >

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

        <div style="background-image: url('img/wallbw.jpg'); background-size: auto 100% " data-role="dialog" id="nouvelle_reunion" class="" data-close-button="true" data-windows-style="true" >

            <div style="background-color: rgba(250, 104,0,0.85);">
                <div class="padding20" style="padding-left: 30px;padding-right: 30px;">
                    <h1 class="fg-white">Nouvelle réunion</h1>
                    <p class="fg-white" style="font-weight: 100; padding-bottom: 20px; font-size: 1.2em; width: 40%;">
                        Vous pouvez programmer une réunion et convoquer des participants à la réunion.
                    </p>
                    <form method="post" action="NouveauReunionServlet" class="grid condensed">
                        <div class="row cells4">
                            <div class="cell colspan2" id="partie_gauche">
                                <div class="row cells12">
                                    <div class="cell colspan12" style="padding-right: 20px;">

                                        <div class="row cells2">
                                            <div class="cell colspan2">
                                                <label class="fg-white">Objet</label>
                                                <div class="input-control text full-size">
                                                    <input name="titre">
                                                    <input name="employe" type="hidden" value="${utilisateur.employe.idemploye}">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row cells4">
                                            <c:set var="now" value="<%=new java.util.Date()%>" />
                                            <div class="cell" style="padding-right: 10px;">
                                                <label class="fg-white">Date</label>
                                                <fmt:formatDate pattern="yyyy-MM-dd" value="${now}" var="debut"/>
                                                <div class="input-control text full-size" data-role="datepicker" data-preset="${debut}">
                                                    <input type="text" name="date_reunion">
                                                    <button class="button"><span class="mif-calendar"></span></button>
                                                </div>
                                            </div>
                                            <div class="cell" style="padding-right: 10px;">
                                                <label class="fg-white">Heure Début</label>
                                                <div class="input-control text full-size">
                                                    <input type="time" name="debut" value="08:00">
                                                </div>
                                            </div>
                                            <div class="cell" style="padding-right: 10px;">
                                                <label class="fg-white">Heure fin</label>
                                                <div class="input-control text full-size" >
                                                    <input type="time" name="fin" value="09:00">
                                                </div>
                                            </div>
                                            <div class="cell">
                                                <label class="fg-white">Salle</label>
                                                <div class="input-control select full-size">
                                                    <select class="" name="salle">
                                                        <c:forEach items="${salleReunions}" var="s">
                                                            <option value="${s.idsalleReunion}">${s.nom} </option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row cells">

                                        </div> 
                                        <div class="row cells6">
                                            <div class="cell colspan6">
                                                <label  class="fg-white">Commentaire</label>
                                                <div class="input-control textarea full-size ${!empty courrier ? 'success': ''}" data-role="input" data-text-auto-resize="true">
                                                    <textarea name="description" placeholder="Décrivez les points à l'ordre du jour...">${courrier.note}</textarea>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="cell colspan2">
                                <div class="row">
                                    <div class="cell">
                                        <label class="fg-white">Personnes à convoquer</label>
                                        <div class="input-control full-size big-input" data-role="select" data-multiple="true" data-placeholder="Choisissez les personnes à convoquer">
                                            <select class="js-example-basic-multiple full-size" name="employes" id="employes">
                                                <c:forEach items="${employes}" var="e">
                                                    <option class="employes" value="${e.idemploye}">${e.noms} ${e.prenoms}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                                                <div style="padding-top: 20px; padding-bottom: 20px;">
                            <input type="submit" class="button bg-green bd-green fg-white" value="Continuer">
                            <a href="reunion" class="button bg-transparent fg-white" style="background: none">
                                Annuler
                            </a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
