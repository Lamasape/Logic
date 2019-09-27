package Logica;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class LeerFichero {

	public static void main(String[] args) throws IOException {
		leerArchivo();
	}

	/**
	 * Metodo que lee los axiomas de un txt y los pasa un arrayList
	 * @return
	 * @throws IOException
	 */
	public static ArrayList<String[]> leerArchivo() throws IOException {
		// Fichero del que queremos leer
		File fichero = new File("axiomas.txt");
		Scanner s = null;
		ArrayList<String[]> axioma = new ArrayList<String[]>();

		try {
			// Leemos el contenido del fichero
			System.out.println("... Leemos el contenido del fichero ...");
			s = new Scanner(fichero);

			// Leemos linea a linea el fichero
			while (s.hasNextLine()) {
				String linea = s.nextLine(); // Guardamos la linea en un String
				axioma.add(linea.split("/n"));
				System.out.println(linea); // Imprimimos la linea
			}

		} catch (Exception ex) {
			System.out.println("Mensaje: " + ex.getMessage());
		} finally {
			// Cerramos el fichero tanto si la lectura ha sido correcta o no
			try {
				if (s != null)
					s.close();
			} catch (Exception ex2) {
				System.out.println("Mensaje 2: " + ex2.getMessage());
			}
		}
		return axioma;
	}

}
