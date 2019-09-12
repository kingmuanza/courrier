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
                        <!--                        <h2>Rechercher</h2>-->
                        <div class="input-control text full-size info">
                            <span class="mif-search prepend-icon fg-blue"></span>
                            <input id="rechercher" type="text" placeholder="Rechercher">
                        </div>
                    </div>
                    <ul class="sidebar darcula" style="height: 260px; background-image: url('img/keama_background.png');">
                        <c:forEach items="${informations}" var="c">
                            <li><a href="#/id/${c.idinformationGenerale}">
                                    <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${c.dateEnregistrement}" var="dateDebut"/>
                                    <span class="mif-info icon"></span>
                                    <span class="title">${c.titre}</span>
                                    <span class="counter">${dateDebut}</span>
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
                    </script>
                </div>
                <div class="cell colspan7" style="height: 100%">
                    <div class="row cells6">
                        <h1 id="titre_liste">Toutes les informations<span class="icon mif-folder-open fg-blue place-right" style="cursor: pointer;" onclick="window.location.href = 'information#/tous/light'"></span></h1>
                        <hr>
                    </div>
                    <div class="row cells6">
                        <table id="example_table" class="dataTable border bordered" style="margin-bottom: 10px;">
                            <thead>
                                <tr>
                                    <th>Date </th>
                                    <th>Référence</th>
                                    <th>Convoqué par</th>
                                    <th>Action</th>
                                </tr>
                            </thead>

                            <tfoot >
                                <tr>
                                    <th>Date </th>
                                    <th>Référence</th>
                                    <th>Convoqué par</th>
                                    <th>Action</th>
                                </tr>
                            </tfoot>

                            <tbody>
                                <c:forEach items="${informations}" var="c">
                                    <tr>
                                        <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${c.dateEnregistrement}" var="dateDebut"/>
                                        <td>${dateDebut}</td>
                                        <td><a href="information#/id/${c.idinformationGenerale}">${c.titre}</a></td>
                                        <td>${c.employe.noms} ${c.employe.prenoms}</td>
                                        <td><span class="fg-blue" style="cursor: pointer;">Supprimer</span></td>
                                    </tr>
                                </c:forEach>

                            </tbody>
                        </table>

                    </div>
                </div>
                <div id="menu_droit" class="cell colspan2 bg-grayLighter">
                    
                    <div class="bg-grayLighter" style="padding: 20px;">
                        <div class="row align-center" >
                            <button class="image-button bg-blue fg-white" onclick="showDialog('#nouveau_courrier')">
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

    </body>
</html>
