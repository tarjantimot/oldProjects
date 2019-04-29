<%-- 
    Document   : user.jsp
    Created on : 2018.03.31., 14:11:42
    Author     : Dottya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>HR Application</title>
    <link href="style.css" rel="stylesheet" type="text/css"/>
  </head>
  <body>
    <h1>Login</h1>
    <form method="POST" action="login">
    <table id="loginTable" align="center">
      <tr>
        <td>Username: </td>
        <td><input type="text" name="username" autofocus></td>
      </tr>
      <tr>
        <td>Password: </td>
        <td><input type="password" name="password"></td>
      </tr>
    </table>
    <p><div class="buttondiv"><input id="greenbutton" type="submit" value="Login"></div></p>
    </form>
    <br><br><br>
    </form
    <h4><div class="buttondiv">ezek majd csak login után látszódnak, jogkörnek megfelelően:</div></h4>
    <p><div class="buttondiv"><a href="newEmployee.jsp"><button id="greenbutton">New Employee (már működik)</button></a></div></p>
    <p><div class="buttondiv"><a href="averageSalary.jsp"><button id="greenbutton">Average Salary //TODO</button></a></div></p>
    <p><div class="buttondiv"><button id="greenbutton">Update Salary //TODO</button></div></p>
    <p><div class="buttondiv"><button id="greenbutton">Kijelentkezés //TODO</button></div></p>
  </body>
</html>
