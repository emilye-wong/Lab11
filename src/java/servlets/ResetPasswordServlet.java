/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dataaccess.UserDB;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;
import services.AccountService;

/**
 *
 * @author emily
 */
public class ResetPasswordServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String uuid = request.getParameter("uuid");
        UserDB userDB = new UserDB();

        try {
            User user = userDB.getUUID(uuid);
            if (user != null) {
                session.setAttribute("uuid", uuid);
                getServletContext().getRequestDispatcher("/WEB-INF/resetNewPassword.jsp").forward(request, response);
            }
        } catch (Exception e) {
            getServletContext().getRequestDispatcher("/WEB-INF/reset.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        AccountService ac = new AccountService();
        String uuid = (String) session.getAttribute("uuid");
        String action = request.getParameter("action");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (action.equals("reset")) {
            String path = getServletContext().getRealPath("/WEB-INF");
            String url = request.getRequestURI().toString();
            boolean reset = ac.resetPassword(email, path, url);

            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        } else if (action.equals("newPassword")) {
            boolean change = ac.changePassword(uuid, password);
                   
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }

    }
}
