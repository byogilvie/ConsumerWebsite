package com.docmation;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author bradl
 */
@WebServlet(urlPatterns = {"/CheckLogIn"})
public class CheckLogIn extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CheckLogIn</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CheckLogIn at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        HttpSession ses = request.getSession();
        response.setContentType("text/html");
        System.out.print(ses.getMaxInactiveInterval());
        boolean isValid = false;
        /*ServletContext context = request.getServletContext();
        String path = request.getRealPath("items.txt");
        out.println(path);
        ItemsFile itemsFile = new ItemsFile(path);
        itemsFile.storeItems(new Item("PS4", "Electronics", "299.99", "ps4"));
        itemsFile.printItems(out);*/
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");
            PreparedStatement stmt = con.prepareStatement("select * from user_data");
            ResultSet rs = stmt.executeQuery();
            String str1, str2;
            while (rs.next()) {
                str1 = rs.getString("username");
                str2 = rs.getString("password");
                System.out.println(str1 + ", " + str2);
                if (username.equals(str1)) {
                    if (password.equals(str2)) {
                        isValid = true;
                        break;
                    }
                }
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException ex) {
            StackTraceElement[] ste = ex.getStackTrace();
            System.out.println("Exception: " + ex.getMessage());
            for (int i = 0; i < ste.length; i++) {
                System.out.println(ste[i].toString());
            }

        } catch (Exception ex) {
            System.out.println("Exception:" + ex.getMessage());
            StackTraceElement[] ste = ex.getStackTrace();
            System.out.println("Exception: " + ex.getMessage());
            for (int i = 0; i < ste.length; i++) {
                System.out.println(ste[i].toString());
            }
        }
        if (isValid) {
            User user = new User(username);
            ses.setAttribute("User", user);
            response.sendRedirect("WalmartCopy.jsp");

        } else {
            response.sendRedirect("error.jsp");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        System.out.println("Test");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
