/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;
import jpa.controller.AccountJpaController;
import jpa.model.Account;

/**
 *
 * @author piyao
 */
public class LoginServlet extends HttpServlet {

    @PersistenceUnit(unitName = "BankPracticePU")
    EntityManagerFactory emf;

    @Resource
    UserTransaction utx;

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
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//
//        if (username != null && username.trim().length() > 0 && password != null && password.trim().length() > 0) {
//            int usernameNum = Integer.valueOf(username);
//            int passwordNum = Integer.valueOf(password);
//
//            AccountJpaController accountJpaCtrl = new AccountJpaController(utx, emf);
//            Account account = accountJpaCtrl.findAccount(usernameNum);
//
//            if (account != null && passwordNum == account.getPin()) {
//                HttpSession session = request.getSession();
//                session.setAttribute("username", account);
//                getServletContext().getRequestDispatcher("/MyAccount").forward(request, response);
//                return;
//            }
//            request.setAttribute("meassge", "Wrong username or password !!");
//        }
//        getServletContext().getRequestDispatcher("/Login.jsp").forward(request, response);




         String username = request.getParameter("username");
         String password = request.getParameter("password");
         
         if (username != null && username.trim().length() > 0 && password != null && password.trim().length() > 0) {
            int usernameNum = Integer.valueOf("username");
            int passNum = Integer.valueOf("password");
            
            AccountJpaController accountJpaCtrl = new AccountJpaController(utx, emf);
            Account account = accountJpaCtrl.findAccount(usernameNum);
             if (account != null && passNum == account.getPin()) {
                 HttpSession session = request.getSession();
                 session.setAttribute("account", account);
                 getServletContext().getRequestDispatcher("/Login").forward(request, response);
                 return;
             }
             request.setAttribute("messgae", "Wrong username or password !!");
        }
         getServletContext().getRequestDispatcher("/Login.jsp").forward(request, response);
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
        processRequest(request, response);
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
