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
        <!-- Fin CSS -->

        <!-- ANGULAR -->
        <script src="js/angular.js" type="text/javascript"></script>
        <script src="js/angular-route.js" type="text/javascript"></script>
        <!-- FIN ANGULAR -->

        <script>
            var app = angular.module('workspace', ['ngRoute']);

            app.config(function ($routeProvider) {
                $routeProvider
                        .when('/', {templateUrl: 'test.jsp'})
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
                Nom de l'entreprise
                <h1 class="keama_h1 fg-blue">WORKSPACE <sup><span id="spinner" class="mif-spinner mif-ani-spin fg-white"></span></sup></h1>

            </div>
        </div>
        <div class=" ">
            <div id="menu" style="z-index: 400;" class="app-bar navy" data-role="appbar">
                <a class="app-bar-element branding mif-apps"> <span onclick="toggleMetroCharm('#left-charm')" class="mif-apps mif-2x"></span></a>
                <span class="app-bar-divider"></span>
                <ul class="app-bar-menu">
                    <li><a href="">Dashboard</a></li>
                    <li>
                        <a href="" class="dropdown-toggle">Project</a>
                        <ul class="d-menu" data-role="dropdown">
                            <li><a href="">New project</a></li>
                            <li class="divider"></li>
                            <li>
                                <a href="" class="dropdown-toggle">Reopen</a>
                                <ul class="d-menu" data-role="dropdown">
                                    <li><a href="">Project 1</a></li>
                                    <li><a href="">Project 2</a></li>
                                    <li><a href="">Project 3</a></li>
                                    <li class="divider"></li>
                                    <li><a href="">Clear list</a></li>
                                </ul>
                            </li>
                        </ul>
                    </li>
                    <li><a href="">Security</a></li>
                    <li><a href="">System</a></li>
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

                <div class="app-bar-element place-right">
                    <span class="dropdown-toggle"><span class="mif-cog"></span> Nom de l'utilisateur</span>
                    <div class="app-bar-drop-container padding10 place-right no-margin-top block-shadow fg-dark" data-role="dropdown" data-no-close="true" style="width: 220px">
                        <h2 class="text-light">Quick settings</h2>
                        <ul class="unstyled-list fg-dark">
                            <li><a href="" class="fg-white1 fg-hover-yellow">Profile</a></li>
                            <li><a href="" class="fg-white2 fg-hover-yellow">Security</a></li>
                            <li><a href="" class="fg-white3 fg-hover-yellow">Exit</a></li>
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
                <div onclick="window.location.href = 'general'" class="tile bg-blue fg-white" data-role="tile">
                    <div class="tile-content iconic">
                        <span class="icon mif-profile"></span>
                    </div>
                    <span class="tile-label">Personnel</span>
                </div>
                <div onclick="window.location.href = 'reunion'" class="tile bg-blue fg-white" data-role="tile">
                    <div class="tile-content iconic">
                        <span class="icon mif-calendar"></span>
                    </div>
                    <span class="tile-label">Réunions</span>
                    <span class="bg-orange tile-badge">5</span>
                </div>
                <div onclick="window.location.href = 'general'" class="tile bg-blue fg-white" data-role="tile">
                    <div class="tile-content iconic">
                        <span class="icon mif-folder-open"></span>
                    </div>
                    <span class="tile-label">Projets</span>
                    <span class="bg-orange tile-badge">2</span>
                </div>
                <div onclick="window.location.href = 'general'" class="tile bg-blue fg-white" data-role="tile">
                    <div class="tile-content iconic">
                        <span class="icon mif-clipboard"></span>
                    </div>
                    <span class="tile-label">Tâches</span>
                    <span class="bg-orange tile-badge">2</span>
                </div>
                <div onclick="window.location.href = 'general'" class="tile bg-blue fg-white" data-role="tile">
                    <div class="tile-content iconic">
                        <span class="icon mif-clipboard"></span>
                    </div>
                    <span class="tile-label">Personnel</span>
                </div>
                <div onclick="window.location.href = 'general'" class="tile bg-blue fg-white" data-role="tile">
                    <div class="tile-content iconic">
                        <span class="icon mif-profile"></span>
                    </div>
                    <span class="tile-label">Personnel</span>
                </div>

            </div>
        </div>
    </body>
</html>
