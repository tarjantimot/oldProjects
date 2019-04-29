package controller;

import java.sql.SQLException;
import model.Model;
import view.View;

public class Main {
  public static void main(String[] args) {
  Model model = null;
		try {
			model  = new model.Model();
      View view = new view.View();
      Controller controller = new Controller(model, view);
      view.setController(controller);
      model.setController(controller);
      controller.init();
		} 
    catch (SQLException ex) {
      model.getLogger().logToFile(ex.getMessage());
			view.Messages.showWarningMessage("Salary update 1.0 - Failed to connect", "SQL access failed, the program extis. Please make shure the SQL server is available.");
		}
    catch (ClassNotFoundException ex) {
      model.getLogger().logToFile(ex.getMessage());
      view.Messages.showWarningMessage("Salary update 1.0 - Failed to connect", "Missing driver.");
    }
  }
}