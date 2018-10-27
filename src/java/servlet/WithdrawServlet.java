/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import jpa.controller.HistoryJpaController;
import jpa.model.Account;
import jpa.model.History;

/**
 *
 * @author piyao
 */
public class WithdrawServlet extends HttpServlet {
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
            throws ServletException, IOException, Exception {
        String money = request.getParameter("money");
        HttpSession session = request.getSession(false);
        if (session == null) {
            getServletContext().getRequestDispatcher("/Login").forward(request, response);
            return;
        }
        if (money != null && money.trim().length() > 0) {
            int inputMoney = Integer.valueOf(money);
            Account account = (Account) session.getAttribute("username");

            if (account.getBalance()>0&&account.getBalance()>=inputMoney
                    &&inputMoney > 0 && account != null) {
                int total = account.getBalance() - inputMoney;
                account.setBalance(total);

                History history = new History();
                history.setAccountid(account);
                history.setAmount(inputMoney);
                history.setBalance(total);
                history.setMethod("withdraw");
                history.setCreatedate(new Date());

                HistoryJpaController historyJpaContrl = new HistoryJpaController(utx, emf);
                historyJpaContrl.create(history);

                List<History> historyFromDB = historyJpaContrl.findHistoryEntities();
                List<History> historyForEachAccount = new ArrayList<>();

                for (History h : historyFromDB) {
                    if (h.getAccountid().getAccountid() == account.getAccountid()) {
                        historyForEachAccount.add(h);
                    }
                }
                account.setHistoryList(historyForEachAccount);

                AccountJpaController accountJpaCtrl = new AccountJpaController(utx, emf);
                accountJpaCtrl.edit(account);
                getServletContext().getRequestDispatcher("/MyAccount").forward(request, response);
                return;
            }
            request.setAttribute("wrongdeposit", "Invalid input money");

        }
        getServletContext().getRequestDispatcher("/Withdraw.jsp").forward(request, response);
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(DepositServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(DepositServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
