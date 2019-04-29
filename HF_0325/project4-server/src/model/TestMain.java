package model;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import model.dtos.DepartmentDTO;

public class TestMain {
	public static void main(String[] args) {
	 try {
			System.out.println("fut a kliens...");
      Registry registry = LocateRegistry.getRegistry("localhost", 1099);
      RMIInterface stub = (RMIInterface) registry.lookup("SERVICE");
			System.out.println(stub.getManagerList());
			System.out.println(stub.getDepartmentList());
			System.out.println(stub.getJobList());
			System.out.println(stub.getEmployeeList());
			System.out.println(stub.getSimilarEmailList("a"));
	 }catch(Exception e) {
		 e.printStackTrace();
	 }
	}
}
