
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
        <title>Login Biblioteca</title>
    </head>
    <body>
        <div align="right">
            <a href="/Biblioteca/loginfunc.jsp"> Acesso para funcionários </a>
        </div>
        <div align="center">
        <h3>Login</h3>
        <form method = "post" action = "/Biblioteca/LoginAuthentication">
            <p>Código: <input type = "text" name = "codigo" size = "11"></p>
            <p>Senha: <input type = "password" name = "senha" size = "12"></p>
            <input type="hidden" name="flag" value="1">
            <p>
                <input type = "submit" name = "Submit" value = "Submit">
                <input type = "reset" value = "Reset">
            </p>
        </form>
        </div>
        
        <script src="js/bootstrap.min.js"></script>
        </body>
</html>

