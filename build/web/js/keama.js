var audioElement = document.createElement('audio');
audioElement.setAttribute('src', 'whatsapp.mp3');
audioElement.setAttribute('autoplay', 'autoplay');
//audioElement.load()

//$.get();

audioElement.addEventListener("load", function () {
    //console.log("joue");
    //audioElement.play();
}, true);

function oscillerOne() {
    var i = 0;
    $(".oscille").fadeTo(700, 1, function () {
        $(".oscille").fadeTo(1000, 0.1, function () {
            i++;
            //console.log(i);
        });
    });
}
function oscillerTile() {
    setInterval(oscillerOne, 2000);
    $('.keama_fixe').animate({
        'background-position-x': '10%',
        'background-position-y': '20%'
    }, 10000, 'linear');
}

var i = 0;

function afficher_info() {
    var n = $(".keama_info").length;
    if (n == 0) {
        //console.log("Aucune information");
        $("#keama_spin").html("Aucune information");
        $("#keama_spin").hide();
        $(".keama_charg_infos").hide();
        arreteTout();
        $(".oscille").removeClass("oscille");

    } else {
        if (n == 1) {
            //console.log("nombre d'info : " + n);
            //console.log("numero de l'info : " + i);
            $(".keama_info:eq(" + 0 + ")").show("fast", function () {
                //audioElement.play();
                $(".keama_info:eq(" + 0 + ")").fadeTo(400, 0.7, function () {
                    $(".keama_info:eq(" + 0 + ")").fadeTo(400, 1, function () {
                        
                    });
                });
                $(".keama_charg_infos").hide();
                //$("#keama_spin").hide();
            });
            //console.log("numero suivant : " + i);
        } else {
            //console.log("nombre d'info : " + n);
            //console.log("numero de l'info : " + i);
            $(".keama_info").css("display","none");
            $(".keama_info:eq(" + i + ")").css("display","block");
            $(".keama_charg_infos").css("display","none");
            //audioElement.play();
            i++;
            if (i >= n) {
                i = 0;
            }
            //console.log("numero suivant : " + i);
        }


    }

}

function supprimerInfo(x) {
    console.log("muanza : " + x.parentElement.parentElement.className);
    x.parentElement.parentElement.className = "text-default";
    x.parentElement.parentElement.style.display = "none";
}

function keama_info() {
    $(".keama_info").hide();
    var n = $(".keama_info").length;
    if (n == 0) {

    } else {
        setInterval(afficher_info, 10000);
    }
}

function arreteTout() {
    for (var j = 1; j < 99999; j++) {
        window.clearInterval(j);
    }
}


