package LigaInfantil;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Crear lista de equipos
		int edad = (int) Math.floor(Math.random()*15)+4;
		int numeroEquipos = (int) Math.floor(Math.random()*19)+4;
		Equipo[] equipos = crearListaEquipos(numeroEquipos, edad);
		
		System.out.println("Número de equipos: "+numeroEquipos);
		for( Equipo e:equipos ) {
			System.out.println(e.getNombre());
		}
		
	}
	
	private static Jugador[] crearListaJugadores(int numeroJugadores, int edad, Equipo equipo) {
		String[] nombres = { "Pepe", "Juan", "María", "Melody", "Cayetano", "Christian", "Johnny", "Ibrahim",
				"Muhammad", "Cho Hej", "Robertinho", "Alicinha", "Paulo Anton", "Alexander", "Etham", "Joel", "Martina",
				"Jenny", "Jessi", "Tayra" };
		
		String[] apellidos = { "Gomez", "Guerrero", "Cardenas", "Cardiel", "Cardona", "Cardoso", "Cariaga", "Carillo",
				"Carrión", "Castillo", "Castorena", "Castro", "Grande", "Grangenal", "Grano", "Grasia", "Griego",
				"Grigalva" };
		
		String[] posiciones = {"Portero", "Defensa", "Centrocampista", "Delantero"};
		
		Jugador[] listaJugadores = new Jugador[numeroJugadores];
		
		for (int i=0;i<numeroJugadores;i++) {
			Jugador jug = new Jugador();
			//Creamos el nombre
			int numero = (int) Math.floor(Math.random()*nombres.length);
			jug.setNombre(nombres[numero]);
	
			//Creamos los apellidos.
			numero = (int) Math.floor(Math.random()*apellidos.length);
			String apellido1 = apellidos[numero];
			numero = (int) Math.floor(Math.random()*apellidos.length);
			String apellido2 = apellidos[numero];
			jug.setApellidos(apellido1+" "+apellido2);

			//Creamos una posición.
			numero = (int) Math.floor(Math.random()*posiciones.length);
			jug.setPosicion(posiciones[numero]);
			
			//Ponemos la edad que hemos definido arriba.
			jug.setEdad(edad);
			
			//Creamos el dorsal.
			jug.setDorsal(i+1);
			
			//Añadir equipo
			jug.setEquipo(equipo);
			
			listaJugadores[i]=jug;
		}
		return listaJugadores;
	}
	
	private static Equipo[] crearListaEquipos(int numeroEquipos, int edad) {
		String[] barrios = {"El Perchel", "La victoria", "El Rincón de la Victoria",
				"Huelin", "Cortijo Alto", "Ronda", "Campanillas",
				"La Curz de Humilladero", "El Soho", "El Puerto de la Torre",
				"Cártama", "Velez Málaga", "La Paz", "Torre de Benalgalbón",
				"Montilla", "El Ejido", "La Roca", "La Palmilla", "El Palo",
				"Los Asperones", "Carranque"};
		String[] mascotas = {"Los Limones", "Los Cangrejos", "Los Michis",
				"Los Asquerosos", "Los Carpinchos", "Los Satanases", "Los Diablitos",
				"Los Olvidones", "Los Amantes", "Los Chonis", "Los Cayetanos",
				"Los Perroflautas", "Los apestados", "Las Divinas", "Las Ingenieras",
				"Las Chunguitas", "Las Sirenitas", "Las Reguetonas"};
		
		Equipo[] equipos = new Equipo[numeroEquipos];
		// La edad debe ser siempre la misma
		
		for( int i=0 ; i<numeroEquipos ; i++) {
			Equipo equipo = new Equipo();
			
			//Nombre del equipo y del Club
			String nombreEquipo;
			
			int numero = (int) Math.floor(Math.random()*barrios.length);
			String barrio = barrios[numero];
			equipo.setClub(barrio+" F.C.");
			
			numero = (int) Math.floor(Math.random()*mascotas.length);
			String mascota = mascotas[numero];
			
			if(barrio.startsWith("El")) {
				barrio=barrio.substring(3);
				nombreEquipo=mascota+" del "+barrio;
			} else {
				nombreEquipo=mascota+" de "+barrio;
			}
			equipo.setNombre(nombreEquipo);
			
			//Hacer el entrenador
			/* Código original
			Entrenador entrenador = crearEntrenador(equipo);
			equipo.setEntrenador(entrenador);
			*/
			equipo.setEntrenador(crearEntrenador(equipo));
			
			//Crear e introducir jugadores
			int numeroJugadores = (int) Math.floor(Math.random()*8)+15;
			Jugador[] listaJugadores = crearListaJugadores(numeroJugadores, edad, equipo);
			equipo.setJugadores(listaJugadores);

			equipos[i] = equipo;			
		}
		
		return equipos;
	}
	
	
	private static Entrenador crearEntrenador(Equipo equipo) {
		String[] nombres = { "Pepe", "Juan", "María", "Melody", "Cayetano", "Christian", "Johnny", "Ibrahim",
				"Muhammad", "Cho Hej", "Robertinho", "Alicinha", "Paulo Anton", "Alexander", "Etham", "Joel", "Martina",
				"Jenny", "Jessi", "Tayra" };
		
		String[] apellidos = { "Gomez", "Guerrero", "Cardenas", "Cardiel", "Cardona", "Cardoso", "Cariaga", "Carillo",
				"Carrión", "Castillo", "Castorena", "Castro", "Grande", "Grangenal", "Grano", "Grasia", "Griego",
				"Grigalva" };
		
		Entrenador entrenador = new Entrenador();
		
		int numero = (int) Math.floor(Math.random()*nombres.length);
		String nombre = nombres[numero];
		
		numero = (int) Math.floor(Math.random()*apellidos.length);
		String apellido = apellidos[numero];
		
		//48 (rango) => 65 años tope - 18 años mínimos (ambos inclusive)
		int edad = (int) Math.floor(Math.random()*48)+18;
		
		int licencia = (int) Math.floor(Math.random()*900000)+100000;
		
		entrenador.setNombre(nombre);
		entrenador.setApellidos(apellido);
		entrenador.setEdad(edad);
		entrenador.setNumeroLicencia(licencia);
		
		return entrenador;

	}
	
	
}
