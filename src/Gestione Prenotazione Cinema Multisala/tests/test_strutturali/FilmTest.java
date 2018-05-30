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

import myclasses.Film;
import myclasses.Utente;

public class FilmTest {

	ArrayList<String> mainActors = new ArrayList<String>();
	Film film;
	static int counter;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		counter = Film.progressiveID;
	}
 
	@Before
	public void setUp() throws Exception {
		film = new Film("Avatar", "Peter Jackson", mainActors, "Fantasy", "Trama", 180, new ArrayList<String>());
		counter++;
		mainActors.add("Pippo");
		mainActors.add("Pluto");
	}

	@After
	public void tearDown() throws Exception {
		film.getMainActors().clear();
	}

	@Test
	public void testFilm() {
		assertNotNull(film);
	}

	@Test
	public void testGetFilmID() {
		assertEquals(film.getFilmID() , counter);
	}

	@Test
	public void testGetTitle() {
		assertEquals(film.getTitle() , "Avatar");
	}

	@Test
	public void testGetRating() {
		assertEquals((double) film.getRating(), (double) 0, (double) 0);
	}

	@Test
	public void testGetFilmDirector() {
		assertEquals(film.getFilmDirector() , "Peter Jackson");
	}

	@Test
	public void testGetMainActors() {
		assertEquals(film.getMainActors().size() , 2);
	}

	@Test
	public void testGetGenre() {
		assertEquals(film.getGenre() , "Fantasy");
	}

	@Test
	public void testGetPlot() {
		assertEquals(film.getPlot() , "Trama");
	}

	@Test
	public void testGetDuration() {
		assertEquals(film.getDuration() , 180);
	}

	@Test
	public void testGetTags() {
		assertEquals(film.getTags().size() , 0);
	}

	@Test
	public void testGetReviews() {
		assertEquals(film.getReviews().size() , 0);
	}

	@Test
	public void testShowFilmInfo() {
		
		
		
		film.showFilmInfo();
	}

	@Test
	public void testShowReviews() {
		//nessuna recensione trovata
		film.showReviews();
		
		//aggiungo una recensione
		Utente reviewer1 = new Utente("Mario", "Rossi", Calendar.getInstance(), "mario.rossi@gmail.com", "marioRossi", "ciao", "12341234123413");
		film.addReview(reviewer1, 3, "commento");
		//viene trovata la lista delle recensioni, in questo caso una
		film.showReviews();
	}

	@Test
	public void testAddReview() {
		//controllo che l'utente inserisca correttamnete la recensione, con i parametri corretti
		//voto compreso tra 1 e 5, commento non vuoto
		
		Utente reviewer1 = new Utente("Mario", "Rossi", Calendar.getInstance(), "mario.rossi@gmail.com", "marioRossi", "ciao", "12341234123413");
		Utente reviewer2 = new Utente("Massimo", "Verdi", Calendar.getInstance(), "massimo.verdi@gmail.com", "massimoVerdi", "ciao", "12341234123413");
		Utente reviewer3 = null;
		
		assertFalse(film.addReview(reviewer1, 6, "commento"));
		assertFalse(film.addReview(reviewer1, -1, "commento"));
		assertFalse(film.addReview(reviewer3, 4, "commento"));
		assertFalse(film.addReview(reviewer1, 4, ""));
		
		assertTrue(film.addReview(reviewer1, 4, "commento"));
		assertTrue(film.addReview(reviewer2, 4, "commento"));
		
	}

}
