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
      <p><input type="text" name="firstName" placeholder="First name..." oninput="this.className = ''"></p>
      <p><input type="text" name="lastName"placeholder="Last name..." oninput="this.className = ''"></p>
      <p><input type="text" name="phone" placeholder="Phone..." oninput="this.className = ''"></p>
    </div>
    <div class="tab">Department:
      <p><select name="department">
        <option value="x">x</option>
        <option value="y">y</option>
      </select></p>
    </div>
    <div class="tab">Job title:
      <p><select name="jobtitle">
        <option value="x">x</option>
        <option value="y">y</option>
      </select></p>
    </div>
    <div class="tab">Manager:
      <p><select name="manager">
        <option value="x">x</option>
        <option value="y">y</option>
      </select></p>
    </div>
    <div class="tab">Salary:
      <p><input placeholder="Salary..." oninput="this.className = ''"></p>
    </div>
    </div>
    <div style="overflow:auto;">
      <div class="buttons">
        <button type="button" id="prevBtn" onclick="nextPrev(-1)">Previous</button>
        <button type="button" id="nextBtn" onclick="nextPrev(1)">Next</button>
      </div>
    </div>
    <div style="text-align:center;margin-top:40px;">
      <span class="step"></span>
      <span class="step"></span>
      <span class="step"></span>
      <span class="step"></span>
    </div>
    </form>
    <script  type="text/javascript" src="wizardLogic.js"></script>
  </body>
</html>
