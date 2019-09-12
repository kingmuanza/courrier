<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                <div class="cell colspan3" id="cell-sidebar" >
                    <div id="block_recherche" class="fg-white" style="padding: 16px; display: block">
                        <!--                        <h2>Rechercher</h2>-->
                        <div class="input-control text full-size warning">
                            <span class="mif-search prepend-icon" style="color:orange"></span>
                            <input id="rechercher" type="text" placeholder="Rechercher">
                        </div>
                    </div>
                    <ul class="sidebar" style="overflow-x:hidden; padding-left: 0px; height: 60vh; overflow-y: auto; background-color: orange;">
                        <c:forEach items="${entreprises}" var="c">
                            <li class="li_muanza">
                                <a href="courrier#/correspondant/${e.identreprisePartenaire}">
                                    <span class="mif-mail-read icon"></span>
                                    <span class="title">${c.nom}</span>
                                    <span class="counter">${c.adresse}</span>
                                </a>
                            </li>
                        </c:forEach>

                    </ul>
                    <script>
                        //Chargement de la page
                        $("#spinner").hide();


                        //Filtrer les éléments de la liste
                        $("#rechercher").keyup(function () {
                            var mot = $("#rechercher").val();
                            console.log(mot);
                            $("ul.sidebar > li").css("display", "none");
                            $("ul.sidebar > li").each(function (index) {
                                console.log(index + ": " + $(this).find(".title:contains('" + mot + "')").text());
                                if ($(this).find(".title:contains('" + mot + "')").length > 0) {
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
                                    className: 'impression ribbed-grayLighter bd-white'
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
                    </script>
                </div>
                <div class="cell colspan9" style="height: 100%; padding-right: 20px;">
                    <div class="row cells6">
                        <h3 id="titre_liste">Correspondants</h3>
                        
                    </div>
                    <div class="row cells6">
                        <table id="example_table" class="dataTable border bordered" style="margin-bottom: 10px;">
                            <thead>
                                <tr>
                                    <th>Entreprise</th>
                                    <th>Adresse</th>
                                    <th>Tel</th>
                                    <th>Email</th>
                                    <th>Correspondants</th>
                                </tr>
                            </thead>

                            <tfoot >
                                <tr>
                                    <th>Entreprise</th>
                                    <th>Adresse</th>
                                    <th>Tel</th>
                                    <th>Email</th>
                                    <th>Correspondants</th>
                                </tr>
                            </tfoot>

                            <tbody>
                                <c:forEach items="${entreprises}" var="e">
                                    <tr>
                                        <td><a href="courrier#/correspondant/${e.identreprisePartenaire}">${e.nom}</a></td>
                                        <td>${e.adresse}</td>
                                        <td>${e.tel}</td>
                                        <td>${e.mail}</td>
                                        <td>
                                            <div class="treeview" data-role="treeview">
                                                <ul>
                                                    <c:forEach items="${e.correspondants}" var="c">
                                                        <li><a class="place-left icon mif-user mif-3x"></a> ${c.nomComplet}<br>${c.poste}</li>
                                                        </c:forEach>
                                                </ul>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>

                            </tbody>
                        </table>

                    </div>
                </div>
                
            </div>
        </div>

        <div data-role="dialog" id="dialog" class="grid padding20 ribbed-grayLighter" data-close-button="true" data-width="600" >
            <h1>Transmission</h1>
            <p style="padding-bottom: 40px;">
                Choisissez le destinataire du courrier
            </p>
            <form method="post" action="nouveauCourrier">
                <div class="row cells6">
                    <div class="cell colspan6">
                        <label>Courrier</label>
                        <div class="input-control select full-size">
                            <select name="dialog_courrier" disabled>
                                <c:forEach items="${courriers}" var="c">
                                    <option value="${c.idcourrier}">${c.ref}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="row cells6">
                    <div class="cell colspan6">
                        <label>Transmettre à : </label>
                        <div class="input-control select full-size">
                            <select name="service">
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
                        <div class="input-control select full-size">
                            <select name="service">
                                <option value="Demande d'approbation">Demande d'approbation</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="row cells6">
                    <div class="cell colspan3">
                        <label>Date d'émission</label>
                        <div class="input-control input full-size">
                            <div class="input-control text full-size" data-role="datepicker" data-date="">
                                <input type="text" name="date_emission">
                                <button class="button"><span class="mif-calendar"></span></button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row cells6">
                    <div class="cell colspan6">
                        <label>Commentaire</label>
                        <div class="input-control textarea full-size" data-role="input" data-text-auto-resize="true">
                            <textarea name="note"></textarea>
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
