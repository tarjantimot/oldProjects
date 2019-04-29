package model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.dtos.DepartmentDTO;
import model.dtos.EmployeeDTO;
import model.dtos.JobDTO;
import model.dtos.ManagerDTO;
import model.entities.DepartmentEntity;
import model.entities.EmployeeEntity;
import model.entities.JobEntity;
import model.entities.ManagerEntity;

public class SQLUtils {
  private EntityManagerFactory entityManagerFactory;

  public SQLUtils() {
    entityManagerFactory = Persistence.createEntityManagerFactory("project3PU");
  }
  
  

  public ArrayList<DepartmentDTO> getDepartmentList(){
    ArrayList<DepartmentDTO> departmentDAOList = new ArrayList<>();
    EntityManager em = entityManagerFactory.createEntityManager();
    Query q = em.createQuery("SELECT D FROM DepartmentEntity D ORDER BY D.departmentName");
    List<DepartmentEntity> departmentList = (List<DepartmentEntity>) q.getResultList();
    for (DepartmentEntity department : departmentList) {
      ManagerEntity manager = department.getManager();
      ManagerDTO managerDTO = null;
      if(manager != null){
        managerDTO = new ManagerDTO(department.getManager().getEmployeeId(),
              department.getManager().getFirstName(), department.getManager().getLastName(), 
              department.getManager().getManagerId());
      }
      departmentDAOList.add(new DepartmentDTO(department.getDepartmentId(),
              department.getDepartmentName(), managerDTO));
    }
    em.close();
    return departmentDAOList;
  }
  
  
  
  public ArrayList<JobDTO> getJobList(){
    ArrayList<JobDTO> jobDTOList = new ArrayList<>();
    EntityManager em = entityManagerFactory.createEntityManager();
    Query q = em.createQuery("SELECT J FROM JobEntity J ORDER BY J.jobTitle");
    List<JobEntity> jobList = (List<JobEntity>) q.getResultList();
    for (JobEntity job : jobList) {
      jobDTOList.add(new JobDTO(job.getJobId(), job.getJobTitle(), 
              job.getMinSalary(), job.getMaxSalary()));
    }
    em.close();
    return jobDTOList;
  }
  
  
  
  public ArrayList<ManagerDTO> getManagerList(){
    EntityManager em = entityManagerFactory.createEntityManager();
    Query q = em.createQuery("SELECT M FROM ManagerEntity M ORDER BY M.lastName, M.firstName");
    List<ManagerEntity> managerList = (List<ManagerEntity>) q.getResultList();
    ArrayList<ManagerDTO> managerDTOList = new ArrayList<>();
    for (ManagerEntity manager : managerList) {
      managerDTOList.add(new ManagerDTO(manager.getEmployeeId(), manager.getFirstName(),
              manager.getLastName(), manager.getManagerId()));
    }
    em.close();
    return managerDTOList;
  }

  public ArrayList<String> getSimilarEmailList(String preferredEmail){
    EntityManager em = entityManagerFactory.createEntityManager();
    String eMail = preferredEmail.toUpperCase();
    Query q = em.createQuery("SELECT e.eMail FROM EmployeeEntity e "
            + "WHERE UPPER(e.eMail) = '" + eMail + "' OR UPPER(e.eMail) LIKE '" + eMail + "%'");
    List<String> similarEMails = (List<String>) q.getResultList();
    ArrayList<String> returnList = new ArrayList<>();
    returnList.addAll(similarEMails);
    em.close();
    return returnList;
  }
  
	public ArrayList<EmployeeDTO> getEmployeeList() {
		EntityManager em = entityManagerFactory.createEntityManager();
		Query q = em.createQuery("SELECT E FROM EmployeeEntity E ORDER BY E.lastName");
		List<EmployeeEntity> employeeList = (List<EmployeeEntity>) q.getResultList();
		ArrayList<EmployeeDTO> employeeDTOList = new ArrayList<>();
		for (EmployeeEntity employee : employeeList) {
			ManagerEntity manager = employee.getManager();
      ManagerDTO managerDTO = null;
      if(manager != null){
        managerDTO = new ManagerDTO(employee.getManager().getEmployeeId(),
              employee.getManager().getFirstName(), employee.getManager().getLastName(), 
              employee.getManager().getManagerId());
      }
			JobEntity job = employee.getJob();
			JobDTO jobDTO = null;
			if(null!=job){
				jobDTO = new JobDTO(job.getJobId(), job.getJobTitle(), job.getMinSalary(), job.getMaxSalary());
			}
			DepartmentEntity department = employee.getDepartment();
			DepartmentDTO departmentDTO = null;
			if(null != department){
				departmentDTO = new DepartmentDTO(department.getDepartmentId(), department.getDepartmentName(), managerDTO);
			}
			employeeDTOList.add(new EmployeeDTO(employee.getEmployeeId(), employee.getFirstName(), employee.getLastName(), employee.geteMail(), employee.getPhoneNumber(),
							jobDTO, employee.getSalary(), employee.getCommission(), managerDTO, departmentDTO));
		}
		em.close();
    return employeeDTOList;
	}
//  public long saveNewEmployee(){
//    return saveNewEmployee(firstName, lastName, email, phone, departmentId, jobId, salary, commission, managerId);
//  }
  
  public long saveNewEmployee(EmployeeDTO employee){
    long returnValue = 0;
    
		if(null!=employee){
			try{
			EntityManager em = entityManagerFactory.createEntityManager();
			DepartmentEntity department = em.find(DepartmentEntity.class, employee.getDepartment().getDepartmentId());
			JobEntity job = em.find(JobEntity.class, employee.getJob().getJobId());
			ManagerEntity manager = em.find(ManagerEntity.class, employee.getManager().getEmployeeId());
			EmployeeEntity newEmployee = new EmployeeEntity(employee.getFirstName(), employee.getLastName(), employee.geteMail().toUpperCase(), employee.getPhoneNumber(),
							job, employee.getSalary(), employee.getComission(), manager, department);
			newEmployee.setHireDate();
			em.getTransaction().begin();
			em.persist(newEmployee);
			em.getTransaction().commit();
			long employeeId = newEmployee.getEmployeeId();
			returnValue = employeeId;
			em.close();
			}catch (Exception e){
				returnValue=-1;
			}
		}
    return returnValue;
	}
	
	public long updateSalary(EmployeeDTO empl) {
		int returnValue;
    if(null!=empl){
			try{
				EntityManager em = entityManagerFactory.createEntityManager();
				EmployeeEntity employee = em.find(EmployeeEntity.class, empl.getEmployeeId());
				employee.setSalary(empl.getSalary());
        em.getTransaction().begin();
        em.persist(employee);
        em.getTransaction().commit();
				em.close();
				returnValue=2;
			} catch (Exception e){
				returnValue=1;
			}
		} else {
			returnValue=0;
		}
    return returnValue;
  }

}
