package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("  <title>Login - LaparGila</title>\n");
      out.write("  <script>\n");
      out.write("    function validateForm() {\n");
      out.write("      var email = document.forms[\"loginForm\"][\"email\"].value;\n");
      out.write("      var pwd = document.forms[\"loginForm\"][\"password\"].value;\n");
      out.write("      if (!email || !pwd) {\n");
      out.write("        alert(\"Please enter email and password.\");\n");
      out.write("        return false;\n");
      out.write("      }\n");
      out.write("      return true;\n");
      out.write("    }\n");
      out.write("  </script>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("  <h2>User Login</h2>\n");
      out.write("  <form name=\"loginForm\" action=\"LoginServlet\" method=\"post\" onsubmit=\"return validateForm()\">\n");
      out.write("    Email:<br>\n");
      out.write("    <input type=\"email\" name=\"email\" required><br><br>\n");
      out.write("    Password:<br>\n");
      out.write("    <input type=\"password\" name=\"password\" required><br><br>\n");
      out.write("    <input type=\"submit\" value=\"Login\">\n");
      out.write("  </form>\n");
      out.write("  <p>New user? <a href=\"register.jsp\">Register here</a></p>\n");
      out.write("</body>\n");
      out.write("</html>\n");
      out.write("\n");
      out.write("\n");
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
