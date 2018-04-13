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

public class Suppression extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String[] tabSupprimer;

        if (request.getParameterValues("MessageASupprimer") != null) {
            tabSupprimer = request.getParameterValues("MessageASupprimer");

            try {
                bd.supprimerMessage(tabSupprimer);
                request.setAttribute("messageInfo", "Message(s) supprimé(s) avec succès");
                RequestDispatcher rd = request.getRequestDispatcher("Index");
                rd.forward(request, response);
            } catch (SQLException | ClassNotFoundException ex) {
                request.setAttribute("messageErreur", "Erreur " + ex.getMessage());
                RequestDispatcher rd = request.getRequestDispatcher("SelectionS");
                rd.forward(request, response);
            } 

        } else {
            tabSupprimer = request.getParameterValues("IdMessage");
            String messageErreur = "";

            if (tabSupprimer.length <= 0) {
                messageErreur = "Veuillez séléctionner au moins un message";
            }

            if (!messageErreur.isEmpty()) {
                RequestDispatcher rd = request.getRequestDispatcher("SelectionS");
                request.setAttribute("messageErreur", messageErreur);
                rd.forward(request, response);
            } else {
                try {
                    bd.supprimerMessage(tabSupprimer);
                    request.setAttribute("messageInfo", "Message(s) supprimé(s) avec succès");
                    RequestDispatcher rd = request.getRequestDispatcher("SelectionS");
                    rd.forward(request, response);

                } catch (SQLException | ClassNotFoundException ex) {
                    request.setAttribute("messageErreur", "Erreur " + ex.getMessage());
                    RequestDispatcher rd = request.getRequestDispatcher("SelectionS");
                    rd.forward(request, response);
            } 
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
