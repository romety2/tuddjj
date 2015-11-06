import java.util.List;
import org.junit.Test;
import com.mielewczykl.jdbc.model.Klasztor;
import com.mielewczykl.jdbc.model.KlasztorManager;
import com.mielewczykl.jdbc.model.Religia;
import com.mielewczykl.jdbc.model.ReligiaManager;
import static org.junit.Assert.*;

public class ReligiaManagerTest {

    ReligiaManager religiaManager = new ReligiaManager();

    private final static String religia = "string";
    private final static String opis = "string";

    @Test
    public void sprawdzPolaczenie()
    {
        assertNotNull(religiaManager.polaczenie());
    }

    @Test
    public void sprawdzDodaj(){

        Religia rel = new Religia(religia, opis);

        religiaManager.usunWszystko();
        assertEquals(1, religiaManager.dodaj(rel));

        List<Religia> religie = religiaManager.dajWszystkieDane();
        Religia dodanaReligia = religie.get(0);

        assertEquals(religia, dodanaReligia.getReligia());
        assertEquals(opis, dodanaReligia.getOpis());

    }

    @Test
    public void sprawdzEdycja(){

        Religia rel = new Religia(religia, opis);

        String religia2 = "string2";
        String opis2 = "string2";

        religiaManager.usunWszystko();

        assertEquals(1, religiaManager.dodaj(rel));

        rel = religiaManager.dajReligie(0);

        religiaManager.edytuj(rel, religia2, opis2);

        List<Religia> religie = religiaManager.dajWszystkieDane();
        Religia edytowanaReligia = religie.get(0);

        assertEquals(religia2, edytowanaReligia.getReligia());
        assertEquals(opis2, edytowanaReligia.getOpis());

    }

    @Test
    public void sprawdzUsun(){

        Religia rel = new Religia(religia, opis);

        religiaManager.usunWszystko();

        assertEquals(1, religiaManager.dodaj(rel));
        assertEquals(0, religiaManager.usun(rel));

    }

    @Test
    public void sprawdzDajWszystkieDane(){

        Religia rel = new Religia(religia, opis);

        religiaManager.usunWszystko();

        religiaManager.dodaj(rel);
        religiaManager.dodaj(rel);
        religiaManager.dodaj(rel);
        religiaManager.dodaj(rel);
        religiaManager.dodaj(rel);

        List<Religia> religie = religiaManager.dajWszystkieDane();

        assertEquals(5, religie.size());

    }

    @Test
    public void sprawdzUsunWszystko(){

        Religia rel = new Religia(religia, opis);

        religiaManager.usunWszystko();

        religiaManager.dodaj(rel);
        religiaManager.dodaj(rel);
        religiaManager.dodaj(rel);
        religiaManager.dodaj(rel);
        religiaManager.dodaj(rel);

        List<Religia> religie = religiaManager.dajWszystkieDane();

        assertEquals(5, religie.size());

        religiaManager.usunWszystko();

        religie = religiaManager.dajWszystkieDane();

        assertEquals(0, religie.size());

    }

    @Test
    public void sprawdzPobranieKlasztorow(){

        String religia2 = "string2";
        String opis2 = "string2";
        String nazwa1 = "string3";
        String kontakt1 = "string3";
        String nazwa2 = "string4";
        String kontakt2 = "string4";
        String nazwa3 = "string5";
        String kontakt3 = "string5";
        String nazwa4 = "string6";
        String kontakt4 = "string6";

        Religia rel = new Religia(religia, opis);
        Religia rel2 = new Religia(religia2, opis2);

        religiaManager.usunWszystko();
        assertEquals(1, religiaManager.dodaj(rel));
        rel = religiaManager.dajReligie(0);
        assertEquals(1, religiaManager.dodaj(rel2));
        rel2 = religiaManager.dajReligie(1);

        KlasztorManager km = new KlasztorManager();
        km.usunWszystko();
        Klasztor klasztor1 = new Klasztor(rel, nazwa1, kontakt1);
        assertEquals(1, km.dodaj(klasztor1));
        Klasztor klasztor2 = new Klasztor(rel2, nazwa2, kontakt2);
        assertEquals(1, km.dodaj(klasztor2));
        Klasztor klasztor3 = new Klasztor(rel, nazwa3, kontakt3);
        assertEquals(1, km.dodaj(klasztor3));
        Klasztor klasztor4 = new Klasztor(rel2, nazwa4, kontakt4);
        assertEquals(1, km.dodaj(klasztor4));

        List<Klasztor> klasztory = religiaManager.pobierzKlasztory(rel);
        for(int i = 0; i < klasztory.size(); i++) {
            assertEquals(rel.getId(), klasztory.get(i).getReligia().getId());
            assertEquals(rel.getReligia(), klasztory.get(i).getReligia().getReligia());
            assertEquals(rel.getOpis(), klasztory.get(i).getReligia().getOpis());
        }

        List<Klasztor> klasztory2 = religiaManager.pobierzKlasztory(rel2);
        for(int i = 0; i < klasztory2.size(); i++) {
            assertEquals(rel2.getId(), klasztory2.get(i).getReligia().getId());
            assertEquals(rel.getReligia(), klasztory.get(i).getReligia().getReligia());
            assertEquals(rel.getOpis(), klasztory.get(i).getReligia().getOpis());
        }

    }

    @Test
    public void sprawdzUsunKlasztory(){

        String religia2 = "string2";
        String opis2 = "string2";
        String nazwa1 = "string3";
        String kontakt1 = "string3";
        String nazwa2 = "string4";
        String kontakt2 = "string4";
        String nazwa3 = "string5";
        String kontakt3 = "string5";
        String nazwa4 = "string6";
        String kontakt4 = "string6";

        Religia rel = new Religia(religia, opis);
        Religia rel2 = new Religia(religia2, opis2);

        religiaManager.usunWszystko();
        assertEquals(1, religiaManager.dodaj(rel));
        rel = religiaManager.dajReligie(0);
        assertEquals(1, religiaManager.dodaj(rel2));
        rel2 = religiaManager.dajReligie(1);

        KlasztorManager km = new KlasztorManager();
        km.usunWszystko();
        Klasztor klasztor1 = new Klasztor(rel, nazwa1, kontakt1);
        assertEquals(1, km.dodaj(klasztor1));
        Klasztor klasztor2 = new Klasztor(rel2, nazwa2, kontakt2);
        assertEquals(1, km.dodaj(klasztor2));
        Klasztor klasztor3 = new Klasztor(rel, nazwa3, kontakt3);
        assertEquals(1, km.dodaj(klasztor3));
        Klasztor klasztor4 = new Klasztor(rel2, nazwa4, kontakt4);
        assertEquals(1, km.dodaj(klasztor4));

        List<Klasztor> klasztory1 = km.dajWszystkieDane();

        assertEquals(4, klasztory1.size());

        religiaManager.usunKlasztory(rel);

        List<Klasztor> klasztory2 = km.dajWszystkieDane();

        assertEquals(2, klasztory2.size());
        for(int i = 0; i < klasztory2.size(); i++)
            assertEquals(rel2.getId(), klasztory2.get(i).getReligia().getId());

        }

}
