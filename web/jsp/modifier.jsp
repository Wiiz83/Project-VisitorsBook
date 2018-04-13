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
                                <li>
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
                <div class="col-md-6">
                    <form id='f_modifier' method="post" action="Modification">

                        <%

                            String mode = (String) request.getAttribute("modeRetour");

                            out.println("<input type='hidden' name='Mode' value='enregistrement'/>");

                            if (mode == "modification") {

                                MessageDor mess = (MessageDor) request.getAttribute("MessageAModifier");
                                out.println("<input type='hidden' name='IdMessage' value='" + mess.getId() + "'/>");
                                out.println("<div class='input-group'><span class='input-group-addon' id='basic-addon1'><i class='glyphicon glyphicon-user'></i></span><input type='text' name='Pseudo' value='" + mess.getPseudo() + "' class='form-control' placeholder='Pseudo' aria-describedby='basic-addon1'></div><br/>");
                                out.println("<div class='input-group'><span class='input-group-addon' id='basic-addon1'><i class='glyphicon glyphicon-envelope'></i></span>  <textarea class='form-control' rows='5' id='comment' placeholder='Message' name='Message'>" + mess.getMessage() + "</textarea></div>");

                            } else if (mode == "feedback") {

                                String msgerreur = (String) request.getAttribute("messageErreur");

                                String p = (String) request.getAttribute("p");
                                String m = (String) request.getAttribute("m");
                                String id = (String) request.getAttribute("id");

                                if (msgerreur != null) {
                                    out.println("<p style='background-color: red; color:white; font-weight: bold; padding: 10px;'>" + msgerreur + "</p>");
                        %>
                        <input type='hidden' name='IdMessage' value='<%= id%>'/>
                        <div class='input-group'><span class='input-group-addon' id='basic-addon1'><i class='glyphicon glyphicon-user'></i></span><input type='text' name='Pseudo' value="<%= (p == null) ? "" : p%>" class='form-control' placeholder='Pseudo' aria-describedby='basic-addon1'></div><br/>
                        <div class='input-group'><span class='input-group-addon' id='basic-addon1'><i class='glyphicon glyphicon-envelope'></i></span>  <textarea class='form-control' rows='5' placeholder='Message' name='Message' value='<%= (m == null) ? "" : m%>'><%= (m == null) ? "" : m%></textarea></div>

                        <%
                                }

                            }
                        %>
                        <br/>
                        <button type="submit" class="btn btn-success" value='Modifier'>Oui</button>
                        <a href="Index"><button type="button" class="btn btn-danger">Non</button></a>
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
