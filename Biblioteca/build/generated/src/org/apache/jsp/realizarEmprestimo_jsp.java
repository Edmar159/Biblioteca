package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class realizarEmprestimo_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        \n");
      out.write("        <link href=\"css/bootstrap.min.css\" rel=\"stylesheet\" media=\"screen\">\n");
      out.write("        <title>Realizar Empréstimo</title>\n");
      out.write("    </head>\n");
      out.write("    <body align=\"center\">\n");
      out.write("        <h3>Dados do Empréstimo</h3>\n");
      out.write("        <form method = \"post\" action = \"/Biblioteca/RealizarEmprestimo\">\n");
      out.write("            <p>ISBN: <input type = \"text\" name = \"isbn\" size = \"15\"></p>\n");
      out.write("            <p>Numero do Exemplar: <input type = \"text\" name = \"numero\" size = \"10\"></p>\n");
      out.write("            <p>Código do Associado: <input type = \"text\" name = \"codigo\" size = \"15\"></p>\n");
      out.write("            <p>Data do Empréstimo: <input type = \"text\" pattern=\"\\d{1,2}/\\d{1,2}/\\d{4}\" name = \"data\" size = \"10\"></p>\n");
      out.write("            <p>\n");
      out.write("                <input type = \"submit\" name = \"Submit\" value = \"Submit\">\n");
      out.write("                <input type = \"reset\" value = \"Reset\">\n");
      out.write("                <input type=\"button\" value=\"Voltar\" onClick=\"history.go(-1)\">\n");
      out.write("            </p>\n");
      out.write("        </form>\n");
      out.write("        <script src=\"js/bootstrap.min.js\"></script>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
