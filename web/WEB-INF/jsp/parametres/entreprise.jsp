<%@page import="java.util.Date"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Entreprise</title>
    </head>
    <body>
        <div class="grid" style="height: 100%; margin-top: -0px">
            <div class="row cells6" style="height: 100%">
                <div class="cell" id="cell-sidebar" style="background-image: url('img/keama_background.png'); height: 100%;">
                    <div id="block_recherche" class="row" style="padding: 16px; display: block">
                        <form method="post" action="UploadServlet" enctype="multipart/form-data">
                            <div class="input-control file full-size" data-role="input">
                                <input id="imageRecue" name="file" type="file" placeholder="Logo de l'entreprise" accept="image/*">
                                <button class="button"><span class="mif-folder"></span></button>
                            </div>
                            <img id="imageAffichee" src="UploadedImages/logo.jpg" alt="" style="border : 0px #00ccff solid"/>
                            <div class="input-control text full-size" data-role="input">
                                <input type="submit" class="bg-blue fg-white">
                            </div>
                        </form>
                    </div>

                    <script>
                        //Chargement de la page
                        $("#spinner").hide();
                        
                        console.log("Taille de la fenetre : " + $(document).height());
                        var total = $(document).height();
                        console.log("Taille de l'entête : " + $('.keama_haut').height());
                        var taille_entete = $('.keama_haut').height();
                        console.log("Taille du menu : " + $('#menu').height());
                        var taille_menu = $('#menu').height();


                        var restant = total - taille_entete - taille_menu;
                        console.log(restant);
                        $('#block_recherche').height(restant);


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

                        function envoyer45Formulaire() {
                            console.log("date : " + $("input[name=date_creation]").val());
                            $.post(
                                    "entreprise?time=1544323",
                                    {
                                        nom: $("input[name=nom]").val(),
                                        sigle: $("input[name=sigle]").val(),
                                        rib: $("input[name=rib]").val(),
                                        date_creation: $("input[name=date_creation]").val(),
                                        adresse: $("input[name=adresse]").val(),
                                        adresse_complement: $("input[name=adresse_complement]").val(),
                                        bp: $("input[name=bp]").val(),
                                        fax: $("input[name=fax]").val(),
                                        tel1: $("input[name=tel1]").val(),
                                        tel2: $("input[name=tel2]").val(),
                                        tel3: $("input[name=tel3]").val(),
                                        email1: $("input[name=email1]").val(),
                                        email2: $("input[name=email2]").val(),
                                        email3: $("input[name=email3]").val(),
                                        time: "00"
                                    }
                            );
                        }

                        function showDialog(id) {
                            var dialog = $(id).data('dialog');
                            dialog.open();
                            //$("#keama_back").css("opacity", "0.2");

                        }
                        
                        function showDialog(id, idsite, nomsite, sigle, estSiege) {
                            var dialog = $(id).data('dialog');
                            dialog.open();
                            $("#formulaireSite input[name=id]").val(idsite);
                            $("#formulaireSite input[name=nom]").val(nomsite);
                            $("#formulaireSite input[name=sigle]").val(sigle);
                            $("#formulaireSite input[name=siege]").attr('checked', estSiege);

                        }

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
                        
                        function supprimer(id) {

                            $.confirm({
                                title: 'Confirmation',
                                boxWidth: '30%',
                                useBootstrap: false,
                                content: 'Etes-vous sûr de vouloir supprimer ce site ? ',
                                buttons: {
                                    Oui: {
                                        text: 'Oui, supprimer',
                                        btnClass: 'button ribbed-red fg-white',
                                        keys: ['enter', 'shift'],
                                        action: function () {
                                            $.post(
                                                    "SiteServlet",
                                                    {
                                                        action: "supprimer",
                                                        id: id
                                                    }
                                            );
                                            var tempsEnMs = Date.now();
                                            window.open("parametres#/entreprise?t=" + tempsEnMs);
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
                <div class="cell colspan4" style="height: 100%">
                    <div class="padding10"></div>
                    <div class="" style="border: 1px solid #1ba1e2; padding: 0px;">
                        <div class="padding10">
                            <div class="step">
                                <div class="step-content">
                                    <div class="ribbed-grayLighter padding20" style="padding-right: 20px; border-bottom: 1px solid #1ba1e2;">
                                        <div class="row cells12" style="padding-top: 0px;">
                                            <div class="cell colspan6">
                                                <h1 class="no-wrap fg-blue">${entreprise.nom.toUpperCase()}</h1>
                                                <small><span class="">${entreprise.sigle.toUpperCase()} : Société à responsabilité limitée</span></small>
                                                <br><small>RIB : ${entreprise.numeroContribuable.toUpperCase()}</small>
                                            </div>
                                            <div class="cell colspan6">
                                                <div class="toolbar place-right">
                                                    <div class="toolbar-section">
                                                        <a class="toolbar-button bg-grayLighter bd-grayLighter" data-role="hint" data-hint="Modifier les informations de l'entreprise" onclick="showDialog('#dialog')" ><span class="mif-pencil "></span></a>

                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row cells4" style="padding-top: 20px;">
                                            <div class="cell">
                                                <fmt:setLocale value="fr" />
                                                <fmt:formatDate value="${entreprise.dateCreation}" type="date" dateStyle="long" var="jour" />
                                                <fmt:formatDate value="${courrier.dateEnr}" type="date" dateStyle="long" var="debut" />
                                                <small>
                                                    <span class="mif-calendar mif-3x place-left padding5"></span> Créé le : <span class="fg-blue">${jour}</span> 
                                                    <br>Siège : <span class="fg-blue">${entreprise.adresse}</span>
                                                    <br>BP : <span class="fg-blue">${entreprise.bp}</span>
                                                </small>
                                            </div>
                                            <div class="cell">
                                                <small>
                                                    <span class="mif-location mif-3x place-left padding5"></span><span class="fg-blue">${entreprise.adresse}</span> 
                                                    <br><span class="fg-blue">${entreprise.adresseComplement}</span>
                                                    <br>Fax : <span class="fg-blue">${entreprise.fax}</span>
                                                </small>
                                            </div>
                                            <div class="cell">
                                                <small>
                                                    <span class="mif-phone mif-3x place-left padding5"></span> ${entreprise.tel1} 
                                                    <br>${entreprise.tel2}
                                                    <br>${entreprise.tel3}
                                                </small>
                                            </div>
                                            <div class="cell">
                                                <small>
                                                    <span class="mif-mail-read mif-3x place-left padding5"></span> ${entreprise.email1} 
                                                    <br>${entreprise.email2}
                                                    <br>${entreprise.email3}
                                                </small>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="listview set-border list-type-listing " data-role="listview">
                                            <div class="padding20">
                                                <small class="place-right">
                                                    <a style="cursor: pointer;" class="toolbar-button" data-role="hint" data-hint="Modifier les informations de l'entreprise" onclick="showDialog('#dialog_site')" >
                                                        <h5 class="mif-plus "></h5>
                                                    </a>
                                                </small>
                                                <h2 class=""><span class="fg-blue">Sites</span></h2>

                                                <table id="example_table" class="dataTable border bordered" style="margin-bottom: 10px; margin-top: 10px;">
                                                    <thead>
                                                        <tr>
                                                            <th>N°</th>
                                                            <th>Nom</th>
                                                            <th>Sigle</th>
                                                            <th>Siège</th>
                                                            <th>Action</th>
                                                        </tr>
                                                    </thead>

                                                    <tfoot >
                                                        <tr>
                                                            <th>N°</th>
                                                            <th>Nom</th>
                                                            <th>Sigle</th>
                                                            <th>Siège</th>
                                                            <th>Action</th>
                                                        </tr>
                                                    </tfoot>

                                                    <tbody>
                                                        <c:forEach items="${entreprise.sites}" varStatus="boucle" var="site">
                                                            <tr class="${site.estSiege ? "ribbed-grayLighter":""}">
                                                                <td>${boucle.count}</td>
                                                                <td><a onclick="showDialog('#dialog_site', '${site.idsite}','${site.nom}', '${site.sigle}', ${site.estSiege});" style="cursor: pointer;">${site.nom}</a></td>
                                                                <td>${site.sigle}</td>
                                                                <td>${site.estSiege ? "Siège":"" }</td>
                                                                <td class="">
                                                                    <a onclick="supprimer('${site.idsite}');" style="cursor: pointer;" data-role="hint" data-hint="Supprimer">Supprimer </a>
                                                                </td>
                                                            </tr>

                                                        </c:forEach>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="menu_droit" class="cell bg-grayLighter" style="padding: 20px;">

                    <div class="row align-center" >
                        <button class="image-button bg-blue fg-white" onclick="showDialog('#dialog')">
                            Modifier
                            <span class="icon mif-pencil bg-lightGreen fg-white"></span>
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <div data-role="dialog, draggable" id="dialog" class="grid padding20 ribbed-grayLighter" data-close-button="true" data-width="800" >
            <h1 class="fg-green">Modifier</h1>
            <p style="padding-bottom: 20px;">
                Modifier les informations de l'entreprise
            </p>
            <form method="post" action="entreprise" class="grid condensed">
                <div class="row cells6">
                    <div class="cell colspan4" style="padding-right: 10px;">
                        <label>Nom de l'entreprise</label>
                        <div class="input-control text full-size">
                            <input name="nom" value="${entreprise.nom}" placeholder="" >
                        </div>
                    </div>
                    <div class="cell colspan2">
                        <label>Sigle</label>
                        <div class="input-control text full-size">
                            <input name="sigle" value="${entreprise.sigle}" placeholder="" >
                        </div>
                    </div>
                </div>
                <div class="row cells6">
                    <div class="cell colspan4" style="padding-right: 10px;">
                        <label>RIB</label>
                        <div class="input-control text full-size">
                            <input name="rib" value="${entreprise.numeroContribuable}" placeholder="" >
                        </div>
                    </div>
                    <div class="cell colspan2">
                        <label>Date de création</label>
                        <fmt:formatDate pattern="yyyy.MM.dd" value="${entreprise.dateCreation}" var="date_creation"/>
                        <div class="input-control text full-size" data-role="datepicker" data-date="${date_creation}">
                            <input type="text" name="date_creation">
                            <button class="button"><span class="mif-calendar"></span></button>
                        </div>
                    </div>
                </div>
                <div class="row cells6">
                    <div class="cell colspan3" style="padding-right: 10px;">
                        <label>Adresse</label>
                        <div class="input-control text full-size">
                            <input name="adresse" value="${entreprise.adresse}" >
                        </div>
                    </div>
                    <div class="cell colspan3">
                        <label>Adresse, Complément</label>
                        <div class="input-control text full-size">
                            <input name="adresse_complement" value="${entreprise.adresseComplement}">
                        </div>
                    </div>
                </div>
                <div class="row cells6">
                    <div class="cell colspan3" style="padding-right: 10px;">
                        <label>BP</label>
                        <div class="input-control text full-size">
                            <input name="bp" placeholder="" value="${entreprise.bp}">
                        </div>
                    </div>
                    <div class="cell colspan3">
                        <label>Fax</label>
                        <div class="input-control text full-size">
                            <input name="fax" placeholder="" value="${entreprise.fax}">
                        </div>
                    </div>
                </div>

                <div class="row cells6">
                    <div class="cell colspan2" style="padding-right: 10px;">
                        <label>Téléphone 1 : </label>
                        <div class="input-control text full-size">
                            <input name="tel1" placeholder="" value="${entreprise.tel1}">
                        </div>
                    </div>
                    <div class="cell colspan2" style="padding-right: 10px;">
                        <label>Téléphone 2 : </label>
                        <div class="input-control text full-size">
                            <input name="tel2" placeholder="" value="${entreprise.tel2}">
                        </div>
                    </div>
                    <div class="cell colspan2" >
                        <label>Téléphone 3 : </label>
                        <div class="input-control text full-size">
                            <input name="tel3" placeholder="" value="${entreprise.tel3}">
                        </div>
                    </div>
                </div>

                <div class="row cells6">
                    <div class="cell colspan2" style="padding-right: 10px;">
                        <label>Email 1 : </label>
                        <div class="input-control text full-size">
                            <input name="email1" placeholder="" value="${entreprise.email1}">
                        </div>
                    </div>
                    <div class="cell colspan2" style="padding-right: 10px;">
                        <label>Email 2 : </label>
                        <div class="input-control text full-size">
                            <input name="email2" placeholder="" value="${entreprise.email2}">
                        </div>
                    </div>
                    <div class="cell colspan2" >
                        <label>Email 3 : </label>
                        <div class="input-control text full-size">
                            <input name="email3" placeholder="" value="${entreprise.email3}">
                        </div>
                    </div>
                </div>
                <div class="row cells6">
                    <div class="cell colspan6">
                        <br>
                        <input type="submit" class="button ribbed-green fg-white" value="Modifier" >
                    </div>
                </div>

            </form>
        </div>

        <div data-role="dialog, draggable" id="dialog_site" class="grid padding20 ribbed-grayLighter" data-close-button="true" data-width="600" >
            <h1 class="fg-green"></h1>
            <form id="formulaireSite" method="post" action="SiteServlet" style="padding-top: 40px;">
                <div class="row cells6">
                    <div class="cell colspan5">
                        <label>Nom du site :</label>
                        <div class="input-control text full-size">
                            <input type="text" name="nom" value="">
                            <input type="hidden" name="id" value="">
                        </div>
                    </div>
                    <div class="cell colspan">
                        <label>Sigle :</label>
                        <div class="input-control text full-size">
                            <input type="text" name="sigle" value="">
                        </div>
                    </div>
                </div>
                <label class="input-control checkbox">
                    <input type="checkbox" name="siege" checked>
                    <span class="check"></span>
                    <span class="caption">Siège de l'entreprise</span>
                </label>
                <div class="row cells6" style="padding-top: 40px;">
                    <div class="cell colspan6">
                        <input type="submit" value="Modifier" class="button ribbed-green fg-white">
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>
