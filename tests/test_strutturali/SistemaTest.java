package test_strutturali;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import myclasses.Cinema;
import myclasses.Film;
import myclasses.Sala;
import myclasses.Sistema;
import myclasses.Spettacolo;
import myclasses.Utente;

public class SistemaTest {

	int BY_TITLE = 0;
	int BY_FILM_DIRECTOR = 1;
	int BY_ACTOR = 2;
	int BY_GENRE = 3;
	int BY_TAG = 4;
	
	int BY_ALL = 5;
	
	int BY_NAME = 10;
	int BY_CITY = 11; 
	
	int CINEMA = 20;
	int FILM = 21;
	
	int PAYPAL = 30;
	int CREDIT_CARD = 31;
	
	Sistema system;
	Utente user;
	Film film;
	Film film2;
	Cinema cinema;
	Spettacolo show;
	Spettacolo show2;
	Calendar date;
	
	@Before
	public void setUp() throws Exception {
		system = new Sistema();
		
		user = new Utente("Mario", "Rossi", Calendar.getInstance(), "mario.rossi@gmail.com", "marioRossi", "ciao", "0000111122223333");
		
		ArrayList<String> mainActors = new ArrayList<String>();
		mainActors.add("Pippo");
		mainActors.add("Pluto");
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("Colossal");
		film = new Film("Avatar", "Peter Jackson", mainActors, "Fantasy", "Trama", 180, tags);
		
		cinema = new Cinema("The Space", "via Roma", "Genova", 10);
		int cinemaRoom = 2;
		Sala sala = new Sala(cinemaRoom, 4, 4);
		cinema.getCinemaRooms().put(sala.getCinemaRoom(), sala);
		
		
		float ticketPrice = (float) 7.50;
		date = Calendar.getInstance();
		date.set(2018, 8, 5);
		show = new Spettacolo(cinema, cinemaRoom, film, ticketPrice, date);
		
		film2 = new Film("Django", "Quentin Tarantino", new ArrayList<String>(), "Fantasy", "Trama", 180, new ArrayList<String>());
		
		float ticketPrice2 = (float) 10;
		show2 = new Spettacolo(cinema, cinemaRoom, film2, ticketPrice2, date);
		
		cinema.getShowsList().add(show);
		cinema.getShowsList().add(show2);
		
		//aggiungo al sistema un utente, un film e un cinema comprensivo di una sala
		system.getUserTable().put(user.getUsername(), user);
		system.getFilmTable().put(film.getFilmID(), film);
		system.getCinemaTable().put(cinema.getCinemaID(), cinema);
	}

	@Test
	public void testSistema() {
		assertNotNull(system);
	}
	
	@Test
	public void testGetFilmTable() {
		HashMap<Integer, Film> map = new HashMap<Integer, Film>();
		map.put(film.getFilmID(), film);
		
		assertEquals(system.getFilmTable(), map);
	}

	@Test
	public void testGetCinemaTable() {
		HashMap<Integer, Cinema> map = new HashMap<Integer, Cinema>();
		map.put(cinema.getCinemaID(), cinema);
		
		assertEquals(system.getCinemaTable(), map);
	}

	@Test
	public void testGetUserTable() {
		HashMap<String, Utente> map = new HashMap<String, Utente>();
		map.put(user.getUsername(), user);
		
		assertEquals(system.getUserTable(), map);
	}

	@Test
	public void testLogin() {
		//creo un secondo utente non presente nel sistema, non registrato
		Utente user2 = new Utente("Massimo", "Verdi", Calendar.getInstance(), "massimo.verdi@gmail.com", "massimoVerdi", "ciao", "12341234123413");
		
		//login secondo utente
		assertFalse(system.login(user2.getUsername(), user2.getPassword()));
		//login utente 1 pwd errata
		assertFalse(system.login(user.getUsername(), "pwd_errata"));
		//utente 1 login con successo
		assertTrue(system.login(user.getUsername(), user.getPassword()));
		//utente 1 già autenticato precendentemente
		assertFalse(system.login(user.getUsername(), user.getPassword()));
		
	}
 
	@Test
	public void testLogout() {
		
		system.login(user.getUsername(), user.getPassword());
		
		assertTrue(system.logout(user.getUsername()));
		
	}

	@Test
	public void testSearchFilm() {
		
		ArrayList<Film> returned = new ArrayList<Film>();
		ArrayList<Film> returnedAll = new ArrayList<Film>(system.getFilmTable().values());
		returned.add(film);
		
		assertEquals(system.searchFilm("Avatar", BY_TITLE), returned);
		assertEquals(system.searchFilm("Ava", BY_TITLE), returned);
		//film con titolo o parte del titolo non presente nel Sistema
		assertEquals(system.searchFilm("Star Wars", BY_TITLE), new ArrayList<Film>());
		
		assertEquals(system.searchFilm("Peter Jackson", BY_FILM_DIRECTOR), returned);
		//fim con regista non presente nel Sistema
		assertEquals(system.searchFilm("Christopher Nolan", BY_FILM_DIRECTOR), new ArrayList<Film>());
		
		assertEquals(system.searchFilm("Pippo", BY_ACTOR), returned);
		//film con attore non presente nel Sistema
		assertEquals(system.searchFilm("Topolino", BY_ACTOR), new ArrayList<Film>());
		
		assertEquals(system.searchFilm("Fantasy", BY_GENRE), returned);
		//film con genere non presente nel Sistema
		assertEquals(system.searchFilm("Horror", BY_GENRE), new ArrayList<Film>());
		
		assertEquals(system.searchFilm("Colossal", BY_TAG), returned);
		//film con tag non presente nel Sistema
		assertEquals(system.searchFilm("LowBudget", BY_TAG), new ArrayList<Film>());

		//ricerca totale di tutti i film
		assertEquals(system.searchFilm("", BY_ALL), returnedAll);
		assertEquals(system.searchFilm("inutile", BY_ALL), returnedAll);

		assertEquals(system.searchFilm("LowBudget", 100), new ArrayList<Film>());
	}

	@Test
	public void testSearchCinema() {
		ArrayList<Cinema> returned = new ArrayList<Cinema>();
		ArrayList<Cinema> returnedAll = new ArrayList<Cinema>(system.getCinemaTable().values());
		returned.add(cinema);
		
		assertEquals(system.searchCinema("The Space", BY_NAME), returned);
		assertEquals(system.searchCinema("Space", BY_NAME), returned);
		//cinema con nome o parte del nome non presente nel Sistema
		assertEquals(system.searchCinema("Fiumara", BY_NAME), new ArrayList<Cinema>());
		
		assertEquals(system.searchCinema("Genova", BY_CITY), returned);
		//cinema con citta' non presente nel Sistema
		assertEquals(system.searchCinema("Milano", BY_CITY), new ArrayList<Cinema>());
		
		//ricerca totale di tutti i cinema
		assertEquals(system.searchCinema("", BY_ALL), returnedAll);
		assertEquals(system.searchCinema("inutile", BY_ALL), returnedAll);
		
		assertEquals(system.searchCinema("Fiumara", 100), new ArrayList<Cinema>());
		
	}

	@Test
	public void testWriteReview() {
		Utente newReviewer = new Utente("Massimo", "Verdi", Calendar.getInstance(), "massimo.verdi@gmail.com", "massimoVerdi", "ciao", "12341234123413");
		
		//inserimento una recensione per un film e un cinema 
		assertTrue(system.writeReview(user, cinema, 4, "commento", CINEMA));
		assertTrue(system.writeReview(user, film, 4, "commento", FILM));
		
		assertFalse(system.writeReview(user, cinema, 4, "commento", 100));
		
		//utente prova a recensire due volte lo stesso cinema e film
		assertFalse(system.writeReview(user, cinema, 4, "commento", CINEMA));
		assertFalse(system.writeReview(user, film, 4, "commento", FILM));
		assertTrue(system.writeReview(newReviewer, cinema, 4, "commento", CINEMA));
		assertTrue(system.writeReview(newReviewer, film, 4, "commento", FILM));

	}

	@Test
	public void testMakeReservation() {
		
		//prenotazione con numero di posti disponibili insufficiente
		assertFalse(system.makeReservation(user, show, 100));
		//prenotazione con numero di posti da prenotare < 1
		assertFalse(system.makeReservation(user, show, 0));
		//due prenotazioni effettuate con successo
		assertTrue(system.makeReservation(user, show, 5));
		assertTrue(system.makeReservation(user, show, 2));
	}

	@Test
	public void testBuyReservation() {
		
		system.makeReservation(user, show, 2); 
			
		assertTrue(system.buyReservation(user, user.getReservations().get(0), PAYPAL));
		//metodo di pagamento non accettabile
		assertFalse(system.buyReservation(user, user.getReservations().get(0), 5));
	}

	@Test
	public void testDeleteReservation() {
		system.makeReservation(user, show, 2);

		assertTrue(system.deleteReservation(user, user.getReservations().get(0)));
		
	}

	@Test
	public void testGetFilmShowList() {
		ArrayList<Spettacolo> returned = new ArrayList<Spettacolo>();
		returned.add(show);
		Film noShowFilm = new Film("Titanic", "Peter Jackson", new ArrayList<String>(), "Drammatico", "Trama", 190, new ArrayList<String>());
		
		assertEquals(system.searchFilmShowList(film, Calendar.getInstance()), returned);
		assertEquals(system.searchFilmShowList(noShowFilm, Calendar.getInstance()), new ArrayList<Spettacolo>());
	}
 
	@Test
	public void testGetCinemaShowList() {
		ArrayList<Spettacolo> returned = new ArrayList<Spettacolo>();
		returned.add(show);
		returned.add(show2);
		
		assertEquals(system.getCinemaShowList(cinema, date), returned);
	}

	@Test
	public void testShowDiscountShow() {
		//nel circuito cienema è presente solo uno spettacolo scontato
		assertEquals(system.getDiscountShowList().size(), 1);
	}

	@Test
	public void testShowDiscountShowCinema() {
		//nel cienema è presente solo uno spettacolo scontato
		assertEquals(system.getDiscountShowList(cinema).size(), 1);
	}

}
