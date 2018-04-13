<%@page import="bd.bd"%>
<%@page import="metier.MessageDor"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Le livre d'or</title>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="Source code generated using layoutit.com">
        <meta name="author" content="LayoutIt!">
        <link href=./css/style.css rel=stylesheet>
        <link href="./css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12">
                    <nav class="navbar navbar-default" role="navigation">
                        <div class="navbar-header">

                            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                                <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span>
                            </button> <a class="navbar-brand" href="Index">Livre d'or</a>
                        </div>

                        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                            <ul class="nav navbar-nav">
                                <li class="active">
                                    <a href="Index">Accueil</a>
                                </li>

                                <li>
                                    <a href="Saisir">Ajouter</a>
                                </li>
                                <li>
                                    <a href="SelectionS">Supprimer</a>
                                </li>
                            </ul>

                            <ul class="nav navbar-nav navbar-right">
                                <form class="navbar-form" role="search" method="post" action="Recherche">
                                    <div class="form-group">
                                        <input type="text" class="form-control" name='Contenu'>
                                    </div> 
                                    <button type="submit" class="btn btn-default">
                                        Rechercher
                                    </button>
                                </form>
                            </ul>
                        </div>

                    </nav>
                </div>
            </div>

            <div class="row">
                <div class="col-md-12">
                    <%
                        String msginfo = (String) request.getAttribute("messageInfo");
                        if (msginfo != null) {
                            out.println("<p style='background-color: green; color:white; font-weight: bold; padding: 10px;'>" + msginfo + "</p>");
                        }
                    %>

                    <form method="post" action="Modification">
                        <table class="table table-bordered">
                            <thead>
                                <tr>
                                    <th class="col-md-1">
                                        Numéro
                                    </th>
                                    <th class="col-md-3"> 
                                        Pseudo
                                    </th>
                                    <th>
                                        Message
                                    </th>
                                    <th class="col-md-1">
                                        Modifier
                                    </th>
                                    <th class="col-md-1">
                                        Supprimer
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    ArrayList<MessageDor> liste = bd.lireMessage();

                                    for (MessageDor m : liste) {
                                        out.println("<tr>");
                                        out.println("<td>" + m.getId() + "</td>");
                                        out.println("<td>" + m.getPseudo() + "</td>");
                                        out.println("<td>" + m.getMessage() + "</td>");
                                        out.println("<form method='post' action='Modification'>");
                                        out.println("<input type='hidden' name='Mode' value='selectionparID'/>");
                                        out.println("<td><button type='submit' class='btn btn-primary btn-sm' name='MessageAModifier' value='" + m.getId() + "'><span class='glyphicon glyphicon-pencil'></span></button></td>");
                                        out.println("</form>");
                                        out.println("<form method='post' action='Suppression'>");
                                        out.println("<input type='hidden' name='Mode' value='selectionparID'/>");
                                        out.println("<td><button type='submit' class='btn btn-danger btn-sm' name='MessageASupprimer' value='" + m.getId() + "'><span class='glyphicon glyphicon-trash'></span></button></td>");
                                        out.println("</form>");
                                        out.println("</tr>");
                                    }
                                %>
                            </tbody>
                        </table>  

                    </form>
                </div>
            </div>
        </div>
    </div>


    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/scripts.js"></script>

</body>
</html>
