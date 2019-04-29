package model;

public interface SQLNewEmployee {
  public final String SQL_SIMILAR_EMAIL = "" + 
          "SELECT EMAIL\n" +
          "FROM EMPLOYEES\n" +
          "WHERE EMAIL LIKE '?%'" + 
          "ORDER BY EMAIL";
  
  public final String SQL_ALL_DEPARTMENTS = "" +
          "SELECT DEPARTMENT_ID, DEPARTMENT_NAME, MANAGER_ID\n" +
          "FROM DEPARTMENTS" +
          "ORDER BY DEPARTMENT_NAME";
  
  public final String SQL_ALL_JOBS = "" +
          "SELECT JOB_ID, JOB_TITLE, MIN_SALARY, MAX_SALARY\n" +
          "FROM JOBS" +
          "ORDER BY JOB_TITLE";

  public final String SQL_ALL_MANAGERS = 
          "SELECT EMPLOYEE_ID, LAST_NAME || ', ' || FIRST_NAME AS NAME\n" +
          "FROM EMPLOYEES\n" +
          "ORDER BY NAME";

}
