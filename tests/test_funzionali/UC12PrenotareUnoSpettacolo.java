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

public class UC12PrenotareUnoSpettacolo {

	Sistema system;
	Utente user;
	Cinema cinema;
	Film film;
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
	}

	//Scenario Principale: L'Utente riesce a completare la Prenotazione dello Spettacolo
	//la prenotazione viene aggiunta alla lista delle Prenotazioni dell'Utente
	@Test
	public void UC12test1() {
		assertTrue(system.makeReservation(user, show, numberOfTickets));
		
		//controllo che la prenotazione sia stata aggiunta al Profilo Utente dal Sistema
		assertEquals(user.getReservations().size(), 1);
		
		//controllo che la prenotazione sia stata aggiunta come non pagata
		assertFalse(user.getReservations().get(0).isPaid());
	}
		

		//Scenario Alternativo 4a: il Sistema non trova abbastanza posti richiesti per lo Spettacolo indicato 
		@Test
		public void UC12test3() {
			assertFalse(system.makeReservation(user, show, 100));
			
			//controllo che nessuna prenotazione sia stata aggiunta al Profilo Utente dal Sistema
			assertEquals(user.getReservations().size(), 0);
		}
		
}
