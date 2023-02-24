import java.util.Random;
import java.util.Scanner;

public class Spel {
    public static final int SPEELVELD_GROOTTE = 7;
    public static int Bteller;
    public static int Hteller;
    private static int puntTeller;
    public static boolean HHeeftZetten = true;
    public static boolean BHeeftZetten = true;

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
            Bteller = 0;
            Hteller = 0;
            puntTeller = 0;

            bord.printBord();
            System.out.println(huidigeSpeler + " is aan de beurt.");
            while(huidigeSpeler == 'B'){
                huidigeSpeler = bord.willekeurigeRobotZet(huidigeSpeler,bord.getAantalZetten());
            }

            bord.printBord();
            int keuze = kiesZetType();
            if(keuze == 1) {
                huidigeSpeler = bord.dupliceer(huidigeSpeler);
            }else if(keuze == 2){
                huidigeSpeler = bord.spring(huidigeSpeler);
            }else if(keuze == 3){
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
            if(huidigeSpeler == 'H'){
                int aantalZetten = bord .getAantalZetten();
                aantalZetten++;
                bord.setAantalZetten(aantalZetten);
            }
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