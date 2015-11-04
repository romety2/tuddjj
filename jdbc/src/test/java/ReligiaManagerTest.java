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
        assertEquals(1, religiaManager.DodajWartosc(rel));

        List<Religia> religie = religiaManager.DajWszystkieDane();
        Religia pierwszaReligia = religie.get(0);

        assertEquals(religia, pierwszaReligia.getReligia());
        assertEquals(opis, pierwszaReligia.getOpis());

    }
}
