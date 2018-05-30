package test_strutturali;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import myclasses.Cinema;
import myclasses.Film;
import myclasses.Sala;
import myclasses.Spettacolo;
import myclasses.Utente;

public class CinemaTest {

	Cinema cinema;
	static int counter;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		counter = Cinema.progressiveID;
	}
	@Before
	public void setUp() throws Exception {
		cinema = new Cinema("Odeon", "via Roma", "Genova", 10);
		counter++;
		Sala sala = new Sala(2,10,10);
		cinema.getCinemaRooms().put(sala.getCinemaRoom(), sala);
	}
	
	@After
	public void tearDown() throws Exception {
		cinema = null;
	}

	@Test
	public void testCinema() {
		assertNotNull(cinema);
	}

	@Test
	public void testGetName() {
		assertEquals(cinema.getName(), "Odeon");
	}

	@Test
	public void testGetAddress() {
		assertEquals(cinema.getAddress(), "via Roma");
	}

	@Test
	public void testGetCity() {
		assertEquals(cinema.getCity(), "Genova");
	}

	@Test
	public void testGetShowsList() {
		assertEquals(cinema.getShowsList().size(), 0);
	}

	@Test
	public void testGetReviews() {
		assertEquals(cinema.getReviews().size(), 0);
	}

	@Test
	public void testGetCinemaRooms() {
		assertEquals(cinema.getCinemaRooms().values().size(), 1);
	}

	@Test
	public void testGetRating() {
		assertEquals((double) cinema.getRating(), (double) 0, (double) 0);
	}

	@Test
	public void testGetCinemaID() {
		assertEquals(cinema.getCinemaID(), counter);
	}

	@Test
	public void testGetBaseTicketPrice() {
		assertEquals((double) cinema.getBaseTicketPrice(), (double) 10, (double) 0);
	}

	@Test
	public void testGetCinemaShowList() {
		//creo due spettacoli con lo stesso film in due date diverse, da quelle di oggi
		//di cui una in un giorno e una nello stesso giorno ma dell'anno dopo
		Film film = new Film("Star Wars", "Pippo", new ArrayList<String>(), "Fantasy", "trama", 200, new ArrayList<String>());
		int cinemaRoom = 2;
		float ticketPrice = (float) 8.5;
		Calendar date = Calendar.getInstance();
		Calendar otherYearDate = Calendar.getInstance();

		date.set(2018, 7, 20);
		Spettacolo show = new Spettacolo(cinema,cinemaRoom,film,ticketPrice,date);
		cinema.getShowsList().add(show);
		
		otherYearDate.set(2019, 7, 20);
		Spettacolo s2 = new Spettacolo(cinema,cinemaRoom,film,ticketPrice,otherYearDate);
		cinema.getShowsList().add(s2);
		
		ArrayList<Spettacolo> returned = new ArrayList<Spettacolo>();
		returned.add(show);
		
		//la ricerca ritorna una lista con solo uno spettacolo con data uguale a quella ricercata
		assertEquals(cinema.getCinemaShowList(date), returned);
		//nessuno spettacolo per la data corrente, ritorna una lista vuota
		assertEquals(cinema.getCinemaShowList(Calendar.getInstance()), new ArrayList<Spettacolo>());

	}

	@Test
	public void testShowCinemaInfo() {
		//creo uno spettacolo in data corrente con il relativo film
		Film film = new Film("Star Wars", "Pippo", new ArrayList<String>(), "Fantasy", "trama", 200, new ArrayList<String>());
		int cinemaRoom = 2;
		float ticketPrice = (float) 8.5;
		Calendar date = Calendar.getInstance();
		
		Spettacolo s = new Spettacolo(cinema,cinemaRoom,film,ticketPrice,date);
		cinema.getShowsList().add(s);
		
		//stampo i dati del cinema, comprensivo della programmazione del giorno corrente
		cinema.showCinemaInfo();
	}

	@Test
	public void testShowReviews() {
		//nessuna recensione trovata
		cinema.showReviews();
		
		//aggiungo una recensione
		Utente u1 = new Utente("Mario", "Rossi", Calendar.getInstance(), "mario.rossi@gmail.com", "marioRossi", "ciao", "0000111122223333");
		cinema.addReview(u1, 4, "bello");
		//viene trovata la lista delle recensioni, in questo caso una
		cinema.showReviews();
	}

	@Test
	public void testAddReview() {
		//controllo che l'utente inserisca correttamnete la recensione, con i parametri corretti
		//voto compreso tra 1 e 5, commento non vuoto
		
		Utente u1 = new Utente("Mario", "Rossi", Calendar.getInstance(), "mario.rossi@gmail.com", "marioRossi", "ciao", "0000111122223333");
		
		Utente u2 = new Utente("Massimo", "Verdi", Calendar.getInstance(), "massimo.verdi@gmail.com", "massimoVerdi", "ciao", "0000111122223333");

		Utente u3 = null;

		assertFalse(cinema.addReview(u1, 6, "commento"));
		assertFalse(cinema.addReview(u1, -1, "commento"));
		assertFalse(cinema.addReview(u3, 4, "commento"));
		assertFalse(cinema.addReview(u1, 4, ""));
		
		assertTrue(cinema.addReview(u1, 4, "commento"));
		assertTrue(cinema.addReview(u2, 4, "commento"));
		
	}

	@Test
	public void testSearchThisCinemaFilmShowList() {
		
		//
		Film film = new Film("Star Wars", "Pippo", new ArrayList<String>(), "Fantasy", "trama", 200, new ArrayList<String>());
		int cinemaRoom = 2;
		float ticketPrice = (float) 8.5;
		Calendar beforeDate = Calendar.getInstance();
		Calendar afterDate = Calendar.getInstance();
		
		//spettacolo passato
		beforeDate.set(2018, 1, 20);
		Spettacolo s = new Spettacolo(cinema,cinemaRoom,film,ticketPrice,beforeDate);
		cinema.getShowsList().add(s);
		
		//spettacolo data futura
		afterDate.set(2018, 6, 9);
		Spettacolo s2 = new Spettacolo(cinema,cinemaRoom,film,ticketPrice,afterDate);
		cinema.getShowsList().add(s2);
		
		//film di cui so non esserci spettacoli in tutto il circuito cinema
		Film noShowFilm = new Film("Titanic", "Peter Jackson", new ArrayList<String>(), "Drammatico", "Trama", 190, new ArrayList<String>());
		
		//film non presente
		assertEquals(cinema.searchThisCinemaFilmShowList(noShowFilm, Calendar.getInstance()).size(), 0);
		//film presente, ritornati solo gli spettacoli futuri presenti
		assertEquals(cinema.searchThisCinemaFilmShowList(film, Calendar.getInstance()).size(),1);

		//film presente, non presenti spettacoli futuri alla data indicata
		Calendar futureDate = Calendar.getInstance();
		futureDate.set(2019, 5, 3);
		assertEquals(cinema.searchThisCinemaFilmShowList(film, futureDate).size(), 0);

	}
	

}
