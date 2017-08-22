/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.docmation;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
@WebServlet(name = "CheckRegistration", urlPatterns = {"/CheckRegistration"})
public class CheckRegistration extends HttpServlet {

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
            out.println("<title>Servlet CheckRegistration</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CheckRegistration at " + request.getContextPath() + "</h1>");
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
        HttpSession ses = request.getSession();
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String userName = request.getParameter("userName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String isAd = request.getParameter("isAdmin");
        String currency = request.getParameter("currency");
        System.out.println(currency);
        boolean isAdmin = false;
        if("isAdmin".equals(isAd)) isAdmin = true;
        User user = new User(userName, firstName, lastName, email, isAdmin, currency);
        ses.setAttribute("User", user);
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");
            PreparedStatement stmt = con.prepareStatement("insert into user_data values(?,?,?,?,?,?,?)");
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, userName);
            stmt.setString(4, password);
            stmt.setString(5, email);
            stmt.setString(6, Boolean.toString(isAdmin));
            stmt.setString(7, currency);

            stmt.execute();

            con.commit();

            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Exception insert:" + e.getMessage());
            StackTraceElement[] ste = e.getStackTrace();
            System.out.println("Exception: " + e.getMessage());
            for (int i = 0; i < ste.length; i++) {
                System.out.println(ste[i].toString());
            }
        }
        response.sendRedirect("WalmartCopy.jsp");
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
