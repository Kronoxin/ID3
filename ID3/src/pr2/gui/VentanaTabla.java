package pr2.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import pr2.Str;
import pr2.id3.ID3;
import pr2.tree.Node;

public class VentanaTabla{
	
	private JFrame ventana;
	
	private Tabla tabla;
	private JTextArea arbol;
	
	public VentanaTabla(final String[] atributos, String[][] ejemplos){
		
		ventana = new JFrame(Str.TITLE);
		
		ventana.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		ventana.setMinimumSize(new Dimension(700, 400));
		ventana.setMaximumSize(new Dimension(700, 400));
		ventana.setResizable(false);
		ventana.setLocation(0, 0);
		ventana.setVisible(true);
		
		ventana.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) { }
			
			@Override
			public void windowIconified(WindowEvent e) { }
			
			@Override
			public void windowDeiconified(WindowEvent e) { }
			
			@Override
			public void windowDeactivated(WindowEvent e) { }
			
			@Override
			public void windowClosing(WindowEvent e) {
				
				ventana.dispose();
				
			}
			
			@Override
			public void windowClosed(WindowEvent e) { }
			
			@Override
			public void windowActivated(WindowEvent e) { }
			
		});
		
		JPanel panelPrin = new JPanel(new BorderLayout());
		
		tabla = new Tabla(ejemplos, atributos);
		
		JScrollPane panelTabla = new JScrollPane(tabla);
		panelTabla.setPreferredSize(
				new Dimension (2*ventana.getWidth()/3, ventana.getHeight()));
		
		arbol = new JTextArea();
		
		JScrollPane panelArbol = new JScrollPane(arbol);
		panelArbol.setPreferredSize(
				new Dimension (ventana.getWidth()/3, ventana.getHeight()));
				
		double entropia = ID3.calcularEntropiaTodos(ejemplos, atributos);
		ID3.id3(ejemplos, atributos, entropia, null);
		String arS = ID3.arbolmierder.muestra();
		ID3.getRules(atributos);
		
		arbol.setText(arS);
		arbol.setEditable(false);
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
												panelTabla, panelArbol);
		splitPane.setOneTouchExpandable(true);
		panelPrin.add(splitPane, BorderLayout.CENTER);
		
		//Panel para realizar la busqueda
		JPanel panelAtributos = new JPanel(new GridLayout(1,atributos.length-1));
		JPanel panelCombo = new JPanel(new GridLayout(1,atributos.length-1));
		
		final ArrayList<JComboBox<String>> combos = 
				new ArrayList<JComboBox<String>>();
		
		for (int i = 0; i<atributos.length-1; i++){
			
			panelAtributos.add(new JLabel(atributos[i]));
			
			Vector<String> lista = new Vector<>();
			
			for (int ej = 0; ej < ejemplos.length; ej++){
				
				String dom = ejemplos[ej][i];
								
				// Si no estaba apuntado en dominios, lo apuntamos
				if (!lista.contains(dom))
					lista.add(dom);
			
			}
			JComboBox<String> aux= new JComboBox<>(lista);
			combos.add(aux);
			panelCombo.add(aux);
		}
		
		//Panel con el boton de comprobar
		final JTextField suerte= new JTextField();
		suerte.setEditable(false);		
		
		JPanel panelComprobar = new JPanel(new GridLayout(1,2));
		JButton probar = new JButton("Comprobar");
		
		probar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				ArrayList<String> v = new ArrayList<String>();

				for(int i = 0; i < atributos.length - 1; i++){
					
					v.add((String)combos.get(i).getSelectedItem());
					
				}
				
				if (ID3.answer(v)) suerte.setBackground(new Color(0, 255, 0));
				else suerte.setBackground(new Color(255, 0, 0));
			}
		});
		
		panelComprobar.add(probar);
		panelComprobar.add(suerte);
		
		//Panel general para la accion de comprobar 
		JPanel panelBusqueda = new JPanel(new GridLayout(3,1));
		panelBusqueda.setBorder(new TitledBorder(""));
		panelBusqueda.add(panelAtributos);
		panelBusqueda.add(panelCombo);
		panelBusqueda.add(panelComprobar);
		
		
		panelPrin.add(panelBusqueda,BorderLayout.SOUTH);
		
		ventana.add(panelPrin);
		
		ventana.pack();
		
	}

}
