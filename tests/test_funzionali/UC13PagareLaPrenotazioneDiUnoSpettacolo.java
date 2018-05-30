package test_funzionali;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import myclasses.Cinema;
import myclasses.Film;
import myclasses.Sala;
import myclasses.Sistema;
import myclasses.Spettacolo;
import myclasses.Utente;

public class UC13PagareLaPrenotazioneDiUnoSpettacolo {

	int PAYPAL = 30;
	
	Sistema system;
	Utente user;
	Film film;
	Cinema cinema;
	Calendar date;
	Spettacolo show;
	int numberOfTickets = 3;
	 
	@Before
	public void setUp() throws Exception {
		system = new Sistema();
		
		user = new Utente("Mario", "Rossi", Calendar.getInstance(), "mario.rossi@gmail.com", "marioRossi", "ciao", "1111222233334444");
		
		ArrayList<String> mainActors = new ArrayList<String>();
		mainActors.add("Pippo");
		mainActors.add("Pluto");
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("Colossal");
		film = new Film("Avatar", "Peter Jackson", mainActors, "Fantasy", "Trama", 180, tags);
		
		cinema = new Cinema("The Space", "via Roma", "Genova", 10);
		int cinemaRoom = 2;
		Sala sala = new Sala(cinemaRoom, 2, 2);
		cinema.getCinemaRooms().put(sala.getCinemaRoom(), sala);
		
		float ticketPrice = (float) 7.50;
		date = Calendar.getInstance();
		date.set(2018, 6, 5);
		show = new Spettacolo(cinema, cinemaRoom, film, ticketPrice, date);
		
		cinema.getShowsList().add(show);
		
		system.getFilmTable().put(film.getFilmID(), film);
		system.getCinemaTable().put(cinema.getCinemaID(), cinema);

		system.makeReservation(user, show, numberOfTickets);
	}

	//Scenario Principale: L'Utente Paga la prenotazione scelta
	@Test
	public void UC13test1() {
		
		//il Sistema mostra il resoconto della prenotazione
		user.getReservations().get(0).showReservationInfo();
		
		//l'Utente conferma avviando la procedura di pagamento con metodo valido
		assertTrue(system.buyReservation(user, user.getReservations().get(0), PAYPAL));
		
		//prenotazione aggiornata come pagata
		assertTrue(user.getReservations().get(0).isPaid());
	}

		//Scenario Alternativo 4a: L'Utente annulla l'operazione
		@Test
		public void UC13test2() {
			
			//il Sistema mostra il resoconto della prenotazione
			user.getReservations().get(0).showReservationInfo();
			
			//l'Utente annulla la prenotazione rimane non pagata
			assertFalse(user.getReservations().get(0).isPaid());
		}

		//Scenario Alternativo 6a: L'Utente paga con un metodo non valido
		@Test
		public void UC13test3() {
			
			//il Sistema mostra il resoconto della prenotazione
			user.getReservations().get(0).showReservationInfo();
			
			//l'Utente conferma avviando la procedura di pagamento con metodo non valido
			assertFalse(system.buyReservation(user, user.getReservations().get(0), 5));
			
			//la prenotazione rimane non pagata
			assertFalse(user.getReservations().get(0).isPaid());
		}
}
