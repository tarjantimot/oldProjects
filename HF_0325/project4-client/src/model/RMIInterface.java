package model;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import model.dtos.DepartmentDTO;
import model.dtos.EmployeeDTO;
import model.dtos.JobDTO;
import model.dtos.ManagerDTO;

public interface RMIInterface extends Remote{
  public UserPOJO login(String userName, String password) throws RemoteException;
  //public boolean logout(); //just on client side
  public ArrayList<String> getSimilarEmailList(String priferredEmail)throws RemoteException;
  public ArrayList<DepartmentDTO> getDepartmentList()throws RemoteException;
  public ArrayList<JobDTO> getJobList()throws RemoteException;
  public ArrayList<ManagerDTO> getManagerList()throws RemoteException;
	public ArrayList<EmployeeDTO> getEmployeeList() throws RemoteException;
  public long insertEmployee(EmployeeDTO employee)throws RemoteException;
  public long updateSalary(EmployeeDTO employee) throws RemoteException;
}
