public class MauMau {
    private Deck ziehstapel ;
    private Deck ablagestapel ;
    private int anzahlSpieler;
    private int anzahlSpielerImSpiel;
    private int spielphase;
    private int maxSpieleranzahl = 5;
    private int handkartenLimit;
    private Queue <Mensch> alleMenschSpieler = new Queue();
    private Queue <Mensch> spielerFertig = new Queue();
    private Queue <Mensch> spielerImSpiel = new Queue();
    private boolean rundeVorbei = false;
    private SonderKarte sk;

    public MauMau (int _maxSpieleranzahl){
        maxSpieleranzahl = _maxSpieleranzahl;
    }
    //Funktionen die in meheren Phasen benoetigt werden:
    public void getSpielerNamen(int _Queue){
        switch (_Queue) {
            case 0: //alleMenschSpieler
                gsn(alleMenschSpieler);
                break;
            case 1: //alle Spieler im Spiel
                gsn(spielerImSpiel);
                break;
            case 2: //alle Spieler die Fertig sind.
                gsn(spielerFertig);
                break;
            default:
                break;
        }
    }

    private void gsn(Queue<Mensch> temp){
        if (!temp.isEmpty()){
            Mensch firstSpieler = temp.front();
            temp.enqueue(firstSpieler);
            temp.dequeu();
            System.out.println(String.format("Im Spiel befinden sich die Spieler: "));
            System.out.print(String.format("%s",firstSpieler.getName()));
            while( temp.front().getName() != firstSpieler.getName() ) {
                System.out.print(String.format(" , %s",temp.front().getName()));
                temp.enqueue(temp.front());
                temp.dequeu();
            }
            System.out.println(".");
        } else {
            System.out.println("Error - Die Spielerliste ist leer.");
        }
    }
    public void karteZiehen(){
        if (anzahlSpieler < 2){
            System.out.println(String.format("Error - Die Spieleranzahl betraegt %s Spieler und ist damit zu klein.",anzahlSpieler));
        } else {
            Mensch aktlSpieler = spielerImSpiel.front();
            checkZiehstapel();
            aktlSpieler.nehmeKarte(ziehstapel.kartenziehen());
        }
    }

    public void checkZiehstapel(){
        if (ziehstapel.countDeck() == 0){ //Überprüft ob es noch Karten zu ziehen gibt, ansonsten wird bis auf die oberste Karte, die Karten des Ablagestapels auf den neuen Stapel gelegt.
            MauMauKarte obersteKarte = ablagestapel.kartenziehen();
            ziehstapel.erhalteDeck(ablagestapel.deckUebergeben());
            ablagestapel.karteablegen(obersteKarte);
        }
    }


    //Ab hier Methoden die zum Start des Spiels benötigt werden.
    public void setSpielphase(int _sp){
        spielphase = _sp;
    }

    public int getSpielphase(){
        return spielphase;
    }

    public void setSpielerAnzahl (int _sa){
        System.out.println(String.format("Die Spieleranzahl wurde um '%s' veraendert.",_sa));
        anzahlSpieler += _sa;
    }
    public int getSpielerAnzahl (){
        return anzahlSpieler;
    }

    public void addSpieler(String _name){
        if (getSpielphase() == 0){
            if (checkName(_name,alleMenschSpieler) == 0){
                if (getSpielerAnzahl() <= maxSpieleranzahl){
                    Mensch temp = new Mensch(_name);
                    alleMenschSpieler.enqueue(temp);
                    setSpielerAnzahl(1);
                    System.out.println(String.format("Der Spieler '%s' wurde erfolgreich dem Spiel hinzugefuegt.",_name));
                } else {
                    System.out.println(String.format("Der Spieler '%s' kann nicht mehr hinzugefuegt werden, da bereits die maximale Spileranzahl mit %s erreicht wurde.",_name, maxSpieleranzahl));
                }
            } else {
                System.out.println(String.format("Der Spieler '%s' kann nicht mehr hinzugefuegt werden, da der Name bereits im Spiel vergeben ist.",_name));
            }
        } else {
            System.out.println(String.format("Der Spieler '%s' kann nicht mehr hinzugefuegt werden, da das Spiel bereits laeuft..",_name));
        }
    }

    private int checkName(String _name,Queue<Mensch> temp){
        if (!temp.isEmpty()){
            Mensch firstSpieler = temp.front();
            temp.enqueue(firstSpieler);
            temp.dequeu();
            if (firstSpieler.getName() == _name){ return 1; }
            while( temp.front().getName() != firstSpieler.getName() ) {
                if (temp.front().getName() == _name) { return 1;}
                temp.enqueue(temp.front());
                temp.dequeu();
            }
        }
        return 0;
    }
    
    public void delSpieler(String _name){
        if (getSpielphase() == 0){
            if (checkName(_name,alleMenschSpieler) == 1){
                Mensch firstSpieler = alleMenschSpieler.front();
                if (alleMenschSpieler.front().getName() == _name){
                    alleMenschSpieler.dequeu();
                    setSpielerAnzahl(-1);
                    System.out.println(String.format("Der Spieler '%s' wurde erfolgreich aus dem Spiel entfernt.",_name)); 
                }
                else {
                    alleMenschSpieler.enqueue(alleMenschSpieler.front());
                    alleMenschSpieler.dequeu();
                    while(alleMenschSpieler.front().getName() != firstSpieler.getName()){
                        if (alleMenschSpieler.front().getName() == _name){
                            alleMenschSpieler.dequeu();
                            setSpielerAnzahl(-1);
                            System.out.println(String.format("Der Spieler '%s' wurde erfolgreich aus dem Spiel entfernt.",_name)); 
                            return;
                        }
                        alleMenschSpieler.enqueue(alleMenschSpieler.front());
                        alleMenschSpieler.dequeu();
                    }
                }
            } else {
                System.out.println(String.format("Der Spieler '%s' kann nicht aus dem Spiel geloescht werden, da er nicht Teil des Spiels ist.",_name)); 
            }
        } else {
            System.out.println(String.format("Der Spieler '%s' kann nicht aus dem Spiel geloescht werden, da die Karten bereits verteilt wurden.",_name)); 
        }
    }
    private void setHandkartenLimit(){
        int pSpieler = getSpielerAnzahl();
        switch (pSpieler) {
            case 1:
                System.out.println("Error - Es sind zu wenig Spieler im Spiel mind. 2 Spieler werden benoetigt.");
                break;
            case 2:
                handkartenLimit = 7;
                break;
            case 3:
                handkartenLimit = 6;
                break;
        
            default:
                handkartenLimit = 5;
                break;
        }
    }
    private void clearDecks(){
      ablagestapel = new Deck(0);
      ziehstapel = new Deck(32);

    }
    public void setSpieler(){
        spielerImSpiel = new Queue();
        String firstName = alleMenschSpieler.front().getName();
        spielerImSpiel.enqueue(alleMenschSpieler.front());
        alleMenschSpieler.enqueue(alleMenschSpieler.front());
        alleMenschSpieler.dequeu();
        while (alleMenschSpieler.front().getName() != firstName){
            spielerImSpiel.enqueue(alleMenschSpieler.front());
            alleMenschSpieler.enqueue(alleMenschSpieler.front());
            alleMenschSpieler.dequeu();
        }
    }
    public void austeilen(){  
        for (int i = 0; i < handkartenLimit; i++) {
             for (int j = 0; j < anzahlSpieler; j++) {
                karteZiehen();
                spielerImSpiel.enqueue(spielerImSpiel.front());
                spielerImSpiel.dequeu();
          } // end of for
        } // end of for
        //Erste Karte des Ablagestapels aufdecken:
        ablagestapel.karteablegen(ziehstapel.kartenziehen());
        sk = new SonderKarte(ablagestapel.getObersteKarte().getFunktion());
        //Überprüfen ob es eine Sonderkarte ist:
        if (sk.getFunktion() == 3){
            sk.setFarbe(ablagestapel.getObersteKarte().getFarbe());
        }
    }
    public void spielStarten(){
        if (spielphase == 0){
            //Spielstarten:
            /*
            1. Handkarten Limit festlegen
            2. Decks erstellen
            3. Decks mischen
            4. Spieler Listen zuweisen
            5. Handkarten Austeilen.
            6. Spielmodus umstellen
            7. Spiel Loop
            */
            setSpielphase(1);
            setHandkartenLimit();
            clearDecks();
            ziehstapel.mischen();
            setSpieler();
            austeilen();
            anzahlSpielerImSpiel = anzahlSpieler;
            spielSchleife();
        } else {
            System.out.println("Error - Das Spiel laeuft bereits...");
        }
    }
    //Ab hier Methoden die zum Spielen benötigt werden:

    

    public Mensch getAktuellerSpieler(){
        if (spielphase == 0){
            System.out.println("Error - Es gibt noch keinen aktuellen Spieler, das Spiel ist noch nicht gestartet.");
            return null;
        } else {
            System.out.println(String.format("Spieler: %s ist gerade an der Reihe.",spielerImSpiel.front().getName()));
            return spielerImSpiel.front();
        }
    }
    public void nextSpieler(){
        Konsole konsole = new Konsole();
        konsole.clearScreen();
        spielerImSpiel.enqueue(spielerImSpiel.front());
        spielerImSpiel.dequeu();
        Mensch aktlSpieler = getAktuellerSpieler();
        System.out.println(String.format("Wenn Spieler '%s' am Platz ist, bitte beliebige Taste druecken und mit 'ENTER' bestaetigen",aktlSpieler.getName()));
        aktlSpieler.platzbestaetigen();        
    }

    public void displaySpiel(){
        Konsole konsole = new Konsole();
        Mensch aktlSpieler = getAktuellerSpieler();
        int zug = 0;
        while (zug == 0){
            konsole.clearScreen();
            konsole.leereZeilen(3);
            getAktuellerSpieler();
            ablagestapel.zeigeObersteKarte();
            if (sk.getFunktion()==3){
                System.out.println(String.format("Warbwunsch ist: %s",sk.getFarbe()));
            }

            if (sk.getFunktion() == 2){ //Der Spieler muss ausetzen.
                System.out.println(String.format("%s muss diese Runde aussetzen. 'ENTER' um den Zug zu beenden.",aktlSpieler.getName()));
                zug = 1;
                sk.setFunktion(0);
            } else {
                if (sk.getFunktion() == 1){ //Zweiziehen...
                    System.out.println("Ziehe zwei Karten...");
                    karteZiehen();
                    karteZiehen();
                    sk.setFunktion(0);
                }
                konsole.leereZeilen(3);
                aktlSpieler.zeigeHandkarten();
                konsole.leereZeilen(2);
                zug = displayMenue(aktlSpieler);
                konsole.leereZeilen(2);
                 if (zug == 0){
                    System.out.println("Neue Eingabe mit 'ENTER' bestaetigen");
                    aktlSpieler.platzbestaetigen();
                }
            }
            
            
        }
        zugBeenden(aktlSpieler);
    }

    public int displayMenue (Mensch aktlSpieler){
        
        String eingabe = aktlSpieler.menu();
        switch (eingabe) {
            case "1":
                MauMauKarte temp = aktlSpieler.legeKarte();
                
                if (pruefezug(temp) == 1){
                    legeKarteAufStapel(temp, aktlSpieler);
                    System.out.println("Die Karte wurde abgelegt.");
                    return 1;
                } else {
                    System.out.println("Error - ungueltiger Zug.");
                    System.out.println(String.format("%s %s kann nicht auf %s %s gelegt werden",
                    temp.getFarbe(),
                    temp.getWert(),
                    ablagestapel.getObersteKarte().getFarbe(),
                    ablagestapel.getObersteKarte().getWert()));
                    aktlSpieler.nehmeKarte(temp);
                }
                return 0;
                
            case "2":
                aktlSpieler.nehmeKarte(ziehstapel.kartenziehen());
                return 1;
                
        
            default:
            System.out.println(String.format("Error - Fehlerhafte Eingabe, bitte wiederholen. Deine Eingabe war: %s",eingabe));
            return 0;    
            
        }
    }
    public int pruefezug(MauMauKarte temp){
        MauMauKarte top = ablagestapel.getObersteKarte();
         if (top.getWert() == temp.getWert()){ //Gleiche Zahl
            return 1;
        } else if ((temp.getID() % 8) == 5){ //Bube geht immer
            return 1;
        } else {
            System.out.println(sk.getFunktion());
            if (sk.getFunktion() == 3){
                System.out.println("Funktion = 3 erkannt");
                if (sk.getFarbe().equals(temp.getFarbe())){
                    System.out.println("Hier 1");
                    return 1;
                } //Gleiche Farbe des Farbwunschs

            } else if (top.getFarbe() == temp.getFarbe()){ //Gleiche Farbe
                return 1;
            }
            return 0;
        }
        
    }
    public void legeKarteAufStapel(MauMauKarte temp, Mensch aktlSpieler){
        sk = new SonderKarte(temp.getFunktion());
        //Überprüfen ob es eine Sonderkarte ist:
        if (temp.getFunktion() == 3){
            sk.setFarbe(aktlSpieler.wunschFarbe());
        }
        ablagestapel.karteablegen(temp);
    }
    public void zugBeenden(Mensch aktlSpieler){
        System.out.println("Dein Zug war gueltig.");
        System.out.println("Bitte druecke eine beliebige Taste um deinen Zug zu beenden und bestaetige mit 'ENTER'");
        aktlSpieler.platzbestaetigen();
        //TODO: Auswerten ob der Spieler keine Handkarten mehr hat.
        if (checkWin(aktlSpieler) == 0) {
            nextSpieler();
        } //muss nicht aufgerufen werden, wenn der Spieler fertig ist.
        
    }

    public int checkWin(Mensch aktlSpieler){
        Konsole konsole = new Konsole();
        if (aktlSpieler.getAnzahlHandkarten()==1){
            aktlSpieler.letzteKarte();
            return 0;
        } else if (aktlSpieler.getAnzahlHandkarten()==0){
            System.out.println(String.format("%S hat keine Handkarten mehr!'ENTER' zum bestaetigen...",aktlSpieler.getName()));
            spielerFertig.enqueue(aktlSpieler);
            spielerImSpiel.dequeu();
            aktlSpieler.platzbestaetigen();
            konsole.clearScreen();
            System.out.println(String.format("Wenn Spieler '%s' am Platz ist, bitte beliebige Taste druecken und mit 'ENTER' bestaetigen",spielerImSpiel.front().getName()));
            spielerImSpiel.front().platzbestaetigen(); 
            anzahlSpielerImSpiel --;

            if (anzahlSpielerImSpiel < 2){
                spielerFertig.enqueue(spielerImSpiel.front());
                spielerImSpiel.dequeu();
                System.out.println("Das Spiel ist vorbei.");
                System.out.println("Hier ist die Reihenfolge der Gewinner");
                getSpielerNamen(2);
                spielphase = 0;
            }
            return 1;
        } else {
            return 0;
        }
    }

    public void spielSchleife(){
        while (spielphase == 1){
        displaySpiel();       
        }
    }
}
