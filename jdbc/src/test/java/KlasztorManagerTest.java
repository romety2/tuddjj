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
    public void checkConnection(){
        assertNotNull(klasztorManager.getConnection());
    }

    @Test
    public void checkAdding(){

        Klasztor klasztor = new Klasztor(nazwa, kontakt);

        klasztorManager.UsunWszystko();
        assertEquals(1, klasztorManager.DodajWartosc(klasztor));

        List<Klasztor> klasztory = klasztorManager.DajWszystkieDane();
        Klasztor klasztorRetrieved = klasztory.get(0);

        assertEquals(nazwa, klasztorRetrieved.getNazwa());
        assertEquals(kontakt, klasztorRetrieved.getKontakt());

    }
}
