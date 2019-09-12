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
        <link href="css/metro.css?id=23" rel="stylesheet" type="text/css"/>
        <link href="css/metro-colors.css" rel="stylesheet" type="text/css"/>
        <link href="css/metro-icons.css" rel="stylesheet" type="text/css"/>
        <link href="css/metro-responsive.css" rel="stylesheet" type="text/css"/>
        <link href="css/metro-rtl.css" rel="stylesheet" type="text/css"/>
        <link href="css/metro-schemes.css?id=3" rel="stylesheet" type="text/css"/>
        <link href="css/jquery.dataTables.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/select2.css" rel="stylesheet" type="text/css"/>
        <link href="css/buttons.dataTables.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/jquery-confirm.css" rel="stylesheet" type="text/css"/>
        <link href="css/viewer.css?id=hd55ntgs" rel="stylesheet" type="text/css"/>
        <link href="css/TimeCircles.css" rel="stylesheet" type="text/css"/>
        <!-- Fin CSS -->

        <!-- ANGULAR -->
        <script src="js/angular.js" type="text/javascript"></script>
        <script src="js/angular-route.js" type="text/javascript"></script>
        <!-- FIN ANGULAR -->
        <style>
            .thumb {
                height: 150px;
                border: 1px solid #fff;
                margin: 10px 5px 0 0;
            }
        </style>
        <script>
            var app = angular.module('workspace', ['ngRoute']);

            app.config(function ($routeProvider) {
                $routeProvider
                        .when('/', {templateUrl: 'PriseCourrierServlet'})
                        .when('/nouveau', {templateUrl: 'nouveauCourrier'})
                        .when('/id/:id', {templateUrl: function (params) {
                                return 'nouveauCourrier?id=' + params.id;
                            }})
                        .when('/creation/:id', {templateUrl: function (params) {
                                return 'NouveauCourrierServlet2?id=' + params.id;
                            }})
                        .when('/tous', {templateUrl: 'tousCourrier'})
                        .when('/suivi', {templateUrl: 'tousCourrier'})
                        .when('/suivi/:id', {templateUrl: function (params) {
                                return 'SuiviCourrierServlet?id=' + params.id;
                            }})
                        .when('/procedures', {templateUrl: 'ProceduresCourrierServlet'})
                        .when('/procedures/:id', {templateUrl: function (params) {
                                return 'ProceduresCourrierServlet?id=' + params.id;
                            }})
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

                        .when('/correspondant', {templateUrl: 'CorrespondantServlet'})
                        .when('/correspondant/:id', {templateUrl: function (params) {
                                return 'CorrespondantServlet?id=' + params.id;
                            }})
                        .when('/evaluation/:id', {templateUrl: function (params) {
                                return 'TachesParStatutServlet?id=' + params.id;
                            }})
                        .when('/ordonner/:id', {templateUrl: function (params) {
                                return 'OrdonnerFichierCourrierServlet?id=' + params.id;
                            }})
                        .when('/treetable', {templateUrl: 'jspTreeTable.jsp'})
                        .when('/chart', {templateUrl: 'jspChart.jsp'})
                        .when('/visualiser', {templateUrl: 'jspVisualiser.jsp'})
                        .when('/formulaire', {templateUrl: 'jspVisualiser.jsp'})
                        .when('/save', {templateUrl: 'jspSaveFile.jsp'})
                        .otherwise({redirectTo: '/'});
            });



            $(document).ready(function () {
                //Réception et affichage de l'image
                console.log("Version de JQUERY : " + $.fn.jquery);
                var nb_fichiers = 0;

                $("a.lien").click(function () {
                    var url_courante = window.location.href.toString();
                    url_courante = $.trim(url_courante)
                    //alert(url_courante);
                    var url_target = $(this).attr('href').toString();
                    url_target = $.trim(url_target)
                    //alert(url_target);
                    if (url_courante.indexOf(url_target) >= 0) {
                        //alert(url_courante.indexOf(url_target));
                    } else {
                        //alert(url_courante.indexOf(url_target));
                        $("#spinner").show();
                    }
                });

                $("#imageRecue").change(function () {
                    console.log("Image recue !!!");
                    $('#non').hide();
                    readURL(this);
                });
                //$(".keama_haut").hide(1000);
                //FIN !!! Réception et affichage de l'image
//                if ($(document).scrollTop() == 0) {
//                    $(document).scroll(function () {
//                        $(".keama_haut").hide(1000);
//                        $(".app-bar").addClass("fixed-top");
//                        if ($(document).scrollTop() == 0) {
//                            $(".keama_haut").show(1000);
//                            $(".app-bar").removeClass("fixed-top");
//                        }
//                    });
//                }
            });
        </script>


    </head>
    <body class="metro " ng-app="workspace" style="overflow-x: hidden">

        <div class=" ">
            <div id="menu"  class="app-bar muanza_bg_violet fixed-top" data-role="appbar"  data-flexclean="false">
                <a onclick="toggleMetroCharm('#left-charm')" class="app-bar-element branding mif-apps"> <span  class="mif-apps mif-2x"></span></a>
                <span class="app-bar-divider"></span>
                <ul class="app-bar-menu">
                    <li><a onclick=" showDialog('#nouveau_courrier');">Nouveau</a></li>

                    <li>
                        <a href="" class="dropdown-toggle">Courriers entrants</a>
                        <ul class="d-menu" data-role="dropdown">
                            <li><a class="lien" href="#entrants">En préparation</a></li>
                            <li><a class="lien" href="#prets">Prêts</a></li>
                            <li><a class="lien" href="#transmis">Transmis</a></li>
                            <li><a class="lien" href="#ignores">Ignorés</a></li>

                        </ul>
                    </li>
                    <li style="display: none;">
                        <a href="" class="dropdown-toggle">Courriers sortants</a>
                        <ul class="d-menu small-dropdown" data-role="dropdown">
                            <li><a class="lien" href="#prets">Documents à signer</a></li>
                            <li><a class="lien" class="no-flexible" href="#transmis">Documents envoyés en signature</a></li>
                            <li><a class="lien" href="#ignores">Documents signés</a></li>
                            <li><a class="lien" href="#tous">Tous</a></li>
                        </ul>
                    </li>
                    <li><a class="lien" href="#tous">Rechercher</a></li>
                    <span class="app-bar-divider"></span>
                    <li><a class="lien" href="#suivi">Suivi du courrier</a></li>
                        <c:if test="${!empty utilisateur.employe.noms}">
                        <span class="app-bar-divider"></span>
                        <li><a class="lien" href="#prise">Prise en charge</a></li>
                        </c:if>

                    <span class="app-bar-divider"></span>
                    <li><a class="lien" href="#correspondant">Correspondants</a></li>
                    <li>
                        <a href="" class="dropdown-toggle">Paramètres</a>
                        <ul class="d-menu small-dropdown" data-role="dropdown">
                            <li><a style="display: none;" class="lien" href="#suivi">Signature électronique</a></li>
                            <li><a class="lien" href="#procedures">Procédures</a></li>
                            <li><a style="display: none;" class="lien" href="#prets">Templates de réponse</a></li>
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
                            <li>
                                <a class="lien" href="deconnexion" class="fg-white fg-hover-yellow">
                                    Déconnexion
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>

            </div>
        </div>

                    <div class="" ng-view style="padding-top: 55px;">

        </div>
                    <div data-role="charm" data-position="left" id="left-charm" style="background-color: #310e31">
            <div style="padding: 20px; padding-right: 30px; width: 400px">
                <h1 class="keama_h1 fg-white">SIACOURRIER</h1>
                <div style="cursor: pointer; padding-bottom: 20px;" onclick="window.location.href = 'start'">
                    Revenir au menu principal 
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
            </div>
        </div>
        <div style="background-image: url('img/wallbw.jpg'); background-size: auto 100% " data-role="dialog" id="nouveau_courrier" class="" data-close-button="true" data-windows-style="true" >
            <div style="background-color: rgba(250, 104,0,0.85);">
                <div class="padding20" style="padding-left: 30px;padding-right: 30px;">
                    <h1 class="fg-white">Enregistrement du courrier</h1>
                    <p class="fg-white" style="font-weight: 100; padding-bottom: 20px; font-size: 1.2em; width: 40%;">
                        Vous pouvez enregistrer le courrier numérisé directement avant la saisie du formulaire.

                        Sélectionnez tous les fichiers à ajouter puis cliquez sur <b>Continuer</b>
                    </p>
                    <div class="grid">
                        <div class="row cells2">
                            <div class="cell">
                                <div class="row cells12">
                                    <div class="cell colspan9">

                                        <form method="post" action="FichierCourrierServlet2" enctype="multipart/form-data" >
                                            <div class="input-control file full-size" data-role="input">
                                                <input required name="imageRecue" id="imageRecue" type="file" placeholder="Selectionnez les fichiers..." accept="image/jpeg" multiple="multiple" oninvalid="this.setCustomValidity('Selectionnez au moins un fichier')">
                                                <button class="button"><span class="mif-folder"></span></button>
                                            </div>
                                            <div id="drop_zone" class="row cells6 ribbed-orange" style="border: 1px dashed white; height: 100px; margin-top: 10px;">
                                                <p class="padding10 fg-white ">
                                                    Déposez les fichiers ici
                                                </p>
                                            </div>
                                            <span class="fg-white" id="nb_fichiers" ></span>
                                            <div style="padding-top: 40px;">
                                                <input type="submit" class="button bg-green bd-green fg-white" value="Continuer">
                                                <a href="" style="background: none" class="button bg-transparent fg-white" id="non" onclick="hideMetroDialog('#nouveau_courrier');
                                                        window.location.href = 'courrier#/nouveau'">
                                                    Je vais ajouter les fichiers plus tard !! 
                                                </a>
                                            </div>
                                        </form>

                                        <script>
                                            $(document).ready(function () {
                                                // Check for the various File API support.
                                                if (window.File && window.FileReader && window.FileList && window.Blob) {
                                                    console.log('LE NAVIGATEUR EST PARFAIT §§');
                                                    function handleFileSelect(evt) {

                                                        var files = evt.target.files; // FileList object
                                                        //var files = evt.target.files; // FileList object

                                                        // files is a FileList of File objects. List some properties.
                                                        var output = [];
                                                        document.getElementById('list_image').innerHTML = "";
                                                        for (var i = 0, f; f = files[i]; i++) {
                                                            output.push('<li><strong>', escape(f.name), '</strong> (', f.type || 'n/a', ') - ',
                                                                    f.size, ' bytes, last modified: ',
                                                                    f.lastModifiedDate ? f.lastModifiedDate.toLocaleDateString() : 'n/a',
                                                                    '</li>');

                                                            if (i < 10) {
                                                                var reader = new FileReader();

                                                                // Closure to capture the file information.
                                                                reader.onload = (function (theFile) {
                                                                    var span = document.createElement('span');
                                                                    return function (e) {
                                                                        // Render thumbnail.
                                                                        span = document.createElement('span');
                                                                        span.innerHTML = ['<img class="thumb" src="', e.target.result,
                                                                            '" title="', escape(theFile.name), '"/>'].join('');
                                                                        document.getElementById('list_image').insertBefore(span, null);
                                                                    };
                                                                })(f);

                                                                // Read in the image file as a data URL.
                                                                reader.readAsDataURL(f);
                                                            }

                                                        }
                                                        document.getElementById('list').innerHTML = '<ol>' + output.join('') + '</ol>';
                                                        $('#nb_fichiers').html(files.length + " fichier(s) ajouté(s)");
                                                    }

                                                    function handleFileSelect2(evt) {
                                                        evt.stopPropagation();
                                                        evt.preventDefault();
                                                        var files = evt.dataTransfer.files; // FileList object
                                                        //var files = evt.target.files; // FileList object

                                                        // files is a FileList of File objects. List some properties.
                                                        var output = [];
                                                        document.getElementById('list_image').innerHTML = "";
                                                        for (var i = 0, f; f = files[i]; i++) {
                                                            output.push('<li><strong>', escape(f.name), '</strong> (', f.type || 'n/a', ') - ',
                                                                    f.size, ' bytes, last modified: ',
                                                                    f.lastModifiedDate ? f.lastModifiedDate.toLocaleDateString() : 'n/a',
                                                                    '</li>');

                                                            // Only process image files.
                                                            if (!f.type.match('image.*')) {
                                                                continue;
                                                            }
                                                            if (i < 10) {
                                                                var reader = new FileReader();

                                                                // Closure to capture the file information.
                                                                reader.onload = (function (theFile) {
                                                                    return function (e) {
                                                                        // Render thumbnail.
                                                                        var span = document.createElement('span');
                                                                        span.innerHTML = ['<img class="thumb" src="', e.target.result,
                                                                            '" title="', escape(theFile.name), '"/>'].join('');
                                                                        document.getElementById('list_image').insertBefore(span, null);
                                                                    };
                                                                })(f);

                                                                // Read in the image file as a data URL.
                                                                reader.readAsDataURL(f);
                                                            }

                                                        }
                                                        document.getElementById('list').innerHTML = '<ol>' + output.join('') + '</ol>';
                                                        document.getElementById('drop_zone').style.border = "1px dashed white";
                                                        $('#nb_fichiers').html(files.length + " fichier(s) ajouté(s)");
                                                    }

                                                    function handleDragOver(evt) {
                                                        evt.stopPropagation();
                                                        evt.preventDefault();
                                                        document.getElementById('drop_zone').style.border = "4px solid white";
                                                        evt.dataTransfer.dropEffect = 'copy'; // Explicitly show this is a copy.
                                                    }

                                                    // Setup the dnd listeners.
                                                    var dropZone = document.getElementById('drop_zone');
                                                    dropZone.addEventListener('dragover', handleDragOver, false);
                                                    dropZone.addEventListener('drop', handleFileSelect2, false);

                                                    document.getElementById('imageRecue').addEventListener('change', handleFileSelect, false);
                                                } else {
                                                    console.log('The File APIs are not fully supported in this browser.');
                                                }
                                            });
                                        </script>

                                    </div>
                                </div>
                            </div>
                            <div class="cell">

                                <div class="row cells6">
                                    <div class="cell" id="imageAfficheeBlock"  style="display: none;">
                                        <img id="imageAffichee" src="img/lettre1.jpg" alt=""/>
                                    </div>
                                    <div class="cell" id="the-canvasBlock"  style="display: none;">
                                        <canvas id="the-canvas"></canvas>
                                    </div>
                                </div>
                                <div id="list_image" class="row cells6">

                                </div>
                                <div id="list" class="row cells6" style="display: none;">

                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
