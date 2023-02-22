import org.junit.jupiter.api.*;

public class SpelTest {
    @Test
    public void testIsBuitenSpeelveld(){
        Assertions.assertTrue(Spel.isBuitenSpeelveld(-1));
        Assertions.assertTrue(Spel.isBuitenSpeelveld(7));
        Assertions.assertFalse(Spel.isBuitenSpeelveld(0));
        Assertions.assertFalse(Spel.isBuitenSpeelveld(3));
        Assertions.assertFalse(Spel.isBuitenSpeelveld(6));
    }
}
