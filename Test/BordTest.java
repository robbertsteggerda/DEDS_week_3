import org.junit.jupiter.api.*;

public class BordTest {
    @Test
    public void testGetCoordinaat(){
        Bord bord = new Bord(7,7);
        bord.setWaarde(0,0,'p');
        Assertions.assertEquals('p',bord.getWaarde(0,0));
    }
    @Test
    public void testIsBuitenSpeelveld(){
        Assertions.assertTrue(Bord.isBuitenSpeelveld(-1));
        Assertions.assertTrue(Bord.isBuitenSpeelveld(7));
        Assertions.assertFalse(Bord.isBuitenSpeelveld(0));
        Assertions.assertFalse(Bord.isBuitenSpeelveld(3));
        Assertions.assertFalse(Bord.isBuitenSpeelveld(6));
    }
}
