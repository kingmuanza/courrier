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
                <div class="cell colspan3" id="cell-sidebar" style="height: 100%;">
                    <div id="block_recherche" class="fg-white" style="padding: 16px; display: block">
                        <!--                        <h2>Rechercher</h2>-->
                        <div class="input-control text full-size warning">
                            <span class="mif-search prepend-icon" style="color: orange"></span>
                            <input id="rechercher" type="text" placeholder="Rechercher">
                        </div>
                    </div>
                    <ul class="sidebar" style="overflow-x:hidden; padding-left: 0px; height: 60vh; overflow-y: auto; background-color: orange;">
                        
                        <c:forEach items="${reunion.reunionEmployes}" var="re">
                            <li class="li_muanza">
                                <a target="_blank" href="message?id=${re.employe.idemploye}">
                                    <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${c.dateDebut}" var="dateDebut"/>
                                    <span class="mif-user icon"></span>
                                    <span class="title">${re.employe.noms}</span>
                                    <span class="counter">${re.employe.poste.libelle}</span>
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

                        $(".timer_keama").TimeCircles(
                                {
                                    start: true,
                                    animation_interval: "smooth",
                                    use_background: true,
                                    fg_width: 0.1
                                }
                        );
                    </script>
                </div>
                <div class="cell colspan6" style="height: 100%">
                    <div class="padding10">


                    </div>
                    <div class="row cells6">
                        <div class="" style="border: 1px solid #310e31; padding: 0px;">
                            <div class="wizard2"
                                 data-role="wizard2"
                                 data-button-labels='{"help": "?", "prev": "<span class=\"mif-arrow-left\"></span>", "next": "<span class=\"mif-arrow-right\"></span>", "finish": "<span class=\"mif-checkmark\"></span>"}'>


                                <div class="step">
                                    <div class="step-content">
                                        <div class="ribbed-grayLighter padding20" style="padding-right: 20px; border-bottom: 1px solid #310e31;">
                                            <div class="row cells3" style="padding-top: 0px;">
                                                <div class="cell colspan2">
                                                    <small>${reunion.ref}</small>
                                                    <h1 class="no-wrap muanza_fg_violet">${reunion.titre}</h1>
                                                </div>
                                                <div class="cell">
                                                    <div class="toolbar place-right">
                                                        <div class="toolbar-section">
                                                            <a class="toolbar-button bg-grayLighter bd-grayLighter" data-role="hint" data-hint="Ajouter un commentaire ou des fichiers" onclick="showDialog('#dialog2')" ><span class="mif-plus "></span></a>
                                                            <a class="toolbar-button bg-grayLighter bd-grayLighter" data-role="hint" data-hint="Terminer" onclick="window.location.href='reunion#/semaine'"><span class="mif-checkmark "></span></a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>


                                            <div class="row cells3" style="padding-top: 20px;">
                                                <div class="cell">
                                                    <fmt:setLocale value="fr" />
                                                    <fmt:formatDate value="${reunion.dateDebut}" type="date" dateStyle="long" var="jour" />
                                                    <fmt:formatDate value="${reunion.dateDebut}" type="time" timeStyle="short" var="debut" />
                                                    <fmt:formatDate value="${reunion.dateFin}" type="time" timeStyle="short" var="fin" />
                                                    <small><span class="mif-calendar mif-2x place-left padding5"></span> ${jour} <br>${debut}-${fin}</small>
                                                </div>
                                                <div class="cell">
                                                    <small><span class="mif-location mif-2x place-left padding5"></span> ${reunion.salleReunion.nom} <br>${reunion.salleReunion.capacite}</small>
                                                </div>
                                                <div class="cell">
                                                    <small><span class="mif-users mif-2x place-left padding5"></span> 
                                                        Convoqué par : <span class="muanza_fg_violet" style="cursor: pointer;" onclick="window.open('message?id=${reunion.employe.idemploye}')">${reunion.employe.noms}</span>
                                                        <br>${fn:length(reunion.reunionEmployes)} personne(s)
                                                    </small>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="listview set-border padding10 list-type-listing " data-role="listview">
                                            <c:forEach items="${reunion.reunionEmployes}" var="re">
                                                <div class="list">
                                                    <span class="list-icon mif-user muanza_fg_violet"></span>
                                                    <span class="list-title">${re.employe.noms} ${re.employe.prenoms}</span>
                                                </div>
                                            </c:forEach>
                                        </div>
                                        <div class="row">
                                            <div class="cell" style="padding-left: 20px;">
                                                <h3 class="no-wrap muanza_fg_violet">Description</h3>
                                                <p class="" >
                                                    ${empty reunion.description ? "Aucune Description":reunion.description}
                                                </p>
                                            </div>
                                        </div>
                                        <c:if test="${fn:length(reunion.reunionRapports)>0}">
                                            <h3 style="padding-left: 20px;" class="muanza_fg_violet">Documents</h3>
                                            <div style="padding-left: 0px;" class="row">
                                                <div class="cell ribbed-grayLighter" >
                                                    <div class="listview set-border padding10 list-type-icons " data-role="listview">
                                                        <c:forEach items="${reunion.reunionRapports}" var="rr">
                                                            <div class="list" onclick="window.open('Rapports/${rr.chemin}')">
                                                                <span class="list-icon mif-file-empty"></span>

                                                                <span class="list-title">${fn:substring(rr.nom, 0, 8)}...</span>
                                                            </div>
                                                        </c:forEach>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
                                                <div id="menu_droit" class="cell colspan3" style="background-color: orange;">
                    <c:set var="now" value="<%=new java.util.Date()%>" />
<!--                    <div class="bg-grayLighter" style="padding: 0px;">
                        <c:if test="${reunion.dateFin < now}">
                            <div class="row align-center" >
                                <button class="image-button bg-blue fg-white" onclick="archiver(${reunion.idreunion})">
                                    Archiver
                                    <span class="icon mif-file-empty bg-lightGreen fg-white"></span>
                                </button>
                            </div>
                        </c:if>
                    </div>-->
                    <div class="row">
                        <p style="padding-left: 20px; color: white">Réunion prévue dans :</p>
                        <div style="padding-left: 20px; padding-right: 20px; padding-bottom: 20px;">
                            <fmt:formatDate value="${reunion.dateDebut}" pattern="yyyy-MM-dd HH:mm:ss" var="temps" />
                            <div class="timer_keama" data-date="${temps}"></div>
                        </div>
                    </div>
                    <div class="row">

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
                            <input name="rapport" type="file" placeholder="Rechercher" accept="application/pdf, application/msword, image/x-png,image/gif,image/jpeg, application/vnd.ms-excel">
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
