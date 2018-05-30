package test_strutturali;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import myclasses.Recensione;
import myclasses.Utente;

public class RecensioneTest {

	Recensione review;
	Utente reviewer;
	
	@Before
	public void setUp() throws Exception {
		reviewer = new Utente("Mario", "Rossi", Calendar.getInstance(), "mario.rossi@gmail.com", "marioRossi", "ciao", "0000111122223333");
		
		review = new Recensione(reviewer, 3, "commento");
	}

	@Test
	public void testRecensione() {
		assertNotNull(reviewer);
	}

	@Test
	public void testGetUser() {
		assertEquals(review.getUser(), reviewer);
	}

	@Test
	public void testGetRate() {
		assertEquals(review.getRate(), 3);
	}

	@Test
	public void testGetComment() {
		assertEquals(review.getComment(), "commento");
	}

	@Test
	public void testShowReviewInfo() {
		review.showReviewInfo();
	}

}
