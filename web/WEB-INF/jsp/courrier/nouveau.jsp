<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Entreprise</title>
        <script src="js/jquery-qrcode-0.14.0.js" type="text/javascript"></script>
    </head>
    <body>
        <div class="grid" style="height: 100%; margin-top: -0px">
            <div class="row cells6" style="height: 100%">
                <div class="cell" id="cell-sidebar" >
                    <c:if test="${!empty courrier}">
                        <div class="image-container" style="padding: 0px; display: block">
                            <div class="frame">
                                <img src="img/qrcode.JPG" alt=""/>
                            </div>
                        </div>
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
                            <li class="${c.idcourrier==courrier.idcourrier? 'active':''}" >
                                <a href="#/id/${c.idcourrier}">
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


                        function printImg() {
                            pwin = window.open(document.getElementById("qrcode").src, "_blank");
                            pwin.onload = function () {
                                //window.print();
                            }
                        }

                        //Filtrer les éléments de la liste
                        //Création dune nvelle fonction contains
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

                        //Ajustement de la largeur des boutons du menu droit
                        console.log("Largeur du menu droit : " + $('#menu_droit').width());
                        var x = 0.8 * $('#menu_droit').width() - 40;
                        console.log(x);
                        $(".image-button").each(function (index) {
                            $(this).width(x);
                        });

                        //Réception et affichage de l'image
                        function readURL(input) {

                            if (input.files && input.files[0]) {
                                var reader = new FileReader();

                                reader.onload = function (e) {
                                    $('#imageAffichee').attr('src', e.target.result);
                                }

                                reader.readAsDataURL(input.files[0]);
                            }

                        }

                        $("#imageRecue").change(function () {
                            readURL(this);
                            console.log("Image recue !!!");
                        });
                        //FIN !!! Réception et affichage de l'image


                        function showDialog(id) {
                            var dialog = $(id).data('dialog');
                            dialog.open();
                            //$("#keama_back").css("opacity", "0.2");

                        }


                        //Réception et affichage de l'image
                        function readURL(input) {

                            if (input.files && input.files[0]) {
                                var reader = new FileReader();

                                reader.onload = function (e) {
                                    $('#imageAffichee').attr('src', e.target.result);
                                }

                                reader.readAsDataURL(input.files[0]);
                            }
                        }

                        $("#imageRecue").change(function () {
                            readURL(this);
                            console.log("Image recue !!!");
                        });

                        function modification(id) {
                            $.post(
                                    "FichierCourrierServlet",
                                    {
                                        action: "supprimer",
                                        id: id
                                    }
                            );
                            location.reload(true);
                        }

                        function terminer(id) {
                            $.post(
                                    "tousCourrier",
                                    {
                                        action: "terminer",
                                        id: id
                                    }
                            );
                            location.href = "courrier#/tous";
                        }
                    </script>
                </div>
                <div class="cell colspan4" style="height: 100%">
                    <h3 id="titre_liste">${empty courrier ? "Nouveau c": "C"}ourrier entrant ${empty courrier ? "": ":"} ${empty courrier ? "": courrier.ref}</h3>

                    <hr class="ribbed-green">
                    <div class="row cells6">
                        <br>
                        <div class="tabcontrol" data-role="tabcontrol">
                            <ul class="tabs">
                                <li><a href="#one">Informations </a></li>
                                    <c:if test="${!empty courrier}">
                                    <li><a href="#two">Fichiers</a></li>
                                    </c:if>
                            </ul>
                            <div class="frames">
                                <div class="frame " id="one">
                                    <form method="post" action="nouveauCourrier" class="grid condensed">
                                        <div class="row cells6" style="display: none;">
                                            <div class="cell colspan2" style="padding-right: 10px;">

                                            </div>
                                            <div class="cell colspan2">

                                            </div>
                                            <div class="cell colspan2">

                                            </div>
                                        </div>
                                        <div class="row cells6">
                                            <div class="cell colspan3" style="padding-right: 10px;">
                                                <label>Référence</label>
                                                <div class="input-control text full-size ${!empty courrier ? 'success': ''}">
                                                    <input name="ref" value="${courrier.ref}" placeholder="" disabled>
                                                    <input name="id" value="${courrier.idcourrier}" type="hidden">
                                                </div>
                                            </div>
                                            <div class="cell colspan3">
                                                <label>Structure concernée <span class="fg-red">*</span></label>
                                                <div class="input-control select full-size ${!empty courrier ? 'success': ''}">
                                                    <select name="service">
                                                        <c:forEach items="${structures}" var="s">
                                                            <option ${courrier.structure.idstructure==s.idstructure ? "selected":""} value="${s.idstructure}">${s.nom}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row cells6">
                                            <div class="cell colspan5" style="padding-right: 10px;">
                                                <label>Expediteur <span class="fg-red">*</span></label>
                                                <div class="input-control text full-size ${!empty courrier ? 'success': ''}">
                                                    <input name="expediteur" value="${courrier.expediteur}" placeholder="" >
                                                </div>
                                            </div>
                                            <div class="cell colspan1">
                                                <label>Date d'émission</label>
                                                <c:set var="now" value="<%=new java.util.Date()%>" />
                                                <c:if test="${!empty courrier}">
                                                    <fmt:formatDate pattern="yyyy-MM-dd" value="${courrier.dateEmission}" var="dateEmission"/>
                                                    <div class="input-control text full-size" data-role="datepicker" data-preset="${dateEmission}">
                                                        <input type="text" name="date_emission">
                                                        <button class="button"><span class="mif-calendar"></span></button>
                                                    </div>
                                                </c:if>
                                                <c:if test="${empty courrier}">
                                                    <fmt:formatDate pattern="yyyy-MM-dd" value="${now}" var="dateEmission"/>
                                                    <div class="input-control text full-size ${!empty courrier ? 'success': ''}" data-role="datepicker" data-preset="${dateEmission}">
                                                        <input type="text" name="date_emission">
                                                        <button class="button"><span class="mif-calendar"></span></button>
                                                    </div>
                                                </c:if>

                                            </div>
                                        </div>
                                        <div class="row cells6">
                                            <div class="cell colspan3" style="padding-right: 10px;">
                                                <label>Objet <span class="fg-red">*</span></label>
                                                <div class="input-control text full-size ${!empty courrier ? 'success': ''}">
                                                    <input name="objet" value="${courrier.objet}" >
                                                </div>
                                            </div>
                                            <div class="cell colspan3" style="padding-right: 10px;">
                                                <label>Adresse de l'expéditeur</label>
                                                <div class="input-control text full-size ${!empty courrier ? 'success': ''}">
                                                    <input name="adresse" value="${courrier.adresseExpediteur}">
                                                </div>
                                            </div>

                                        </div>

                                        <div class="row cells12">
                                            <div class="cell colspan3" style="padding-right: 10px;">
                                                <label>Statut</label>
                                                <div class="input-control select full-size ${!empty courrier ? 'success': ''}">
                                                    <select name="statut">
                                                        <option value="Entrant">Entrant</option>
                                                        <option value="A ignorer" ${courrier.statut=="A ignorer" ? "selected":""}>A ignorer</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="cell colspan3" style="padding-right: 10px;">
                                                <label>Nature</label>
                                                <div class="input-control text full-size ${!empty courrier ? 'success': ''}">
                                                    <select name="nature">
                                                        <option value="Facture">Facture</option>
                                                        <option value="Lettre" ${courrier.nature=="Lettre" ? "selected":""}>Lettre</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="cell colspan3" style="padding-right: 10px;">
                                                <label>Importance</label>
                                                <div class="input-control select full-size ${!empty courrier ? 'success': ''}">
                                                    <select name="importance">
                                                        <option value="1">Faible</option>
                                                        <option value="2" ${courrier.importance=="2" ? "selected":""}>Moyenne</option>
                                                        <option value="3" ${courrier.importance=="3" ? "selected":""}>Urgent</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="cell colspan3">
                                                <label>Support</label>
                                                <div class="input-control text full-size ${!empty courrier ? 'success': ''}">
                                                    <select name="support">
                                                        <option value="poste" >Poste</option>
                                                        <option value="Courriel" ${courrier.support=="Courriel" ? "selected":""}>Courriel</option>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row cells6">
                                            <div class="cell colspan6">
                                                <label>Commentaire</label>
                                                <div class="input-control textarea full-size ${!empty courrier ? 'success': ''}" data-role="input" data-text-auto-resize="true">
                                                    <textarea name="note">${courrier.note}</textarea>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row cells6">
                                            <div class="cell colspan6">
                                                <br>
                                                <c:if test="${empty courrier}">
                                                    <input type="submit" value="Enregistrer et ajouter des fichiers" class="button ribbed-green fg-white">
                                                </c:if>
                                                <c:if test="${!empty courrier}">
                                                    <a onclick="terminer(${courrier.idcourrier});" style="cursor : pointer;" class="button ribbed-green fg-white">Terminé</a>
                                                </c:if>
                                            </div>
                                        </div>

                                    </form>
                                </div>
                                <div class="frame" id="two">
                                    <div class="image-container">
                                        <div class="frame">
                                            <a onclick="showDialog('#dialog')" style="cursor: pointer;">Ajouter une nouvelle page</a>
                                        </div>

                                    </div>
                                    <!--                                    <div class="row cells3">
                                    ${courrier.courrierFichiers}
                                </div>-->

                                    <div class="row cells3">
                                        <c:forEach items="${courrier.courrierFichiers}" var="cf">
                                            <div class="cell colspan1">
                                                <div class="image-container">
                                                    <div class="frame">
                                                        <img src="Courriers/${cf.chemin}" alt=""/>
                                                    </div>
                                                    <div class="image-overlay">
                                                        <h2 class="align-center" style="cursor: pointer;">${cf.chemin}</h2>

                                                    </div>
                                                </div>
                                                <div class="align-center">
                                                    <button class="button bg-red fg-white" style="cursor: pointer;" onclick="modification(${cf.idcourrierFichier});">Supprimer</button>
                                                </div>
                                            </div>
                                        </c:forEach>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="menu_droit" class="cell bg-grayLighter" style="padding: 20px;">

                    <div class="row align-center" >
                        <button class="image-button bg-blue fg-white" onclick="window.location.href = 'courrier'">
                            Nouveau
                            <span class="icon mif-file-empty bg-lightGreen fg-white"></span>
                        </button>
                    </div>
                    <c:if test="${!empty courrier}">
                        <div class="row align-center" >
                            <button class="image-button bg-blue fg-white" onclick="keama_impression();
                                    $('.impression').click();">
                                Imprimer
                                <span class="icon mif-printer bg-lighterBlue fg-white"></span>
                            </button>
                        </div>
                        <div class="row align-center" >
                            <button class="image-button bg-blue fg-white" onclick="keama_export();
                                    $('.impressionExcel').click();">
                                Exporter
                                <span class="icon mif-file-excel bg-darkIndigo fg-white"></span>
                            </button>
                        </div>
                        <div class="row align-center">
                            <button class="image-button bg-blue fg-white" onclick="keama_export();
                                    $('.impressionPDF').click();">
                                Exporter
                                <span class="icon mif-file-pdf bg-orange fg-white"></span>
                            </button>
                        </div>
                    </c:if>

                </div>
            </div>
        </div>
        <div data-role="dialog" id="dialog" class="grid padding20 ribbed-grayLighter" data-close-button="true" data-width="400">
            <div class="row cells">
                <div class="cell" style="height: 100%">
                    <form method="post" action="FichierCourrierServlet" enctype="multipart/form-data">
                        <div class="input-control file full-size" data-role="input">
                            <input name="imageRecue" id="imageRecue" type="file" placeholder="Rechercher" accept="image/*">
                            <input name="courrier" type="hidden" value="${courrier.idcourrier}">
                            <button class="button"><span class="mif-folder"></span></button>
                        </div>
                        <img id="imageAffichee" src="img/background.jpg" alt=""/>
                        <div class="input-control text full-size" data-role="input">
                            <input type="submit" class="bg-blue fg-white">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
