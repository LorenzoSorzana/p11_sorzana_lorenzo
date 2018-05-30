package myclasses;
/**
 * 
 *Classe Sala
 * <p>
 * Classe che rappresenta le Sale di un {@link Cinema}
 * 
 * @version 1.0
 * @author Sorzana
 *
 */
public class Sala {

	private int cinemaRoom;
	private int capacity;			
	private int rows;
	private int columns;

	/**
	 * Costruttore di Sala: istanzia un nuovo oggetto Sala, impostando i parametri
	 *  
	 * @param cinemaRoom
	 * 		numero identificativo della Sala
	 * @param rows
	 * 		numero di righe dei posti 
	 * @param columns
	 * 		numero di colonne dei posti
	 */
	public Sala(int cinemaRoom, int rows, int columns) {
		this.cinemaRoom = cinemaRoom;
		this.rows = rows;
		this.columns = columns;
		this.capacity = rows * columns;
	}

	/**
	 * Metodo utilizzato per restituire il numero identificativo della Sala
	 * 
	 * @return cienmaRoom
	 */
	public int getCinemaRoom() {
		return cinemaRoom;
	}

	/**
	 * Metodo utilizzato per restituire la capacit� totale della Sala
	 * 
	 * @return capacity
	 */
	public int getCapacity() {
		return capacity;
	}
	/**
	 * Metodo utilizzato per restituire il numero di righe di posti della Sala
	 * 
	 * @return rows
	 */
	public int getRows() {
		return rows;
	}
	/**
	 * Metodo utilizzato per restituire il numero di colonne di posti della Sala
	 * 
	 * @return columns
	 */
	public int getColumns() {
		return columns;
	}


}
