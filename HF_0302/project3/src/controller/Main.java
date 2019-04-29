package controller;

import java.sql.SQLException;
import model.NewEmployeeModel;
import model.SalaryUpdateModel;
import view.NewEmployeeView;
import view.SalaryUpdateView;
import view.View;

public class Main {
  public static void main(String[] args) {
		try {
			SalaryUpdateModel sum  = new SalaryUpdateModel();
      NewEmployeeModel nem = new NewEmployeeModel();
      SalaryUpdateView suv = new SalaryUpdateView();
      NewEmployeeView nev = new NewEmployeeView();
      View view = new View(suv, nev);
      SalaryUpdateController suc = new SalaryUpdateController(sum, suv);
      NewEmployeeController nec = new NewEmployeeController(nem, nev);
      view.setController(suc, nec);
      suv.setController(suc);
      nev.setController(nec);
      sum.setController(suc);
      nem.setController(nec);
      suc.init();
      nec.init();
      view.showMainWindow();
		} 
    catch (SQLException ex) {
			view.Messages.showWarningMessage("Salary update 1.0 - Failed to connect", "SQL access failed, the program extis. Please make shure the SQL server is available.");
		}
    catch (ClassNotFoundException ex) {
      view.Messages.showWarningMessage("Salary update 1.0 - Failed to connect", "Missing driver.");
    }
  }
}