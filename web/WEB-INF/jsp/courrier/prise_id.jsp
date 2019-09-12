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
            <div class="row cells12" style="height: 100%;" >
                <div class="cell colspan3" id="cell-sidebar" >
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
                </div>
                
                <div class="cell colspan6" style="height: 100%; background-color: #ffffff;">
                    
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
                                    <div class="grid">
                                        <!--                                        <div class="row cells6" style="padding-bottom: 0px;">
                                                                                    <legend>Informations</legend>
                                                                                    <hr>
                                        
                                                                                </div>-->
                                        <div class="row cells6">
                                            <div class="cell colspan4" style="padding-right: 0px;">
                                                <label>Référence</label>
                                                <div class="input-control text full-size ${!empty courrier ? 'success': ''}">
                                                    <input name="ref" value="${courrier.ref}" placeholder="" disabled>
                                                    <input name="id" value="${courrier.idcourrier}" type="hidden">
                                                </div>
                                            </div>
                                            <div class="cell colspan2">
                                                <label>Date d'émission</label>
                                                <c:set var="now" value="<%=new java.util.Date()%>" />
                                                <c:if test="${!empty courrier.dateEmission}">
                                                    <fmt:formatDate pattern="yyyy-MM-dd" value="${courrier.dateEmission}" var="dateEmission"/>
                                                    <div class="input-control text full-size" data-role="datepicker" data-preset="${dateEmission}">
                                                        <input type="text" name="date_emission">
                                                        <button class="button"><span class="mif-calendar"></span></button>
                                                    </div>
                                                </c:if>
                                                <c:if test="${empty courrier.dateEmission}">
                                                    <fmt:formatDate pattern="yyyy-MM-dd" value="${now}" var="dateEmission"/>
                                                    <div class="input-control text full-size ${!empty courrier ? 'success': ''}" data-role="datepicker" data-preset="${dateEmission}">
                                                        <input type="text" name="date_emission">
                                                        <button class="button"><span class="mif-calendar"></span></button>
                                                    </div>
                                                </c:if>
                                            </div>
                                        </div>
                                        <div class="row cells6">
                                            <div class="cell colspan6">
                                                <label>Correspondant <span class="fg-red">*</span></label>
                                                <div class="input-control select full-size" data-role="select" data-placeholder="Choisissez le correspondant" data-allow-clear="true">
                                                    <select id="correspondant" class="full-size" name="correspondant" required>
                                                        <option></option>
                                                        <c:forEach items="${correspondants}" var="c" varStatus="loop">
                                                            <option value="${c.idcorrespondant}" ${c.idcorrespondant==courrier.correspondant.idcorrespondant? "selected":""} >${c.nomComplet} (${c.poste}) : ${c.entreprisePartenaire.nom}</option>
                                                        </c:forEach>
                                                    </select>
                                                    <div class="button-group">
                                                        <button onclick="showDialog('#dialogCorrespondant')" class="button"><span class="icon mif-user-plus"></span></button>
                                                        <button onclick="showDialog('#dialogEntreprise')" class="button"><span class="icon mif-folder-plus"></span></button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row cells6">
                                            <div class="cell colspan6" style="padding-right: 0px;">
                                                <label>Objet <span class="fg-red">*</span></label>
                                                <div class="input-control text full-size">
                                                    <input name="objet" value="${courrier.objet}" required>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="grid condensed">
                                        <div class="row cells9">

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

                                                <c:if test="${!empty courrier}">
                                                    <a onclick="terminer(${courrier.idcourrier});" style="cursor : pointer;" class="button ribbed-green fg-white">Terminé</a>
                                                </c:if>
                                                <c:if test="${!empty courrier}">
                                                    <a onclick="supprimer(${courrier.idcourrier});" style="cursor : pointer;" class="button ribbed-orange fg-white">Supprimer</a>
                                                </c:if>
                                            </div>
                                        </div>
                                    </div>
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
                <div class="cell colspan3" id="cell-sidebar" style="height: 800px ; overflow-y: scroll">

                    <c:forEach items="${courrier.courrierFichiers}" var="cf" varStatus="loop">
                        <canvas id="myCanvas${loop.index}" height="100" style="border:0px solid #000000; padding-bottom: 10px;" >
                        </canvas>
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
                                size: 100,
                                // code color or image element
                                fill: '#000',
                                // background color or image element, null for transparent background
                                background: null,
                                // content
                                text: '${cf.courrier.ref}',
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

                            var c${loop.index} = document.getElementById("myCanvas${loop.index}");
                            var ctx${loop.index} = c${loop.index}.getContext("2d");
                            ctx${loop.index}.canvas.width = window.innerWidth / 3 - 75;
                            var largeur${loop.index} = ctx${loop.index}.canvas.width;
                            ctx${loop.index}.canvas.height = (window.innerWidth / 3 - 75) * 1.5;


                            $("#spinner").show();
                            var myImage${loop.index} = new Image(largeur${loop.index}, largeur${loop.index} * 1.5);
                            myImage${loop.index}.src = 'Courriers/${cf.chemin}';
                            myImage${loop.index}.onload = function () {
//                                alert("Loaded");
                                ctx${loop.index}.drawImage(myImage${loop.index}, 0, 0, largeur${loop.index}, largeur${loop.index} * 1.5);
                                $("#myCanvas${loop.index}").qrcode(options);
                                $("#spinner").hide();
                            };



//                            var qrcode${loop.index} = new Image(100, 100);
//                            qrcode${loop.index}.src = 'img/qrcode.JPG';
//                            ctx${loop.index}.drawImage(qrcode${loop.index}, 10, 10, 100, 100);
                        </script>
                    </c:forEach>
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


                        var restant = total - taille_entete - taille_menu;
                        console.log(restant);
                        $("cell-sidebar").height(restant);


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
                        function closeDialog(id) {
                            var dialog = $(id).data('dialog');
                            dialog.close();
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
                            $.confirm({
                                title: 'Confirmation',
                                boxWidth: '30%',
                                useBootstrap: false,
                                content: 'Etes-vous sûr de vouloir supprimer le fichier ?',
                                type: 'red',
                                buttons: {
                                    Oui: {
                                        text: 'Oui, je le veux',
                                        btnClass: 'button ribbed-red fg-white',
                                        keys: ['enter', 'shift'],
                                        action: function () {
                                            $.post(
                                                    "FichierCourrierServlet",
                                                    {
                                                        action: "supprimer",
                                                        id: id
                                                    }
                                            );
                                            location.reload(true);
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
                        var correspondantAEnvoyer = 1;
                        function terminer(id) {

                            var i = $("select[name=correspondant]").val();
                            var j = $("input[name=objet]").val();
                            if (i && j) {
                                $.post(
                                        "NouveauCourrierServlet2",
                                        {
                                            action: "terminer",
                                            id: $("input[name=id]").val(),
                                            statut: $("input[name=statut]").val(),
                                            correspondant: $("select[name=correspondant]").val(),
                                            objet: $("input[name=objet]").val(),
                                            date_emission: $("input[name=date_emission]").val(),
                                            nature: $("select[name=nature]").val(),
                                            importance: $("select[name=importance]").val(),
                                            support: $("select[name=support]").val(),
                                            note: $("textarea[name=note]").val()
                                        }
                                ).done(function (data) {
                                    var tempsEnMs = Date.now();
                                    window.open("/keamaworkspace/courrier#/prets?time=" + tempsEnMs);
                                    window.close();
                                });
                                ;

                            } else {
                                if (!i) {
                                    $.alert({
                                        title: 'Correspondant!',
                                        boxWidth: '30%',
                                        useBootstrap: false,
                                        type: 'red',
                                        autoClose: 'cancelAction|1000',
                                        content: 'Veuillez renseigner le correspondant !',
                                    });
                                    $("select[name=correspondant]").parent().addClass('error');
                                }
                                if (!j) {
                                    $.alert({
                                        title: 'Objet!',
                                        boxWidth: '30%',
                                        useBootstrap: false,
                                        type: 'red',
                                        autoClose: 'cancelAction|1000',
                                        content: 'Veuillez renseigner l\'objet !',
                                    });
                                    $("input[name=objet]").parent().addClass('error');
                                }
                            }
                        }

                        function creerCorrespondant() {

                            var i = $("input[name=nom_corres]").val();
                            var j = $("input[name=poste_corres]").val();
                            var o = $("select[name=entreprise_corres]").val();
                            var k = $("#entreprise_corres option[value='" + o + "']").text();
                            if (i && j) {

                                $.post(
                                        "CorrespondantServlet",
                                        {
                                            action: "terminer",
                                            id: $("input[name=id_corres]").val(),
                                            nom: $("input[name=nom_corres]").val(),
                                            tel: $("input[name=tel_corres]").val(),
                                            email: $("input[name=email_corres]").val(),
                                            poste: $("input[name=poste_corres]").val(),
                                            entreprise: $("select[name=entreprise_corres]").val()
                                        }
                                ).done(function (data) {
                                    $('#correspondant').prepend("<option value='" + data + "' selected>" + i + "(" + j + ") : " + k + "</option>");
                                    closeDialog('#dialogCorrespondant');
                                });
                                ;

                            } else {
                                if (!i) {
                                    $.alert({
                                        title: 'Correspondant!',
                                        boxWidth: '30%',
                                        useBootstrap: false,
                                        type: 'red',
                                        autoClose: 'cancelAction|1000',
                                        content: 'Veuillez renseigner le correspondant !',
                                    });
                                    $("select[name=correspondant]").parent().addClass('error');
                                }
                                if (!j) {
                                    $.alert({
                                        title: 'Objet!',
                                        boxWidth: '30%',
                                        useBootstrap: false,
                                        type: 'red',
                                        autoClose: 'cancelAction|1000',
                                        content: 'Veuillez renseigner l\'objet !',
                                    });
                                    $("input[name=objet]").parent().addClass('error');
                                }
                            }


                        }
                        function creerEntreprise() {

                            var i = $("input[name=nom_entre]").val();
                            var j = $("input[name=adresse_entre]").val();
                            if (i && j) {

                                $.post(
                                        "EntreprisePartenaireServlet",
                                        {
                                            action: "terminer",
                                            id: $("input[name=id_entre]").val(),
                                            nom: $("input[name=nom_entre]").val(),
                                            tel: $("input[name=tel_entre]").val(),
                                            email: $("input[name=email_entre]").val(),
                                            adresse: $("input[name=adresse_entre]").val()
                                        }
                                ).done(function (data) {
                                    $('#entreprise_corres').prepend("<option value='" + data + "' selected>" + i + "</option>");
                                    closeDialog('#dialogEntreprise');
                                });
                                ;

                            } else {
                                if (!i) {
                                    $.alert({
                                        title: 'Correspondant!',
                                        boxWidth: '30%',
                                        useBootstrap: false,
                                        type: 'red',
                                        autoClose: 'cancelAction|1000',
                                        content: 'Veuillez renseigner le correspondant !',
                                    });
                                    $("select[name=correspondant]").parent().addClass('error');
                                }
                                if (!j) {
                                    $.alert({
                                        title: 'Objet!',
                                        boxWidth: '30%',
                                        useBootstrap: false,
                                        type: 'red',
                                        autoClose: 'cancelAction|1000',
                                        content: 'Veuillez renseigner l\'objet !',
                                    });
                                    $("input[name=objet]").parent().addClass('error');
                                }
                            }


                        }

                        function supprimer(id) {

                            $.confirm({
                                title: 'Confirmation',
                                boxWidth: '30%',
                                useBootstrap: false,
                                content: 'Etes-vous sûr de vouloir supprimer le courrier ?',
                                type: 'orange',
                                buttons: {
                                    Oui: {
                                        text: 'Oui, je le veux',
                                        btnClass: 'button ribbed-orange fg-white',
                                        keys: ['enter', 'shift'],
                                        action: function () {
                                            $.post(
                                                    "NouveauCourrierServlet2",
                                                    {
                                                        action: "supprimer",
                                                        id: $("input[name=id]").val()
                                                    }
                                            );
                                            var tempsEnMs = Date.now();
                                            window.open("/keamaworkspace/courrier#/prets?time=" + tempsEnMs);
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
        <div data-role="dialog" id="dialogCorrespondant" class="grid padding20 ribbed-grayLighter" data-close-button="true" data-width="600">
            <div class="row cells">
                <div class="cell" style="height: 100%">
                    <h3>Correspondant</h3>
                    <div>
                        <label>Entreprise</label>
                        <div class="input-control text full-size" data-role="input">
                            <select id="entreprise_corres" name="entreprise_corres">
                                <c:forEach items="${entreprises}" var="c">
                                    <option value="${c.identreprisePartenaire}">${c.nom}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <label>Nom</label>
                        <div class="input-control text full-size" data-role="input">
                            <input name="nom_corres" >
                            <input name="id_corres" type="hidden">
                        </div>
                        <label>Poste</label>
                        <div class="input-control text full-size" data-role="input">
                            <input name="poste_corres" >
                        </div>
                        <label>Email</label>
                        <div class="input-control text full-size" data-role="input">
                            <input name="email_corres" >
                        </div>
                        <label>Tel :</label>
                        <div class="input-control text full-size" data-role="input">
                            <input name="tel_corres" >
                        </div>
                        <div class="input-control text full-size" data-role="input">
                            <input type="submit" class="bg-blue fg-white" onclick="creerCorrespondant();">
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div data-role="dialog" id="dialogEntreprise" class="grid padding20 ribbed-grayLighter" data-close-button="true" data-width="600">
            <div class="row cells">
                <div class="cell" style="height: 100%">
                    <h3>Entreprise</h3>
                    <div>
                        <label>Nom</label>
                        <div class="input-control text full-size" data-role="input">
                            <input name="nom_entre" >
                            <input name="id_entre" type="hidden">
                        </div>
                        <label>Adresse</label>
                        <div class="input-control text full-size" data-role="input">
                            <input name="adresse_entre" >
                        </div>
                        <label>Email</label>
                        <div class="input-control text full-size" data-role="input">
                            <input name="email_entre" >
                        </div>
                        <label>Tel :</label>
                        <div class="input-control text full-size" data-role="input">
                            <input name="tel_entre" >
                        </div>
                        <div class="input-control text full-size" data-role="input">
                            <input type="submit" class="bg-blue fg-white" onclick="creerEntreprise()();">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
