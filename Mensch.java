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
            if (Handkarte.get(i).getFunktion() != 0){
                System.out.println(String.format("Handkarte %s: | %s | %s | %s",i,
                Handkarte.get(i).getFarbe(),
                Handkarte.get(i).getWert(),
                Handkarte.get(i).getNameFunktion()));
            } else {
                System.out.println(String.format("Handkarte %s: | %s | %s ",i,
                Handkarte.get(i).getFarbe(),
                Handkarte.get(i).getWert()));
            }
            
        }
    }
    public void    zeigeAblageStapel(MauMauKarte Karte)     {
        System.out.println("Dies ist die Farbe und der Wert der obersten Karte des Ablagestapels:");
        System.out.println(Karte.getFarbe() + Karte.getWert());
    }
    public MauMauKarte    legeKarte()             {
        while(true){
            System.out.println("Bitte Kartenfarbe angeben");
            input = new Scanner(System.in);
           // String Farbe ="-1";
           // String Wert ="-1";
            //Farbe = input.nextLine();
           // System.out.println(Farbe);
            //System.out.println("Bitte Kartenwert angeben");
           // Wert = input.nextLine();
            //System.out.println(Wert);
            //input.close();
            MauMauKarte lKarte;
            // geht alle Handkarten durch und sucht die ausgewählte Karte, gibt sie zurück und löscht sie aus der Handkatenliste
            //for(int i = 0; i < Handkarte.size(); i++) {
                System.out.println("Bitte gib die Nummer der Handkarte ein, die du ablegen moechtest:");
                int i = input.nextInt();
                //if ((Handkarte.get(i).getFarbe() == Farbe) && (Handkarte.get(i).getWert() == Wert)) {
                    if (Handkarte.size()>= i && 0 <= i){
                    lKarte = new MauMauKarte (Handkarte.get(i).getID());
                    System.out.println(lKarte.getFarbe());
                    System.out.println(lKarte.getWert());
                    Handkarte.remove(i);
                    return lKarte;
               // } 
            }
            //System.out.println(String.format("Deine Eingabe %S %S entspricht nicht deinen Handkarten.",Farbe,Wert));
         }
        
    }
    public void     nehmeKarte(MauMauKarte nKarte) {super.Handkarte.add(nKarte);}
    public String    wunschFarbe()           {
        input = new Scanner(System.in);
        String Farbe = "-1";
        System.out.println("Welche Farbe wird gewuenscht? [Karo, Kreuz, Pic, Herz]");
        Farbe = input.nextLine();
        
        //input.close();
        return Farbe;
    }
    public void platzbestaetigen(){
        input = new Scanner(System.in);
        String temp = "-1";
        temp = input.nextLine();
        //input.close();
        return;
    }

    public String menu(){
        System.out.println("Was moechtest du tun?");
        System.out.println("1: Handkarte ablegen");
        System.out.println("2: Karte ziehen");
        input = new Scanner(System.in);
        String eingabe ="-1";
        eingabe = input.nextLine();
        
        //input.close();
        return eingabe;
    }

}
