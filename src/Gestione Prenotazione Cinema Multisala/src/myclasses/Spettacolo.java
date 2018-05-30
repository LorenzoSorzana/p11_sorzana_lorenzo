package myclasses;
import java.util.Calendar;
/**
 * 
 *Classe Spettacolo
 * <p>
 * Classe che rappresenta gli Spettacoli di un {@link Cinema}
 * 
 * @version 1.0
 * @author Sorzana
 *
 */
public class Spettacolo {
	
	private Cinema cinema;
	private int cinemaRoom;
	private Film film;
	private float ticketPrice;		
	private int remainingSeats;		
	private boolean[][] occupiedSeats;	
	private Calendar dateAndHour;

	/**
	 * Costruttore di Spettacolo: istanzia un nuovo oggetto Spettacolo, impostando i parametri
	 * 
	 * @param cinema
	 * 		cinema che ospita lo spettacolo
	 * @param cinemaRoom
	 * 		numero idenitficativo della sala del cinema
	 * @param film
	 * 		film dello spettacolo
	 * @param ticketPrice
	 * 		prezzo di un singolo biglietto
	 * @param dateAndHour
	 * 		data e ora dello spettacolo
	 */
	public Spettacolo(Cinema cinema, int cinemaRoom, Film film, float ticketPrice, Calendar dateAndHour) {
		
		Sala tmpCinemaRoom = cinema.getCinemaRooms().get(cinemaRoom); 
		
		this.cinema = cinema;
		this.cinemaRoom = cinemaRoom;
		this.film = film;
		this.ticketPrice = ticketPrice;
		this.remainingSeats = tmpCinemaRoom.getRows()*tmpCinemaRoom.getColumns();
		this.occupiedSeats = new boolean[tmpCinemaRoom.getRows()][tmpCinemaRoom.getColumns()];
		for(int i=0; i<tmpCinemaRoom.getRows(); i++)
			for(int j=0; j<tmpCinemaRoom.getColumns(); j++)
				occupiedSeats[i][j] = false;			
	
		this.dateAndHour = dateAndHour;
	}

	/**
	 * Metodo utilizzato per restituire il cinema che ospita lo spettacolo
	 * 
	 * @return cinema
	 */
	public Cinema getCinema() {
		return cinema;
	}
	
	/**
	 * Metodo utilizzato per restituire il numero identificativo della Sala
	 * 
	 * @return cinemaRoom
	 */
	public int getCinemaRoom() {
		return cinemaRoom;
	}
	
	/**
	 * Metodo utilizzato per restituire il film dello Spettacolo
	 * 
	 * @return filmID
 	 */
	public Film getFilm() {
		return film;
	}
	
	/**
	 * Metodo utilizzato per restituire il prezzo del singolo biglietto per lo Spettacolo
	 * 
	 * @return ticketPrice
	 */
	public float getTicketPrice() {
		return ticketPrice;
	}
	
	/**
	 * Metodo utilizzato per restituire il numero di posti rimanenti non prenotati per lo Spettacolo
	 * 
	 * @return remainingSeats
	 */
	public int getRemainingSeats() {
		return remainingSeats;
	}
	/**
	 * Metodo utilizzato per impostare il numero di posti non prenotati
	 * 
	 * @param remainingSeats
	 * 		nuemero posti liberi
	 */
	public void setRemainingSeats(int remainingSeats) {
		this.remainingSeats = remainingSeats;
	}
	/**
	 * Metodo utilizzato per restituire la tabella  che tiene conto di quali posti sono prenotati
	 * 
	 * @return occupiedSeats
	 */
	public boolean[][] getOccupiedSeats() {
		return occupiedSeats;
	}
	
	/**
	 * Metodo utilizzato per restituire la data e l'ora dello Spettacolo
	 * 
	 * @return dateAndHour
	 */
	public Calendar getDateAndHour() {
		return dateAndHour;
	}
	
	
	
	/**
	 * Metodo che permette di visualizzare a video i dati relativi a uno Spettacolo.
	 * Nello specifico: il nome del cinema che ospita lo spettacolo, la data e ora,
	 * il titolo del film dello spettacolo, il prezzo del biglietto singolo
	 * posti rimanenti che possono essere prenotati 
	 */
	public void showShowInfo() {
		
		System.out.println("Cinema : " + cinema.getName() + " - Sala : " + cinemaRoom + "\n" +
				"Data e Ora : " + dateAndHour.get(Calendar.DAY_OF_MONTH) + "/" +
								(dateAndHour.get(Calendar.MONTH) + 1) + "/" + 
								dateAndHour.get(Calendar.YEAR) + " - " +
								dateAndHour.get(Calendar.HOUR_OF_DAY) + ":" +
								dateAndHour.get(Calendar.MINUTE) + "\n" +
				"Film : " + film.getTitle() + "\n" + 
				"Prezzo Biglietto : " + ticketPrice + "\n" +
				"Posti Rimanenti : " + remainingSeats);
		
	}
		
	
}
