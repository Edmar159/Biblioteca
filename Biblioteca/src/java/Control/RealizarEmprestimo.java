package Control;

import Model.Emprestimo;
import Model.Exemplar;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RealizarEmprestimo extends HttpServlet {

    private String situacao;
    private int isbn, numero;
    private Emprestimo instancia;
    private EmprestimoDao iDao;
    private Exemplar exemplar;
    private ExemplarDao eDao;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        // Obtem os dados do formulário
        instancia = new Emprestimo();
        instancia.setIsbn(Integer.parseInt(request.getParameter("isbn")));
        instancia.setNumero(Integer.parseInt(request.getParameter("numero")));
        instancia.setCodigo(Integer.parseInt(request.getParameter("codigo")));
        instancia.setData_emprestimo(request.getParameter("data"));
        try {
            isbn = instancia.getIsbn();
            numero = instancia.getNumero();

            eDao = new ExemplarDao();
            exemplar = eDao.selectExemplar(isbn, numero);
            situacao = exemplar.getSituacao();
            if (situacao.equals("disponivel")) {
                iDao = new EmprestimoDao();
                iDao.empresta(instancia);
                eDao.setSituacao(exemplar, "emprestado");
                out.println("Empréstimo feito com sucesso");
                out.println("<input type=\"button\" value=\"Voltar\" onClick=\"history.go(-2)\">");
            } else if (situacao.equals("emprestado")) {
                out.println("Exemplar ja está emprestado");
                out.println("<input type=\"button\" value=\"Voltar\" onClick=\"history.go(-1)\">");
            }
        } catch (Exception ex) {
            out.println("Error: " + ex.getMessage());
            out.println("<input type=\"button\" value=\"Voltar\" onClick=\"history.go(-1)\">");
        }
    }

}
