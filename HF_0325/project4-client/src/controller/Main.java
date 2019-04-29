package controller;

import model.LoginModel;
import model.NewEmployeeModel;
import model.SalaryUpdateModel;
import model.Server;
import view.NewEmployeeView;
import view.SalaryUpdateView;
import view.View;

public class Main {
  public static void main(String[] args) {
		try {
			Server server = new Server();
			server.connectRemote();
			SalaryUpdateModel sum  = new SalaryUpdateModel(server);
			LoginModel logInModel = new LoginModel();
      NewEmployeeModel nem = new NewEmployeeModel(server);
      SalaryUpdateView suv = new SalaryUpdateView();
      NewEmployeeView nev = new NewEmployeeView();
      View view = new View(suv, nev);
			LoginController logInController = new LoginController(logInModel, view);
      SalaryUpdateController suc = new SalaryUpdateController(sum, suv);
      NewEmployeeController nec = new NewEmployeeController(nem, nev);
      view.setController(suc, nec, logInController);
      suv.setController(suc);
      nev.setController(nec);
      sum.setController(suc);
      nem.setController(nec);
      suc.init();
      nec.init();
      view.showMainWindow();
		} 
    catch (Exception ex) {
			ex.printStackTrace();
			view.Messages.showWarningMessage("Salary update 1.0 - Failed to connect", "SQL access failed, the program extis. Please make shure the SQL server is available.");
		
    }
  }
}