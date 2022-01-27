/**
 * 
 * Máquina enigma
 * Cifra y descifra mensajes 
 *
 * IMPORTANTE: Codificación UTF-8
 * (botón derecho, propiedades -> other:UTF-8)
 *
 * @author Equipo de programación:
 * 	Javier Ramos del Río
 * 	Alberto Fernandez Baena
 * 	Juan José Montijano Cantero
 * 	Miguel Carmona
 */

/*
 * Si se le pasa como parámetro --cli, se activa el modo consola.
 * Permite entrada de datos por argumentos y salida en texto para
 * utilizarla (en linux, añadir a un >> archivo existente para
 * tener todas las frases por ejemplo) 
 * 
 * --cli <= activa el modo consola
 * --iniciio <= posición inicial para el rotor A
 * --fin <= posición final para el rotor A
 * --mensajeid <= Índice del array frasesCifradas
 * --raw <= si existe, hace un volcado de todas las frases sin comparar 
 */

/*
 * ejecutado así (lanzo 10 hilos de golpe):
 * java -cp . EquipoEnigma.java --cli --inicio 50 --fin 59 --mensajeid 1 >> ~/enigmaSalida &
 * Se obtiene:
 * 
 * Para toda las del 24 de noviembre:
    A:59 B:31 C:92 D:87
    
    Unidad de reconocimiento de Kurtinowa, noroeste de Sebez en el corredor de vuelo en dirección a Dubrowki, Opotschka.
    Empezó a moverse a las 18:30. Ataque. Regimiento de infantería 3 avanza despacio pero seguro.
    Hora 17:06, I (número romano) Regimiento de infantería 3 en el corredor de vuelo comenzando a 16 km hacia el este de Kamenec.
    
    Informe del Tiempo. Sabado, 24 noviembre 1941. 6:45h a.m. U-505 U-Boat en periodo de entrenamiento.
    Cielos parcialmente nublados. Ausencia total de precipitaciones.
    Mar en calma. Viento noreste 6. Visibilidad total.
    
    Alerta, U-264 bajo ataque. contenido del radiotelegrama 1132/19: Obligado a sumergirse bajo ataque, cargas de profundidad.
    Última ubicación enemiga 08:30 horas, plaza marítima AJ9863, siguiendo 220 grados, 8 nudos.
    [Presión] 14 milibares cayendo, [viento] noreste 4, visibilidad 10.
    
    LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL
    LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL QUE OS DEN BRITANICOS
    
   Para el último mensaje del 30 de abril:
    A:59 B:31 C:92 D:87
   
    MENSAJE DE EMERGENCIA DE GUERRA [Para] Todos: Lo siguiente se anunciará de inmediato:He recibido la siguiente orden:
    'En lugar del anterior Reichsmarschall 'Göring', el Führer le ha nombrado,Herr Grossadmiral, como su sucesor.
    La autorización por escrito [está] en camino. Con efecto inmediato, debe solicitar todas las medidas,
    que son requeridas por la situación actual. Firmado, Reichsleiter (Tulpe) 'Bormann':[De] Comandante en Jefe de la Armada,
    [enviado] a través de la emisora de radio al Almirante Comandante de Submarinos.
 
 */

import java.util.Scanner;

public class EquipoEnigma {

	public static String palabrasClave[][] = {
			{"noviembre","1941","potschka","17:00"},
			{"noviembre","informe","tiempo","1941"},
			{"noviembre","u-boat","22:30","1941"},
			{"noviembre","1941"},
			{"muert","guerra","rendi","mensaje"}
			};
	
	public static String frasesCifradas[][] = {
			{
				"Mensaje 1: 24 noviembre 1941. Cerca de Opotschka. sobre las 17:00. 16ª División Motorizada de Infanteria",
				"Hkgn/_<$-Lar9AOV{9l]ggFQ8r1;FyL5N<$6dn.^EC,Z;~drJ|ho}gdL2DPG&PKTjT\\kY#/sg}.D[{9JOEmHJ^kg,Npó!KC<tV>`rFU\\!({a#**}Nn.]_\"|6S\\ón~_ys.vQe+62KtubX#u[Y8!;A_J;z:@$UX/gB/zzN'nS=UeGgiXc{tí3CC_|cLLTcdsqccua3E2-<2Kn8;X^p(tvKq#3!Q\\eb)?Y!dt+úpvx&Uq,91GnJ]0NR\"Ipe!\"Im%wK1qvKCa?/íd&I3KjD}<Eb&`M^L0;'Qw\"``6{C4flW/kQ..oK6Jejj}Kx'TOsb'Ps$R K^&PQIy^D_dkq0+"
			},{
				"Mensaje 2: 24 noviembre 1941. Canal de la Mancha. Suroeste de Inglaterra. 6:45 h.",
				"<~d-@{\"R,E[@*NFg)R-'Um4V|1WN-KYBTW}7pX!hUtU<XK~< ^}88y-R@q7gU'`$P\",)V;<lqK~FX3<R?rcAemg*;cu{ 1G'ELiiF4O[dwL]t$y Gk:1NMtA\\S[W*@'sP-+cDwJ+|qyIlN./3S,{fW:dI~xs_'r@?v+cB8[F`]R|vGf nM\\^%*5FeBSVbY+Icx?<`M1/4#cuJ:UGC@q\\naj"
			},{
				"Mensaje 3: 24 noviembre 1941. Mar Céltico. U-Boat. 22:30",
				"4Mc`B@H#|=!'i5C,##~9v>C;} KbjVNiSzxq#+s@U'}=1]s<R`PryV`5CSt=OXdnztLV^^}<r{o1Yo:$B+h~Y>xH*9vjslCZFrg:f\\\\'VHL\"xa'YU6BjDrsSR<xÚ58!U$t_8:]=r|óhSbG2/|U(z&{n3o-wmp$|[P!$6=Ky@BiípqRP1Chj?FVGP^+A[NXz8bw0?eu$-!$^{aT!a'@[Vu\"Q@p{1*\\óH0dxPPp^rc8D/tC<bfW'W3(K'?)Z?@5e)+qc\\}`2\"&7^6VO<.G9^hGOkxEP#"
			},{
				"Mensaje 4: 24 Noviembre 1941. 7:05.",
				"?VJcyThxs&;f\"+-@d)KMNR};dgwtG]&y1'`wO<ZI\"*hNscKL2p5ud^K+^8.!l=wJG;6_AlhMOqZ'2ox4{9K\"2NE[tDS`^m.^|#(0Op65AJx3_Wb\\2Exq,~[g:$E1t\"cFnS64|X(m_VFzI xh_5rBB+!G,T[EJiUv|Wc{n1Fy->0C_,FXYe)NgjrwBh1-<:czJ?UT-=savfFO-{@)CvW'->0_oI%1@D.j1ypW],"
			},{
				"Mensaje Final: 30 de Abril de 1945. De las oficinas del Reichsleiter Bormann, propagado y repetido por múltiples elementos en el mar.",
				"@VLqnYaSk&nf#+3B]2BQC-u;8i!tMjzT@2uEdT.XEI,|a>Kve?R8.#dT'XaO&w-spdM$V:á(\\2#KI8'S.UhLtQS60d_%i2;)EJ2jk5FeSj0bm2z*?e0fT`I1bKcS~O,e|z^P4(6;q!aUD@+'pc)^]Y-j6{p yköD$!h]8kIAJ?üfdLK3X&;q[EsBB&6I3d\"}BlZ/fju0|#=w)I4P.LDXYe;Z +).@^7-A`&gN_QZ4=xc4hLYózG*f|e4d?+&sP*QGR=á)?`va8VO.9rt.7MSLn>{bkU;Z?6~sGsWQWH\\Agpm]ya!(q(.1YZ*1@Ks{>i!fk-Xs|6Tq#F3[Nz:ybjnBC&k#xi M4RI{suRr`N6zZólfP9(V\"t/nkcM?%|WGl=;;eM#!]\\)c4M:pL4TTMUo#m+T`yp7kT\\+)R>+G9SIuDw_.lDgfq.'~nKNU58]qVsZCj{`:~dZN-RJ}+wBo[éAAV*v`AZq[#1qn '0<<8B5WgN@_iI.^q(l)OhArDJe4GC\\c#_pi7dTT3wC5v$bf"
			}};
	
	public static boolean cli=false; //modo entrada y salida estándar por consola
	public static boolean raw=false; //modo entrada y salida estándar por consola
	
	
	/**
	 * Constructor
	 */
	public EquipoEnigma() {
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		int posiciones[]=check_args(args);

		if( cli ) { //modo consola
			
			System.out.println();
			
			if( posiciones[2] > 0 ) { //se recibe un índice de palabrasClave
				
				System.out.println("\n#### DESCIFRANDO UNA SOLA FRASE ####\n(puede tardar bastante tiempo para dar resultados)\n");
				for( int j=0 ; j<palabrasClave[ posiciones[2] ].length ; j++ )
					System.out.println("claves: "+palabrasClave[ posiciones[2] ][j]);
				
				System.out.println(frasesCifradas[ posiciones[2] ][0]+"\n");
				fuerzaBruta(frasesCifradas[ posiciones[2] ][1], palabrasClave[ posiciones[2] ], posiciones[0], posiciones[1]);
				
			} else { 
				for( int i=0 ; i<frasesCifradas.length ; i++ ) {
					System.out.println("\n#### DESCIFRANDO NUEVA FRASE ####\n(puede tardar bastante tiempo para dar resultados)\n");

					for( int j=0 ; j<palabrasClave[i].length ; j++ )
						System.out.println("claves: "+palabrasClave[i][j]);

					System.out.println(frasesCifradas[i][0]+"\n");
					fuerzaBruta(frasesCifradas[i][1], palabrasClave[i], posiciones[0], posiciones[1]);
				}
			}
			System.out.println("\n\n*** FUERZA BRUTA FINALIZADA ***\n\n");
		} else
			menu();

	}
	
	
	/**
	 * Menú principal
	 */
	public static void menu() {
		System.out.println("********************************************");
		System.out.println("************** MÁQUINA ENIGMA **************");
		System.out.println("********************************************\n");
		System.out.println(" 1 - Cifrar un texto");
		System.out.println(" 2 - Descifrar un texto");
		System.out.println(" 3 - Ejecutar la fuerza bruta según lo programado");
		System.out.println("\n 0 - Salir\n");
		
		int opcion=leerNumero("\n Seleccione una opción:", "\n   Opción incorrecta.", 0, 3);
		
		Scanner sc = new Scanner(System.in);
		String frase;
		int a,b,c,d;

		switch( opcion ) {
		case 0:
			System.out.println(" Hasta otra amigo!!");
			System.exit(0);
			break;
		case 1:
			System.out.println("\nIntroduzca la frase a cifrar:\n");
			frase=sc.nextLine();
			System.out.println();
			a =leerNumero("Posición del rotor A:"," Incorrecto. Rango permitido de 0 a 99.\n",0,99);
			b =leerNumero("Posición del rotor B:"," Incorrecto. Rango permitido de 0 a 99.\n",0,99);
			c =leerNumero("Posición del rotor C:"," Incorrecto. Rango permitido de 0 a 99.\n",0,99);
			d =leerNumero("Posición del rotor D:"," Incorrecto. Rango permitido de 0 a 99.\n",0,99);
			System.out.println("\nLa frase introducida es:\n" + frase);
			System.out.println("\nLa frase cifrada es:\n" + cifrarFrase(frase, a, b, c, d) + "\n\n");
			break;
		case 2:
			System.out.println("\nIntroduzca la frase a descifrar:\n");
			frase=sc.nextLine();
			System.out.println();
			a =leerNumero("Posición del rotor A:"," Incorrecto. Rango permitido de 0 a 99.\n",0,99);
			b =leerNumero("Posición del rotor B:"," Incorrecto. Rango permitido de 0 a 99.\n",0,99);
			c =leerNumero("Posición del rotor C:"," Incorrecto. Rango permitido de 0 a 99.\n",0,99);
			d =leerNumero("Posición del rotor D:"," Incorrecto. Rango permitido de 0 a 99.\n",0,99);
			System.out.println("\nLa frase introducida es:\n" + frase);
			System.out.println("\nLa frase descifrada es:\n" + descifrarFrase(frase, a, b, c, d) + "\n\n");
			break;
		case 3:
			System.out.println("\n** FUERZA BRUTA **\n");
			a =leerNumero("Introduzca el inicio rotor A:"," Incorrecto. Rango permitido de 0 a 99.\n",0,99);
			b =leerNumero("Introduzca el fin rotor A:"," Incorrecto. Rango permitido de 0 a 99.\n",0,99);
			System.out.println();
			for( int i=0 ; i<frasesCifradas.length ; i++ ) {
				System.out.println("\n#### DESCIFRANDO NUEVA FRASE ####\n(puede tardar bastante tiempo para dar resultados)\n");
				System.out.println(frasesCifradas[i][0]+"\n");
				fuerzaBruta(frasesCifradas[i][1], palabrasClave[i], a, b);
			}
			System.out.println("\n\n*** FUERZA BRUTA FINALIZADA ***\n\n");
			break;
		}
		
		menu();
		
	}

	/*
	 * Comprueba los argumentos args
	 * retorna
	 *  [0] rotor A inicio
	 *  [1] rotor A fin
	 *  [2] rotor A índice array mensajes
	 */
	private static int[] check_args(String[] args) {
		int retorno[] = {0,0,-1};
		
		for( int i=0 ; i<args.length ; i++ ) {
			
			switch( args[i] ) {
			case "--cli":
				cli=true;
				break;
			case "--raw":
				raw=true;
				break;
			case "--mensajeid": //número índice para frasesCifradas 
				if( i+1<args.length ) {
					if( ( retorno[2]=enteroPositivo(args[i+1]) ) < 0 ) {
						System.out.println("El valor de inicio no es válido");
						System.exit(0);
					} else {
						if( retorno[2] >= frasesCifradas.length ) {
							System.out.println("No se ha encontrado el mensaje seleccionado");
							System.exit(0);
						}
					}
				}
				break;
			case "--inicio": 
				if( i+1<args.length ) {
					if( ( retorno[0]=enteroPositivo(args[i+1]) ) < 0 ) {
						System.out.println("El valor de inicio no es válido");
						System.exit(0);
					}
				}
				else {
					System.out.println("No se recibió el valor de inicio para el rotor A.");
					System.exit(0);
				}
				break;
			case "--fin": 
				if( i+1<args.length ) {
					if( ( retorno[1]=enteroPositivo(args[i+1]) ) < 0 ) {
						System.out.println("El valor de fin no es válido");
						System.exit(0);
					}
				}
				else {
					System.out.println("No se recibió el valor de fin para el rotor A.");
					System.exit(0);
				}
				break;
			}
		}
		return retorno;
	}

	
	/**
	 * Fuerza bruta para buscar patrones en mensajes cifrados
	 * 
	 * En dicha máquina tendremos 4 rotores cuyos valores seran enteros positivos
	 * con un valor entre 0 y 99, ambos inclusive. El 0 implica que no codifica.
	 */
	public static void fuerzaBruta(String fraseCifrada, String claves[], int inicioRotorA, int finRotorA) {
		
		String fraseDescifrada;
		int cantidadClaves;

		//Recorremos rotor A
		for( int a=inicioRotorA ; a<=finRotorA ; a++ ) {
			System.out.println("[rotor a]: "+a);
			//Recorremos el rotor B
			for( int b=0 ; b<=99 ; b++ ) {
				//Recorremos el rotor C
				for( int c=0 ; c<=99 ; c++ ) {
					//Recorremos el rotor D
					for( int d=0 ; d<=99 ; d++ ) {
						
						fraseDescifrada = descifrarFrase( fraseCifrada, a, b, c, d );
						if( raw ) {
							System.out.println("\n  A:"+a+ " B:"+b+ " C:"+c+ " D:"+d +"\n"
									+ fraseDescifrada+"\n");
						
						} else if( (cantidadClaves=encontrarPalabras( fraseDescifrada.toLowerCase(), claves )) > 0 ) {
							//hemos encontrado alguna coincidencia en esta frase
							System.out.println( "\n  Posible frase descifrada ("+cantidadClaves+" coincidencias):\n"
									+ "  A:"+a+ " B:"+b+ " C:"+c+ " D:"+d +"\n"
									+ fraseDescifrada+"\n\n");
						}
					}
				}
			}
		}
		
	}
	
	/**
	 * Busca patrones en un texto de entrada
	 */
	public static int encontrarPalabras( String texto, String[] claves ) {
		
		int retorno=0;
		
		for( String clave:claves ) {
			if( texto.contains(clave) ) {
				retorno++;
			}
		}

		return retorno;

	}
	

	/**
	 * Lee por entrada estándar y devuelve un entero en el rango introducido
	 * Si no se introduce en ese rango, vuelve a pedirlo
	 */
	public static int leerNumero(String mensaje, String error, int inicioRango, int finRango)
	{
		System.out.print(mensaje);
		
		Scanner sc = new Scanner(System.in);
		String input=sc.nextLine();
		int a;
		
		try {
			a=Integer.valueOf(input);
		}
		catch (NumberFormatException ex) {
			a=inicioRango-1;
		}
		
		if( a>=inicioRango && a<=finRango )
			return a;
		else {
			System.out.print(error);
			return leerNumero(mensaje, error,inicioRango, finRango);
		}
	}
	
	/**
	 * Devuelve un entero positivo si se le pasa
	 * @param String s
	 * @return boolean
	 */
	public static int enteroPositivo(String s)
	{
	    try {
	        int a=Integer.parseInt(s);
	        if( a>=0 )
	        	return a;
	    }
	    catch (NumberFormatException ex) {
	    }
	    return -1;
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
	public static String rotorAcifrar (String cadena, int valor) {

		
		String retorno = "";
		int contador = 1;


		for( int i=0 ; i<=cadena.length()-1 ; i++ ) {
			if (contador <=3 && (int)cadena.charAt(i)>=32 && (int)cadena.charAt(i)<=126) {
				retorno = retorno + Cifrar(cadena.charAt(i), valor);
			}else {
				retorno = retorno + cadena.charAt(i);
			}
			
			if (contador >=6) {//empezamos otro bloque
				contador=0;
			}
			contador++;
		}
		return retorno;

	}
	
	public static String  rotorAdescifrar (String cadena, int valor) {

		String retorno = "";
		int contador = 1;

		for (int i=0;i<=cadena.length()-1;i++) {
			if (contador <=3 && (int)cadena.charAt(i)>=32 && (int)cadena.charAt(i)<=126) { //ciframos el primer grupo de 3
				retorno += Descifrar(cadena.charAt(i), valor);
			}else {
				retorno = retorno + cadena.charAt(i);
			}
			
			if (contador >=6) {//empezamos otro bloque
				contador=0;
			}
			contador++;
		}
		return retorno;
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
    public static String rotorBcifrar (String cadena, int valor) {

        String retorno = "";
        
        for(int i=0;i<cadena.length();i++) {//Ciframos y si es par se le suma al valor 11
            char letra = cadena.charAt(i);
            int codigo=(int)letra;
            if(codigo>=32 && codigo<=126 && i%2==0) {
                retorno += Cifrar(letra,valor);
                valor=valor+11;

            }else {//se cifra pero no se le suma nada
                retorno += letra;
            }
        }
    
        return retorno;
        
    }
    
    public static String rotorBdescifrar (String cadena, int valor) {

        String retorno= "";
        for(int i=0;i<cadena.length();i++) {//Desciframos y si es par se le suma al valor 11
            char letra = cadena.charAt(i);
            int codigo=(int)letra;
             if(i%2==0 && codigo>=32 && codigo<=126) {
                retorno += Descifrar(letra,valor);
                valor=valor+11;

            }else {
                retorno += letra;
                
            }
        }
        return retorno;
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
    public static String rotorCcifrar (String cadena, int valor) {

        String cadenafinal="";
        
        for (int i=cadena.length()-1;i>=0;i--) {
            
            if (i%2!=0) { //Es impar.
            
                int codigo=(int)cadena.charAt(i);
                
                if (codigo>=32 && codigo<=126) {
                    cadenafinal += Cifrar(cadena.charAt(i), valor);
                    valor += 23;
                }else {
                    cadenafinal += cadena.charAt(i);
                }
                
            }else { //Es par.
                cadenafinal += cadena.charAt(i);
            }    
        }
        return cadenafinal;
    }
        
    public static String rotorCdescifrar (String cadena, int valor) {

        String cadenafinal="";
        int par = 0;
        
        if (cadena.length() % 2 == 0) {
            par = 1;
        }
        
        for (int i=0; i<cadena.length(); i++){
            
            int codigo=(int)cadena.charAt(i);
            
            if ( (i+par)%2!=0 && codigo>=32 && codigo<=126) { //Si es impar
                cadenafinal += Descifrar(cadena.charAt(i), valor);
                valor = valor + 23;
            }else {
                cadenafinal += cadena.charAt(i);
            }
        }
        String cadenaretorno="";
        
        for (int i=cadenafinal.length()-1; i>=0; i--) {
            cadenaretorno += cadenafinal.charAt(i);
        }
        return cadenaretorno;
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
	 * Invierte una cadena dada
	 */
	private static String invertir(String cadena) {
		String salida="";
		for(int i=cadena.length()-1 ; i >= 0 ; i-- ) {
			salida += cadena.charAt(i);
		}
		return salida;
	}
	
	
    public static char Cifrar (char letra, int valor) {

        int codigo=(int)letra;
        int desplazamiento = codigo+valor%95;
        int resultado;
        if (desplazamiento>126) resultado=desplazamiento-95;
        else resultado=desplazamiento;
        return (char)resultado;


    }
    public static char Descifrar (char letra, int valor) {

        int codigo=(int)letra;
        int desplazamiento = codigo-valor%95;
        int resultado;
        if (desplazamiento<32) resultado=desplazamiento+95;
        else resultado=desplazamiento;
        return (char)resultado;

    }


}
