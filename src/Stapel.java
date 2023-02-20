public class Stapel<T>{
    private StapelKnoop bovenkant;
    private class StapelKnoop {

        private T waarde;
        private StapelKnoop volgende;

        private StapelKnoop(T waarde,StapelKnoop volgende){
            this.waarde = waarde;
            this.volgende = volgende;
        }
    }

    public Stapel<T> duw(T w) {
        bovenkant = new StapelKnoop(w, bovenkant);
        return this;
    }
    public T pak() {
        if (bovenkant == null){
            throw new IllegalArgumentException("stapel is leeg");
        }
        T data = bovenkant.waarde;
        bovenkant = bovenkant.volgende;
        return data;
    }
}