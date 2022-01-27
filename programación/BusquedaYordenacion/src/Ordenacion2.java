public class Ordenacion2 {

	public static void main(String[] args) {
		final int NUMEROELEMENTOS=1000000;
		final int NUMEROPRUEBAS=10;
		long bubbleSuma=0;
		long quickSuma=0;
		for(int i=0;i<NUMEROPRUEBAS;i++) {
			int[] listaElementos=generarListaDesordenada(NUMEROELEMENTOS);
			//int[] listaElementos2=listaElementos.clone();
			// Cálculo del BubbleSort
			long inicio=System.currentTimeMillis();
			listaElementos=quickSort(listaElementos);
			long fin=System.currentTimeMillis();
			long tiempo=(fin-inicio);
			bubbleSuma+=tiempo;
			System.out.println("B. Ha tardado "+tiempo +" milisegundos en ordenar por BubbleSort.");
		}
		System.out.println("Resultados finales: ");
		System.out.println("Media BubbleSort: "+(bubbleSuma/NUMEROPRUEBAS)+"ms");
		System.out.println("Media QuickSort: "+(quickSuma/NUMEROPRUEBAS)+"ms");
	}
	private static int[] generarListaDesordenada(int numElementos) {
		int[] listaDesordenada=new int[numElementos];
		for (int i=0;i<numElementos;i++) {
			listaDesordenada[i]=(int) Math.floor((Math.random())*100);
		}
		return listaDesordenada;
	}
	private static int[] bubbleSort(int[] listaElementos) {
		int temp=0;
		int n=listaElementos.length;
		boolean ordenado=true;
		for(int i=0;i<n;i++) {
			ordenado=true;
			for (int j=1;j<(n-i);j++) {
				if(listaElementos[j-1]>listaElementos[j]) {
					// Si el que está arriba es más grande que el que está abajo, se intercambian
					temp=listaElementos[j-1];
					listaElementos[j-1]=listaElementos[j];
					listaElementos[j]=temp;
					ordenado=false;
				}
			}
			if (ordenado) return listaElementos;
		}
		return listaElementos;
	}

	private static int[] quickSort(int[] listaElementos) {
		int izq=0;
		int der=listaElementos.length-1;
		return quickRecursive(listaElementos, izq, der);
	}

	private static int[] quickRecursive(int[] listaElementos, int izq, int der) {
		int pivote=listaElementos[izq]; // Pivote = 1º elemento
		int i=izq; // i se mueve de izq a der
		int j=der; // j se mueve de der a izq
		int aux; // variable de intercambio
		while(i<j) { // Mientras que no se cruce
			while(listaElementos[i]<=pivote && i<j) i++; // Buscamos el elemento mayor que el pivote
			while(listaElementos[j]>pivote) j--; // Buscamos el elemento menor que el pivote
			if(i<j) {
				aux=listaElementos[i]; // Si no se cruzan los intercambiamos
				listaElementos[i]=listaElementos[j];
				listaElementos[j]=aux;
			}
		}
		listaElementos[izq]=listaElementos[j]; // Ponemos el pivote en su lugar
		listaElementos[j]=pivote;
		if(izq<j-1) listaElementos=quickRecursive(listaElementos, izq, j-1); // Ordenamos subarray izquierdo
		if(j+1<der) listaElementos=quickRecursive(listaElementos, j+1, der); // Ordenamos subarray derecho
		return listaElementos;
	}
}