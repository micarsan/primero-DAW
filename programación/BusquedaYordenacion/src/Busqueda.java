import java.util.Scanner;

public class Busqueda {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final int NUMERO=100000000;
		
		//int[]nuestraLista = generarListaDesordenada(NUMERO);
		long inicio = System.currentTimeMillis();
		int[]nuestraLista = generarListaOrdenada(NUMERO);
		long fin = System.currentTimeMillis();
		long tiempo = fin - inicio; 
		System.out.println(tiempo+" milisegundos en generar la lista ordenada");
		System.out.println();
		
		//Ponemos un número nosotros
		int valor = nuestraLista[NUMERO*2/3];
	
		//Búsqueda Lineal.
		inicio = System.currentTimeMillis(); 
		int resultado=busquedaLineal(nuestraLista, valor);	
		fin = System.currentTimeMillis();
		tiempo = fin - inicio;  
		System.out.println(tiempo +" milisegundos en encontrar el elemento por búsqueda lineal\n");
				
		//Búsqueda Binaria.
		inicio = System.currentTimeMillis(); 
		resultado=busquedaBinariaRecursiva(nuestraLista, valor);	
		fin = System.currentTimeMillis();
		tiempo = fin - inicio;  
		System.out.println(tiempo+" milisegundos en encontrar el elemento por búsqueda binaria\n");

		//Búsqueda Recursiva.
		inicio = System.currentTimeMillis(); 
		resultado=busquedaBinariaIterativa(nuestraLista, valor);	
		fin = System.currentTimeMillis();
		tiempo = fin - inicio;  
		System.out.println( (fin-inicio) + " milisegundos en encontrar el elemento por búsqueda recursiva\n");

		if (resultado>=0) {
			System.out.println("El valor "+valor+" está en la posición "+resultado);
		} else  {
			System.out.println("El valor "+valor+" no está en nuestra lista");
		}
	}

	private static int[] generarListaDesordenada (int numeroElementos) {
		int[] listaDesordenada = new int[numeroElementos];
		
		for (int i = 0; i < numeroElementos; i++) {
			listaDesordenada[i] = (int)Math.floor(Math.random()*1000);
		}
		return listaDesordenada;
	}
	
	private static int[] generarListaOrdenada (int numeroElementos) {
		
		int[] listaOrdenada = new int[numeroElementos];
		final int VALORINICIAL= 1;
		
		for (int i = 0; i < numeroElementos; i++) {
			if (i==0) {
				listaOrdenada[i] = VALORINICIAL;
			}else {
				listaOrdenada[i] = listaOrdenada[i-1] + (int)Math.floor((Math.random()*5)+1);
			}
		}
		return listaOrdenada;
	}

	private static int busquedaLineal(int[] listaElementos, int valor) {
		int contador=0;
		for (int i=0;i<listaElementos.length;i++) {
			if (listaElementos[i]==valor) {
				System.out.println("Número de comparaciones: "+contador);
				return i;
			}	
			contador++;
		}
		return -1;
	}

	private static int busquedaBinariaIterativa(int[] listaElementos,int valor) {
		
		int firstIndex = 0;
		int lastIndex = listaElementos.length-1;
		int contador = 0;
		
		while (firstIndex<=lastIndex) {
			//Creamos el índice de enmedio
			int midIndex= (firstIndex+lastIndex)/2;
			//Caso feliz. Hemos encontrado el valor buscado.
			contador++;
			if (listaElementos[midIndex]==valor) {
				return midIndex;
			//Caso que el midIndex sea más pequeño que el valor buscado.
			} else if (listaElementos[midIndex]<valor) {
				firstIndex=midIndex+1;
			}
			//Caso que el midIndex sea más grande que el valor buscado.
			else {
				lastIndex = midIndex-1;
			}
		}
		System.out.println("Número de comparaciones: "+contador);
		return -1;
	}

	private static int busquedaBinariaRecursiva(int[] listaElementos,int valor) {
		int firstIndex=0;
		int lastIndex=listaElementos.length-1;
		return binSubFuncion(listaElementos,firstIndex,lastIndex,valor);
	}
	
	private static int binSubFuncion(int[] listaElementos, int firstIndex, int lastIndex, int valor) {
		
		if( lastIndex >= firstIndex ) {
			int midIndex = (firstIndex + lastIndex)/2;
			
			if( listaElementos[midIndex] == valor )
				return midIndex;
			else if ( listaElementos[midIndex]>valor )
				return binSubFuncion(listaElementos, firstIndex, midIndex-1, valor);
			else
				return binSubFuncion(listaElementos, midIndex+1, lastIndex, valor);
			
		} else
			return -1;
		
	}

}
