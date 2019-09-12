<%-- 
    Document   : test
    Created on : Nov 7, 2016, 6:56:38 PM
    Author     : KEAMA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="grid" style="height: 100%; margin-top: -0px">
            <div class="row cells6" style="height: 100%">
                <div class="cell" id="cell-sidebar" style="background-image: url('img/keama_background.png'); height: 100%;">
                    <div id="block_recherche" class="fg-white" style="padding: 16px; display: block">
                        <!--                        <h2>Rechercher</h2>-->
                        <div class="input-control text full-size info">
                            <span class="mif-search prepend-icon fg-blue"></span>
                            <input id="rechercher" type="text" placeholder="Rechercher">
                        </div>
                    </div>
                    <ul class="sidebar darcula" style="height: 260px; background-image: url('img/keama_background.png');">
                        <li><a href="#/">
                                <span class="mif-apps icon"></span>
                                <span class="title">all items</span>
                                <span class="counter">Muanza dérange</span>
                            </a></li>
                        <li><a href="#/">
                                <span class="mif-vpn-publ icon"></span>
                                <span class="title">websites</span>
                                <span class="counter">0</span>
                            </a></li>
                        <li class="active"><a href="#/">
                                <span class="mif-drive-eta icon"></span>
                                <span class="title">Virtual machines</span>
                                <span class="counter">2</span>
                            </a></li>
                        <li><a href="#/">
                                <span class="mif-cloud icon"></span>
                                <span class="title">Cloud services</span>
                                <span class="counter">0</span>
                            </a></li>
                        <li><a href="#/">
                                <span class="mif-database icon"></span>
                                <span class="title">SQL Databases</span>
                                <span class="counter">0</span>
                            </a></li>
                        <li><a href="#/">
                                <span class="mif-cogs icon"></span>
                                <span class="title">Automation</span>
                                <span class="counter">0</span>
                            </a></li>
                        <li class="middle"><a href="#/">
                                <span class="mif-apps icon"></span>
                                <span class="title">all items</span>
                                <span class="counter">0</span>
                            </a></li>
                    </ul>
                    <script>
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
                    </script>
                </div>
                <div class="cell colspan4" style="height: 100%">
                    <div class="row cells6">
                        <h1 id="titre_liste">Enregistrement de fichier</h1>
                        <hr>
                    </div>
                    <div class="row cells6">
                        <div class="tabcontrol" data-role="tabcontrol">
                            <ul class="tabs">
                                <li><a href="#one">One </a></li>
                                <li><a href="#two">Two</a></li>
                            </ul>
                            <div class="frames">
                                <div class="frame" id="one">
                                    <form method="post" action="UploadServlet" enctype="multipart/form-data">
                                        <div class="row cells3">
                                            <div class="cell" style="height: 100%">
                                                <div class="input-control file full-size" data-role="input">
                                                    <input id="imageRecue" name="file" type="file" placeholder="Rechercher" accept="image/*">
                                                    <button class="button"><span class="mif-folder"></span></button>
                                                </div>
                                                <img id="imageAffichee" src="img/background.jpg" alt=""/>
                                                <div class="input-control text full-size" data-role="input">
                                                    <input type="submit" class="bg-blue fg-white">
                                                </div>
                                            </div>
                                            
                                        </div>
                                    </form>
                                </div>
                                <div class="frame" id="two">4</div>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="menu_droit" class="cell bg-grayLighter" style="padding: 20px;">

                    <div class="row align-center" >
                        <button class="image-button bg-blue fg-white" onclick="">
                            Nouveau
                            <span class="icon mif-file-empty bg-lightGreen fg-white"></span>
                        </button>
                    </div>
                    <div class="row align-center" >
                        <button class="image-button bg-blue fg-white" onclick="keama_impression(); $('.impression').click();">
                            Imprimer
                            <span class="icon mif-printer bg-lighterBlue fg-white"></span>
                        </button>
                    </div>
                    <div class="row align-center" >
                        <button class="image-button bg-blue fg-white" onclick="keama_export(); $('.impressionExcel').click();">
                            Exporter
                            <span class="icon mif-file-excel bg-darkIndigo fg-white"></span>
                        </button>
                    </div>
                    <div class="row align-center">
                        <button class="image-button bg-blue fg-white" onclick="keama_export(); $('.impressionPDF').click();">
                            Exporter
                            <span class="icon mif-file-pdf bg-orange fg-white"></span>
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
