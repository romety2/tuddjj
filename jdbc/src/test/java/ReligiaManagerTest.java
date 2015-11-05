import java.util.List;
import org.junit.Test;
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
        assertNotNull(religiaManager.getConnection());
    }

    @Test
    public void sprawdzDodawanie(){

        Religia rel = new Religia(religia, opis);

        religiaManager.UsunWszystko();
        assertEquals(1, religiaManager.Dodaj(rel));

        List<Religia> religie = religiaManager.DajWszystkieDane();
        Religia dodanaReligia = religie.get(0);

        assertEquals(religia, dodanaReligia.getReligia());
        assertEquals(opis, dodanaReligia.getOpis());

    }

    @Test
    public void sprawdzEdycje(){

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
    public void sprawdzUsuwanie(){

        religiaManager.UsunWszystko();

        Religia rel = new Religia(religia, opis);

        religiaManager.UsunWszystko();
        assertEquals(1, religiaManager.Dodaj(rel));
        assertEquals(0, religiaManager.Usun(rel));;

    }
}
