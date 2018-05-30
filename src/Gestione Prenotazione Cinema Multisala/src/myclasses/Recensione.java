package myclasses;
/**
 * 
 *Classe Recensione
 * <p>
 * Classe che rappresenta le recensioni che possono essere scritte dagli utenti
 *  riguardanti i fil o cinema presenti nel sistema.
 * 
 * @version 1.0
 * @author Sorzana
 *
 */
public class Recensione {

	private Utente user;
	private int vote;
	private String comment;
	
	/**
	 * Costruttore di Recensione: istanzia un nuovo oggetto Recensione, impostando i parametri
	 * 
	 * @param user
	 * 		utente che scrive la recensione 
	 * @param vote
	 * 		voto dell'utente
	 * @param comment
	 * 		breve descrizione con un commento
	 */
	public Recensione(Utente user, Integer vote, String comment) {
		this.user = user;
		this.vote = vote;
		this.comment = comment;
	}
	
	/**
	 * Metodo utilizzato per la restituzione dell'utente che ha recensito
	 * 
	 * @return user
	 */
	public Utente getUser() {
		return user;
	}
	
	/**
	 * Metodo utilizzato per la restituzione del voto assegnato dall'utente
	 * 
	 * @return vote
	 */
	public int getRate() {
		return vote;
	}
	
	/**
	 * Metodo utilizzato per la restituzione del commento scritto dall'utente
	 * 
	 * @return comment
	 */
	public String getComment() {
		return comment;
	}
	
	
	
	/**
	 * Metodo utilizzato per stampare i dati del singolo pagamento
	 * Mostrando: l'utente recensore, il voto e il commento
	 */
	public void showReviewInfo(){
		System.out.println("Utente : " + this.user.getUsername() + "\n" +
				"Voto : " + this.vote + "\n" +
				"Commento : " + this.comment); 
	}
}
