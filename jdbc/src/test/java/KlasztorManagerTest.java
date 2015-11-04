import java.util.List;
import org.junit.Test;
import com.mielewczykl.jdbc.model.Klasztor;
import com.mielewczykl.jdbc.model.KlasztorManager;
import com.mielewczykl.jdbc.model.Religia;
import com.mielewczykl.jdbc.model.ReligiaManager;
import static org.junit.Assert.*;


public class KlasztorManagerTest {

    KlasztorManager klasztorManager = new KlasztorManager();

    private final static String nazwa = "string";
    private final static String kontakt = "string";

    @Test
    public void sprawdzPolaczenie()
    {
        assertNotNull(klasztorManager.getConnection());
    }

    @Test
    public void sprawdzDodawanie()
    {

        ReligiaManager rm = new ReligiaManager();
        Klasztor klasztor = new Klasztor(rm.DajPierwszaReligie(), nazwa, kontakt);

        klasztorManager.UsunWszystko();
        assertEquals(1, klasztorManager.DodajWartosc(klasztor));

        List<Klasztor> klasztory = klasztorManager.DajWszystkieDane();
        Klasztor pierwszyKlasztor = klasztory.get(0);


        assertEquals(nazwa, pierwszyKlasztor.getNazwa());
        assertEquals(kontakt, pierwszyKlasztor.getKontakt());

    }
}
