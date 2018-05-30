package test_funzionali;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import myclasses.Sistema;
import myclasses.Utente;

public class UC9Autenticarsi {

	Sistema system;
	Utente user;
 
	@Before
	public void setUp() throws Exception {
		system = new Sistema();
		user = new Utente("Mario", "Rossi", Calendar.getInstance(), "mario.rossi@gmail.com", "marioRossi", "ciao", "0000111122223333");
		
		system.getUserTable().put(user.getUsername(), user);
	}

	//Scenario Principale: l'Utente riesce ad autenticarsi
	@Test
	public void UC9test1() {
		assertTrue(system.login(user.getUsername(), user.getPassword()));
		assertTrue(system.getUserTable().get(user.getUsername()).isLoggedIn());
	}

		//Scenario Alternativo 3a: l'Utente annulla l'operazione
		@Test
		public void UC9test2() {
			assertFalse(system.getUserTable().get(user.getUsername()).isLoggedIn());
		}
		
		//Scenario Alternativo 4a: il Sistema non valida i dati inseriti
		@Test
		public void UC3test3() {
			assertFalse(system.login("NotRegisteredUser", "pwd"));
			assertFalse(system.login(user.getUsername(), "pwd_errata"));
			assertFalse(system.getUserTable().get(user.getUsername()).isLoggedIn());
		}
}
