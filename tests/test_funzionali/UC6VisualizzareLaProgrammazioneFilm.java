package test_funzionali;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import myclasses.Cinema;
import myclasses.Film;
import myclasses.Sala;
import myclasses.Sistema;
import myclasses.Spettacolo;

public class UC6VisualizzareLaProgrammazioneFilm {

	Sistema system;
	Film film;
	Film film2;
	Cinema cinema;
	Spettacolo show;
	Calendar date;
	 
	@Before
	public void setUp() throws Exception {
		system = new Sistema();
		 
		ArrayList<String> mainActors = new ArrayList<String>();
		mainActors.add("Pippo");
		mainActors.add("Pluto");
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("Colossal");
		film = new Film("Avatar", "Peter Jackson", mainActors, "Fantasy", "Trama", 180, tags);
		
		film2 = new Film("King Kong", "Peter Jackson", mainActors, "Fantasy", "Trama", 180, tags);
		
		cinema = new Cinema("The Space", "via Roma", "Genova", 10);
		int cinemaRoom = 2;
		Sala sala = new Sala(cinemaRoom, 2, 2);
		cinema.getCinemaRooms().put(sala.getCinemaRoom(), sala);
		
		float ticketPrice = (float) 7.50;
		date = Calendar.getInstance();
		date.set(2018, 7, 5);
		show = new Spettacolo(cinema, cinemaRoom, film, ticketPrice, date);
		
		cinema.getShowsList().add(show);
		
		system.getFilmTable().put(film.getFilmID(), film);
		system.getCinemaTable().put(cinema.getCinemaID(), cinema);
	}

	//Scenario Principale: il Sistema trova una lista di Spettacoli per il fim indicato
	@Test
	public void UC6test1() {
		ArrayList<Spettacolo> returned = new ArrayList<Spettacolo>();
		returned.add(show);
				
		assertEquals(system.searchFilmShowList(film, Calendar.getInstance()), returned);
		
	}
	
	//Scenario Alternativo 2a: il Sistema non trova Spettacoli per il fim indicato
		@Test
		public void UC6test2() {
			assertEquals(system.searchFilmShowList(film2, Calendar.getInstance()), new ArrayList<Spettacolo>());
		}
	

}
