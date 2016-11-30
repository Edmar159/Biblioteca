<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
        <title>Cadastrar Associado</title>
    </head>
    <body align="center">

        <c:choose>
            <c:when test="${ associado eq null }">
                <jsp:forward page="erroLogin.jsp" />
            </c:when>
            <c:otherwise>
                <h2>Bem-vindo ${ associado.nome }</h2>  
            </c:otherwise>
        </c:choose>
        <h3>Dados do Associado</h3>
        <form method = "post" action = "/Biblioteca/CadastrarAssociado">
            <p>Código: <input type = "text" name = "codigo" size = "11"></p>
            <p>Nome: <input type = "text" name = "nome" size = "30"></p>
            <p>Senha: <input type = "password" name = "senha" size = "12"></p>
            <p>Email: <input type = "text" name = "email" size = "40"></p>
            <p>Endereço: <input type = "text" name = "endereco" size = "50"></p>
            <p>Tipo: </p>
            <p>
                <input type="radio" name="tipo" value="Grad" checked> Aluno de graduação<br>
                <input type="radio" name="tipo" value="Posgrad"> Aluno de pós-graduação<br>
                <input type="radio" name="tipo" value="Prof"> professor 
            </p>
            <p>
                <input type = "submit" name = "Submit" value = "Submit">
                <input type = "reset" value = "Reset">
                <input type="button" value="Voltar" onClick="history.go(-1)">
            </p>
        </form>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>
