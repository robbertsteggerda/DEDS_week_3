import java.util.Scanner;

public class Spel {
    public void dupliceer(Bord bord){
        Scanner scanner = new Scanner(System.in);
        System.out.println("voer het y coordinaat van je beginstuk in: ");
        int startY = scanner.nextInt();
        System.out.println("voer het x coordinaat van je beginstuk in: ");
        int startX = scanner.nextInt();
        System.out.println("voer het y coordinaat van je doel in: ");
        int doelY = scanner.nextInt();
        System.out.println("voer het x coordinaat van je doel in: ");
        int doelX = scanner.nextInt();

        if(isBuitenSpeelveld(startY)||isBuitenSpeelveld(startX)||isBuitenSpeelveld(doelY)||isBuitenSpeelveld(doelX)){
            System.out.println("ingevoerde coördinaten liggen buiten het speelveld! Kies opnieuw.");
        }
    }
    public static boolean isBuitenSpeelveld(int getal){
        return getal < 0 || getal > 6;
    }
}
