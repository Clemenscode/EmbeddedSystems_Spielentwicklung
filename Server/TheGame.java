/**
  *
  * Beschreibung
  *
  * @version 1.0 vom 05.12.2016
  * @author Alpay E., Daniel R., Johannes G., Kai M.
  */
import java.util.ArrayList;
import java.util.List;


public class TheGame {
  private int spielerAnzahl = 0;
  private final int MAX_SPIELERANZAHL = 5;
  private int handkartenLimit;
  private Deck d;
  private int minKartenAblegen = 2;
  private Spieler aktSpieler;
  private Stack<Karte> aufwaerts1 = new Stack<Karte>();         
  private Stack<Karte> aufwaerts2 = new Stack<Karte>();
  private Stack<Karte> abwaerts1 = new Stack<Karte>();
  private Stack<Karte> abwaerts2 = new Stack<Karte>();
  private Queue<Spieler> spieler = new Queue<Spieler>();
  private List<Spieler> spielerFertig = new ArrayList<>();
  private Queue<Spieler> spielerImSpiel = new Queue<Spieler>();  // speichert alle sich im Spiel befindenen Spieler ohne Veränderung (für Neustart) - nur beim Verlassen wird der Spieler gelöscht.
  private int spielerRanking = 0;
  private boolean spielVorbei = false;
  private Spieler rankSpieler;
  private boolean spielBegonnen = false;
  private int aktPhase = 0;
  private boolean gewonnen = false;
  private int  restKarten = 0;
  private long startTime;
  private long endTime;
  private Queue<String> ipAdressen = new Queue();     //Kai13.02. Neue Queue für IP-Adressen, bevor man sich Namen aussucht und er einen Spieler anlegt
  private int ipanzahl = 0;
  
  
  public TheGame() {
  }
  
  // legt das Limit fest, welches bestimmt wie viele Handkarten abgelegt werden müssen
  public void setMinKartenAblegen(int pLimit) {
    minKartenAblegen = pLimit;
  }
  
  // return die aktuelle Spieleranzahl
  public int getSpielerAnzahl() {
    return spielerAnzahl;
  }
  
  // erhöht die Spieleranzahl um 1
  public void erhoeheSpielerAnzahl() {
    spielerAnzahl = spielerAnzahl + 1;
  }
  
  // final Methode! gibt die max Anzahl an Spieler zurück, die bei einem Spiel dabei sein dürfen
  public int getMaxSpielerAnzahl() {
    return MAX_SPIELERANZAHL;
  }
  
  // return den pContent (Spieler) der an der Reihe ist.
  public Spieler getAktSpieler() {
    return aktSpieler;
  }
  
  // return das Limit, welches bestimmt wie viele Handkarten abgelegt werden müssen
  public int getMinKartenAblegen() {
    return minKartenAblegen;
  }
  
  public boolean spielVorbei() {
    return spielVorbei;
  }
  
  public boolean spielerHinzufuegen(Spieler pSpieler) {
    if (getSpielerAnzahl() == 0) {
      spieler.enqueue(pSpieler);
      erhoeheSpielerAnzahl();
      aktSpieler = pSpieler;
      spielerImSpiel.enqueue(pSpieler);
      return true;
    } // end of if
    
    if (getSpielerAnzahl() < getMaxSpielerAnzahl()) {
      spieler.enqueue(pSpieler);
      erhoeheSpielerAnzahl();
      spielerImSpiel.enqueue(pSpieler);
      return true;
    } // end of if
    //funktioniert
    
    return false;
  }
  
  public void IPHinzufuegen(String pIP) {                                     //Kai13.02.
    ipAdressen.enqueue(pIP);
    ipanzahl = ipanzahl + 1;
  }
  
  public int getIPAnzahl(){
    return ipanzahl;
  }
  
  
  
  public void starten() {
    if (getSpielerAnzahl() > 0) {
      aufwaerts1.push(new Karte(1));
      aufwaerts2.push(new Karte(1));
      abwaerts1.push(new Karte(100));
      abwaerts2.push(new Karte(100));
      startSpielerFinal();
      rankSpieler = aktSpieler;
      aktPhase = 2;
      startTime = System.nanoTime();
      //funktioniert!
    } // end of if
  }
  
  public void startSpielerFestlegen(Spieler pSpieler, int pRanking) {
    if (pRanking > rankSpieler.getRanking()) {
      rankSpieler = pSpieler;
    }
    
    if (pSpieler.getRanking() == 0) {
      spielerRanking++;
    } // end of if
    
    pSpieler.setRanking(pRanking);
  }
  
  public void startSpielerFinal() {
    aktSpieler = spieler.front();
    
    while (aktSpieler.getName() != rankSpieler.getName()) {
      spieler.enqueue(spieler.front());
      spieler.dequeu();
      aktSpieler = spieler.front();
    } // end of while
  }
  
  public int getSpielerRanking() {
    return spielerRanking;
  }
  
  public Karte ziehen() {
    return d.ziehen();
  }
  
  public void zugBeenden() {
    naechsterSpieler();
  }
  
  public boolean getGewonnen() {
    return gewonnen;
  }
  
  public String ablegen(int pStapelNr, int pKartenNr) {
    if ((0 < pStapelNr) && (pStapelNr < 5) && (0 < pKartenNr) &&
    (pKartenNr < (getAktSpieler().gibAnzahlHandkarten() + 1))) {
      if (zugGueltig(pStapelNr, pKartenNr)) {
        switch (pStapelNr) {
          case 1:
          aufwaerts1.push(getAktSpieler().ablegen(pKartenNr));
          
          break;
          
          case 2:
          aufwaerts2.push(getAktSpieler().ablegen(pKartenNr));
          
          break;
          
          case 3:
          abwaerts1.push(getAktSpieler().ablegen(pKartenNr));
          
          break;
          
          case 4:
          abwaerts2.push(getAktSpieler().ablegen(pKartenNr));
          
          break;
        } // end of switch
        
        //prüft ob der aktuelle Spieler, wenn er noch nicht seinen Zug beenden darf, ob er noch ablegen kann
        if (aktSpieler.getAbgelgegteKarten() < minKartenAblegen) {
          spielVorbei = verloren(aktSpieler);
        } // end of if
        
        return ("Deine Karte wurde abgelegt.");
      } // end of if
      else {
        return ("Ungültiger Zug!");
      }
    } // end of if
    else {
      return ("Ungültiger Zug!");
    }
  }
  
  public void naechsterSpieler() {
    //verändert 14.02.17 - hier muss die Überprüfung hin, da es sonst zu Problemen mit der GUI kam.
    if (d.istLeer() && (aktSpieler.gibAnzahlHandkarten() == 0)) {
      spielerFertig.add(aktSpieler);
      spieler.dequeu();
      if (spieler.isEmpty()) {
        gewonnen = true;
        return;
      } // end of if
    }
    else {
      spieler.enqueue(spieler.front());
      spieler.dequeu();
      //nachziehen
      while ((aktSpieler.getAbgelgegteKarten() > 0) && !d.istLeer()) {
        nachziehen();
        aktSpieler.setAbgelegteKarten(aktSpieler.getAbgelgegteKarten() - 1);
      }
      aktSpieler.setAbgelegteKarten(0); //falls das Deck leer ist; 
    } // end of if-else
    
    //hier muss der akt. Spieler ebenfalls verändert werden!
    aktSpieler = spieler.front();
    
    if (d.istLeer()) {
      setMinKartenAblegen(1);
    } // end of if
    
    spielVorbei = verloren(aktSpieler);
    
    //methode getestet funktioniert!
  }
  
  public void naechsterSpielerAusteilen() {
    spieler.enqueue(spieler.front());
    spieler.dequeu();
    aktSpieler = spieler.front();
  }
  
  public void nachziehen() {
    aktSpieler.aufnehmen(d.ziehen());
    
    //funktioniert
  }
  
  //version 2 von johannes, gleicher Fehler wie bei Alpay, pKartenNr ist nicht der Wert der eigentlichen Karte! 
  public boolean zugGueltig(int pStapelNr, int pKartenNr) {
    pKartenNr--;
    
    //1+2 aufwärts, 3+4 abwärts
    boolean allowed = false;
    
    switch (pStapelNr) {
      case 1:
      allowed = ((getAktSpieler().gibKartenWert(pKartenNr) > aufwaerts1.top()
      .getWert()) ||
      (getAktSpieler().gibKartenWert(pKartenNr) == (aufwaerts1.top()
      .getWert() -
      10)));
      
      break;
      
      case 2:
      allowed = ((getAktSpieler().gibKartenWert(pKartenNr) > aufwaerts2.top()
      .getWert()) ||
      (getAktSpieler().gibKartenWert(pKartenNr) == (aufwaerts2.top()
      .getWert() -
      10)));
      
      break;
      
      case 3:
      allowed = ((getAktSpieler().gibKartenWert(pKartenNr) < abwaerts1.top()
      .getWert()) ||
      (getAktSpieler().gibKartenWert(pKartenNr) == (abwaerts1.top()
      .getWert() +
      10)));
      
      break;
      
      case 4:
      allowed = ((getAktSpieler().gibKartenWert(pKartenNr) < abwaerts2.top()
      .getWert()) ||
      (getAktSpieler().gibKartenWert(pKartenNr) == (abwaerts2.top()
      .getWert() +
      10)));
      
      break;
    } // end of switch
    
    return allowed;
    
    //funktioniert
  }
  
  /**
  * Verteilt die Karten vom Kartenstapel an die verschiedenen
  * Spieler in der Teilnehmerliste, indem er beim 1. anfängt,
  * dann dem 2.,3.,4. [...] auch jeweils eine Karte gibt bis
  * er allen eine Karte gegeben hat und wieder von vorne anfängt,
  * bis jeder Spieler die durch 'maxHandkartenAnzahl' festgelegte
  * Handkartenzahl hat.
  * @author Johannes Gundlach, Daniel Röthgen
  * @Version: 1.12.16
  **/
  public void austeilen() {
    aktSpieler = spieler.front();
    
    for (int i = 0; i < handkartenLimit; i++) {
      for (int j = 0; j < spielerAnzahl; j++) {
        getAktSpieler().aufnehmen(d.ziehen());
        naechsterSpielerAusteilen();
      } // end of for
    } // end of for
  }
  
  public boolean zugDarfBeendetWerden(Spieler pSpieler) {
    if (pSpieler.getName().equals(aktSpieler.getName())) {
      if (pSpieler.getAbgelgegteKarten() >= getMinKartenAblegen()) {
        return true;
      } // end of if
    }
    
    return false;
  }
  
  public String zeigeHandkarten(Spieler pSpieler) {
    return pSpieler.zeigeHandkarten();
  }
  
  public String zeigeStapel() {
    String temp = "";
    temp += (aufwaerts1.top().getWert() + ",");
    temp += (aufwaerts2.top().getWert() + ",");
    temp += (abwaerts1.top().getWert() + ",");
    temp += abwaerts2.top().getWert();
    
    return temp;
  }
  
  public void setHandkartenLimit() {
    if (spielerAnzahl == 1) {
      handkartenLimit = 8;
    } else if (spielerAnzahl == 2) {
      handkartenLimit = 7;
    } else if ((3 <= spielerAnzahl) &&
    (spielerAnzahl <= getMaxSpielerAnzahl())) {
      handkartenLimit = 6;
    } // end of if-else
  }
  
  /**
  * Die Methode'verlorenSpieler(Spieler pSpieler)' überprüft, ob ein Spieler nicht mehr legen kann, und folglich das Spiel verloren ist.
  * Wenn dies der Fall ist wird true zurück gegeben, andernfalls false. Die Anzahl der minimal noch zu legenden Karten die hierfür eintscheident ist
  * wird durch minKartenAblegen - pSpieler.getAbgelgegteKarten() errechnet.
  *
  * @version 1.0 vom 05.12.2016
  * @author Johannes Gundlach
  * @param pSpieler
  */
  public boolean verloren(Spieler pSpieler) {
    if (gewonnen) {
      return false;
    } // end of if
    
    int pSpielerMinAblegen = minKartenAblegen - pSpieler.getAbgelgegteKarten();
    
    if (pSpielerMinAblegen < 2) {
      for (int j = 0; j < pSpieler.gibAnzahlHandkarten(); j++) {
        for (int k1 = 1; k1 <= 4; k1++) {
          int firstCard = pSpieler.gibKartenWert(j);
          
          if (zugGueltigWert(k1, firstCard) ||
          ((((k1 == 1) || (k1 == 2)) &&
          (firstCard == (getStapelKarte(k1) - 10))) ||
          (((k1 == 3) || (k1 == 4)) &&
          (firstCard == (getStapelKarte(k1) + 10))))) {
            return false;
          } // end of if
        } // end of for
      }
    } else {
      Spieler current = pSpieler;
      int firstCard = -1;
      int firstStackPosition = -1;
      
      for (int i = 0; i < current.gibAnzahlHandkarten(); i++) {
        for (int k1 = 1; k1 <= 4; k1++) {
          if (zugGueltigWert(k1, current.gibKartenWert(i))) {
            //speichert den Wert der ersten Karte, die gelegt werden könnte.
            firstCard = current.gibKartenWert(i);
            
            for (int j = 0; j < current.gibAnzahlHandkarten(); j++) {
              for (int k2 = 1; k2 <= 4; k2++) {
                int secondCard = current.gibKartenWert(j);
                
                if (zugGueltigWert(k2, secondCard) &&
                (secondCard != firstCard)) {
                  return false;
                } else if ((k1 == k2) && (secondCard != firstCard) && //überprüft, ob die erste Karte 10 größer oder kleiner ist und 
                // der Zug dennoch legitim ist.
                (((firstCard == (getStapelKarte(k1) - 10)) &&
                (secondCard > firstCard) &&
                ((k1 == 1) || (k1 == 2))) ||
                ((firstCard == (getStapelKarte(k1) + 10)) &&
                (secondCard < firstCard) &&
                ((k1 == 3) || (k1 == 4))))) {
                  return false;
                }
              } // end of for
            }
          } // end of if
        } // end of for
      } // end of for    
    }
    
    if (pSpielerMinAblegen < 1) {
      return false;
    } else {
      return true;
    }
  }
  
  public boolean zugGueltigWert(int pStapelNr, int pKartenWert) {
    //1+2 aufwärts, 3+4 abwärts
    boolean allowed = false;
    
    if ((pKartenWert > 99) || (pKartenWert < 2)) {
      return false;
    } // end of if
    
    switch (pStapelNr) {
      case 1:
      allowed = ((pKartenWert > aufwaerts1.top().getWert()) ||
      (pKartenWert == (aufwaerts1.top().getWert() - 10)));
      
      break;
      
      case 2:
      allowed = ((pKartenWert > aufwaerts2.top().getWert()) ||
      (pKartenWert == (aufwaerts2.top().getWert() - 10)));
      
      break;
      
      case 3:
      allowed = ((pKartenWert < abwaerts1.top().getWert()) ||
      (pKartenWert == (abwaerts1.top().getWert() + 10)));
      
      break;
      
      case 4:
      allowed = ((pKartenWert < abwaerts2.top().getWert()) ||
      (pKartenWert == (abwaerts2.top().getWert() + 10)));
      
      break;
    } // end of switch
    
    return allowed;
  }
  
  private int getStapelKarte(int n) {
    int toReturn;
    
    switch (n) {
      case 1:
      toReturn = aufwaerts1.top().getWert();
      
      break;
      
      case 2:
      toReturn = aufwaerts2.top().getWert();
      
      break;
      
      case 3:
      toReturn = abwaerts1.top().getWert();
      
      break;
      
      case 4:
      toReturn = abwaerts2.top().getWert();
      
      break;
      
      default:
      toReturn = -1;
    } // end of switch
    
    return toReturn;
  }

  public void spielerVerlassen(Spieler pSpieler) { //20.02.17 - GameV15 -> viele Kleinigkeiten umgeändert um Bugs zu beheben.
      spielerImSpielLoeschen(pSpieler); //löscht den Spieler aus der SpielerImSpielListe
      //24.02.17
      //legt die übrigen, falls vorhanden, Handkarten auf das Deck zurück     
      int hZahl = pSpieler.gibAnzahlHandkarten();
      if (hZahl !=0) {   //falls Handkarten vorhanden sind...
      for (int i = 0; i < hZahl; i++) {  //legt die restlichen Handkarten zurück aufs Deck
        d.add(pSpieler.gibKarte(i));
      } // end of for
      d.mischen();   //mischt das Deck neu
    } // end of if
    try {
       if (aktSpieler.getName().equals(pSpieler.getName())) {
    spieler.dequeu();
    aktSpieler=spieler.front();
    spielerAnzahl--;  
    } // end of if
    else {
    for (int i =0;i<spielerAnzahl ;i++ ) {
      if (spieler.front().getName().equals(pSpieler.getName())) {
        spieler.dequeu();
        spielerAnzahl--;
      } // end of if
      spieler.enqueue(spieler.front());
      spieler.dequeu();
    } // end of for    
    }   
    } catch(Exception e) {
      
    } 
   
  }
  
  public void spielerImSpielLoeschen(Spieler pSpieler){  //20.02.17 - GameV15 -> löscht einen Spieler aus der spielerImSpielListe -> wird in SpielerVerlassen aufgerufen.
    //verändert die spielerImSpiel Liste:
    for (int i = 0;spielerAnzahl>i ;i++ ) {
      if (spielerImSpiel.front().getName().equals(pSpieler.getName())) {
        spielerImSpiel.dequeu();
      } // end of if
      spielerImSpiel.enqueue(spielerImSpiel.front());
      spielerImSpiel.dequeu();
    } // end of for
    }
    
  public void IPLoeschen(String pIP) {
    //24.02 Daniel - GameV18 -> alte Version hatte einen Fehler drin, es wurde immer eine IP-Adresse gelöscht, auch wenn sie bereits vorher gelöscht wurde.
    for (int i = 0;i<ipanzahl ;i++ ) {
      if (ipAdressen.front().equals(pIP)) {
        ipAdressen.dequeu();
        ipanzahl--;
      } // end of if
      ipAdressen.enqueue(ipAdressen.front());
      ipAdressen.dequeu();
    } // end of for
  }
  
  public boolean ipVorhanden(String pIP) {                         //Kai13.02. Methode an neue Queue für IPs angepasst
    if (getIPAnzahl() == 0) {
      return false;
    }
    
    for (int i = 0; i < getIPAnzahl(); i++) {
      String tempIP = ipAdressen.front();
      
      if (tempIP.equals(pIP)) {
        return true;
      } // end of if
      
      ipAdressen.enqueue(tempIP);
      ipAdressen.dequeu();
    } // end of for
    
    return false;
  }
  
  public boolean nameVorhanden(String pName) {
    if (getSpielerAnzahl() == 0) {
      return false;
    }
    
    for (int i = getSpielerAnzahl(); i > 0; i--) {
      String tempName = aktSpieler.getName();
      
      if (tempName.equals(pName)) {
        return true;
      } // end of if
      
      naechsterSpielerAusteilen();
    } // end of for
    
    return false;
  }
  
  public String zeigeSpielerliste() {  //20.02.17 - GameV15 -> verwendet nun die SpielerImSpiel Queue, da diese bei allen Phasen gleich bleibt.
    String temp = new String();
    if (getSpielerAnzahl()>0) {
      for (int i = getSpielerAnzahl(); i > 0; i--) {
        temp = temp + spielerImSpiel.front().getName() + ", ";
        Spieler pTemp = spielerImSpiel.front();
        spielerImSpiel.dequeu();
        spielerImSpiel.enqueue(pTemp);
      }
      
      return temp;//neue Verarbeitung -> Komma wird benötigt = temp.substring(0, temp.length() - 2); // gibt den String ohne das letzte Komma zurück.
    } // end of if
    return ("Niemand");
  }
  
  public String zeigeAktPhase() {
    String temp = new String("Error");
    
    switch (aktPhase) {
      case 0:
      temp = ("Das Spiel befindet sich in der Anmeldephase.");
      
      break;
      
      case 1:
      temp = ("Das Spiel befindet sich in der Rankingphase.");
      
      break;
      
      case 2:
      temp = ("Das Spiel befindet sich in der Spielphase.");
      
      break;
      
      case 3:
      temp = ("Das Spiel befindet sich in der Auswertungsphase.");
      
      break;
    } // end of switch
    
    return temp;
  }
  
  public int getAktPhase() {
    return aktPhase;
  }
  
  public boolean starteRanking(int pDeck) {
    if (getSpielerAnzahl() <= 0) { // hier könnte man eine mindest Spieleranzahl einstellen
      return false;
    } // end of if
    // wenn die mindest Spieleranzahl erreicht ist:
    
    d = new Deck(pDeck); // debugdeck ---------<---------<------<------<--------------------------------------------------
    spielBegonnen = true;
    setHandkartenLimit();
    austeilen();
    rankSpieler = aktSpieler;
    aktPhase = 1;
    return true;
  }
  
  public void setAktPhase(int pPhase) {
    aktPhase = pPhase;
  }
  
  public Spieler ipZuSpieler(String pIP) {  //20.02.17 - GameV15 -> verwendet nun die SpielerImSpiel Queue, da diese bei allen Phasen gleich bleibt.
    Spieler temp = new Spieler("Error", "Error", -1);
    //neue Version.
    if (!spielerImSpiel.isEmpty()) {
      for (int i = getSpielerAnzahl();i>0 ;i-- ) {
        if (spielerImSpiel.front().getIP().equals(pIP)) {
          temp = spielerImSpiel.front();
        } // end of if
        spielerImSpiel.enqueue(spielerImSpiel.front());
        spielerImSpiel.dequeu();
      } // end of for
    } // end of if
     
    return temp;
  }

  
  public String starteSpielphase() {
    if (getSpielerRanking() != getSpielerAnzahl()) {
      return ("\n---Es müssen erst alle Spieler ihr Ranking abgeben, bevor das Spiel gestartet\nwird!\n---");
    }
    
    starten();
    
    return ("\n" + "Das Spiel läuft...");
  }
  
  public String werAmZug(String pIP) {
    if (pIP.equals(aktSpieler.getIP())) {
      return ("Du bist an der Reihe!");
    } // end of if
    else {
      return "ID_MSG";
    } // end of if-else
  }
  public String amZugName(){
    return (aktSpieler.getName() + "< ist an der Reihe.");
  }
  public boolean amZug(String pIP) {
    return pIP.equals(aktSpieler.getIP());
  }
  
  public String nachziehstapelLeer() {
    if (d.istLeer()) {
      return ("Das Deck ist leer.");
    } // end of if 
    else {
      return ("Das Deck ist nicht leer.");
    } // end of if-else
  }
  
  public boolean hatSpielBegonnen() {
    return spielBegonnen;
  }
  
  //Auswertungsbefehle
  public int restKarten() {
    
    if (restKarten==0) {
      if (!d.istLeer()) { // wenn das Deck nicht leer ist, werden die restlichen Karten die im Deck sind gezählt.
        
        while (!d.istLeer()) {
          d.ziehen();
          restKarten++;
        } // end of while
      } // end of if
    }
    restKarten = restKarten + restHandkarten();
    return (restKarten);
  }
  
  public int restHandkarten() { //Das Gegenteil vom austeilen, jede Karte die ein Spieler noch auf der Hand hat wird gezählt.
    
    int a = 0;
    
    while (!spieler.isEmpty()) {
      aktSpieler = spieler.front();
      a = a + aktSpieler.gibAnzahlHandkarten();
      spieler.dequeu();
    } // end of while
    
    return a;
  }
  public void setEndTime(){     //setzt die Endzeit, nachdem das Spiel definitiv vorbei ist fest.
    endTime = System.nanoTime();
  }
  
  public String spielZeit(){   // berechnet die Dauer des Spiels in Minuten und Sekunden.         //Kai12.02. 
    long duration = (endTime - startTime)/1000000000;
    long pTimeSec = duration;
    long pTimeMin = duration/60;
    String min = "Minuten";
    String sec = "Sekunden";
    if (pTimeSec>=60) {
      pTimeSec = pTimeSec - pTimeMin*60;
    } // end of if
    if (pTimeMin == 1) {
      min = "Minute";
    }else{
      min = "Minuten";
    } // end of if
    if (pTimeSec == 1) {
      sec = "Sekunde";
    }else{
      sec = "Sekunden";
    } // end of if
    return("Dauer: "+ pTimeMin + " " + min + " und " + pTimeSec + " " + sec);
  }
  
  public String score(){
    //long teiler =  1000000000*100;
    //long pTimeSec = (endTime - startTime)/ teiler;
    long pRestKarten = restKarten() * 100;
    //long pHighscore = 9400 - pRestKarten;
    //pHighscore = pHighscore / pTimeSec;
    
    
    long pTimeSec = (endTime - startTime)/1000000000;
    long score = 9400 - pRestKarten - pTimeSec;
    if (score < 0) {
      score = 0;
    } // end of if
    return ("Es wurde folgende Punktzahl erreicht: " + score+"."); 
  }
  
  public int restDeck(){
    return d.restDeck();
  }  
  
  public Queue neuStart(){  //daniel15.02 -> gibt die Spielerliste zurück, welche Spieler am Ende des Spiels noch dabei waren ->für Restart.
    return spielerImSpiel;
  }
  
  public boolean erlaubterName(String pName){ // Überprüft ob der Name nur aus Buchstaben und Zahlen besteht (+ best. Zeichen).
      if (pName.equals("")) { //verhindert das man ohne Namen beitreten könnte.
      return false;
    } // end of if
    String pTemp = pName.replaceAll("[[^\\w\\däüöÄÜÖß\\_\\+\\-\\. ]]", "");   //24.02.17 - GameV18 ->neuer filter
    String pTemp2 = pTemp.replaceAll(" ","");
    //String pTemp = pName.replaceAll("[^\\w]", ""); //erstetzt alle Chars die keine Buchstaben aus dem Standard-Alphabet sind und alle Zeichen die keine Zahlen von 1-9 beinhalten durch leerzeichen
    
    System.out.println(pTemp2);
  
    if (pTemp2.equals(pName)) {   //die beiden Strings sind nicht mehr gleich, falls untererlaubte Zeichen gefunden wurden
      return true;
    } // end of if
    
    return false;
    }
  public boolean richtigeSyntaxt(String pEingabe){
    //Methode überprüft ob nur ein '<' & ein '>' in der Eingabe vorkommen.
  int counter=0;// Zähler
  int counter2=0;
  for(int i=pEingabe.length()-1;i>=0;i--){
    if(pEingabe.charAt(i)=='<') counter++;
    if(pEingabe.charAt(i)=='>') counter2++;
  }
    if (counter == 1 && counter2 == 1) {
    return true;  
    } // end of if
    return false;
    }
   public boolean playerInGame(String pIP){
   boolean temp = false;
    if (!spielerImSpiel.isEmpty()) {
      for (int i = getSpielerAnzahl();i>0 ;i-- ) {
        if (spielerImSpiel.front().getIP().equals(pIP)) {
          temp = true;
        } // end of if
        spielerImSpiel.enqueue(spielerImSpiel.front());
        spielerImSpiel.dequeu();
      } // end of for
    } // end of if
    return temp; 
     }   
} // end of class TheGame
