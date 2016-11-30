<%-- 
    Document   : index
    Created on : 18/10/2016, 17:06:34
    Author     : Edmar
--%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
        <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
        <title>Biblioteca</title>
    </head>
    <body>
    <div align="center">
        <c:choose>
            <c:when test="${ associado eq null }">
                <jsp:forward page="erroLogin.jsp" />
            </c:when>
            <c:otherwise>
                <h2>Bem-vindo ${ associado.nome }</h2>  
            </c:otherwise>
        </c:choose>
   <form method = "post" action = "/Biblioteca/ServletControle">
        <h3>Operações</h3>
        <p>
            <c:choose>
            <c:when test="${ associado.tipo eq 'funcionario' }">
                        
                <input type="radio" name="opcao" value="associado">Cadastrar Associado<br>
                <input type="radio" name="opcao" value="exemplar">Cadastrar Exemplar<br>
                <input type="radio" name="opcao" value="publicacao">Cadastrar Publicação<br>
                <input type="radio" name="opcao" value="conPublicacao">Consultar Publicação<br>
                <input type="radio" name="opcao" value="devolucao">Realizar Devolução<br>
                <input type="radio" name="opcao" value="emprestimo">Realizar Emprestimo<br>
                <input type="radio" name="opcao" value="relatorio">Gerar Relatório de Associados em Atraso<br>
            </c:when>
            <c:otherwise>
                <input type="radio" name="opcao" value="conPublicacao">Consultar Publicação<br>
                <input type="radio" name="opcao" value="conHist">Consultar Histórico<br>
                
            </c:otherwise>
            </c:choose>  

                
        </p>
            <p>
                <input type = "submit" name = "Submit" value = "Ok">
                <input type="button" value="Voltar" onClick="history.go(-1)">
            </p>
        </form>
        <script src="js/bootstrap.min.js"></script>
    </div>
    </body>
</html>
