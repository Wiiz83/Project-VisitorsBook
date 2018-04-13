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
public class Recherche extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String contenu = request.getParameter("Contenu");
        String messageErreur = "";

        try {
            ArrayList<MessageDor> liste = bd.rechercheMessages(contenu);

            if (liste.isEmpty()) {
                messageErreur = "Aucun élément ne correspond à votre recherche, veuillez réessayer";
                RequestDispatcher rd = request.getRequestDispatcher("Rechercher");
                request.setAttribute("messageErreur", messageErreur);
                rd.forward(request, response);
            } else {
                request.setAttribute("listeResultat", liste);
                request.setAttribute("messageInfo", "Voici les résultats de votre recherche");
                RequestDispatcher rd = request.getRequestDispatcher("Rechercher");
                rd.forward(request, response);
            }
        } catch (SQLException | ClassNotFoundException ex) {
                request.setAttribute("messageErreur", "Erreur " + ex.getMessage());
                RequestDispatcher rd = request.getRequestDispatcher("Rechercher");
                rd.forward(request, response);
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
