import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class Bord {
    private boolean spelVoorbij;
    private char[][] speelveld;
    private static Stapel stapel = new Stapel();
    private int aantalZetten = 0;

    public void setAantalZetten(int aantalZetten) {
        this.aantalZetten = aantalZetten;
    }

    public int getAantalZetten() {
        return aantalZetten;
    }

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
            return '.';
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
                this.setWaarde(x, y, '.');
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
        } else {
            //Dit stuk code zorgt ervoor dat de computer alleen van coordinaten springt waarvan het vakje van hem is
            Random rand = new Random();
            ArrayList<Coordinaat> mogelijkeStartCoordinaten = new ArrayList<Coordinaat>();
            mogelijkeStartCoordinaten.clear();
            for (int i = 0; i < Spel.SPEELVELD_GROOTTE; i++) {
                for (int j = 0; j < Spel.SPEELVELD_GROOTTE; j++) {
                    if (this.getWaarde(i, j) == huidigeSpeler) {
                        if (isLegaleDuplicatieMogelijk(i, j)) {
                            mogelijkeStartCoordinaten.add(new Coordinaat(i, j, (ArrayList<Coordinaat>) (vindLegaleDuplicaties(i, j)).clone()));
                        }
                    }
                }
            }

            //de computer kiest willekeurig één van de mogelijke startvakjes
            //vanaf één van deze vakjes probeert hij naast de tegenstander te gaan staan
            //als dit kan, doet hij een zet waarbij hij naast een tegenstander gaat staan
            //als dit niet kan, doet hij een willekeurige sprong
            //helaas werkt dit niet helemaal
            int index = (int) (Math.random() * mogelijkeStartCoordinaten.size());
            int doelIndex = (int) (Math.random() * mogelijkeStartCoordinaten.get(index).getDoelen().size());

            //  ArrayList<Coordinaat> mogelijkeDoelenLijst = new ArrayList<Coordinaat>();
            // ArrayList<Coordinaat> doelenLijst = new ArrayList<Coordinaat>();
            //  for (int j = 0; j < mogelijkeStartCoordinaten.size(); j++) {
            //     mogelijkeDoelenLijst.addAll(this.vindLegaleDuplicaties(mogelijkeStartCoordinaten.get(j).getX(),mogelijkeStartCoordinaten.get(j).getY()));
            //      if(this.isAangrenzendAanTegenstander(mogelijkeDoelenLijst.get(j).getX(),mogelijkeStartCoordinaten.get(j).getY(),huidigeSpeler)){
            //          doelenLijst.add(mogelijkeDoelenLijst.get(j));
            //     }
            //      for (int i = 0; i < mogelijkeStartCoordinaten.get(j).getDoelen().size(); i++) {
            //          doelenLijst.addAll(mogelijkeStartCoordinaten.get(j).getDoelen());
            //          if (this.isAangrenzendAanTegenstander(doelenLijst.get(i).getX(), doelenLijst.get(i).getY(), huidigeSpeler)) {
            //              doelX = doelenLijst.get(i).getX();
            //              doelY = doelenLijst.get(i).getY();
            //          }
            //       }

            while(mogelijkeStartCoordinaten.get(index).getDoelen().size() == 0){
                index = (int) (Math.random() * mogelijkeStartCoordinaten.size());
            }
                doelY = mogelijkeStartCoordinaten.get(index).getDoelen().get(doelIndex).getY();
                doelX = mogelijkeStartCoordinaten.get(index).getDoelen().get(doelIndex).getX();

            if (doelY == -1) {
                doelY = rand.nextInt(6);
            }
            if (doelX == -1) {
                doelX = rand.nextInt(6);
            }
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
        int doelX = -1;
        int doelY = -1;
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
            //Dit stuk code zorgt ervoor dat de computer alleen van coordinaten springt waarvan het vakje van hem is
            Random rand = new Random();
            ArrayList<Coordinaat>  mogelijkeStartCoordinaten = new ArrayList<Coordinaat>();
            mogelijkeStartCoordinaten.clear();
            for (int i = 0; i < Spel.SPEELVELD_GROOTTE; i++) {
                for (int j = 0; j < Spel.SPEELVELD_GROOTTE; j++) {
                    if (this.getWaarde(i, j) == huidigeSpeler) {
                        if(isLegaleSprongMogelijk(i,j)){
                            mogelijkeStartCoordinaten.add(new Coordinaat(i,j, (ArrayList<Coordinaat>) (vindLegaleSprongen(i,j)).clone()));
                        }
                    }
                }
            }

            //de computer kiest willekeurig één van de mogelijke startvakjes
            //vanaf één van deze vakjes probeert hij naast de tegenstander te gaan staan
            //als dit kan, doet hij een zet waarbij hij naast een tegenstander gaat staan
            //als dit niet kan, doet hij een willekeurige sprong
            //helaas werkt dit niet helemaal
            int index = (int)(Math.random() * mogelijkeStartCoordinaten.size());
            int doelIndex = (int) (Math.random()*mogelijkeStartCoordinaten.get(index).getDoelen().size());

         //   ArrayList<Coordinaat> mogelijkeDoelenLijst = new ArrayList<Coordinaat>();
         //   ArrayList<Coordinaat> doelenLijst = new ArrayList<Coordinaat>();
         //   for (int j = 0; j < mogelijkeStartCoordinaten.size(); j++) {
         //       mogelijkeDoelenLijst.addAll(this.vindLegaleSprongen(mogelijkeStartCoordinaten.get(j).getX(),mogelijkeStartCoordinaten.get(j).getY()));
         //       if(this.isAangrenzendAanTegenstander(mogelijkeDoelenLijst.get(j).getX(),mogelijkeStartCoordinaten.get(j).getY(),huidigeSpeler)){
         //           doelenLijst.add(mogelijkeDoelenLijst.get(j));
         //       }
         //       for (int i = 0; i < mogelijkeStartCoordinaten.get(j).getDoelen().size(); i++) {
         //           doelenLijst.addAll(mogelijkeStartCoordinaten.get(j).getDoelen());
         //           if (this.isAangrenzendAanTegenstander(doelenLijst.get(i).getX(), doelenLijst.get(i).getY(), huidigeSpeler)) {
         //               doelX = doelenLijst.get(i).getX();
        //                doelY = doelenLijst.get(i).getY();
         //           }

            startY = rand.nextInt(mogelijkeStartCoordinaten.get(index).getY()+1);
            startX = rand.nextInt(mogelijkeStartCoordinaten.get(index).getX()+1);
            if(doelY == -1) {
                doelY = rand.nextInt(6);
            }
            if(doelX == -1) {
                doelX = rand.nextInt(6);
            }
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
       // if(!Spel.isBuitenSpeelveld(startX) && !Spel.isBuitenSpeelveld(startY) && !Spel.isBuitenSpeelveld(doelX) && !Spel.isBuitenSpeelveld(doelY)) {
            setWaarde(startX, startY, '.');
            this.setWaarde(doelX, doelY, huidigeSpeler);
            vervangAangrenzendAanTegenstander(doelX, doelY, huidigeSpeler);
            return (Spel.wisselSpeler(huidigeSpeler));
       // }
      //  return 'B';
    }

    private ArrayList<Coordinaat> vindLegaleSprongen(int x, int y) {
        ArrayList<Coordinaat> lijst = new ArrayList<Coordinaat>();
        if(this.getWaarde(x+2,y) == '.' && x+2 >= 0 && x+2 < Spel.SPEELVELD_GROOTTE){
            lijst.add(new Coordinaat(x+2,y));
        } if(this.getWaarde(x-2,y) == '.' && x-2 >= 0 && x-2 < Spel.SPEELVELD_GROOTTE){
            lijst.add(new Coordinaat(x-2,y));
        } if(this.getWaarde(x+2,y+2) == '.' && x+2 >= 0 && x+2 < Spel.SPEELVELD_GROOTTE && y+2 >= 0 && y+2 < Spel.SPEELVELD_GROOTTE){
            lijst.add(new Coordinaat(x+2,y+2));
        } if(this.getWaarde(x+2,y-2) == '.' && x+2 >= 0 && x+2 < Spel.SPEELVELD_GROOTTE && y-2 >= 0 && y-2 < Spel.SPEELVELD_GROOTTE){
            lijst.add(new Coordinaat(x+2,y-2));
        } if(this.getWaarde(x,y+2) == '.' && y+2 >= 0 && y+2 < Spel.SPEELVELD_GROOTTE){
            lijst.add(new Coordinaat(x,y+2));
        } if(this.getWaarde(x-2,y-2) == '.' && y-2 >= 0 && y-2 < Spel.SPEELVELD_GROOTTE && x-2 >= 0 && x-2 < Spel.SPEELVELD_GROOTTE){
            lijst.add(new Coordinaat(x-2,y-2));
        } if(this.getWaarde(x-2,y+2) == '.' && x-2 >= 0 && x-2 < Spel.SPEELVELD_GROOTTE && y+2 >= 0 && y+2 < Spel.SPEELVELD_GROOTTE){
            lijst.add(new Coordinaat(x-2,y+2));
        } if(this.getWaarde(x+2,y-2) == '.' && x+2 >= 0 && x+2 < Spel.SPEELVELD_GROOTTE && y-2 >= 0 && y-2 < Spel.SPEELVELD_GROOTTE){
            lijst.add(new Coordinaat(x+2,y-2));
        }
        return lijst;
    }
    private ArrayList<Coordinaat> vindLegaleDuplicaties(int x, int y) {
        ArrayList<Coordinaat> lijst = new ArrayList<Coordinaat>();
        if (this.getWaarde(x + 1, y) == '.' && x + 1 >= 0 && x + 1 < Spel.SPEELVELD_GROOTTE) {
            lijst.add(new Coordinaat(x + 1, y));
        } if (this.getWaarde(x - 1, y) == '.' && x - 1 >= 0 && x - 1 < Spel.SPEELVELD_GROOTTE) {
            lijst.add(new Coordinaat(x - 1, y));
        } if (this.getWaarde(x + 1, y + 1) == '.' && x + 1 >= 0 && x + 1 < Spel.SPEELVELD_GROOTTE && y + 1 >= 0 && y + 1 < Spel.SPEELVELD_GROOTTE) {
            lijst.add(new Coordinaat(x + 1, y + 1));
        } if (this.getWaarde(x + 1, y - 1) == '.' && x + 1 >= 0 && x + 1 < Spel.SPEELVELD_GROOTTE && y - 1 >= 0 && y - 1 < Spel.SPEELVELD_GROOTTE) {
            lijst.add(new Coordinaat(x + 1, y - 1));
        } if (this.getWaarde(x, y + 1) == '.' && y + 1 >= 0 && y + 1 < Spel.SPEELVELD_GROOTTE) {
            lijst.add(new Coordinaat(x, y + 1));
        } if (this.getWaarde(x - 1, y - 1) == '.' && y - 1 >= 0 && y - 1 < Spel.SPEELVELD_GROOTTE && x + 1 >= 0 && x - 1 < Spel.SPEELVELD_GROOTTE) {
            lijst.add(new Coordinaat(x - 1, y - 1));
        } if (this.getWaarde(x - 1, y + 1) == '.' && x - 1 >= 0 && x - 1 < Spel.SPEELVELD_GROOTTE && y + 1 >= 0 && y + 1 < Spel.SPEELVELD_GROOTTE) {
            lijst.add(new Coordinaat(x - 1, y + 1));
        } if (this.getWaarde(x + 1, y - 1) == '.' && x + 1 >= 0 && x + 1 < Spel.SPEELVELD_GROOTTE && y - 1 >= 0 && y - 1 < Spel.SPEELVELD_GROOTTE) {
            lijst.add(new Coordinaat(x + 1, y - 1));
        }
        return lijst;
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
        return false;
    }

    public boolean isVeldVrij(int x, int y) {
        if (this.getWaarde(x, y) == '.') {
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
        /*
        Willekeurige zet: er zijn checks om te testen of een legale sprong/duplicatie mogelijk is.
        De mogelijke zetten worden in een ArrayList gezet, en één van deze zetten wordt willekeurig gekozen
        De strategie van de computer is om de eerste vijf zetten indien dit mogelijk is altijd een duplicatie
        te doen. Op deze manier worden zijn velden opgebouwd aan het begin van het spel.

        Ik wilde eerst proberen om indien mogelijk de computer altijd naast een veld van de tegenstander te
        laten gaan, daarom ook de uitgecommente code. Helaas is dat binnen de tijd niet gelukt. Daarom heb ik
        voor deze simpelere optie gekozen.

        Ik heb ook overwogen om voor een Monte Carlo analyse te gaan, maar op hier had ik helaas geen tijd voor.
        */

        Random rand = new Random();
        int zetType = rand.nextInt(1);
        for (int i = 0; i < Spel.SPEELVELD_GROOTTE; i++) {
            for (int j = 0; j < Spel.SPEELVELD_GROOTTE; j++) {
                if (this.getWaarde(i,j) == huidigeSpeler) {
                    //indien aantal zetten kleiner is dan vijf: doe een duplicatie als dit mogelijk is
                    if (!this.isLegaleSprongMogelijk(i, j) || this.getAantalZetten() < 5) {
                        zetType = 1;
                    }else{
                        zetType = rand.nextInt(2);
                    }
                }
            }
        }

        for (int i = 0; i < Spel.SPEELVELD_GROOTTE; i++) {
            for (int j = 0; j < Spel.SPEELVELD_GROOTTE; j++) {
                if (this.getWaarde(i,j) == huidigeSpeler) {
                    if (!this.isLegaleDuplicatieMogelijk(i, j)) {
                        zetType = 0;
                    }else{
                        zetType = rand.nextInt(2);
                    }
                }
            }
        }
        if (zetType == 0) {
            return this.spring(huidigeSpeler);
        }else{
            return this.dupliceer(huidigeSpeler);
        }
    }

    public boolean isAangrenzendAanTegenstander(int x, int y, char huidigeSpeler) {
        char tegenstander = Spel.wisselSpeler(huidigeSpeler);
        if (this.getWaarde(x - 1, y) == tegenstander) {
            return true;
        }
        if (this.getWaarde(x + 1, y) == tegenstander) {
            return true;
        }
        if (this.getWaarde(x, y - 1) == tegenstander) {
            return true;
        }
        if (this.getWaarde(x, y + 1) == tegenstander) {
            return true;
        }
        if (this.getWaarde(x + 1, y + 1) == tegenstander) {
            return true;
        }
        if (this.getWaarde(x - 1, y - 1) == tegenstander) {
            return true;
        }
        if (this.getWaarde(x + 1, y - 1) == tegenstander) {
            return true;
        }
        if (this.getWaarde(x - 1, y + 1) == tegenstander) {
            return true;
        }
        return false;
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

    public boolean heeftLegaleZet(char huidigeSpeler) {
        for (int i = 0; i < Spel.SPEELVELD_GROOTTE; i++) {
            for (int j = 0; j < Spel.SPEELVELD_GROOTTE; j++) {
                if (this.getWaarde(i,j) == huidigeSpeler) {
                    if (!this.isLegaleSprongMogelijk(i, j) && !this.isLegaleDuplicatieMogelijk(i, j)) {
                        System.out.println("Geen legale zetten mogelijk! Andere speler is aan de beurt.");
                        return false;
                    }else{
                        return true;
                    }
                }
            }
        }
        if(huidigeSpeler == 'H'){
            Spel.HHeeftZetten = true;
        } else{
            Spel.BHeeftZetten = true;
        }
        return true;
    }

    private boolean isLegaleDuplicatieMogelijk(int x, int y) {
        if (this.getWaarde(x+1,y+1) == '.') {
            return true;
        } else if (this.getWaarde(x+1,y-1) == '.') {
            return true;
        } else if (this.getWaarde(x-1,y+1) == '.') {
            return true;
        } else if (this.getWaarde(x-1,y-1) == '.') {
            return true;
        } else if (this.getWaarde(x+1,y) == '.') {
            return true;
        } else if (this.getWaarde(x,y+1) == '.') {
            return true;
        } else if (this.getWaarde(x-1,y) == '.') {
            return true;
        } else if (this.getWaarde(x,y-1)== '.') {
            return true;
        } else {
            return false;
        }
    }

    private boolean isLegaleSprongMogelijk(int x, int y) {
        if (this.getWaarde(x+2,y+2) == '.') {
            return true;
        } else if (this.getWaarde(x+2,y-2) == '.') {
            return true;
        } else if (this.getWaarde(x-2,y+2) == '.') {
            return true;
        } else if (this.getWaarde(x-2,y-2) == '.') {
            return true;
        } else if (this.getWaarde(x+2,y) == '.') {
            return true;
        } else if (this.getWaarde(x,y+2) == '.') {
            return true;
        } else if (this.getWaarde(x-2,y) == '.') {
            return true;
        } else if (this.getWaarde(x,y-2) == '.') {
            return true;
        } else {
            return false;
        }
    }
}