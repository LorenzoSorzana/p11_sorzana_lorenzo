package test_funzionali;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import myclasses.Film;
import myclasses.Sistema;
import myclasses.Utente;

public class UC37RecensireUnFilm {

	int FILM = 21;
	
	Sistema system;
	Utente user;
	Film film;
	
	@Before
	public void setUp() throws Exception {
		system = new Sistema();	
		user = new Utente("Mario", "Rossi", Calendar.getInstance(), "mario.rossi@gmail.com", "marioRossi", "ciao", "0000111122223333");
		film = new Film("Avatar", "Peter Jackson", new ArrayList<String>(), "Fantasy", "Trama", 180, new ArrayList<String>());
		
		system.getFilmTable().put(film.getFilmID(), film);
		system.getUserTable().put(user.getUsername(), user);
	}

	//Scenario Principale: l'Utente inserisce correttamente la recensione
	@Test
	public void UC14test1() {
		assertTrue(system.writeReview(user, film, 3, "commento", FILM));
	}
	
		//Scenario Alternativo 2a: il Sistema non valida la scrittura di una recensione per l'Utente
		//perch� ha gi� recensito il film selezioanto
		@Test
		public void UC14test2() {
			//prima recensione gi� accettata
			assertTrue(system.writeReview(user, film, 3, "commento", FILM));
			
			//non valiazione immissione recensione
			assertFalse(system.writeReview(user, film, 3, "commento", FILM));
		}

		//Scenario Alternativo 4a: il Sistema non valida la recensione immessa 
		//perch� i parametri inseriti non rispettano le regole su voto e commento
		@Test
		public void UC14test3() {
			//non valiazione recensione inserita, perch� il commento � vuoto
			assertFalse(system.writeReview(user, film, 4, "", FILM));
		}
		
}
