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
        assertNotNull(klasztorManager.polaczenie());
    }

    @Test
    public void sprawdzDodaj()
    {
        ReligiaManager rm = new ReligiaManager();
        Religia rel = rm.dajReligie(0);

        if (rel == null)
        {
            rel = new Religia("string", "string");
            assertEquals(1, rm.dodaj(rel));
            rel = rm.dajReligie(0);
        }

        Klasztor klasztor = new Klasztor(rel, nazwa, kontakt);

        klasztorManager.usunWszystko();

        assertEquals(1, klasztorManager.dodaj(klasztor));

        List<Klasztor> klasztory = klasztorManager.dajWszystkieDane();
        Klasztor dodanyKlasztor = klasztory.get(0);

        assertEquals(rel.getId(), dodanyKlasztor.getReligia().getId());
        assertEquals(rel.getReligia(), dodanyKlasztor.getReligia().getReligia());
        assertEquals(rel.getOpis(), dodanyKlasztor.getReligia().getOpis());
        assertEquals(nazwa, dodanyKlasztor.getNazwa());
        assertEquals(kontakt, dodanyKlasztor.getKontakt());

    }

    @Test
    public void sprawdzEdycja()
    {
        String nazwa2 = "string2";
        String kontakt2 = "string2";

        ReligiaManager rm = new ReligiaManager();
        Religia rel = rm.dajReligie(0);

        if (rel == null)
        {
            rel = new Religia("string", "string");
            assertEquals(1, rm.dodaj(rel));
            rel = rm.dajReligie(0);
        }

        Klasztor klasztor = new Klasztor(rel, nazwa, kontakt);

        Religia rel2 = new Religia(nazwa2,  kontakt2);

        klasztorManager.usunWszystko();

        assertEquals(1, klasztorManager.dodaj(klasztor));

        klasztor = klasztorManager.dajKlasztor(0);

        assertEquals(1, rm.dodaj(rel2));
        rel2 = rm.dajReligie(1);

        klasztorManager.edytuj(klasztor, rel2, nazwa2, kontakt2);

        List<Klasztor> klasztory = klasztorManager.dajWszystkieDane();
        Klasztor edytowanyKlasztor = klasztory.get(0);

        assertEquals(rel2.getId(), edytowanyKlasztor.getReligia().getId());
        assertEquals(rel2.getReligia(), edytowanyKlasztor.getReligia().getReligia());
        assertEquals(rel2.getOpis(), edytowanyKlasztor.getReligia().getOpis());
        assertEquals(nazwa2, edytowanyKlasztor.getNazwa());
        assertEquals(kontakt2, edytowanyKlasztor.getKontakt());

    }

    @Test
    public void sprawdzUsun()
    {
        ReligiaManager rm = new ReligiaManager();
        Religia rel = rm.dajReligie(0);

        if (rel == null)
        {
            rel = new Religia("string", "string");
            assertEquals(1, rm.dodaj(rel));
            rel = rm.dajReligie(0);
        }

        Klasztor klasztor = new Klasztor(rel, nazwa, kontakt);

        klasztorManager.usunWszystko();

        assertEquals(1, klasztorManager.dodaj(klasztor));
        assertEquals(0, klasztorManager.usun(klasztor));

    }

    @Test
         public void sprawdzDajWszystkieDane(){

        ReligiaManager rm = new ReligiaManager();
        Religia rel = rm.dajReligie(0);

        if (rel == null)
        {
            rel = new Religia("string", "string");
            assertEquals(1, rm.dodaj(rel));
            rel = rm.dajReligie(0);
        }

        Klasztor klasztor = new Klasztor(rel, nazwa, kontakt);

        klasztorManager.usunWszystko();

        klasztorManager.dodaj(klasztor);
        klasztorManager.dodaj(klasztor);
        klasztorManager.dodaj(klasztor);
        klasztorManager.dodaj(klasztor);
        klasztorManager.dodaj(klasztor);

        List<Klasztor> klasztory = klasztorManager.dajWszystkieDane();

        assertEquals(5, klasztory.size());

    }

    @Test
    public void sprawdzUsunWszystko(){

        ReligiaManager rm = new ReligiaManager();
        Religia rel = rm.dajReligie(0);

        if (rel == null)
        {
            rel = new Religia("string", "string");
            assertEquals(1, rm.dodaj(rel));
            rel = rm.dajReligie(0);
        }

        Klasztor klasztor = new Klasztor(rel, nazwa, kontakt);

        klasztorManager.usunWszystko();

        klasztorManager.dodaj(klasztor);
        klasztorManager.dodaj(klasztor);
        klasztorManager.dodaj(klasztor);
        klasztorManager.dodaj(klasztor);
        klasztorManager.dodaj(klasztor);

        List<Klasztor> klasztory = klasztorManager.dajWszystkieDane();

        assertEquals(5, klasztory.size());

        klasztorManager.usunWszystko();

        klasztory = klasztorManager.dajWszystkieDane();

        assertEquals(0, klasztory.size());

    }

    @Test
    public void sprawdzPrzypiszReligie(){

        ReligiaManager rm = new ReligiaManager();
        Religia rel = rm.dajReligie(0);

        if (rel == null)
        {
            rel = new Religia("string", "string");
            assertEquals(1, rm.dodaj(rel));
            rel = rm.dajReligie(0);
        }

        Klasztor klasztor = new Klasztor(rel, nazwa, kontakt);

        klasztorManager.usunWszystko();

        klasztorManager.dodaj(klasztor);

        List<Klasztor> klasztory = klasztorManager.dajWszystkieDane();

        klasztor = klasztory.get(0);

        assertEquals(klasztor.getReligia().getId(), klasztorManager.przypiszReligie(klasztor).getId());
        assertEquals(klasztor.getReligia().getReligia(), klasztorManager.przypiszReligie(klasztor).getReligia());
        assertEquals(klasztor.getReligia().getOpis(), klasztorManager.przypiszReligie(klasztor).getOpis());

    }
}
