<%@page import="java.util.Date"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SIA COURRIER</title>

        <!-- JavaScript Chart, Jquery, treetable, metro-->
        <script src="js/jquery.js" type="text/javascript"></script>
        <script src="js/Chart.js" type="text/javascript"></script>
        <script src="js/metro.js" type="text/javascript"></script>
        <script src="js/jquery.treetable.js" type="text/javascript"></script>
        <!-- Fin JavaScript -->

        <!-- CSS  : TreeTable1, 2; Metro, -->
        <link href="css/jquery.treetable.css" rel="stylesheet" type="text/css"/>
        <link href="css/keama.css" rel="stylesheet" type="text/css"/>
        <link href="css/jquery.treetable.theme.default.css" rel="stylesheet" type="text/css"/>
        <link href="css/metro.css" rel="stylesheet" type="text/css"/>
        <link href="css/metro-colors.css" rel="stylesheet" type="text/css"/>
        <link href="css/metro-icons.css" rel="stylesheet" type="text/css"/>
        <link href="css/metro-responsive.css" rel="stylesheet" type="text/css"/>
        <link href="css/metro-rtl.css" rel="stylesheet" type="text/css"/>
        <link href="css/metro-schemes.css" rel="stylesheet" type="text/css"/>
        <!-- Fin CSS -->
        <style>
            .ombre{
                box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.3), 0 6px 20px 0 rgba(0, 0, 0, 0.29);
            }
        </style>

    </head>

    <body class="metro keama_fixe" style="background-image: url('img/wallbw.jpg'); background-attachment: fixed;">
        <div style="position: fixed; width: 100vw; height: 100vh; background-color: rgba(31, 15, 31, 0.8);">
            
        </div>
        <div class="keama_milieu ombre" style="border : 3px solid #410f41; border-radius: 0px; background-color: white">
            <div>
                <div class="fg-white lign-center">
                    <div id="loading" class="row cells12">
                        <h1 class="keama_h1" style="font-size: 3.5em; color: #777; font-weight: bold">
                            SIA<span class="muanza_fg_violet">COURRIER</span>
                            <sup>
                                <span id="spinner" class="mif-spinner mif-ani-spin muanza_fg_violet">

                                </span>
                            </sup>
                        </h1>
                    </div>

                </div>

                <div class="grid">
                    <div class="row cells6">
                        <div class="cell colspan6">
                            <div class="input-control text full-size ">
                                <span class="mif-user prepend-icon muanza_fg_violet"></span>
                                <input id="login" name="login" type="text" placeholder="Login">
                                <button class="button helper-button clear"><span class="mif-cross"></span></button>
                            </div>
                        </div>
                    </div>
                    <div class="row cells6">
                        <div class="cell colspan6">
                            <div class="input-control password full-size " data-role="input">
                                <span class="mif-lock prepend-icon muanza_fg_violet"></span>
                                <input id="password" name="password" type="password" placeholder="Mot de passe">
                                <button class="button helper-button clear"><span class="mif-cross"></span></button>
                            </div>
                        </div>
                    </div>
                    <div class="row cells12">
                        <button onclick="chargement();" class="button fg-white muanza_bg_violet bd-darkIndigo">
                            Connexion
                        </button>
                    </div>
                </div>
            </div>
        </div>

        <div class="fg-white" style="position: fixed; bottom: 0; right: 0">
            <div style="padding: 20px; font-weight: bold;">
                &copy; SIA TECHNOLOGY GROUP 2018
            </div>
        </div>
    </body>

    <script>
        $(document).ready(function () {
            $('#spinner').hide();
        });
        function chargement() {
            $('#spinner').show();
//            $('#login').attr("disabled", "disabled");
//            $('#password').attr("disabled", "disabled");
//
//            $("#login").fadeTo("slow", 0.5, function () {
//                $("#logo").fadeTo(5000, 0.95, function () {
//
//                });
//            });
//
//            $("#password").fadeTo("slow", 0.5, function () {
//                // Animation complete.
//            });

            //Envoi du login et du passe
            $.post(
                    "start",
                    {
                        login: $("#login").val(),
                        passe: $("#password").val()
                    },
                    function (data) {

                        console.log(data);
                        if (data == "null") {
                            $.Notify({
                                caption: 'Notification',
                                content: 'Echec de connexion. Login ou Mot de passe incorrect',
                                timeout: 50000,
                                type: 'alert'
                            });
                            $(".input-control").removeClass("info");
                            $(".prepend-icon").removeClass("fg-orange");
                            $(".input-control").addClass("error");
                            $(".prepend-icon").addClass("fg-red");
                        } else {
        <c:if test="${empty sessionScope.url}">
                            window.location.href = 'start';
        </c:if>
        <c:if test="${!empty sessionScope.url}">
                            window.location.href = '${sessionScope.url}';
        </c:if>
                        }
                        //window.location.href = 'start';
                    }
            );
        }
    </script>
</html>
