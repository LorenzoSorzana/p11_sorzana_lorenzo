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
import myclasses.Sistema;
import myclasses.Spettacolo;

public class UC8VisualizzareScontiDisponibili {

	Sistema system;
	Cinema cinema1;
	Cinema cinema2;
	Film film;
	Spettacolo show1;
	Spettacolo show2;
	Spettacolo show3;
	Calendar date1 = Calendar.getInstance();
	Calendar date2 = Calendar.getInstance();
	Calendar date3 = Calendar.getInstance();
	
	@Before
	public void setUp() throws Exception {
		system = new Sistema();
		
		cinema1 = new Cinema("The Space", "via Roma", "Genova", 10);
		int cinemaRoom1 = 2;
		Sala sala = new Sala(cinemaRoom1, 2, 2);
		cinema1.getCinemaRooms().put(sala.getCinemaRoom(), sala);
		
		cinema2 = new Cinema("Odeon", "via XX Settembre", "Genova", 9);
		int cinemaRoom2 = 2;
		Sala sala2 = new Sala(cinemaRoom2, 2, 2);
		cinema2.getCinemaRooms().put(sala.getCinemaRoom(), sala2);
		
		film = new Film("Avatar", "Peter Jackson", new ArrayList<String>(), "Fantasy", "Trama", 180, new ArrayList<String>());
		
		float ticketPrice1 = (float) 7.50;
		date1.set(2018, 6, 5);
		show1 = new Spettacolo(cinema1, cinemaRoom1, film, ticketPrice1, date1);
		
		
		float ticketPrice2 = (float) 10;
		date2.set(2018, 7, 5);
		show2 = new Spettacolo(cinema1, cinemaRoom1, film, ticketPrice2, date2);
		
		float ticketPrice3 = (float) 5;
		date3.set(2018, 7, 5);
		show3 = new Spettacolo(cinema2, cinemaRoom1, film, ticketPrice3, date3);
		
		system.getCinemaTable().put(cinema1.getCinemaID(), cinema1);
		system.getCinemaTable().put(cinema2.getCinemaID(), cinema2);
	}

	//Scenario Principale: l'Utente comunca di voler vedere gli Sconti di tutti i cinema presenti 
	//nel Circuito cinema
	@Test
	public void UC8test1() {
		cinema1.getShowsList().add(show1);
		cinema1.getShowsList().add(show2);
		cinema2.getShowsList().add(show3);

		assertEquals(system.getDiscountShowList().size(), 2);
	}

		//Scenario Alternativo 3b: l'Utente indica il cinema di cui vuole visualizzare gli sconti
		@Test
		public void UC8test2() {
			cinema1.getShowsList().add(show1);
			cinema1.getShowsList().add(show2);
			cinema2.getShowsList().add(show3);
			
			assertEquals(system.getDiscountShowList(cinema1).size(),1);
		}
	
			//Scenario Alternativo 3a: il Sistema non trova Sconti e mostra la lista vuota 
			@Test
			public void UC8test2_2() {
				assertTrue(system.getDiscountShowList(cinema1).isEmpty());
			}
		
		//Scenario Alternativo 4a: il Sistema non trova Sconti per tutto il Circuito Cinema 
		//e mostra la lista vuota
		@Test
		public void UC8test3() {	
			assertTrue(system.getDiscountShowList().isEmpty());
		}
}
