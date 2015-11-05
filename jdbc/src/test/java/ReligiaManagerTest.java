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
        assertNotNull(religiaManager.Polaczenie());
    }

    @Test
    public void sprawdzDodaj(){

        Religia rel = new Religia(religia, opis);

        religiaManager.UsunWszystko();
        assertEquals(1, religiaManager.Dodaj(rel));

        List<Religia> religie = religiaManager.DajWszystkieDane();
        Religia dodanaReligia = religie.get(0);

        assertEquals(religia, dodanaReligia.getReligia());
        assertEquals(opis, dodanaReligia.getOpis());

    }

    @Test
    public void sprawdzEdycja(){

        Religia rel = new Religia(religia, opis);

        String religia2 = "string2";
        String opis2 = "string2";

        religiaManager.UsunWszystko();

        assertEquals(1, religiaManager.Dodaj(rel));

        rel = religiaManager.DajReligie(0);

        religiaManager.Edytuj(rel, religia2, opis2);

        List<Religia> religie = religiaManager.DajWszystkieDane();
        Religia edytowanaReligia = religie.get(0);

        assertEquals(religia2, edytowanaReligia.getReligia());
        assertEquals(opis2, edytowanaReligia.getOpis());

    }

    @Test
    public void sprawdzUsun(){

        Religia rel = new Religia(religia, opis);

        religiaManager.UsunWszystko();

        assertEquals(1, religiaManager.Dodaj(rel));
        assertEquals(0, religiaManager.Usun(rel));

    }

    @Test
    public void sprawdzDajWszystkieDane(){

        Religia rel = new Religia(religia, opis);

        religiaManager.UsunWszystko();

        religiaManager.Dodaj(rel);
        religiaManager.Dodaj(rel);
        religiaManager.Dodaj(rel);
        religiaManager.Dodaj(rel);
        religiaManager.Dodaj(rel);

        List<Religia> religie = religiaManager.DajWszystkieDane();

        assertEquals(5, religie.size());

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

        religiaManager.UsunWszystko();
        assertEquals(1, religiaManager.Dodaj(rel));
        rel = religiaManager.DajReligie(0);
        assertEquals(1, religiaManager.Dodaj(rel2));
        rel2 = religiaManager.DajReligie(1);

        KlasztorManager km = new KlasztorManager();
        km.UsunWszystko();
        Klasztor klasztor1 = new Klasztor(rel, nazwa1, kontakt1);
        assertEquals(1, km.Dodaj(klasztor1));
        Klasztor klasztor2 = new Klasztor(rel2, nazwa2, kontakt2);
        assertEquals(1, km.Dodaj(klasztor2));
        Klasztor klasztor3 = new Klasztor(rel, nazwa3, kontakt3);
        assertEquals(1, km.Dodaj(klasztor3));
        Klasztor klasztor4 = new Klasztor(rel2, nazwa4, kontakt4);
        assertEquals(1, km.Dodaj(klasztor4));

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
}
