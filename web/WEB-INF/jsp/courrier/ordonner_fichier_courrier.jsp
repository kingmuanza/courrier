<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ordonner</title>
        <script>
            var options = {};
            var viewer = new Viewer(document.getElementById('images'), options);

        </script>
    </head>
    <body>
        <div id="keama_back" class="grid" style="height: 100%; margin-top: -0px">

            <div class="row cells6" style="height: 100%; padding-left: 20px; padding-top: 20px; padding-right: 20px;">
                <h1>
                    Réorganiser les images du courrier : ${courrier.ref}
                    <span class="place-right icon mif-checkmark fg-green" style="cursor: pointer;"></span>
                </h1>
            </div>
            <!-- <c:forEach items="${courrierFichiers}" var="cf" varStatus="i">
                <c:if test="${i.index%6==0}">
                    <div class="row cells6 padding20">
                </c:if>

                <div id="index${i.count}" class="cell padding5" style="border : 2px solid #000;" ondrop="drop(event, '${i.count}')" ondragover="allowDrop(event)">
                    <img src="Courriers/${cf.chemin}" id="${cf.idcourrierFichier}" alt="" draggable="true" ondragstart="drag(event, 'index${i.count}')"/>
                                           <p class="bg-green">
                                                Ordre : ${cf.ordre}
                                            </p>
                                            <p class="bg-blue">
                                                Index : ${i.count}
                                            </p>

                </div>

                <c:if test="${i.index%6==5}">
                </div>
                </c:if>
            </c:forEach>-->

            <div class="row ribbed-grayLighter">
                <div class="docs-galley ">
                    <ul id="images" class="docs-pictures clearfix">
                        <c:forEach items="${courrier.courrierFichiers}" var="cf">
                            <li><img data-original="img/qrcode.JPG" src="Courriers/${cf.chemin}" alt="Picture"></li>
                            </c:forEach>
                    </ul>
                </div>
            </div>

            <div class="row cells6 padding20" style="height: 100%">
                <button class="command-button bg-green fg-white">
                    <span class="icon mif-checkmark"></span>
                    Terminé
                    <small>Les fichiers sont dans l'ordre</small>
                </button>
            </div>
        </div>
    </body>
</html>
