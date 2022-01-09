/**
 * Mensch Klasse
 * <p>
 * Unterklasse von Spieler
 * @version 1.0.
 * @author Clemens Reichert
 * 
 */

import java.util.*;


public class Mensch extends Spieler{

    private Scanner input;
    public Mensch(String Name)              {super(Name);}
    public void    zeigeHandkarten()        {
        // geht alle Handkarten durch und gibt deren Werte aus
        for(int i = 0; i < Handkarte.size(); i++) {
            System.out.println(Handkarte.get(i).getFarbe());
            System.out.println(Handkarte.get(i).getWert());
        }
    }
    public void    zeigeAblageStapel(Karte Karte)     {
        System.out.println("Dies ist die Farbe und der Wert der obersten Karte des Ablagestapels:");
        System.out.println(Karte.getFarbe() + Karte.getWert());
    }
    public Karte    legeKarte()             {
        System.out.println("Bitte Kartenfarbe angeben;");
        input = new Scanner(System.in);
        String Farbe = input.nextLine();
        System.out.println("Bitte Kartenwert angeben");
        String Wert = input.nextLine();
        input.close();
        Karte lKarte;
        // geht alle Handkarten durch und sucht die ausgewählte Karte, gibt sie zurück und löscht sie aus der Handkatenliste
        for(int i = 0; i < Handkarte.size(); i++) {
            if (Handkarte.get(i).getFarbe() == Farbe && Handkarte.get(i).getWert() == Wert) {
                lKarte = Handkarte.get(i);
                Handkarte.remove(i);
                return lKarte;
            } 
        }
        Karte Error = new Karte(0);
        return Error;
    }
    public void     nehmeKarte(Karte nKarte) {super.Handkarte.add(nKarte);}
    public String    wunschFarbe()           {
        input = new Scanner(System.in);
        String Farbe = input.nextLine();
        input.close();
        return Farbe;
    }
}
