<%@page import="java.util.Date"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
        <script src="js/keama.js" type="text/javascript"></script>
        <!-- Fin JavaScript -->

        <!-- CSS  : TreeTable1, 2; Metro, -->
        <link href="css/jquery.treetable.css" rel="stylesheet" type="text/css"/>
        <link href="css/keama.css" rel="stylesheet" type="text/css"/>
        <link href="css/jquery.treetable.theme.default.css" rel="stylesheet" type="text/css"/>
        <link href="css/metro.css?id=5" rel="stylesheet" type="text/css"/>
        <link href="css/metro-colors.css" rel="stylesheet" type="text/css"/>
        <link href="css/metro-icons.css" rel="stylesheet" type="text/css"/>
        <link href="css/metro-responsive.css" rel="stylesheet" type="text/css"/>
        <link href="css/metro-rtl.css" rel="stylesheet" type="text/css"/>
        <link href="css/metro-schemes.css" rel="stylesheet" type="text/css"/>
        <!-- Fin CSS -->

        <style>
            .tile-area-controls {
                position: fixed;
                right: 40px;
                top: 10px;
            }

            .tile-group {
                left: 50px;
            }

            .tile, .tile-small, .tile-sqaure, .tile-wide, .tile-large, .tile-big, .tile-super {
                opacity: 0;
                -webkit-transform: scale(.8);
                transform: scale(.8);
            }

            #charmSettings .button {
                margin: 5px;
            }

            .schemeButtons {
                /*width: 300px;*/
            }

            @media screen and (max-width: 640px) {
                .tile-area {
                    overflow-y: scroll;
                }
                .tile-area-controls {
                    display: none;
                }
            }

            @media screen and (max-width: 320px) {
                .tile-area {
                    overflow-y: scroll;
                }

                .tile-area-controls {
                    display: none;
                }

            }
        </style>

        <script>
            (function ($) {
                $.StartScreen = function () {
                    var plugin = this;
                    var width = (window.innerWidth > 0) ? window.innerWidth : screen.width;

                    plugin.init = function () {
                        setTilesAreaSize();
                        if (width > 640)
                            addMouseWheel();
                    };

                    var setTilesAreaSize = function () {
                        var groups = $(".tile-group");
                        var tileAreaWidth = 80;
                        $.each(groups, function (i, t) {
                            if (width <= 640) {
                                tileAreaWidth = width;
                            } else {
                                tileAreaWidth += $(t).outerWidth() + 80;
                            }
                        });
                        $(".tile-area").css({
                            width: tileAreaWidth
                        });
                    };

                    var addMouseWheel = function () {
                        $("body").mousewheel(function (event, delta, deltaX, deltaY) {
                            var page = $(document);
                            var scroll_value = delta * 50;
                            page.scrollLeft(page.scrollLeft() - scroll_value);
                            return false;
                        });
                    };

                    plugin.init();
                }


            })(jQuery);

            $(function () {
                $.StartScreen();

                var tiles = $(".tile, .tile-small, .tile-sqaure, .tile-wide, .tile-large, .tile-big, .tile-super");

                $.each(tiles, function () {
                    var tile = $(this);
                    setTimeout(function () {
                        tile.css({
                            opacity: 1,
                            "-webkit-transform": "scale(1)",
                            "transform": "scale(1)",
                            "-webkit-transition": ".3s",
                            "transition": ".3s"
                        });
                    }, Math.floor(Math.random() * 500));
                });

                $(".tile-group").animate({
                    left: 0
                });
            });

            function showCharms(id) {
                var charm = $(id).data("charm");
                if (charm.element.data("opened") === true) {
                    charm.close();
                } else {
                    charm.open();
                }
            }

            function setSearchPlace(el) {
                var a = $(el);
                var text = a.text();
                var toggle = a.parents('label').children('.dropdown-toggle');

                toggle.text(text);
            }

            $(function () {
                var current_tile_area_scheme = localStorage.getItem('tile-area-scheme') || "tile-area-scheme-dark";
                $(".tile-area").removeClass(function (index, css) {
                    return (css.match(/(^|\s)tile-area-scheme-\S+/g) || []).join(' ');
                }).addClass(current_tile_area_scheme);

                $(".schemeButtons .button").hover(
                        function () {
                            var b = $(this);
                            var scheme = "tile-area-scheme-" + b.data('scheme');
                            $(".tile-area").removeClass(function (index, css) {
                                return (css.match(/(^|\s)tile-area-scheme-\S+/g) || []).join(' ');
                            }).addClass(scheme);
                        },
                        function () {
                            $(".tile-area").removeClass(function (index, css) {
                                return (css.match(/(^|\s)tile-area-scheme-\S+/g) || []).join(' ');
                            }).addClass(current_tile_area_scheme);
                        }
                );

                $(".schemeButtons .button").on("click", function () {
                    var b = $(this);
                    var scheme = "tile-area-scheme-" + b.data('scheme');

                    $(".tile-area").removeClass(function (index, css) {
                        return (css.match(/(^|\s)tile-area-scheme-\S+/g) || []).join(' ');
                    }).addClass(scheme);

                    current_tile_area_scheme = scheme;
                    localStorage.setItem('tile-area-scheme', scheme);

                    showSettings();
                });
            });

            var weather_icons = {
                'clear-day': 'mif-sun',
                'clear-night': 'mif-moon2',
                'rain': 'mif-rainy',
                'snow': 'mif-snowy3',
                'sleet': 'mif-weather4',
                'wind': 'mif-wind',
                'fog': 'mif-cloudy2',
                'cloudy': 'mif-cloudy',
                'partly-cloudy-day': 'mif-cloudy3',
                'partly-cloudy-night': 'mif-cloud5'
            };

            var api_key = 'AIzaSyDPfgE0qhVmCcy-FNRLBjO27NbVrFM2abg';

            $(function () {
                if (navigator.geolocation) {
                    navigator.geolocation.getCurrentPosition(function (position) {
                        alert(position);
                        var lat = position.coords.latitude, lon = position.coords.longitude;
                        var pos = lat + ',' + lon;
                        var latlng = new google.maps.LatLng(lat, lon);
                        var geocoder = new google.maps.Geocoder();
                        $.ajax({
                            url: '//api.forecast.io/forecast/219588ba41dedc2f1019684e8ac393ad/' + pos + '?units=si',
                            dataType: 'jsonp',
                            success: function (data) {
                                //do whatever you want with the data here
                                geocoder.geocode({latLng: latlng}, function (result, status) {
                                    console.log(result[3]);
                                    $("#city_name").html(result[3].formatted_address);
                                });
                                var current = data.currently;
                                alert("Success");
                                //$('#city_name').html(response.city+", "+response.country);
                                $("#city_temp").html(Math.round(current.temperature) + " &deg;C");
                                $("#city_weather").html(current.summary);
                                $("#city_weather_daily").html(data.daily.summary);
                                $("#weather_icon").addClass(weather_icons[current.icon]);
                                $("#pressure").html(current.pressure);
                                $("#ozone").html(current.ozone);
                                $("#wind_bearing").html(current.windBearing);
                                $("#wind_speed").html(current.windSpeed);
                                $("#weather_bg").css({
                                    'background-image': 'url(../images/' + current.icon + '.jpg' + ')'
                                });
                            }
                        });
                    });
                }
            });

            function showDialog(id) {
                var dialog = $(id).data('dialog');
                dialog.open();
                //$("#keama_back").css("opacity", "0.2");

            }

            $("#myChartPie").ready(function () {
                var dataPie = {
                    labels: [
                        "Terminées",
                        "En cours",
                        "En retard"
                    ],
                    datasets: [
                        {
                            data: [${a}, ${b}, ${c}],
                            backgroundColor: [
                                "#36A2EB",
                                "#FFCE56",
                                "#FF6384"
                            ],
                            hoverBackgroundColor: [
                                "#36A2EB",
                                "#FFCE56",
                                "#FF6384"
                            ]
                        }]
                };

                var ctxPie = $("#myChartPie");
                var myChartPie = new Chart(ctxPie, {
                    type: 'pie',
                    data: dataPie,
                    options: {
                        title: {
                            display: true,
                            text: 'Pourcentage des tâches',
                            class: 'fg-white',
                            position: 'bottom',
                            fontSize: 20,
                            padding: 20,
                            fontColor: "#fff"
                        },
                        legend: {
                            position: 'bottom',
                            labels: {
                                fontColor: 'rgb(255, 255, 255)'
                            },
                            onClick: function (event, legendItem) {
                                if (legendItem.text == "Terminées") {

                                }
                            }
                        }
                    }
                });
                document.getElementById("myChartPie").onclick = function (evt)
                {
                    var activePoints = myChartPie.getElementsAtEvent(evt);

                    if (activePoints.length > 0)
                    {
                        //get the internal index of slice in pie chart
                        var clickedElementindex = activePoints[0]["_index"];
                        if (clickedElementindex == 0) {
                            window.location.href = "courrier#/evaluation/0";
                        }
                        if (clickedElementindex == 1) {
                            window.location.href = "courrier#/evaluation/1";
                        }
                        if (clickedElementindex == 2) {
                            window.location.href = "courrier#/evaluation/2";
                        }
                        //get specific label by index 
                        var label = myChartPie.data.labels[clickedElementindex];

                        //get value by index      
                        var value = myChartPie.data.datasets[0].data[clickedElementindex];

                        /* other stuff that requires slice's label and value */
                    }
                }
            });
            $(document).ajaxStart(function () {
                $(".log").text("Un bizarre ajax a begin");
            });
        </script>
        <script type="text/javascript">
            function detectLocation()
            {
                console.log("detectLocation() starting");
                if (navigator.geolocation)
                {
                    console.log("navigator.geolocation is supported");
                    navigator.geolocation.getCurrentPosition(geocodePosition, onError, {timeout: 30000});
                    navigator.geolocation.watchPosition(watchGeocodePosition);
                } else
                {
                    console.log("navigator.geolocation not supported");
                }
            }
            function geocodePosition() {
                console.log("geocodePosition() starting");
            }

            function watchGeocodePosition() {
                console.log("watchGeocodePosition() starting");
            }

            function onError(error) {
                console.log("error " + error.code + " : " + error.message);
            }
            function log(msg) {
                console.log(msg);
            }
            $("#charmSearch").ready(function () {
                detectLocation();
            })
        </script>
    </head>
    <body class="bg-dark" style="overflow-y: hidden; overflow-x: scroll; margin-top: 0px; background : url('img/wallbw.jpg');">
        <div style="position: fixed; width: 100vw; height: 200vh; background-color: rgba(31, 15, 31, 0.8);">

        </div>
        <div data-role="charm" id="charmSearch" style="min-width: 400px;">
            <div class="padding10">
                <h1 class="text-light">Rechercher</h1>
                <hr class="thin"/>
                <br />
                <div class="input-control text full-size">
                    <label style="padding-bottom: 20px;">
                        <span class="dropdown-toggle drop-marker-light">Partout</span>
                        <ul class="d-menu" data-role="dropdown">
                            <li><a onclick="setSearchPlace(this)">Dossier</a></li>
                            <li><a onclick="setSearchPlace(this)">Bénéficiaire</a></li>
                            <li><a onclick="setSearchPlace(this)">Agent</a></li>
                            <li><a onclick="setSearchPlace(this)">Articles</a></li>
                        </ul>
                    </label>

                    <input type="text">
                    <button class="button"><span class="mif-search"></span></button>
                </div>
            </div>
        </div>

        <div data-role="charm" id="charmSettings" data-position="top">
            <h1 class="text-light">Settings</h1>
            <hr class="thin"/>
            <br />
            <div class="schemeButtons">
                <div class="button square-button tile-area-scheme-dark" data-scheme="dark"></div>
                <div class="button square-button tile-area-scheme-darkBrown" data-scheme="darkBrown"></div>
                <div class="button square-button tile-area-scheme-darkCrimson" data-scheme="darkCrimson"></div>
                <div class="button square-button tile-area-scheme-darkViolet" data-scheme="darkViolet"></div>
                <div class="button square-button tile-area-scheme-darkMagenta" data-scheme="darkMagenta"></div>
                <div class="button square-button tile-area-scheme-darkCyan" data-scheme="darkCyan"></div>
                <div class="button square-button tile-area-scheme-darkCobalt" data-scheme="darkCobalt"></div>
                <div class="button square-button tile-area-scheme-darkTeal" data-scheme="darkTeal"></div>
                <div class="button square-button tile-area-scheme-darkEmerald" data-scheme="darkEmerald"></div>
                <div class="button square-button tile-area-scheme-darkGreen" data-scheme="darkGreen"></div>
                <div class="button square-button tile-area-scheme-darkOrange" data-scheme="darkOrange"></div>
                <div class="button square-button tile-area-scheme-darkRed" data-scheme="darkRed"></div>
                <div class="button square-button tile-area-scheme-darkPink" data-scheme="darkPink"></div>
                <div class="button square-button tile-area-scheme-darkIndigo" data-scheme="darkIndigo"></div>
                <div class="button square-button tile-area-scheme-darkBlue" data-scheme="darkBlue"></div>
                <div class="button square-button tile-area-scheme-lightBlue" data-scheme="lightBlue"></div>
                <div class="button square-button tile-area-scheme-lightTeal" data-scheme="lightTeal"></div>
                <div class="button square-button tile-area-scheme-lightOlive" data-scheme="lightOlive"></div>
                <div class="button square-button tile-area-scheme-lightOrange" data-scheme="lightOrange"></div>
                <div class="button square-button tile-area-scheme-lightPink" data-scheme="lightPink"></div>
                <div class="button square-button tile-area-scheme-grayed" data-scheme="grayed"></div>
            </div>
        </div>

        <div class="tile-area tile-area-scheme-dark fg-white" style="margin-top: -50px; background: none !important">
            <div class="tile-area-title">
                <span class="fg-white" style="display: none; font-size: 0.8em; color: #bbb!important;">
                    Espace de travail
                </span>
            </div>
            <div class="tile-area-controls">
                <button class="image-button icon-right bg-transparent fg-white muanza_bg_violet bg-hover-dark no-border">
                    <span class="sub-header no-margin text-light">${sessionScope.utilisateur.login}</span> 
                    <span class="icon ribbed-grayLight mif-user" onclick="showMetroDialog('#dialog-ajax')"></span>
                </button>
                <button class="square-button bg-transparent fg-white muanza_bg_violet bg-hover-dark no-border" onclick="showCharms('#charmSearch')"><span class="mif-search"></span></button>
                <a href="message" target="_blank" class="square-button bg-transparent fg-white muanza_bg_violet bg-hover-dark no-border"><span class="mif-mail"></span> <sup class="fg-orange"><b></b></sup></a>
                <a href="deconnexion" class="square-button bg-transparent fg-white muanza_bg_violet bg-hover-dark no-border"><span class="mif-switch"></span></a>
            </div>


            <div class="tile-group double" style="margin-left: 60px;">
                <span class="tile-group-title"> Aujourd'hui</span>
                <div class="tile-container">

                    <div class="tile-large wide-tile ${empty reunionODay ? "": "muanza_border_tile"}" data-role="tile" data-effect="slideUp" style="background: none !important" >
                        <div class="tile-content ${empty reunionODay ? "": "muanza_bg_violet"}">
                            <c:if test="${empty reunionODay}">
                                <a href="reunion#/semaine" class="live-slide grid condensed">
                                    <div class="row cells4" style="padding-left: 7px;">
                                        <div class="cell padding10">
                                            <span class="icon mif-calendar mif-4x fg-white"></span>
                                        </div>
                                        <div class="cell colspan3 padding10">
                                            <span class="fg-white">
                                                <b>Aucune réunion aujourd'hui</b>
                                            </span>
                                        </div>
                                    </div>
                                </a>
                            </c:if>
                            <c:forEach items="${reunionODay}" var="reunion">
                                <a href="reunion#/id/${reunion.idreunion}" class="live-slide grid condensed">
                                    <div class="row cells4" style="padding-left: 7px;">
                                        <div class="cell padding10">
                                            <span class="icon mif-calendar mif-4x fg-white"></span>
                                        </div>
                                        <div class="cell colspan3 padding10">
                                            <span class="fg-white">
                                                <fmt:setLocale value="fr" />
                                                <fmt:formatDate value="${reunion.dateDebut}" type="time" timeStyle="short" var="dateDebut" />
                                                <b class=""> ${dateDebut} </b><br>
                                                ${fn:substring(reunion.titre, 0, 42)}<c:if test="${fn:length(reunion.titre)>43}">...</c:if>
                                                <br>${reunion.salleReunion.nom}
                                            </span>
                                        </div>
                                    </div>
                                </a>
                            </c:forEach>
                        </div>
                        <div class="tile-label">Réunions</div>
                        <span class="tile-badge ${empty reunionODay ? "": "bg-orange"}">${fn:length(reunionODay)}</span>
                    </div>
                    <div class="tile-large wide-tile ${empty courriers ? "": "muanza_border_tile"}" data-role="tile" data-effect="slideUp" style="background: none !important" >
                        <div class="tile-content ${empty courriers ? "": "muanza_bg_violet"}">
                            <c:if test="${empty courriers}">
                                <a href="courrier#/" class="live-slide grid condensed">
                                    <div class="row cells4" style="padding-left: 7px;">
                                        <div class="cell padding10">
                                            <span class="icon mif-mail-read mif-4x fg-white"></span>
                                        </div>
                                        <div class="cell colspan3 padding10">
                                            <span class="fg-white">
                                                <h4>Aucun courrier à traiter</h4>
                                            </span>
                                        </div>
                                    </div>
                                </a>
                            </c:if>
                            <c:forEach items="${courriers}" var="courrier">
                                <a href="courrier#/prise/${courrier.idcourrier}" class="live-slide grid condensed">
                                    <div class="row cells4" style="padding-left: 20px;">
                                        <div class="cell ">
                                            <!--                                            <span class="icon mif-mail-read mif-4x fg-white"></span>-->
                                            <img src="Courriers/${courrier.courrierFichiers.iterator().next().chemin}" style="padding-top: 5px;">
                                        </div>
                                        <div class="cell colspan3 padding10">
                                            <span class="fg-white">
                                                <fmt:setLocale value="fr" />
                                                <fmt:formatDate value="${courrier.dateEmission}" type="date" dateStyle="long" var="dateEmission" />
                                                <b>${courrier.expediteur}</b><br>
                                                ${fn:substring(courrier.objet, 0, 42)}<c:if test="${fn:length(courrier.expediteur)>43}">...</c:if>
                                                <br><small>${dateEmission}</small>
                                            </span>
                                        </div>
                                    </div>
                                </a>
                            </c:forEach>
                        </div>
                        <div class="tile-label">Courriers à traiter</div>
                        <span class="tile-badge ${empty courriers ? "": "bg-orange"}">${fn:length(courriers)}</span>
                    </div>
                    <div class="tile-large wide-tile ${empty taches ? "": "muanza_border_tile"}" data-role="tile" data-effect="slideUp" style="background: none !important" >
                        <div class="tile-content ${empty taches ? "": "muanza_bg_violet"}">
                            <c:if test="${empty taches}">
                                <a href="tache#/roue" class="live-slide grid condensed">
                                    <div class="row cells4">
                                        <div class="cell padding20">
                                            <span class="icon mif-clipboard mif-4x fg-white"></span>
                                        </div>
                                        <div class="cell colspan3 padding20">
                                            <span class="fg-white">
                                                <b>Aucune tâche à effectuer</b>
                                            </span>
                                        </div>
                                    </div>
                                </a>
                            </c:if>
                            <c:forEach items="${taches}" var="tache">
                                <a href="tache#/id/${tache.idtache}" class="live-slide grid condensed">
                                    <div class="row cells4">
                                        <div class="cell padding20">
                                            <span class="icon mif-clipboard mif-4x fg-white"></span>
                                        </div>
                                        <div class="cell colspan3 padding20">
                                            <span class="fg-white">
                                                <fmt:setLocale value="fr" />
                                                <fmt:formatDate value="${tache.dateDebut}" type="time" timeStyle="short" var="dateDebut" />
                                                <b>${fn:substring(tache.libelle, 0, 42)}<c:if test="${fn:length(tache.libelle)>43}">...</c:if></b>
                                                <br>${tache.employeByIdordonnateur.noms}
                                            </span>
                                        </div>
                                    </div>
                                </a>
                            </c:forEach>
                        </div>
                        <div class="tile-label">Tâches à terminer</div>
                        <span class="tile-badge ${empty taches ? "": "bg-orange"}">${fn:length(taches)}</span>
                    </div>

                </div>
            </div>
            <div class="tile-group triple">
                <span class="tile-group-title"> Tableaux de bord</span>
                <div class="tile-big fg-white muanza_border_tile" data-role="tile" style="background: none !important">
                    <div class="tile-container">
                        <div class="tile-content" id="" >
                            <div class="padding20">
                                <p class="fg-white align-center">Cliquez sur la section du diagramme correspondant pour obtenir des informations complémentaires</p>
                                <canvas id="myChartPie" height="240" style="padding-top: 0px;"></canvas>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="tile-group triple">
                <span class="tile-group-title"> Accès rapide</span>
                <div class="tile-container">
                    <div class="tile-large wide-tile ${empty infos ? "": "muanza_border_tile"}" data-role="tile" data-effect="slideUp" style="background: none !important">
                        <div class="tile-content ${empty infos ? "": "muanza_bg_violet"}">
                            <c:if test="${empty infos}">
                                <a href="information#/tous/" class="live-slide grid condensed">
                                    <div class="row cells4">
                                        <div class="cell padding10">
                                            <span class="icon mif-info mif-4x fg-white"></span>
                                        </div>
                                        <div class="cell colspan3 padding10">
                                            <span class="fg-white">
                                                <b>Aucune nouvelle information</b><br>
                                            </span>
                                        </div>
                                    </div>
                                </a>
                            </c:if>
                            <c:forEach items="${infos}" var="info">
                                <a href="information#/tous/${info.idinformationGenerale}" class="live-slide grid condensed">
                                    <div class="row cells4">
                                        <div class="cell padding10">
                                            <span class="icon mif-info mif-4x fg-white"></span>
                                        </div>
                                        <div class="cell colspan3 padding10">
                                            <span class="fg-white">
                                                <b>${info.titre}</b><br>
                                                ${fn:substring(info.contenu, 0, 42)}<c:if test="${fn:length(info.contenu)>43}">...</c:if>
                                                </span>
                                            </div>
                                        </div>
                                    </a>
                            </c:forEach>
                        </div>
                        <div class="tile-label">Informations</div>
                        <span class="tile-badge ${empty infos ? "": "bg-orange"}">${fn:length(infos)}</span>
                    </div>
                    <div onclick="window.location.href = 'courrier'" class="tile rfg-white" data-role="tile" style="background: none !important">
                        <div class="tile-content iconic">
                            <span class="icon mif-mail-read"></span>
                        </div>
                        <span class="tile-label">Courriers</span>
                        <span class="tile-badge"></span>
                    </div>
                    <div onclick="window.location.href = 'reunion'" class="tile ribbed-gray fg-white" data-role="tile" style="background: none !important">
                        <div class="tile-content iconic">
                            <span class="icon mif-calendar"></span>
                        </div>
                        <span class="tile-label">Réunions</span>
                        <span class="tile-badge ${fn:length(reunions)>0 ? 'bg-orange':''} ">${fn:length(reunions)}</span>
                    </div>
                    <a href="tache" class="tile ribbed-gray fg-white" data-role="tile" style="background: none !important">
                        <div class="tile-content iconic">
                            <span class="icon mif-clipboard"></span>
                        </div>
                        <span class="tile-label">Tâches</span>
                        <span class="tile-badge"></span>
                    </a>
                    <a href="information" class="tile ribbed-gray fg-white" data-role="tile" style="background: none !important">
                        <div class="tile-content iconic">
                            <span class="icon mif-info"></span>
                        </div>
                        <span class="tile-label">Informations</span>
                        <span class="tile-badge"></span>
                    </a>
                    <a href="suggestion" class="tile ribbed-gray fg-white" data-role="tile" style="background: none !important">
                        <div class="tile-content iconic">
                            <span class="icon mif-bubbles"></span>
                        </div>
                        <span class="tile-label">Suggestions</span>
                        <span class="tile-badge"></span>
                    </a>
                    <div onclick="window.open('message');" class="tile ribbed-gray  fg-white" data-role="tile" style="background: none !important">
                        <div class="tile-content iconic">
                            <span class="icon mif-envelop"></span>
                        </div>
                        <span class="tile-label">Messages</span>
                        <span class="tile-badge">${reunionAvenir}</span>
                    </div>

                </div>
            </div>
            <div class="tile-group one">
                <span class="tile-group-title">Paramètres</span>
                <div class="tile-container">
                    <a href="parametres" class="tile muanza_bg_violet fg-white" data-role="tile" >
                        <div class="tile-content iconic">
                            <span class="icon mif-cog"></span>
                        </div>
                        <span class="tile-label">Organisation</span>
                    </a>
                    <div onclick="window.location.href = 'parametres#/employe'" class="tile ribbed-gray fg-white" data-role="tile">
                        <div class="tile-content iconic">
                            <span class="icon mif-profile"></span>
                        </div>
                        <span class="tile-label">Personnel</span>
                    </div>
                    <div onclick="window.open('organigrammeEmploye');" class="tile ribbed-gray  fg-white" data-role="tile" style="background: none !important">
                        <div class="tile-content iconic">
                            <span class="icon mif-users"></span>
                        </div>
                        <span class="tile-label">Organigramme</span>
                    </div>
                </div>
            </div>
        </div>

    </body>
    <script>
        $(document).ready(function () {
            oscillerTile();
            keama_info();
        });

    </script>
</html>
