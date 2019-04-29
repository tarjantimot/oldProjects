<%@page import="hu.bh.dzzt.dtos.ManagerDTO"%>
<%@page import="hu.bh.dzzt.dtos.JobDTO"%>
<%@page import="hu.bh.dzzt.dtos.DepartmentDTO"%>
<%@page import="hu.bh.dzzt.dtos.EmployeeDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Employee list:</h2>
        <div>
            <% List<EmployeeDTO> employees = (List<EmployeeDTO>)request.getAttribute("employeeList");
            if(employees != null)
            for(EmployeeDTO employee : employees){%>
                <%=employee.toString()%><br>
            <%}%>
        </div>
        <h2>Department list:</h2>
        <div>
            <% List<DepartmentDTO> departments = (List<DepartmentDTO>)request.getAttribute("departmentList");
            if(departments != null)
            for(DepartmentDTO department : departments){%>
                <%=department.toString()%><br>
            <%}%>
        </div>
        <h2>Job list:</h2>
        <div>
            <% List<JobDTO> jobs = (List<JobDTO>)request.getAttribute("jobList");
            if(jobs != null)
            for(JobDTO job : jobs){%>
                <%=job.toString()%><br>
            <%}%>
        </div>
        <h2>Manager list:</h2>
        <div>
            <% List<ManagerDTO> managers = (List<ManagerDTO>)request.getAttribute("managerList");
            if(managers != null)
            for(ManagerDTO manager : managers){%>
                <%=manager.toString()%><br>
            <%}%>
        </div>
        <h2>Similar email list:</h2>
        <div>
            <% List<String> emails = (List<String>)request.getAttribute("similarEmail");
            if(emails != null)
            for(String email : emails){%>
                <%=email%><br>
            <%}%>
        </div>
    </body>
</html>
