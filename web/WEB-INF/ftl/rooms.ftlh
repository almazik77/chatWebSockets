<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, appUser-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Rooms</title>
    <script
            src="https://code.jquery.com/jquery-3.4.1.js"
            integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
            crossorigin="anonymous"></script>

    <script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>

    <script>
        function makeId(length) {
            var result = '';
            var characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
            var charactersLength = characters.length;
            for (var i = 0; i < length; i++) {
                result += characters.charAt(Math.floor(Math.random() * charactersLength));
            }
            return result;
        }

        var newRoomId = makeId(10);

        function redirect(roomID) {
            window.location.href = 'http://localhost:8080/room/' + roomID;
        }
    </script>
</head>


<body>
<h1>email - ${email}</h1>
<h1>Rooms</h1>
<#list roomsId as roomId>
    <a href="/room/${roomId}" onclick="redirect('${roomId}','${email}')">Room ID - ${roomId}</a> <br>
</#list>
<button onclick="redirect(newRoomId, '${email}')">New Room</button>
</body>
</html>