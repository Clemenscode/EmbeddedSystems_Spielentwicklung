/**
 * Spieler Oberklasse
 * <p>
 * @version 1.0.
 * @author Clemens Reichert
 * 
 */

import java.util.*;

public class Spieler {
    private String  Name;
    private int     AnzahlGewinne;
    private boolean Bereit;
    private boolean anderReihe;
    protected List<MauMauKarte> Handkarte;

    public Spieler(String Name)             {
        this.Name = Name;
        Handkarte = new ArrayList<MauMauKarte>();
    }
    public String   getName()               {return Name;}
    public boolean  getAnDerReihe()         {return anderReihe;}
    public String   letzteKarte()           {
        if (Handkarte.isEmpty()) {
            return "MauMau";
        }
        else {
            return null;
        }
        
    }
    public void     setAnzahlGewinne()      {AnzahlGewinne++;}
    public int      getAnzahlGewinne()      {return AnzahlGewinne;}
    public boolean  getBereit()             {return Bereit;}
    public void     setBereit()             {Bereit = true;}
    public int      getAnzahlHandkarten()   {return Handkarte.size();}
}
