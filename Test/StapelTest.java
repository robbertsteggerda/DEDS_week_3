import org.junit.jupiter.api.*;

public class StapelTest {
    //Ik heb hier vier tests die eigenlijk hetzelfde doen maar met verschillende datatypes (waaronder ook null).
    //Ik test "duw" en "pak" allebei in één test omdat ik zonder "pak" niet bij de data in de stapel kan.
    @Test
    public void testDuw1() throws LegeStapelException {
        Stapel stapel = new Stapel();
        stapel.duw(1);
        Assertions.assertEquals(1,stapel.pak());
        }
        @Test
        public void testDuw123() throws LegeStapelException {
        Stapel stapel = new Stapel();
        stapel.duw(1).duw(2).duw(3);
        Assertions.assertEquals(3,stapel.pak());
        Assertions.assertEquals(2,stapel.pak());
        Assertions.assertEquals(1,stapel.pak());
    }

    @Test
    public void testDuwNull() throws LegeStapelException{
        Stapel stapel = new Stapel();
        stapel.duw(null);
        Assertions.assertEquals(null,stapel.pak());
    }

    @Test
    public void testDuwPietBob() throws LegeStapelException{
        Stapel stapel = new Stapel();
        stapel.duw("piet").duw("bob");
        Assertions.assertEquals("bob",stapel.pak());
        Assertions.assertEquals("piet",stapel.pak());
    }
}
