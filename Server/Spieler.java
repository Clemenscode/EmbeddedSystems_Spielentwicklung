/**
  *
  * Beschreibung
  *
  * @version 1.0 vom 05.12.2016
  * @author Johannes Gundlach
  */
import java.util.ArrayList;
import java.util.List;


public class Spieler {
  private String name;
  private List<Karte> handkarten = new ArrayList<Karte>();
  private int abgelegteKarten;
  private boolean istDran;
  private int ranking = 0; //startspieler ranking 10 = hoch 1 = gering
  private String ip;
  private int port;

  public Spieler(String n, String i, int p) {
    name = n;
    ip = i;
    port = p;
  }
  public int getPort(){
    return port;
    }
  public String getIP() {
    return ip;
  }

  public String getName() {
    return name;
  }

  public void setName(String name){
    this.name = name;
    }
  public void aufnehmen(Karte karte) {
    handkarten.add(karte);
  }

  public void setAbgelegteKarten(int pWert) {
    abgelegteKarten = pWert;
  }

  public boolean getIsDran() {
    return istDran;
  }

  public boolean verloren() {
    //todo verloren implementieren
    return false;
  }

  public int gibAnzahlHandkarten() {
    return handkarten.size();
  }

  public int gibKartenWert(int pKartenNr) {
    return handkarten.get(pKartenNr).getWert();
  }

  public Karte ablegen(int pKartenNr) {
    int pNumber = pKartenNr - 1;

    if ((pNumber >= 0) && (pNumber < handkarten.size())) {
      Karte abgelegteKarte = handkarten.get(pNumber);
      handkarten.remove(pNumber);
      abgelegteKarten++;

      return abgelegteKarte;
    } else {
      return null;
    }
  }

  public void setIstDran(boolean pIstDran) {
    istDran = pIstDran;

    if (!pIstDran) {
      abgelegteKarten = 0;
    } // end of if
  }

  public int getAbgelgegteKarten() {
    return abgelegteKarten;
  }

  public String zeigeHandkarten() {
    String s = "";

    for (int i = 0; i < handkarten.size(); i++) {
      s += (handkarten.get(i).getWert() + ",");
    } // end of for

    if (s.length() == 0) {
      return null;
    } else {
      //entfernt das letze Komma bevor es returned wird
      return (s.substring(0, s.length() - 1));
    }
  }

  public String getKarten() {
    String s = name + "\n";

    for (int i = 0; i < handkarten.size(); i++) {
      s += (handkarten.get(i).getWert() + "\n");
    } // end of for

    return s;
  }

  public void setRanking(int rank) {
    ranking = rank;
  }

  public int getRanking() {
    return ranking;
  }

  public Karte gibKarte(int pKartenNr) {
    return handkarten.get(pKartenNr);
  }
}
