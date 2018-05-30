package test_funzionali;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import myclasses.Cinema;
import myclasses.Utente;

public class UC7VisualizzareRecensioniCinema {

	Cinema cinema;
	Utente user;
	
	@Before
	public void setUp() throws Exception {
		cinema = new Cinema("Odeon", "via Roma", "Genova", 10);
		user = new Utente("Mario", "Rossi", Calendar.getInstance(), "mario.rossi@gmail.com", "marioRossi", "ciao", "0000111122223333");
	}

	//Scenario Principale: il Sistema visualizza le recensioni del Cinema selezionato
	@Test
	public void UC7test1() {
		cinema.addReview(user, 4, "bello");	
		cinema.showReviews();
	}
	
		//Scenario Alternativo 2a: il Sistema non trova recensioni per il cinema selezionato
		@Test
		public void UC7test2() {
			cinema.showReviews();
		}

}
