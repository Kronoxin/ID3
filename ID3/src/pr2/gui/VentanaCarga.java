package pr2.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import pr2.Str;

public class VentanaCarga {
	
	private JFrame ventana;
	
	private JPanel panelPrin;
	
	private JButton cargarAtributos;
	private JButton cargarEjemplos;
	private JButton empezar;
	private String[] atributos;
	private String[][] ejemplos;
	
	public VentanaCarga(){
		
		ventana = new JFrame(Str.TITLE);
		
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setMinimumSize(new Dimension(400, 50));
		ventana.setMaximumSize(new Dimension(400, 50));
		ventana.setResizable(false);
		ventana.setLocation(0, 0);
		ventana.setVisible(true);
		
		panelPrin = new JPanel(new BorderLayout());
		
		panelPrin.add(panelBotones(), BorderLayout.NORTH);		
		
		ventana.add(panelPrin);
		
		ventana.pack();
		
	}
	
	private JPanel panelBotones(){
		
		JPanel botones = new JPanel(new GridLayout(3,1));
		
		cargarAtributos = new JButton(Str.BOTON_CARGAR_ATR);
		cargarAtributos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				try{ atributos = FileManager.loadAtributos(); }
				catch(Exception e){
					
					JOptionPane.showMessageDialog(ventana, 
							Str.ERROR_CARGAR_ATR);
					
				}
			}
		});
		cargarEjemplos = new JButton(Str.BOTON_CARGAR_EJ);
		cargarEjemplos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				try{ ejemplos = FileManager.loadEjemplos(); }
				catch(Exception e){
					
					JOptionPane.showMessageDialog(ventana, 
							Str.ERROR_CARGAR_EJ);
					
				}
				
			}
		});
		
		empezar = new JButton(Str.BOTON_EMPEZAR);
		empezar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if (atributos == null)
					JOptionPane.showMessageDialog(ventana, 
							Str.ERROR_FALTA_ATRIBUTOS);
				
				else if (ejemplos == null)
					JOptionPane.showMessageDialog(ventana, 
							Str.ERROR_FALTA_EJEMPLOS);
				
				else if (atributos.length != ejemplos[0].length)
					JOptionPane.showMessageDialog(ventana, 
							Str.ERROR_NUMERO_AE);
				
				else new VentanaTabla(atributos, ejemplos);
				
			}
		});
		
		JButton ayuda = new JButton("Ayuda");
		ayuda.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				JOptionPane.showMessageDialog(ventana, "El archivo de atributos debe tener "
						+ ""
						+ ""
						+ "");
				
			}
		});
		
		
		botones.add(cargarAtributos);
		botones.add(cargarEjemplos);
		botones.add(empezar);
		return botones;
		
	}

}
