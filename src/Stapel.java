public class Stapel<T>{
    private Stapel<T> vorige;
    private T waarde;
    
    Stapel(Stapel<T> vorige,T waarde){
        this.vorige = vorige;
        this.waarde = waarde;
    }

    public void duw(T waarde){
        this.vorige = new Stapel<T>(this.vorige,this.waarde);
        this.waarde = waarde;
    }

    public T pak(){
        if(this.isLeeg()){
            throw new IllegalArgumentException("Stapel is leeg");
        }

        T bovenkant = this.waarde;
        this.waarde = vorige.waarde;
        this.vorige = this.vorige.vorige;

        return bovenkant;
    }

    private boolean isLeeg() {
        return this.vorige == null;
    }
}
