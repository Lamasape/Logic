package Logica;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Resolucion {

	static String[] axiomas = { "¬q v ¬r v p", "q", "¬r" };

	public static void main(String[] args) {
		result("¬p");
	}

	public static void result(String original) {
		String axiomaOriginal = null;
		for (String ax : axiomas) {
			axiomaOriginal = comparacionCompleja(original, ax);
			// eliminamos del axiomaComplejo los axiomas que ya se han utilizado
			axiomas = eliminarArray(axiomas, ax);
			// volvemos a iterar
			if (!axiomaOriginal.equals("")) {
				result(axiomaOriginal);
			} else
				System.out.println(" Clausula Vacia");

		}

	}

	/**
	 * Compara el predicado original con el axiomaSimple, si son iguales devuelve
	 * true sino devuelve false.
	 * 
	 * @param original
	 * @param axioma
	 * @return
	 */
	public static boolean comparacionSimple(String original, String axioma) {

		if (original.contains("¬")) {
			String[] sinNegacionOriginal = original.split("¬");
			// trim() elimina los espacios en blanco
			if (sinNegacionOriginal[1].trim().equals(axioma.trim())) {
				return true;
			}
		} else if (axioma.contains("¬")) {
			String[] sinNegacionAxioma = axioma.split("¬");
			// trim() elimina los espacios en blanco
			if (sinNegacionAxioma[1].trim().equals(original.trim())) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Compara predicado original con axiomas complejos, si son iguales elimina el
	 * axioma de la lista de axiomas.
	 * 
	 * @param original
	 * @param axioma
	 * @return
	 */
	public static String comparacionCompleja(String original, String axioma) {

		String[] axiomas = axioma.split("v");
		String[] axiomaOriginal = original.split("v");
		String[] axiomaActualizado = null;

		// original puede ser mas largo o mas pequeño que el axioma
		for (int i = 0; i < axiomas.length; i++) {
			for (int j = 0; j < axiomaOriginal.length; j++) {

				if (comparacionSimple(axiomaOriginal[j], axiomas[i])) {
					axiomaActualizado = axiomas;
					// quitamos de axioma a ax.
					original = original.replace(axiomaOriginal[j], "");
					axioma = axioma.replace(axiomaActualizado[i], "");
					String result = axioma + original;
					// TODO NO SE COMO QUITAR LA ULTIMA "V"
					if (result.equals("vv")) {
						result = "";

					}
					return result;

				}
			}
		}
		return axioma;
	}

	/**
	 * Elimina axiomass de la lista de axiomas
	 * 
	 * @param array
	 * @param index
	 * @return
	 */
	public static String[] eliminarArray(String[] array, String index) {

		List<String> list = new ArrayList<>(Arrays.asList(array));
		list.remove(index);

		// Creates a new array with the same size as the list and copies the list
		// elements to it.
		array = list.toArray(new String[list.size()]);

		return array;
	}

}// FIn clase
