import java.util.ArrayList;

public class Coordinaat {
    int x;
    int y;
    ArrayList<Coordinaat> doelen;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public Coordinaat(int x, int y){
        this.x = x;
        this.y = y;
    }

    public ArrayList<Coordinaat> getDoelen() {
        return doelen;
    }

    public Coordinaat (int x, int y, ArrayList<Coordinaat> doelen){
        this.x = x;
        this.y = y;
        this.doelen = doelen;
    }

}
