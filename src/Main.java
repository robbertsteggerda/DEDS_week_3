//COMMENTS OP HET SPEL:
//Het bord wordt soms dubbel geprint. Hierop kon ik binnen de tijd geen passende oplossing vinden.
//Als je geen legale zetten meer hebt moet je soms nog één zet doen om het spel te eindigen.
//De code is erg slordig. Hierover schrijf ik mijn reflectie meer.

//Strategie van de computer:
//De computer vindt bij het maken van een zet alle mogelijke zetten. Hiervan kiest hij er dan één.
//De strategie van de AI is om de eerste vijf zetten, indien mogelijk, altijd een duplicatie te doen.
//Hierdor wordt zijn aantal vakjes opgebouwd aan het begin.
//Ik wilde iets beters maken, maar dit lukte binnen de tijd helaas niet.

public class Main {
    public static void main(String[] args) throws LegeStapelException {
        Spel.startSpel();
    }
}