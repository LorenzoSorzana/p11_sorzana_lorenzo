package test_strutturali;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import myclasses.Cinema;
import myclasses.Film;
import myclasses.Sala;
import myclasses.Spettacolo;

public class SpettacoloTest {

	Spettacolo show;
	Cinema cinema;
	int cinemaRoom;
	Film film;
	float ticketPrice;
	Sala sala;
	Calendar date;
	
	@Before
	public void setUp() throws Exception {
		cinema = new Cinema("Odeon", "via Roma", "Genova", 10);
		cinemaRoom = 2;
		ticketPrice = (float) 8.5;
		sala = new Sala(cinemaRoom,10,10);
		cinema.getCinemaRooms().put(sala.getCinemaRoom(), sala);
		film = new Film("Star Wars", "Pippo", new ArrayList<String>(), "Fantasy", "trama", 200, new ArrayList<String>());
		date = Calendar.getInstance();
		
		show = new Spettacolo(cinema, cinemaRoom, film, ticketPrice, date);
	}

	@Test
	public void testSpettacolo() {
		assertNotNull(show);
	}

	@Test
	public void testGetCinema() {
		assertEquals(show.getCinema(), cinema);
	}

	@Test
	public void testGetCinemaRoom() {
		assertEquals(show.getCinemaRoom(), cinemaRoom);
	}

	@Test
	public void testGetFilm() {
		assertEquals(show.getFilm(), film);
	}

	@Test
	public void testGetTicketPrice() {
		assertEquals((double) show.getTicketPrice(), (double) ticketPrice, (double) 0);
	}

	@Test
	public void testGetRemainingSeats() {
		assertEquals(show.getRemainingSeats(), sala.getCapacity());
	}
	
	@Test
	public void testSetRemainingSeats() {
		show.setRemainingSeats(5);
		
		assertEquals(show.getRemainingSeats(), 5);
					
	}

	@Test
	public void testGetOccupiedSeats() {
		//nella setup creo la sala e lo spettacolo, senza prenotare 
		//tutto deve risultare a false
		for(int i=0; i<sala.getRows(); i++)
			for(int j=0; j<sala.getColumns(); j++)
				assertFalse(show.getOccupiedSeats()[i][j]);
	}

	@Test
	public void testGetDateAndHour() {
		assertEquals(show.getDateAndHour(), date);
	}

	@Test
	public void testShowShowInfo() {
		show.showShowInfo();;
	}

}
