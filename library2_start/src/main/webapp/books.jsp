<%-- 
    Document   : books
    Created on : 2019.02.01., 9:49:58
    Author     : pappmico
--%>

<%@page import="java.util.List"%>
<%@page import="hu.evo.training.library.model.Book"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Books</title>
    </head>
    <body>
        <%
            //out.print(request.getAttribute("EXCEPTION"));
            for(Book book: (List<Book>)request.getAttribute(Book.ATTR)){
                out.print(book);
                out.print("<br />");
            }
        %>
    </body>
</html>
