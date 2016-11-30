package Control;

import Model.Exemplar;
import Model.Publicacao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConsultarPublicacao extends HttpServlet {

    private String fonte, titulo;
    private int isbn;
    private List<Publicacao> publicacoes;
    private PublicacaoDao pDao;
    private List<Exemplar> exemplares;
    private ExemplarDao eDao;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<div align =\"center\">");
        try {
            fonte = request.getParameter("fonte");
            if (fonte.equals("isbn")) {
                isbn = Integer.parseInt(request.getParameter("isbn"));
                eDao = new ExemplarDao();
                exemplares = eDao.getExemplar(isbn);
                pDao = new PublicacaoDao();
                publicacoes = pDao.getPublicacao(isbn);

                out.println("<table border:1>");
                out.println("<tr>");
                out.println("<th>ISBN<th>");
                out.println("<th>Titulo<th>");
                out.println("<th>Numero<th>");
                out.println("<th>Situacao<th>");
                out.println("</tr>");
                for (Publicacao publicacao : publicacoes) {
                    for (Exemplar exemplar : exemplares) {
                        out.println("<tr>");
                        out.println("<td>" + isbn + "<td>");
                        out.println("<td>" + publicacao.getTitulo() + "<td>");
                        out.println("<td>" + exemplar.getNumero() + "<td>");
                        out.println("<td>" + exemplar.getSituacao() + "<td>");
                        out.println("</tr>");
                    }
                }
                out.println("<table>");
                out.println("<input type=\"button\" value=\"Voltar\" onClick=\"history.go(-2)\">");
            } else if (fonte.equals("titulo")) {
                titulo = request.getParameter("titulo");
                eDao = new ExemplarDao();
                exemplares = eDao.selectByTitulo(titulo);

                out.println("<table border:1>");
                out.println("<tr>");
                out.println("<th>ISBN<th>");
                out.println("<th>Titulo<th>");
                out.println("<th>Numero<th>");
                out.println("<th>Situacao<th>");
                out.println("</tr>");
                for (Exemplar exemplar : exemplares) {
                    out.println("<tr>");
                    out.println("<td>" + exemplar.getIsbn() + "<td>");
                    out.println("<td>" + titulo + "<td>");
                    out.println("<td>" + exemplar.getNumero() + "<td>");
                    out.println("<td>" + exemplar.getSituacao() + "<td>");
                    out.println("</tr>");
                }
                out.println("<table>");
                out.println("<input type=\"button\" value=\"Voltar\" onClick=\"history.go(-2)\">");
            }

        } catch (Exception ex) {
            out.println("Error: " + ex.getMessage());
            out.println("<input type=\"button\" value=\"Voltar\" onClick=\"history.go(-1)\">");
        }
        out.println("</div>");
    }
}
