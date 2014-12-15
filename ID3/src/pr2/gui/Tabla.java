package pr2.gui;

import java.awt.Color;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class Tabla extends JTable {
	
	private static final long serialVersionUID = 1L;
	
	private String[][] lista;
	private String[] cn;
	private TablaModel model;
	
	public Tabla(String[][] lista, String[] cn){
		
		super();
		
		this.lista = lista;
		this.cn = cn;
		
		model = new TablaModel();
		model.setColumnIdentifiers(cn);
		
		setModel(model);
		
		setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

		setVisible(true);
		
	}
	
	private class TablaModel extends DefaultTableModel{
		
		private static final long serialVersionUID = 1L;
		
		private TablaModel(){
			
			super();
			
		}
		
		public int getRowCount(){
			
			return lista.length;
			
		}
		
		public int getColumnCount() {
			
			return cn.length;
			
		}
		
		public String getValueAt(int row, int column){
			
			if(row >= 0){
			
				String[] reserva = lista[row];
				
				return reserva[column];
				
			}
			
			else return "Empty";
		
		}
		
		@Override
		public void setValueAt(Object value, int row, int column){
			
			lista[row][column] = (String)value;
			
		}
		
		
		public String getColumnName(int column){
			
			return cn[column];
			
		}
		
		public boolean isCellEditable(int row, int column) {
			
			return false;
				
		}
	}

}
