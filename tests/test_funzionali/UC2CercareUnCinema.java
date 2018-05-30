package test_funzionali;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import myclasses.Cinema;
import myclasses.Sala;
import myclasses.Sistema;

public class UC2CercareUnCinema {

	int BY_NAME = 10;
	int BY_ALL = 5;

	
	Sistema system;
	Cinema cinema;
	 
	@Before
	public void setUp() throws Exception {
		system = new Sistema();
		
		cinema = new Cinema("The Space", "via Roma", "Genova", 10);
		int cinemaRoom = 2;
		Sala sala = new Sala(cinemaRoom, 2, 2);
		cinema.getCinemaRooms().put(sala.getCinemaRoom(), sala);
		
		system.getCinemaTable().put(cinema.getCinemaID(), cinema);
	}

	//Scenario Principale: l'Utente visualizza la lista dei cinema compatibili con la ricerca eseguita
	@Test
	public void UC2test1() {
		ArrayList<Cinema> returned = new ArrayList<Cinema>();
		returned.add(cinema);
		
		//il Sistema trova i Cinema corrispondenti
		assertEquals(system.searchCinema("Space", BY_NAME), returned);
	}
	
		//Scenario Alternativo 2a: l'Utente comunica di voler ricercare tutti i Cinema presenti nel Circuito Cinema
		@Test
		public void UC2test2() {
			//il Sistema restituisce la lista di tutti i Cinema presenti
			assertEquals(system.searchCinema("", BY_ALL), new ArrayList<Cinema>(system.getCinemaTable().values()));
		}

		//Scenario Alternativo 3a: il Sistema non trova Cinema presenti, per il tipo di ricerca richiesto
		@Test
		public void UC2test3() {
			//il Sistema restituisce la lista vuota
			assertEquals(system.searchCinema("Odeon", BY_NAME), new ArrayList<Cinema>());
		}
}
