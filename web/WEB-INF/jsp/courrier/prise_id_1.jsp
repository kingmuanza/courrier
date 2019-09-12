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
        <div id="keama_back" class="grid" style="height: 100%; margin-top: -0px;">
            <div class="row cells12" style="height: 100%">
                <div class="cell colspan3" id="cell-sidebar2" style="background-color: orange">
                    <c:if test="${!empty courrier}">
                        <canvas id="myCanvas" height="100" style="background-color: #fff; padding: 5px">
                        </canvas>
                    </c:if>
                    <div id="block_recherche" class="fg-white" style="padding: 16px; display: block">
                        <!--                        <h2>Rechercher</h2>-->
                        <div class="input-control text full-size warning">
                            <span class="mif-search prepend-icon" style="color:orange"></span>
                            <input id="rechercher" type="text" placeholder="Rechercher">
                        </div>
                    </div>
                    <ul class="sidebar" style="overflow-x:hidden; padding-left: 0px; height: 60vh; overflow-y: auto; background-color: orange;">
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
                        var options = {
                            // render method: 'canvas', 'image' or 'div'
                            render: 'canvas',
                            // version range somewhere in 1 .. 40
                            minVersion: 1,
                            maxVersion: 40,
                            // error correction level: 'L', 'M', 'Q' or 'H'
                            ecLevel: 'L',
                            // offset in pixel if drawn onto existing canvas
                            left: 10,
                            top: 10,
                            // size in pixel
                            size: $('#myCanvas').parent().width() - 30,
                            // code color or image element
                            fill: '#000',
                            // background color or image element, null for transparent background
                            background: '#fff',
                            // content
                            text: 'ref : <c:out value="${courrier.ref}"/>, expediteur : <c:out value="${courrier.expediteur}"/>, objet : <c:out value="${courrier.objet}"/>',
                            // corner radius relative to module width: 0.0 .. 0.5
                            radius: 0,
                            // quiet zone in modules
                            quiet: 0,
                            // modes
                            // 0: normal
                            // 1: label strip
                            // 2: label box
                            // 3: image strip
                            // 4: image box
                            mode: 0,
                            mSize: 0.1,
                            mPosX: 0.5,
                            mPosY: 0.5,
                            label: 'no label',
                            fontname: 'sans',
                            fontcolor: '#000',
                            image: null
                        };
                        var c = document.getElementById("myCanvas");
                        var ctx = c.getContext("2d");
                        ctx.canvas.width = $('#myCanvas').parent().width();
                        ctx.canvas.height = $('#myCanvas').parent().width();
                        $("#myCanvas").qrcode(options);
                        //Chargement de la page
                        $("#spinner").hide();
                        var viewer = new Viewer(document.getElementById('images'), {});


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
                                        btnClass: 'button muanza_bg_violet fg-white',
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

                        function terminertache(id) {

                            $.confirm({
                                title: 'Confirmation',
                                boxWidth: '30%',
                                useBootstrap: false,
                                content: 'Avez-vous terminé votre tâche  ? ',
                                buttons: {
                                    Oui: {
                                        text: 'Oui, elle est terminée',
                                        btnClass: 'button muanza_bg_violet fg-white',
                                        keys: ['enter', 'shift'],
                                        action: function () {
                                            $.post(
                                                    "PriseCourrierServlet",
                                                    {
                                                        action: "terminerTache",
                                                        id: id
                                                    }
                                            );
                                            var tempsEnMs = Date.now();
                                            window.location.href="courrier#/suivi/${courrier.idcourrier}?t=" + tempsEnMs;
                                            
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
                                "Restant"
                            ],
                            datasets: [
                                {
                                    data: [${a}, ${c-a}],
                                    backgroundColor: [
                                        "#60a917",
                                        "#FFCE56"
                                    ],
                                    hoverBackgroundColor: [
                                        "#60a917",
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
                                },
                                rotation: 0,
                                legend: {
                                    onClick: function (event, legendItem) {
                                        alert(legendItem);
                                    }
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
                <div class="cell colspan6" style="height: 100%">
                    <div class="padding10">


                    </div>
                    <div class="row cells6">
                        <c:forEach items="${courrier.courrierResponsables}" var="cr">
                            <c:if test="${cr.employe.idemploye==utilisateur.employe.idemploye && cr.statut!='Terminé'}">
                                <c:set var="tempsARebours" value="${cr.dateLimite}" />
                            </c:if>
                        </c:forEach>
                        <div class="" style="border: 1px solid ${tempsARebours<now ? "#ce352c":"#310e31"} ; padding: 0px;">
                            <div class="padding10">
                                <div class="step">
                                    <div class="step-content">
                                        <div class="ribbed-grayLighter padding20" style="padding-right: 20px; border-bottom: 1px solid #310e31;">
                                            <div class="row cells12" style="padding-top: 0px;">
                                                <div class="cell colspan9">
                                                    <small>${courrier.ref}</small>
                                                    <div>
                                                        <b>${courrier.objet}</b>
                                                    </div>
                                                    <h1 class="no-wrap muanza_fg_violet">${courrier.expediteur}</h1>
                                                    Action à mener : 
                                                    <c:forEach items="${courrier.courrierResponsables}" var="cr">
                                                        <c:if test="${cr.employe.idemploye==utilisateur.employe.idemploye && cr.statut!='Terminé'}">
                                                            <b>${empty cr.libelle ? cr.typeApprobation : cr.libelle}</b>
                                                            <c:set var="tempsARebours" value="${cr.dateLimite}" />
                                                            <c:set var="idcourrierResponsable" value="${cr.idcourrierResponsable}" />
                                                        </c:if>
                                                    </c:forEach>

                                                    <fmt:formatDate value="${tempsARebours}" type="date" dateStyle="medium" var="temps" />
                                                    </br><small>Avant le : <span class="${tempsARebours<now ? 'fg-red':'muanza_fg_violet'}">${temps}</span></small>

                                                </div>
                                                <div class="cell colspan3">
                                                    <div class="toolbar place-right">
                                                        <div class="toolbar-section">
                                                            <c:if test="${empty courrier.courrierProcedureEnCourses}">
                                                                <a class="toolbar-button bg-grayLighter bd-grayLighter" data-role="hint" data-hint="Ajouter un commentaire ou des fichiers" onclick="showDialog('#dialog2')" ><span class="mif-plus "></span></a>
                                                                <a class="toolbar-button bg-grayLighter bd-grayLighter" data-role="hint" data-hint="Transmettre" onclick="showDialog('#dialog', '${courrier.idcourrier}')"><span class="mif-arrow-right "></span></a>
                                                                <a class="toolbar-button bg-grayLighter bd-grayLighter" data-role="hint" data-hint="Terminer" onclick="terminer('${idcourrierResponsable}')"><span class="mif-checkmark "></span></a>
                                                                </c:if>
                                                                <c:if test="${!empty courrier.courrierProcedureEnCourses}">
                                                                <a class="toolbar-button bg-grayLighter bd-grayLighter" data-role="hint" data-hint="Ajouter un commentaire ou des fichiers" onclick="showDialog('#dialog2')" ><span class="mif-plus "></span></a>
                                                                <a class="toolbar-button bg-grayLighter bd-grayLighter" data-role="hint" data-hint="Terminer" onclick="terminertache('${idcourrierResponsable}')"><span class="mif-checkmark "></span></a>
                                                                </c:if>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row cells3" style="padding-top: 20px;">
                                                <div class="cell">
                                                    <fmt:setLocale value="fr" />
                                                    <fmt:formatDate value="${courrier.dateEmission}" type="date" pattern="yyyy/MM/dd" var="jour" />
                                                    <fmt:formatDate value="${courrier.dateEnr}" type="date" pattern="yyyy/MM/dd" var="debut" />
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

                                        <div class="row">
                                            <c:if test="${fn:length(courrier.courrierFichiers)>0}">
                                                <div class="docs-galley">
                                                    <ul id="images" class="docs-pictures clearfix">
                                                        <c:forEach items="${courrier.courrierFichiers}" var="cf">
                                                            <li><img data-original="img/qrcode.JPG" src="Courriers/${cf.chemin}" alt="Picture"></li>
                                                            </c:forEach>
                                                    </ul>
                                                </div>
                                                <p class="">
                                                    <a class="ribbed-grayLighter button bd-white" href="courrier#/ordonner/${courrier.idcourrier}">Réorganiser les fichiers</a>
                                                </p>
                                            </c:if>
                                        </div>


                                        <!--###################################################################################################-->                                       
                                        <c:forEach items="${commentaires}" var="comment">
                                            <fmt:setLocale value="fr" />
                                            <fmt:formatDate value="${comment.dateEnregistrement}" type="date" dateStyle="long" var="dateEnregistrement" />
                                            <p style="padding-left: 30px;"><small>${dateEnregistrement}</small></p>
                                            <div class="row cells6">
                                                <div class="cell align-center" style="border-right: 1px solid #000;">
                                                    <span class="mif-bubbles muanza_fg_violet mif-4x padding5"></span>
                                                </div>
                                                <div class="cell colspan5 padding5">
                                                    <span class="muanza_fg_violet">${comment.courrierResponsable.employe.noms}</span> : ${empty comment.courrierResponsable.libelle ? comment.courrierResponsable.typeApprobation : comment.courrierResponsable.libelle }
                                                    <h3>${comment.commentaire}</h3>
                                                    <c:if test="${!empty comment.courrierResponsableCommentaireFichiers}">
                                                        <a href="CommentaireCourrier/${comment.courrierResponsableCommentaireFichiers.iterator().next().chemin}" class="tile bg-blue">
                                                            <div class="tile-content iconic">
                                                                <span class="icon mif-file-empty fg-white"></span>
                                                            </div>
                                                        </a>
                                                    </c:if>
                                                </div>
                                            </div>
                                        </c:forEach>
                                        <!--###################################################################################################-->   


                                    </div>
                                </div>
                            </div>
                        </div>


                    </div>
                    <div class="row">
                        <div class="listview set-border list-type-listing " data-role="listview">
                            <div class="padding20">
                                <small class="place-right">${!empty courrier.courrierProcedureEnCourses ? courrier.courrierProcedureEnCourses.iterator().next().courrierProcedure.nom :""}</small>
                                <h4 class="no-wrap"><span class="muanza_fg_violet">Suivi du courrier</span></h4>
                                <h4 class="no-wrap"><span class="fg-green">${!empty courrier.courrierProcedureEnCourses ? courrier.courrierProcedureEnCourses.iterator().next().ref : ""}</span></h4>
                                <br>
                                <table id="example_table" class="dataTable border bordered" style="margin-bottom: 10px; margin-top: 10px;">
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
                                                <td>${empty c.libelle ? c.typeApprobation : c.libelle }</td>

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
                <div id="menu_droit" class="cell colspan3 " style="min-height: 100vh; background-color: orange">
                    <c:set var="now" value="<%=new java.util.Date()%>" />
                    <div class="" style="padding: 20px; background-color: orange">
                        <div class="row" style="padding-bottom : 30px;">
                            <c:if test="${tempsARebours>now}">
                                <p><b class="muanza_fg_violet">TEMPS RESTANT</b></p>
                            </c:if>
                            <c:if test="${tempsARebours<now}">
                                <fmt:formatDate value="${tempsARebours}" type="date" dateStyle="medium" var="temps" />
                                <p><b class="fg-red">TEMPS ECOULE</b><br><small>Depuis le ${temps}</small></p>
                                </c:if>
                                <fmt:formatDate value="${tempsARebours}" pattern="yyyy-MM-dd HH:mm:ss" var="temps" />
                            <div class="timer_keama fg-white" data-date="${temps}"></div>

                        </div>
                        <c:if test="${!empty courrier.getCourrierProcedureEnCourses()}">
                            <div class="row" style="padding-bottom : 10px;">
                                <div class="cell">
                                    <b class="muanza_fg_violet">${courrier.getCourrierProcedureEnCourses().iterator().next().ref}</b><br>
                                </div>
                            </div>
                            <div class="row keama_chart" style="overflow: hidden ; border: 0px #fff solid;">
                                <canvas id="myChartPie" height="240"></canvas>
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
        <div data-role="dialog" id="dialog2" class="grid padding20 ribbed-grayLighter" data-close-button="true" data-width="700" data-overlay="true">
            <div class="row cells6">
                <div class="cell" style="height: 100%">
                    <h1 class="muanza_fg_violet"><span class="mif-bubbles mif-4x place-left padding5"></span></h1>
                </div>    
                <div class="cell colspan5" style="height: 100%">
                    <h1 class="muanza_fg_violet">Commentaire</h1>
                    <p style="padding-bottom: 20px;">
                        Souhaitez-vous ajouter un commentaire ou des documents avant la fin du traitement ?
                    </p>
                    <form method="post" action="CommentaireCourrierServlet" enctype="multipart/form-data">
                        <label>Commentaire</label>
                        <div class="input-control textarea full-size" data-role="input" data-text-auto-resize="true">
                            <textarea name="commentaire"></textarea>
                            <input type="hidden" name="courrierResponsable" value="${idcourrierResponsable}">
                        </div>
                        <label>Sélectionnez les documents </label>
                        <div class="input-control file full-size" data-role="input">
                            <input name="rapport" type="file" placeholder="" accept="application/pdf, application/msword, image/x-png,image/gif,image/jpeg, application/vnd.ms-excel">
                            <button class="button"><span class="mif-folder"></span></button>
                        </div>
                        <div class="input-control text full-size" data-role="input">
                            <input type="submit" value="Terminer" class="muanza_bg_violet fg-white">
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
