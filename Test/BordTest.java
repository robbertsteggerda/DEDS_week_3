import org.junit.jupiter.api.*;

public class BordTest {
    @Test
    public void testGetCoordinaat(){
        Bord bord = new Bord(7,7);
        bord.setWaarde(0,0,'p');
        Assertions.assertEquals('p',bord.getWaarde(0,0));
    }

}
