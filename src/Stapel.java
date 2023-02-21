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

    public Stapel<T> duw(T waarde) {
        bovenkant = new StapelKnoop(waarde, bovenkant);
        return this;
    }
    public T pak() throws LegeStapelException {
        if (bovenkant == null){
            throw new LegeStapelException();
        }
        T data = bovenkant.waarde;
        bovenkant = bovenkant.volgende;
        return data;
    }
}

class LegeStapelException extends Exception {
    public LegeStapelException(){
        super("Stapel is leeg");
    }
}