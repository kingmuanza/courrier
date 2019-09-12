<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html> 
    <head> 
        <title>Organigramme</title>	
        <!--<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>-->	
        <script src="js/jquery.js" type="text/javascript"></script>
        <link href="css/getorgchart.css" rel="stylesheet" type="text/css"/>
        <script src="js/getorgchart.js" type="text/javascript"></script>

        <style type="text/css">
            html, body {margin: 0px; padding: 0px;width: 100%;height: 100%;overflow: hidden; }
            #people {width: 100%;height: 100%; }   
            div.get-org-chart .get-text
            {
                font-size: 25px !important;
            }
        </style>
    </head>
    <body>    
        <div id="people"></div>
        <script type="text/ecmascript">
            $("#people").getOrgChart({ 
                theme: "annabel" ,
                color: "blue",
                enableEdit: false,
                enableDetailsView: false,
                enablePrint: true,
                linkType: "M",
                levelSeparation: 200,
                siblingSeparation: 200,
                enableGridView: true,
                enableMove: true
            });
            <c:if test="${!empty poste}" >
                <c:forEach items="${poste.employes}" var="employe">
            $("#people").getOrgChart("createPerson", 
                    ${poste.idposte}, 
                    ${empty poste.poste ? "null" : poste.poste.idposte}, 
                    {
                        Name: "${employe.noms}", 
                        Image:"${employe.imageLogo.chemin}", 
                        Title: "${poste.libelle}"
                    }
                );
                </c:forEach>
            </c:if>
            <c:forEach items="${postes}" var="poste">
                <c:forEach items="${poste.employes}" var="employe">
                $("#people").getOrgChart("createPerson", 
                    ${poste.idposte}, 
                    ${empty poste.poste ? "null" : poste.poste.idposte}, 
                    {
                        Name: "${employe.noms}", 
                        Image:"${employe.imageLogo.chemin}", 
                        Title: "${poste.libelle}"
                    }
                );
        
                </c:forEach>
            </c:forEach>
            $("#people").getOrgChart("draw");
        </script>	
    </body>
</html>
