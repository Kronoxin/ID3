/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pr2.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import javax.swing.*;


/**
 *
 * @author Ruben
 */
public class VentanaAyuda extends JFrame{
    private JPanel contenedor;			//contenedor principal
    private JTextArea Instrucciones;	//textArea donde escribiremos las instrucciones de la aplicaciï¿½n
	
	Font fuente_para_texto = new Font("Verdana" , Font.BOLD, 15);		//objeto Font para dar un tipo de letra al textArea de Instrucciones
	String texto;		
    
    public VentanaAyuda(){
        
    
    
       super("Instrucciones de uso");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);			//cierrate al pulsar X en la ventana
		setBounds(10, 10, 500, 600);							//definimos ( x, y , ancho , alto ) del frame
		contenedor = new JPanel();
		
		texto ="INSTRUCCIONES DE USO : \n" +
				"\nAntes de interactuar con el Algoritmo hay que cargar los ficheros de Ejemplo.\n"
                        + "\nPara ello debe ir a: \n"
                        + "Inicio --> Cargar fichero de atributos \n" 
                        + "Inicio --> Cargar fichero de ejemplo \n" +
                        
			"\nUna vez los ficheros estén cargados tendra que pulsar el botón Empezar:\n" +
			"\nEn esta nueva ventana podrá ver el Arbol de Decisión ID3 y comprobar \n"
                        + "seleccionando los parámetros deseados su correcto funcionamiento\n" +

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
