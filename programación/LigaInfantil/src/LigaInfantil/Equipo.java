package LigaInfantil;

public class Equipo {
	
	private String nombre;
	private String club;
	private String equipacion;
	private Entrenador entrenador;
	private Jugador[] jugadores;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getClub() {
		return club;
	}
	public void setClub(String club) {
		this.club = club;
	}
	public String getEquipacion() {
		return equipacion;
	}
	public void setEquipacion(String equipacion) {
		this.equipacion = equipacion;
	}
	public Entrenador getEntrenador() {
		return entrenador;
	}
	public void setEntrenador(Entrenador entrenador) {
		this.entrenador = entrenador;
	}
	public Jugador[] getJugadores() {
		return jugadores;
	}
	public void setJugadores(Jugador[] jugadores) {
		this.jugadores = jugadores;
	}
	public String toStringListaJugadores() {
		String listaJugadores="";
		for (Jugador j : jugadores) {
			listaJugadores += j;
		}
		return listaJugadores;

	}
	
	@Override
	public String toString() {
		return 	"Nombre: "+ nombre +", Club: "+ club +"\n"+
				"Entrenador: "+ entrenador.getNombre() +" "+ entrenador.getApellidos() +"\n"+
				"Jugadores:\n\n"+ toStringListaJugadores();
	}
}
