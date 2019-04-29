<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HR Application</title>
        <link href="style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div>
            <!--employee list-->
            <h1>Salary Update</h1>
            <table id="salarychangetable">
                <thead>
                    <tr>
                        <th><div class="buttondiv">Id&nbsp;<a href="SalaryChange?sort=idinc"><button id="whitebutton">▲</button></a><a href="SalaryChange?sort=iddec"><button id="whitebutton">▼</button></a></div></th>
                        <th><div class="buttondiv">Name&nbsp;<a href="SalaryChange?sort=nameinc"><button id="whitebutton">▲</button></a><a href="SalaryChange?sort=namedec"><button id="whitebutton">▼</button></a></div></th>
                        <th><div class="buttondiv">Department&nbsp;<a href="SalaryChange?sort=depinc"><button id="whitebutton">▲</button></a><a href="SalaryChange?sort=depdec"><button id="whitebutton">▼</button></a></div></th>
                        <th><div class="buttondiv">Manager&nbsp;<a href="SalaryChange?sort=maninc"><button id="whitebutton">▲</button></a><a href="SalaryChange?sort=mandec"><button id="whitebutton">▼</button></a></div></th>
                        <th><div class="buttondiv">Job&nbsp;<a href="SalaryChange?sort=jobinc"><button id="whitebutton">▲</button></a><a href="SalaryChange?sort=jobdec"><button id="whitebutton">▼</button></a></div></th>
                        <th><div class="buttondiv">Allowed minimum <br>salary</div></th>
                        <th><div class="buttondiv">Allowed maximum <br>salary</div></th>
                        <th><div class="buttondiv">Salary</div></th>
                        <th><div class="buttondiv"><a href="login"><button id="whitebutton">◄◄&nbspBack</button></a></div></th>
                    </tr>
                </thead>
                <c:forEach  items="${employeeList}" var="employee"> 
                    <form action="SalaryChange" method="post">
                        <tr>
                            <td><c:out value="${employee.id }" /></td>
                            <td><c:out value="${employee.name}" /></td>
                            <td><c:out value="${employee.department}" /></td>
                            <td><c:out value="${employee.manager}" /></td>
                            <td><c:out value="${employee.job}" /></td>
                            <td><c:out value="${employee.minSalary}" /></td>
                            <td><c:out value="${employee.maxSalary}" /></td>                               
                            <td><input type="text" name="salary" value="${employee.salary}" <c:if test="${!employee.isSalaryOk()}">style="background:#ffdddd"</c:if></td>
                            <td>
                                <button type="submit" value="update">Update</button>
                                <input type="hidden" name="event" value="update">
                                <input type="hidden" name="id" value="${employee.id}">
                            <input type="hidden" name="sort" value="${sort}">
                            </td>
                        </tr>
                    </form>
                </c:forEach>       
            </table>           
        </div>
    </body>
</html>
