<%-- 
    Document   : ListBook
    Created on : 2019.02.13., 15:54:06
    Author     : Erdahoy
--%>

<%@page import="hu.evo.training.library.model.Book"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
<body>
        <% List<Book> bookList = (List<Book>) request.getAttribute(Book.ATTR); %>
        <table>
            <% for (int i = 0; i < bookList.size(); i++) {%>
            <tr data-href="myServlet?id=<%=bookList.get(i).getId()%>">
                <td><%=bookList.get(i).getId()%></td>
                <td><%=bookList.get(i).getAuthor()%></td>
                <td><%=bookList.get(i).getYear()%></td>
                <td><%=bookList.get(i).getTitle()%></td>
            </tr>
            <%}%>
        </table>
    </body>
</html>
