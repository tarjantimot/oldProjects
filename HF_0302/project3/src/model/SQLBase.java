package model;

public interface SQLBase {
  public final String DRIVER = "oracle.jdbc.driver.OracleDriver";
  public final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	
  public final String USER_NAME = "HR";
  public final String USER_PWD = "hr";
	
//  public final String SQL_CHECK_SALARY = 
//    "SELECT SALARY\n" +
//    "FROM EMPLOYEES\n" +
//    "WHERE EMPLOYEE_ID = ?";
}