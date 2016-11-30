package Control;

import Model.Associado;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CadastrarAssociado extends HttpServlet {

    private Associado associado;
    private AssociadoDao dao;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        // Obtem os dados do formulário
        associado = new Associado();
        associado.setCodigo(Integer.parseInt(request.getParameter("codigo")));
        associado.setNome(request.getParameter("nome"));
        associado.setEndereco(request.getParameter("endereco"));
        associado.setEmail(request.getParameter("email"));
        associado.setSenha(request.getParameter("senha"));
        associado.setTipo(request.getParameter("tipo"));
        //Adiciona associado no banco
        try {
            dao = new AssociadoDao();
            dao.adiciona(associado);
            out.println("Usuário " + associado.getNome() + " está agora registrado na base de dados");
            out.println("<input type=\"button\" value=\"Voltar\" onClick=\"history.go(-2)\">");
        } catch (Exception ex) {
            out.println("Error: " + ex.getMessage());
            out.println("<input type=\"button\" value=\"Voltar\" onClick=\"history.go(-1)\">");
        }
    }
}
