<html lang="en">
<head>
    <title>Sign In</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, appUser-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
</head>


<form action="/signIn" method="post">
    <div class="form-group">
        <label for="email">username</label>
        <input type="email" name="email" id="email">
    </div>
    <div class="form-group">
        <label for="password">Password</label>
        <input type="password" name="password" id="password">
    </div>
    <div class="form-group">
        <button type="submit">Sign in</button>
    </div>
</form>


</html>