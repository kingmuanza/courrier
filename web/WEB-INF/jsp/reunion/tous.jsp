<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script>
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
                                    className: 'button impressionExcel'
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
        </script>
    </head>
    <body>
        <div id="keama_back" class="grid" style="height: 100%; margin-top: -0px">
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
                    
                </div>
                <div class="cell colspan9" style="height: 100%; padding-right: 20px;">
                    <div class="row cells6">
                        <h3 id="titre_liste">
                            Toutes les réunions
                            
                        </h3>
                    </div>
                    <div class="row cells6">
                        <table id="example_table" class="dataTable border bordered" style="margin-bottom: 10px;">
                            <thead>
                                <tr>
                                    <th>Date début</th>
                                    <th>Référence</th>
                                    <th>Objet</th>
                                    <th>Convoqué par</th>
                                    <th>Date fin</th>
                                    <th>Statut</th>
                                    
                                </tr>
                            </thead>

                            <tfoot >
                                <tr>
                                    <th>Date début</th>
                                    <th>Référence</th>
                                    <th>Objet</th>
                                    <th>Convoqué par</th>
                                    <th>Date fin</th>
                                    <th>Statut</th>
                                    
                                </tr>
                            </tfoot>

                            <tbody>
                                <c:forEach items="${reunions}" var="c">
                                    <tr onclick="window.location.href='reunion#/id/${c.idreunion}'">
                                        <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${c.dateDebut}" var="dateDebut"/>
                                        <td>${dateDebut}</td>
                                        <td>${c.ref.length()>22 ? c.ref.substring(6,22): c.ref}</td>
                                        <td>${c.titre}</td>
                                        <td>${c.employe.noms} ${c.employe.prenoms}</td>
                                        <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${c.dateFin}" var="dateFin"/>
                                        <td>${dateFin}</td>
                                        <td>${c.statut}</td>
                                        

                                    </tr>
                                </c:forEach>

                            </tbody>
                        </table>

                    </div>
                </div>
                
            </div>
        </div>

    </body>
</html>
