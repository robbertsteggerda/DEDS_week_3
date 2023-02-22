public class Bord {
    private char[][] speelveld;

    Bord(int x, int y){
        this.speelveld = new char[x][y];
    }

    public char getWaarde(int x, int y){
        return this.speelveld[x][y];
    }

    public void setWaarde(int x, int y, char waarde){
        this.speelveld[x][y] = waarde;
    }

    public char[][] getSpeelveld(){
        return this.speelveld;
    }

    public static void startSpel() {
        Bord bord = new Bord(7,7);
        zetBeginStand(bord);


        System.out.println("The Great Outdoors Outside");
        for (int x = 0; x < 7; x++) {
            System.out.print(x + " ");
            for (int y = 0; y < 7; y++) {
                System.out.print(bord.getSpeelveld()[x][y] + " ");
            }
            System.out.println();
        }
        System.out.println("  0 1 2 3 4 5 6");
    }

    private static void zetBeginStand(Bord bord){
        for (int x = 0; x < 7; x++) {
            for (int y = 0; y < 7; y++) {
                bord.setWaarde(x,y,'x');
            }
        }
        bord.setWaarde(6,0,'H');
        bord.setWaarde(6,1,'H');
        bord.setWaarde(5,0,'H');
        bord.setWaarde(5,1,'H');

        bord.setWaarde(0,5,'B');
        bord.setWaarde(0,6,'B');
        bord.setWaarde(1,5,'B');
        bord.setWaarde(1,6,'B');
    }
}

