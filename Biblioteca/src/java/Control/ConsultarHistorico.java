package Control;

import Model.Exemplar;
import Model.Publicacao;
import Model.Historico;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Model.Associado;
import Model.Emprestimo;
import java.text.ParseException;
import java.util.Calendar;

public class ConsultarHistorico extends HttpServlet {

    private List<Historico> historicos;
    private HistoricoDao hDao;
    private List<Emprestimo> emprestimos;
    private EmprestimoDao eDao;
    private Exemplar exemplar;
    private ExemplarDao exDao;
    private Publicacao publicacao;
    private PublicacaoDao pDao;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession(); //obtem a sessao do usuario, caso exista
        Associado associ = (Associado) session.getAttribute("associado");
        PrintWriter out = response.getWriter();
        out.println("<div align =\"center\">");
        try {
            eDao = new EmprestimoDao();
            emprestimos = eDao.getEmps(associ.getCodigo());
            out.println("<h3>Exemplares Não Devolvidos</h3>");
            out.println("<table border:1>");
            out.println("<tr>");
            out.println("<th>Titulo<th>");
            out.println("<th>ISBN<th>");
            out.println("<th>Numero do Exemplar<th>");
            out.println("<th>Data de Emprestimo<th>");
            out.println("<th>Dias em Atraso ou Restantes<th>");
            out.println("</tr>");
            for (Emprestimo emprestimo : emprestimos) {
                pDao = new PublicacaoDao();
                publicacao = pDao.getPubli(emprestimo.getIsbn());
                out.println("<tr>");
                out.println("<td>" + publicacao.getTitulo() + "<td>");
                out.println("<td>" + publicacao.getIsbn() + "<td>");
                out.println("<td>" + emprestimo.getNumero() + "<td>");
                out.println("<td>" + emprestimo.getData_emprestimo() + "<td>");
                if (associ.getTipo().equals("Grad")) {
                    int dias = 7;
                    long atraso = calculaAtraso(emprestimo.getData_emprestimo());
                    if ((dias - atraso) < 0) {
                        out.println("<td>" + (atraso - dias) + " Dias em atraso<td>");
                        out.println("</tr>");
                    } else {
                        out.println("<td>" + (dias - atraso) + " Dias restantes<td>");
                        out.println("</tr>");
                    }
                } else if (associ.getTipo().equals("Posgrad")) {
                    int dias = 10;
                    long atraso = calculaAtraso(emprestimo.getData_emprestimo());
                    if ((dias - atraso) < 0) {
                        out.println("<td>" + (atraso - dias) + " Dias em atraso<td>");
                        out.println("</tr>");
                    } else {
                        out.println("<td>" + (dias - atraso) + " Dias restantes<td>");
                        out.println("</tr>");
                    }
                } else if (associ.getTipo().equals("Prof")) {
                    int dias = 14;
                    long atraso = calculaAtraso(emprestimo.getData_emprestimo());
                    if ((dias - atraso) < 0) {
                        out.println("<td>" + (dias - atraso) + " Dias em atraso<td>");
                        out.println("</tr>");
                    } else {
                        out.println("<td>" + (atraso - dias) + " Dias restantes<td>");
                        out.println("</tr>");
                    }
                }
            }
            out.println("<table>");

            hDao = new HistoricoDao();
            historicos = hDao.getHistorico(associ.getCodigo());
            out.println("<h3>Exemplares Devolvidos</h3>");
            out.println("<table border:1>");
            out.println("<tr>");
            out.println("<th>Titulo<th>");
            out.println("<th>ISBN<th>");
            out.println("<th>Numero do Exemplar<th>");
            out.println("<th>Data deEmprestimo<th>");
            out.println("<th>Data de Devolucao<th>");
            out.println("</tr>");
            for (Historico historico : historicos) {
                pDao = new PublicacaoDao();
                publicacao = pDao.getPubli(historico.getIsbn());
                out.println("<tr>");
                out.println("<td>" + publicacao.getTitulo() + "<td>");
                out.println("<td>" + publicacao.getIsbn() + "<td>");
                out.println("<td>" + historico.getNumero() + "<td>");
                out.println("<td>" + historico.getData_emprestimo() + "<td>");
                out.println("<td>" + historico.getData_devolucao() + "<td>");
                out.println("</tr>");
            }
            out.println("<table>");

        } catch (Exception ex) {
            out.println("Error: " + ex.getMessage());
            out.println("<input type=\"button\" value=\"Voltar\" onClick=\"history.go(-1)\">");
        }
        out.println("</div>");
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
