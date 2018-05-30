package test_funzionali;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import myclasses.Cinema;
import myclasses.Film;
import myclasses.Sala;
import myclasses.Spettacolo;

public class UC33VisualizzareProgrammazioneCinema {

	Cinema cinema;
	Film film;
	Spettacolo show;
	Calendar date = Calendar.getInstance();
	
	@Before
	public void setUp() throws Exception {
		film = new Film("Avatar", "Peter Jackson", new ArrayList<String>(), "Fantasy", "Trama", 180, new ArrayList<String>());

		cinema = new Cinema("The Space", "via Roma", "Genova", 10);
		int cinemaRoom = 2;
		Sala sala = new Sala(cinemaRoom, 2, 2);
		cinema.getCinemaRooms().put(sala.getCinemaRoom(), sala);
		
		float ticketPrice = (float) 7.50;
		date.set(2018, 6, 5);
		show = new Spettacolo(cinema, cinemaRoom, film, ticketPrice, date);
		
		cinema.getShowsList().add(show);
		
	}

	//Scenario Principale: l'Utente visualizza la Programmazione del Cinema della data richiesta
	@Test
	public void UC33test1() {
		assertEquals(cinema.getCinemaShowList(date).size(), 1);
	}
	
		//Scenario Alternativo 4a: l'Utente visualizza la Programmazione vuota per la data richiesta
		@Test
		public void UC33test2() {
			assertTrue(cinema.getCinemaShowList(Calendar.getInstance()).isEmpty());
		}

}
