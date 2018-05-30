package test_funzionali;

import java.util.ArrayList;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import myclasses.Film;
import myclasses.Utente;

public class UC36VisualizzareRecensioniFilm {

	Film film;
	Utente user;
	
	@Before
	public void setUp() throws Exception {
		film = new Film("Avatar", "Peter Jackson", new ArrayList<String>(), "Fantasy", "Trama", 180, new ArrayList<String>());
		user = new Utente("Mario", "Rossi", Calendar.getInstance(), "mario.rossi@gmail.com", "marioRossi", "ciao", "0000111122223333");
	}

	//Scenario Principale: il Sistema visualizza le recensioni del film selezionato
	@Test
	public void UC36test1() {
		film.addReview(user, 4, "bello");	
		film.showReviews();
	}
	
		//Scenario Alternativo 2a: il Sistema non trova recensioni per il film selezionato
		@Test
		public void UC36test2() {
			film.showReviews();
		}

}
