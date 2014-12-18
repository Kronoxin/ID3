/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pr2.gui;

import java.awt.Color;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Ruben
 */
public class popup {
     
    
    public popup(){
        super();
    }
    
    public static void si(){
        Icon icono_ok;
       
        
        icono_ok = new ImageIcon("src/images/iconOK.png");
        

      JOptionPane pane = new JOptionPane();

      pane.showMessageDialog(null, "","Verificado",JOptionPane.INFORMATION_MESSAGE, icono_ok);
      pane.setBackground(Color.GREEN);
      pane.setForeground(Color.GREEN);
  
      
      
    }
    
    public static void no(){
        
        Icon icono_no;
        icono_no = new ImageIcon("src/images/iconNO.png");
        JOptionPane pane = new JOptionPane();

      pane.showMessageDialog(null, "","Denegado",JOptionPane.ERROR_MESSAGE, icono_no);
      pane.setBackground(Color.red);
      pane.setForeground(Color.red);
      
      
    }
    
}
