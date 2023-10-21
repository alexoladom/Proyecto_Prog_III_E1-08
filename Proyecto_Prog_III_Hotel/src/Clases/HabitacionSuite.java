package Clases;

public class HabitacionSuite extends Habitacion{

	protected static final int numCamas = 4;
	protected final static double precioPorNoche = 100;
	
	
	public HabitacionSuite(boolean ocupado, int planta, int numero) {
		super(ocupado,planta,numero);
	}
	
	public HabitacionSuite() {
		super();
	}

	public static int getNumcamas() {
		return numCamas;
	}

	public static double getPreciopornoche() {
		return precioPorNoche;
	}
	
	@Override
	public String toString() {
		return String.format("HabitacionSuite %s, ocupado=%s, planta=%s, %s]", id, ocupado, planta, numero);
	}
}