package test_funzionali;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import myclasses.Sistema;
import myclasses.Utente;

public class UC11GestireIlProprioProfilo {

	Sistema system;
	Utente user;
	Calendar dateOfBirth = Calendar.getInstance();
	Calendar newDate = Calendar.getInstance();
	
	@Before
	public void setUp() throws Exception {
		system = new Sistema();
		user = new Utente("Mario", "Rossi", dateOfBirth, "mario.rossi@gmail.com", "marioRossi", "ciao", "1111222233334444");
		
		system.getUserTable().put(user.getUsername(), user);
		
		newDate.set(1990, 6, 5);
	}


	//Scenario Principale: l'Utente vuole Gestire il proprio Profilo modificando i dati personali
	@Test
	public void UC11test1() {
		//Il Sistema mostra i dati che l'Utente pu� modificare
		user.showUserInfo();
		
		//L'Utente modifica i dati 
		assertTrue(user.modifyProfile("Massimo", "Verdi", newDate, "password", "0000111122223333"));
		
		//L'Utente visualizza i dati aggiornati
		user.showUserInfo();
	}
	
		//Scenario Alternativo 2a: l'Utente decide di visualizzare il proprio profilo
		@Test
		public void UC11test2() {
			user.showUserInfo();
		}
		
		//Scenario Alternativo 4a: l'Utente non inserisce i dati da modificare e annulla l'operazione
		@Test
		public void UC11test3() {
			user.showUserInfo();
			
			//dati non modificati
			assertEquals(user.getName(), "Mario");
			assertEquals(user.getSurname(), "Rossi");
			assertEquals(user.getDateOfBirth(), Calendar.getInstance());
			assertEquals(user.getPassword(), "ciao");
			assertEquals(user.getCreditCard(), "1111222233334444");
		}
		
		//Scenario Alternativo 7a: il Sistema non valida i dati inseriti per la modifica
		@Test
		public void UC11test5() {
			
			//dati da moidficare non accettati dal Sistema
			assertFalse(user.modifyProfile("", "Verdi", newDate, "newPwd", ""));
			
			//i dati non vengono aggiornati
			assertEquals(user.getName(), "Mario");
			assertEquals(user.getSurname(), "Rossi");
			assertEquals(user.getDateOfBirth(), dateOfBirth);
			assertEquals(user.getPassword(), "ciao");
			assertEquals(user.getCreditCard(), "1111222233334444");
		}
}
