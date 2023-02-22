import java.util.Scanner;
import java.util.Random;

public class Bord {
    private char[][] speelveld;
    private boolean spelVoorbij;

    Bord(int x, int y){
        this.speelveld = new char[x][y];
    }

    public char getWaarde(int x, int y){
        if(isBuitenSpeelveld(x)||isBuitenSpeelveld(y)){
            return 'x';
        }
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
        bord.zetBeginStand();
        char huidigeSpeler = kiesBeginnendeSpeler();
        while(!bord.spelVoorbij) {
            bord.printBord();
            huidigeSpeler = bord.dupliceer(huidigeSpeler);
            bord.printBord();
        }
    }

    private static char kiesBeginnendeSpeler() {
        Random random = new Random();
        int willekeurigGetal = random.nextInt(2);
        if(willekeurigGetal == 0){
            System.out.println("H mag beginnen");
            return 'H';
        }else{
            System.out.println("B mag beginnen");
            return 'B';
        }
    }

    public void printBord() {
        System.out.println("The Great Outdoors Outside");
        for (int x = 0; x < 7; x++) {
            System.out.print(x + " ");
            for (int y = 0; y < 7; y++) {
                System.out.print(this.getSpeelveld()[x][y] + " ");
            }
            System.out.println();
        }
        System.out.println("  0 1 2 3 4 5 6");
    }

    private void zetBeginStand(){
        for (int x = 0; x < 7; x++) {
            for (int y = 0; y < 7; y++) {
                this.setWaarde(x,y,'x');
            }
        }
        this.setWaarde(6,0,'H');
        this.setWaarde(6,1,'H');
        this.setWaarde(5,0,'H');
        this.setWaarde(5,1,'H');

        this.setWaarde(0,5,'B');
        this.setWaarde(0,6,'B');
        this.setWaarde(1,5,'B');
        this.setWaarde(1,6,'B');
    }

        public char dupliceer(char huidigeSpeler){
            Scanner scanner = new Scanner(System.in);
            System.out.println("voer het y coordinaat van je doel in: ");
            int doelY = scanner.nextInt();
            System.out.println("voer het x coordinaat van je doel in: ");
            int doelX = scanner.nextInt();

            if(isBuitenSpeelveld(doelX) || isBuitenSpeelveld(doelY)){
                System.out.println("De gekozen coördinaten liggen buiten het speelveld! Kies opnieuw a.u.b.");
                return(huidigeSpeler);
            }
             if (!isAangrenzendAanHuidigeSpeler(doelX,doelY,huidigeSpeler)) {
                System.out.println("Illegale zet! Kies opnieuw.");
                return(huidigeSpeler);
            } else{
                this.setWaarde(doelX,doelY,huidigeSpeler);
                return(wisselSpeler(huidigeSpeler));
            }

            }

        public static boolean isBuitenSpeelveld(int getal){
            return getal < 0 || getal > 6;
    }
    public boolean isAangrenzendAanHuidigeSpeler(int x,int y,char huidigeSpeler){
        if(this.getWaarde(x,y) == huidigeSpeler ){
            return false;
        } else if(this.getWaarde(x-1,y) == huidigeSpeler || this.getWaarde(x+1,y) == huidigeSpeler || this.getWaarde(x,y-1) == huidigeSpeler || this.getWaarde(x,y+1) == huidigeSpeler || this.getWaarde(x-1,y-1) == huidigeSpeler || this.getWaarde(x-1,y+1) == huidigeSpeler || this.getWaarde(x+1,y-1) == huidigeSpeler | this.getWaarde(x+1,y+1) == huidigeSpeler){
            return true;
        }else
        return false;
    }

    public static char wisselSpeler(char huidigeSpeler){
        if(huidigeSpeler == 'H'){
            return 'B';
        }
        else{
            return 'H';
        }
    }
}