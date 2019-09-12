<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <script type="text/javascript" src="js/jquery-confirm.js"></script>
    
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
        <link href="css/jquery-confirm.css" rel="stylesheet" type="text/css"/>
        <!-- Fin CSS -->

        <!-- ANGULAR -->
        <script src="js/angular.js" type="text/javascript"></script>
        <script src="js/angular-route.js" type="text/javascript"></script>
        <!-- FIN ANGULAR -->

        <script>
            var app = angular.module('workspace', ['ngRoute']);

            app.config(function ($routeProvider) {
                $routeProvider
                        .when('/', {templateUrl: 'entreprise'})
                        .when('/entreprise', {templateUrl: 'entreprise'})
                        .when('/structure', {templateUrl: 'StructureServlet'})
                        .when('/poste', {templateUrl: 'PosteServlet'})
                        .when('/employe', {templateUrl: 'EmployeServlet'})
                        .when('/employe/:id', {templateUrl: function (params) {
                                return 'EmployeServlet?id=' + params.id;
                            }})

                        .when('/datatable', {templateUrl: 'jspDataTable.jsp'})
                        .when('/treetable', {templateUrl: 'jspTreeTable.jsp'})
                        .when('/chart', {templateUrl: 'jspChart.jsp'})
                        .when('/visualiser', {templateUrl: 'jspVisualiser.jsp'})
                        .when('/formulaire', {templateUrl: 'jspVisualiser.jsp'})
                        .when('/save', {templateUrl: 'jspSaveFile.jsp'})
                        .otherwise({redirectTo: '/'});
            });
        </script>


    </head>
    <body class="metro " ng-app="workspace">

        <div class="keama_haut">
            <div class="fg-white keama_haut_texte">
                ${entreprise.nom}
                <h1 class="keama_h1 fg-blue">PARAMETRES <sup><span id="spinner" class="mif-spinner mif-ani-spin fg-white"></span></sup></h1>

            </div>
        </div>
        <div class=" ">
            <div id="menu" style="z-index: 400;" class="app-bar navy" data-role="appbar">
                <a class="app-bar-element branding mif-apps"> <span onclick="toggleMetroCharm('#left-charm')" class="mif-apps mif-2x"></span></a>
                <span class="app-bar-divider"></span>
                <ul class="app-bar-menu">
                    <li><a href="#entreprise">L'entreprise</a></li>
                    <li>
                        <a href="" class="dropdown-toggle">Organisation</a>
                        <ul class="d-menu" data-role="dropdown">
                            <li><a href="#structure">Structures</a></li>
                            <li><a href="#poste">Postes</a></li>
                            <li><a href="#employe">Employés</a></li>
                            <li class="divider"></li>
                            <li>
                                <a href="" class="dropdown-toggle">Organigrammes</a>
                                <ul class="d-menu" data-role="dropdown">
                                    <li><a href="">Structures</a></li>
                                    <li><a href="">Postes</a></li>
                                    <li><a href="">Employés</a></li>
                                </ul>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="" class="dropdown-toggle">Utilisateurs</a>
                        <ul class="d-menu" data-role="dropdown">
                            <li><a href="">Profils</a></li>
                            <li><a href="">Utilisateurs</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="" class="dropdown-toggle">JSP</a>
                        <ul class="d-menu" data-role="dropdown">
                            <li><a href="#datatable">JSP with datatable</a></li>
                            <li><a href="#treetable">JSP with treetable</a></li>
                            <li><a href="#chart">JSP with Chart</a></li>
                            <li><a href="#visualiser">JSP with Visualiser</a></li>
                            <li><a href="#formulaire">JSP with Form</a></li>
                            <li><a href="#save">JSP Save File</a></li>
                            <li class="divider"></li>
                            <li><a href="">About</a></li>
                        </ul>
                    </li>
                </ul>

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
    </body>
</html>
