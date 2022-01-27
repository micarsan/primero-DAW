
public class Ordenacion {
	
	public static void main( String[] args ) {
		
		final int NUMEROELEMENTOS = 50000;
		final int NUMEROPRUEBAS = 10;
		long bubbleSuma=0, quickSuma=0, mergeSuma=0;
		
		for( int i=0 ; i<NUMEROPRUEBAS ; i++ ) {
			int[] lista = generarListaDesordenada(NUMEROELEMENTOS);
			int[] lista2 = lista.clone();
			int[] lista3 = lista.clone();
			
			// BubbleSort
			long inicio = System.currentTimeMillis();
			lista = bubbleSort(lista);
			long fin = System.currentTimeMillis();
			long tiempo = fin - inicio; 
			System.out.println(tiempo+" milisegundos BubbleSort.");
			bubbleSuma+=tiempo;

			// QuickSort
			inicio = System.currentTimeMillis();
			lista2 = quickSort(lista2);
			fin = System.currentTimeMillis();
			tiempo = fin - inicio; 
			System.out.println(tiempo+" milisegundos QuickSort.");
			quickSuma+=tiempo;

			// MergeSort
			inicio = System.currentTimeMillis();
			mergeSort(lista3);
			fin = System.currentTimeMillis();
			tiempo = fin - inicio; 
			System.out.println(tiempo+" milisegundos MergeSort.");
			mergeSuma+=tiempo;

			
		}
		
		System.out.println("Resultados FINALES:");
		System.out.println("BubbleSort: "+ bubbleSuma/NUMEROPRUEBAS);
		System.out.println("QuickSort: "+ quickSuma/NUMEROPRUEBAS);
		System.out.println("MergeSort: "+ mergeSuma/NUMEROPRUEBAS);
		
		
		//for( int num : listaElementos ) System.out.print(num + " ");
		
	}
	
	private static int[] generarListaDesordenada (int numeroElementos) {
		int[] listaDesordenada = new int[numeroElementos];
		
		for (int i = 0; i < numeroElementos; i++) {
			listaDesordenada[i] = (int)Math.floor(Math.random()*numeroElementos*10);
		}
		return listaDesordenada;
	}
	
	private static int[] bubbleSort( int[] listaElementos ) {
		int temp=0;
		int n=listaElementos.length;
		boolean ordenado=true;
		
		for( int i=0 ; i<n ; i++ ) {
			for( int j=1 ; j<(n-i) ; j++ ) {
				if( listaElementos[j-1] > listaElementos[j] ) {
					//Si el que está arriba es mayor que el que está abajo, intercambiamos.
					temp=listaElementos[j-1];
					listaElementos[j-1]=listaElementos[j];
					listaElementos[j]=temp;
					ordenado=false;
				}
			}
			if( ordenado )
				return listaElementos;
		}
		
		//temporal - para que no de error
		return listaElementos;
	}
	
	private static int[] quickSort( int[] lista ) {
		int izq=0;
		int der=lista.length-1;
		
		lista = quickRecursive(lista, izq, der);
		return quickRecursive(lista, izq, der);
		
	}
	
	private static int[] quickRecursive(int[] lista, int izq, int der) {
		int pivote = lista[izq];	// El pivote es el 1er elemento
		int i=izq;					// i se mueve de izq a der
		int j=der;					// j se mueve de der a izq
		int aux;					// variable de intercambio
		
		while( i<j ) {				// mientras no se crucen
			while( lista[i]<=pivote && i<j  ) i++;	//buscamos elemento mayor que el pivote
			while( lista[j]>pivote ) j--;			//buscamos elemento menor que el pivote
			
			if( i<j ) {				// si no se cruzan, los intercambiamos
				aux=lista[i];
				lista[i] = lista[j];
				lista[j] = aux;
			}
		}
		
		lista[izq] = lista[j];		// ponemos el pivote en su lugar
		lista[j] = pivote;
		
		if( izq < j-1 ) lista=quickRecursive( lista, izq, j-1 ); //ordenamos el subarray izquierdo
		if( j+1 < der ) lista=quickRecursive( lista, j+1, der ); //ordenamos el subarray derecho
		
		
		//temporal
		return lista;
	}
	
	private static void mergeSort( int lista[] ) {
		sort( lista, 0, lista.length-1 );
	}
	
	private static void sort( int lista[], int izq, int der ) {
		if( izq<der ) {
			// Tomamos el punto medio del array
			int mid = (izq+der)/2;
			
			//Dividimos
			sort( lista, izq, mid );
			sort( lista, mid+1, der );
			
			merge( lista, izq, mid, der );
			
		}
	}
	
	private static void merge( int lista[], int izq, int mid, int der ) {
		
		//Calculamos el tamaño de las dos sublistas a mezclar
		int n1 = mid - izq +1;
		int n2 = der - mid;
		
		//Creamos 2 sublistas temporales
		int[] listaIzq = new int[n1];
		int[] listaDer = new int[n2];
		
		//Copiamos los datos a las sublistas
		for( int i=0 ; i<n1 ; i++ ) listaIzq[i] = lista[izq+i];
		for( int i=0 ; i<n2 ; i++ ) listaDer[i] = lista[mid+i+1];
		
		//Fusionamos las dos sublistas en la lista original
		//Creamos índices de utilidad
		int i=0, j=0, k=0;
		
		//Ordenamos
		while( i<n1 && j<n2 ) {
			if( listaIzq[i] <= listaDer[j] ) {
				lista[k] = listaIzq[i];
				i++;
			} else {
				lista[k] = listaDer[j];
				j++;
			}
			k++;
		}
		
		
		//Volcamos el resto de la sublista que quede
		while( i < n1 ) {
			lista[k] = listaIzq[i];
			i++;
			k++;
		}
		
		while( j < n2 ) {
			lista[k] = listaIzq[j];
			j++;
			k++;
		}
		
	}
	

}
