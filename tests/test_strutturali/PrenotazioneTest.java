package test_strutturali;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import myclasses.Cinema;
import myclasses.Film;
import myclasses.Prenotazione;
import myclasses.Sala;
import myclasses.Spettacolo;

public class PrenotazioneTest {

	Prenotazione reservation;
	Spettacolo show;
	ArrayList<String> seats;

	static int counter;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		counter = Prenotazione.progressiveID;
	}
	@Before
	public void setUp() throws Exception {
		
		Film film = new Film("Avatar", "Peter Jackson", new ArrayList<String>(), "Fantasy", "Trama", 180, new ArrayList<String>());
		Cinema cinema = new Cinema("Odeon", "via Roma", "Genova", 10);
		int cinemaRoomNumber = 2;
		Sala sala = new Sala(cinemaRoomNumber, 10, 10);
		cinema.getCinemaRooms().put(sala.getCinemaRoom(), sala);
		show = new Spettacolo(cinema, cinemaRoomNumber, film, (float) 7, Calendar.getInstance());
		
		seats = new ArrayList<String>();
		seats.add("A1");
		seats.add("A2");
		
		reservation = new Prenotazione(show, seats);
		counter++;
	}

	@Test
	public void testPrenotazione() {
		assertNotNull(reservation);
	}

	@Test
	public void testGetReservationId() {
		assertEquals(reservation.getReservationId(), counter);
	}

	@Test
	public void testGetShow() {
		assertEquals(reservation.getShow(), show);
	}

	@Test
	public void testGetNumberOfTickets() {
		assertEquals(reservation.getNumberOfTickets(), seats.size());
	}

	@Test
	public void testGetSeats() {
		assertEquals(reservation.getSeats(), seats);
	}

	@Test
	public void testGetPrice() {
		assertEquals((double) reservation.getPrice(), (double) (7*reservation.getNumberOfTickets()), (double) 0);
	}

	@Test
	public void testIsPaid() {
		assertFalse(reservation.isPaid());
		
		reservation.setPaid(true);
		assertTrue(reservation.isPaid());
		
	}

	@Test
	public void testSetPaid() {
		reservation.setPaid(true);
		assertTrue(reservation.isPaid());
		
		reservation.setPaid(false);
		assertFalse(reservation.isPaid());
	}

	@Test
	public void testShowReservationInfo() {
		reservation.showReservationInfo();
	}

}
