import org.junit.jupiter.api.*;

public class StapelTest {
    @Test
    public void testDuw() {
        Stapel stapel = new Stapel();
        stapel.duw(1).duw(3).duw("bob");
        for (int i = 0; i < 5; i++) {
            System.out.print(stapel.pak() + ", ");
        }
    }
    @Test
    public void testPak() {

    }
}
