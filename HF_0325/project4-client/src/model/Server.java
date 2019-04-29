package model;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.dtos.DepartmentDTO;
import model.dtos.EmployeeDTO;
import model.dtos.JobDTO;
import model.dtos.ManagerDTO;

public class Server {
	
	RMIInterface stub;
  public void connect(){
	 
	 
	}
  public boolean connectRemote(){
		boolean success;
	 try {
      Registry registry = LocateRegistry.getRegistry("localhost", 1099);
      System.out.println(registry);
      stub = (RMIInterface) registry.lookup("SERVICE");
			System.out.println(stub.getEmployeeList());
			System.out.println(stub.getDepartmentList());
			System.out.println(stub.getManagerList());
			System.out.println(stub.getJobList());
			System.out.println(stub.getSimilarEmailList("a"));
			success = true;
	 }catch(Exception e) {
		 e.printStackTrace();
		 success=false;
	 }
	 return success;
  }
  
  public boolean disconnectRemote(){
    return false;
  }
  
  public UserPOJO login(String userName, String password){
		UserPOJO user = new UserPOJO("fake", -1);
		try {
			Registry registry = LocateRegistry.getRegistry("localhost", 1099);
			try {
				stub = (RMIInterface) registry.lookup("SERVICE");
			} catch (NotBoundException ex) {
				Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
			} catch (AccessException ex) {
				Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
			}
			user = stub.login(userName, password);
		} catch (RemoteException ex) {
			Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
		}
		return user;
  }
	
  //public boolean logout(); //just on client side
  public ArrayList<String> getSimilarEmailList(String priferredEmail){
		try {
			return stub.getSimilarEmailList(priferredEmail);
		} catch (RemoteException ex) {
			Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
  }
	
  public ArrayList<DepartmentDTO> getDepartmentList(){
		try {
			return stub.getDepartmentList();
		} catch (RemoteException ex) {
			Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
  }
	
  public ArrayList<JobDTO> getJobList(){
		try {
			return stub.getJobList();
		} catch (RemoteException ex) {
			Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
  }
	
  public ArrayList<ManagerDTO> getManagerList(){
		try {
			return stub.getManagerList();
		} catch (RemoteException ex) {
			Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
  }

  public ArrayList<EmployeeDTO> getEmployeeList(){
    try{
      return stub.getEmployeeList();
    }
    catch (RemoteException ex){
      Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
  }
	
  public long insertEmployee(EmployeeDTO employee){
		try {
			return stub.insertEmployee(employee);
		} catch (RemoteException ex) {
			Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
		}
		return 0;
  }
	
  public long updateSalary(EmployeeDTO employee){
		try {
			return stub.updateSalary(employee);
		} catch (RemoteException ex) {
			Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
		}
		return 0;
  }
	
//	public static void main(String[] args) {
//		connectRemote();
//	}
}
