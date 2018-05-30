package myclasses;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Classe Cinema
 * <p>
 * Classe che rappresenta il Cinema. Caratterizzata da: ID, titolo, media dei voti, regista,
 * lista degli attori principali, genere, trama, durata, i tag (parole chiave utilizzati nella fase di ricerca).
 * Ogni Film ha inoltre una lista di recensioni che puo' essere riempita dai vari utenti del sistema.
 *
 * @version 1.0
 * @author Sorzana
 *
 */
public class Film {

	public static int progressiveID = 0;
	
	private int filmID;
	private String title;
	private float rating;	
	private String filmDirector;
	private List<String> mainActors;
	private String genre;
	private String plot;
	private int duration;
	private List<String> tags;						
	private List<Recensione> reviews;		
	
	/**
	 * Costruttore di Film: istanzia un nuovo oggetto Film, impostando i parametri
	 * 
	 * @param title
	 * 		titolo del film
	 * @param filmDirector
	 * 		regista del film
	 * @param mainActors
	 * 		lista degli attori principali 
	 * @param genre
	 * 		genere del film 
	 * @param plot
	 * 		trama
	 * @param duration
	 * 		durata
	 * @param tags
	 * 		parole chiave per la ricerca
	 */
	public Film(String title, String filmDirector, List<String> mainActors, String genre, String plot,
			int duration, List<String> tags) {
		
		this.filmID = ++progressiveID;
		this.title = title;
		this.rating = (float) 0;
		this.filmDirector = filmDirector;
		this.mainActors = mainActors;
		this.genre = genre;
		this.plot = plot;
		this.duration = duration;
		this.tags = tags;
		this.reviews = new ArrayList<Recensione>();
	}

	/**
	 * Metodo utilizzato per restituire l'ID del film
	 * 
	 * @return filmID
	 */
	public int getFilmID() {
		return filmID;
	}
	
	/**
	 * Metodo utilizzato per restituire il titolo del film
	 * 
	 * @return film
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Metodo utilizzato per restituire il voto medio delle recensioni del film
	 * 
	 * @return rating
	 */
	public float getRating() {
		return rating;
	}
	
	/**
	 * Metodo utilizzato per restituire il nome del regista del film
	 * 
	 * @return filmDirector
	 */
	public String getFilmDirector() {
		return filmDirector;
	}
	
	/**
	 * Metodo utilizzato per restituire la lista degli attori principali del film 
	 * 
	 * @return mainActors
	 */
	public List<String> getMainActors() {
		return mainActors;
	}
	
	/**
	 * Metodo utilizzato per restituire il genere del film 
	 * 
	 * @return genre
	 */
	public String getGenre() {
		return genre;
	}
	
	/**
	 * Metodo utilizzato per restituire la trama del film
	 * 
	 * @return plot
	 */
	public String getPlot() {
		return plot;
	}
	
	/**
	 * Metodo utilizzato per restituire la durata del film
	 * 
	 * @return duration
	 */
	public int getDuration() {
		return duration;
	}
	
	/**
	 * Metodo utilizzato per restituire le parole chiave per la ricerca di un film 
	 * 
	 * @return tags
	 */
	public List<String> getTags() {
		return tags;
	}
	
	/**
	 * Metodo utilizzato per restituire le recensioni che riguardano il film in questione
	 * 
	 * @return reviews
	 */
	public List<Recensione> getReviews() {
		return reviews;
	}
	
	
	
	/**
	 * Metodo che permette di visualizzare a video i dati relativi a una scheda film.
	 * Nello specifico: titolo, regista, genere, voto medio recensioni, attori principali 
	 * durata e trama
	 */
	public void showFilmInfo(){
		
		String actors = "";
		for(int i=0; i<this.mainActors.size(); i++) {
			actors = actors + this.mainActors.get(i);
			if(i != this.mainActors.size()-1)
				actors = actors + ", ";
		}
		   
		System.out.println("Titolo : " + this.title + "\n" +
				"Regista : " + this.filmDirector + "\n" +
				"Genere : " + this.genre + "\n" +
				"Rating : " + this.rating + "\n" +
				"Attori Principali : " + (String) actors + "\n" +
				"Durata : " + this.duration + " min" + "\n" +
				"Trama : " + this.plot + "\n");
		
	}
	
	/**
	 * Metodo che permette di visualizzare a video la lista delle recensioni di un film
	 */
	public void showReviews(){	
		
		if(this.reviews.isEmpty()) {
			System.out.println("Recensioni non presenti per questo film.");
			return;
		}
		
		for(Recensione review : this.reviews)
			review.showReviewInfo();
	}
	 
	/**
	 * Metodo che permette di inserire una nuova recensione all'interno della lista delle 
	 * recensioni del film in questione.
	 *  
	 * @param reviewer
	 * 		utente che ha scritto la recensione 
	 * @param vote
	 * 		voto dato dall'utente
	 * @param comment
	 * 		commento scritto dell'utente
	 * @return true se i dati sono corretti (recensione non null, voto compreso tra 1 e 5,
	 * 			 commento non vuoto e l'utente non abbia gi� recensito il film)
	 * 			false in caso contrario
	 */
	public boolean addReview(Utente reviewer, int vote, String comment) {
		if(reviewer == null ||  vote > 5 || vote < 0 || comment.equals("")) {
			return false;
		}
		
		//aggiungo la recensione 
		reviews.add(new Recensione(reviewer, vote, comment));
		
		float tmpRating = 0;
		
		//calocolo la media dei voti del film 
		for(Recensione review : reviews)					
			tmpRating = tmpRating + review.getRate();				
	
			rating = (tmpRating / reviews.size());
		
		return true;
	}
	
}
