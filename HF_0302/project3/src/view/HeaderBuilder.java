package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.SystemColor;
import javax.swing.JPanel;


public class HeaderBuilder {
  
  public static JPanel buildHeader(){
    JPanel pnHeader = new JPanel(new GridLayout(5, 1));
    JPanel pnColored = new JPanel();
    JPanel pnColored1 = new JPanel();
    JPanel pnColored2 = new JPanel();
    JPanel pnColored3 = new JPanel();
    pnColored.setBackground(SystemColor.window);
    pnColored1.setBackground(new Color(0, 153, 51));
    pnColored2.setBackground(new Color(51, 255, 119));
    pnColored3.setBackground(new Color(179, 255, 179));
    pnHeader.add(pnColored);
    pnHeader.add(pnColored1);
    pnHeader.add(pnColored2);
    pnHeader.add(pnColored3);
    return pnHeader;
  }
}
