import java.util.Random;
import java.util.Scanner;

public class Spel {
    public static final int SPEELVELD_GROOTTE = 7;

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

    public static void startSpel() {
        Bord bord = new Bord(SPEELVELD_GROOTTE,SPEELVELD_GROOTTE);
        bord.zetBeginStand();
        char huidigeSpeler = kiesBeginnendeSpeler();
        while(!bord.isSpelVoorbij()) {
            bord.printBord();
            System.out.println(huidigeSpeler + " is aan de beurt.");
            int keuze = kiesZetType();
            if(keuze == 1) {
                huidigeSpeler = bord.dupliceer(huidigeSpeler);
            }else if(keuze == 2){
                huidigeSpeler = bord.spring(huidigeSpeler);
            }else{
                System.out.println("ongeldige keuze, kies opnieuw a.u.b.");
            }
        }
    }

    public static int kiesZetType(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Kies het type zet dat je wilt doen: ");
        System.out.println("1. dupliceer");
        System.out.println("2. sprong");
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
