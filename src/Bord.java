import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.Random;

public class Bord {
    private boolean spelVoorbij;
    private char[][] speelveld;
    private static Stapel stapel = new Stapel();

    public void setSpelVoorbij() {
        this.spelVoorbij = true;
    }

    public boolean isSpelVoorbij() {
        return this.spelVoorbij;
    }

    Bord(int x, int y) {
        this.speelveld = new char[x][y];
    }

    public char getWaarde(int x, int y) {
        if (Spel.isBuitenSpeelveld(x) || Spel.isBuitenSpeelveld(y)) {
            return 'x';
        }
        return this.speelveld[x][y];
    }

    public void setWaarde(int x, int y, char waarde) {
        this.speelveld[x][y] = waarde;
    }

    public char[][] getSpeelveld() {
        return this.speelveld;
    }

    public void slaBordOp() {
        char[][] kopie = new char[Spel.SPEELVELD_GROOTTE][Spel.SPEELVELD_GROOTTE];
        for (int i = 0; i < Spel.SPEELVELD_GROOTTE; i++) {
            for (int j = 0; j < Spel.SPEELVELD_GROOTTE; j++) {
                kopie[i][j] = this.getSpeelveld()[i][j];
            }
        }
        stapel.duw(kopie);
    }

    public void zetBordTerug() throws LegeStapelException {
        char[][] kopie = (char[][]) stapel.pak();
        for (int i = 0; i < kopie.length; i++) {
            for (int j = 0; j < kopie.length; j++) {
                this.getSpeelveld()[i][j] = kopie[i][j];
            }
        }
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

    public void zetBeginStand() {
        for (int x = 0; x < Spel.SPEELVELD_GROOTTE; x++) {
            for (int y = 0; y < Spel.SPEELVELD_GROOTTE; y++) {
                this.setWaarde(x, y, 'x');
            }
        }
        this.setWaarde(6, 0, 'H');
        this.setWaarde(6, 1, 'H');
        this.setWaarde(5, 0, 'H');
        this.setWaarde(5, 1, 'H');

        this.setWaarde(0, 5, 'B');
        this.setWaarde(0, 6, 'B');
        this.setWaarde(1, 5, 'B');
        this.setWaarde(1, 6, 'B');
    }

    public char dupliceer(char huidigeSpeler) {
        //sla alleen het bord op/vraag om coordinaten als de huidige speler niet de computer is.
        int doelY = 0;
        int doelX = 0;
        if (huidigeSpeler == 'H') {
            slaBordOp();

        Scanner scanner = new Scanner(System.in);
        System.out.println("voer het X coordinaat van je doel in: ");
        doelY = scanner.nextInt();
        System.out.println("voer het Y coordinaat van je doel in: ");
        doelX = scanner.nextInt();
        //willekeurige zet van de computer
        }else{
            Random rand = new Random();
            doelY = rand.nextInt(6);
            doelX = rand.nextInt(6);
        }

        if (Spel.isBuitenSpeelveld(doelX) || Spel.isBuitenSpeelveld(doelY)) {
            System.out.println("De gekozen coördinaten liggen buiten het speelveld! Kies opnieuw a.u.b.");
            return (huidigeSpeler);
        }
        if (!isAangrenzendAanHuidigeSpeler(doelX, doelY, huidigeSpeler)) {
            if( huidigeSpeler == 'H') {
                System.out.println("Illegale zet! Kies opnieuw.");
            }
            return (huidigeSpeler);
        } else {
            this.setWaarde(doelX, doelY, huidigeSpeler);
            vervangAangrenzendAanTegenstander(doelX, doelY, huidigeSpeler);
            return (Spel.wisselSpeler(huidigeSpeler));
        }
    }

    public char spring(char huidigeSpeler) {
        int startY = 0;
        int startX = 0;
        int doelY = 0;
        int doelX = 0;
        //sla alleen het bord op als de huidige speler niet de computer is
        if (huidigeSpeler == 'H') {
            slaBordOp();
            Scanner scanner = new Scanner(System.in);
            System.out.println("voer het X coordinaat van je start in: ");
            startY = scanner.nextInt();
            System.out.println("voer het Y coordinaat van je start in: ");
            startX = scanner.nextInt();
            System.out.println("voer het X coordinaat van je doel in: ");
            doelY = scanner.nextInt();
            System.out.println("voer het Y coordinaat van je doel in: ");
            doelX = scanner.nextInt();
        } else{
            Random rand = new Random();
            startY = rand.nextInt(6);
            startX = rand.nextInt(6);
            doelY = rand.nextInt(6);
            doelX = rand.nextInt(6);
        }

        if (Spel.isBuitenSpeelveld(doelX) || Spel.isBuitenSpeelveld(doelY)) {
            if( huidigeSpeler == 'H') {
                System.out.println("De gekozen coördinaten liggen buiten het speelveld! Kies opnieuw a.u.b.");
            }
            return (huidigeSpeler);
        }
        if (Spel.isBuitenSpeelveld(startX) || Spel.isBuitenSpeelveld(startY)) {
            if( huidigeSpeler == 'H') {
                System.out.println("De gekozen coördinaten liggen buiten het speelveld! Kies opnieuw a.u.b.");
            }
            return huidigeSpeler;
        }
        if (!isVeldVrij(doelX, doelY)) {
            if( huidigeSpeler == 'H') {
                System.out.println("Illegale zet! Dit veld is al bezet. Kies opnieuw.");
            }
            return huidigeSpeler;
        }
        if (!isStartVanHuidigeSpeler(startX, startY, huidigeSpeler)) {
            if( huidigeSpeler == 'H') {
                System.out.println("Illegale zet! Het startveld is niet van jou. Kies opnieuw.");
            }
            return huidigeSpeler;
        }
        if (!isLegaleSprong(startX, startY, doelX, doelY)) {
            if( huidigeSpeler == 'H') {
                System.out.println("Illegale zet! Je moet 2 vakjes springen. Kies opnieuw");
            }
            return huidigeSpeler;
        }
        setWaarde(startX, startY, 'x');
        this.setWaarde(doelX, doelY, huidigeSpeler);
        vervangAangrenzendAanTegenstander(doelX, doelY, huidigeSpeler);
        return (Spel.wisselSpeler(huidigeSpeler));
    }

    public boolean isLegaleSprong(int startX, int startY, int doelX, int doelY) {
        //check voor diagonale sprong van één vakje (mag niet)
        if (Math.abs(startX - doelX) == 1 && Math.abs(startY - doelY) == 1) {
            return false;
        }
        //check voor paardensprong (mag niet)
        if (Math.abs(startX - doelX) == 1 && Math.abs(startY - doelY) == 2 || Math.abs(startX - doelX) == 2 && Math.abs(startY - doelY) == 1) {
            return false;
        }

        //check of de X en Y coördinaat beide binnen 2 maar boven 1 van het startvakje liggen
        if (Math.abs(startX - doelX) <= 2 && Math.abs(startY - doelY) <= 2 && Math.abs(startX - doelX) + Math.abs(startY - doelY) > 1) {
            return true;
        }
        return false;
    }


    public boolean isStartVanHuidigeSpeler(int x, int y, char huidigeSpeler) {
        if (this.getWaarde(x, y) == huidigeSpeler) {
            return true;
        }
        System.out.println(this.getWaarde(x, y));
        return false;
    }

    public boolean isVeldVrij(int x, int y) {
        if (this.getWaarde(x, y) == 'x') {
            return true;
        }
        return false;
    }

    public boolean isAangrenzendAanHuidigeSpeler(int x, int y, char huidigeSpeler) {
        if (!isVeldVrij(x, y)) {
            return false;
        } else if (this.getWaarde(x - 1, y) == huidigeSpeler || this.getWaarde(x + 1, y) == huidigeSpeler || this.getWaarde(x, y - 1) == huidigeSpeler || this.getWaarde(x, y + 1) == huidigeSpeler || this.getWaarde(x - 1, y - 1) == huidigeSpeler || this.getWaarde(x - 1, y + 1) == huidigeSpeler || this.getWaarde(x + 1, y - 1) == huidigeSpeler || this.getWaarde(x + 1, y + 1) == huidigeSpeler) {
            return true;
        } else
            return false;
    }

    public char robotZet(char huidigeSpeler) {
        //willekeurige zet:
        Random rand = new Random();
        int zetType = rand.nextInt(2);
        if (zetType == 0) {
            return this.spring(huidigeSpeler);
        }else{
            return this.dupliceer(huidigeSpeler);
        }
    }

    public void vervangAangrenzendAanTegenstander(int x, int y, char huidigeSpeler) {
        char tegenstander = Spel.wisselSpeler(huidigeSpeler);
        if (this.getWaarde(x - 1, y) == tegenstander) {
            this.setWaarde(x - 1, y, huidigeSpeler);
        }
        if (this.getWaarde(x + 1, y) == tegenstander) {
            this.setWaarde(x + 1, y, huidigeSpeler);
        }
        if (this.getWaarde(x, y - 1) == tegenstander) {
            this.setWaarde(x, y - 1, huidigeSpeler);
        }
        if (this.getWaarde(x, y + 1) == tegenstander) {
            this.setWaarde(x, y + 1, huidigeSpeler);
        }
        if (this.getWaarde(x + 1, y + 1) == tegenstander) {
            this.setWaarde(x + 1, y + 1, huidigeSpeler);
        }
        if (this.getWaarde(x - 1, y - 1) == tegenstander) {
            this.setWaarde(x - 1, y - 1, huidigeSpeler);
        }
        if (this.getWaarde(x + 1, y - 1) == tegenstander) {
            this.setWaarde(x + 1, y - 1, huidigeSpeler);
        }
        if (this.getWaarde(x - 1, y + 1) == tegenstander) {
            this.setWaarde(x - 1, y + 1, huidigeSpeler);
        }
    }
}