<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Login</h1>


        <form action="j_spring_security_check" method="post">
            Account：<Input name="j_username"/><br/>
            Password：<input name="j_password" type="password"/><br/>
            <input value="submit" type="submit"/>
        </form>

    </body>
</html>
