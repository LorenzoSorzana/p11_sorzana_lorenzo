package myclasses;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
/**
 * 
 *Classe Utente
 * <p>
 * Classe che rappresenta gli Utenti
 * 
 * @version 1.0
 * @author Sorzana
 *
 */
public class Utente {

	private String name;
	private String surname;
	private Calendar dateOfBirth;											
	private String email;
	private String username;
	private String password;
	private String creditCard;
	private boolean isLoggedIn;
	private List<Prenotazione> reservations;
	private List<Pagamento> paymets;
 	
	/**
	 * Costruttore di Utente: istanzia un nuovo oggetto Utente, impostando i parametri
	 * 
	 * @param name
	 * 		nome dell'utente
	 * @param surname
	 * 		cognome dell'utente
	 * @param dateOfBirth
	 * 		data di nascita dell'utente
	 * @param username
	 * 		username usato dall'utente
	 * @param password
	 * 		password usata dall'utente
	 * @param creditCard
	 * 		carta di credito dell'utente
	 */
	public Utente(String name, String surname, Calendar dateOfBirth, String email, String username, String password, String creditCard) {
		this.name = name;
		this.surname = surname;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.username = username;
		this.password = password;
		this.creditCard = creditCard;
		this.isLoggedIn = false;
		this.reservations = new ArrayList<Prenotazione>();
		this.paymets = new ArrayList<Pagamento>();
	}

	/**
	 * Metodo utilizzato per restituire il nome dell'utente
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Metodo utilizzato per restituire il cognome dell'utente
	 * 
	 * @return surname
	 */
	public String getSurname() {
		return surname;
	}
	
	/**
	 * Metodo utilizzato per restituire la data di nascita dell'utente
	 * 
	 * @return dateOfBirth
	 */
	public Calendar getDateOfBirth() {
		return dateOfBirth;
	}
	
	/**
	 * Metodo utilizzato per restituire l'email dell'utente
	 * 
	 * @return email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Metodo utilizzato per restituire l'username dell'utente
	 * 
	 * @return username
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Metodo utilizzato per restituire la password dell'utente
	 * 
	 * @return password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Metodo utilizzato per restituire il valore booleano del flag autenticato
	 * 
	 * @return isLoggedin
	 */
	public boolean isLoggedIn() {
		return isLoggedIn;
	}
	/**
	 * Metodo utilizzato per impostare il valore del flag di autenticazione dell'utente
	 * 
	 * @param isLoggedIn
	 * 		flag autenticazione utente
	 */
	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}
	/**
	 * Metodo utilizzato per restituire la lista di pagamenti dell'utente
	 * 
	 * @return payments
	 */
	public List<Pagamento> getPaymets() {
		return paymets;
	}
	
	/**
	 * Metodo utilizzato per restituire la carta di credito dell'utente
	 * 
	 * @return creditCard
	 */
	public String getCreditCard() {
		return creditCard;
	}
	
	/**
	 * Metodo utilizzato per restituire la lista delle prenotazioni dell'utente
	 * 
	 * @return reservations
	 */
	public List<Prenotazione> getReservations() {
		return reservations;
	}
	
	
	/**
	 * Metodo che permette di visualizzare a video le prenotazioni, nello 
	 * specifico divise in pagate e non pagate
	 */
	public void showReservations() {
		
		if(this.reservations.isEmpty()) {
			System.out.println("Nessuna Prenotazione");
			return;
		}
		
		System.out.println("Paid Reservations : ");
		for(Prenotazione reservation : getPaidReservations())
			reservation.showReservationInfo();
		
		
		System.out.println("Reservations to be paid: ");
		for(Prenotazione reservation : getUnpaidReservations())
			reservation.showReservationInfo();
		
	}
	
	/**
	 * Metodo che restituisce la lista delle Prenotazioni non pagate dell'Utente
	 * 
	 * @return unpaiedReservations
	 */
	public ArrayList<Prenotazione> getUnpaidReservations(){
		
		ArrayList<Prenotazione> unpaiedReservations = new ArrayList<Prenotazione>();

		for(Prenotazione reservation : this.reservations)
			if(!reservation.isPaid())
				unpaiedReservations.add(reservation);
		
		return unpaiedReservations;
	}
	
	/**
	 * Metodo che restituisce la lista delle Prenotazioni pagate dell'Utente
	 * 
	 * @return paiedReservations
	 */
	public ArrayList<Prenotazione> getPaidReservations(){
		
		ArrayList<Prenotazione> paiedReservations = new ArrayList<Prenotazione>();

		for(Prenotazione reservation : this.reservations)
			if(reservation.isPaid())
				paiedReservations.add(reservation);
		
		return paiedReservations;
	}

	/**
	 * Metodo che permette ad un utente di modificare i propri dati personali
	 * 
	 * @param name
	 * 		nome modificato
	 * @param surname
	 * 		cognome modificato
	 * @param dateOfBirth
	 * 		data di nascita modificata
	 * @param password
	 * 		password modificata 
	 * @return true se la modifica avviene correttamente, false altrimenti
	 */
	public boolean modifyProfile(String name, String surname, Calendar dateOfBirth, String password, String creditCard){
		
		if(!name.equals(""))
			this.name = name;
		else
			return false;
		
		if(!surname.equals(""))
			this.surname = surname;
		else
			return false;
		
		if(dateOfBirth != null)
			this.dateOfBirth = dateOfBirth;
		else
			return false;
		
		if(!password.equals(""))
			this.password = password;
		else
			return false;
		
		if(!creditCard.equals(""))
			this.creditCard = creditCard;
		else
			return false;
		
		return true;
	}
	
	
	/**
	 * Metodo che permette di visualizzare a video i dati relativi a una scheda film.
	 * Nello specifico: il nome e cognome dell'utente, la data di nascita, l'email, l'username,
	 * il numero della carta di credito e la lista dei pagamenti
	 */
	public void showUserInfo() {
		
		System.out.println( "Nome : " + name + " Cognome : " + surname + "\n" +
					"Data di Nascita : " + dateOfBirth.get(Calendar.DAY_OF_MONTH) + "/" +
											(dateOfBirth.get(Calendar.MONTH) + 1)  + "/" + 
											dateOfBirth.get(Calendar.YEAR) + "\n" +
					"email : " + email + "\n" +
					"Username : " + username + "\n" +
					"Carta di Credito : " + creditCard + "\n");
			
		System.out.println("Pagamenti : ");
		for(Pagamento payment: paymets)
			payment.showPaymentInfo();
	}


	
	
}
