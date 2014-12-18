/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pr2.gui;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author Ruben
 */
public class SobreNosotros extends JFrame{
       private JPanel contenedor;			//contenedor principal
    private JTextArea Instrucciones;	//textArea donde escribiremos las instrucciones de la aplicaciï¿½n
	
	Font fuente_para_texto = new Font("Verdana" , Font.BOLD, 15);		//objeto Font para dar un tipo de letra al textArea de Instrucciones
	String texto;	
        
    public SobreNosotros(){
        
         super("Sobre Nosotros");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);			//cierrate al pulsar X en la ventana
		setBounds(10, 10, 500, 600);							//definimos ( x, y , ancho , alto ) del frame
		contenedor = new JPanel();
		
		texto ="Sobre Nosotros : \n" +
				"\nEsta es una práctica desarrollada para la asignatura:\n"
                        + "\nIngeniería del Conocimiento\n"
                        + "\nPor los alumnos: \n" 
                        + "\nDaniel Lago Aguado"
                        + "\nRubén Gómez Fuentes." +
                       
				"";
		
		Instrucciones = new JTextArea();
		Instrucciones.setBackground(new Color(238,238,238));
		Instrucciones.setEditable(false);
		Instrucciones.setFont(fuente_para_texto);
		Instrucciones.setText(texto);
		

		contenedor.add(Instrucciones);
		setContentPane(contenedor);								//aï¿½adimos el panel contenedor al frame
		
                this.pack();
                this.setLocationRelativeTo(null);
                this.setVisible(true);
        
        
    }
    
}
