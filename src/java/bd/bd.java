package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import metier.MessageDor;

/**
 *
 * @author 21613052
 */
public class bd {

    /* private static final String SERVEUR = "jdbc:mysql://kolga.ut-capitole.fr/db_21613052";
    private static final String LOGIN = "21613052";
    private static final String MDP = "15738P";
    private static Connection cx;
     */
    private static final String SERVEUR = "jdbc:mysql://localhost/db_livredor";
    private static final String LOGIN = "root";
    private static final String MDP = "";
    private static Connection cx;

    // méthode de connexion
    public static void connexion() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");
        cx = DriverManager.getConnection(SERVEUR, LOGIN, MDP);
    }

    public static void closeConnexion() throws SQLException {
        cx.close();
    }

    // méthode d'insertion d'un message d'or
    public static int enregistrerMessage(MessageDor m) throws SQLException, ClassNotFoundException {
        // création eventuelle de la connexion a la BD
        if (bd.cx == null) {
            bd.connexion();
        }

        // Espace de requete
        PreparedStatement st;

        // requete sql
        String sql = "INSERT INTO Message(Pseudo,Message) VALUES (?,?)";
        int nb = 0;

        // ouverture de l'espace de requete
        st = bd.cx.prepareStatement(sql);

        // insertion du message 
        st.setString(1, m.getPseudo());
        st.setString(2, m.getMessage());

        nb = st.executeUpdate();
        st.close();

        return nb;
    }

    public static int modifierMessage(String id, String pseudo, String message) throws SQLException, ClassNotFoundException {
        if (bd.cx == null) {
            bd.connexion();
        }
        PreparedStatement st;
        String sql = "UPDATE Message SET Pseudo = ?, Message = ? WHERE NumMsg = ?";
        int nb = 0;

        st = bd.cx.prepareStatement(sql);

        st.setString(1, pseudo);
        st.setString(2, message);
        st.setInt(3, Integer.parseInt(id));

        nb = st.executeUpdate();
        st.close();

        return nb;
    }

    public static ArrayList<MessageDor> lireMessage() throws SQLException, ClassNotFoundException {

        if (bd.cx == null) {
            bd.connexion();
        }

        PreparedStatement st;
        String sql = "SELECT NumMsg,Pseudo,Message FROM Message";

        try {
            st = bd.cx.prepareStatement(sql);
        } catch (SQLException e) {
            throw new SQLException("problème ouverture espace de requête " + e.getMessage());
        }

        ArrayList<MessageDor> liste = new ArrayList<MessageDor>();

        try {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                liste.add(new MessageDor(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3)));
            }

        } catch (SQLException e) {
            throw new SQLException("problème de lecture  " + e.getMessage());
        }

        return liste;
    }

    public static ArrayList<MessageDor> rechercheMessages(String recherche) throws SQLException, ClassNotFoundException {

        if (bd.cx == null) {
            bd.connexion();
        }

        PreparedStatement st;
        String sql = "SELECT NumMsg,Pseudo,Message FROM Message WHERE Pseudo = ? OR Message = ?";

        try {
            st = bd.cx.prepareStatement(sql);
        } catch (SQLException e) {
            throw new SQLException("problème ouverture espace de requête " + e.getMessage());
        }

        ArrayList<MessageDor> liste = new ArrayList<MessageDor>();

        try {
            st.setString(1, recherche);
            st.setString(2, recherche);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                liste.add(new MessageDor(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3)));
            }

        } catch (SQLException e) {
            throw new SQLException("problème de lecture  " + e.getMessage());
        }

        return liste;
    }

    public static ArrayList<MessageDor> lireMessageParID(String[] tab) throws SQLException, ClassNotFoundException {

        if (bd.cx == null) {
            bd.connexion();
        }
        
        PreparedStatement st;
        String sql = "SELECT NumMsg,Pseudo,Message FROM Message WHERE NumMsg = ? ";
        ArrayList<MessageDor> liste = new ArrayList<MessageDor>();

        try {
            st = bd.cx.prepareStatement(sql);
        } catch (SQLException e) {
            throw new SQLException("problème ouverture espace de requête " + e.getMessage());
        }

        try {
            for (int i = 0; i < tab.length; i++) {
                st.setString(1, tab[i]);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    liste.add(new MessageDor(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3)));
                }
            }
        } catch (SQLException e) {
            throw new SQLException("problème de lecture  " + e.getMessage());
        }

        return liste;
    }

    public static MessageDor lireMessageParSonID(String ID) throws SQLException, ClassNotFoundException {

        if (bd.cx == null) {
            bd.connexion();
        }

        PreparedStatement st;
        String sql = "SELECT NumMsg,Pseudo,Message FROM Message WHERE NumMsg = ? ";
        MessageDor m;

        try {
            st = bd.cx.prepareStatement(sql);
        } catch (SQLException e) {
            throw new SQLException("problème ouverture espace de requête " + e.getMessage());
        }

        try {
            st.setString(1, ID);
            ResultSet rs = st.executeQuery();
            rs.next();
            m = new MessageDor(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3));
        } catch (SQLException e) {
            throw new SQLException("problème de lecture  " + e.getMessage());
        }

        return m;
    }

    public static void supprimerMessage(String[] tabSupprimer) throws SQLException, ClassNotFoundException {

        if (bd.cx == null) {
            bd.connexion();
        }

        PreparedStatement st;
        String sql = "DELETE FROM Message WHERE NumMsg = ? ;";

        try {
            st = bd.cx.prepareStatement(sql);
        } catch (SQLException e) {
            throw new SQLException("problème ouverture espace de requête lors de la suppresion");
        }
        try {
            for (int i = 0; i < tabSupprimer.length; i++) {
                st.setString(1, tabSupprimer[i]);
                st.executeUpdate();
            }
        } catch (SQLException e) {
            throw new SQLException("problème de suppresion " + e.getMessage());
        }

        st.close();
    }

}
