package test_funzionali;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import myclasses.Sistema;
import myclasses.Utente;

public class UC10Deautenticarsi {

	Sistema system;
	Utente user;
	 
	@Before
	public void setUp() throws Exception {
		system = new Sistema();
		user = new Utente("Mario", "Rossi", Calendar.getInstance(), "mario.rossi@gmail.com", "marioRossi", "ciao", "0000111122223333");
		
		system.getUserTable().put(user.getUsername(), user);
		
		system.login(user.getUsername(), user.getPassword());
	}

	//Scenario Principale: l'Utente riesce ad deautenticarsi
	@Test
	public void UC10test1() {
		assertTrue(system.logout(user.getUsername()));
		assertFalse(system.getUserTable().get(user.getUsername()).isLoggedIn());
	}
	
		//Scenario Alternativo 3a: l'Utente annulla l'operazione
		@Test
		public void UC10test2() {
			assertTrue(system.getUserTable().get(user.getUsername()).isLoggedIn());
		}

}
