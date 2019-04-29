<%-- 
    Document   : ListPeople
    Created on : 2019.02.13., 15:48:14
    Author     : Erdahoy
--%>

<%@page import="java.util.List"%>
<%@page import="hu.evo.training.library.model.Person"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
    </head>
    <body>
        <% List<Person> personList = (List<Person>) request.getAttribute(Person.ATTR); %>
        <table>
            <% for (int i = 0; i < personList.size(); i++) {%>
            <tr>
                <td><%=personList.get(i).toString()%></td>
            </tr>
            <%}%>
        </table>
    </body>
</html>
