<%-- 
    Document   : newEmployee
    Created on : 2018.03.31., 14:12:21
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
    <h1>Adding a new employee</h1>
    <div class="formBody"><form method="POST" action="" id="newEmployeeWizard">
    <div class="tab">Name and phone:
      <p><input type="text" name="firstName" placeholder="First name..." oninput="this.className = ''" ></p>
      <p><input type="text" name="lastName"placeholder="Last name..." oninput="this.className = ''" ></p>
      <p><input type="text" name="phone" placeholder="Phone..." oninput="this.className = ''" ></p>
    </div>
    <div class="tab">Department:
      <p><select name="department">
        <c:forEach items="${departmentList}"  var="department">
          <option value="${department.departmentId}">${department.departmentName}</option>
        </c:forEach>
      </select></p>
    </div>
    <div class="tab">Job title:
      <p><select name="jobtitle" id="joblist" onchange="jobSelected(this)">
        <c:forEach items="${jobList}"  var="job">
          <option value="${job.jobId}">${job.jobTitle}</option>
        </c:forEach>
      </select></p>
    </div>
        
    <div class="tab">Manager:
      <p><select name="manager">
        <c:forEach items="${managerList}"  var="manager">
          <option value="${manager.employeeId}">${manager.lastName}, ${manager.firstName}</option>
        </c:forEach>
      </select></p>
    </div>
    <div class="tab">
        <p><input name="salary" type="text" oninput="validateForm2()" placeholder="Salary..."  id="newEmployeeWizard"></p>
    </div>
    <div style="overflow:auto;">
      <div class="buttons">
        <button type="button" id="prevBtn" onclick="nextPrev(-1)">Previous</button>
        <button type="button" id="nextBtn" onclick="nextPrev(1)">Next</button>
        <input type ="submit" id="submitbutton" value="Submit" name="submit">
      </div>
    </div>
    </form>
    </div>
    <div style="text-align:center;margin-top:40px;">
      <span class="step"></span>
      <span class="step"></span>
      <span class="step"></span>
      <span class="step"></span>
    </div>
    <div class="buttondiv"><a href="login"><button id="greenbutton">◄◄&nbspBack</button></a></div>
    <script type="text/javascript" src="wizardLogic.js"></script>
  </body>
</html>
