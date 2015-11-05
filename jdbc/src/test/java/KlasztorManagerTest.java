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
        klasztorManager.UsunWszystko();

        ReligiaManager rm = new ReligiaManager();
        Religia rel = rm.DajPierwszaReligie();

        if (rel == null)
        {
            rel = new Religia("string", "string");
            assertEquals(1, rm.Dodaj(rel));
            rel = rm.DajPierwszaReligie();
        }

        Klasztor klasztor = new Klasztor(rel, nazwa, kontakt);

        assertEquals(1, klasztorManager.Dodaj(klasztor));

        List<Klasztor> klasztory = klasztorManager.DajWszystkieDane();
        Klasztor dodanyKlasztor = klasztory.get(0);

        assertEquals(nazwa, dodanyKlasztor.getNazwa());
        assertEquals(kontakt, dodanyKlasztor.getKontakt());

    }

    @Test
    public void sprawdzUsuwanie()
    {
        klasztorManager.UsunWszystko();
        ReligiaManager rm = new ReligiaManager();
        Religia rel = rm.DajPierwszaReligie();

        if (rel == null)
        {
            rel = new Religia("string", "string");
            assertEquals(1, rm.Dodaj(rel));
            rel = rm.DajPierwszaReligie();
        }

        Klasztor klasztor = new Klasztor(rel, nazwa, kontakt);

        assertEquals(1, klasztorManager.Dodaj(klasztor));
        assertEquals(0, klasztorManager.Usun(klasztor));;

    }
}
