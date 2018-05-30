package test_strutturali;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import myclasses.Cinema;
import myclasses.Film;
import myclasses.Pagamento;
import myclasses.Prenotazione;
import myclasses.Sala;
import myclasses.Spettacolo;
import myclasses.Utente;

public class UtenteTest {

	Utente user; 
	Calendar dateOfBirth = Calendar.getInstance();
	@Before
	public void setUp() throws Exception {
		user = new Utente("Mario", "Rossi", dateOfBirth, "mario.rossi@gmail.com", "marioRossi", "ciao", "12341234123413");
	}


	@Test
	public void testUtente() {
		assertNotNull(user);
	}

	@Test
	public void testGetName() {
		assertEquals(user.getName(), "Mario");
	}

	@Test
	public void testGetSurname() {
		assertEquals(user.getSurname(), "Rossi");
	}

	@Test
	public void testGetDateOfBirth() {
		assertEquals(user.getDateOfBirth(), dateOfBirth);
	}

	@Test
	public void testGetEmail() {
		assertEquals(user.getEmail(), "mario.rossi@gmail.com");
	}

	@Test
	public void testGetUsername() {
		assertEquals(user.getUsername(), "marioRossi");
	}

	@Test
	public void testGetPassword() {
		assertEquals(user.getPassword(), "ciao");
	}

	@Test
	public void testIsLoggedIn() {
		assertFalse(user.isLoggedIn());
	}

	@Test
	public void testGetPaymets() {
		assertEquals(user.getPaymets().size(), 0);
	}

	@Test
	public void testGetCreditCard() {
		assertEquals(user.getCreditCard(), "12341234123413");
	}

	@Test
	public void testGetReservations() {
		assertEquals(user.getReservations().size(), 0);
	}

	@Test
	public void testShowReservations() {
		//lista di prenotazioni vuota
		user.showReservations();
		
		//creo il necessario per creare delle prenotazioni
		Film film = new Film("Avatar", "Peter Jackson", new ArrayList<String>(), "Fantasy", "Trama", 180, new ArrayList<String>());
		Cinema cinema = new Cinema("Odeon", "via Roma", "Genova", 10);
		int cinemaRoomNumber = 2;
		Sala sala = new Sala(cinemaRoomNumber, 10, 10);
		cinema.getCinemaRooms().put(sala.getCinemaRoom(), sala);
		Spettacolo show = new Spettacolo(cinema, cinemaRoomNumber, film, (float) 7, Calendar.getInstance());

		//creo due prenotazioni una non pagata e l'altra pagata
		Prenotazione notPaid = new Prenotazione(show, new ArrayList<String>());
		user.getReservations().add(notPaid);
		Prenotazione paid = new Prenotazione(show, new ArrayList<String>());
		paid.setPaid(true);
		user.getReservations().add(paid);

		//mostro le recensioni divise in pagate e non 
		user.showReservations();
	}
	
	@Test
	public void testGetUnpaidReservations() {
		user.showReservations();
		
		Film film = new Film("Avatar", "Peter Jackson", new ArrayList<String>(), "Fantasy", "Trama", 180, new ArrayList<String>());
		Cinema cinema = new Cinema("Odeon", "via Roma", "Genova", 10);
		int cinemaRoomNumber = 2;
		Sala sala = new Sala(cinemaRoomNumber, 10, 10);
		cinema.getCinemaRooms().put(sala.getCinemaRoom(), sala);
		Spettacolo show = new Spettacolo(cinema, cinemaRoomNumber, film, (float) 7, Calendar.getInstance());

		Prenotazione notPaid = new Prenotazione(show, new ArrayList<String>());
		user.getReservations().add(notPaid);
		Prenotazione paid = new Prenotazione(show, new ArrayList<String>());
		paid.setPaid(true);
		user.getReservations().add(paid);
		
		assertEquals(user.getUnpaidReservations().size(), 1);
	}

	@Test
	public void testGetPaidReservations() {
		user.showReservations();
		
		Film film = new Film("Avatar", "Peter Jackson", new ArrayList<String>(), "Fantasy", "Trama", 180, new ArrayList<String>());
		Cinema cinema = new Cinema("Odeon", "via Roma", "Genova", 10);
		int cinemaRoomNumber = 2;
		Sala sala = new Sala(cinemaRoomNumber, 10, 10);
		cinema.getCinemaRooms().put(sala.getCinemaRoom(), sala);
		Spettacolo show = new Spettacolo(cinema, cinemaRoomNumber, film, (float) 7, Calendar.getInstance());

		Prenotazione notPaid = new Prenotazione(show, new ArrayList<String>());
		user.getReservations().add(notPaid);
		Prenotazione paid = new Prenotazione(show, new ArrayList<String>());
		paid.setPaid(true);
		user.getReservations().add(paid);
		
		assertEquals(user.getPaidReservations().size(), 1);
	}

	@Test
	public void testModifyProfile() {
		Calendar newDate = Calendar.getInstance();
		newDate.set(2018,1,23);
		assertTrue(user.modifyProfile("Massimo", "Verdi", newDate, "password", "0000111122223333"));
		assertFalse(user.modifyProfile("", "Verdi", newDate, "password", "0000111122223333"));
		assertFalse(user.modifyProfile("Massimo", "", newDate, "password", "0000111122223333"));
		assertFalse(user.modifyProfile("Massimo", "Verdi", null, "password", "0000111122223333"));
		assertFalse(user.modifyProfile("Massimo", "Verdi", newDate, "", "0000111122223333"));
		assertFalse(user.modifyProfile("Massimo", "Verdi", newDate, "password", ""));
	}

	@Test
	public void testShowUserInfo() {
		user.getPaymets().add(new Pagamento(30, (float) 100));
		
		user.showUserInfo();
	}

}
