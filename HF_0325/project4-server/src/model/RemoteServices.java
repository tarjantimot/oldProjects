
package model;

import java.io.File;
import java.io.IOException;
import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import model.dtos.DepartmentDTO;
import model.dtos.EmployeeDTO;
import model.dtos.JobDTO;
import model.dtos.ManagerDTO;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class RemoteServices {

	private SQLUtils SQLUtils;

	public RemoteServices() {
		SQLUtils = new SQLUtils();
	}
	
	private class RemoteDataProvider extends UnicastRemoteObject implements RMIInterface {

		public RemoteDataProvider() throws RemoteException {
		}
		
		@Override
		public UserPOJO login(String userName, String password) {
			List<String> userNames = new ArrayList<>();
			List<String> passwords = new ArrayList<>();
			UserPOJO user=null;
			userName = userName.toUpperCase();
			try {
				File file = new File("./files/HrUser.xml");
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = dbFactory.newDocumentBuilder();
				Document doc = builder.parse(file);
				NodeList list = doc.getElementsByTagName("staff");
				for (int i = 0; i < list.getLength(); i++) {
					Node nNode = list.item(i);
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
						Element element = (Element) nNode;
						userNames.add(element.getElementsByTagName("username").item(0).getTextContent());
						passwords.add(element.getElementsByTagName("password").item(0).getTextContent());
					}
				}
			} catch (ParserConfigurationException ex) {
					java.util.logging.Logger.getLogger(RemoteDataProvider.class.getName()).log(Level.SEVERE, null, ex);
			} catch (SAXException ex) {
					java.util.logging.Logger.getLogger(RemoteDataProvider.class.getName()).log(Level.SEVERE, null, ex);
			} catch (IOException ex) {
					java.util.logging.Logger.getLogger(RemoteDataProvider.class.getName()).log(Level.SEVERE, null, ex);
			}
			int loginIndex;
			loginIndex = userNames.indexOf(userName);
			System.out.println(loginIndex);
			if(loginIndex>=0){
			if(BCrypt.checkpw(password, passwords.get(loginIndex)))
				 user = new UserPOJO(userName, loginIndex);
			} else{
				user = new UserPOJO("", -1);
			}
;
				 
				return user;
		}
	

		@Override
		public ArrayList<String> getSimilarEmailList(String priferredEmail) {
			return SQLUtils.getSimilarEmailList(priferredEmail);
		}

		@Override
		public ArrayList<DepartmentDTO> getDepartmentList() {
			return SQLUtils.getDepartmentList();
		}

		@Override
		public ArrayList<JobDTO> getJobList() {
			return SQLUtils.getJobList();
		}

		@Override
		public ArrayList<ManagerDTO> getManagerList() {
			return SQLUtils.getManagerList();
		}

		@Override
		public long insertEmployee(EmployeeDTO employee) {
			return  SQLUtils.saveNewEmployee (employee);
		}

		@Override
		public long updateSalary(EmployeeDTO employee) {
			return SQLUtils.updateSalary(employee);
		}

		@Override
		public ArrayList<EmployeeDTO> getEmployeeList() throws RemoteException {
			return SQLUtils.getEmployeeList();
		}
	}	
		
	public void StartServer(){
		try {
			System.out.println("Fut a szerver...");
			Registry registry = LocateRegistry.createRegistry(1099);
			registry.bind("SERVICE", new RemoteDataProvider());
		} 
		catch (AccessException ex) {
		} 
		catch (RemoteException ex) {
		} 
		catch (AlreadyBoundException ex) {
		}
	}
}

