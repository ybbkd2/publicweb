<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Login</h1>


        <form action="system_login.do" method="post">
            Account：<Input name="t_username"/><br/>
            Password：<input name="t_password" type="password"/><br/>
            <input value="submit" type="submit"/>
        </form>

    </body>
</html>
