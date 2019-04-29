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
    <c:if test="${accessLevel<0 || accessLevel ==null}">
        <h1>Welcome to our HR Application, please log in!</h1>
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
            <p><div class="buttondiv"><input id="greenbutton" type="submit" value="Login" name="logInOut"></div></p>
        </form>
        <br><br>
    <!--</form-->
        <c:if test="${accessLevel==-2}">
            <h2 style="text-align: center">Wrong username or password!</h2>
        </c:if>
    </c:if>
    <c:if test="${accessLevel>-1}">
        <!--<h4><div class="buttondiv">ezek majd csak login után látszódnak, jogkörnek megfelelően:</div></h4>-->
        <h1>Welcome <c:out value="${userName}"/>!</h1>
        <c:if test="${accessLevel>0}">
            <p><div class="buttondiv"><a href="NewEmployeeServlet"><button id="greenbutton">New Employee</button></a></div></p>
        </c:if>
        <p><div class="buttondiv"><a href="chart.png"><button id="greenbutton">Average Salary </button></a></div></p>
        <p><div class="buttondiv"><a href="SalaryChange"><button id="greenbutton">Update Salary </button></a></div></p>
        <form method="POST" action="login"> 
            <p><div class="buttondiv"><button id="greenbutton" type="submit" value="logout" name="logInOut">Kijelentkezés</button></div></p>
        </form>
    </c:if>
  </body>
</html>
