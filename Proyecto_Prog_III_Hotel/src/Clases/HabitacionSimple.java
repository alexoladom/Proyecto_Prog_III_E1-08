package Clases;

public class HabitacionSimple extends Habitacion{

	protected final static double precioPorNoche = 40;
	protected final static int numCamas = 1;
	
	
	
	public HabitacionSimple(boolean ocupado, int planta, int numero) {
		super(ocupado,planta,numero);
	}
	
	public HabitacionSimple() {
		super();
	}

	public static double getPreciopornoche() {
		return precioPorNoche;
	}

	public static int getNumcamas() {
		return numCamas;
	}
	
	
	@Override
	public String toString() {
		return String.format("HabitacionSimple %s, ocupado=%s, planta=%s, %s]", id, ocupado, planta, numero);
	}
}
