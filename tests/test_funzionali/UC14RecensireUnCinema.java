package test_funzionali;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import myclasses.Cinema;
import myclasses.Sistema;
import myclasses.Utente;

public class UC14RecensireUnCinema {

	int CINEMA = 20;
	
	Sistema system;
	Utente user;
	Cinema cinema;
	
	@Before
	public void setUp() throws Exception {
		system = new Sistema();	
		user = new Utente("Mario", "Rossi", Calendar.getInstance(), "mario.rossi@gmail.com", "marioRossi", "ciao", "0000111122223333");
		cinema = new Cinema("Odeon", "via Roma", "Genova", 10);
		
		system.getCinemaTable().put(cinema.getCinemaID(), cinema);
		system.getUserTable().put(user.getUsername(), user);
	}

	//Scenario Principale: l'Utente inserisce correttamente la recensione
	@Test
	public void UC14test1() {
		assertTrue(system.writeReview(user, cinema, 3, "commento", CINEMA));
	}
	
		//Scenario Alternativo 2a: il Sistema non valida la scrittura di una recensione per l'Utente
		//perchè ha già recensito il Cinema selezioanto
		@Test
		public void UC14test2() {
			//prima recensione già accettata
			assertTrue(system.writeReview(user, cinema, 3, "commento", CINEMA));
			
			//non valiazione immissione recensione
			assertFalse(system.writeReview(user, cinema, 3, "commento", CINEMA));
		}

		//Scenario Alternativo 4a: il Sistema non valida la recensione immessa 
		//perchè i parametri inseriti non rispettano le regole su voto e commento
		@Test
		public void UC14test3() {
			//non valiazione recensione inserita, perchè il commento è vuoto
			assertFalse(system.writeReview(user, cinema, 4, "", CINEMA));
		}
		
}
