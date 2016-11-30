package Control;

import Model.Publicacao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CadastrarPublicacao extends HttpServlet {

    private Publicacao publicacao;
    private PublicacaoDao dao;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        // Obtem os dados do formulário
        publicacao = new Publicacao();
        publicacao.setIsbn(Integer.parseInt(request.getParameter("isbn")));
        publicacao.setTitulo(request.getParameter("titulo"));
        publicacao.setAutor(request.getParameter("autor"));
        publicacao.setEditora(request.getParameter("editora"));
        publicacao.setAno(Integer.parseInt(request.getParameter("ano")));
        try {
            dao = new PublicacaoDao();
            dao.adiciona(publicacao);
            out.println("Publicação " + publicacao.getTitulo() + " está agora registrada na base de dados");
            out.println("<input type=\"button\" value=\"Voltar\" onClick=\"history.go(-2)\">");
        } catch (Exception ex) {
            out.println("Error: " + ex.getMessage());
            out.println("<input type=\"button\" value=\"Voltar\" onClick=\"history.go(-1)\">");
        }
    }
}
