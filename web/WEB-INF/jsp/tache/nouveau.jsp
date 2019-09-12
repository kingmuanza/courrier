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
            <div class="row cells7" style="height: 100%">
                <div class="cell colspan2" id="cell-sidebar" style="background-image: url('img/keama_background.png'); height: 100%;">
                    <c:if test="${!empty tacheZ}">
                        <div class="image-container" style="padding: 0px; display: block">
                            <div class="frame">
                                <img src="img/qrcode.JPG" alt=""/>
                            </div>
                        </div>
                    </c:if>
                    <div id="block_recherche" class="fg-white" style="padding: 16px; display: block">
                        <!--                        <h2>Rechercher</h2>-->
                        <div class="input-control text full-size info">
                            <span class="mif-search prepend-icon fg-blue"></span>
                            <input id="rechercher" type="text" placeholder="Rechercher">
                        </div>
                    </div>
                    <ul class="sidebar darcula" style="height: 260px; background-image: url('img/keama_background.png');">
                        <c:forEach items="${taches}" var="c">
                            <li class="${c.idtache==tache.idtache? 'active':''}">
                                <a href="#/id/${c.idtache}">
                                    <span class="mif-mail-read icon"></span>
                                    <span class="title">${c.libelle}</span>
                                    <span class="counter">${c.employeByIdordonnateur.noms}</span>
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
                            location.href = "tacheZ#/tous";
                        }
                    </script>
                </div>
                <div class="cell colspan4" style="height: 100%">
                    <h1 id="titre_liste">${empty tache ? "Nouvelle tâche": tache.libelle}</h1>

                    <hr class="ribbed-green">
                    <div class="row cells6">
                        <br>
                        <div class="tabcontrol" data-role="tabcontrol">
                            <ul class="tabs">
                                <li><a href="#one">Informations </a></li>
                                    <c:if test="${!empty tacheZ}">
                                    <li><a href="#two">Fichiers</a></li>
                                    </c:if>
                            </ul>
                            <div class="frames">
                                <div class="frame " id="one">
                                    <form method="post" action="NouvelleTacheServlet" class="grid condensed">
                                        <div class="row cells6">
                                            <div class="cell colspan6">
                                                <label class="fg-">Libellé</label>
                                                <div class="input-control text full-size ${!empty tache ? 'success': ''}">
                                                    <input name="libelle" value="${tache.libelle}" placeholder="" >
                                                    <input name="id" value="${tache.idtache}" type="hidden">
                                                    <input name="employe" value="${utilisateur.employe.idemploye}" type="hidden">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row cells">
                                            <div class="cell" style="padding-right: 8px;">
                                                <label class="fg-">Exécutants</label>
                                                <div class="input-control full-size" data-role="select" data-multiple="true" data-placeholder="Choisissez les personnes à convoquer">
                                                    <select class="js-example-basic-multiple full-size" name="employes" id="employes" multiple>
                                                        <c:forEach items="${employes}" var="e">
                                                            <option value="${e.idemploye}">${e.noms} ${e.prenoms}</option>
                                                        </c:forEach>
                                                    </select>
                                                    <script>
                                                        <c:forEach items="${tache.tacheEmployes}" var="te">
                                                            $('#employes option[value=${te.employe.idemploye}]').prop('selected', true);
                                                        </c:forEach>
                                                        
                                                    </script>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row cells3">
                                            <div class="cell colspan1" style="padding-right: 10px;">
                                                <label class="fg-">Date début</label>
                                                <c:set var="now" value="<%=new java.util.Date()%>" />
                                                <c:if test="${!empty tache}">
                                                    <fmt:formatDate pattern="yyyy-MM-dd" value="${tache.dateDebut}" var="dateDebut"/>
                                                    <div class="input-control text full-size" data-role="datepicker" data-preset="${dateDebut}">
                                                        <input type="text" name="date_debut">
                                                        <button class="button"><span class="mif-calendar"></span></button>
                                                    </div>
                                                </c:if>
                                                <c:if test="${empty tache}">
                                                    <fmt:formatDate pattern="yyyy-MM-dd" value="${now}" var="dateEmission"/>
                                                    <div class="input-control text full-size ${!empty tache ? 'success': ''}" data-role="datepicker" data-preset="${dateEmission}">
                                                        <input type="text" name="date_debut">
                                                        <button class="button"><span class="mif-calendar"></span></button>
                                                    </div>
                                                </c:if>
                                            </div>
                                            <div class="cell colspan1" style="padding-right: 10px;">
                                                <label class="fg-">Date fin</label>
                                                <c:set var="now" value="<%=new java.util.Date()%>" />
                                                <c:if test="${!empty tache}">
                                                    <fmt:formatDate pattern="yyyy-MM-dd" value="${tache.dateFin}" var="dateFin"/>
                                                    <div class="input-control text full-size" data-role="datepicker" data-preset="${dateFin}">
                                                        <input type="text" name="date_fin">
                                                        <button class="button"><span class="mif-calendar"></span></button>
                                                    </div>
                                                </c:if>
                                                <c:if test="${empty tache}">
                                                    <fmt:formatDate pattern="yyyy-MM-dd" value="${now}" var="dateEmission"/>
                                                    <div class="input-control text full-size ${!empty tache ? 'success': ''}" data-role="datepicker" data-preset="${dateEmission}">
                                                        <input type="text" name="date_fin">
                                                        <button class="button"><span class="mif-calendar"></span></button>
                                                    </div>
                                                </c:if>
                                            </div>
                                            <div class="cell colspan1">
                                                <label class="fg-">Attribué le :</label>
                                                <c:set var="now" value="<%=new java.util.Date()%>" />
                                                <c:if test="${!empty tache}">
                                                    <fmt:formatDate pattern="yyyy-MM-dd" value="${tache.dateEmission}" var="dateEmission"/>
                                                    <div class="input-control text full-size" data-role="datepicker" data-preset="${dateEmission}">
                                                        <input type="text" name="date_emission" readonly>
                                                        <button class="button"><span class="mif-calendar"></span></button>
                                                    </div>
                                                </c:if>
                                                <c:if test="${empty tache}">
                                                    <fmt:formatDate pattern="yyyy-MM-dd" value="${now}" var="dateEmission"/>
                                                    <div class="input-control text full-size ${!empty tache ? 'success': ''}" data-role="datepicker" data-preset="${dateEmission}">
                                                        <input type="text" name="date_emission" readonly>
                                                        <button class="button"><span class="mif-calendar"></span></button>
                                                    </div>
                                                </c:if>
                                            </div>
                                        </div>

                                        <div class="row cells6">
                                            <div class="cell colspan6">
                                                <label class="fg-">Commentaire</label>
                                                <div class="input-control textarea full-size ${!empty tacheZ ? 'success': ''}" data-role="input" data-text-auto-resize="true">
                                                    <textarea name="description">${tacheZ.note}</textarea>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row cells6">
                                            <div class="cell colspan6">
                                                <br>
                                                <c:if test="${empty tacheZ}">
                                                    <input type="submit" value="Enregistrer" class="button ribbed-green fg-white">
                                                </c:if>
                                                <c:if test="${!empty tacheZ}">
                                                    <a onclick="terminer(${tacheZ.idtacheZ});" style="cursor : pointer;" class="button ribbed-green fg-white">Terminé</a>
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
                                    ${tacheZ.tacheZFichiers}
                                </div>-->

                                    <div class="row cells3">
                                        <c:forEach items="${tacheZ.tacheZFichiers}" var="cf">
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
                                                    <button class="button bg-red fg-white" style="cursor: pointer;" onclick="modification(${cf.idtacheZFichier});">Supprimer</button>
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
                        <button class="image-button bg-blue fg-white" onclick="window.location.href = 'tacheZ'">
                            Nouveau
                            <span class="icon mif-file-empty bg-lightGreen fg-white"></span>
                        </button>
                    </div>
                    <c:if test="${!empty tacheZ}">
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
                            <input name="tacheZ" type="hidden" value="${tacheZ.idtacheZ}">
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
