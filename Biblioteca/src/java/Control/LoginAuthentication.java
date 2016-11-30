package Control;

import Model.Associado;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

public class LoginAuthentication extends HttpServlet {

    private String senha;
    private int codigo, flag;
    private Associado associado;
    private AssociadoDao dao;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(); //obtem a sessao do usuario, caso exista

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        try {
            codigo = Integer.parseInt(request.getParameter("codigo"));
            senha = request.getParameter("senha");
            flag = Integer.parseInt(request.getParameter("flag"));
            dao = new AssociadoDao();
            associado = dao.login(codigo, senha);
            if (flag == 1 && associado.getTipo().equals("funcionario")) {

                out.println("Área reservada para login de Associado");
                out.println("<a href=\"/Biblioteca/loginfunc.jsp\"> Continuar");
            } else if (flag == 2 && !associado.getTipo().equals("funcionario")) {

                out.println("Área reservada para login de Funcionario");
                out.println("<a href=\"/Biblioteca/login.jsp\"> Continuar");
            } else if (associado.getCodigo() == codigo) {
                session.setAttribute("associado", associado);
                RequestDispatcher r = request.getRequestDispatcher("/index.jsp");
                r.forward(request, response);

            } else {
                session.invalidate();
                out.println("Não existe usuário com o código <b>" + codigo + "</b> ou a senha está incorreta ");
                out.println("<a href='login.jsp'><br>Tente Novamente</a>");
            }
        } catch (Exception ex) {
            out.println("Usuário e/ou senha inválidos");
            out.println("<input type=\"button\" value=\"Voltar\" onClick=\"history.go(-1)\">");
        }
    }
}
