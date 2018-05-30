package myclasses;
import java.util.Calendar;


/**
 * 
 * Classe Pagamento
 * <p>
 * Classe che rappresenta il Pagamento. Comprende il metodo che simula l'operazione di pagamento 
 * come specificato nell'SDD.
 * 
 * @version 1.0
 * @author Sorzana
 *
 */
public class Pagamento {

	private static final int PAYPAL = 30;
	private static final int CREDIT_CARD = 31;
	
	public static int receiptsCounter;
	
	private int paymentId;
	private int paymentMethod;
	private float cost;
	private Calendar date;
	
	/**
	 * Costruttore di Pagamento: istanzia un nuovo oggetto Pagamento, impostando i parametri
	 * 
	 * @param paymentMethod
	 * 		tipologia del pagamento
	 * @param cost
	 * 		costo totale del pagamento
	 */
	public Pagamento(int paymentMethod, float cost) {
		this.paymentId = ++receiptsCounter;
		this.paymentMethod = paymentMethod;
		this.cost = cost;
		this.date = Calendar.getInstance();
	}
	
	/**
	 * Metodo utilizzato per restituire l'ID del pagamento
	 * 
	 * @return paymentId
	 */
	public int getPaymentId() {
		return paymentId;
	}
	
	/**
	 * Metodo utilizzato per restituire il metodo di pagamento
	 * 
	 * @return paymentMethod
	 */
	public int getPaymentMethod() {
		return paymentMethod;
	}
	
	/**
	 * Metodo utilizzato per restituire il costo totale del pagamento
	 * 
	 * @return cost
	 */
	public float getCost() {
		return cost; 
	}
	
	/**
	 * Metodo utilizzato per restituire la data del pagamento
	 * 
	 * @return date
	 */
	public Calendar getDate() {
		return date;
	}
	

	
	/**
	 * Metodo utilizzato per stampare i dati del singolo pagamento
	 * Mostrando: l'ID, il metodo, il costo totale, la data
	 */
	public void showPaymentInfo() {
		System.out.println("ID : " + paymentId + "\n" +
							"Metodo di Pagamento : " + paymentMethod + "\n" +
							"Costo Totale : " + cost + "\n" + 
							"Data : " + date.get(Calendar.DAY_OF_MONTH) + "/" +
										(date.get(Calendar.MONTH) + 1) + "/" + 
										date.get(Calendar.YEAR) + " - " +
										date.get(Calendar.HOUR_OF_DAY) + ":" +
										date.get(Calendar.MINUTE) + "\n");
	}
	
	/**
	 * Metodo utilizzato per il pagamento
	 * 
	 * @param buyer
	 * 		utente che esegue il pagamento 
	 * @param reservation
	 * 		prenotazione da acuistare
	 * @param paymentMethodP
	 * 		tipologia di pagamento scelta dall'utente
	 * @return true se il pagamento avviene, false in caso contrario
	 */
	public boolean payReservation(){
		
		if(paymentMethod != PAYPAL && paymentMethod != CREDIT_CARD)
			return false;
		else
			return true;
		
	}
	
}
