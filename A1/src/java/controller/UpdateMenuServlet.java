package controller;

import model.MenuItem;
import model.MenuDAO;
import util.DBConnection;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "UpdateMenuServlet", urlPatterns = {"/UpdateMenuServlet"})
public class UpdateMenuServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("menu_id"));
        String name = request.getParameter("name");
        String desc = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));

        MenuItem item = new MenuItem();
        item.setMenuId(id);
        item.setName(name);
        item.setDescription(desc);
        item.setPrice(price);

        try (Connection conn = DBConnection.getConnection()) {
            MenuDAO dao = new MenuDAO(conn);
            dao.updateMenuItem(item);
            response.sendRedirect("menu.jsp");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
