public class Stapel<T>{

    private StapelKnoop bovenkant;
    private class StapelKnoop {

        private T waarde;
        private StapelKnoop volgende;

        private StapelKnoop(T waarde, StapelKnoop volgende) {
            waarde = waarde;
            volgende = volgende;
        }
    }

    private Stapel<T> vorige;
    private T waarde;
}