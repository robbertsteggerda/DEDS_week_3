import java.util.Random;
import java.util.Scanner;

public class Spel {
    public static final int SPEELVELD_GROOTTE = 7;
    public static int Bteller;
    public static int Hteller;
    private static int puntTeller;
    public static boolean HHeeftZetten = true;
    public static boolean BHeeftZetten = true;
    public static boolean isZetTeruggezet = false;

    //deze methode kiest een willekeurige speler
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


    public static void startSpel() throws LegeStapelException {
        Bord bord = new Bord(SPEELVELD_GROOTTE,SPEELVELD_GROOTTE);
        bord.zetBeginStand();
        bord.printBord();
       char huidigeSpeler = kiesBeginnendeSpeler();

        while(!bord.isSpelVoorbij()) {
            while(!bord.heeftLegaleZet(huidigeSpeler) && (HHeeftZetten || BHeeftZetten)){
                if(huidigeSpeler == 'H'){
                    huidigeSpeler = wisselSpeler(huidigeSpeler);
                    HHeeftZetten = false;
                } else{
                    BHeeftZetten = false;
                    huidigeSpeler = wisselSpeler(huidigeSpeler);
                }
                //wisselSpeler(huidigeSpeler);
            }

            if(huidigeSpeler == 'x'){
                break;
            }
            Bteller = 0;
            Hteller = 0;
            puntTeller = 0;

            bord.printBord();
            System.out.println(huidigeSpeler + " is aan de beurt.");
            while(huidigeSpeler == 'B'){
                boolean eindigHetSpel = !bord.heeftLegaleZet(huidigeSpeler);
                if(eindigHetSpel){
                    bord.setSpelVoorbij();
                    break;
                }
                huidigeSpeler = bord.robotZet(huidigeSpeler);
            }
            boolean eindigHetSpel2 = !bord.heeftLegaleZet(huidigeSpeler);
            if(eindigHetSpel2){
                break;
            }

            System.out.println("Aantal zetten " + bord.getAantalZetten());
            bord.printBord();
            boolean eindigHetSpel = false;
            if(!bord.heeftLegaleZet(huidigeSpeler)) {
            //    boolean eindigHetSpel = !bord.heeftLegaleZet(huidigeSpeler);
                eindigHetSpel = true;
            }
            if(eindigHetSpel){
                bord.setSpelVoorbij();
            }
            int keuze = kiesZetType();
            if(keuze == 1) {
                huidigeSpeler = bord.dupliceer(huidigeSpeler);
            }else if(keuze == 2){
                huidigeSpeler = bord.spring(huidigeSpeler);
            }else if(keuze == 3){
                int aantalZetten = bord.getAantalZetten();
                aantalZetten--;
                bord.setAantalZetten(aantalZetten);
                isZetTeruggezet = true;
                bord.zetBordTerug();
                } else{
                System.out.println("ongeldige keuze, kies opnieuw a.u.b.");
            }
            for(int x = 0;x<SPEELVELD_GROOTTE;x++){
                for(int y = 0;y<SPEELVELD_GROOTTE;y++){
                    if(bord.getWaarde(x,y) == 'H'){
                        Hteller++;
                    }else if(bord.getWaarde(x,y) == 'B'){
                        Bteller++;
                    } else if(bord.getWaarde(x,y) == '.'){
                        puntTeller++;
                    }
                }
            }

            System.out.println("score: H = " + Spel.Hteller + " B = " + Spel.Bteller);
            System.out.println("Aantal zetten: " + bord.getAantalZetten());

            if(Bteller == 0){
                System.out.println("H heeft gewonnen!");
                bord.printBord();
                bord.setSpelVoorbij();
            }

            if(Hteller == 0){
                System.out.println("B heeft gewonnen!");
                bord.printBord();
                bord.setSpelVoorbij();
            }
            if(puntTeller == 0){
                printWinnaar(bord);
            }
            if(!isZetTeruggezet) {
                int aantalZetten = bord.getAantalZetten();
                aantalZetten++;
                bord.setAantalZetten(aantalZetten);
            }else{
                isZetTeruggezet = false;
            }
        }
        printWinnaar(bord);
    }

    private static void printWinnaar(Bord bord) {
        for (int x = 0; x < SPEELVELD_GROOTTE; x++) {
            for (int y = 0; y < SPEELVELD_GROOTTE; y++) {
                if (bord.getWaarde(x, y) == 'H') {
                    Hteller++;
                } else if (bord.getWaarde(x, y) == 'B') {
                    Bteller++;
                }
            }
        }

        if(Bteller>Hteller){
            System.out.println("B heeft gewonnen!");
            bord.printBord();
            bord.setSpelVoorbij();
        }else if(Hteller>Bteller){
            System.out.println("H heeft gewonnen!");
            bord.printBord();
            bord.setSpelVoorbij();
        }else if(Bteller == Hteller){
            System.out.println("Het is gelijkspel!");
            bord.printBord();
            bord.setSpelVoorbij();
        }
    }

    public static int kiesZetType(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Kies het type zet dat je wilt doen: ");
        System.out.println("1. dupliceer");
        System.out.println("2. sprong");
        System.out.println("3. zet terugnemen");
        int keuze = scanner.nextInt();
        return keuze;
    }

    public static char wisselSpeler(char huidigeSpeler){
        if(huidigeSpeler == 'H'){
            return 'B';
        }
        return 'H';
    }
    public static boolean isBuitenSpeelveld(int getal){
        return getal < 0 || getal > 6;
    }
}