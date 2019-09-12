<%-- 
    Document   : test
    Created on : Nov 7, 2016, 6:56:38 PM
    Author     : KEAMA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="grid" style="height: 100%; margin-top: -0px">
            <div class="row cells6" style="height: 100%">
                <div class="cell" id="cell-sidebar" style="background-image: url('img/keama_background.png'); height: 100%;">
                    <div id="block_recherche" class="fg-white" style="padding: 16px; display: block">
                        <!--                        <h2>Rechercher</h2>-->
                        <div class="input-control text full-size info">
                            <span class="mif-search prepend-icon fg-blue"></span>
                            <input id="rechercher" type="text" placeholder="Rechercher">
                        </div>
                    </div>
                    <ul class="sidebar darcula" style="height: 260px; background-image: url('img/keama_background.png');">
                        <li><a href="#/">
                                <span class="mif-apps icon"></span>
                                <span class="title">all items</span>
                                <span class="counter">Muanza dérange</span>
                            </a></li>
                        <li><a href="#/">
                                <span class="mif-vpn-publ icon"></span>
                                <span class="title">websites</span>
                                <span class="counter">0</span>
                            </a></li>
                        <li class="active"><a href="#/">
                                <span class="mif-drive-eta icon"></span>
                                <span class="title">Virtual machines</span>
                                <span class="counter">2</span>
                            </a></li>
                        <li><a href="#/">
                                <span class="mif-cloud icon"></span>
                                <span class="title">Cloud services</span>
                                <span class="counter">0</span>
                            </a></li>
                        <li><a href="#/">
                                <span class="mif-database icon"></span>
                                <span class="title">SQL Databases</span>
                                <span class="counter">0</span>
                            </a></li>
                        <li><a href="#/">
                                <span class="mif-cogs icon"></span>
                                <span class="title">Automation</span>
                                <span class="counter">0</span>
                            </a></li>
                        <li class="middle"><a href="#/">
                                <span class="mif-apps icon"></span>
                                <span class="title">all items</span>
                                <span class="counter">0</span>
                            </a></li>
                    </ul>
                    <script>
                        //Filtrer les éléments de la liste
                        $("#rechercher").keyup(function () {
                            var mot = $("#rechercher").val();
                            console.log(mot);
                            $("ul.sidebar > li").css("display", "none");
                            $("ul.sidebar > li").each(function (index) {
                                console.log(index + ": " + $(this).find(".title:contains('" + mot + "')").text());
                                if ($(this).find(".title:contains('" + mot + "')").length > 0) {
                                    $(this).css("display", "block");
                                }
                            });
                        });
                        console.log("Taille de la fenetre : " + $(document).height());
                        var total = $(document).height();
                        console.log("Taille de l'entête : " + $('.keama_haut').height());
                        var taille_entete = $('.keama_haut').height();
                        console.log("Taille du menu : " + $('#menu').height());
                        var taille_menu = $('#menu').height();
                        console.log("Taille du block de recherche : " + $('#block_recherche').height());
                        var taille_recherche = $('#block_recherche').height();

                        var restant = total - taille_entete - taille_menu - taille_recherche;
                        console.log(restant);
                        $("ul.sidebar").height(restant);

                        var titre = $("#titre_liste").html();

                        //Ajustement de la largeur des boutons du menu droit
                        console.log("Largeur du menu droit : " + $('#menu_droit').width());
                        var x = 0.8 * $('#menu_droit').width() - 40;
                        console.log(x);
                        $(".image-button").each(function (index) {
                            $(this).width(x);
                        });

                        //Ajustement de la largeur des blocks pour les graphiques
                        var hauteur_dashboard = $("#first_dashboard").width();
                        console.log("largeur dun carreau : " + hauteur_dashboard);
                        $(".keama_chart").each(function (index) {
                            $(this).height(hauteur_dashboard);
                        });

                        
                        var dataChart = {
                            labels: ["Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi"],
                            datasets: [{
                                    data: [12, 19, 3, 5, 2, 3],
                                    label: 'La semaine dernière',
                                    backgroundColor: [
                                        'rgba(255, 99, 132, 0.2)',
                                        'rgba(255, 99, 132, 0.2)',
                                        'rgba(255, 99, 132, 0.2)',
                                        'rgba(255, 99, 132, 0.2)',
                                        'rgba(255, 99, 132, 0.2)',
                                        'rgba(255, 99, 132, 0.2)'
                                    ],
                                    borderColor: [
                                        'rgba(255,99,132,1)',
                                        'rgba(255,99,132,1)',
                                        'rgba(255,99,132,1)',
                                        'rgba(255,99,132,1)',
                                        'rgba(255,99,132,1)',
                                        'rgba(255,99,132,1)'
                                    ],
                                    borderWidth: 1
                                }, {
                                    data: [5, 5, 5, 8, 10, 13],
                                    label: 'cette semaine',
                                    backgroundColor: [
                                        'rgba(0, 99, 132, 0.2)',
                                        'rgba(0, 99, 132, 0.2)',
                                        'rgba(0, 99, 132, 0.2)',
                                        'rgba(0, 99, 132, 0.2)',
                                        'rgba(0, 99, 132, 0.2)',
                                        'rgba(0, 99, 132, 0.2)'
                                    ],
                                    borderColor: [
                                        'rgba(0,99,132,1)',
                                        'rgba(0,99,132,1)',
                                        'rgba(0,99,132,1)',
                                        'rgba(0,99,132,1)',
                                        'rgba(0,99,132,1)',
                                        'rgba(0,99,132,1)'
                                    ],
                                    borderWidth: 1
                                }]
                        };
                        var dataPie = {
                            labels: [
                                "Red",
                                "Blue",
                                "Yellow"
                            ],
                            datasets: [
                                {
                                    data: [300, 50, 100],
                                    backgroundColor: [
                                        "#FF6384",
                                        "#36A2EB",
                                        "#FFCE56"
                                    ],
                                    hoverBackgroundColor: [
                                        "#FF6384",
                                        "#36A2EB",
                                        "#FFCE56"
                                    ]
                                }]
                        };
                        
                        var ctxBar = $("#myChartBar");
                        var myChartBar = new Chart(ctxBar, {
                            type: 'bar',
                            data: dataChart,
                            options: {
                                scales: {
                                    yAxes: [{
                                            ticks: {
                                                beginAtZero: true
                                            }
                                        }]
                                },
                                title: {
                                    display: true,
                                    text: 'Diagramme des tâches effectuées',
                                    position: 'bottom'
                                }
                            }
                        });

                        var ctxLine = $("#myChartLine");
                        var myChartLine = new Chart(ctxLine, {
                            type: 'line',
                            data: dataChart,
                            options: {
                                scales: {
                                    yAxes: [{
                                            ticks: {
                                                beginAtZero: true
                                            }
                                        }]
                                },
                                title: {
                                    display: true,
                                    text: 'Courbe des tâches effectuées'
                                }
                            }
                        });

                        var ctxPie = $("#myChartPie");
                        var myChartPie = new Chart(ctxPie, {
                            type: 'pie',
                            data: dataPie,
                            options: {
                                title: {
                                    display: true,
                                    text: 'Pourcentages des tâches effectuées'
                                }
                            }
                        });
                        
                        var ctxDoughnut = $("#myChartDoughnut");
                        var myChartDoughnut = new Chart(ctxDoughnut, {
                            type: 'doughnut',
                            data: dataPie,
                            options: {
                                title: {
                                    display: true,
                                    text: 'Pourcentages des tâches effectuées'
                                }
                            }
                        });


                    </script>
                </div>
                <div class="cell colspan4" style="height: 100%">
                    <div class="row cells6">
                        <h1 id="titre_liste">Tableau de bord</h1>
                        <hr>
                    </div>
                    <div class="row cells4">
                        <div id="first_dashboard" class="cell colspan2 keama_chart" style="overflow: hidden ; border: 1px #eeeeee solid;">
                            <canvas id="myChartBar" style="padding: 10px;" height="240"></canvas>
                        </div>
                        <div class="cell colspan2 keama_chart" style="overflow: hidden ; border: 1px #eeeeee solid;">
                            <canvas id="myChartLine" style="padding: 10px;" height="240"></canvas>
                        </div>

                    </div>
                    <div class="row cells4">
                        <div class="cell colspan2 keama_chart" style="overflow: hidden ; border: 1px #eeeeee solid;">
                            <canvas id="myChartPie" style="padding: 10px;" height="240"></canvas>
                        </div>
                        <div class="cell colspan2 keama_chart" style="overflow: hidden ; border: 1px #eeeeee solid;">
                            <canvas id="myChartDoughnut" style="padding: 10px;" height="240"></canvas>
                        </div>
                    </div>
                </div>
                <div id="menu_droit" class="cell bg-grayLighter" style="padding: 20px;">

                    <div class="row align-center" >
                        <button class="image-button bg-blue fg-white" onclick="">
                            Nouveau
                            <span class="icon mif-file-empty bg-lightGreen fg-white"></span>
                        </button>
                    </div>
                    <div class="row align-center" >
                        <button class="image-button bg-blue fg-white" onclick="keama_impression();
                                $('.impression').click();">
                            Imprimer
                            <span class="icon mif-printer bg-lighterBlue fg-white"></span>
                        </button>
                    </div>
                    <div class="row align-center" >
                        <button class="image-button bg-blue fg-white" onclick="keama_export();
                                $('.impressionExcel').click();">
                            Exporter
                            <span class="icon mif-file-excel bg-darkIndigo fg-white"></span>
                        </button>
                    </div>
                    <div class="row align-center">
                        <button class="image-button bg-blue fg-white" onclick="keama_export(); $('.impressionPDF').click();">
                            Exporter
                            <span class="icon mif-file-pdf bg-orange fg-white"></span>
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
