package test_funzionali;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ SS1Pagare.class, UC10Deautenticarsi.class, UC11GestireIlProprioProfilo.class,
		UC12PrenotareUnoSpettacolo.class, UC13PagareLaPrenotazioneDiUnoSpettacolo.class, UC14RecensireUnCinema.class,
		UC15GestirePrenotazioni.class, UC2CercareUnCinema.class, UC33VisualizzareProgrammazioneCinema.class,
		UC36VisualizzareRecensioniFilm.class, UC37RecensireUnFilm.class, UC3ConsultareUnaSchedaCinema.class,
		UC4CercareUnFilm.class, UC5ConsultareUnaSchedaFilm.class, UC6VisualizzareLaProgrammazioneFilm.class,
		UC7VisualizzareRecensioniCinema.class, UC8VisualizzareScontiDisponibili.class, UC9Autenticarsi.class })
public class AllTests {

}
