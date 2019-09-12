<%@page import="org.apache.commons.lang3.StringEscapeUtils"%>
<%@page import="java.util.Date"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="muanza" uri="/WEB-INF/tlds/mytaglibs" %>

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
                    <div id="block_recherche" class="row" style="padding: 20px; display: block">
                        <form method="post" action="UploadServlet" enctype="multipart/form-data">
                            <div class="input-control file full-size" data-role="input">
                                <input id="imageRecue" name="file" type="file" placeholder="Choisissez une photo" accept="image/*">
                                <button class="button"><span class="mif-folder"></span></button>
                            </div>
                            <img id="imageAffichee" src="${employe.imageLogo.chemin}" alt="" style="border : 0px #00ccff solid"/>
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

                        var titre = $("#titre_liste").html();
                        $("#example_table").DataTable({
                            'searching': true,
                            "info": true,
                            "pageLength": 200,
                            "ordering": false,
                            dom: '<"top"fB>rt<"bottom"lp><"clear">',
                            buttons: [
                                {
                                    text: "Actualiser",
                                    className: 'impression ribbed-grayLighter bd-white',
                                    action: function () {
                                        $("#example_table").treetable();
                                    }
                                }, {
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
                                    className: 'impression ribbed-grayLighter bd-white'
                                },
                                {
                                    text: "Réduire tout",
                                    className: 'impression ribbed-grayLighter bd-white',
                                    action: function () {
                                        $("#example_table").treetable("collapseAll");
                                    }
                                },
                                {
                                    text: "Développer tout",
                                    className: 'impression ribbed-grayLighter bd-white',
                                    action: function () {
                                        $("#example_table").treetable("expandAll");
                                    }
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
                        $("#example_table").treetable({expandable: true, column: 1});
                        $("#example_table").treetable("expandAll");

                        function supprimer(id) {

                            $.confirm({
                                title: 'Confirmation',
                                boxWidth: '30%',
                                useBootstrap: false,
                                content: 'Etes-vous sûr de vouloir supprimer cette structure ? ',
                                buttons: {
                                    Oui: {
                                        text: 'Oui, supprimer',
                                        btnClass: 'button ribbed-red fg-white',
                                        keys: ['enter', 'shift'],
                                        action: function () {
                                            $.post(
                                                    "StructureServlet",
                                                    {
                                                        action: "supprimer",
                                                        id: id
                                                    }
                                            );
                                            var tempsEnMs = Date.now();
                                            window.open("parametres#/structure?t=" + tempsEnMs);
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
                                                <span class="">${employe.poste.libelle}</span>
                                                <h1 class="no-wrap fg-blue">${employe.noms.toUpperCase()} ${employe.prenoms}</h1>
                                                <small>Sexe : </small><small class="fg-blue">${employe.sexe ? "Femme":"Homme"}</small>
                                            </div>
                                            <div class="cell colspan6">
                                                <div class="toolbar place-right">
                                                    <div class="toolbar-section">
                                                        <a class="toolbar-button bg-grayLighter bd-grayLighter" data-role="hint" data-hint="Modifier les informations de l'entreprise" onclick="showDialog('#dialog_employe')" ><span class="mif-pencil "></span></a>

                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row cells4" style="padding-top: 20px;">
                                            <div class="cell">
                                                <fmt:setLocale value="fr" />
                                                <fmt:formatDate value="${employe.datenaiss}" type="date" dateStyle="long" var="jour" />
                                                <small>
                                                    <span class="mif-calendar mif-3x place-left padding5"></span> 
                                                    Né le : <span class="fg-blue">${empty employe.datenaiss ? "<span>Indéfini</span>":jour}</span> 
                                                    <br>A : <span class="fg-blue">${empty employe.lieunaiss ? "<span>Indéfini</span>":employe.lieunaiss}</span>
                                                    <br>Age : 57 ans

                                                </small>
                                            </div>
                                            <div class="cell">
                                                <small>
                                                    <span class="mif-user mif-3x place-left padding5"></span>
                                                    CNI : <span class="fg-blue">${employe.cni}</span> 
                                                    <br>Matricule : <span class="fg-blue">${employe.matricule}</span>
                                                    <fmt:formatDate value="${employe.dateAccession}" type="date" dateStyle="long" var="dateAccession" />
                                                    <br>Employé depuis le : <span class="fg-blue">${empty employe.dateAccession ? 'Indéfini':dateAccession}</span>

                                                </small>
                                            </div>
                                            <div class="cell">
                                                <small>
                                                    <span class="mif-phone mif-3x place-left padding5"></span> 
                                                    <span class="fg-blue">${empty employe.tel1 ? 'Indéfini': employe.tel1} </span>
                                                    <br><span class="fg-blue">${empty employe.tel2 ? 'Indéfini': employe.tel3} </span>
                                                    <br>Appel local
                                                </small>
                                            </div>
                                            <div class="cell">
                                                <small>
                                                    <span class="mif-mail-read mif-3x place-left padding5"></span> 
                                                    <span class="fg-blue">${empty employe.email1 ? 'Indéfini': employe.email1} </span>
                                                    <br><span class="fg-blue">${empty employe.email2 ? 'Indéfini': employe.email2} </span>
                                                    <br><span class="fg-blue">Ecrire un message </span>
                                                </small>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="listview set-border list-type-listing " data-role="listview">
                                            <div class="padding20">
                                                <small class="place-right">
                                                    <a style="cursor: pointer;" class="toolbar-button" data-role="hint" data-hint="Ajouter un poste" onclick="showDialog('#dialog_nouveau')" >
                                                        <h5 class="mif-plus "></h5>
                                                    </a>
                                                </small>
                                                <h2 class=""><span class="fg-blue">Employés</span></h2>

                                                <table id="example_table" class="dataTable border bordered" style="margin-bottom: 10px; margin-top: 10px;">
                                                    <thead>
                                                        <tr>
                                                            <th>N°</th>
                                                            <th>Sigle</th>
                                                            <th>Nom</th>

                                                            <th>
                                                                Sexe
                                                            </th>
                                                            <th>Utilisateur</th>
                                                        </tr>
                                                    </thead>

                                                    <tfoot >
                                                        <tr>
                                                            <th>N°</th>
                                                            <th>Sigle</th>
                                                            <th>Nom</th>
                                                            <th>Sexe</th>
                                                            <th>Utilisateur</th>
                                                        </tr>
                                                    </tfoot>

                                                    <tbody>
                                                        <c:forEach items="${postes}" varStatus="boucle" var="poste">
                                                            <tr class="" data-tt-id="${poste.idposte}" data-tt-parent-id="${poste.poste.idposte}">
                                                                <td>${boucle.count}</td>
                                                                <td style="white-space: nowrap;">
                                                                    <a onclick="showDialog('#dialog_site', '${poste.idposte}', '${muanza : escapeJS(poste.libelle)}', '${poste.sigle}', '${poste.poste.idposte}', '${poste.structure.idstructure}');" style="cursor: pointer;">
                                                                        ${poste.sigle}
                                                                    </a>
                                                                </td>
                                                                <td>
                                                                    <a href="parametres#/employe/${poste.employes.iterator().next().idemploye}" style="cursor: pointer;">
                                                                        <c:out value="${poste.employes.iterator().hasNext() ? poste.employes.iterator().next().noms :'' }"/> <c:out value="${poste.employes.iterator().hasNext() ? poste.employes.iterator().next().prenoms :'' }"/>
                                                                    </a>
                                                                </td>
                                                                <td style="white-space: nowrap;"><c:out value="${poste.employes.iterator().next().sexe ? 'Femme' :'Homme' }"/></td>
                                                                <td class="">
                                                                    ${poste.employes.iterator().next().utilisateurs.iterator().hasNext() ? "<span class='mif-user-check fg-green'></span> ".concat(poste.employes.iterator().next().utilisateurs.iterator().next().login) :"<span class='mif-user-plus fg-blue'></span> <a href=''>Ajouter un utilisateur</a>" }
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
                    <div class="row align-center" >
                        <button class="image-button bg-blue fg-white" onclick="window.open('organigrammeEmploye?id=${employe.poste.idposte}')">
                            Organigramme
                            <span class="icon mif-users bg-lightGreen fg-white"></span>
                        </button>
                    </div>
                </div>
            </div>
        </div>

        <div data-role="dialog, draggable" id="dialog_employe" class="grid padding20 ribbed-grayLighter" data-close-button="true" data-width="900" >
            <h1 class="fg-green">Employé</h1>
            <form id="formulairePoste" method="post" action="EmployeServlet" style="padding-top: 40px;">
                <div class="row cells6">
                    <div class="cell">
                        <label>Sexe :</label>
                        <div class="input-control select full-size">
                            <select name="sexe">
                                <option value="0">Homme</option>
                                <option value="1">Femme</option>
                            </select>
                        </div>
                    </div>
                    <div class="cell colspan3">
                        <label>Noms :</label>
                        <div class="input-control text full-size">
                            <input type="text" name="noms" value="${employe.noms}">
                            <input type="hidden" name="id" value="${employe.idemploye}">
                        </div>
                    </div>
                    <div class="cell colspan2">
                        <label>Prénoms :</label>
                        <div class="input-control text full-size">
                            <input type="text" name="prenoms" value="${employe.prenoms}">
                        </div>
                    </div>
                </div>
                <div class="row cells8">
                    <div class="cell colspan4">
                        <label>Poste :</label>
                        <div class="input-control select full-size">
                            <select name="poste">
                                <option value="">Aucun</option>
                                <c:forEach items="${postes}" varStatus="boucle" var="poste">
                                    <option value="${poste.idposte}" ${employe.poste.idposte==poste.idposte ? "selected":""}>${poste.libelle}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="cell colspan2">
                        <label>Matricule :</label>
                        <div class="input-control text full-size">
                            <input type="text" name="matricule" value="${employe.matricule}">
                        </div>
                    </div>
                    <div class="cell colspan2">
                        <label>CNI :</label>
                        <div class="input-control text full-size">
                            <input type="text" name="cni" value="${employe.cni}">
                        </div>
                    </div>
                </div>
                <div class="row cells6">
                    <div class="cell colspan2">
                        <label>Date de naissance :</label>
                        <fmt:formatDate pattern="yyyy-MM-dd" value="${employe.datenaiss}" var="datenaiss"/>
                        <div class="input-control text full-size" data-role="datepicker" data-preset="${!empty employe.datenaiss ? datenaiss:'2016-01-01'}">
                            <input type="text" name="datenaiss">
                            <button class="button"><span class="mif-calendar"></span></button>
                        </div>
                    </div>
                    <div class="cell colspan4">
                        <label>Lieu de naissance :</label>
                        <div class="input-control text full-size">
                            <input type="text" name="lieunaiss" value="${employe.lieunaiss}">
                        </div>
                    </div>
                </div>

                <div class="row cells4">
                    <div class="cell">
                        <label>Tel 1 :</label>
                        <div class="input-control text full-size">
                            <input type="text" name="tel1" value="${employe.tel1}">
                        </div>
                    </div>
                    <div class="cell">
                        <label>Email 1 :</label>
                        <div class="input-control text full-size">
                            <input type="text" name="email1" value="${employe.email1}">
                        </div>
                    </div>
                    <div class="cell">
                        <label>Tel 2 :</label>
                        <div class="input-control text full-size">
                            <input type="text" name="tel2" value="${employe.tel2}">
                        </div>
                    </div>
                    <div class="cell">
                        <label>Email 2 :</label>
                        <div class="input-control text full-size">
                            <input type="text" name="email2" value="${employe.email2}">
                        </div>
                    </div>
                </div>
                <div class="row cells6">
                    <div class="cell colspan2">
                        <label>Date d'accession :</label>
                        <fmt:formatDate pattern="yyyy-MM-dd" value="${employe.dateAccession}" var="datenaiss"/>
                        <div class="input-control text full-size" data-role="datepicker" data-preset="${!empty employe.dateAccession ? dateAccession:'2016-01-01'}">
                            <input type="text" name="dateAccession">
                            <button class="button"><span class="mif-calendar"></span></button>
                        </div>
                    </div>
                </div>
                <div class="row cells6" style="padding-top: 40px;">
                    <div class="cell colspan6">
                        <input type="submit" value="Modifier" class="button ribbed-green fg-white">
                    </div>
                </div>
            </form>
        </div>
                            
        <div data-role="dialog, draggable" id="dialog_nouveau" class="grid padding20 ribbed-grayLighter" data-close-button="true" data-width="900" >
            <h1 class="fg-green">Employé</h1>
            <form id="formulairePoste" method="post" action="EmployeServlet" style="padding-top: 40px;">
                <div class="row cells6">
                    <div class="cell">
                        <label>Sexe :</label>
                        <div class="input-control select full-size">
                            <select name="sexe">
                                <option value="0">Homme</option>
                                <option value="1">Femme</option>
                            </select>
                        </div>
                    </div>
                    <div class="cell colspan3">
                        <label>Noms :</label>
                        <div class="input-control text full-size">
                            <input type="text" name="noms" value="">
                            <input type="hidden" name="id" value="">
                        </div>
                    </div>
                    <div class="cell colspan2">
                        <label>Prénoms :</label>
                        <div class="input-control text full-size">
                            <input type="text" name="prenoms" value="">
                        </div>
                    </div>
                </div>
                <div class="row cells8">
                    <div class="cell colspan4">
                        <label>Poste :</label>
                        <div class="input-control select full-size">
                            <select name="poste">
                                <option value="">Aucun</option>
                                <c:forEach items="${postes}" varStatus="boucle" var="poste">
                                    <option value="${poste.idposte}" ${employe.poste.idposte==poste.idposte ? "selected":""}>${poste.libelle}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="cell colspan2">
                        <label>Matricule :</label>
                        <div class="input-control text full-size">
                            <input type="text" name="matricule" value="">
                        </div>
                    </div>
                    <div class="cell colspan2">
                        <label>CNI :</label>
                        <div class="input-control text full-size">
                            <input type="text" name="cni" value="">
                        </div>
                    </div>
                </div>
                <div class="row cells6">
                    <div class="cell colspan2">
                        <label>Date de naissance :</label>
                        <fmt:formatDate pattern="yyyy-MM-dd" value="${employe.datenaiss}" var="datenaiss"/>
                        <div class="input-control text full-size" data-role="datepicker" data-preset="${!empty employe.datenaiss ? datenaiss:'2016-01-01'}">
                            <input type="text" name="datenaiss">
                            <button class="button"><span class="mif-calendar"></span></button>
                        </div>
                    </div>
                    <div class="cell colspan4">
                        <label>Lieu de naissance :</label>
                        <div class="input-control text full-size">
                            <input type="text" name="lieunaiss" value="">
                        </div>
                    </div>
                </div>

                <div class="row cells4">
                    <div class="cell">
                        <label>Tel 1 :</label>
                        <div class="input-control text full-size">
                            <input type="text" name="tel1" value="">
                        </div>
                    </div>
                    <div class="cell">
                        <label>Email 1 :</label>
                        <div class="input-control text full-size">
                            <input type="text" name="email1" value="">
                        </div>
                    </div>
                    <div class="cell">
                        <label>Tel 2 :</label>
                        <div class="input-control text full-size">
                            <input type="text" name="tel2" value="">
                        </div>
                    </div>
                    <div class="cell">
                        <label>Email 2 :</label>
                        <div class="input-control text full-size">
                            <input type="text" name="email2" value="">
                        </div>
                    </div>
                </div>
                <div class="row cells6">
                    <div class="cell colspan2">
                        <label>Date d'accession :</label>
                        <fmt:formatDate pattern="yyyy-MM-dd" value="${employe.dateAccession}" var="datenaiss"/>
                        <div class="input-control text full-size" data-role="datepicker" data-preset="${!empty employe.dateAccession ? dateAccession:'2016-01-01'}">
                            <input type="text" name="dateAccession">
                            <button class="button"><span class="mif-calendar"></span></button>
                        </div>
                    </div>
                </div>
                <div class="row cells6" style="padding-top: 40px;">
                    <div class="cell colspan6">
                        <input type="submit" value="Modifier" class="button ribbed-green fg-white">
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>
