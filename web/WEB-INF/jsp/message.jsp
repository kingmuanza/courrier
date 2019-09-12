<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Messagerie - KEAMA</title>

        <!-- JavaScript Chart, Jquery, treetable, metro-->
        <script src="js/jquery.js" type="text/javascript"></script>
        <script src="js/Chart.js" type="text/javascript"></script>
        <script src="js/metro.js" type="text/javascript"></script>
        <script src="js/jquery.treetable.js" type="text/javascript"></script>
        <script src="js/jquery.dataTables.js" type="text/javascript"></script>
        <script src="js/dataTables.buttons.min.js" type="text/javascript"></script>
        <script src="js/buttons.html5.min.js" type="text/javascript"></script>
        <script src="js/buttons.print.min.js" type="text/javascript"></script>
        <script src="js/pdfmake.min.js" type="text/javascript"></script>
        <script src="js/vfs_fonts.js" type="text/javascript"></script>
        <script src="js/buttons.flash.min.js" type="text/javascript"></script>
        <script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/processing.js/1.4.1/processing-api.min.js"></script>
        <script type="text/javascript" src="https://rawgithub.com/mozilla/pdf.js/gh-pages/build/pdf.js"></script>
        <script src="js/select2.js" type="text/javascript"></script>
        <script src="js/jquery-qrcode-0.14.0.js" type="text/javascript"></script>
        <script src="js/jquery-confirm.js"></script>
        <script src="js/viewer.js" type="text/javascript"></script>
        <script src="js/TimeCircles.js?id=3" type="text/javascript"></script>
        <!-- Fin JavaScript -->

        <!-- CSS  : TreeTable1, 2; Metro, -->
        <link href="css/jquery.treetable.css" rel="stylesheet" type="text/css"/>
        <link href="css/keama.css" rel="stylesheet" type="text/css"/>
        <link href="css/jquery.treetable.theme.default.css?modified=20012009" rel="stylesheet" type="text/css"/>
        <link href="css/metro.css?id=23" rel="stylesheet" type="text/css"/>
        <link href="css/metro-colors.css" rel="stylesheet" type="text/css"/>
        <link href="css/metro-icons.css" rel="stylesheet" type="text/css"/>
        <link href="css/metro-responsive.css" rel="stylesheet" type="text/css"/>
        <link href="css/metro-rtl.css" rel="stylesheet" type="text/css"/>
        <link href="css/metro-schemes.css?id=3" rel="stylesheet" type="text/css"/>
        <link href="css/jquery.dataTables.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/select2.css" rel="stylesheet" type="text/css"/>
        <link href="css/buttons.dataTables.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/jquery-confirm.css" rel="stylesheet" type="text/css"/>
        <link href="css/viewer.css" rel="stylesheet" type="text/css"/>
        <link href="css/TimeCircles.css" rel="stylesheet" type="text/css"/>
        <!-- Fin CSS -->

        <!-- ANGULAR -->
        <script src="js/angular.js" type="text/javascript"></script>
        <script src="js/angular-route.js" type="text/javascript"></script>
        <!-- FIN ANGULAR -->

        <script>
            var app = angular.module('workspace', ['ngRoute']);

            app.config(function ($routeProvider) {
                $routeProvider
                        .when('/', {templateUrl: 'message_content.jsp'})
                        .otherwise({redirectTo: '/'});
            });



            $(document).ready(function () {
                //Réception et affichage de l'image
                function readURL(input) {
                    var filesAmount = input.files.length;
                    $('#nb_fichiers').html(filesAmount + " fichier(s) ajouté(s)")
                    if (input.files && input.files[0]) {
                        var file = input.files[0];
                        var fileType = file["type"];
                        var ValidImageTypes = ["image/gif", "image/jpeg", "image/png"];
                        var ValidPDFTypes = ["application/pdf", "application/PDF"];
                        if ($.inArray(fileType, ValidImageTypes) > -1) {
                            $('#the-canvasBlock').css("display", "none");
                            var reader = new FileReader();
                            reader.onload = function (e) {
                                $('#imageAffichee').attr('src', e.target.result);
                                $('#imageAfficheeBlock').css("display", "block");
                            }
                            reader.readAsDataURL(input.files[0]);
                        }
                        if ($.inArray(fileType, ValidPDFTypes) > -1) {
                            $('#imageAfficheeBlock').css("display", "none");
                            PDFJS.disableWorker = true;
                            var pdf = document.getElementById('imageRecue');
                            fileReader = new FileReader();
                            fileReader.onload = function (ev) {
                                console.log(ev);
                                PDFJS.getDocument(fileReader.result).then(function getPdfHelloWorld(pdf) {
                                    //
                                    // Fetch the first page
                                    //
                                    console.log(pdf)
                                    pdf.getPage(1).then(function getPageHelloWorld(page) {
                                        var scale = 0.5;
                                        var viewport = page.getViewport(scale);
                                        //
                                        // Prepare canvas using PDF page dimensions
                                        //
                                        var canvas = document.getElementById('the-canvas');
                                        var context = canvas.getContext('2d');
                                        canvas.height = viewport.height;
                                        canvas.width = viewport.width;
                                        //
                                        // Render PDF page into canvas context
                                        //
                                        var task = page.render({canvasContext: context, viewport: viewport})
                                        task.promise.then(function () {
                                            console.log(canvas.toDataURL('image/jpeg'));
                                            $('#the-canvasBlock').css("display", "block");
                                        });
                                    });
                                }, function (error) {
                                    console.log(error);
                                });
                            };
                            fileReader.readAsArrayBuffer(file);
                        }
                    }
                }

                $("a.lien").click(function () {
                    var url_courante = window.location.href.toString();
                    url_courante = $.trim(url_courante)
                    //alert(url_courante);
                    var url_target = $(this).attr('href').toString();
                    url_target = $.trim(url_target)
                    //alert(url_target);
                    if (url_courante.indexOf(url_target) >= 0) {
                        //alert(url_courante.indexOf(url_target));
                    } else {
                        //alert(url_courante.indexOf(url_target));
                        $("#spinner").show();
                    }
                });

                function showDialog(id) {
                    var dialog = $(id).data('dialog');
                    dialog.open();
                    //$("#keama_back").css("opacity", "0.2");

                }

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
            });


        </script>


    </head>
    <body class="metro  ribbed-grayLighter " ng-app="workspace" style="overflow-y: hidden; margin-top: -13px;">
        <div id="message" class="grid condensed" style="border: 1px solid #eee;">

            <div class="row cells4">
                <div class="cell" style="background-image: url('img/keama_background.png'); height: 1000px; overflow-y: scroll">
                    <h1 class="fg-blue" style="padding-left: 20px; padding-right: 20px;">
                        Message 
                        <span style="cursor: pointer" onclick="window.location.href='start'" class="mif-home place-right"></span>
                    </h1>
                    <div id="" class="fg-white" style="padding: 16px; display: block">
                        <!--                        <h2>Rechercher</h2>-->
                        <div class="input-control text full-size info">
                            <span class="mif-search prepend-icon fg-blue"></span>
                            <input id="rechercher" type="text" placeholder="Rechercher">
                        </div>
                    </div>
                    <ul class="sidebar darcula" style="background-image: url('img/keama_background.png');">
                        <c:forEach items="${poste.employes}" var="employe">
                            <li><a href="message?id=${employe.idemploye}">
                                    <span class="mif-user icon"></span>
                                    <span class="title">${employe.noms} ${employe.prenoms}</span>
                                    <span class="counter">${poste.libelle}</span>
                                </a>
                            </li>
                        </c:forEach>
                        <c:forEach items="${postes}" var="poste">
                            <c:forEach items="${poste.employes}" var="employe">
                                <li><a href="message?id=${employe.idemploye}">
                                        <span class="mif-user icon"></span>
                                        <span class="title">${employe.noms} ${employe.prenoms}</span>
                                        <span class="counter">${poste.libelle}</span>
                                    </a>
                                </li>
                            </c:forEach>
                        </c:forEach>
                    </ul>
                </div>
                <div class="cell colspan3 ribbed-grayLighter">
                    <c:if test="${!empty recepteur}">
                        <div class="row cells12" style="padding-left: 20px;">
                            <div class="cell">
                                <img width="100" height="100;" style="padding-top: 0px; margin: 20px; border-radius: 50%; border: 2px solid #fff" src="${recepteur.imageLogo.chemin}" alt="" data-role="fitImage" data-format="cycle" class="place-left ribbed-blue bd-blue"/>
                            </div>
                            <div class="cell colspan11" style="padding-left: 20px;">
                                <h1 class="fg-blue" >${recepteur.noms} ${recepteur.prenoms}</h1>
                                <h3 class="fg-green" >${recepteur.poste.libelle}</h3>
                            </div>
                        </div>

                        <div class="padding20" style="height: 580px; overflow-y: scroll">
                            <c:forEach items="${messages}" var="message">
                                <c:if test="${message.employeByIdrecepteur.idemploye==utilisateur.employe.idemploye}">
                                    <div class="bg-blue fg-white place-left padding20" style="width: 80%;">
                                        <span class="">${message.message}</span><br>
                                        <small class="place-right">${message.dateEnvoi}</small>
                                    </div>
                                </c:if>
                                <c:if test="${message.employeByIdrecepteur.idemploye!=utilisateur.employe.idemploye}">
                                    <div class="bg-green fg-white place-right padding20" style="width: 80%;">
                                        <span class="">${message.message}</span><br>
                                        <small class="place-right">${message.dateEnvoi}</small>
                                    </div>
                                </c:if>
                            </c:forEach>

                        </div>
                        <div class="padding20" style="height: 300px; overflow-y: hidden">
                            <small class="fg-gray">${utilisateur.employe.noms} ${utilisateur.employe.prenoms}</small>
                            <form action="message" method="post">
                                <div class="input-control textarea full-size">
                                    <textarea name="message" placeholder="Ecrivez votre messsage ici..."></textarea>
                                    <input type="hidden" name="emetteur" value="${utilisateur.employe.idemploye}">
                                    <input type="hidden" name="recepteur" value="${recepteur.idemploye}">
                                </div>
                                <input class="button bg-blue fg-white" type="submit" value="Envoyer">
                            </form>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
    </body>
</html>
