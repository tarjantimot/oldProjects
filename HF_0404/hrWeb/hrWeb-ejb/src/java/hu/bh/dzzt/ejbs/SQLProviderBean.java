package hu.bh.dzzt.ejbs;

import hu.bh.dzzt.dtos.DepartmentDTO;
import hu.bh.dzzt.dtos.EmployeeDTO;
import hu.bh.dzzt.dtos.JobDTO;
import hu.bh.dzzt.dtos.ManagerDTO;
import hu.bh.dzzt.enities.DepartmentEntity;
import hu.bh.dzzt.enities.EmployeeEntity;
import hu.bh.dzzt.enities.JobEntity;
import hu.bh.dzzt.enities.ManagerEntity;
import hu.bh.dzzt.enities.RoleEntity;
import hu.bh.dzzt.enities.UserEntity;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TransactionRequiredException;
import javax.persistence.TypedQuery;

@Stateless
@Remote(value=SQLProviderRemote.class)
public class SQLProviderBean implements SQLProviderRemote{

  @PersistenceContext(unitName = "HrSchemePU")
  private EntityManager emHr;

  @PersistenceContext(unitName = "DerbyHrUserPU")
  private EntityManager emHrUser;

  @Override
  public int validateLogin(String userName, String pwd) {
    int result = 0;
    TypedQuery<UserEntity> tq = emHrUser.createQuery("SELECT ue FROM UserEntity ue WHERE UPPER(ue.userName) = UPPER('" + userName + "')", UserEntity.class);
    UserEntity user = tq.getSingleResult();
    if (user != null && user.getUserPwd().equals(pwd)) {
      if (user.getUserRole().getValue() != null) //Get the role
      {
        result = user.getUserRole().getValue();
      } else {
        //Has no role
        result = -1;
      }
    } else {
      //Wrong user name or password
      result = -2;
    }
    return result;
  }

  @Override
  public int insertUser(String userName, String password, int role) {
    int result = 0;
    if (userName != null && userName.matches("[A-Za-z][A-Za-z0-9]{3,}")) {
      TypedQuery<UserEntity> tqu = emHrUser.createQuery("SELECT ue FROM UserEntity ue WHERE UPPER(ue.userName) = UPPER('" + userName + "')", UserEntity.class);
      UserEntity user = tqu.getSingleResult();
      if (user == null) {
        if (password != null && password.matches("[A-Za-z0-9 -_]{5,}")) {
          user = new UserEntity();
          user.setUserName(userName);
          user.setUserPwd(password);
          TypedQuery<RoleEntity> tqr = emHrUser.createQuery("SELECT r FROM RoleEntity r WHERE r.value = " + role, RoleEntity.class);
          RoleEntity r = tqr.getSingleResult();
          if (r != null) {
            user.setUserRole(r);
            try {
              emHrUser.persist(user);
              result = 0;
            } catch (EntityExistsException | IllegalArgumentException | TransactionRequiredException ex) {
              //database error
              result = -4;
              ;
            }
          } else {
            //wrong role
            result = -7;
          }
        } else {
          //wrong password
          result = -6;
        }
      } else {
        //user exists
        result = -3;
      }
    } else {
      //wrong user name
      result = -5;
    }
    return result;
  }

  @Override
  public int insertRole(String name, int value) {
    int result = 0;
    TypedQuery<RoleEntity> tqr = emHrUser.createQuery("SELECT r FROM RoleEntity r WHERE r.value = " + value, RoleEntity.class);
    RoleEntity r = tqr.getSingleResult();
    if (r == null) {
      tqr = emHrUser.createQuery("SELECT r FROM RoleEntity r WHERE UPPER(r.name) = UPPER('" + name +"')", RoleEntity.class);
      r = tqr.getSingleResult();
      if(r == null){
        r = new RoleEntity();
        r.setName(name);
        r.setValue(value);
        emHrUser.persist(r);
      }
      else{
        //wrong name
        result = -9;
      }
    }
    else{
      //value exists
      result = -8;
    }
    return result;
  }
  
  @Override
  public List<DepartmentDTO> getDepartmentList(){
    List<DepartmentDTO> departmentDAOList = new ArrayList<>();
    TypedQuery<DepartmentEntity> tq = emHr.createQuery("SELECT D FROM DepartmentEntity D ORDER BY D.departmentName", DepartmentEntity.class);
    List<DepartmentEntity> departmentList = tq.getResultList();
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
    return departmentDAOList;
  }
  
  @Override
  public List<JobDTO> getJobList(){
    List<JobDTO> jobDTOList = new ArrayList<>();
    TypedQuery<JobEntity> tq = emHr.createQuery("SELECT J FROM JobEntity J ORDER BY J.jobTitle", JobEntity.class);
    List<JobEntity> jobList = tq.getResultList();
    for (JobEntity job : jobList) {
      jobDTOList.add(new JobDTO(job.getJobId(), job.getJobTitle(), 
              job.getMinSalary(), job.getMaxSalary()));
    }
    return jobDTOList;
  }
  
  @Override
  public List<ManagerDTO> getManagerList(){
    List<ManagerDTO> managerDTOList = new ArrayList<>();
    TypedQuery<ManagerEntity> tq = emHr.createQuery("SELECT M FROM ManagerEntity M ORDER BY M.lastName, M.firstName", ManagerEntity.class);
    List<ManagerEntity> managerList = tq.getResultList();
    for (ManagerEntity manager : managerList) {
      managerDTOList.add(new ManagerDTO(manager.getEmployeeId(), manager.getFirstName(),
              manager.getLastName(), manager.getManagerId()));
    }
    return managerDTOList;
  }
  
  @Override
	public List<EmployeeDTO> getEmployeeList() {
    System.out.println("employeelist start");
		List<EmployeeDTO> employeeDTOList = new ArrayList<>();
    TypedQuery<EmployeeEntity> tq = emHr.createQuery("SELECT E FROM EmployeeEntity E ORDER BY E.lastName", EmployeeEntity.class);
		List<EmployeeEntity> employeeList = tq.getResultList();
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
      EmployeeDTO employeeDTO = new EmployeeDTO(employee.getEmployeeId(), employee.getFirstName(), employee.getLastName(), employee.geteMail(), employee.getPhoneNumber(),
							jobDTO, employee.getSalary(), employee.getCommission(), managerDTO, departmentDTO);
			employeeDTOList.add(employeeDTO);
      System.out.println(employeeDTO);
		}
    System.out.println("List size: " + employeeDTOList.size());
    return employeeDTOList;
	}
  
  @Override
  public List<String> getSimilarEmailList(String preferredEmail){
    String eMail = preferredEmail.toUpperCase();
    TypedQuery<String> tq = emHr.createQuery("SELECT e.eMail FROM EmployeeEntity e "
            + "WHERE UPPER(e.eMail) = '" + eMail + "' OR UPPER(e.eMail) LIKE '" + eMail + "%'", String.class);
    List<String> similarEMails = tq.getResultList();
    List<String> returnList = new ArrayList<>();
    if(similarEMails != null)
      returnList.addAll(similarEMails);
    return returnList;
  }

}
