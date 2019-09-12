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
                        <c:forEach items="${reunions}" var="c">
                            <li><a href="#/id/${c.idreunion}">
                                    <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${c.dateDebut}" var="dateDebut"/>
                                    <span class="mif-calendar icon"></span>
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
                        var x = 0.8 * $('#menu_droit').width() - 50;
                        console.log(x);
                        $(".image-button").each(function (index) {
                            $(this).width(x);
                        });

                        function showDialog(id) {
                            var dialog = $(id).data('dialog');
                            dialog.open();
                            //$("#keama_back").css("opacity", "0.2");

                        }

                        function archiver(id) {

                            $.confirm({
                                title: 'Confirmation',
                                boxWidth: '30%',
                                useBootstrap: false,
                                content: 'Etes-vous sûr de vouloir archiver la réunion ?',
                                type: 'orange',
                                buttons: {
                                    Oui: {
                                        text: 'Oui, je le veux',
                                        btnClass: 'button ribbed-orange fg-white',
                                        keys: ['enter', 'shift'],
                                        action: function () {
                                            $.post(
                                                    "AfficherReunionServlet",
                                                    {
                                                        action: "archiver",
                                                        id: id
                                                    }
                                            );
                                            var tempsEnMs = Date.now();
                                            window.open("reunion#/mes/light" + tempsEnMs);
                                            window.close();
                                        }
                                    },
                                    Annuler: {
                                        text: 'Non',
                                        btnClass: 'button ribbed-gray fg-white',
                                        keys: ['enter', 'shift'],
                                        action: function () {

                                        }
                                    }
                                }
                            });
                        }
                    </script>
                </div>
                <div class="cell colspan7" style="height: 100%">
                    <div class="">
                        <h1 id="titre_liste">Réunion ${reunion.ref}</h1>

                    </div>
                    <div class="row cells6">
                        <div class="" style="border: 1px solid #1ba1e2; padding: 10px;">
                            <div class="wizard2"
                                 data-role="wizard2"
                                 data-button-labels='{"help": "?", "prev": "<span class=\"mif-arrow-left\"></span>", "next": "<span class=\"mif-arrow-right\"></span>", "finish": "<span class=\"mif-checkmark\"></span>"}'>


                                <div class="step">
                                    <div class="step-content">
                                        <p class="text-small">Créé le 01/01/2016 à 00:00</p>
                                        <div class="clear-float">
                                            <div style="width: 100px; margin-right: 20px" class="place-left">
                                                <img src="img/reunion.jpg" data-role="fitImage" data-format='square' data-type='bordered'>
                                            </div>
                                            <div class="place-left">
                                                <h4 class="no-wrap"> ${reunion.titre}</h4>
                                                <h5>Convoqué par : ${reunion.employe.noms} ${reunion.employe.prenoms}</h5>
                                                ${reunion.dateDebut} - ${reunion.dateFin}
                                            </div>
                                        </div>

                                        <br />
                                        <div class="padding20 bg-grayLighter no-phone">
                                            ${reunion.description}
                                        </div>
                                        <div class="listview list-type-icons set-border padding10" data-role="listview" id="lv1">
                                            <c:forEach items="${reunion.reunionEmployes}" var="re">
                                                <div class="list">
                                                    <img src="img/group.png" class="list-icon">
                                                    <span class="list-title">${re.employe.noms} ${re.employe.prenoms}</span>
                                                </div>
                                            </c:forEach>
                                        </div>
                                        <c:if test="${fn:length(reunion.reunionRapports)>0}">
                                            <div class="row">
                                                <h3 class="fg-green">Liste des documents</h3>
                                                <ul class="">
                                                    <c:forEach items="${reunion.reunionRapports}" var="rr">
                                                        <li><a href="Rapports/${rr.chemin}">${rr.nom}</a></li>
                                                        </c:forEach>
                                                </ul>
                                            </div>
                                        </c:if>
                                    </div>
                                </div>


                            </div>
                        </div>

                    </div>
                </div>
                <div id="menu_droit" class="cell colspan2 bg-grayLighter">
                    <c:set var="now" value="<%=new java.util.Date()%>" />
                    <div class="bg-grayLighter" style="padding: 20px;">
                        <c:if test="${reunion.dateDebut > now}">
                            <div class="row align-center" >
                                <button class="image-button bg-blue fg-white">
                                    Annuler
                                    <span class="icon mif-cancel bg-lighterBlue fg-white"></span>
                                </button>
                            </div>
                            <div class="row align-center" >
                                <button class="image-button bg-blue fg-white">
                                    Reporter
                                    <span class="icon mif-alarm bg-darkIndigo fg-white"></span>
                                </button>
                            </div>
                        </c:if>
                        <c:if test="${reunion.dateFin < now}">
                            <div class="row align-center" >
                                <button class="image-button bg-blue fg-white" onclick="showDialog('#dialog2')">
                                    Ajouter document
                                    <span class="icon mif-file-empty bg-lightGreen fg-white"></span>
                                </button>
                            </div>
                        </c:if>
                        <c:if test="${reunion.dateFin < now}">
                            <div class="row align-center" >
                                <button class="image-button bg-blue fg-white" onclick="archiver(${reunion.idreunion})">
                                    Archiver
                                    <span class="icon mif-file-empty bg-lightGreen fg-white"></span>
                                </button>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
        <div data-role="dialog" id="dialog2" class="grid padding20 ribbed-grayLighter" data-close-button="true" data-width="400">
            <div class="row cells">
                <div class="cell" style="height: 100%">
                    <form method="post" action="RapportReunionServlet" enctype="multipart/form-data">
                        <label>Nom du document : </label>
                        <div class="input-control text full-size" data-role="input">
                            <input name="nom" value="">
                        </div>
                        <label>Sélectionnez le document </label>
                        <div class="input-control file full-size" data-role="input">
                            <input name="rapport" type="file" placeholder="Rechercher" accept="application/pdf">
                            <input name="reunion" type="hidden" value="${reunion.idreunion}">
                            <button class="button"><span class="mif-folder"></span></button>
                        </div>
                        <div class="input-control text full-size" data-role="input">
                            <input type="submit" class="bg-blue fg-white">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
