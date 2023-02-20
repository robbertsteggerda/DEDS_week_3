public class Stapel<T>{
    private StapelKnoop bovenkant;
    private class StapelKnoop {

        private T waarde;
        private StapelKnoop volgende;

        private StapelKnoop(T waarde, StapelKnoop volgende) {
            waarde = this.waarde;
            volgende = this.volgende;
        }
    }
    private Stapel<T> vorige;
    private T waarde;

    public Stapel<T> duw(T waarde) {
        bovenkant = new StapelKnoop(waarde, bovenkant);
        return this;
    }

    public T pak() {
        T data = bovenkant.waarde;
        bovenkant = bovenkant.volgende;
        return data;
    }
}