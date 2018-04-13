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
import java.util.ArrayList;
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
public class Modification extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String mode = request.getParameter("Mode");

        if ("enregistrement".equals(mode)) {
            String pseudo = request.getParameter("Pseudo");
            String message = request.getParameter("Message");
            String id = request.getParameter("IdMessage");
            String messageErreur = "";
            String modeRetour = "feedback";

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
                request.setAttribute("modeRetour", modeRetour);
                request.setAttribute("id", id);
                request.setAttribute("p", pseudo);
                request.setAttribute("m", message);
                RequestDispatcher rd = request.getRequestDispatcher("Modifier");
                request.setAttribute("messageErreur", messageErreur);
                rd.forward(request, response);
            } else {

                try {
                    bd.modifierMessage(id, pseudo, message);
                    request.setAttribute("messageInfo", "Message modifié avec succès");
                    RequestDispatcher rd = request.getRequestDispatcher("Index");
                    rd.forward(request, response);
                } catch (SQLException | ClassNotFoundException ex) {
                    request.setAttribute("messageErreur", "Erreur " + ex.getMessage());
                    RequestDispatcher rd = request.getRequestDispatcher("Modifier");
                    rd.forward(request, response);
            } 

            }
        } else if ("selectionparID".equals(mode)) {

            String ID = request.getParameter("MessageAModifier");

            try {

                MessageDor message = bd.lireMessageParSonID(ID);

                String modeRetour = "modification";
                request.setAttribute("modeRetour", modeRetour);
                request.setAttribute("MessageAModifier", message);
                RequestDispatcher rd = request.getRequestDispatcher("Modifier");
                rd.forward(request, response);
            } catch (SQLException | ClassNotFoundException ex) {
                request.setAttribute("messageErreur", "Erreur " + ex.getMessage());
                RequestDispatcher rd = request.getRequestDispatcher("Modifier");
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
