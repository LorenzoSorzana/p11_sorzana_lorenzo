package myclasses;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
/**
 * 
 *Classe Sistema
 * <p>
 * Classe che rappresenta il Sistema. Tendendo traccia di Utenti, Film e Cinema
 * 
 * @version 1.0
 * @author Sorzana
 *
 */
public class Sistema {

	private static final int BY_TITLE = 0;
	private static final int BY_FILM_DIRECTOR = 1;
	private static final int BY_ACTOR = 2;
	private static final int BY_GENRE = 3;
	private static final int BY_TAG = 4;
	
	//parametro per le ricerche totali di cinema e film
	private static final int BY_ALL = 5;

	private static final int BY_NAME = 10;
	private static final int BY_CITY = 11; 
	
	private static final int CINEMA = 20;
	private static final int FILM = 21;
	
	
	private HashMap<Integer, Film> filmTable;
	private HashMap<Integer, Cinema> cinemaTable;
	private HashMap<String, Utente> userTable;
	
	/**
	 * Costruttore di Sistema, istanzia un nuovo oggetto Sistema, settando le table
	 * per Utenti, Film e Cinema
	 */
	public Sistema(){
		this.filmTable = new HashMap<Integer, Film>();
		this.cinemaTable = new HashMap<Integer, Cinema>();
		this.userTable = new HashMap<String, Utente>();		
	}
	
	/**
	 * Metodo utilizzato per restituire la tabella dei Cinema presenti nel Circuito Cinema
	 * 
	 * @return filmTable
	 */
	public HashMap<Integer, Film> getFilmTable() {
		return filmTable;
	}

	/**
	 * Metodo utilizzato per restituire la tabella dei Cinema presenti nel Circuito Cinema
	 * 
	 * @return cinemaTable
	 */
	public HashMap<Integer, Cinema> getCinemaTable() {
		return cinemaTable;
	}

	/**
	 * Metodo utilizzato per restituire la tabella degli Utenti presenti nel Circuito Cinema
	 *
	 * @return userTable
	 */
	public HashMap<String, Utente> getUserTable() {
		return userTable; 
	}

	/**
	 * Metodo utilizzato per effettuare il login
	 * 
	 * @param username
	 * 		username dell'Utente che intende autenticarsi
	 * @param password
	 * 		password dell'Utente che intende autenticarsi
	 * @return
	 * 		true se username e password sono corrette, false altrimenti
	 */
	public boolean login(String username, String password){
		
		if(!userTable.containsKey(username))
			return false; 
		
		Utente user = userTable.get(username);
		
		if(user.isLoggedIn()) {
			return false;
		}
		
		if(password.equals(user.getPassword()))
			user.setLoggedIn(true);
	
		
		return user.isLoggedIn();
	}
	
	/**
	 * Metodo utilizzato per effettuare il logout
	 * 
	 * @param username
	 * 		username dell'Utente che vuole deautenticarsi
	 * @return
	 * 		true se la deautenticazione avviene con successo
	 */
	public boolean logout(String username){
		 
		//sempre presente, nello UC10 precondizione ï¿½ essere autenticato
		Utente user = userTable.get(username);

		user.setLoggedIn(false);
		
		return true;
	}
	
	/**
	 * Metodo che permette di ricercare un Film per Parametro Ricerca Film
	 * (puÃ² essere il Titolo, parte del titolo, regista, attori, genere o tag)
	 * oppure ricercare tutti i Film del Circuito Cinema
	 * 
	 * @param parameter
	 * 		parametro di ricerca 
	 * @param filmTypeResearch
	 * 		tipo di ricerca che si vuole effettuare tra le tipologie disponibili
	 * @return toReturn
	 * 		lista dei film che corrispondono alla ricerca effettuata
	 */		
	public ArrayList<Film> searchFilm(String parameter, int filmTypeResearch) {
		
		ArrayList<Film> toReturn = new ArrayList<Film>();
		
		ArrayList<Film> filmList = new ArrayList<Film>(filmTable.values());
		
		for(Film f : filmList) {
			
			if(filmTypeResearch == BY_TITLE) {
				if(f.getTitle().equals(parameter) || f.getTitle().contains(parameter))
					toReturn.add(f);
			}else if(filmTypeResearch == BY_FILM_DIRECTOR) {
				if(f.getFilmDirector().equals(parameter))
					toReturn.add(f);
			}else if(filmTypeResearch == BY_ACTOR) {
				for(String actor : f.getMainActors())
					if(actor.equals(parameter)) {
						toReturn.add(f);
						break;
					}
			}else if(filmTypeResearch == BY_GENRE) {
				if(f.getGenre().equals(parameter))
					toReturn.add(f);
			}else if(filmTypeResearch == BY_TAG) {
				for(String tag : f.getTags()) 
					if(tag.equals(parameter)){
						toReturn.add(f);
						break;
					}
			}else if(filmTypeResearch == BY_ALL) {
				toReturn = filmList;
			}
			
		}
		
		return toReturn;
	}
	
	/**
	 * Metodo che permette di ricercare un Cinema per Parametro Ricerca Cinema
	 * (puÃ² essere il nome , parte del nome o cittÃ )
	 * oppure ricercare tutti i Cinema del Circuito Cinema
	 * 
	 * @param parameter
	 * 		parametro di ricerca
	 * @param cinemaTypeResearch
	 * 		tipo di ricerca che si vuole effettuare tra le tipologie disponibili
	 * @return toReturn
	 * 		lista dei film che corrispondono alla ricerca effettuata
	 */
	public ArrayList<Cinema> searchCinema(String parameter, int cinemaTypeResearch){
		
		ArrayList<Cinema> cinemaList = new ArrayList<Cinema>(cinemaTable.values());
		
		ArrayList<Cinema> toReturn = new ArrayList<Cinema>();
		
		if(cinemaTypeResearch == BY_NAME) {
			for(Cinema cinema : cinemaList)
				if(cinema.getName().equals(parameter) || cinema.getName().contains(parameter))
					toReturn.add(cinema);
		}else if(cinemaTypeResearch == BY_CITY){
			for(Cinema cinema : cinemaList)
				if(cinema.getCity().equals(parameter))
					toReturn.add(cinema);
		}else if(cinemaTypeResearch == BY_ALL) {
			toReturn = cinemaList;
		}
		
		return toReturn;
	}
	
	/**
	 * Metodo che permette di scrivere una recensione per un Film o Cinema
	 * 
	 * @param reviewer
	 * 		Utente che scrive la recensione
	 * @param toReview
	 * 		Film o Cinema da recensire
	 * @param vote
	 * 		voto dato dall'Utente al Film o Cinema
	 * @param comment
	 * 		commento scritto dall'Utente 
	 * @param objectTypeReview
	 * 		tipo di oggetto che si vuole recensire tra Film o Cinema
	 * @return
	 * 		true se la scrittura della recensione è andata a buon fine
	 * 		false se c'è stato un errore
	 */
	public boolean writeReview(Utente reviewer, Object toReview, int vote, String comment, int objectTypeReview) {
			
			boolean writeReviewResult = false;
			
			switch (objectTypeReview) {
			
			case CINEMA:
				Cinema cinemaToReview = (Cinema) toReview;
				
				//controllo l'utente non abbia giï¿½ recensito
				for(Recensione review : cinemaToReview.getReviews() )
					if(review.getUser().getUsername().equals(reviewer.getUsername()))
						return false;
				
				writeReviewResult = cinemaToReview.addReview(reviewer, vote, comment);
				break;
			
			case FILM:
				Film filmToReview = (Film) toReview;
				
				//controllo l'utente non abbia giï¿½ recensito
				for(Recensione review : filmToReview.getReviews())
					if(review.getUser().getName().equals(reviewer.getName()))
						return false;
				
				writeReviewResult = filmToReview.addReview(reviewer, vote, comment);
				break;
				
			}
		
			return writeReviewResult;
			
		}
	
	/**
	 * Metodo che permette di effettuare la prenotazione di uno Spettacolo
	 * 
	 * @param user
	 * 		Utente che intende effettuare la prenotazione
	 * @param show
	 * 		Spettacolo che si vuole prenotare
	 * @param numberOfTickets
	 * 		numero di biglietti che si vuole prenotare
	 * @return
	 * 		true se la prenotazione è andata a buon fine
	 * 		false altrimenti
	 */
	public boolean makeReservation(Utente user, Spettacolo show, int numberOfTickets){
			
		if(show.getRemainingSeats() < numberOfTickets)
			return false;
		
		if(numberOfTickets < 1) 
			return false;
		
		ArrayList<String> reservationSeats = new ArrayList<String>();
		
		Sala tmpCinemaRoom = show.getCinema().getCinemaRooms().get(show.getCinemaRoom());
			
		//struttura della posizione dei sedili [rows][columns]
		for(int i=0; i<tmpCinemaRoom.getRows(); i++){
			//ogni riga e un array di posti lungo quanto il numero delle colonne 
			for(int j=0; j<tmpCinemaRoom.getColumns() && j<numberOfTickets ; j++) {
				if(!show.getOccupiedSeats()[i][j]){
					show.getOccupiedSeats()[i][j] = true;
					reservationSeats.add((char) (90-i) + "-" + (j+1));
				}
				if(reservationSeats.size() == numberOfTickets)
					break;				
			}
		}
		
		user.getReservations().add(new Prenotazione(show, reservationSeats));
		show.setRemainingSeats(show.getRemainingSeats() - numberOfTickets);
		return true;			
		
	}

	/**
	 * Metodo che permette di pagare una Prenotazione 
	 * 
	 * @param buyer
	 * 		Utente che intende pagare una prenotazione
	 * @param reservation
	 * 		Prenotazione da pagare
	 * @param paymentMethod
	 * 		metodo di pagamento scelto
	 * @return
	 * 		true se il pagamento va a buon fine
	 * 		false altrimenti
	 */
	public boolean buyReservation(Utente buyer, Prenotazione reservation, int paymentMethod) {
		
		Pagamento receipt = new Pagamento(paymentMethod, reservation.getPrice());
		
		if(receipt.payReservation()) {
			reservation.setPaid(true);
			buyer.getPaymets().add(receipt);
			return true;
		}
		
		return false;
	}
	 
	/**
	 * Metodo per cancellare una Prenotazione 
	 * 
	 * @param user
	 * 		Utente che vuole cancellare una Prenotazione
	 * 
	 * @param reservation
	 * 		Prenotazione che si vuole cancellare
	 * @return
	 * 		true se la cancellazione avviene con successo
	 * 		false altrimenti
	 */
	public boolean deleteReservation(Utente user, Prenotazione reservation){
		
		//libero i posti precedentemente occupati
		for(String seatToBeReleased : reservation.getSeats()){
			int row = 0;
			int	column = 0;
						
			row = 90 - (int) seatToBeReleased.charAt(0);
			column = Integer.parseInt(seatToBeReleased.substring(2)) - 1;
			
			reservation.getShow().getOccupiedSeats()[row][column] = false;
			
			reservation.getShow().setRemainingSeats(reservation.getShow().getRemainingSeats() + reservation.getSeats().size());
		}
		return true;
	}

	/**
	 * Metodo per ricercare gli Spettacoli di un cinema da una certa data in poi 
	 * per un certo Film
	 * 
	 * @param film
	 * 		Film di cui si vogliono cercare gli Spettacoli
	 * @param beginResearchDate
	 * 		data dalla quale in poi si vogliono cercare gli Spettacoli del Film
	 * @return showsToReturn
	 * 		Lista degli Spettacoli del Film indicato dalla data indicata in poi
	 * 	
	 */
	public ArrayList<Spettacolo> searchFilmShowList(Film film, Calendar beginResearchDate){
		ArrayList<Cinema> cinemaList = new ArrayList<Cinema>(cinemaTable.values());
		
		ArrayList<Spettacolo> showsToReturn = new ArrayList<Spettacolo>();

		//int filmID = film.getFilmID();
		
		for(Cinema cinema : cinemaList)
			showsToReturn.addAll(cinema.searchThisCinemaFilmShowList(film, beginResearchDate));
		
		return showsToReturn;
	}

	//funzione per visualizzare la programmazioene di un cinema
	/**
	 * Metodo che restituisce la programmazione di un Cinema in una certa data
	 * 
	 * @param cinema
	 * 		Cinema del quale si vuole ricercare la programmazione 
	 * @param date
	 * 		data in cui si vuole ricercare la programmazione 
	 * @return 
	 * 		lista degli spettacoli del cinema nella data indicata
	 */
	public ArrayList<Spettacolo> getCinemaShowList(Cinema cinema, Calendar date){
		return cinema.getCinemaShowList(date);
	}

	/**
	 * Metodo che ritorna gli Spettacoli scontati di tutto il Circuito Cinema
	 * 
	 * @return
	 * 		lista degli spettacoli scontati
	 */
	public ArrayList<Spettacolo> getDiscountShowList(){
		
		ArrayList<Spettacolo> discountShows = new ArrayList<Spettacolo>();
	
		ArrayList<Cinema> cinemaList = new ArrayList<Cinema>(cinemaTable.values());
		for(Cinema tmpCinema : cinemaList) {
			ArrayList<Spettacolo> tmpCinemaShowList = (ArrayList<Spettacolo>) tmpCinema.getShowsList();
			for(Spettacolo tmpShow : tmpCinemaShowList)
				if(tmpShow.getTicketPrice() < tmpCinema.getBaseTicketPrice())
					discountShows.add(tmpShow);	

		}
			
		System.out.println("Lista di Sconti : ");
		for(Spettacolo show: discountShows)
			show.showShowInfo();
		
		return discountShows;
	}
	
	/**
	 * Metodo che ritorna gli Spettacoli scontati di un Cinema indicato
	 * 
	 * @param cinema
	 * 		Cinema di cui si vogliono visualizzare gli sconti 
	 * @return
	 * 		lista degli spettacoli scontati
	 */
	public ArrayList<Spettacolo> getDiscountShowList(Cinema cinema){
		
		ArrayList<Spettacolo> discountShows = new ArrayList<Spettacolo>();
		
		Cinema tmpCinema = cinemaTable.get(cinema.getCinemaID());
		ArrayList<Spettacolo> tmpCinemaShowList = (ArrayList<Spettacolo>) tmpCinema.getShowsList();
		for(Spettacolo tmpShow : tmpCinemaShowList)
			if(tmpShow.getTicketPrice() < tmpCinema.getBaseTicketPrice())
				discountShows.add(tmpShow);		
		
		System.out.println("\nLista di Sconti : ");
		for(Spettacolo show: discountShows)
			show.showShowInfo();
		
		return discountShows;
	}
	
	
}
