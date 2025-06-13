package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;
import model.*;
import util.DBConnection;

public final class recordPayment_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html; charset=UTF-8");
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

    String param = request.getParameter("order_id");
    if (param == null) {
        out.println("Missing order ID.");
        return;
    }

    int orderId = Integer.parseInt(param);
    Connection conn = DBConnection.getConnection();
    OrderDAO orderDAO = new OrderDAO(conn);
    MenuDAO menuDAO = new MenuDAO(conn);

    Order order = orderDAO.getOrderById(orderId);
    MenuItem item = menuDAO.getMenuById(order.getMenuId());

    if (order.getUserId() != user.getUserId()) {
        out.println("Unauthorized access.");
        return;
    }

    double totalAmount = item.getPrice() * order.getQuantity();

      out.write("\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("<head><title>Record Payment</title></head>\n");
      out.write("<body>\n");
      out.write("<h2>Pay for Order #");
      out.print( orderId );
      out.write("</h2>\n");
      out.write("\n");
      out.write("<p><strong>Item:</strong> ");
      out.print( item.getName() );
      out.write("<br>\n");
      out.write("<strong>Quantity:</strong> ");
      out.print( order.getQuantity() );
      out.write("<br>\n");
      out.write("<strong>Total:</strong> RM ");
      out.print( String.format("%.2f", totalAmount) );
      out.write("</p>\n");
      out.write("\n");
      out.write("<form action=\"RecordPaymentServlet\" method=\"post\">\n");
      out.write("    <input type=\"hidden\" name=\"order_id\" value=\"");
      out.print( orderId );
      out.write("\">\n");
      out.write("    <input type=\"hidden\" name=\"amount\" value=\"");
      out.print( totalAmount );
      out.write("\">\n");
      out.write("\n");
      out.write("    <label>Payment Method:</label>\n");
      out.write("    <select name=\"method\">\n");
      out.write("        <option value=\"Cash\">Cash</option>\n");
      out.write("        <option value=\"Card\">Card</option>\n");
      out.write("        <option value=\"Online Banking\">Online Banking</option>\n");
      out.write("    </select><br><br>\n");
      out.write("\n");
      out.write("    <input type=\"submit\" value=\"Confirm Payment\">\n");
      out.write("</form>\n");
      out.write("\n");
      out.write("<p><a href=\"viewOrders.jsp\">Back to My Orders</a></p>\n");
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
