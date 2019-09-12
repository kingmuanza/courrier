<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Entreprise</title>
        <script src="js/jquery-qrcode-0.14.0.js" type="text/javascript"></script>
    </head>
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
        $("#streamer").height(restant);
        //$("#streamer").attr('data-meter-interval',30);

        function showDialog(id) {
            var dialog = $(id).data('dialog');
            dialog.open();
            //$("#keama_back").css("opacity", "0.2");

        }
    </script>

    <body class="grid ribbed-grayLighter padding20" style="">
        <div class="padding20" style="padding-bottom: 20px;">
            <div class="row cells2" style="padding-bottom: 20px; margin-top: -10px;">
                <div class="cell">
                    <div>Cette semaine</div>
                    <fmt:setLocale value="fr" />
                    <fmt:formatDate value="${lundi}" type="date" dateStyle="long" var="debut" />
                    <fmt:formatDate value="${dimanche}" type="date" dateStyle="long" var="fin" />
                    <small>Du ${debut} au ${fin}</small>
                </div>
            </div>
            <div id="streamer" class="bg-white streamer" data-role="streamer" data-scroll-bar="true" data-slide-to-time="10:20" data-slide-speed="500">
                <div class="streams">
                    <div class="streams-title">
                        <div class="toolbar streamer-toolbar">
                            <button class="toolbar-button js-show-all-streams" title="Show all streams"><span class="mif-event-available"></span></button>
                            <button class="toolbar-button js-schedule-mode" title="On|Off schedule mode"><span class="mif-history"></span></button>
                            <button class="toolbar-button js-go-previous-time" title="Go to previous time interval"><span class="mif-previous"></span></button>
                            <button class="toolbar-button js-go-next-time" title="Go to next time interval"><span class="mif-next"></span></button>
                        </div>
                    </div>
                    <div class="stream bg-teal">
                        <div class="stream-title">LUNDI</div>
                        <div class="stream-number"></div>
                    </div>
                    <div class="stream bg-orange">
                        <div class="stream-title">MARDI</div>
                        <div class="stream-number"></div>
                    </div>
                    <div class="stream bg-lightBlue">
                        <div class="stream-title">MERCREDI</div>
                        <div class="stream-number"></div>
                    </div>
                    <div class="stream bg-darkGreen">
                        <div class="stream-title">JEUDI</div>
                        <div class="stream-number"></div>
                    </div>
                    <div class="stream bg-pink">
                        <div class="stream-title">VENDREDI</div>
                        <div class="stream-number"></div>
                    </div>
                    <div class="stream bg-violet">
                        <div class="stream-title">SAMEDI</div>
                        <div class="stream-number"></div>
                    </div>
                </div>

                <div class="events">
                    <div class="events-area">
                        <div class="events-grid">
                            <div class="event-group">
                                <div class="event-stream" id="lundi">
                                    <c:forEach var="i" begin="0" end="9" step="1">
                                        <c:set var="oui" value="${0}"/>
                                        <c:forEach items="${reunionLundi}" var="r">
                                            <fmt:formatDate pattern="HH" value="${r.dateDebut}" var="heure_debut"/>
                                            <fmt:formatDate pattern="HH:mm" value="${r.dateDebut}" var="hm"/>
                                            <fmt:parseNumber var="debut" type="number" value="${heure_debut}" />
                                            <c:if test="${debut >= 8+i && debut < 9+i}">
                                                <c:set var="oui" value="${1}"/>
                                                <div class="event" data-role="tile" onclick="window.location.href = 'reunion#/id/${r.idreunion}'">
                                                    <div class="event-content live-slide">
                                                        <div class="event-content-logo">
                                                            <img class="icon" src="img/reunion.jpg">
                                                            <div class="time">${hm}</div>
                                                        </div>
                                                        <div class="event-content-data">
                                                            <div class="title">${r.titre}</div>
                                                            <div class="subtitle">${r.employe.noms}</div>
                                                            <div class="remark">${r.description}</div>
                                                        </div>
                                                    </div>
                                                    <!--                                                    <div class="event-content live-slide">
                                                                                                            <div class="event-content-logo">
                                                                                                                <img class="icon" src="img/reunion.jpg">
                                                                                                                <div class="time">${hm}</div>
                                                                                                            </div>
                                                                                                            <div class="event-content-data">
                                                                                                                <div class="title">${r.titre}</div>
                                                                                                                <div class="subtitle">${r.employe.noms}</div>
                                                                                                                <div class="remark">${r.description}</div>
                                                                                                            </div>
                                                                                                        </div>-->
                                                </div>
                                            </c:if>
                                        </c:forEach>
                                        <c:if test="${oui==0}">
                                            <c:set var="oui" value="${0}"/>
                                            <div class="event"></div>
                                        </c:if>
                                    </c:forEach>
                                </div>
                                <div class="event-stream" id="mardi">
                                    <c:forEach var="i" begin="0" end="9" step="1">
                                        <c:set var="oui" value="${0}"/>
                                        <c:forEach items="${reunionMardi}" var="r">
                                            <fmt:formatDate pattern="HH" value="${r.dateDebut}" var="heure_debut"/>
                                            <fmt:formatDate pattern="HH:mm" value="${r.dateDebut}" var="hm"/>
                                            <fmt:parseNumber var="debut" type="number" value="${heure_debut}" />
                                            <c:if test="${debut >= 8+i && debut < 9+i}">
                                                <c:set var="oui" value="${1}"/>
                                                <div class="event" data-role="tile" onclick="window.location.href = 'reunion#/id/${r.idreunion}'">
                                                    <div class="event-content live-slide">
                                                        <div class="event-content-logo">
                                                            <img class="icon" src="img/reunion.jpg">
                                                            <div class="time">${hm}</div>
                                                        </div>
                                                        <div class="event-content-data">
                                                            <div class="title">${r.titre}</div>
                                                            <div class="subtitle">${r.employe.noms}</div>
                                                            <div class="remark">${r.description}</div>
                                                        </div>
                                                    </div>
                                                    <!--                                                    <div class="event-content live-slide">
                                                                                                            <div class="event-content-logo">
                                                                                                                <img class="icon" src="img/reunion.jpg">
                                                                                                                <div class="time">${hm}</div>
                                                                                                            </div>
                                                                                                            <div class="event-content-data">
                                                                                                                <div class="title">${r.titre}</div>
                                                                                                                <div class="subtitle">${r.employe.noms}</div>
                                                                                                                <div class="remark">${r.description}</div>
                                                                                                            </div>
                                                                                                        </div>-->
                                                </div>
                                            </c:if>
                                        </c:forEach>
                                        <c:if test="${oui==0}">
                                            <c:set var="oui" value="${0}"/>
                                            <div class="event"></div>
                                        </c:if>
                                    </c:forEach>
                                </div>
                                <div class="event-stream" id="mercredi">
                                    <c:forEach var="i" begin="0" end="9" step="1">
                                        <c:set var="oui" value="${0}"/>
                                        <c:forEach items="${reunionMercredi}" var="r">
                                            <fmt:formatDate pattern="HH" value="${r.dateDebut}" var="heure_debut"/>
                                            <fmt:formatDate pattern="HH:mm" value="${r.dateDebut}" var="hm"/>
                                            <fmt:parseNumber var="debut" type="number" value="${heure_debut}" />
                                            <c:if test="${debut >= 8+i && debut < 9+i}">
                                                <c:set var="oui" value="${1}"/>
                                                <div class="event" data-role="tile" onclick="window.location.href = 'reunion#/id/${r.idreunion}'">
                                                    <div class="event-content live-slide">
                                                        <div class="event-content-logo">
                                                            <img class="icon" src="img/reunion.jpg">
                                                            <div class="time">${hm}</div>
                                                        </div>
                                                        <div class="event-content-data">
                                                            <div class="title">${r.titre}</div>
                                                            <div class="subtitle">${r.employe.noms}</div>
                                                            <div class="remark">${r.description}</div>
                                                        </div>
                                                    </div>
                                                    <!--                                                    <div class="event-content live-slide">
                                                                                                            <div class="event-content-logo">
                                                                                                                <img class="icon" src="img/reunion.jpg">
                                                                                                                <div class="time">${hm}</div>
                                                                                                            </div>
                                                                                                            <div class="event-content-data">
                                                                                                                <div class="title">${r.titre}</div>
                                                                                                                <div class="subtitle">${r.employe.noms}</div>
                                                                                                                <div class="remark">${r.description}</div>
                                                                                                            </div>
                                                                                                        </div>-->
                                                </div>
                                            </c:if>
                                        </c:forEach>
                                        <c:if test="${oui==0}">
                                            <c:set var="oui" value="${0}"/>
                                            <div class="event"></div>
                                        </c:if>
                                    </c:forEach>
                                </div>

                                <div class="event-stream" id="jeudi">
                                    <c:forEach var="i" begin="0" end="9" step="1">
                                        <c:set var="oui" value="${0}"/>
                                        <c:forEach items="${reunionJeudi}" var="r">
                                            <fmt:formatDate pattern="HH" value="${r.dateDebut}" var="heure_debut"/>
                                            <fmt:formatDate pattern="HH:mm" value="${r.dateDebut}" var="hm"/>
                                            <fmt:parseNumber var="debut" type="number" value="${heure_debut}" />
                                            <c:if test="${debut >= 8+i && debut < 9+i}">
                                                <c:set var="oui" value="${1}"/>
                                                <div class="event" data-role="tile" onclick="window.location.href = 'reunion#/id/${r.idreunion}'">
                                                    <div class="event-content live-slide">
                                                        <div class="event-content-logo">
                                                            <img class="icon" src="img/reunion.jpg">
                                                            <div class="time">${hm}</div>
                                                        </div>
                                                        <div class="event-content-data">
                                                            <div class="title">${r.titre}</div>
                                                            <div class="subtitle">${r.employe.noms}</div>
                                                            <div class="remark">${r.description}</div>
                                                        </div>
                                                    </div>
                                                    <!--                                                    <div class="event-content live-slide">
                                                                                                            <div class="event-content-logo">
                                                                                                                <img class="icon" src="img/reunion.jpg">
                                                                                                                <div class="time">${hm}</div>
                                                                                                            </div>
                                                                                                            <div class="event-content-data">
                                                                                                                <div class="title">${r.titre}</div>
                                                                                                                <div class="subtitle">${r.employe.noms}</div>
                                                                                                                <div class="remark">${r.description}</div>
                                                                                                            </div>
                                                                                                        </div>-->
                                                </div>
                                            </c:if>
                                        </c:forEach>
                                        <c:if test="${oui==0}">
                                            <c:set var="oui" value="${0}"/>
                                            <div class="event"></div>
                                        </c:if>
                                    </c:forEach>
                                </div>

                                <div class="event-stream" id="vendredi">
                                    <c:forEach var="i" begin="0" end="9" step="1">
                                        <c:set var="oui" value="${0}"/>
                                        <c:forEach items="${reunionVendredi}" var="r">
                                            <fmt:formatDate pattern="HH" value="${r.dateDebut}" var="heure_debut"/>
                                            <fmt:formatDate pattern="HH:mm" value="${r.dateDebut}" var="hm"/>
                                            <fmt:parseNumber var="debut" type="number" value="${heure_debut}" />
                                            <c:if test="${debut >= 8+i && debut < 9+i}">
                                                <c:set var="oui" value="${1}"/>
                                                <div class="event" data-role="tile" onclick="window.location.href = 'reunion#/id/${r.idreunion}'">
                                                    <div class="event-content live-slide">
                                                        <div class="event-content-logo">
                                                            <img class="icon" src="img/reunion.jpg">
                                                            <div class="time">${hm}</div>
                                                        </div>
                                                        <div class="event-content-data">
                                                            <div class="title">${r.titre}</div>
                                                            <div class="subtitle">${r.employe.noms}</div>
                                                            <div class="remark">${r.description}</div>
                                                        </div>
                                                    </div>
                                                    <!--                                                    <div class="event-content live-slide">
                                                                                                            <div class="event-content-logo">
                                                                                                                <img class="icon" src="img/reunion.jpg">
                                                                                                                <div class="time">${hm}</div>
                                                                                                            </div>
                                                                                                            <div class="event-content-data">
                                                                                                                <div class="title">${r.titre}</div>
                                                                                                                <div class="subtitle">${r.employe.noms}</div>
                                                                                                                <div class="remark">${r.description}</div>
                                                                                                            </div>
                                                                                                        </div>-->
                                                </div>
                                            </c:if>
                                        </c:forEach>
                                        <c:if test="${oui==0}">
                                            <c:set var="oui" value="${0}"/>
                                            <div class="event"></div>
                                        </c:if>
                                    </c:forEach>
                                </div>

                                <div class="event-stream" id="samedi">
                                    <c:forEach var="i" begin="0" end="9" step="1">
                                        <c:set var="oui" value="${0}"/>
                                        <c:forEach items="${reunionSamedi}" var="r">
                                            <fmt:formatDate pattern="HH" value="${r.dateDebut}" var="heure_debut"/>
                                            <fmt:formatDate pattern="HH:mm" value="${r.dateDebut}" var="hm"/>
                                            <fmt:parseNumber var="debut" type="number" value="${heure_debut}" />
                                            <c:if test="${debut >= 8+i && debut < 9+i}">
                                                <c:set var="oui" value="${1}"/>
                                                <div class="event" data-role="tile" onclick="window.location.href = 'reunion#/id/${r.idreunion}'">
                                                    <div class="event-content live-slide">
                                                        <div class="event-content-logo">
                                                            <img class="icon" src="img/reunion.jpg">
                                                            <div class="time">${hm}</div>
                                                        </div>
                                                        <div class="event-content-data">
                                                            <div class="title">${r.titre}</div>
                                                            <div class="subtitle">${r.employe.noms}</div>
                                                            <div class="remark">${r.description}</div>
                                                        </div>
                                                    </div>
                                                    <!--                                                    <div class="event-content live-slide">
                                                                                                            <div class="event-content-logo">
                                                                                                                <img class="icon" src="img/reunion.jpg">
                                                                                                                <div class="time">${hm}</div>
                                                                                                            </div>
                                                                                                            <div class="event-content-data">
                                                                                                                <div class="title">${r.titre}</div>
                                                                                                                <div class="subtitle">${r.employe.noms}</div>
                                                                                                                <div class="remark">${r.description}</div>
                                                                                                            </div>
                                                                                                        </div>-->
                                                </div>
                                            </c:if>
                                        </c:forEach>
                                        <c:if test="${oui==0}">
                                            <c:set var="oui" value="${0}"/>
                                            <div class="event"></div>
                                        </c:if>
                                    </c:forEach>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
            
        </div>
    </div>
</body>
</html>
