/**
 * Práctica Individual 2. Programación Básica en JAva
 * 
 * Programa con menú para varios sub-programas internos.
 *  
 * @author Miguel Carmona
 * 2021 1ºDAW
 */

import java.util.Scanner;

public class Practica2 {

	//Array con las vocales (minúsculas) agrupadas según si están
	//normales, acentuadas o con diéresis
	static char vocales[][] = {
			{'a','e','i','o','u'},
			{'á','é','í','ó','ú'},
			{'ä','ë','ï','ö','ü'} };

	//código de las vocales (minúsculas) normales, acentuadas y con diéresis
	static boolean funciona=false;
	
	/**
	 * constructor
	 */
	public Practica2() {
		
	}

	public static void main(String[] args) {
		menu();
	}

	/**
	 * Show menu and wait for a valid option
	 *  Get: Array whit elements in format {
	 *   {"option1", "title", "function to execute"},
	 *   {"option2", "title", "function to execute"} }
	 *  Return: valid option of array parsed
	 */
	private static void menu() {

		String[][] elements = {
				{"1","1  - Operaciones matemáticas"},
				{"2","2  - Serie de Fibonacci"},
				{"3","3  - Número primo"},
				{"4","4  - Cambio de monedas"},
				{"5","5  - Ecuación de segundo grado"},
				{"6","6  - Quitar espacios"},
				{"7","7  - Invertir cadena"},
				{"8","8  - Contar vocales y consonantes"},
				{"9","9  - Pasar vocales a mayúsculas\n"
				+"       y consonantes a minúsculas"},
				{"11","11 - Extractor de vocales"},
				{"12","12 - Factorial"},
				{"13","13 - Palíndromo"},
				{"14","14 - Encriptador"},
				{"15","15 - Diamante de asteriscos"},
				{"",""},
				{"","Cambiar velocidad de animaciones:"},
				{"N","    N - Normal"},
				{"S","    S - Lento (Slow)"},
				{"F","    F - Rápido (Fast)"},
				{"D","    D - Desactivado (Disabled)"},
				{"",""},
				{"0","0 - Salir"},
				{"",""}
		};

		MyUtils.cleanConsole();
		printMenu(elements);

		//Save valid options for check later
		String[] validOptions = new String[0];
		for( String[] element:elements ) {
			if( element[0]!="" ) { //Exclude elements without option (information only) 
				String elementTmp[] = new String[validOptions.length+1];
				System.arraycopy(validOptions, 0, elementTmp, 0, validOptions.length);
				elementTmp[elementTmp.length-1] = element[0];
				validOptions = elementTmp;
			}
		}

		MyUtils.retroPrint("Introduzca una opción: ");
		String option;
		int forClean=0;
		while( ( option = MyUtils.readWhitOptions(validOptions) ).isEmpty() ) {
			System.out.print("Opción incorrecta. Introduzca una opción: ");	
			forClean++;
			if( forClean%10 == 0) { //Clean console and show menu again after 10 errors
				MyUtils.cleanConsole();
				printMenu(elements);
				MyUtils.retroPrint("Introduzca una opción: ");
			}
		}

		switch( option.toUpperCase() ) {
		case "1":
			printMenuCabecera("Operaciones matemáticas");
			operacionesMatematicas();
			break;
		case "2":
			printMenuCabecera("Serie de Fibonacci");
			serieFibonacci();
			break;
		case "3":
			printMenuCabecera("Comprobar número primo");
			numeroPrimo();
			break;
		case "4":
			printMenuCabecera("Cambio de monedas");
			cambioMonedas();
			break;
		case "5":
			printMenuCabecera("Ecuación de segundo grado");
			equacionSegundoGrado();
			break;
		case "6":
			printMenuCabecera("Quitar espacios");
			quitarEspacios();
			break;
		case "7":
			printMenuCabecera("Invertir cadena");
			invertirCadena();
			break;
		case "8":
			printMenuCabecera("Invertir cadena");
			contarVocalesYConsonantes();
			break;
		case "9":
			printMenuCabecera("Invertir cadena");
			vocalesAMayusculasYConsonantesAMinusculas();
			break;
		case "11":
			printMenuCabecera("Extractor de vocales");
			extractorDeVocales();
			break;
		case "12":
			printMenuCabecera("Factorial");
			factorial();
			break;
		case "13":
			printMenuCabecera("Palíndromo");
			palindromo();
			break;
		case "14":
			printMenuCabecera("Cifrador");
			cifrador();
			break;
		case "15":
			printMenuCabecera("Diamante de asteriscos");
			diamanteDeAsteriscos();
			break;
		case "N":
		case "S":
		case "F":
		case "D":
			MyUtils.changeRetroVelocity(option.toUpperCase());
			break;
		case "0":
			MyUtils.retroPrint("\nSaliendo... ¡Hasta otra amigo!\n\n");
			System.exit(0);
		}

		//System.out.println("despues de switch");
		//MyUtils.readKeyboard();

		menu();

	}
	/**
	 * Imprime por salida estándar el menú principal
	 */
	private static void printMenu(String[][] elements) {
		printMenuCabecera("MI PROGRAMA RETRO");
		MyUtils.retroPrint("          SELECCIONE UNA OPCION\n");
		MyUtils.retroPrint("\n",0);

		//Print elements
		for( String[] element:elements ) {
			MyUtils.retroPrint("  "+element[1]+"\n",2);
		}
	}
	/**
	 * Imprime la cabecera para cada sección
	 */
	private static void printMenuCabecera(String texto) {
		int longitudLinea=50;
		String linea="\n";
		String out = "";
		
		//out += "*****************************************\n";
		int asteriscos=( longitudLinea-texto.length() )/2;
		for( int i=1 ; i<=asteriscos-1 ; i++) {
			out+="*";
		}
		out+=" "+texto+" ";
		for( int i=1 ; i<=asteriscos-1 ; i++) {
			out+="*";
		}
		
		for( int i=1 ; i<=out.length() ; i++) {
			linea+="*";
		}
		linea+="\n";
		
		out = "\n"+linea+out+linea+"\n";
		MyUtils.retroPrint(out,2);
		
	}



	/**
	 * 1) Operaciones Matemáticas:
	 * Recibe por teclado dos números e imprime por pantalla la suma,
	 * la resta, la multiplicación, el cociente y el resto de ambos.
	 */
	private static void operacionesMatematicas() {
		float numero1= MyUtils.readKeyboardFloat("Introduzca el primer número: ");
		float numero2= MyUtils.readKeyboardFloat("Introduzca el segundo número: ");
		MyUtils.retroPrint("\nLos números introducidos son:\n  Número 1: "+numero1+
				"\n  Número 2: "+numero2);
		MyUtils.retroPrint("\n\nLa suma de ambos números es: "+(numero1+numero2));
		MyUtils.retroPrint("\nLa resta de ambos números es: "+(numero1-numero2));
		MyUtils.retroPrint("\nLa multiplicación de ambos números es: "+(numero1*numero2));
		MyUtils.retroPrint("\nLa división entre ambos números es: "+(numero1/numero2));
		MyUtils.retroPrint("\nEl cociente de la división entre ambos números es: "
				+(int)(numero1/numero2));
		MyUtils.retroPrint("\nEl resto de la división entre ambos números es: "
				+(numero1%numero2));

		MyUtils.retroPrint("\n\nPulse enter para para volver a menú principal.\n");
		MyUtils.readKeyboard();
	}

	/**
	 * 2) Serie de Fibonacci:
	 * Recibe un numero por teclado e imprime por pantalla ese numero de números de Fibonacci.
	 * Para conseguir el siguiente numero de Fibonacci tienes que sumar el número actual con
	 * el anterior. Los dos primeros números valen 1 y 1, el tercero vale 1+1=2,
	 * el cuarto 1+2=3, el quinto 2+3=5, etc.
	 */
	private static boolean serieFibonacci() {
		int elements=MyUtils.readKeyboardInt("Introduzca la cantidad que desee generar: ");
		//elements++;
		long outlong[] = {1,1,2};
		if( elements < 0 ) {
			MyUtils.retroPrint("Debe introducir un valor entero positivo.\n");
			return serieFibonacci();
		} else if( elements <= 3 ) {
			MyUtils.retroPrint("Para una cantidad de 3 o menor,"
					+ " cito los 3 primeros resultados.\n");
		} else {

			if( elements > 92 ) {
				elements=92;
				MyUtils.retroPrint("Lo siento pero el límite que puedo calcular son 92.\n");
			}

			outlong = new long[(elements)];
			outlong[0] = 1; outlong[1] = 1; outlong[2] = 2;

			//insert values
			for( int i=3 ; i<outlong.length ; i++ ) {
				outlong[i] = outlong[(i-2)] + outlong[(i-1)];
			}
		}

		String serie = "";
		for( int i=0 ; i<outlong.length ; i++ ) {
			if( i+1 < 10 )
				serie+="[posición 00"+(i+1)+"]: ";
			else
				serie+="[posición 0"+(i+1)+"]: ";

			serie+=+outlong[i]+"\n";
		}
		MyUtils.retroPrint("El número fibonacci para la última posición ("+outlong.length+
				") de\nla serie es: "+ outlong[(outlong.length-1)] +"\n");
		MyUtils.retroPrint("La serie fibonacci completa es:\n"+serie);

		MyUtils.retroPrint("\n\nPulse enter para volver a menú principal.\n");
		MyUtils.readKeyboard();
		return true;
	}

	/**
	 * 3) Numero Primo:
	 * Escribe un programa que reciba un numero por pantalla y nos diga si es primo o no.
	 */
	private static boolean numeroPrimo() {
		MyUtils.retroPrint("Introduzca el número: ");
		int number= MyUtils.readKeyboardInt("Introduzca el número: ");

		if( number < 2 ) {
			MyUtils.retroPrint("Debe introducir un número positivo mayor o igual a 2\n"
					+ "ya que es el primer número primo.\n");
			return numeroPrimo();
		} else {
			boolean prime=true;
			for( int i=2 ; i<=(number/2) ; i++ ) {
				if( number%i == 0 ) {
					prime = false;
					break;
				}
			}
			if( prime )
				MyUtils.retroPrint("El número "+number+" ES primo.");
			else
				MyUtils.retroPrint("El número "+number+" NO ES primo.");
		}

		MyUtils.retroPrint("\n\nPulse enter para para volver a menú principal.\n");
		MyUtils.readKeyboard();
		return true;
	}


	/**
	 * 4) Cambio de monedas:
	 * Escribe un programa que nos pida un precio, una cantidad de dinero tal que
	 * cantidad>=precio y nos diga la vuelta y las monedas que nos tiene que devolver.
	 */
	private static int cambioMonedas_billetes_org[][] = { //valor en céntimos
			{50000,20000,10000,5000,2000,1000,500}, {-1,-1,-1,-1,-1,-1,-1}, {0,0,0,0,0,0,0} };
	/* array[0] => valor del billete/moneda en céntimos 
	 * array[1] => cantidad que se tiene en dinero (si es negativo, no hay límite)
	 * array[2] => cantidad a devolver de cada */ 
	private static int cambioMonedas_monedas_org[][] = { //valor en céntimos
			{200,100,50,20,10,5,2,1}, {-1,-1,-1,-1,-1,-1,-1,-1}, {0,0,0,0,0,0,0,0} };
	private static int cambioMonedas_billetes[][];// = cambioMonedas_billetes_org;
	private static int cambioMonedas_monedas[][];// = cambioMonedas_monedas_org;

	private static boolean cambioMonedas() {

		//trabajamos todos los valores céntimos enteros para evitar redondeos
		int importe = (int)Math.round(MyUtils.readKeyboardFloat("Introduzca el importe "
				+ "de la compra en €: ")*100 );
		int dinero = (int)Math.round( MyUtils.readKeyboardFloat("Introduzca el dinero "
				+ "disponible en €: ")*100 );

		MyUtils.retroPrint("\nHa introducido "+( (float)importe )/100+" € de importe de la compra.");
		MyUtils.retroPrint("\nHa introducido "+( (float)dinero )/100+" € de dinero disponible.\n\n");

		//reseteamos los datos anteriores
		cambioMonedas_billetes = cambioMonedas_billetes_org;
		cambioMonedas_monedas = cambioMonedas_monedas_org;

		if( dinero < importe ) {
			MyUtils.retroPrint("El importe de la compra debe ser menos del dinero que tiene.\n\n");
			cambioMonedas();
			return true;
		}

		int tecla=3;
		while( tecla >= 1 && tecla <=3 ) {

			if( tecla == 1 ) {
				cambioMonedas_tabla(1);

				String validOptions[] = {"a","b","c","d","e","f","g","1","2","3","4","5","6","7","8","0"};
				String option;
				MyUtils.retroPrint("Indique de cuál no dispone suficiente: ");
				while( ( option=MyUtils.readWhitOptions( validOptions ) ).equals("") ) {
					MyUtils.retroPrint("Opción no válida.\nIndique de cuál no dispone suficiente: ");
				}
				if( option.equals("0") ) return true; //salimos

				MyUtils.retroPrint("¿De cuánto dispone?: ");
				int disponible;
				while( (disponible=MyUtils.readKeyboardInt()) < 0 ) {
					MyUtils.retroPrint("Opción no válida.\nIndique 0 si no tiene ninguno:  ");
				}

				if( MyUtils.isInt(option) ) { //Son monedas
					int key = MyUtils.returnIsInt(option)-1;
					cambioMonedas_monedas[1][key] = disponible;
				} else { //Son billetes
					int key = (int)(option.toLowerCase().charAt(0))-97;
					cambioMonedas_billetes[1][key] = disponible;
				}

			} else if( tecla == 2 ) {
				cambioMonedas_tabla(tecla);
			}
			
			String cambio[] = cambioMonedas(importe, dinero);
			if( cambio[2].equals("sin cambio") ) {
				MyUtils.retroPrint("\nNo hay suficientes combinaciones como para devolver todo el cambio.\n"
						+ "Actualice su cambio o finalice el programa.\n");
			} else {
				if( cambio[0].equals("") && cambio[1].equals("") ) {
					MyUtils.retroPrint("No hay cambio");
				} else {
					MyUtils.retroPrint("\nEl total a devolver es de "+ MyUtils.decimalFormat((dinero-importe)+"")
					+"€ que se reparte en:\n"+cambio[0]+cambio[1]);
				}
			}


			MyUtils.retroPrint("\n\n"
					+ "Pulse 1 (y enter) si no dispone de algo de cambio.\n"
					+ "Pulse 2 (y enter) si quiere ver un resumen del cambio.\n"
					+ "\nPulse enter para volver a menú principal. ");
			
			tecla = MyUtils.returnIsInt( MyUtils.readKeyboard() );

		};

		return true;
	}
	/**
	 * Actualiza la tabla de monedas
	 * Retorna un string con la devolución [0]y[1] y una tercera salida de error [2]
	 */
	private static String[] cambioMonedas(int importe, int dinero) {

		int cambio = dinero - importe;

		String salidaBilletes = "";
		for( int i=0 ; i<cambioMonedas_billetes[0].length ; i++ ) {

			int cociente = cambio/cambioMonedas_billetes[0][i];
			if( cambioMonedas_billetes[1][i] >= 0 && cociente > cambioMonedas_billetes[1][i] ) {
				//es positivo (hemos establecido cantidad) y tenemos menos de lo que se necesita
				cociente = cambioMonedas_billetes[1][i];
			}

			if( cociente > 1 ) {
				salidaBilletes += "  "+cociente+" billetes de "+ (cambioMonedas_billetes[0][i])/100 +"€\n";
			} else if( cociente == 1 ) {
				salidaBilletes += "  1 billete de "+ (cambioMonedas_billetes[0][i])/100 +"€\n";
			}

			//guardamos la cantidad usada de este billete
			cambioMonedas_billetes[2][i]=cociente;

			cambio = cambio - (cambioMonedas_billetes[0][i]*cociente);
		}

		String salidaMonedas = "";
		for( int i=0 ; i<cambioMonedas_monedas[0].length ; i++ ) {
			int cociente = cambio/cambioMonedas_monedas[0][i];

			if( cambioMonedas_monedas[1][i] >= 0 && cociente > cambioMonedas_monedas[1][i] ) {
				//es positivo (hemos establecido cantidad) y tenemos menos de lo que se necesita
				cociente = cambioMonedas_monedas[1][i];
			}

			if( cociente >= 2 ) {
				salidaMonedas += "  "+cociente+" monedas de ";
			} else if( cociente == 1 ) {
				salidaMonedas += "  1 moneda de ";
			}

			if( cociente >= 1 ) {
				if( cambioMonedas_monedas[0][i] >= 100 )
					salidaMonedas += cambioMonedas_monedas[0][i]/100+"€\n";
				else {
					if( cambioMonedas_monedas[0][i] == 1 )
						salidaMonedas += cambioMonedas_monedas[0][i]+" céntimo de €\n";
					else
						salidaMonedas += cambioMonedas_monedas[0][i]+" céntimos de €\n";
				}
			}

			//guardamos la cantidad usada de esta moneda
			cambioMonedas_monedas[2][i]=cociente;

			cambio = cambio - (cambioMonedas_monedas[0][i]*cociente);
		}

		String error="";
		if( cambio > 0 )
			error="sin cambio"; 

		String retorno[] = {salidaBilletes,salidaMonedas,error};
		return retorno;

	}
	/**
	 * Muestra la tabla para los datos de cambioMonedas
	 */
	private static void cambioMonedas_tabla( int type ) {
		String out;
		if( type == 1 ) {
			out = 	    " +-----------------------------------------------------------------------------+\n"
					+ 	" |                  Para editar, elija una tecla y pulse enter                 |\n"
					+ 	" +-----------------------------------------------------------------------------+\n"
					+	" |   Cantidad   |   Cantidad   |                                |  Elija una   |\n"
					+	" |  disponible  |   devolver   |                                |    tecla     |\n"
					+	" +-----------------------------------------------------------------------------+\n";
		} else {
			out = 	    " +-----------------------------------------------------------------------------+\n"
					+ 	" |               Resumen de dinero disponible y cálculo de cambio              |\n"
					+ 	" +-----------------------------------------------------------------------------+\n"
					+	" |   Cantidad   |   Cantidad   |                                |    Cálculo   |\n"
					+	" |  disponible  |   devolver   |                                |     total    |\n"
					+	" +-----------------------------------------------------------------------------+\n";
		}


		for( int i=0 ; i< cambioMonedas_billetes[0].length ; i++ ) {

			if( cambioMonedas_billetes[1][i] >= 0 ) //si no es negativo
				out += " |  "+ String.format("%10d", cambioMonedas_billetes[1][i])+"  |";
			else
				out += " |  sin límite  |";

			out += "  "+ String.format("%10d", cambioMonedas_billetes[2][i]) +"  |";
			out += "  Billetes de " + String.format("%3d", cambioMonedas_billetes[0][i]/100 );
			out += " €             |";
			if( type == 1 ) {
				out += "       "+ Character.toUpperCase( (char)(97+i) ) +"      |";
			} else {
				out += "  "+ String.format("%10d", cambioMonedas_billetes[2][i]*cambioMonedas_billetes[0][i]/100 ) +"  |";
			}
			out += "\n";
		}
		for( int i=0 ; i< cambioMonedas_monedas[0].length ; i++ ) {

			if( cambioMonedas_monedas[1][i] >= 0 ) //si no es negativo
				out += " |  "+ String.format("%10d", cambioMonedas_monedas[1][i])+"  |";
			else
				out += " |  sin límite  |";

			out += "  "+String.format("%10d", cambioMonedas_monedas[2][i])+"  |";

			if( cambioMonedas_monedas[0][i] >= 100 ) {
				out += "  Monedas  de  " + String.format("%2d", cambioMonedas_monedas[0][i]/100 )
				+ " €             |";
			} else {
				if( cambioMonedas_monedas[0][i] == 1 )
					out += "  Monedas  de  " + String.format("%2d", cambioMonedas_monedas[0][i] )
					+ " céntimos de € |";
				else
					out += "  Monedas  de  " + String.format("%2d", cambioMonedas_monedas[0][i] )
				+ " céntimos de € |";
				
			}

			if( type == 1 ) {
				out += "       "+ (i+1) +"      |";
			} else {
				out += "  "+String.format("%10.2f", ( (double)(cambioMonedas_monedas[2][i]*cambioMonedas_monedas[0][i]) )/100 ) +"  |";
			}
			out += "\n";
		}

		out += 	" +-----------------------------------------------------------------------------+\n";
		MyUtils.retroPrint(out);
	}


	/**
	 * 5) Ecuación de segundo grado:
	 * Escribir un programa que dada una ecuación de segundo grado de la forma
	 * a*x^2+b*x+c=0 nos diga si tiene cero, una o dos soluciones y en los dos
	 * últimos casos nos las muestre por pantalla.
	 */
	private static void equacionSegundoGrado() {
		MyUtils.retroPrint("Dada la ecuación a·x² + b·x + c = 0\n");
		double a = MyUtils.readKeyboardDouble(" Introduzca el valor de a :",10);
		double b = MyUtils.readKeyboardDouble(" Introduzca el valor de b :",10);
		double c = MyUtils.readKeyboardDouble(" Introduzca el valor de c :",10);

		double insideRoot = b*b-4*a*c;

		if( insideRoot < 0 ) {
			MyUtils.retroPrint("La ecuación "+a+"·X² + "+b+"·X + "+c+" no tiene solución real");
		} else if( insideRoot == 0 ) {
			MyUtils.retroPrint("La ecuación "+a+"·x² + "+b+"·x + "+c+" tiene sólo una solución:\n"
					+ (-b/(2*a)) );
		} else {
			MyUtils.retroPrint("\nLa ecuación "+a+"·X² + "+b+"·X + "+c+" tiene dos soluciones:\n"
					+ "Solución 1: x="+ (-b+Math.sqrt(insideRoot))/(2*a) +"\n"
					+ "Solución 2: x="+ (-b-Math.sqrt(insideRoot))/(2*a) +"\n");
		}

		MyUtils.retroPrint("\n\nPulse enter para volver a menú principal.\n");
		MyUtils.readKeyboard();
	}


	/**
	 * 6) Quitar espacios:
	 * Escribir un programa que dada una frase que metemos por teclado,
	 * elimine todos los espacios y nos devuelva la frase sin ellos.
	 */
	private static void quitarEspacios() {

		String frase = MyUtils.readKeyboardString("Introduzca una frase a "
				+ "la que quitar los espacios:\n");

		MyUtils.retroPrint("\n\nLa frase sin espacios es:\n"+MyUtils.quitarEspacios(frase));

		MyUtils.retroPrint("\n\nPulse enter para volver a menú principal.\n");
		MyUtils.readKeyboard();
	}


	/**
	 * 7) Invertir cadena: Escribir un programa que dada una frase
	 * que metemos por teclado nos reescriba esa misma frase al revés.
	 */
	private static void invertirCadena() {

		String frase = MyUtils.readKeyboardString("Introduzca una frase que "
				+ "quiera invertir:\n");
		String salida = "";

		for(int i=frase.length()-1 ; i>=0 ; i--) {
			salida += frase.charAt(i);
		}

		MyUtils.retroPrint("\n\nLa frase invertida es:\n"+salida);

		MyUtils.retroPrint("\n\nPulse enter para volver a menú principal. ");
		MyUtils.readKeyboard();
		MyUtils.retroPrint("\n");
	}


	/**
	 * 8) Contar vocales y consonantes: Escribir un programa que dada una frase que
	 * metemos por teclado nos diga cuantas consonantes y cuantas vocales hay dentro.
	 * (Ojo: los símbolos tipo ,$·$€;:´´´¡¡¡''' no son ni consonantes ni vocales).
	 * Pista: pasarlas a minúsculas y considerar que las letras van seguidas, o sea,
	 * si x es una letra x>='a' y x<='z'.
	 */
	private static void contarVocalesYConsonantes() {

		String frase = MyUtils.readKeyboardString("Introduzca una frase para "
				+ "contar las vocales y consonantes:\n");
		String salida = "";
		int numVocales=0;
		int numConsonantes=0;
		int numOtros=0;

		for(int i=0 ; i<frase.length() ; i++) {

			boolean encontrado = false;
			int charInt = (int)(Character.toLowerCase( frase.charAt(i) ) );

			for( char grupoVocales[]:vocales ) {
				for( char vocal:grupoVocales ) {

					if( (int)vocal == charInt ) {
						numVocales++;
						encontrado=true;
						break;
					}
				}
				if(encontrado) break;
			}

			if( !encontrado ) {
				if( charInt >= 97 && charInt <= 122 ) {
					numConsonantes++;
				} else {
					numOtros++;
				}
			}
		}

		if     ( numVocales >  1 ) salida += "  "+numVocales+" vocales.\n";
		else if( numVocales == 1 ) salida += "  1 vocal.\n";

		if     ( numConsonantes >  1 ) salida += "  "+numConsonantes+" consonantes.\n";
		else if( numConsonantes == 1 ) salida += "  1 consonante.\n";

		if     ( numOtros >  1 ) salida += "  "+numOtros+" caracteres no alfabéticos.\n";
		else if( numOtros == 1 ) salida += "  1 caracter no alfabético.\n";

		MyUtils.retroPrint("\n\nLa frase\n"+frase+"\ntiene un total de "
				+ frase.length() +" caracteres repartidos en:\n"+salida);

		MyUtils.retroPrint("\n\nPulse enter para volver a menú principal.\n");
		MyUtils.readKeyboard();
	}


	/**
	 * 9) Pasar vocales a mayúsculas y consonantes a minúsculas.
	 * Escribir un programa que pase las vocales a letras mayúsculas
	 * y las consonantes a minúsculas en una frase entrada por teclado.
	 */
	private static void vocalesAMayusculasYConsonantesAMinusculas() {

		String frase = MyUtils.readKeyboardString("Introduzca una frase para "
				+ "pasar las vocales\na mayúsculas y consonantes a minúsculas:\n");
		String salida = "";

		for(int i=0 ; i<frase.length() ; i++) {

			boolean encontrado = false;
			int charInt = (int)(Character.toLowerCase( frase.charAt(i) ) );

			for( char grupoVocales[]:vocales ) {
				for( char vocal:grupoVocales ) {

					if( (int)vocal == charInt ) {
						salida += Character.toUpperCase(frase.charAt(i));
						encontrado=true;
						break;
					}

				}
			}

			//si no se ha encontrado (no es vocal) y está en el rango del alfabeto, es consonante
			if( !encontrado ) {
				if( charInt >= 97 && charInt <= 122)
					salida += Character.toLowerCase(frase.charAt(i));
				else
					salida += frase.charAt(i);
			}

		}

		MyUtils.retroPrint("\n\nSe muestra en primer lugar la frase original\n"
				+ "y a continuación la convertida:\n\n"+frase+"\n"+salida);

		MyUtils.retroPrint("\n\nPulse enter para volver a menú principal.\n");
		MyUtils.readKeyboard();
	}


	/**
	 * 11) Extractor de Vocales: Escribe un programa que dada una cadena
	 * introducida por teclado nos devuelva las vocales.
	 */
	private static void extractorDeVocales() {

		String frase = MyUtils.readKeyboardString("Introduzca una frase para "
				+ "extraer todas las vocales:\n");
		String salida = "";

		for(int i=0 ; i<frase.length() ; i++) {

			for( char grupoVocales[]:vocales ) {
				for( char vocal:grupoVocales ) {

					if( vocal == Character.toLowerCase( frase.charAt(i) ) ) {
						salida += frase.charAt(i);
						break;
					}

				}
			}

		}

		MyUtils.retroPrint("\n\nLa frase\n"+frase+"\ncontiene todas estas vocales:\n"+salida);

		MyUtils.retroPrint("\n\nPulse enter para volver a menú principal.\n");
		MyUtils.readKeyboard();

	}


	/**
	 * 12) Factorial: Escribe un programa que calcule el factorial de un numero.
	 */
	private static void factorial() {

		String mensaje = "Introduzca un número entero en el rango de 1 y 65: ";

		int numero = MyUtils.readKeyboardInt(mensaje);
		while( numero < 1 && numero > 66 ) {
			MyUtils.retroPrint("El número "+numero+" introducido no está entre el rango de 1 a 65 solicitado.\n");
			numero = MyUtils.readKeyboardInt(mensaje);
		}

		MyUtils.retroPrint("\nEl factorial de "+numero+" es: " + factorial( (long)numero ) );
		MyUtils.retroPrint("\n\nPulse enter para volver a menú principal.\n");
		MyUtils.readKeyboard();
	}
	//llamamos recursivamente hasta que se retorne 1 y multiplicamos los resultados.
	private static long factorial(long numero) {
		if (numero<=1)
			return 1;
		else
			return numero * factorial(numero-1);
	}


	/**
	 * 13) Palíndromo: Escribe un programa que calcule si una cadena
	 * introducida por teclado es palíndromo o no.
	 * Ejemplo: Dabale arroz a la zorra el abad, saldría positivo.
	 * Ojo con las mayúsculas y minúsculas. No deben influir.
	 */
	private static void palindromo() {

		String frase = MyUtils.readKeyboardString("Introduzca una frase para "
				+ "verificar si es palíndroma:\n");

		//quitamos los espacios y pasamos a minúculas
		String fraseLimpia = MyUtils.quitarEspacios(frase).toLowerCase();

		//quitamos las tildes
		for( int i=0 ; i<vocales[1].length ; i++ )
			fraseLimpia=fraseLimpia.replace(vocales[1][i], vocales[0][i]);

		//invertimos la frase
		String fraseLimpia_inversa = "";
		for( int i=fraseLimpia.length()-1 ; i >=0 ; i-- ) {
			fraseLimpia_inversa += fraseLimpia.charAt(i);
		}

		//comparamos
		if( fraseLimpia.equals(fraseLimpia_inversa) )
			MyUtils.retroPrint("\nLa frase dada ES palíndroma.");
		else
			MyUtils.retroPrint("\nLa frase dada NO ES palíndroma.");

		MyUtils.retroPrint("\n\nPulse enter para volver a menú principal.\n");
		MyUtils.readKeyboard();
	}



	/**
	 * 14) Encriptador: Escribe un programa que reciba dos entradas.
	 * La primera una frase y la segunda una letra E o D (minúscula o mayuscula).
	 * Si la letra es "E" entonces encriptará la frase desplazando las vocales, es decir:
	 *  'a'='e', 'e'='i','i'='o','o'='u' y 'u'='a'.
	 * Si la letra es "D" entonces desencriptará la frase haciendo el cambio contrario.
	 * Nota: Para probarlo encriptad una frase, copiadla y probad a desencriptarla.
	 */
	private static void cifrador() {

		String frase = MyUtils.readKeyboardString("Introduzca una frase para "
				+ "cifrarla o descifrarla:\n");

		String cifrar = MyUtils.readKeyboardString("Introduzca E para cifrar o D para descifrar:\n");
		while( !cifrar.toUpperCase().equals("E") && !cifrar.toUpperCase().equals("D") ) {
			MyUtils.retroPrint("Valor incorrecto.\n");
			cifrar = MyUtils.readKeyboardString("Introduzca E para cifrar o D para descifrar:\n");
		}

		String salida = "";

		for(int i=0 ; i<frase.length() ; i++) {
			boolean encontrado = false;

			//recorremos cada grupo de vocales
			for( char grupoVocales[]:vocales ) {

				for( int j=0 ; j<grupoVocales.length ; j++ ) {
					if( Character.toLowerCase( frase.charAt(i) ) == grupoVocales[j] ) {
						//hemos encontrado la vocal

						//movemos la posición encontrada hacia adelante o atrás
						//según haya que cifrar o descifrar
						int key;
						if( cifrar.toUpperCase().equals("E") ) {
							if( j==grupoVocales.length-1 ) key = 0;
							else key = j+1;
						} else {
							if( j==0 ) key = grupoVocales.length-1;
							else key = j-1;
						}

						//Comprobamos si está en mayúsculas
						if(Character.toLowerCase( frase.charAt(i) ) == frase.charAt(i) )
							salida += grupoVocales[key];
						else
							salida += Character.toUpperCase( grupoVocales[key] ); //La pasamos a mayúscula

						encontrado = true;
						break;
					}
				}
				if( encontrado ) break;
			}

			if( !encontrado )
				salida += frase.charAt(i);
		}

		if( cifrar.toUpperCase().equals("E") )
			MyUtils.retroPrint("\nFrase original:\n"+frase+"\nFrase cifrada:\n"+salida);
		else
			MyUtils.retroPrint("\nFrase original:\n"+frase+"\nFrase descifrada:\n"+salida);


		MyUtils.retroPrint("\n\nPulse enter para volver a menú principal.\n");
		MyUtils.readKeyboard();
	}



	/**
	 * 15) Diamante de asteriscos: Hacer un programa que dado un numero,
	 * dibuje un diamante de asteriscos de ese número de filas.
	
	 * Ejemplo. Para el numero 5
	         *
	        ***
	       *****
	        ***
	         *
	
	 * Para el numero 8
	         *
	        ***
	       *****
	      *******
	      *******
	       *****
	        ***
	         *
	*/
	private static void diamanteDeAsteriscos() {

		int lineas = MyUtils.readKeyboardInt("Introduzca el número de filas que tendrá el diamante:");
		while( lineas < 1 | lineas > 325 ) {
			MyUtils.retroPrint("Tiene que escoger un número entero entre el 1 y el 325\n"
					+ "que es el límite de líneas para la consola de eclipse.\n");
			lineas = MyUtils.readKeyboardInt("Introduzca el número de filas que tendrá el diamante:");
		}

		/* formateamos los números de las líneas
		 * "%01d" sólo 1 caracter (cuando líneas < 10¹)
		 * "%02d" 2 carácteres (01,02...99) (cuando líneas < 10²)
		 * etc... 
		 */
		String lineFormat="%01d";
		for( int i=1 ; Math.pow(10, i) <= lineas ; i++ ) {
			lineFormat = "%0"+(i+1)+"d";
		}

		String salida = "";

		//calculamos la mitad con redondeo superior si hubiese decimales
		int mitadLineas = (int)Math.ceil( (float)lineas/2 );

		int contador = 1; //para los números de línea

		//Primera mitad del diamante
		for( int i=1 ; i <= mitadLineas ; i++ ) {
			salida += "["+ String.format(lineFormat, contador++) +"] ";

			//espacios
			for(int j=1 ; j<=mitadLineas-i ; j++ )
				salida += " ";

			//asteriscos
			for(int k=1 ; k<=(i+i-1) ; k++ )
				salida += "*";

			salida += "\n";

			//si es par y estamos en la parte del centro, añadimos otra línea igual que la anterior.
			if( lineas%2 == 0 && i == mitadLineas ) {
				salida += "["+ String.format(lineFormat, contador++) +"] ";
				for(int k=1 ; k<=(i+i-1) ; k++ )
					salida += "*";
				salida += "\n";
			}
		}

		//Segunda mitad del diamante
		for( int i=mitadLineas-1 ; i >= 1 ; i-- ) {
			salida += "["+ String.format(lineFormat, contador++) +"] ";

			//espacios
			for(int j=1 ; j<=mitadLineas-i ; j++ )
				salida += " ";

			//asteriscos
			for(int k=1 ; k<=(i+i-1) ; k++ )
				salida += "*";

			salida += "\n";
		}

		MyUtils.retroPrint(salida, 1);


		MyUtils.retroPrint("\n\nPulse enter para volver a menú principal.\n");
		MyUtils.readKeyboard();
	}

}


class MyUtils {

	private static int textSpeed = 3; //Standard velocity between characters

	/**
	 * Clear console printing 200 new lines
	 */
	public static void cleanConsole() {
		for( int i=0 ; i<200 ; i++ ) {
			System.out.println();
		}
	}

	/**
	 * Read (form keyboard) 
	 * @return String
	 */
	public static String readKeyboard() {
		Scanner sc = new Scanner(System.in);
		return sc.nextLine();
	}

	/**
	 * Read (form keyboard) and repeat while is empty
	 * @return String
	 */
	public static String readKeyboardString(String mensaje) {

		if( !mensaje.equals("") )
			MyUtils.retroPrint(mensaje);

		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();

		while( input.equals("") ) {
			retroPrint("No ha introducido ningún valor.\n"+mensaje);
			input = sc.nextLine();
		}

		return input;
	}


	/**
	 * Read (form keyboard) and repeat while is not int
	 * @return int
	 */
	public static int readKeyboardInt(String mensaje) {

		if( !mensaje.equals("") )
			MyUtils.retroPrint(mensaje);

		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		int output = 0;
		boolean found = false;

		while( !found ) {
			try {
				output = Integer.valueOf(input);
				found = true;
			}
			catch (NumberFormatException ex) {
				found = false;
				retroPrint("Valor introducido incorrecto.\nDebe introducir un número "
						+ "entero\n"+mensaje);
				input = sc.nextLine();
			}
		}
		return output;
	}
	public static int readKeyboardInt() {
		return readKeyboardInt("");
	}


	/**
	 * Read (form keyboard) and repeat while is not Float
	 * @return Float
	 */
	public static float readKeyboardFloat(String mensaje) {

		if( !mensaje.equals("") )
			MyUtils.retroPrint(mensaje);

		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		float output=0;
		boolean found=false;

		while( !found ) {
			try {
				output = Float.parseFloat(input);
				found = true;
			}
			catch (NumberFormatException ex) {
				found = false;
				retroPrint("Valor introducido incorrecto.\nUnilice el \".\" (punto) "
						+ "para los decimales.\n"+mensaje);
				input = sc.nextLine();
			}
		}
		return output;
	}


	/**
	 * Read (form keyboard) and repeat while is not double
	 * Cut to decimals numbers
	 * @return double
	 */
	public static double readKeyboardDouble(String mensaje, int decimals) {

		if( !mensaje.equals("") )
			MyUtils.retroPrint(mensaje);

		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		double output=0;
		boolean found=false;

		while( !found ) {
			try {
				output = Double.parseDouble(input);
				found = true;
			}
			catch (NumberFormatException ex) {
				found = false;
				retroPrint("Valor introducido incorrecto.\nUnilice el \".\" (punto) "
						+ "para los decimales.\n"+mensaje);
				input = sc.nextLine();
			}
		}

		if( decimals > 0) {
			return (double)(Math.round(output*Math.pow(10,decimals)))/(Math.pow(10,decimals));
		}
		return output;
	}


	/**
	 * Read (form keyboard) and return it if is a valid option
	 *  Get:
	 * 	 array validOptions -> array of valid options to compare
	 *  @return String 
	 */
	public static String readWhitOptions( String[] validOptions ) {

		boolean found=false;
		String input=readKeyboard();

		for( String option:validOptions ) {
			if( option.toUpperCase().equals(input.toUpperCase())  ) {
				found=true;
			}
		}

		if( found ) {
			return input;
		} else {
			return "";
		}
	}

	/**
	 * Retorna un stream sin espacios
	 * @param String frase => string a quitar los espacios
	 * @return String => string sin espacios
	 */
	public static String quitarEspacios(String frase) {
		String salida = "";
		for(int i=0 ; i<frase.length() ; i++) {
			if( (int)(frase.charAt(i)) != 32  )
				salida += frase.charAt(i);
		}
		return salida;
	}


	/**
	 * Escribe en la salida estándar String en "modo retro"
	 * @param
	 * 	String text -> Texto a imprimir
	 * 	int velocity -> retardo de tiempo entre caracteres
	 * 		De 1 a 10
	 * 		Por defecto: 5
	 */
	public static void retroPrint(String text, int speed) {

		if( speed<1 || speed>10 ) {
			speed = 5; //set default value if speed arg is out of range
		}
		for (int i=0 ; i<text.length() ; i++) {
			try {
				System.out.print(text.charAt(i));
				Thread.sleep(MyUtils.textSpeed*speed);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public static void retroPrint(String text) {
		retroPrint(text, 5);
	}

	/**
	 * Cambia la velocidad para retroPrint
	 * @param velocity
	 */
	public static void changeRetroVelocity(String velocity) {
		switch( velocity.toUpperCase() ) {
		case "N":
			MyUtils.textSpeed=3;
			break;
		case "S":
			MyUtils.textSpeed=7;
			break;
		case "F":
			MyUtils.textSpeed=1;
			break;
		case "D":
			MyUtils.textSpeed=0;
			break;
		}
	}

	/**
	 * Pone un punto (decimal) separando los 2 últimos caracteres
	 * @param Stream money
	 * @return Stream money (formateado)
	 */
	public static String decimalFormat(String money) {

		if( money.length() <= 1 )
			return "0.0"+money;
		if( money.length() == 2 )
			return "0."+money;

		String out="";
		for( int i=0 ; i<money.length() ; i++ ) {

			if( i+2 == money.length() )
				out+=".";

			out+=money.charAt(i);
		}		
		return out;
	}

	/**
	 * Return int(s) if is int or 0 if not
	 * @param String s
	 * @return true if isInt
	 */
	public static boolean isInt(String s)
	{
		try {
			Integer.valueOf(s);
			return true;
		}
		catch (NumberFormatException ex) {
			return false;
		}
	}

	/**
	 * Return int(s) if is int or 0 if not
	 * @param String s
	 * @return int or 0
	 */
	public static int returnIsInt(String s)
	{
		try {
			return Integer.valueOf(s);
		}
		catch (NumberFormatException ex) {
			return -0;
		}
	}

	/**
	 * String
	 * añade un valor(o array) a array
	 */
	public static String[] arrayAdd(String[] array, String value) {

		if( array.length < 1 ) {
			String retorno[] = {value};
			return retorno;
		}

		String retorno[] = new String[array.length+1];
		System.arraycopy(array, 0, retorno, 0, array.length);
		retorno[retorno.length-1] = value;
		return retorno;
	}

	/**
	 * Int
	 * añade un valor(o array) a array
	 */
	public static int[] arrayAdd(int[] array, int value) {

		if( array.length < 1 ) {
			int retorno[] = {value};
			return retorno;
		}

		int retorno[] = new int[array.length+1];
		System.arraycopy(array, 0, retorno, 0, array.length);
		retorno[retorno.length-1] = value;
		return retorno;
	}



}
