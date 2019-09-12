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
        <div id="keama_back" class="grid" style="height: 100%; margin-top: -0px; background-color: #fafafa">
            <div class="row cells12" style="height: 100%">
                <div class="cell colspan3" id="cell-sidebar" style="background-color: orange; height: 100vh; overflow-x: hidden">
                    <div id="block_recherche" class="fg-white" style="padding: 16px; display: block">
                        <!--                        <h2>Rechercher</h2>-->
                        <div class="input-control text full-size warning">
                            <span class="mif-search prepend-icon" style="color: orange"></span>
                            <input id="rechercher" type="text" placeholder="Rechercher">
                        </div>
                    </div>
                    <ul class="sidebar" style="overflow-x:hidden; padding-left: 0px; height: 60vh; overflow-y: auto; background-color: orange;">
                        <c:forEach items="${reunions}" var="c">
                            <li class="li_muanza" style="background-color: orange; border-bottom: 1px solid rgba(250, 150, 0, 1)">
                                <a href="#/id/${c.idreunion}" style="color: #fff">
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

                        function indisponible(id) {

                            $.confirm({
                                title: 'Confirmation',
                                boxWidth: '30%',
                                useBootstrap: false,
                                content: 'Etes-vous sûr de ne pas pouvoir participer à la réunion ?',
                                buttons: {
                                    Oui: {
                                        text: 'Oui, je suis sûr',
                                        btnClass: 'button ribbed-orange fg-white',
                                        keys: ['enter', 'shift'],
                                        action: function () {
                                            $.post(
                                                    "AVenirReunionServlet",
                                                    {
                                                        action: "indisponible",
                                                        id: id
                                                    }
                                            );
                                            var tempsEnMs = Date.now();
                                            window.open("reunion#/avenir/light" + tempsEnMs);
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
                        function disponible(id) {

                            $.confirm({
                                title: 'Confirmation',
                                boxWidth: '30%',
                                useBootstrap: false,
                                content: 'Etes-vous sûr de pouvoir participer à la réunion ?',
                                buttons: {
                                    Oui: {
                                        text: 'Oui, je suis sûr',
                                        btnClass: 'button ribbed-green fg-white',
                                        keys: ['enter', 'shift'],
                                        action: function () {
                                            $.post(
                                                    "AVenirReunionServlet",
                                                    {
                                                        action: "disponible",
                                                        id: id
                                                    }
                                            );
                                            var tempsEnMs = Date.now();
                                            window.open("reunion#/avenir/light" + tempsEnMs);
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
                <div class="cell colspan9" style="height: 100%; padding-right: 20px;">
                    <h3 id="titre_liste">
                        Réunions à venir
                        <span class="icon mif-menu muanza_fg_violet place-right" style="cursor: pointer;" onclick="window.location.href = 'reunion#/avenir'">

                        </span>
                    </h3>
                    <div class="row cells6">
                        <div class="" style="border: 1px solid #310e31; padding: 5px;">
                            <div class="wizard2"
                                 data-role="wizard2"
                                 data-button-labels='{"help": "?", "prev": "<span class=\"mif-arrow-left\"></span>", "next": "<span class=\"mif-arrow-right\"></span>", "finish": "<span class=\"mif-checkmark\"></span>"}'>

                                <c:forEach items="${reunions}" var="reunion">
                                    <c:set var="idre" scope="session" value="0"/>
                                    <c:set var="dispo" scope="session" value="0"/>
                                    <div class="step">
                                        <div class="step-content">
                                            <div class="bg-grayLighter padding20" style="padding-right: 20px; border-bottom: 1px solid #310e31;">
                                                <small>${reunion.ref}</small>
                                                <h1 class="no-wrap muanza_fg_violet">${reunion.titre}</h1>

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
                                                        <small><span class="mif-users mif-2x place-left padding5"></span> ${reunion.employe.noms} <br>${fn:length(reunion.reunionEmployes)} personne(s)</small>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="row">
                                                <div class="cell" >
                                                    <h3 class="no-wrap muanza_fg_violet">Description</h3>
                                                    <p class="" >
                                                        ${empty reunion.description ? "Aucune Description":reunion.description}
                                                    </p>
                                                </div>
                                            </div>
                                            <c:if test="${fn:length(reunion.reunionRapports)>0}">
                                                <h3 style="padding-left: 0px;" class="muanza_fg_violet">Documents</h3>
                                                <div style="padding-left: 0px;" class="row">
                                                    <div class="cell" >
                                                        <div class="listview set-border padding10 list-type-icons " data-role="listview">
                                                            <c:forEach items="${reunion.reunionRapports}" var="rr">
                                                                <div class="list" onclick="window.open('Rapports/${rr.chemin}')">
                                                                    <c:if test="${fn:substringAfter(rr.chemin, '.')=='pdf'}">
                                                                        <span class="list-icon mif-file-pdf"></span>
                                                                    </c:if>
                                                                    <c:if test="${fn:substringAfter(rr.chemin, '.')=='doc'|| fn:substringAfter(rr.chemin, '.')=='docx'}">
                                                                        <span class="list-icon mif-file-word"></span>
                                                                    </c:if>
                                                                    <c:if test="${fn:substringAfter(rr.chemin, '.')=='xls'|| fn:substringAfter(rr.chemin, '.')=='xlsx' || fn:substringAfter(rr.chemin, '.')=='xlt'}">
                                                                        <span class="list-icon mif-file-excel"></span>
                                                                    </c:if>
                                                                    <c:if test="${fn:substringAfter(rr.chemin, '.')=='jpg'|| fn:substringAfter(rr.chemin, '.')=='jpeg' || fn:substringAfter(rr.chemin, '.')=='gif' || fn:substringAfter(rr.chemin, '.')=='png'}">
                                                                        <span class="list-icon mif-image"></span>
                                                                    </c:if>
                                                                    <span class="list-title">${fn:substring(rr.nom, 0, 8)}...</span>
                                                                </div>
                                                            </c:forEach>
                                                        </div>
                                                    </div>
                                                </div>
                                            </c:if>
                                            <div style="padding-top: 5px;">
                                                <div class="listview set-border padding10 list-type-listing " data-role="listview">
                                                    <c:forEach items="${reunion.reunionEmployes}" var="re">
                                                        <div class="list">
                                                            <span class="list-icon mif-user muanza_fg_violet"></span>
                                                            <span class="list-title">${re.employe.noms} ${re.employe.prenoms}</span>
                                                        </div>
                                                    </c:forEach>
                                                </div>
                                            </div>

                                        </div>
                                    </div>
                                </c:forEach>

                            </div>
                        </div>

                    </div>
                </div>

            </div>
        </div>

    </body>
</html>
