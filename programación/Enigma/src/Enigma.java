/**
 * @author Miguel Carmona
 * Codificación UTF8 ¡¡IMPORTANTE!!
 *
 */

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Enigma {
	
	private static boolean showlogs=false; //define si se muestran logs por consola
	private static boolean gui=true; //define si se usa interface gráfica o cli
	private static String[] datos = {
			"",//[0] mensaje
			"54",//[1] rotorA
			"73",//[2] rotorB
			"85",//[3] rotorC
			"91",//[4] rotorD
			"descifrar"};//[5] cifrar/descifrar

	public Enigma() {
	}

	public static void main(String[] args) throws Exception {
		check_logs(args);
		
		pruebas();
		
		//String frase = "Frase$ d/e prueDFGba con tíldess ü/& ok";
				String frase ="Informe 345/32 Buque de Carga en el Atlantico.";
				String fraseDescifrar = "*k_epnaltA{ha{je agr]?{a` euqu>{./+543 einkbjI";
				int rotorA=2;
				int rotorB=3;
				int rotorC=4;
				int rotorD=91;
				
				System.out.println("Frase original:\n"+frase);
				String cifrado = cifrarFrase(frase, rotorA, rotorB, rotorC, rotorD);
				System.out.println("\nFrase cifrada:\n"+cifrado+"\n");
				cifrado = descifrarFrase(frase, rotorA, rotorB, rotorC, rotorD);
				System.out.println("\nFrase descifrada:\n"+cifrado+"\n\n");
				
				System.out.println("Rotor A:\n");
				System.out.println( rotorAdescifrar( rotorAcifrar(frase, rotorA) , rotorA) );
				System.out.println("Rotor B:\n");
				System.out.println( rotorBdescifrar( rotorBcifrar(frase, rotorA) , rotorA) );
				System.out.println("Rotor C:\n");
				System.out.println( rotorCdescifrar( rotorCcifrar(frase, rotorA) , rotorA) );
				System.out.println("Rotor D:\n");
				System.out.println( rotorDdescifrar( rotorDcifrar(frase, rotorD) , rotorD) );
				System.out.println("\n");
				System.out.println(fraseDescifrar);
				System.out.println(rotorDcifrar(frase, rotorD));
				System.out.println("\n");
				System.out.println(frase);
				System.out.println(rotorDdescifrar(fraseDescifrar, rotorD));

				System.exit(0);
				
		

		//Comprobamos si hay una entrada pipe
		if( System.in.available() > 0 ) {
			String stdin="";
			Scanner s = new Scanner(System.in);
			while( s.hasNext() ) {
				stdin += s.nextLine()+"\n";
			}
			s.close();
			stdin = stdin.replaceFirst("[\n\r]+$", "");//quitamos el último salto de línea
			if( showlogs ) System.out.println( "stdin: "+stdin );
			
			if( stdin.length()>0 ) {
				//Hemos recibido el mensaje. Forzamos a descifrado (por si no se pasa más referencias)
				datos[0] = stdin;
				datos[5] = "descifrar";
			}
		}
		
		if( args.length <= 1 ) {
			gui=true;
		} else {
			gui=false;
		}
		
		check_args(args);
		
		if( gui ) {
			if( showlogs ) System.out.println("GUI mode");
			gui_principal();
		} else { //CLI
			if( showlogs ) System.out.println("CLI mode");
			if( datos[0].isEmpty() | datos[5].isEmpty() ) {
				if( showlogs ) System.out.println("No se recibió el mensaje o la opción de cifrar/descifrar");
				cli_error("Faltan parámetros obligatorios.");
			}
		}
		
		operaciones();
	}

	/*
	 * Operaciones
	 */
	private static void operaciones() {
		
		if( showlogs ) {
			for( int i=0 ; i<datos.length ; i++ )
				System.out.println("datos["+i+"]: "+datos[i]);
		}
		
		String mensaje = datos[0];
		
		//comprobamos si hay que cifrar o descifrar (valor en datos[5])
		if( datos[5].equals("cifrar") ) { //ciframos
			
			mensaje = rotorAcifrar(mensaje, Integer.parseInt(datos[1]));
			if( showlogs ) System.out.println("Salida rotor A: "+mensaje);

			mensaje = rotorBcifrar(mensaje, Integer.parseInt(datos[2]));
			if( showlogs ) System.out.println("Salida rotor B: "+mensaje);

			mensaje = rotorCcifrar(mensaje, Integer.parseInt(datos[3]));
			if( showlogs ) System.out.println("Salida rotor C: "+mensaje);
	
			mensaje = rotorDcifrar(mensaje, Integer.parseInt(datos[4]));
			if( showlogs ) System.out.println("Salida rotor D: "+mensaje);

		} else { //desciframos
			
			mensaje = rotorDdescifrar(mensaje, Integer.parseInt(datos[4]));
			if( showlogs ) System.out.println("Salida rotor D: "+mensaje);
			
			mensaje = rotorCdescifrar(mensaje, Integer.parseInt(datos[3]));
			if( showlogs ) System.out.println("Salida rotor C: "+mensaje);
			
			mensaje = rotorBdescifrar(mensaje, Integer.parseInt(datos[2]));
			if( showlogs ) System.out.println("Salida rotor B: "+mensaje);
			
			mensaje = rotorAdescifrar(mensaje, Integer.parseInt(datos[1]));
			if( showlogs ) System.out.println("Salida rotor A: "+mensaje);
		}
		
		datos[0] = mensaje;
		
		if( showlogs ) {
			for( int i=0 ; i<datos.length ; i++ )
				System.out.println("datos["+i+"]: "+datos[i]);
		}

		
		if(gui) {
			gui_principal();
			while( datos[0].isEmpty() ) {
				JOptionPane.showMessageDialog(null,
						"No se ha recibido ningún mensaje.\nInténtelo de nuevo o cancele para terminar",
						"ERROR",
						JOptionPane.ERROR_MESSAGE);
				gui_principal();
			}
			operaciones();
		} else {
			System.out.println(datos[0]);
		}
	}
	
	/*
	 * Comprueba los argumentos args
	 */
	private static void check_args(String[] args) {
		if( showlogs ) {
			for( int i=0 ; i<args.length ; i++ )
				System.out.println("args["+i+"]: "+args[i]);
		}

		for( int i=0 ; i<args.length ; i++ ) {
			switch( args[i] ) {
			case "--logs":
				break;
			case "--gui":
				gui=true;
				break;
			case "--cli":
				gui=false;
				break;
			case "--mensaje": 
				if( i+1<args.length ) {
					datos[0] = args[i+1];
					i++;
				}
				else cli_error("No se recibió el mensaje.");
				break;
			case "--cifrar":
				datos[5] = "cifrar";
				break;
			case "--descifrar":
				datos[5] = "descifrar";
				break;
			case "--rotor1":
				if( i+1<args.length ) {
					if(!isInt( datos[1] = args[i+1] )) cli_error("El valor de --rotor1 no es válido");
					if( Integer.parseInt(datos[1]) < 0 | Integer.parseInt(datos[1]) > 99 )
						cli_error("El valor de --rotor1 no es válido");
					i++;
				}
				else cli_error("No se recibió el valor de rotor1.");
				break;
			case "--rotor2":
				if( i+1<args.length ) {
					if(!isInt( datos[2] = args[i+1] )) cli_error("El valor de --rotor2 no es válido");
					if( Integer.parseInt(datos[2]) < 0 | Integer.parseInt(datos[2]) > 99 )
						cli_error("El valor de --rotor2 no es válido");
					i++;
				}
				else cli_error("No se recibió el valor de rotor2.");
				break;
			case "--rotor3":
				if( i+1<args.length ) {
					if(!isInt( datos[3] = args[i+1] )) cli_error("El valor de --rotor1 no es válido");
					if( Integer.parseInt(datos[3]) < 0 | Integer.parseInt(datos[3]) > 99 )
						cli_error("El valor de --rotor3 no es válido");
					i++;
				}
				else cli_error("No se recibió el valor de rotor3.");
				break;
			case "--rotor4":
				if( i+1<args.length ) {
					if(!isInt( datos[4] = args[i+1] )) cli_error("El valor de --rotor4 no es válido");
					if( Integer.parseInt(datos[4]) < 0 | Integer.parseInt(datos[4]) > 99 )
						cli_error("El valor de --rotor4 no es válido");
					i++;
				}
				else cli_error("No se recibió el valor de rotor4.");
				break;
			default:
				if( showlogs ) System.out.println("Parámetro "+args[i]+" no válido. Ignorado.");
			}
		}
	}

	private static void cli_error(String mensaje) {
		System.out.println("\nERROR:\n"+mensaje+"\n");
		System.out.println("Cifrador/descifrador de mensajes para máquina enigma\n"
				+ "Ejecútelo sin parámetros (o con --gui) para usar el modo gráfico\n\n"
				+ "Parámetros obligatorios:\n"
				+ "  --mensaje \"Mensaje a cifrar/descifrar\"\n"
				+ "    También puede pasar el mensaje por entrada estándar (UNIX pipe por ejemplo).\n\n"
				+ "Parámetros opcionales:\n"
				+ "  --cifrar ó --descifrar para la acción (descifrar por defecto)\n"
				+ "  --logs (para mostrar los logs)\n"
				+ "  --gui (fuerza el modo gráfico)\n"
				+ "  --cli (fuerza el modo consola)\n"
				+ "  Posiciones del rotor (XX es número de 0 a 99)\n"
				+ "    --rotor1 XX (por defecto 54)\n"
				+ "    --rotor2 XX (por defecto 73)\n"
				+ "    --rotor3 XX (por defecto 85)\n"
				+ "    --rotor4 XX (por defecto 91)\n"
				+ "");
		System.exit(0);
	}

	/*
	 * Genera el menú gráfico principal
	 */
	private static void gui_principal() {
		
		String backgroundcolor="#ababab";
		UIManager UI=new UIManager();
		UI.put("OptionPane.background",Color.decode(backgroundcolor));
		UI.put("Panel.background",Color.decode(backgroundcolor));
		 
		JLabel label = new JLabel("Introduzca el mensaje:");
		
		JTextArea msgBox = new JTextArea(datos[0]);
		msgBox.setColumns(40);
		msgBox.setRows(8);
		msgBox.setLineWrap(true);
		msgBox.setWrapStyleWord(true);
		msgBox.setSize(msgBox.getPreferredSize().width, msgBox.getPreferredSize().height);
		msgBox.setBackground( Color.decode("#ffffff") );
		msgBox.setBorder(BorderFactory.createEmptyBorder(5,5,5,5)); //padding interno
		//msgBox.setBorder(BorderFactory.createLineBorder(Color.decode("#333333")));
		
		JRadioButton radio1=new JRadioButton("Cifrar");
		radio1.setActionCommand("cifrar");
		radio1.setBackground( Color.decode(backgroundcolor) );
		if(datos[5].equals("cifrar")) radio1.setSelected(true);
		
		JRadioButton radio2=new JRadioButton("Descifrar");
		radio2.setActionCommand("descifrar");
		radio2.setBackground( Color.decode(backgroundcolor) );
		if(datos[5].equals("descifrar")) radio2.setSelected(true);
		
		ButtonGroup radioGroup = new ButtonGroup();
		radioGroup.add(radio1);
		radioGroup.add(radio2);
		
		JPanel myPanel = new JPanel();
		myPanel.setPreferredSize(new Dimension(460,220));
		
		myPanel.add(label);
		myPanel.add(Box.createVerticalStrut(30));
		myPanel.add(msgBox);

		myPanel.add(Box.createVerticalStrut(140));
		myPanel.add(radio1);
		myPanel.add(radio2);
		
		ImageIcon icon = new ImageIcon("");
		int result = JOptionPane.showConfirmDialog(null, myPanel, 
				"Máquina ENIGMA", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, icon);

		if (result == JOptionPane.OK_OPTION) {
			datos[0] = msgBox.getText();
			datos[5] = radioGroup.getSelection().getActionCommand();
		} else {
			System.exit(0);
		}
		
/*		if( showlogs ) {
			for( int i=0 ; i<datos.length ; i++ )
				System.out.println("datos["+i+"]: "+datos[i]);
		}
*/

	}

	/*
	 * Comprobaciones de cada rotor por separado
	 */
	private static void pruebas() {
		System.out.println("\nPRUEBAS\n");
		
		String mensajeNormal = "Informe 345/32 Buque de Carga en el Atlantico.";
		mensajeNormal="Mamón";
		System.out.println("Mensaje inicial:"+mensajeNormal);

		String mensajeCifrado = rotorAcifrar(mensajeNormal, 54);
		System.out.println( "A:" + mensajeCifrado );
		System.out.println( "A:" + rotorAdescifrar(mensajeCifrado, 54) );
		
		mensajeCifrado = rotorBcifrar(mensajeNormal, 73);
		System.out.println( "B:" + mensajeCifrado );
		System.out.println( "B:" + rotorBdescifrar(mensajeCifrado, 73) );
		
		mensajeCifrado = rotorCcifrar(mensajeNormal, 85);
		System.out.println( "C:" + mensajeCifrado );
		System.out.println( "C:" + rotorCdescifrar(mensajeCifrado, 85) );
		
		mensajeCifrado = rotorDcifrar(mensajeNormal, 91);
		System.out.println( "D:" + mensajeCifrado );
		System.out.println( "D:" + rotorDdescifrar(mensajeCifrado, 91) );
		
		System.out.println("\nFIN PRUEBAS\n");
	}
	
	/**
	 * Ciframos una frase pasándola por los 4 rotores.
	 * Recibe:
	 * 	frase a cifrar
	 * 	valorA posición del rotor A
	 * 	valorB posición del rotor B
	 * 	valorC posición del rotor C
	 * 	valorD posición del rotor D
	 * Devuelve la frase cifrada.
	 */
	public static String cifrarFrase(String frase, int valorA, int valorB, int valorC, int valorD) {
		
		frase = rotorAcifrar(frase, valorA);
		frase = rotorBcifrar(frase, valorB);
		frase = rotorCcifrar(frase, valorC);
		frase = rotorDcifrar(frase, valorD);
		
		return frase;
	}
	
	/**
	 * Desciframos una frase pasándola por los 4 rotores.
	 * Recibe:
	 * 	frase a descifrar
	 * 	valorA posición del rotor A
	 * 	valorB posición del rotor B
	 * 	valorC posición del rotor C
	 * 	valorD posición del rotor D
	 * Devuelve la frase descifrada.
	 */
	public static String descifrarFrase(String frase, int valorA, int valorB, int valorC, int valorD) {
		
		frase = rotorDdescifrar(frase, valorD);
		frase = rotorCdescifrar(frase, valorC);
		frase = rotorBdescifrar(frase, valorB);
		frase = rotorAdescifrar(frase, valorA);
		
		return frase;
	}


	/**
	 * Cifrado para el rotor A
	 * Codificaremos cada 3 letras, dejando las otras 3 sin tocar.
	 * 	Ej:
	 *  ”Informe 345/32 Buque de Carga en el Atlantico.”
     *   Valor Rotor A: 54
     *   “ E=orm<Vi45/ihVBuqL<Vde y8Iga <EVel wKCant@:F.”
	 */
	private static String rotorAcifrar( String cadena, int valor) {
		
		int contador=1;
		String salida="";

		for( int i=0 ; i<=cadena.length()-1 ; i++ ) {
			
			int codigo=(int)cadena.charAt(i);
			
			if( contador <= 3 && codigo >= 32 && codigo <= 136 ) { //primer grupo de 3, ciframos si está en el rango
				salida += Cifrar(cadena.charAt(i), valor);
			
			} else { //segundo grupo de 3, no ciframos o está fuera del rango
				salida += cadena.charAt(i);
			}

			if( contador >= 6 ) {
				contador=0;
			}

			contador++;
		}

		return salida;
	}

	/**
	 * Descifrado para el rotor A
	 */
	private static String rotorAdescifrar( String cadena, int valor) {

		int contador=1;
		String salida="";

		for( int i=0 ; i<=cadena.length()-1 ; i++ ) {
			
			int codigo=(int)cadena.charAt(i);
			
			if( contador <= 3 && codigo >= 32 && codigo <= 136 ) { //primer grupo de 3, ciframos si está en el rango
				salida += Descifrar(cadena.charAt(i), valor);
			
			} else { //segundo grupo de 3, no ciframos
				salida += cadena.charAt(i);
			}

			if( contador >= 6 ) {
				contador=0;
			}

			contador++;
		}

		return salida;
	}

	/**
	 * Cifrado para el rotor B
	 * aplicaremos la función Cifrar donde el valor inicial de Rotor B se aplicará
	 * progresivamente, es decir, cada vez que llamemos a la función Cifrar,
	 * añadiremos 11 al valor del Rotor y sólo si el índice es par.
	 * Ej:
	 *  Texto: "Informe 345/32 Buque de Carga en el Atlantico."
	 *  Valor Rotor B: 73
	 *  Texto Cifrado:" 3n[ormp I4V/_2WBXqcexdi Ra-g' 6n[eS 3tiavt|c.."
	 */
	private static String rotorBcifrar( String cadena, int valor ) {
		
		String salida="";
		
		for( int i=0 ; i<cadena.length() ; i++ ) {
			
			int codigo=(int)cadena.charAt(i);
			
			if( i%2 == 0 && codigo >= 32 && codigo <= 136 ) { //es par
				salida += Cifrar(cadena.charAt(i), valor);
				valor = valor + 11;
			
			} else {
				salida += cadena.charAt(i);
			}
		}

		return salida;
	}

	/**
	 * Descifrado para el rotor B
	 */
	private static String rotorBdescifrar( String cadena, int valor ) {

		String salida="";
		for( int i=0 ; i<cadena.length() ; i++ ) {
			
			int codigo=(int)cadena.charAt(i);
			
			if( i%2 == 0 && codigo >= 32 && codigo <= 136 ) { //es par
				salida += Descifrar(cadena.charAt(i), valor);
				valor = valor + 11;
			
			} else {
				salida += cadena.charAt(i);
			}
		}

		return salida;
	}

	/**
	 * Cifrado para el rotor C
	 * Aplicaremos la función Cifrar aplicando el valor de Rotor C pero
	 * comenzando por el final y sólo si el índice (al revés) es impar.
	 * Cada vez que apliquemos la función sumaremos 23 al valor del rotor.
	 * Ej:
	 *  Texto: “Informe 345/32 Buque de Carga en el Atlantico.”
	 *  Valor Rotor C: 85
	 *  Texto Cifrado: “$opi9n=lgA*l' Geoanr CUeQ iu-ut {305L3OeTrmf$I”
	 */
	private static String rotorCcifrar( String cadena, int valor ) {

		String salida="";
		
		for( int i=cadena.length()-1 ; i>=0 ; i-- ) {
			
			int codigo=(int)cadena.charAt(i);
			
			if( i%2 != 0 && codigo >= 32 && codigo <= 136 ) { //es impar
				salida += Cifrar(cadena.charAt(i), valor);
				valor = valor + 23;
			
			} else {
				salida += cadena.charAt(i);
			}
		}

		return salida;
	}

	/**
	 * Descifrado para el rotor C
	 */
	private static String rotorCdescifrar( String cadena, int valor ) {

		String salida="";

		/* Comprobamos si el número de caracteres de la cadena es par o impar
		   Si es par, debemos variar una posición para el descifrado correcto*/ 
		int par=0;
		if( cadena.length()%2 == 0 )
			par=1;

		for( int i=0 ; i<cadena.length() ; i++ ) {

			int codigo=(int)cadena.charAt(i);

			if( (i+par)%2 != 0 && codigo >= 32 && codigo <= 136 ) { //es impar
				salida += Descifrar(cadena.charAt(i), valor);
				valor = valor + 23;

			} else {
				salida += cadena.charAt(i);
			}
		}

		return invertir(salida);
	}
	
	/**
	 * Cifrado para el rotor D
	 * Recibe un código, lo invierte y lo codifica cada 5 letras
	 * Ej:
	 *  "Informe 345/32 Buque de Carga en el Atlantico."
	 *  "*k_epnaltA{ha{je agr]?{a` euqu>{./+543 einkbjI"
	 *  Valor Rotor D: 91
	 */
	private static String rotorDcifrar( String cadena, int valor ) {
		
		String salida="";
		cadena = invertir(cadena);
		
		for(int i=0 ; i < cadena.length() ; i++ ) {
			
			int codigo=(int)cadena.charAt(i);
			
			if( (i/5)%2 == 0 && codigo >= 32 && codigo <= 126 )
				salida += Cifrar(cadena.charAt(i), valor);
			else
				salida += cadena.charAt(i);
			
		}
		
		return salida;
	}
	
	/**
	 * Descifrado para el rotor D
	 */
	private static String rotorDdescifrar( String cadena, int valor ) {
		
		String salida="";

		for(int i=0 ; i < cadena.length() ; i++ ) {
			
			int codigo=(int)cadena.charAt(i);
			if( codigo >= 32 && codigo <= 126 ) {

				if( (i/5)%2 == 0 ) salida += Descifrar(cadena.charAt(i), valor);
				else salida += cadena.charAt(i);
				
			} else
				salida += cadena.charAt(i);
		}
		
		//invertimos la cadena
		return invertir(salida);
	}

	
	/**
	 * Comprueba si se pasa el parámetro --logs como argumento y los activa
	 */
	private static void check_logs(String[] args) {
		if(args.length > 0) {
			for( int i=0 ; i<args.length ; i++ ) {
				if( args[i].equals("--logs") ) {
					showlogs=true;
					System.out.println("Logs activos.");
					break;
				}
			}
		}
	}

	/**
	 * Devuelve true si es int
	 * @param String s
	 * @return boolean
	 */
	public static boolean isInt(String s)
	{
	    try {
	        Integer.parseInt(s);
	        return true;
	    }
	    catch (NumberFormatException ex) {
	        return false;
	    }
	}

	/**
	 * Invierte una cadena dada
	 */
	private static String invertir(String cadena) {
		String salida="";
		for(int i=cadena.length()-1 ; i >= 0 ; i-- ) {
			salida += cadena.charAt(i);
		}
		return salida;
	}

	private static char Cifrar (char letra, int valor) {
		
		int codigo=(int)letra;
		int desplazamiento = codigo+valor%95;
		int resultado;
		
		if (desplazamiento>126) {
			resultado=desplazamiento-95;
		} else {
			resultado=desplazamiento;
		}
		
		return (char)resultado;
	}
	
	private static char Descifrar (char letra, int valor) {
		
		int codigo=(int)letra;
		int desplazamiento = codigo-valor%95;
		int resultado;
		
		if (desplazamiento<32) {
			resultado=desplazamiento+95;
		} else {
			resultado=desplazamiento;
		}
		
		return (char)resultado;
	}

	
	
	
	
	
}
