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
        Religia rel = rm.DajReligie(0);

        if (rel == null)
        {
            rel = new Religia("string", "string");
            assertEquals(1, rm.Dodaj(rel));
            rel = rm.DajReligie(0);
        }

        Klasztor klasztor = new Klasztor(rel, nazwa, kontakt);

        klasztorManager.UsunWszystko();

        assertEquals(1, klasztorManager.Dodaj(klasztor));

        List<Klasztor> klasztory = klasztorManager.DajWszystkieDane();
        Klasztor dodanyKlasztor = klasztory.get(0);

        assertEquals(rel.getId(), dodanyKlasztor.getReligia().getId());
        assertEquals(rel.getReligia(), dodanyKlasztor.getReligia().getReligia());
        assertEquals(rel.getOpis(), dodanyKlasztor.getReligia().getOpis());
        assertEquals(nazwa, dodanyKlasztor.getNazwa());
        assertEquals(kontakt, dodanyKlasztor.getKontakt());

    }

    @Test
    public void sprawdzEdycje()
    {
        String nazwa2 = "string2";
        String kontakt2 = "string2";

        ReligiaManager rm = new ReligiaManager();
        Religia rel = rm.DajReligie(0);

        if (rel == null)
        {
            rel = new Religia("string", "string");
            assertEquals(1, rm.Dodaj(rel));
            rel = rm.DajReligie(0);
        }

        Klasztor klasztor = new Klasztor(rel, nazwa, kontakt);

        Religia rel2 = new Religia(nazwa2,  kontakt2);

        klasztorManager.UsunWszystko();

        assertEquals(1, klasztorManager.Dodaj(klasztor));

        klasztor = klasztorManager.DajKlasztor(0);

        assertEquals(1, rm.Dodaj(rel2));
        rel2 = rm.DajReligie(1);

        klasztorManager.Edytuj(klasztor, rel2, nazwa2, kontakt2);

        List<Klasztor> klasztory = klasztorManager.DajWszystkieDane();
        Klasztor edytowanyKlasztor = klasztory.get(0);

        /* assertEquals(rel2.getId(), edytowanyKlasztor.getReligia().getId());
        assertEquals(rel2.getReligia(), edytowanyKlasztor.getReligia().getReligia());
        assertEquals(rel2.getOpis(), edytowanyKlasztor.getReligia().getOpis()); */
        assertEquals(nazwa2, edytowanyKlasztor.getNazwa());
        assertEquals(kontakt2, edytowanyKlasztor.getKontakt());

    }

    @Test
    public void sprawdzUsuwanie()
    {
        ReligiaManager rm = new ReligiaManager();
        Religia rel = rm.DajReligie(0);

        if (rel == null)
        {
            rel = new Religia("string", "string");
            assertEquals(1, rm.Dodaj(rel));
            rel = rm.DajReligie(0);
        }

        Klasztor klasztor = new Klasztor(rel, nazwa, kontakt);

        klasztorManager.UsunWszystko();

        assertEquals(1, klasztorManager.Dodaj(klasztor));
        assertEquals(0, klasztorManager.Usun(klasztor));;

    }
}
