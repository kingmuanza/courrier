<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script>

            //Filtrer les éléments de la liste

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
                        extend: 'print',
                        text: "Imprimer",
                        title: titre,
                        message: 'KEAMA WORKSPACE',
                        className: 'impression ribbed-grayLighter bd-white'
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

            var viewer = new Viewer(document.getElementById('images'), {});

            var dataPie = {
                labels: [
                    "Etapes Terminées",
                    "Etapes en cours",
                    "Etapes à suivre"
                ],
                datasets: [
                    {
                        data: [${a}, ${b}, ${c-a-b}],
                        backgroundColor: [
                            "#60a917",
                            "#36A2EB",
                            "#FFCE56"
                        ],
                        hoverBackgroundColor: [
                            "#60a917",
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
                        padding: 20,
                        text: 'Pourcentage des courriers traités'
                    },
                    legend: {
                        position: 'bottom',
                        padding: 40,
                        labels: {

                        },
                        onClick: function (event, legendItem) {
                            if (legendItem.text == "Terminées") {

                            }
                        }
                    }
                }
            });
        </script>
    </head>
    <body>
        <div id="keama_back" class="grid" style="height: 100%; margin-top: -0px; background-color: #fafafa">
            <div class="row cells6" style="height: 100%">

                <div class="cell colspan3 padding10" style="height: 100%">

                    <div class="row cells6">
                        <div class="" style="border: 1px solid #310e31; padding: 0px;">
                            <div class="padding10">


                                <div class="step">
                                    <div class="step-content">
                                        <div class="ribbed-grayLighter padding20" style="padding-right: 20px; border-bottom: 1px solid #310e31;">
                                            <div class="row cells" style="padding-top: 0px;">
                                                <div class="cell">
                                                    <small>${courrier.ref}</small>
                                                    <h1 class="no-wrap muanza_fg_violet">${courrier.expediteur}</h1>

                                                </div>

                                            </div>
                                            <div class="row cells3" style="padding-top: 20px;">
                                                <div class="cell">
                                                    <fmt:setLocale value="fr" />
                                                    <fmt:formatDate value="${courrier.dateEmission}" type="date" dateStyle="long" var="jour" />
                                                    <fmt:formatDate value="${courrier.dateEnr}" type="date" dateStyle="long" var="debut" />
                                                    <small><span class="mif-calendar mif-2x place-left padding5"></span> Envoyé le : <span class="muanza_fg_violet">${jour}</span> <br>Enregistré le : <span class="muanza_fg_violet">${debut}</span></small>
                                                </div>
                                                <div class="cell">
                                                    <small><span class="mif-folder-open mif-2x place-left padding5"></span> Nature : <span class="muanza_fg_violet">${courrier.nature}</span> <br> Support : <span class="muanza_fg_violet">${courrier.support}</span></small>
                                                </div>
                                                <div class="cell">
                                                    <small><span class="mif-user mif-2x place-left padding5"></span> ${courrier.correspondant.nomComplet} <br>${courrier.correspondant.poste}</small>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="listview set-border padding20 list-type-listing " data-role="listview">
                                            <h4 class="no-wrap">Objet : <span class="muanza_fg_violet">${courrier.objet}</span></h4>
                                        </div>

                                        <div class="row">
                                            <c:if test="${fn:length(courrier.courrierFichiers)>0}">
                                                <div class="docs-galley">
                                                    <ul id="images" class="docs-pictures clearfix">
                                                        <c:forEach items="${courrier.courrierFichiers}" var="cf">
                                                            <li><img data-original="img/qrcode.JPG" src="Courriers/${cf.chemin}" alt="Picture"></li>
                                                            </c:forEach>
                                                    </ul>
                                                </div>
                                                <p class="padding20">
                                                    <a class="ribbed-grayLighter button bd-white" href="courrier#/ordonner/${courrier.idcourrier}">Réorganiser les fichiers</a>
                                                </p>
                                            </c:if>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row cells3">
                    </div>
                </div>
                <div id="menu_droit" class="cell colspan3">
                    <div class="row">
                        <div class="padding20">
                            <small class="place-right">${!empty courrier.courrierProcedureEnCourses ? courrier.courrierProcedureEnCourses.iterator().next().courrierProcedure.nom :""}</small>
                            <h4 class="no-wrap"><span class="muanza_fg_violet">Suivi du courrier</span></h4>
                            <h4 class="no-wrap"><span class="fg-green">${!empty courrier.courrierProcedureEnCourses ? courrier.courrierProcedureEnCourses.iterator().next().ref :""}</span></h4>
                            <br>
                            <table id="example_table" class="dataTable border bordered" style="margin-bottom: 10px;">
                                <thead>
                                    <tr>
                                        <c:if test="${!empty courrier.courrierProcedureEnCourses}">
                                            <th>Ordre</th>
                                            </c:if>
                                        <th>Date</th>
                                        <th>Responsable</th>
                                        <th>Travail à effectuer</th>

                                        <th>Date limite</th>
                                        <th>Statut</th>
                                    </tr>
                                </thead>

                                <tfoot >
                                    <tr class="">
                                        <c:if test="${!empty courrier.courrierProcedureEnCourses}">
                                            <th>Ordre</th>
                                            </c:if>
                                        <th>Date</th>
                                        <th>Responsable</th>
                                        <th>Travail à effectuer</th>

                                        <th>Date limite</th>
                                        <th>Statut</th>
                                    </tr>
                                </tfoot>

                                <tbody>
                                    <c:forEach items="${courrier.courrierResponsables}" var="c">
                                        <tr class="${c.employe.idemploye==sessionScope.utilisateur.employe.idemploye ? "ribbed-grayLighter":""}">
                                            <c:if test="${!empty courrier.courrierProcedureEnCourses}">
                                                <td>${c.courrierProcedureTache.ordre}</td>
                                            </c:if>
                                            <fmt:setLocale value="fr" />
                                            <fmt:formatDate value="${c.dateRecuperation}" type="date" dateStyle="long" var="dateRecuperation" />
                                            <fmt:formatDate value="${c.dateRecuperation}" pattern="yyyy.MM.dd HH:mm:ss" var="dateRecuperation2" />
                                            <td><span style="display: none;">${dateRecuperation2}</span>${dateRecuperation}</td>
                                            <td>${c.employe.noms}</td>
                                            <td>${empty c.typeApprobation ? "Indéfini": c.libelle}</td>

                                            <fmt:setLocale value="fr" />
                                            <fmt:formatDate value="${c.dateLimite}" type="date" dateStyle="long" var="dateLimite" />
                                            <td>${empty c.dateLimite ? "Aucune":dateLimite}</td>
                                            <td class="align-center">${c.statut=="Terminé"? "<span class='mif-checkmark fg-green '></span>":c.statut}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>

                   
                </div>
            </div>
        </div>

    </body>
</html>
