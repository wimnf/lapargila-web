package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.Connection;
import java.util.*;
import model.*;
import util.DBConnection;
import model.User;

public final class menu_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\n");
      out.write("\n");
      out.write("\n");

    User user = (User) session.getAttribute("currentUser");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    Connection conn = DBConnection.getConnection();
    if (conn == null) {
        out.println("Database connection failed. Check DBConnection.java and MySQL.");
        return;
    }

    MenuDAO menuDAO = new MenuDAO(conn);
    List<MenuItem> menuList = menuDAO.getAllMenuItems();

      out.write("\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("    <title>Menu - LaparGila</title>\n");
      out.write("    <style>\n");
      out.write("        body { font-family: Arial, sans-serif; padding: 20px; }\n");
      out.write("        table { width: 100%; border-collapse: collapse; margin-top: 20px; }\n");
      out.write("        th, td { border: 1px solid #ccc; padding: 10px; text-align: left; }\n");
      out.write("        th { background-color: #f4a261; color: white; }\n");
      out.write("        h1 { color: #264653; }\n");
      out.write("        .navbar {\n");
      out.write("            background: #f4f4f4; padding: 10px;\n");
      out.write("            border-bottom: 1px solid #ccc; margin-bottom: 20px;\n");
      out.write("        }\n");
      out.write("        .navbar a {\n");
      out.write("            margin-right: 10px; text-decoration: none; color: #1d3557;\n");
      out.write("        }\n");
      out.write("    </style>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("\n");
      out.write("<div class=\"navbar\">\n");
      out.write("    Welcome, <strong>");
      out.print( user.getName() );
      out.write("</strong> |\n");
      out.write("    <a href=\"menu.jsp\">Menu</a>\n");
      out.write("    ");
 if (user.getEmail().equals("admin@example.com")) { 
      out.write("\n");
      out.write("        <a href=\"viewAllOrders.jsp\">View All Orders</a>\n");
      out.write("    ");
 } else { 
      out.write("\n");
      out.write("        <a href=\"placeOrder.jsp\">Place Order</a>\n");
      out.write("        <a href=\"viewOrders.jsp\">My Orders</a>\n");
      out.write("    ");
 } 
      out.write("\n");
      out.write("    <a href=\"editProfile.jsp\">My Profile</a>\n");
      out.write("    <a href=\"logout.jsp\" style=\"color:red;\">Logout</a>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<h1>LaparGila Food Menu</h1>\n");
      out.write("\n");
      out.write("<table>\n");
      out.write("    <tr>\n");
      out.write("        <th>ID</th>\n");
      out.write("        <th>Name</th>\n");
      out.write("        <th>Description</th>\n");
      out.write("        <th>Price (RM)</th>\n");
      out.write("        <th>Action</th>\n");
      out.write("    </tr>\n");
      out.write("\n");

    for (MenuItem item : menuList) {

      out.write("\n");
      out.write("    <tr>\n");
      out.write("        <td>");
      out.print( item.getMenuId() );
      out.write("</td>\n");
      out.write("        <td>");
      out.print( item.getName() );
      out.write("</td>\n");
      out.write("        <td>");
      out.print( item.getDescription() );
      out.write("</td>\n");
      out.write("        <td>");
      out.print( String.format("%.2f", item.getPrice()) );
      out.write("</td>\n");
      out.write("        <td>\n");
      out.write("            ");
 if (user.getEmail().equals("admin@example.com")) { 
      out.write("\n");
      out.write("                <a href=\"editMenu.jsp?id=");
      out.print( item.getMenuId() );
      out.write("\">Edit</a> |\n");
      out.write("                <a href=\"DeleteMenuServlet?id=");
      out.print( item.getMenuId() );
      out.write("\"\n");
      out.write("                   onclick=\"return confirm('Delete this item?')\">Delete</a>\n");
      out.write("            ");
 } else { 
      out.write("\n");
      out.write("                <form action=\"placeOrder.jsp\" method=\"get\" style=\"display:inline;\">\n");
      out.write("                    <input type=\"hidden\" name=\"menu_id\" value=\"");
      out.print( item.getMenuId() );
      out.write("\">\n");
      out.write("                    <button type=\"submit\">Order</button>\n");
      out.write("                </form>\n");
      out.write("            ");
 } 
      out.write("\n");
      out.write("        </td>\n");
      out.write("    </tr>\n");

    }

      out.write("\n");
      out.write("</table>\n");
      out.write("\n");
      out.write("</body>\n");
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
