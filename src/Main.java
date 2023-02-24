//COMMENTS OP HET SPEL:
//Het bord wordt soms dubbel geprint. Hierop kon ik binnen de tijd geen passende oplossing vinden.
//Als je geen legale zetten meer hebt moet je soms nog één zet doen om het spel te eindigen.
//De code is erg slordig. Hierover schrijf ik mijn reflectie meer.

//Strategie van de computer:
//De computer vindt bij het maken van een zet alle mogelijke zetten. Hiervan kiest hij er dan één.
//De strategie van de AI is om de eerste vijf zetten, indien mogelijk, altijd een duplicatie te doen.
//Hierdor wordt zijn aantal vakjes opgebouwd aan het begin.
//Ik wilde iets beters maken, maar dit lukte binnen de tijd helaas niet.

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
public class Main {
    public static void main(String[] args) throws LegeStapelException {
        Spel.startSpel();
    }
}