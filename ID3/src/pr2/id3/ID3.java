package pr2.id3;

import java.util.ArrayList;

import pr2.tree.Tree;

public class ID3 {
	
	public static Tree arbolmierder = new Tree();
	private static ArrayList<ArrayList<String>> tabla =
			new ArrayList<ArrayList<String>>();
	
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
					arbolmierder.addNode(
							atributos[atributos.length - 1] + " no", dominio);
				else 
					arbolmierder.addNode(
							atributos[atributos.length - 1] + " si", dominio);
				
			}
			else id3(lista, atributos,
					entropias.get(atMejor).get(v), dominio);
			
			
		}
	}

	public static void getRules(String[] atributos){
		
		ArrayList<String> at = new ArrayList<String>();
		
		for (int i = 0; i < atributos.length; i++) {
			
			at.add(atributos[i]);
			
		}
		
		ArrayList<String> rules = new ArrayList<String>();
		
		arbolmierder.toRules(rules, "", null);
		
		for (int fila = 0; fila < rules.size(); fila++) {
			
			tabla.add(fila, new ArrayList<String>());
			
			// Inicializamos a null la fila
			for(int i = 0; i < atributos.length; i++)
				tabla.get(fila).add(null);
			
			String[] reglasResult = rules.get(fila).split(">");
			String reglas = reglasResult[0];
			String result = reglasResult[1];
			
			String[] valores = reglas.split("&");
			
			for (int i = 0; i < valores.length; i++) {
				
				String val = valores[i].replace("|", "");
				
				String[] igual = val.split("=");
				
				int pos = at.indexOf(igual[0]);
				
				tabla.get(fila).set(pos, igual[1]);
				
			}
			
			if (result.endsWith("si")) result = "si";
			else result = "no";
			tabla.get(fila).set(atributos.length-1, result);
			
		}
		
		for (int i = 0; i < tabla.size(); i++){
			for(int j = 0; j < tabla.get(i).size(); j++){

				System.out.print(tabla.get(i).get(j) + " ");
				
			}
			
			System.out.println();
		}
	}
	
	public static boolean answer(ArrayList<String> values){
		
		int fila = 0;
		boolean encontrado = false;
		
		while (fila < tabla.size() && !encontrado){
			
			encontrado = true;
			
			for(int j = 0; j < values.size(); j++){

				if (tabla.get(fila).get(j) != null){
					
					encontrado &= tabla.get(fila).get(j).equals(values.get(j));
					
				}
				
			}
			
			fila++;

		}
		
		return tabla.get(fila-1).get(tabla.get(fila-1).size()-1).equals("si");
		
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

	public static double calcularEntropiaTodos(String[][] ejemplos, 
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

}
