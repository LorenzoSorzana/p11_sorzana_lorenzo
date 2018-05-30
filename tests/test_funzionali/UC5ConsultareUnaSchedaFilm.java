package test_funzionali;


import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import myclasses.Film;

public class UC5ConsultareUnaSchedaFilm {

	Film film;
	
	@Before
	public void setUp() throws Exception {
		ArrayList<String> mainActors = new ArrayList<String>();
		mainActors.add("Pippo");
		mainActors.add("Pluto");
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("Colossal");
		film = new Film("Avatar", "Peter Jackson", mainActors, "Fantasy", "Trama", 180, tags);
	}

	//Scenario Principale: il Sistema mostra la Scheda del Film Selezionato, l'Utente la visualizza
	@Test
	public void UC5test() {
		film.showFilmInfo();
	}

}
