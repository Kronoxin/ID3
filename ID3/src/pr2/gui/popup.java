/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pr2.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
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
       
        
        icono_ok = new ImageIcon("Imagenes/iconOK.png");

      JOptionPane pane = new JOptionPane();

      pane.showMessageDialog(null, "","Verificado",JOptionPane.INFORMATION_MESSAGE, icono_ok);
      pane.setBackground(Color.GREEN);
      pane.setForeground(Color.GREEN);
      
      
    }
    
    public static void no(){
        
        Icon icono_no;
        icono_no = new ImageIcon("Imagenes/iconNO.png");
        JOptionPane pane = new JOptionPane();

      pane.showMessageDialog(null, "","Denegado",JOptionPane.ERROR_MESSAGE, icono_no);
      pane.setBackground(Color.red);
      pane.setForeground(Color.red);
      
      
    }
    
}
