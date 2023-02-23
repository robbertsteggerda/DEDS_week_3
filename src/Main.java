//COMMENTS OP HET SPEL:
//Het bord wordt soms dubbel geprint. Hierop kon ik binnen de tijd geen passende oplossing vinden.
//Als je geen legale zetten meer hebt moet je soms nog één zet doen om het spel te eindigen.
//De code is erg slordig. Hierover schrijf ik mijn reflectie meer.
//Bij het maken van een willekeurige sprong test de computer of het startveld van hem is. Dit is omdat de code anders te langzaam is.
//voor een willekeurige duplicatie was dit niet nodig.

//TODO:Als de computer een mogelijke zet heeft die grenst aan een vakje van de speler, deze zet eerst.

public class Main {
    public static void main(String[] args) throws LegeStapelException {
        Spel.startSpel();
    }
}