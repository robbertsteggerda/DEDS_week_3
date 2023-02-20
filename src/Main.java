public class Main {
    public static void main(String[] args) {
        testDuw();
    }
    public static void testDuw() {
        Stapel stapel = new Stapel();
        stapel.duw(1).duw(3).duw("bob");
        for (int i = 0; i < 3; i++) {
            System.out.print(stapel.pak() + ", ");
        }
    }
}