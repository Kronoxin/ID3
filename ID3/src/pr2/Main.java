package pr2;

import javax.swing.UIManager;
import pr2.gui.NuevaGUI;

import pr2.gui.VentanaCarga;

public class Main {

	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
	    } 
	    catch (Exception e) {}
		
		new NuevaGUI();
		
		
	}
}
