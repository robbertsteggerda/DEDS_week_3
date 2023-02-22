public class Bord {
    private char[][] speelveld;

    Bord(int x, int y){
        this.speelveld = new char[x][y];
    }

    public void setCoordinaatWaarde(int x,int y,char waarde){
        this.speelveld[x][y] = waarde;
    }

    public char[][] getSpeelveld(){
        return this.speelveld;
    }

    public static void startSpel() {
        Bord bord = new Bord(7,7);
        for (int x = 0; x < 7; x++) {
            for (int y = 0; y < 7; y++) {
                bord.setCoordinaatWaarde(x,y,'x');
            }
        }

        zetBeginStand(bord);

        System.out.println("The Great Outdoors Outside");
        for (int x = 0; x < 7; x++) {
            for (int y = 0; y < 7; y++) {
                System.out.print(bord.getSpeelveld()[x][y] + " ");
            }
            System.out.println();
        }
    }

    private static void zetBeginStand(Bord bord){
        bord.setCoordinaatWaarde(6,0,'H');
        bord.setCoordinaatWaarde(6,1,'H');
        bord.setCoordinaatWaarde(5,0,'H');
        bord.setCoordinaatWaarde(5,1,'H');

        bord.setCoordinaatWaarde(0,5,'B');
        bord.setCoordinaatWaarde(0,6,'B');
        bord.setCoordinaatWaarde(1,5,'B');
        bord.setCoordinaatWaarde(1,6,'B');
    }
}

