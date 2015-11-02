import java.util.List;
import org.junit.Test;
import com.mielewczykl.jdbc.model.Klasztor;
import com.mielewczykl.jdbc.model.KlasztorManager;
import static org.junit.Assert.*;


public class KlasztorManagerTest {

    KlasztorManager klasztorManager = new KlasztorManager();

    private final static String NAME_1 = "Zenek";
    private final static int YOB_1 = 1945;

    @Test
    public void checkConnection(){
        assertNotNull(klasztorManager.getConnection());
    }

    @Test
    public void checkAdding(){

        Klasztor klasztor = new Klasztor(NAME_1, YOB_1);

        klasztorManager.clearPersons();
        assertEquals(1, klasztorManager.addPerson(klasztor));

        List<Klasztor> klasztors = klasztorManager.getAllPersons();
        Klasztor klasztorRetrieved = klasztors.get(0);

        assertEquals(NAME_1, klasztorRetrieved.getName());
        assertEquals(YOB_1, klasztorRetrieved.getYob());

    }
}
