
package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Logger {

  File logFile = new File("./files/log.txt");
  
  public void logToFile (String log) {
    if(null!= log) {
      try (BufferedWriter bw = new BufferedWriter(new FileWriter(logFile, true))) {
        bw.write(new Date(System.currentTimeMillis())+" : "+ log);
        bw.newLine();
      } catch (IOException ex) {
      }
    }
  }
}

  

