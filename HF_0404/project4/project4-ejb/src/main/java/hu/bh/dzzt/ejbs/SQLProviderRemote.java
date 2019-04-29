package hu.bh.dzzt.ejbs;

import hu.bh.dzzt.dtos.DepartmentDTO;
import hu.bh.dzzt.dtos.EmployeeDTO;
import hu.bh.dzzt.dtos.JobDTO;
import hu.bh.dzzt.dtos.ManagerDTO;
import hu.bh.dzzt.dtos.UserDTO;
import java.util.List;

public interface SQLProviderRemote {
  
  public UserDTO validateLogin(String userName, String pwd);

  public int insertUser(String userName, String password, int role);

  public int insertRole(String name, int value);
  
  public List<DepartmentDTO> getDepartmentList();
  
  public List<JobDTO> getJobList();
  
  public List<ManagerDTO> getManagerList();
  
	public List<EmployeeDTO> getEmployeeList();
  
  public List<String> getSimilarEmailList(String preferredEmail);
  
  public EmployeeDTO getEmployeeById(long id);

  public long saveNewEmployee(EmployeeDTO employee);

  public long getDepartmentWageCost(DepartmentDTO department);
  
  public boolean updateSalary(long employeeId, long salary);

  public long getDepartmentEmployeeCount(DepartmentDTO department);

}
