<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Create a new match</title>
</head>
<body>

<form action="new-match" method="post">

    <label>
        Имя игрока 1
        <input name="player1" required>
    </label>
    <br>
    <label>
        Имя игрока 2
        <input name="player2" required>
    </label>
    <br>
    <label>
        Количество сетов
        <select required name="setsCount">
            <option>3</option>
            <option>5</option>
        </select>
    </label>
    <input type="submit" value="Начать">

</form>

</body>
</html>
