package test_funzionali;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import myclasses.Pagamento;

public class SS1Pagare {

	int PAYPAL = 30;
	int CREDIT_CARD = 31;
	
	Pagamento payment;
	
	@Before
	public void setUp() throws Exception {
	}

	//Scenario Principale: l'Utente inserisce un metodo di pagamento accettato
	@Test
	public void SS1test1() {
		//pagamento legato a una prenotazione con metodo di pagamento valido
		payment = new Pagamento(PAYPAL, (float) 30.0);
		
		assertTrue(payment.payReservation());
	}
	
		//Scenario Alternativo: l'Utente non inserisce un metodo di pagamento accettato
		@Test
		public void SS1test2() {
			//pagamento legato a una prenotazione con metodo di pagamento non valido
			payment = new Pagamento(100, (float) 30.0);
			
			assertFalse(payment.payReservation());
		}

}
