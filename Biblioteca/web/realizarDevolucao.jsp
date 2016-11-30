<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
        <title>Realizar Empréstimo</title>
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
    <h3>Dados do Empréstimo</h3>
    <form method = "post" action = "/Biblioteca/RealizarDevolucao">
        <p>ISBN: <input type = "text" name = "isbn" size = "15"></p>
        <p>Numero do Exemplar: <input type = "text" name = "numero" size = "10"></p>
        <p>Data de Devolução: <input type = "text" pattern="\d{1,2}/\d{1,2}/\d{4}" name = "dataDevolucao" size = "10"></p>
        <p>
            <input type = "submit" name = "Submit" value = "Submit">
            <input type = "reset" value = "Reset">
            <input type="button" value="Voltar" onClick="history.go(-1)">
        </p>
    </form>
    <script src="js/bootstrap.min.js"></script>
</body>
</html>
