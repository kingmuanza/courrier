<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>MUANZA</title>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

        <style>
            .draggable { width: 150px; height: 150px; padding: 0.5em; border : 2px solid #000}
            .drag_img { width: 100px; height: 100px; }
        </style>
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <script>
            $(function () {
                $('#drag').draggable();
                $('#not-drag').draggable();

                $('#drop').droppable({
                    accept: '#drag', // je n'accepte que le bloc ayant "drag" pour id
                    drop: function (ev) {
                       console.log("ID du receveur : " + $(this).attr('id'));
                       console.log(ev);
                    },
                    over: function (event) {
                        $('#drop').css("border","5px solid #888");
                    }
                });
            });
        </script>
    </head>
    <body>

        <div class="draggable" id="muan">
            <img id="drag" src="img/pp/1.JPG" class="drag_img">
        </div>

        <div id="not-drag" class="draggable">
            <p>Ceci n'est pas un élément valide</p>
        </div>

        <div id="drop" class="draggable">

        </div>


    </body>
</html>