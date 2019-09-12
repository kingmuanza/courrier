<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html> 
    <head> 
        <title>Organigramme</title>	
<!--        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>	-->
        <script src="js/jquery.js" type="text/javascript"></script>
        <link href="css/getorgchart.css" rel="stylesheet" type="text/css"/>
        <script src="js/getorgchart.js" type="text/javascript"></script>

        <style type="text/css">
            html, body {margin: 0px; padding: 0px;width: 100%;height: 100%;overflow: hidden; }
            #people {width: 100%;height: 100%; }   
        </style>
    </head>
    <body>    
        <div id="people"></div>
        <script type="text/ecmascript">
            $("#people").getOrgChart({ 
                theme: "annabel" ,
                color: "blue",
                enableGridView: true,
                enableMove: true
            });
            //$("#people").getOrgChart("createPerson", ${poste.idposte}, null, {Name: "<c:forEach items="${poste.employes}" var="e" end="0">${e.noms}</c:forEach>", Title: "${poste.libelle}", Phone: "079 0346 5377", Image:"img/pp.png"});
            <c:forEach items="${postes}" var="poste">
                $("#people").getOrgChart("createPerson", 
                ${poste.idposte}, 
                ${empty poste.poste ? "null" : poste.poste.idposte}, 
                    {
                        Name: "<c:forEach items="${poste.employes}" var="e" end="0">${e.noms}</c:forEach>", 
                        Image:"<c:forEach items="${poste.employes}" var="e" end="0">${e.imageLogo.chemin}</c:forEach>", 
                        Title: "${poste.libelle}", 
                        Phone: "079 0346 5377"
                    }
                );
        
            </c:forEach>
            $("#people").getOrgChart("draw");
        </script>	
    </body>
</html>
