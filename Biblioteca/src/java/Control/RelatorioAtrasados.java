package Control;

import Model.Associado;
import Model.Emprestimo;
import Model.Publicacao;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Calendar;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RelatorioAtrasados extends HttpServlet {

    private Associado associado;
    private AssociadoDao aDao;
    private List<Emprestimo> instancias;
    private EmprestimoDao iDao;
    private Publicacao publicacao;
    private PublicacaoDao pDao;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<div align=\"center\">");
        try {
            iDao = new EmprestimoDao();
            instancias = iDao.getAll();
            out.println("<h3>Exemplares não devolvidos</h3>");
            out.println("<table border:1>");
            out.println("<tr>");
            out.println("<th>Codigo do Usuário<th>");
            out.println("<th>Nome do Usuário<th>");
            out.println("<th>Título do Livro<th>");
            out.println("<th>ISBN<th>");
            out.println("<th>Número de Sequência<th>");
            out.println("<th>Data de Empréstimo<th>");
            out.println("</tr>");
            for (Emprestimo emprestimo : instancias) {
                aDao = new AssociadoDao();
                associado = aDao.getAssociado(emprestimo.getCodigo());
                pDao = new PublicacaoDao();
                publicacao = pDao.getPubli(emprestimo.getIsbn());
                if (associado.getTipo().equals("Grad")) {
                    int dias = 7;
                    long atraso = calculaAtraso(emprestimo.getData_emprestimo());
                    if ((atraso - dias) > 0) {
                        out.println("<tr>");
                        out.println("<td>" + associado.getCodigo() + "<td>");
                        out.println("<td>" + associado.getNome() + "<td>");
                        out.println("<td>" + publicacao.getTitulo() + "<td>");
                        out.println("<td>" + emprestimo.getIsbn() + "<td>");
                        out.println("<td>" + emprestimo.getNumero() + "<td>");
                        out.println("<td>" + emprestimo.getData_emprestimo() + "<td>");
                        out.println("</tr>");
                    }
                } else if (associado.getTipo().equals("Posgrad")) {
                    int dias = 10;
                    long atraso = calculaAtraso(emprestimo.getData_emprestimo());
                    if ((atraso - dias) > 0) {
                        out.println("<tr>");
                        out.println("<td>" + associado.getCodigo() + "<td>");
                        out.println("<td>" + associado.getNome() + "<td>");
                        out.println("<td>" + publicacao.getTitulo() + "<td>");
                        out.println("<td>" + emprestimo.getIsbn() + "<td>");
                        out.println("<td>" + emprestimo.getNumero() + "<td>");
                        out.println("<td>" + emprestimo.getData_emprestimo() + "<td>");
                        out.println("</tr>");
                    }
                } else if (associado.getTipo().equals("Prof")) {
                    int dias = 14;
                    long atraso = calculaAtraso(emprestimo.getData_emprestimo());
                    if ((atraso - dias) > 0) {
                        out.println("<tr>");
                        out.println("<td>" + associado.getCodigo() + "<td>");
                        out.println("<td>" + associado.getNome() + "<td>");
                        out.println("<td>" + publicacao.getTitulo() + "<td>");
                        out.println("<td>" + emprestimo.getIsbn() + "<td>");
                        out.println("<td>" + emprestimo.getNumero() + "<td>");
                        out.println("<td>" + emprestimo.getData_emprestimo() + "<td>");
                        out.println("</tr>");
                    }
                }
            }
            out.println("<table>");
            out.println("<input type=\"button\" value=\"Voltar\" onClick=\"history.go(-1)\">");
            out.println("</div>");
        } catch (Exception ex) {
            out.println("Error: " + ex.getMessage());
            out.println("<input type=\"button\" value=\"Voltar\" onClick=\"history.go(-1)\">");
        }
    }

    public long calculaAtraso(String dataEmprestimo) throws ParseException {
        long atraso;
        Calendar dtEmprestimo = Calendar.getInstance();
        Calendar dtDevolucao = Calendar.getInstance();
        String[] emp = dataEmprestimo.split("/");
        int diaE = Integer.parseInt(emp[0]);
        int mesE = Integer.parseInt(emp[1]) - 1;
        int anoE = Integer.parseInt(emp[2]);
        dtEmprestimo.set(anoE, mesE, diaE);
        atraso = dtDevolucao.getTimeInMillis() - dtEmprestimo.getTimeInMillis();
        return atraso / (24 * 60 * 60 * 1000);
    }
}
