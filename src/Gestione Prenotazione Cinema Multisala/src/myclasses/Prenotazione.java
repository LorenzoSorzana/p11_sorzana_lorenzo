package myclasses;
import java.util.Calendar;
import java.util.List;

/**
 * 
 *Classe Prenotazione
 * <p>
 * Classe che rappresenta le Prenotazioni di uno {@link Spettacolo}
 * 
 * @version 1.0
 * @author Sorzana
 *
 */
public class Prenotazione {

	public static int progressiveID = 0;
	
	private int reservationID;
	private Spettacolo show;
	private int numberOfTickets;
	private List<String> seats;
	private float price;
	private boolean paid;
	
	/**
	 * Costruttore di Prenotazione: istanzia un nuovo oggetto Prenotazione, impostando i parametri
	 *  
	 * @param show
	 * 		spettacolo legato alla prenotazione
	 * @param seats
	 * 		lista dei posti a sedere
	 */
	public Prenotazione(Spettacolo show, List<String> seats) {
		this.reservationID = ++progressiveID;
		this.show = show;
		this.numberOfTickets = seats.size();
		this.seats = seats;
		this.price = numberOfTickets * show.getTicketPrice();
		this.paid = false;
	}
	
	/**
	 * Metodo utilizzato per restituire l'ID della prenotazione 
	 * 
	 * @return reservationID
	 */
	public int getReservationId() {
		return reservationID;
	}
	
	/**
	 * Metodo utilizzato per restituire lo spettacolo legato alla prenotazione 
	 * 
	 * @return show
	 */
	public Spettacolo getShow() {
		return show;
	}
	
	/**
	 * Metodo utilizzato per restituire il numero di biglietti prenotati
	 * 
	 * @return numberOfTickets
	 */
	public int getNumberOfTickets() {
		return numberOfTickets;
	}
	
	/**
	 * Metodo utilizzato per restituire la lista dei posti a sedere 
	 * 
	 * @return seats
	 */
	public List<String> getSeats() {
		return seats;
	}
	
	/**
	 * Metodo utilizzato per restituire il prezzo totale della prenotazione 
	 * 
	 * @return price
	 */
	public float getPrice() {
		return price;
	}
	
	/**
	 * Metodo utilizzato per restituire se una prenotazione � pagata o meno
	 * 
	 * @return paid
	 */
	public boolean isPaid() {
		return paid;
	}
	/**
	 * Metodo utilizzato per impostare se una prenotazione � pagata o meno
	 * 
	 * @param paid
	 * 		true se pagata, false altrimenti
	 */
	public void setPaid(boolean paid) {
		this.paid = paid;
	}
	 
	
	/**
	 * Metodo che permette di visualizzare a video i dati relativi a una Prenotazione.
	 * Nello specifico: id, titolo del film, data e ora, lista dei posti, e prezzo totale
	 * se � pagata o meno, il cinema che ospita lo spettacolo, e la sala del cinema
	 */
	public void showReservationInfo() {
		
		String bookedSeats = "";

		for(int i=0; i<this.seats.size(); i++) {
			bookedSeats = bookedSeats + this.seats.get(i);
			if(i != this.seats.size()-1)
				bookedSeats = bookedSeats + " - ";
		}
		
		System.out.println("ID : " + this.reservationID + "\n" +
				"Show : " + this.show.getFilm().getTitle() + "\n" +
				"Date and Hour : " + this.show.getDateAndHour().get(Calendar.DAY_OF_MONTH) + "/" +
									this.show.getDateAndHour().get(Calendar.MONTH) + "/" + 
									this.show.getDateAndHour().get(Calendar.YEAR) + " - " +
									this.show.getDateAndHour().get(Calendar.HOUR_OF_DAY) + ":" +
									this.show.getDateAndHour().get(Calendar.MINUTE) + "\n" +
				"Seats : " + bookedSeats + "\n" +
				"Total Price : " + this.price + "\n" +
				"Paid : " + this.paid + "\n" +
				"Cinema : " + this.show.getCinema().getName() + "\n" +
				"Room : " + this.show.getCinemaRoom());
	}
	
}
