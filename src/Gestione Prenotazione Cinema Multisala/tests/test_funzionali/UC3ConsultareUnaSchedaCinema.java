package test_funzionali;

import org.junit.Before;
import org.junit.Test;

import myclasses.Cinema;
import myclasses.Sala;

public class UC3ConsultareUnaSchedaCinema {

	Cinema cinema;
	
	@Before
	public void setUp() throws Exception {
		cinema = new Cinema("The Space", "via Roma", "Genova", 10);
		int cinemaRoom = 2;
		Sala sala = new Sala(cinemaRoom, 2, 2);
		cinema.getCinemaRooms().put(sala.getCinemaRoom(), sala);
		
		//aggiungere uno spettacolo nella data corrente per visualizzarlo come programmzione
	}

	//Scenario Principale: il Sistema mostra la Scheda del Cinema Selezionato, l'Utente la visualizza
	@Test
	public void UC3test() {
		cinema.showCinemaInfo();
	}

} 
