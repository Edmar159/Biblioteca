package Control;

import Model.Exemplar;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CadastrarExemplar extends HttpServlet {

    private Exemplar exemplar;
    private ExemplarDao dao;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        // Obtem os dados do formulário
        exemplar = new Exemplar();
        exemplar.setIsbn(Integer.parseInt(request.getParameter("isbn")));
        exemplar.setNumero(Integer.parseInt(request.getParameter("numero")));
        exemplar.setPreco(Double.parseDouble(request.getParameter("preco")));
        exemplar.setSituacao(request.getParameter("situacao"));
        //Adiciona exemplar no banco
        try {
            dao = new ExemplarDao();
            dao.adiciona(exemplar);
            out.println("Exemplar " + exemplar.getNumero() + " está agora registrado na base de dados");
            out.println("<input type=\"button\" value=\"Voltar\" onClick=\"history.go(-2)\">");
        } catch (Exception ex) {
            out.println("Error: " + ex.getMessage());
            out.println("<input type=\"button\" value=\"Voltar\" onClick=\"history.go(-1)\">");
        }
    }
}
