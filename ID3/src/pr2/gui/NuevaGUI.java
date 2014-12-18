/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pr2.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import pr2.Str;
import pr2.id3.ID3;

/**
 *
 * @author Ruben
 */
public class NuevaGUI extends JFrame{
    
    JPanel panel_menu;
    JPanel panel_principal;
    JTable tabla = new JTable();
    JTextArea arbol = new JTextArea();
    JMenuBar barra_menu = new JMenuBar();
    JMenu menu_inicio = new JMenu("Inicio");
    JMenuItem item_CargaFicheroAtributos = new JMenuItem("Cargar fichero de atributos...");
    JMenuItem item_CargaFicheroEjemplo = new JMenuItem("Cargar fichero de ejemplo...");
    JMenu menu_ayuda = new JMenu("Ayuda");
    JMenuItem item_Instrucciones = new JMenuItem("Instrucciones");
    JMenuItem item_Nosotros = new JMenuItem("Sobre nosotros..");
    JButton boton_empezar = new JButton("Empezar");
    JSeparator sep_ver = new JSeparator(SwingConstants.VERTICAL);
    JSeparator sep_hor = new JSeparator();
    
    private String[] atributos;
    private String[][] ejemplos;
    private Container contenedorPrincipal;
    
    Font fuente = new Font("Verdana", Font.BOLD,14);

            
    
    public NuevaGUI (){
        setTitle("Practica 2 - Algoritmo ID3");
        
        panel_menu = new JPanel(new BorderLayout(1, 2));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setBounds(100, 100, 1000, 800);
        contenedorPrincipal = new JPanel();
        contenedorPrincipal = this.getContentPane();
        contenedorPrincipal.setLayout(new BorderLayout(0,0));
        setContentPane(contenedorPrincipal);
        this.setLocationRelativeTo(null);
        setVisible(true);
        boton_empezar.setBackground(Color.CYAN);
        boton_empezar.setFont(fuente);
        menu_inicio.setFont(fuente);
        menu_inicio.setBackground(Color.GRAY);
        item_CargaFicheroAtributos.setFont(fuente);
        item_CargaFicheroEjemplo.setFont(fuente);
        item_Instrucciones.setFont(fuente);
        item_Nosotros.setFont(fuente);
        menu_ayuda.setFont(fuente);

        barra_menu.add(menu_inicio);
    //    barra_menu.add(sep_ver);
	barra_menu.add(menu_ayuda);
	
        menu_ayuda.add(item_Instrucciones);
        menu_ayuda.add(item_Nosotros);
	menu_inicio.add(item_CargaFicheroAtributos);

	menu_inicio.add(item_CargaFicheroEjemplo);
        menu_inicio.add(boton_empezar);
      
       
        barra_menu.add(sep_ver);
        barra_menu.add(boton_empezar);
        

      //  barra_menu.add(boton_empezar);
        panel_menu.add(barra_menu);
      //  panel_menu.add(boton_empezar);
        
         //boton_empezar.setPreferredSize(new Dimension(90,40));
        
        item_Instrucciones.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new VentanaAyuda();
            }
        });
        
        item_Nosotros.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new SobreNosotros();
            }
        });
        
        item_CargaFicheroAtributos.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                try{ atributos = FileManager.loadAtributos(); 
                if ( atributos != null && ejemplos != null) boton_empezar.setEnabled(true);
                }
                
		catch(Exception e){
					
		JOptionPane.showMessageDialog(NuevaGUI.this, 
			Str.ERROR_CARGAR_ATR);
					
				}
                
            }
        });
        
        item_CargaFicheroEjemplo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                try{ ejemplos = FileManager.loadEjemplos(); 
                if ( atributos != null && ejemplos != null) boton_empezar.setEnabled(true);
                
                }
		catch(Exception e){
					
		JOptionPane.showMessageDialog(NuevaGUI.this, 
			Str.ERROR_CARGAR_EJ);
					
				}
				
            }
        });
        boton_empezar.setEnabled(false);
        boton_empezar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (atributos == null)
					JOptionPane.showMessageDialog(NuevaGUI.this, 
							Str.ERROR_FALTA_ATRIBUTOS);
				
				else if (ejemplos == null)
					JOptionPane.showMessageDialog(NuevaGUI.this, 
							Str.ERROR_FALTA_EJEMPLOS);
				
				else if (atributos.length != ejemplos[0].length)
					JOptionPane.showMessageDialog(NuevaGUI.this, 
							Str.ERROR_NUMERO_AE);
				
				else contenedorPrincipal.add(new VentanaTabla2(atributos, ejemplos),BorderLayout.CENTER);
                                     
            }
        });
        
     //   panel_menu.add(barra_menu, BorderLayout.WEST);
     //   panel_menu.add( boton_empezar, BorderLayout.EAST);
        
   

        
        
        contenedorPrincipal.add(panel_menu, BorderLayout.NORTH);
      //  contenedorPrincipal.add(panel_principal, BorderLayout.CENTER);
      //  this.pack();


        
    }


    
    
    
    
}
