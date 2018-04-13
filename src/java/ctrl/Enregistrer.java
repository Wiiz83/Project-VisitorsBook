/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrl;

import bd.bd;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.MessageDor;

/**
 *
 * @author 21613052
 */
public class Enregistrer extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pseudo = request.getParameter("Pseudo");
        String message = request.getParameter("Message");
        String messageErreur = "";

        if (message.isEmpty() && pseudo.isEmpty()) {
            messageErreur = "Veuillez saisir les informations demandées";
        } else {
            if (message.isEmpty()) {
                messageErreur = "Veuillez saisir un message";
            } else if (pseudo.isEmpty()) {
                messageErreur = "Veuillez saisir un pseudo";
            }
        }

        if (!messageErreur.isEmpty()) {

            RequestDispatcher rd = request.getRequestDispatcher("Saisir");
            request.setAttribute("messageErreur", messageErreur);

            if (!pseudo.isEmpty()) {
                request.setAttribute("p", pseudo);
            }

            if (!message.isEmpty()) {
                request.setAttribute("m", message);
            }

            rd.forward(request, response);
        } else {

            MessageDor m = new MessageDor(pseudo, message);

            try {
                bd.enregistrerMessage(m);

                request.setAttribute("messageInfo", "Message ajouté avec succès");
                RequestDispatcher rd = request.getRequestDispatcher("Saisir");
                rd.forward(request, response);
            } catch (SQLException | ClassNotFoundException ex) {
                request.setAttribute("messageErreur", "Erreur " + ex.getMessage());
                RequestDispatcher rd = request.getRequestDispatcher("Saisir");
                rd.forward(request, response);
            } 

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
