/*
Arminder Khinda
Date: 02/13/2018
Comp 585
Purpose: Separate menu bar object to put at the top of the calculator
 */

package calculator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;


public class MenuBar extends JMenuBar {
    
    // Menu
    private JMenuBar menuBar;
    private JMenu appMenu;
    private JMenu helpMenu;
    private JMenuItem exitMenuItem;
    private JMenuItem aboutMenuItem;
    
    public MenuBar() {
        menuBar = new JMenuBar();
        
        // menu
        appMenu = new JMenu("Application");
        helpMenu = new JMenu("Help");
        
        // menu items
        exitMenuItem = new JMenuItem("Exit");
        aboutMenuItem = new JMenuItem("About");
        
        // add menu items to menu
        appMenu.add(exitMenuItem);
        helpMenu.add(aboutMenuItem);
        
        // add menus to bar
        menuBar.add(appMenu);
        menuBar.add(helpMenu);
        
        // menu item listeners
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
        aboutMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(null,"Thanks for using my app.");
            }
        });
        
        this.add(menuBar);
    }       
}
