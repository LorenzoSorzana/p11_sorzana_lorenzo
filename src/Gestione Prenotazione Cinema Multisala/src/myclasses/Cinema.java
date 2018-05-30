package myclasses;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 * 
 * Classe Cinema
 * <p>
 * Classe che rappresenta il Cinema. Caratterizzata da: ID, nome del cinema, indirizzo, citta'
 * voto medio delle recensioni, prezzo base del biglietto, lista degli spettacoli, lista delle {@link Sala}
 * Ogni Cinema ha inoltre una lista di recensioni che puo' essere riempita dai vari utenti del sistema.
 *
 * @version 1.0
 * @author Sorzana
 *
 */
public class Cinema {

	public static int progressiveID = 0;

	private int cinemaID;
	private String name;
	private String address;
	private String city;
	private float rating;
	private float baseTicketPrice; 
	private List<Spettacolo> showsList;
	private List<Recensione> reviews;
	private HashMap<Integer, Sala> cinemaRooms;

	/**
	 * Costruttore di Cinema: istanzia un nuovo oggetto Cinema, impostando i parametri
	 *   
	 * @param name
	 * 		nome del cinema 
	 * @param address
	 * 		indirizzo del cinema
	 * @param city
	 * 		citt� in cui si trova il cinema
	 * @param baseTicketPrice
	 * 		prezzo base del biglietto
	 */
	public Cinema(String name, String address, String city, float baseTicketPrice) {	
		this.cinemaID = ++progressiveID;
		this.name = name;
		this.address = address;
		this.city = city;
		this.rating = (float) 0;
		this.baseTicketPrice = baseTicketPrice;
		this.showsList = new ArrayList<Spettacolo>();
		this.reviews = new ArrayList<Recensione>();
		this.cinemaRooms = new HashMap<Integer, Sala>();
	}
	
	/**
	 * Metodo utilizzato per restituire il nome del Cinema
	 *   
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Metodo utilizzato per restituire l'indirizzo del Cinema
	 *  
	 * @return address
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * Metodo utilizzato per restituire la citt� in cui si trova il Cinema
	 * 
	 * @return city
	 */
	public String getCity() {
		return city;
	}
	
	/**
	 * Metodo utilizzato per restituire la lista degli spettacoli di un Cinema
	 *  
	 * @return showsList
	 */
	public List<Spettacolo> getShowsList() {
		return showsList;
	}
	
	/**
	 * Metodo utilizzato per restituire la lista delle Recensioni di un Cinema
	 * 
	 * @return reviews
	 */
	public List<Recensione> getReviews() {
		return reviews;
	}
	
	/**
	 * Metodo utilizzato per restituire la lista delle Sale di un Cinema
	 *  
	 * @return cinemaRooms
	 */
	public HashMap<Integer, Sala> getCinemaRooms() {
		return cinemaRooms;
	}
	
	/**
	 * Metodo utilizzato per restituire il voto medio di un Cinema
	 *  
	 * @return rating
	 */
	public float getRating() {
		return rating;
	}
	
	/**
	 * Metodo utilizzato per restituire l'ID di un Cinema
	 *  
	 * @return cinemaID
	 */
	public int getCinemaID() {
		return cinemaID;
	}
	
	/**
	 * Metodo utilizzato per restituire il prezzo base di un biglietto di un Cinema
	 *  
	 * @return baseTicketPrice
	 */
	public float getBaseTicketPrice() {
		return baseTicketPrice;
	}
	

	

	/**
	 * Metodo che permette di cercare la programmazione di un Cinema 
	 * in una certa data
	 * 
	 * @param date
	 * 		data in cui si vuole eseguire la ricerca
	 * @return toReturn
	 * 		lista degli spettacoli trovati nella ricerca
	 */
	public ArrayList<Spettacolo> getCinemaShowList(Calendar date){
			
		ArrayList<Spettacolo> toReturn = new ArrayList<Spettacolo>();

			for(Spettacolo show : this.showsList) 
				if(show.getDateAndHour().get(Calendar.DAY_OF_YEAR) == date.get(Calendar.DAY_OF_YEAR)
				&& show.getDateAndHour().get(Calendar.YEAR) == date.get(Calendar.YEAR))
					toReturn.add(show);
		
		System.out.println("Programmazione cinema : " + this.name +
				" nella data : " + date.get(Calendar.DAY_OF_MONTH) + "/" +
																(date.get(Calendar.MONTH) + 1)  + "/" + 
																date.get(Calendar.YEAR) + "\n");

		for(Spettacolo show: toReturn)
			show.showShowInfo();
		
		return toReturn;
	}

	/**
	 * Metodo che permette di visualizzare a video i dati relativi a una scheda cinema.
	 * Nello specifico: nome del cinema, indirizzo del cinema, citta del cinema, e programmazione 
	 * per la data corrente alla richiesta
	 * 
	 */
	public void showCinemaInfo() {

		System.out.println("Cinema : " + this.name + "\n" + 
							"Address : " + this.address + "\n" + 
							"City : " + this.city);
		
		for(Spettacolo show: this.getCinemaShowList(Calendar.getInstance()))
			show.showShowInfo();
		
	}
	 
	/**
	 * Metodo che permette di visualizzare a video la lista delle recensioni di un cinema
	 */
	public void showReviews() {

		if (this.reviews.isEmpty()) {
			System.out.println("Recensioni non presenti per questo cinema.");
			return;
		}
		
		for (Recensione review : this.reviews)
			review.showReviewInfo();
	}

	/**
	 * Metodo che permette di inserire una nuova recensione all'interno della lista delle 
	 * recensioni del cinema in questione.
 	 *	
	 * @param reviewer
	 * 		utente che ha scritto la recensione 
	 * @param vote
	 * 		voto dato dall'utente
	 * @param comment
	 * 		commento scritto dell'utente
	 * @return true se i dati sono corretti (recensione non null, voto compreso tra 1 e 5,
	 * 			 commento non vuoto e l'utente non abbia gi� recensito il cinema)
	 * 			false in caso contrario
	 */
	public boolean addReview(Utente reviewer, int vote, String comment) {
		
		if(reviewer == null ||  vote > 5 || vote < 0 || comment.equals("")) {
			return false;
		}
		
		//aggiungo la recensione 
		reviews.add(new Recensione(reviewer, vote, comment));
		
		float tmpRating = 0;
		
		//calocolo la media dei voti del cinema 
		for(Recensione review : reviews)					
			tmpRating = tmpRating + review.getRate();				
	
			rating = (tmpRating / reviews.size());
		
		return true;
	}
	
	/**
	 * Metodo che permette di cercare la programmazione di un film indicato nelle date presenti e future
	 * 
	 * @param filmID
	 * 		id univoco del film di cui si vuole ricercare la programmazione 
	 * @return toReturn
	 * 			lista degli spettacoli (programmazione) trovati 
	 */
	public ArrayList<Spettacolo> searchThisCinemaFilmShowList(Film film, Calendar beginResearchDate){
		
		ArrayList<Spettacolo> toReturn = new ArrayList<Spettacolo>();
		
		for(Spettacolo show : showsList)
			if(show.getFilm() == film && show.getDateAndHour().after(beginResearchDate))
				toReturn.add(show);
		
		return toReturn;
	}
	
	
}
