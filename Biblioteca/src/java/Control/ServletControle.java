/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Model.Associado;

public class ServletControle extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        Associado login = new Associado();
        login = (Associado) session.getAttribute("associado");

        String opcao = request.getParameter("opcao");
        out.println(opcao);
        if (opcao.equals("associado")) {
            RequestDispatcher r = request.getRequestDispatcher("/cadastrarAssociado.jsp");
            r.forward(request, response);

        } else if (opcao.equals("exemplar")) {
            RequestDispatcher r = request.getRequestDispatcher("/cadastrarExemplar.jsp");
            r.forward(request, response);

        } else if (opcao.equals("publicacao")) {
            RequestDispatcher r = request.getRequestDispatcher("/cadastrarPublicacao.jsp");
            r.forward(request, response);

        } else if (opcao.equals("conPublicacao")) {
            RequestDispatcher r = request.getRequestDispatcher("/consultarPublicacao.jsp");
            r.forward(request, response);

        } else if (opcao.equals("devolucao")) {
            RequestDispatcher r = request.getRequestDispatcher("/realizarDevolucao.jsp");
            r.forward(request, response);

        } else if (opcao.equals("emprestimo")) {
            RequestDispatcher r = request.getRequestDispatcher("/realizarEmprestimo.jsp");
            r.forward(request, response);

        } else if (opcao.equals("conHist")) {
            RequestDispatcher r = request.getRequestDispatcher("/ConsultarHistorico");
            r.forward(request, response);

        } else if (opcao.equals("relatorio")) {
            RequestDispatcher r = request.getRequestDispatcher("/RelatorioAtrasados");
            r.forward(request, response);

        }
    }

}
