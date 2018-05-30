package test_funzionali;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import myclasses.Film;
import myclasses.Sistema;

public class UC4CercareUnFilm {

	int BY_TITLE = 0;
	int BY_ALL = 5;

	Sistema system;
	Film film;
	
	@Before
	public void setUp() throws Exception {
		system = new Sistema();
		
		film = new Film("Avatar", "Peter Jackson", new ArrayList<String>(), "Fantasy", "Trama", 180, new ArrayList<String>());
		
		system.getFilmTable().put(film.getFilmID(), film);
	}
 
	//Scenario Principale: l'Utente visualizza la lista dei Film compatibili con la ricerca eseguita
	@Test
	public void UC4test1() {
		ArrayList<Film> returned = new ArrayList<Film>();
		returned.add(film);
		
		//il Sistema trova i Film corrispondenti
		assertEquals(system.searchFilm("Avatar", BY_TITLE), returned);
	}
	
		//Scenario Alternativo 2a: l'Utente comunica di voler ricercare tutti i Film presenti nel Circuito Cinema
		@Test
		public void UC4test2() {
			//il Sistema restituisce la lista di tutti i Film presenti
			assertEquals(system.searchFilm("", BY_ALL), new ArrayList<Film>(system.getFilmTable().values()));
		}

		//Scenario Alternativo 3a: il Sistema non trova Film presenti, per il tipo di ricerca richiesto
		@Test
		public void UC4test3() {
			//il Sistema restituisce la lista vuota
			assertEquals(system.searchFilm("Star Wars", BY_TITLE), new ArrayList<Film>());
		}

}
