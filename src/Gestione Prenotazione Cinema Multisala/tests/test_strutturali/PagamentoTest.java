package test_strutturali;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import myclasses.*
;
public class PagamentoTest {

	int PAYPAL = 30;
	int CREDIT_CARD = 31;
	
	Pagamento payment;
	Utente user;
	Prenotazione prenotazione;
	Calendar date = Calendar.getInstance();
	
	Pagamento receipt;
	Pagamento receipt2;
	Pagamento receipt3;
	
	static int counter;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		counter = Pagamento.receiptsCounter;
	}
	
	@Before
	public void setUp() throws Exception {
		payment = new Pagamento(PAYPAL, (float) 100);
		counter++;
	}

	
	@Test
	public void testPagamento() {
		assertNotNull(payment);
	}

	@Test
	public void testGetPaymentId() {
		 
		assertEquals(payment.getPaymentId(), counter);				
	}

	@Test
	public void testGetPaymentMethod() {
		assertEquals(payment.getPaymentMethod(),PAYPAL);
	}

	@Test
	public void testGetCost() {
		assertEquals((double) payment.getCost(), (double) 100, (double) 1);
	}

	@Test
	public void testGetDate() {
		assertEquals(payment.getDate(),date);
	}

	@Test
	public void testShowPaymentInfo() {
		payment.showPaymentInfo();
	}

	@Test
	public void testPayReservation() {
		//creo uno spettacolo e la relativa prenotazione
		
		user = new Utente("Mario", "Rossi", Calendar.getInstance(), "mario.rossi@gmail.com", "marioRossi", "ciao", "12341234123413");
		Cinema cinema = new Cinema("Odeon", "via Roma", "Genova", 10);
		cinema.getCinemaRooms().put(2, new Sala(2, 10, 10));
		Film film = new Film("Star Wars", "Pippo", new ArrayList<String>(), "Fantasy", "trama", 200, new ArrayList<String>());
		int cinemaRoom = 2;
		float ticketPrice = (float) 8.5;
		date = Calendar.getInstance();
		
		Spettacolo show = new Spettacolo(cinema, cinemaRoom, film, ticketPrice, date);
		
		prenotazione = new Prenotazione(show, new ArrayList<String>());
		
		//creo tre oggetti pagamento
		Pagamento receipt = new Pagamento(PAYPAL, prenotazione.getPrice());
		Pagamento receipt2 = new Pagamento(CREDIT_CARD, prenotazione.getPrice());
		Pagamento receipt3 = new Pagamento(5, prenotazione.getPrice());
		
		//devo aggiornare il counter perch√® ho creato tre nuovi elementi di 
		//cui devo tenere il conto
		counter = counter +3;
		
		//prenotazioni per cui il metodo di pagamento Ë accettato
		assertTrue(receipt.payReservation());
		assertTrue(receipt2.payReservation());
		//prenotazione per cui il metodo di pagamento non Ë accettato
		assertFalse(receipt3.payReservation());
		
	}

}
