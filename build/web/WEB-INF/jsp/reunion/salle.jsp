<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                        <div class="input-control text full-size info">
                            <span class="mif-search prepend-icon fg-blue"></span>
                            <input id="rechercher" type="text" placeholder="Rechercher">
                        </div>
                    </div>
                    <ul class="sidebar darcula" style="height: 260px; background-image: url('img/keama_background.png');">
                        <c:forEach items="${salles}" var="c">
                            <li><a href="#/salle/${c.idsalleReunion}">
                                    <span class="mif-location-city icon"></span>
                                    <span class="title">${c.nom}</span>
                                    <span class="counter">${c.statut}</span>
                                </a>
                            </li>
                        </c:forEach>

                    </ul>
                    <script>
                            
                        //Chargement de la page
                        $("#spinner").hide();


                        //Filtrer les éléments de la liste
                        jQuery.expr[':'].icontains = function (a, i, m) {
                            return jQuery(a).text().toUpperCase()
                                    .indexOf(m[3].toUpperCase()) >= 0;
                        };
                        //Application
                        $("#rechercher").keyup(function () {
                            var mot = $("#rechercher").val();
                            console.log(mot);
                            $("ul.sidebar > li").css("display", "none");
                            $("ul.sidebar > li").each(function (index) {
                                console.log(index + ": " + $(this).find(".title:icontains('" + mot + "')").text());
                                if ($(this).find(".title:icontains('" + mot + "')").length > 0) {
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
                                    className: 'impressionExcel ribbed-grayLighter bd-white'
                                },
                                {
                                    extend: 'pdfHtml5',
                                    text: "Exporter en PDF",
                                    title: titre,
                                    message: 'KEAMA WORKSPACE',
                                    className: 'impressionPDF ribbed-grayLighter bd-white'
                                },
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
                        function closeDialog(id) {
                            var dialog = $(id).data('dialog');
                            dialog.close();
                            //$("#keama_back").css("opacity", "0.2");

                        }

                        <c:if test="${!empty salle}">
                        $(document).ready(function () {
                            showDialog('#nouvelle_salle');
                        });
                        showDialog('#nouvelle_salle');
                        </c:if>
                    </script>
                </div>
                <div class="cell colspan7" style="height: 100%">
                    <div class="row cells6">
                        <h1 id="titre_liste">Salles de réunion</h1>
                        <hr>
                    </div>

                    <div class="row cells6">
                        <table id="example_table" class="dataTable border bordered" style="margin-bottom: 10px;">
                            <thead>
                                <tr>
                                    <th>Nom</th>
                                    <th>Capacité</th>
                                    <th>Statut</th>
                                </tr>
                            </thead>

                            <tfoot >
                                <tr>
                                    <th>Nom</th>
                                    <th>Capacité</th>
                                    <th>Statut</th>
                                </tr>
                            </tfoot>

                            <tbody>
                                <c:forEach items="${salles}" var="c">
                                    <tr>
                                        <td><a href="#salle/${c.idsalleReunion}">${c.nom}</a></td>
                                        <td>${c.capacite}</td>
                                        <td>${c.statut}</td>
                                    </tr>
                                </c:forEach>

                            </tbody>
                        </table>

                    </div>


                </div>
                <div id="menu_droit" class="cell colspan2 bg-grayLighter">

                    <div class="bg-grayLighter" style="padding: 20px;">
                        <div class="row align-center" >
                            <button class="image-button bg-blue fg-white" onclick="showDialog('#nouvelle_salle');">
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
                            <button class="image-button bg-blue fg-white" onclick="keama_export(); $('.impressionExcel').click();">
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
        </div>
        <div data-role="dialog" id="nouvelle_salle" class="grid padding20 ribbed-grayLighter" data-close-button="false" data-width="800">
            <div class="row cells6">
                <div class="row cells6">
                    <h3>${empty salle ? "Nouvelle salle de réunion":salle.nom}</h3>
                    <hr>
                </div>
                <form method="post" action="SalleReunionServlet" class="grid condensed">

                    <div class="row cells6">
                        <div class="cell colspan6" style="padding-right: 10px;">
                            <label>Nom</label>
                            <div class="input-control text full-size">
                                <input name="nom" value="${salle.nom}">
                                <input name="id" type="hidden" value="${salle.idsalleReunion}">
                            </div>
                        </div>
                    </div>
                    <div class="row cells2">
                        <div class="cell" style="padding-right: 10px;">
                            <label >Capacité</label>
                            <div class="input-control text full-size">
                                <input name="capacite" type="number" value="${salle.capacite}">
                            </div>
                        </div>
                        <div class="cell">
                            <label >Statut</label>
                            <div class="input-control select full-size">
                                <select name="statut">
                                    <option value="Disponible">Disponible</option>
                                    <option value="Occupé" ${salle.statut=="Occupé" ? "selected":""}>Occupé</option>
                                    <option value="Indisponible" ${salle.statut=="Indisponible" ? "selected":""}>Indisponible</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div style="padding-top: 20px;">
                        <input type="submit" class="button ribbed-green fg-white" value="${empty salle ? 'Enregistrer':'Modifier'}">
                        <a class="button fg-white bg-orange" href="#/salle" onclick="closeDialog('#nouvelle_salle');">Annuler</a>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
