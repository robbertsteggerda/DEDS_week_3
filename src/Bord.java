import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.Random;

public class Bord {
    private boolean spelVoorbij;
    private char[][] speelveld;

    public boolean isSpelVoorbij(){
        return this.spelVoorbij;
    }

    Bord(int x, int y){
        this.speelveld = new char[x][y];
    }

    public char getWaarde(int x, int y){
        if(Spel.isBuitenSpeelveld(x)||Spel.isBuitenSpeelveld(y)){
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


    public void printBord() {
        System.out.println("The Great Outdoors Wins");
        for (int x = 0; x < 7; x++) {
            System.out.print(x + " ");
            for (int y = 0; y < 7; y++) {
                System.out.print(this.getSpeelveld()[x][y] + " ");
            }
            System.out.println();
        }
        System.out.println("  0 1 2 3 4 5 6");
    }

    public void zetBeginStand(){
        for (int x = 0; x < Spel.SPEELVELD_GROOTTE; x++) {
            for (int y = 0; y < Spel.SPEELVELD_GROOTTE; y++) {
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

            if(Spel.isBuitenSpeelveld(doelX) || Spel.isBuitenSpeelveld(doelY)){
                System.out.println("De gekozen coördinaten liggen buiten het speelveld! Kies opnieuw a.u.b.");
                return(huidigeSpeler);
            }
             if (!isAangrenzendAanHuidigeSpeler(doelX,doelY,huidigeSpeler)) {
                System.out.println("Illegale zet! Kies opnieuw.");
                return(huidigeSpeler);
            } else{
                this.setWaarde(doelX,doelY,huidigeSpeler);
                vervangAangrenzendAanTegenstander(doelX,doelY,huidigeSpeler);
                return(Spel.wisselSpeler(huidigeSpeler));
            }
            }

            public char spring(char huidigeSpeler){
                Scanner scanner = new Scanner(System.in);
                System.out.println("voer het y coordinaat van je start in: ");
                int startY = scanner.nextInt();
                System.out.println("voer het x coordinaat van je start in: ");
                int startX = scanner.nextInt();
                System.out.println("voer het y coordinaat van je doel in: ");
                int doelY = scanner.nextInt();
                System.out.println("voer het x coordinaat van je doel in: ");
                int doelX = scanner.nextInt();
                if(Spel.isBuitenSpeelveld(doelX) || Spel.isBuitenSpeelveld(doelY)) {
                    System.out.println("De gekozen coördinaten liggen buiten het speelveld! Kies opnieuw a.u.b.");
                    return (huidigeSpeler);
                }
                    if(Spel.isBuitenSpeelveld(startX) || Spel.isBuitenSpeelveld(startY)) {
                        System.out.println("De gekozen coördinaten liggen buiten het speelveld! Kies opnieuw a.u.b.");
                        return huidigeSpeler;
                    }
                    if(!isVeldVrij(doelX,doelY)){
                        System.out.println("Illegale zet! Dit veld is al bezet. Kies opnieuw.");
                        return huidigeSpeler;
                    }
                    if(!isStartVanHuidigeSpeler(startX,startY,huidigeSpeler)){
                        System.out.println("Illegale zet! Het startveld is niet van jou. Kies opnieuw.");
                        return huidigeSpeler;
                    }
                    setWaarde(startX,startY,'x');
                    this.setWaarde(doelX,doelY,huidigeSpeler);
                    vervangAangrenzendAanTegenstander(doelX,doelY,huidigeSpeler);
                return(Spel.wisselSpeler(huidigeSpeler));
            }

            public boolean isStartVanHuidigeSpeler(int x, int y,char huidigeSpeler){
        if (this.getWaarde(x,y) == huidigeSpeler){
            return true;
        }
                System.out.println(this.getWaarde(x,y));
        return false;
            }
            public boolean isVeldVrij(int x,int y) {
                if (this.getWaarde(x, y) == 'x') {
                    return true;
                }
                return false;
            }
    public boolean isAangrenzendAanHuidigeSpeler(int x,int y,char huidigeSpeler){
        if(!isVeldVrij(x,y)){
            return false;
        } else if(this.getWaarde(x-1,y) == huidigeSpeler || this.getWaarde(x+1,y) == huidigeSpeler || this.getWaarde(x,y-1) == huidigeSpeler || this.getWaarde(x,y+1) == huidigeSpeler || this.getWaarde(x-1,y-1) == huidigeSpeler || this.getWaarde(x-1,y+1) == huidigeSpeler || this.getWaarde(x+1,y-1) == huidigeSpeler || this.getWaarde(x+1,y+1) == huidigeSpeler){
            return true;
        }else
        return false;
    }

    public void vervangAangrenzendAanTegenstander(int x,int y,char huidigeSpeler){
    char tegenstander = Spel.wisselSpeler(huidigeSpeler);
        if(this.getWaarde(x-1,y) == tegenstander){
            this.setWaarde(x-1,y,huidigeSpeler);
        }
        if(this.getWaarde(x+1,y) == tegenstander){
            this.setWaarde(x+1,y,huidigeSpeler);
        }
        if(this.getWaarde(x,y-1) == tegenstander){
            this.setWaarde(x,y-1,huidigeSpeler);
        }
        if(this.getWaarde(x,y+1) == tegenstander){
            this.setWaarde(x,+1,huidigeSpeler);
        }
        if(this.getWaarde(x+1,y+1) == tegenstander){
            this.setWaarde(x+1,y+1,huidigeSpeler);
        }
        if(this.getWaarde(x-1,y-1) == tegenstander){
            this.setWaarde(x-1,y-1,huidigeSpeler);
        }
        if(this.getWaarde(x+1,y-1) == tegenstander){
            this.setWaarde(x+1,y-1,huidigeSpeler);
        }
        if(this.getWaarde(x-1,y+1) == tegenstander){
            this.setWaarde(x-1,y+1,huidigeSpeler);
        }
    }
}