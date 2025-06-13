package controller;

import model.MenuDAO;
import util.DBConnection;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "DeleteMenuServlet", urlPatterns = {"/DeleteMenuServlet"})
public class DeleteMenuServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));

        try (Connection conn = DBConnection.getConnection()) {
            MenuDAO dao = new MenuDAO(conn);
            dao.deleteMenuItem(id);
            resp.sendRedirect("menu.jsp");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
