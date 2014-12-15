/**
 * FileManager.java
 * 
 * @author Rodrigo Claudio Miguez Rein, Alexis Vizcaya Hervella
 */
package pr2.gui;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileManager {
    
    private static String path = "";
	
	public static String[] loadAtributos(){
		
		String[] rej = null;
		
		JFileChooser chooser = new JFileChooser(path);
		chooser.setDialogTitle("Abrir Archivo de Atributos");
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Archivos .txt", "txt");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			
			String name = chooser.getSelectedFile().getPath();
                        
                        path=name;
					
			FileInputStream file;
			
			try {

				file = new FileInputStream(name);
				BufferedReader br = new BufferedReader(new InputStreamReader(
						file));

				String line = br.readLine();
				
				rej = line.split(",");

				br.close();
				
			} 
			catch (FileNotFoundException e) {

				JOptionPane.showMessageDialog(null, "Aqui falta un mensaje");

			} 
			catch (IOException e) {

				JOptionPane.showMessageDialog(null, "Aqui falta un mensaje");

			}
		}
		
		return rej;
		
	}
	
	public static String[][] loadEjemplos(){
		
		ArrayList<String[]> rej = new ArrayList<String[]>();
		String[][] tabla = null;
		
		JFileChooser chooser = new JFileChooser(path);
		chooser.setDialogTitle("Abrir Archivo de Ejemplos");
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Archivos .txt", "txt");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			
			String name = chooser.getSelectedFile().getPath();
                        
                        path=name;
					
			FileInputStream file;
			
			try {

				file = new FileInputStream(name);
				BufferedReader br = new BufferedReader(new InputStreamReader(
						file));

				String line = br.readLine();
				
				while(line != null){
					
					rej.add(line.split(","));
					
					line = br.readLine();
				
				}

				br.close();
				
			} 
			catch (FileNotFoundException e) {

				JOptionPane.showMessageDialog(null, "Aqui falta un mensaje");

			} 
			catch (IOException e) {

				JOptionPane.showMessageDialog(null, "Aqui falta un mensaje");

			}
			
			
			rej.remove(rej.size() - 1);
			
			tabla = new String[rej.size()][rej.get(0).length];
			
			for (int i = 0; i < rej.size(); i++) {
				for (int j = 0; j < rej.get(i).length; j++){

					tabla[i][j] = rej.get(i)[j];
					
				}
			}	
		}
		
		return tabla;
		
	}
}
