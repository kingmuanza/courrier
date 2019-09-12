<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div id="keama_back" class="grid" style="height: 100%; margin-top: -0px">
            <div class="row cells12" style="height: 100%">
                <div class="cell colspan3" id="cell-sidebar" style="background-image: url('img/keama_background.png'); height: 100%;">
                    <div id="block_recherche" class="fg-white" style="padding: 16px; display: block">
                        <!--                        <h2>Rechercher</h2>-->
                        <div class="input-control text full-size info">
                            <span class="mif-search prepend-icon fg-blue"></span>
                            <input id="rechercher" type="text" placeholder="Rechercher">
                        </div>
                    </div>
                    <ul class="sidebar darcula" style="height: 260px; background-image: url('img/keama_background.png');">
                        <c:forEach items="${postes}" var="poste">
                            <c:forEach items="${poste.employes}" var="c">
                                <li><a href="#/roue/${c.idemploye}">
                                        <span class="mif-user icon"></span>
                                        <span class="title">${c.noms} ${c.prenoms}</span>
                                        <span class="counter">${poste.libelle}</span>
                                    </a>
                                </li>
                            </c:forEach>
                        </c:forEach>

                    </ul>
                    <script>
                        //Chargement de la page
                        $("#spinner").hide();


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
                        $("#example_table").DataTable({
                            'searching': true,
                            "info": true,
                            dom: '<"top"fB>rt<"bottom"lp><"clear">',
                            buttons: [
                                {
                                    extend: 'excel',
                                    text: "Exporter vers Excel",
                                    title: titre,
                                    message: 'KEAMA WORKSPACE',
                                    className: 'impressionExcel'
                                },
                                {
                                    extend: 'pdfHtml5',
                                    text: "Exporter en PDF",
                                    title: titre,
                                    message: 'KEAMA WORKSPACE',
                                    className: 'impressionPDF'
                                },
                                {
                                    extend: 'print',
                                    text: "Imprimer",
                                    title: titre,
                                    message: 'KEAMA WORKSPACE',
                                    className: 'impression'
                                }
                            ],
                            "language": {
                                "sEmptyTable": "Aucune donnée disponible",
                                "sInfo": "Affiche _START_ à _END_ sur _TOTAL_ entrées",
                                "lengthMenu": "Afficher _MENU_ lignes par page",
                                "sSearch": "Rechercher : ",
                                "zeroRecords": "Aucun résultat",
                                "info": "Page _PAGE_ sur _PAGES_",
                                "infoEmpty": "Aucun résultat disponible",
                                "sProcessing": "Veuillez patienter...",
                                "infoFiltered": "(sur les _MAX_ disponibles)"
                            }
                        });

                        function keama_impression() {
                            $.Notify({
                                caption: 'Impression',
                                content: 'Impression du fichier en cours',
                                type: 'success'
                            });
                        }
                        function keama_export() {
                            $.Notify({
                                caption: 'Export',
                                content: 'Le fichier est en cours d\'exportation ',
                                type: 'success'
                            });
                        }
                        console.log("Largeur du menu droit : " + $('#menu_droit').width());
                        var x = 0.8 * $('#menu_droit').width() - 40;
                        console.log(x);
                        $(".image-button").each(function (index) {
                            $(this).width(x);
                        });

                        function showDialog(id) {
                            var dialog = $(id).data('dialog');
                            dialog.open();
                            //$("#keama_back").css("opacity", "0.2");

                        }

                        var dataPie = {
                            labels: [
                                "Retard",
                                "Terminées",
                                "En cours"
                            ],
                            datasets: [
                                {
                                    data: [${fn:length(retard)}, ${fn:length(terminees)}, ${fn:length(cours)}],
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
                    </script>
                </div>
                <div class="cell colspan7" style="height: 100%">
                    <div class="row cells7">
                        <c:if test="${!empty employe}">
                            <h1 id="titre_liste">
                                Tableau de bord
                            </h1>
                            <span class="mif-user fg-blue"></span> <small> ${employe.noms} ${employe.prenoms}</small>
                            <hr class="bg-blue">
                        </c:if>
                        <c:if test="${empty employe}">
                            <h1 id="titre_liste">
                                Tableau de bord de mon service
                            </h1>
                            <hr class="bg-blue">
                        </c:if>
                    </div>
                    <div class="row cells6">
                        <div class="cell colspan3 keama_chart" style="overflow: hidden ; border: 1px #eeeeee solid;">
                            <canvas id="myChartPie" style="padding: 10px;" height="240"></canvas>
                        </div>
                        <div class="cell colspan2 keama_chart padding10" style="overflow: hidden ; border: 1px #eeeeee solid;">
                            <p>En retard : ${fn:length(retard)}</p>
                            <p>En cours : ${fn:length(cours)}</p>
                            <p>Terminées : ${fn:length(terminees)}</p>
                        </div>

                    </div>
                    <div class="row cells3" style="padding-bottom: 100px ;">
                        <div class="cell">
                            <h3>Tâches en retard <sup class="fg-orange" style="cursor: pointer;">${fn:length(retard)}</sup></h3>
                            <hr class="bg-blue">
                            <div class="accordion" data-role="accordion" data-close-any="true">
                                <c:forEach items="${retard}" var="tache">
                                    <div class="frame">
                                        <fmt:setLocale value="fr" />
                                        <fmt:formatDate value="${tache.dateFin}" type="date" dateStyle="long" var="now2" />
                                        <div class="heading">${tache.libelle}</div>
                                        <div class="content"><p>${tache.description}</p><small>A terminé le <a href="tache#/avenir/light/${tache.idtache}">${now2}</a></small></div>
                                            </c:forEach>
                                </div>
                            </div>
                            <div class="cell">
                                <h3>Tâches en cours <sup class="fg-green" style="cursor: pointer;">${fn:length(cours)}</sup></h3>
                                <hr class="bg-blue">
                                <div class="accordion" data-role="accordion" data-close-any="true">
                                    <c:forEach items="${cours}" var="tache">
                                        <div class="frame">
                                            <fmt:setLocale value="fr" />
                                            <fmt:formatDate value="${tache.dateFin}" type="date" dateStyle="long" var="now2" />
                                            <div class="heading">${tache.libelle}</div>
                                            <div class="content"><p>${tache.description}</p><small>A terminé le <a href="tache#/avenir/light/${tache.idtache}">${now2}</a></small></div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                            <div class="cell">
                                <h3>Tâches terminées <sup class="fg-blue" style="cursor: pointer;">${fn:length(terminees)}</sup></h3>
                                <hr class="bg-blue">
                                <div class="accordion" data-role="accordion" data-close-any="true">
                                    <c:forEach items="${terminees}" var="tache">
                                        <div class="frame">
                                            <fmt:setLocale value="fr" />
                                            <fmt:formatDate value="${tache.dateFin}" type="date" dateStyle="long" var="now2" />
                                            <div class="heading">${tache.libelle}</div>
                                            <div class="content"><p>${tache.description}</p><small>A terminé le <a href="tache#/avenir/light/${tache.idtache}">${now2}</a></small></div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div id="menu_droit" class="cell bg-grayLighter colspan2" style="padding: 20px;">

                        <div class="row align-center" >
                            <button class="image-button bg-blue fg-white" onclick="showDialog('#nouveau_courrier')">
                                Nouveau
                                <span class="icon mif-file-empty bg-lightGreen fg-white"></span>
                            </button>
                        </div>
                    </div>
                </div>
            </div>

    </body>
</html>
