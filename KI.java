/**
 * KI Klasse
 * <p>
 * Unterklasse von Spieler
 * @version 1.0.
 * @author Clemens Reichert
 * 
 */

import java.util.*;

public class KI extends Spieler{
    private Karte Karte;
    public KI(String Name)              {super(Name);}
    public void     zeigeHandkarte()        {}
    public void    zeigeAblageStapel(Karte Karte)     {
        this.Karte = Karte;
    }
    public Karte    legeKarte()             {
        Karte lKarte;
        for(int i = 0; i < Handkarte.size(); i++) {
            if (Karte.getFarbe() == Handkarte.get(i).getFarbe()) {
                lKarte = Handkarte.get(i);
                Handkarte.remove(i);
                return lKarte;
            }
            else if (Karte.getWert() == Handkarte.get(i).getWert()) {
                lKarte = Handkarte.get(i);
                Handkarte.remove(i);
                return lKarte;
            }

        }
        lKarte =  new Karte(0);
        return lKarte;

    }
    public void     nehmeKarte(Karte Karte) {super.Handkarte.add(Karte);}
    public String      wunschFarbe()           {
        int cntKreuz = 0;
        int cntKaro = 0;
        int cntPic = 0;
        int cntHerz = 0;
        List<Integer> Farben = new ArrayList<Integer>();
        for(int i = 0; i < Handkarte.size(); i++) {
            switch (Handkarte.get(i).getFarbe()) {
                case "Kreuz":
                    cntKreuz++;
                    break;
                case "Karo":
                    cntKaro++;
                    break;
                case "Pic":
                    cntPic++;
                    break;
                case "Herz":
                    cntHerz++;
                    break;
                default:
                    break;
            }
        }
        Farben.add(cntKreuz);
        Farben.add(cntKaro);
        Farben.add(cntPic);
        Farben.add(cntHerz);
        Collections.sort(Farben);
        if (Farben.get(Farben.size()-1) == cntKreuz) {return "Kreuz";} 
        else if (Farben.get(Farben.size()-1) == cntKaro) {return "Karo";}
        else if (Farben.get(Farben.size()-1) == cntPic) {return "Pic";}
        else if (Farben.get(Farben.size()-1) == cntHerz) {return "Herz";}
        else {return "";}
    }
}

