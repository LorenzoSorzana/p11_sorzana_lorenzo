package test_funzionali;

import static org.junit.Assert.assertEquals;
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
 
public class UC15GestirePrenotazioni {

	int PAYPAL = 30;
	
	Sistema system;
	Utente user;
	Cinema cinema;
	Film film;
	Spettacolo show;
	Calendar date = Calendar.getInstance();
	
	@Before
	public void setUp() throws Exception {
		system = new Sistema();
		 
		user = new Utente("Mario", "Rossi", Calendar.getInstance(), "mario.rossi@gmail.com", "marioRossi", "ciao", "1111222233334444");
		
		film = new Film("Avatar", "Peter Jackson", new ArrayList<String>(), "Fantasy", "Trama", 180, new ArrayList<String>());
		 
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

		//system.makeReservation(user, show, 3);
	}

	//Scenario Principale: l'Utente paga una Prenotazione
	//il pagamento va a buon fine
	@Test
	public void UC15test1() {
		system.makeReservation(user, show, 3);
		
		//l'Utente visualizza le Prenotazioni presenti nel suo Profilo
		user.showReservations();
		
		assertEquals(user.getUnpaidReservations().size(),1);
		
		assertTrue(system.buyReservation(user, user.getUnpaidReservations().get(0), PAYPAL));
		
		assertEquals(user.getPaidReservations().size(), 1);
		
	}
	
		//Scenario Alternativo 2a: il Sistema non trova prenotazioni e lo notifica
		@Test
		public void UC15test2() {
			//l'Utente visualizza la lista vuota delle Prenotazioni
			user.showReservations();
		}
		
		//Scenario Alternativo 4a: l'Utente vuole cancellare una Prenotazione non Pagata
		@Test
		public void UC15test3() {
			system.makeReservation(user, show, 3);
			
			assertTrue(system.deleteReservation(user, user.getReservations().get(0)));
		}
		
			//Scenario Alternativo 3a: l'Utente annulla
			@Test
			public void UC15test3_2() {
				system.makeReservation(user, show, 3);
				
				assertEquals(user.getReservations().size(), 1);
			}
			
			
		
		//Scenario Alternativo 7a: l'Utente vuole pagare una Prenotazione non Pagata
		//il pagamento non va a buon fine
		@Test
		public void UC15test4_2() {
				system.makeReservation(user, show, 3);
				
				//metodo di pagamento non accettabile
				assertFalse(system.buyReservation(user,  user.getUnpaidReservations().get(0), 5));
				
				assertFalse(user.getReservations().get(0).isPaid());
			}
		
}
