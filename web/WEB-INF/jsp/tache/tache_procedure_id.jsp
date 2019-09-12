<%@page import="java.util.Date"%>
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
        <c:set var="tempsARebours" value="<%=new java.util.Date()%>" />
        <c:set var="now" value="<%=new java.util.Date()%>" />
        <div id="keama_back" class="grid" style="height: 100%; margin-top: -0px">
            <div class="row cells12" style="height: 100%">
                <div class="cell colspan2" id="cell-sidebar" style="background-image: url('img/keama_background.png'); height: 100%;">
                    <c:if test="${!empty courrier}">
                        <canvas id="myCanvas" height="100" style="background-color: #fff; padding: 5px">
                        </canvas>
                    </c:if>
                    <div id="block_recherche" class="fg-white" style="padding: 16px; display: block">
                        <!--                        <h2>Rechercher</h2>-->
                        <div class="input-control text full-size info">
                            <span class="mif-search prepend-icon fg-blue"></span>
                            <input id="rechercher" type="text" placeholder="Rechercher">
                        </div>
                    </div>
                    <ul class="sidebar darcula" style="height: 260px; background-image: url('img/keama_background.png');">
                        <c:forEach items="${courriers}" var="c">
                            <li><a href="#/prise/${c.idcourrier}">
                                    <span class="mif-mail-read icon"></span>
                                    <span class="title">${c.expediteur}</span>
                                    <span class="counter">${c.ref}</span>
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
                                    className: 'impression  ribbed-grayLighter bd-white'
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

                        function showDialog(id, courrier) {
                            var dialog = $(id).data('dialog');
                            dialog.open();
                            $("#courriers").val(courrier);

                        }

                        function terminer(id) {

                            $.confirm({
                                title: 'Confirmation',
                                boxWidth: '30%',
                                useBootstrap: false,
                                content: 'Le traitement du courrier est t\'il terminé ? ',
                                buttons: {
                                    Oui: {
                                        text: 'Oui, il est terminé',
                                        btnClass: 'button ribbed-blue fg-white',
                                        keys: ['enter', 'shift'],
                                        action: function () {
                                            $.post(
                                                    "PriseCourrierServlet",
                                                    {
                                                        action: "terminer",
                                                        id: id
                                                    }
                                            );
                                            var tempsEnMs = Date.now();
                                            window.open("courrier#/prise?t=" + tempsEnMs);
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
                        var dataPie = {
                            labels: [
                                "Terminées",
                                "En cours"
                            ],
                            datasets: [
                                {
                                    data: [0, 2],
                                    backgroundColor: [
                                        "#36A2EB",
                                        "#FFCE56"
                                    ],
                                    hoverBackgroundColor: [
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
                                    text: 'Pourcentage de traitement'
                                }
                            }
                        });

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
                <div class="cell colspan7" style="height: 100%">
                    <div class="padding10">


                    </div>
                    <div class="row cells6">
                        <c:forEach items="${courrier.courrierResponsables}" var="cr">
                            <c:if test="${cr.employe.idemploye==utilisateur.employe.idemploye && cr.statut!='Terminé'}">
                                <c:set var="tempsARebours" value="${cr.dateLimite}" />
                            </c:if>
                        </c:forEach>
                        <div class="" style="border: 1px solid ${tempsARebours<now ? "#ce352c":"#1ba1e2"} ; padding: 0px;">
                            <div class="padding10">
                                <div class="step">
                                    <div class="step-content">
                                        <div class="ribbed-grayLighter padding20" style="padding-right: 20px; border-bottom: 1px solid #1ba1e2;">
                                            <div class="row cells12" style="padding-top: 0px;">
                                                <div class="cell colspan6">
                                                    <small>REF : <b>${tache.procedureEffective.ref}</b></small>
                                                    <h1 class="no-wrap fg-blue">${tache.tacheProcedure.libelle}</h1>
                                                    <c:set var="tempsARebours" value="${tache.dateFin}" />
                                                    <fmt:formatDate value="${tempsARebours}" type="date" dateStyle="medium" var="temps" />
                                                    <small>Avant le : <span class="${tempsARebours<now ? 'fg-red':'fg-blue'}">${temps}</span></small>

                                                </div>
                                                <div class="cell colspan6">
                                                    <div class="toolbar place-right">
                                                        <div class="toolbar-section">
                                                            <a class="toolbar-button bg-grayLighter bd-grayLighter" data-role="hint" data-hint="Ajouter un commentaire ou des fichiers" onclick="showDialog('#dialog2')" ><span class="mif-plus "></span></a>
                                                            <a class="toolbar-button bg-grayLighter bd-grayLighter" data-role="hint" data-hint="Terminer" onclick="terminer('${courrier.idcourrier}')"><span class="mif-checkmark "></span></a>
                                                            <a class="toolbar-button bg-grayLighter bd-grayLighter" data-role="hint" data-hint="Archiver"><span class="mif-cabinet "></span></a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
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
                                            </c:if>
                                        </div>
                                        <div class="row">
                                            <div class="listview set-border padding20 list-type-listing " data-role="listview">
                                                <h4 class="no-wrap"><span class="fg-blue">Suivi de la procédure</span></h4>
                                                <table id="example_table" class="dataTable border bordered" style="margin-bottom: 10px;">
                                                    <thead>
                                                        <tr>
                                                            <th>Nom</th>
                                                            <th>Exécutant</th>
                                                            <th>Date début</th>
                                                            <th>Date fin</th>
                                                            <th>Statut</th>
                                                        </tr>
                                                    </thead>

                                                    <tbody>

                                                        <c:forEach items="${procedure.tacheProcedureEffectues}" var="tpe">
                                                            <tr>
                                                                <td>
                                                                    <span style="display: none">${tpe.tacheProcedure.ordre}</span>
                                                                    ${tpe.tacheProcedure.libelle}
                                                                </td>
                                                                <td>${tpe.tacheProcedure.employe.noms} ${tpe.tacheProcedure.employe.prenoms}</td>
                                                                <fmt:setLocale value="fr" />
                                                                <fmt:formatDate value="${tpe.dateDebut}" type="date" dateStyle="long" var="dateDebut" />
                                                                <fmt:formatDate value="${tpe.dateFin}" type="date" dateStyle="long" var="dateFin" />
                                                                <td>${dateDebut}</td>
                                                                <td>${dateFin}</td>
                                                                <td>${tpe.statut}</td>
                                                            </tr>
                                                        </c:forEach>
                                                    </tbody>
                                                    <tfoot >
                                                        <tr>
                                                            <th>Nom</th>
                                                            <th>Exécutant</th>
                                                            <th>Date début</th>
                                                            <th>Date fin</th>
                                                            <th>Statut</th>
                                                        </tr>
                                                    </tfoot>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>


                    </div>
                </div>
                <div id="menu_droit" class="cell colspan3 ribbed-grayLighter">
                    <c:set var="now" value="<%=new java.util.Date()%>" />
                    <div class="ribbed-grayLighter" style="padding: 20px;">
                        <div class="row" style="padding-bottom : 30px;">
                            <c:if test="${tempsARebours>now}">
                                <p><b>TEMPS RESTANT</b></p>
                            </c:if>
                            <c:if test="${tempsARebours<now}">
                                <fmt:formatDate value="${tempsARebours}" type="date" dateStyle="medium" var="temps" />
                                <p><b class="fg-red">TEMPS ECOULE</b><br><small>Depuis le ${temps}</small></p>
                                </c:if>
                                <fmt:formatDate value="${tempsARebours}" pattern="yyyy-MM-dd HH:mm:ss" var="temps" />
                            <div class="timer_keama" data-date="${temps}"></div>

                        </div>

                        <div class="row keama_chart" style="overflow: hidden ; border: 1px #eeeeee solid;">
                            <canvas id="myChartPie" height="240"></canvas>
                        </div>
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
        <div data-role="dialog" id="dialog2" class="grid padding20 ribbed-grayLighter" data-close-button="true" data-width="600">
            <div class="row cells">
                <div class="cell" style="height: 100%">
                    <h1 class="fg-green">Fin de traitement</h1>
                    <p style="padding-bottom: 20px;">
                        Souhaitez-vous ajouter un commentaire ou des documents avant la fin du traitement ?
                    </p>
                    <form method="post" action="RapportReunionServlet" enctype="multipart/form-data">
                        <label>Sélectionnez les documents </label>
                        <div class="input-control file full-size" data-role="input">
                            <input name="rapport" type="file" placeholder="" accept="application/pdf, application/msword, image/x-png,image/gif,image/jpeg, application/vnd.ms-excel">
                            <input name="reunion" type="hidden" value="${reunion.idreunion}">
                            <button class="button"><span class="mif-folder"></span></button>
                        </div>
                        <label>Commentaire</label>
                        <div class="input-control textarea full-size" data-role="input" data-text-auto-resize="true">
                            <textarea name="note"></textarea>
                            <input type="hidden" name="${courrier.idcourrier}">
                        </div>
                        <div class="input-control text full-size" data-role="input">
                            <input type="submit" value="Terminer" class="ribbed-green fg-white">
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div data-role="dialog" id="dialog" class="grid padding20 ribbed-grayLighter" data-close-button="true" data-width="600" >
            <h1 class="fg-green">Transmission</h1>
            <p style="padding-bottom: 20px;">
                Choisissez le destinataire du courrier
            </p>
            <form method="post" action="PriseCourrierServlet">

                <div class="row cells6">
                    <div class="cell colspan6">
                        <label>Transmettre à : </label>
                        <div class="input-control select full-size">
                            <select name="employe">
                                <c:forEach items="${employes}" var="e">
                                    <option value="${e.idemploye}">${e.noms} ${e.prenoms} - ${e.poste.libelle}</option>
                                </c:forEach>
                            </select>
                        </div>

                    </div>
                </div>
                <div class="row cells6">
                    <div class="cell colspan6">
                        <label>Action à mener :</label>
                        <div class="input-control text full-size">
                            <input type="text" name="approbation">
                            <input type="hidden" name="action" value="transmettre">
                        </div>
                    </div>
                </div>
                <div class="row cells6">
                    <div class="cell colspan3">
                        <label>Date limite</label>
                        <div class="input-control input full-size">
                            <fmt:setLocale value="fr" />
                            <c:set var="now" value="<%=new java.util.Date()%>" />
                            <c:set var="tomorrow" value="<%=new Date(new Date().getTime() + 60 * 60 * 24 * 1000)%>"/>
                            <fmt:formatDate value="${tomorrow}" type="date" pattern="yyyy.MM.dd"  var="now2" />
                            <div class="input-control text full-size" data-role="datepicker" data-preset="${now2}">
                                <input type="text" name="date_limite">
                                <button class="button"><span class="mif-calendar"></span></button>
                            </div>
                        </div>
                    </div>
                    <div class="cell colspan3">

                    </div>
                </div>
                <div class="row cells6">
                    <div class="cell colspan6">
                        <label>Commentaire</label>
                        <div class="input-control textarea full-size" data-role="input" data-text-auto-resize="true">
                            <textarea name="note"></textarea>
                            <input type="hidden" name="id" value="${courrier.idcourrier}" >
                        </div>
                    </div>
                </div>
                <div class="row cells6">
                    <div class="cell colspan6">
                        <br>
                        <input type="submit" value="Transmettre" class="button ribbed-green fg-white">
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>
