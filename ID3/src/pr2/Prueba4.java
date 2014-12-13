package pr2;

import java.util.ArrayList;

import pr2.tree.Tree;

public class Prueba4 {
	
	private static Tree arbolmierder = new Tree();
	
	public static void id3(String[][] ejemplos, String[] atributos,
			double entropiaS, String padre){
		
		ArrayList<ArrayList<String>> dominios = 
				new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<Integer>> positivos = 
				new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> negativos = 
				new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Double>> entropias = 
				new ArrayList<ArrayList<Double>>();

		// Recorremos cada columna para separar los distintos dominios de cada
		// atributo y contar las ocurrencias positivas y negativas
		for (int at = 0; at < atributos.length - 1; at++){
			
			dominios.add(at, new ArrayList<String>());
			positivos.add(at, new ArrayList<Integer>());
			negativos.add(at, new ArrayList<Integer>());
			
			for (int ej = 0; ej < ejemplos.length; ej++){
				
				String dom = ejemplos[ej][at];
				String valor = ejemplos[ej][atributos.length - 1];
				
				// Si no estaba apuntado en dominios, lo apuntamos
				if (!dominios.get(at).contains(dom))
					dominios.get(at).add(dom);
				
				// Cogemos la posicion del dominio
				int pos = dominios.get(at).indexOf(dom);
				
				// Si no existe esa posicion en alguno de los arraylists, crearla
				if (positivos.get(at).size() <= pos)
					positivos.get(at).add(pos, 0);
				
				if (negativos.get(at).size() <= pos)
					negativos.get(at).add(pos, 0);
				
				int p = positivos.get(at).get(pos);
				int n = negativos.get(at).get(pos);
				
				if (valor.equals("si")) positivos.get(at).set(pos, p + 1);
				else if (valor.equals("no")) negativos.get(at).set(pos, n + 1);
					
			}
		}
		
		// Calculamos las entropias de cada dominio y las guardamos.
		for (int i = 0; i < atributos.length - 1; i++) {
			
			entropias.add(i, new ArrayList<Double>());
			
			for (int j = 0; j < dominios.get(i).size(); j++) {
				
				double p = positivos.get(i).get(j);
				double n = negativos.get(i).get(j);
				
				entropias.get(i).add(j, infor(p+n, p, n));
				
			}
		}
		
		// Calculamos la ganancia de cada atributo y sacamos el que mayor
		// ganancia tenga
		double gananciaMejor = 0;
		int atMejor = 0;
		
		for (int i = 0; i < atributos.length - 1; i++) {
			
			double ganancia = entropiaS;
			
			for (int j = 0; j < dominios.get(i).size(); j++) {
				
				double pn = positivos.get(i).get(j) + 
							negativos.get(i).get(j);
				
				ganancia -= (pn/ejemplos.length) * entropias.get(i).get(j);
				
			}
			
			if (ganancia > gananciaMejor){
				
				gananciaMejor = ganancia;
				atMejor = i;
				
			}
		}
		
		if (padre == null) arbolmierder.addNode(atributos[atMejor]);
		else arbolmierder.addNode(atributos[atMejor], padre);
		
		// Para cada dominio del atributo mejor, seguir el algoritmo.
		for (int v = 0; v < dominios.get(atMejor).size(); v++){
			
			String dominio = dominios.get(atMejor).get(v);
			
			arbolmierder.addNode(dominio, atributos[atMejor]);
			
			int longitud = positivos.get(atMejor).get(v) + 
							negativos.get(atMejor).get(v);
			
			int fila = 0;
			
			// Lista auxiliar para guardar las filas en las que aparece el dominio. 
			String[][] lista = new String[longitud][atributos.length];
			
			for (int i = 0; i < ejemplos.length; i++){
				
				// Si en ejemplos encontramos un valor con el dominio v, lo metemos en lista
				if (ejemplos[i][atMejor].equals(dominio)){
					
					for (int j = 0; j < ejemplos[i].length; j++){
						
						lista[fila][j] = ejemplos[i][j];
						
					}
					
					fila++;
					
				}
			}
			
			// Si la entropia es 0, no se sigue y se mira si va a positivo o negativo
			if (entropias.get(atMejor).get(v) == 0){
				
				if (positivos.get(atMejor).get(v) == 0)
					arbolmierder.addNode("-", dominio);
				else 
					arbolmierder.addNode("+", dominio);
				
			}
			else id3(lista, atributos,
					entropias.get(atMejor).get(v), dominio);
			
			
		}
	}

	private static double infor(double total, double pos, double neg){
		
		double p = pos/total;
		double n = neg/total;
		
		double p1 = 0;
		if (p > 0) p1 = -p*(Math.log10(p)/Math.log10(2));
		
		double p2 = 0;
		if (n > 0) p2 = n*(Math.log10(n)/Math.log10(2));
		
		return p1 - p2;
		
	}

	private static double calcularEntropiaTodos(String[][] ejemplos, 
			String[] atributos) {
		
		double ex = ejemplos.length; // Numero total de ejemplos
		double pos = 0; // Numero total de positivos
		double neg = 0; // Numero total de negativos
		
		// Contamos el total de negativos y positivos
		for (int i = 0; i < ejemplos.length; i++) {
			
			if (ejemplos[i][atributos.length - 1].equals("si")) pos++;
			else if (ejemplos[i][atributos.length - 1].equals("no")) neg++;
			
		}
		
		// Devolvemos la entropia total
		return infor(ex, pos, neg);
		
	}
	
	public static void main(String[] args) {
		
		String[] atributos = 
			{ "TiempoExterior", "Temperatura", "Humedad", "Viento", "Jugar" };
		
		String[][] ejemplos =
			{ {"soleado", "caluroso", "alta", "falso", "no"},
			  {"soleado", "caluroso", "alta", "verdad", "no"},
			  {"nublado", "caluroso", "alta", "falso", "si"},
			  {"lluvioso", "templado", "alta", "falso", "si"},
			  {"lluvioso", "frio", "normal", "falso", "si"},
			  {"lluvioso", "frio", "normal", "verdad", "no"},
			  {"nublado", "frio", "normal", "verdad", "si"},
			  {"soleado", "templado", "alta", "falso", "no"},
			  {"soleado", "frio", "normal", "falso", "si"},
			  {"lluvioso", "templado", "normal", "falso", "si"},
			  {"soleado", "templado", "normal", "verdad", "si"},
			  {"nublado", "templado", "alta", "verdad", "si"},
			  {"nublado", "caluroso", "normal", "falso", "si"},
			  {"lluvioso", "templado", "alta", "verdad", "no"} };
		
		double entropia = calcularEntropiaTodos(ejemplos, atributos);
		
		id3(ejemplos, atributos, entropia, null); 
		
		arbolmierder.display("TiempoExterior");
        
	}
}
