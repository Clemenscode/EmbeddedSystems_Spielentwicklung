import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

//@author Daniel, Kai

public class SpielServer extends Server {
  // Anfang Attribute
  TheGame g = new TheGame();
  private JTextArea jTextArea;
  private int pKartenZahl = 99;
  private boolean alreadyAlert = false;
  private String freihalteIP1 = "";
  private String freihalteIP2 = "";
  private String freihalteIP3 = "";
  private String freihalteIP4 = "";

  // Ende Attribute
  public SpielServer(int pServerPort, JTextArea j, int pDeckZahl) {
    super(pServerPort);
    jTextArea = j;
    pKartenZahl = pDeckZahl;
  }
  
  // Anfang Methoden                                                                                                                     
  public void processNewConnection(String pClientIP, int pClientPort) {
    if (g.hatSpielBegonnen()) { //verhindert das weitere Spieler joinen können, wenn das Spiel bereits gestartet ist.
      send(pClientIP, pClientPort,
      "Du kommst zu spät. Das Spiel hat schon begonnen.");
      //closeConnection(pClientIP, pClientPort);
      
      return;
    } // end of if
    
    if (g.getIPAnzahl() == g.getMaxSpielerAnzahl()) {
      send(pClientIP, pClientPort, "Der Server ist voll."); //neu11.02.17
      
      return;
    } // end of if
    
    if (!g.ipVorhanden(pClientIP)) {
      send(pClientIP, pClientPort,
      "Willkommen auf dem TheGame-Spielserver! \n\nWähle einen Benutzernamen!");
      
      sendToAll("Ein neuer Spieler hat den Server betreten, sich aber noch nicht angemeldet.");
      g.IPHinzufuegen(pClientIP); //Kai13.02.
      g.zeigeSpielerliste();  
    } else {
      send(pClientIP, pClientPort,
      "Auf dem Server ist schon jemand mit deiner IP-Adresse.");
    } // end of if-else
  }
  
  public void processClosedConnection(String pClientIP, int pClientPort) { //Daniel 20.02 - GameV15 -> alte Methode hat manchmal dazu geführt, dass 2x die Verlassen Methode aufgerufen wurde.
    g.IPLoeschen(pClientIP);
    
    if (!g.ipZuSpieler(pClientIP).getName().equals("Error")) {
      g.spielerVerlassen(g.ipZuSpieler(pClientIP));  
    } // end of if
    if (g.getIPAnzahl() == 0) { //Daniel 18.02.17 - GameV12->startet den Server neu, falls kein Spieler mehr auf dem Server sein sollte
      g = new TheGame();
    }     
  }
  
  public void processMessage(String pClientIP, int pClientPort, String pMessage) {
    if (jTextArea.getText() == "") {
      jTextArea.append(pMessage);
    } // end of if
    else {
      jTextArea.append("\n" + pMessage);
    }
    
    jTextArea.setCaretPosition(jTextArea.getDocument().getLength());
    
    //ALLGEMEINE BEFEHLE
    if (pMessage.startsWith("+SPIELERLISTE")) {
      send(pClientIP, pClientPort, "Aktuell spielen: " + g.zeigeSpielerliste());
      
      return;
    } // end of if
    
    if (pMessage.startsWith("+AKTPHASE")) {
      send(pClientIP, pClientPort, g.zeigeAktPhase());
      
      return;
    } // end of if
    
    if (pMessage.startsWith("+HELP")) {
      String temp = new String("Error");
      
      switch (g.getAktPhase()) {
        case 0:
        temp = ("---\nDas Spiel befindet sich aktuell in der Anmeldephase. Wenn sich genügend\nSpieler angemeldet haben, kannst du über den Button 'Spielstart' die\nRankingphase einleiten bzw. direkt in die Spielphase gelangen, falls du\nalleine spielen möchtest.\n---");
        
        break;
        
        case 1:
        temp = ("---\nEinige Befehle der Rankingphase sind:\n1. +AKTPHASE <Gibt die aktuelle Spielphase an>\n2. +SPIELSTART <Geht in die Spielphase über> \n3. +RANKING <Du kannst beim Ranking zwischen 1-10 wählen\n1 = auf keinen Fall anfangen,\n10 = unbedingt anfangen>\n---");
        
        break;
        
        case 2:
        temp = ("---\nEinige Befehle der Spielphase sind:\n1. +AKTPHASE <Gibt die aktuelle Spielphase an>\n2. +Zeige_ZIEHSTAPEL <Gibt an, wie viele Karten noch auf dem Deck\nliegen>\n3. +SPIELERVERLASSEN <Leavt das Spiel>\n---");
        
        break;
        
        case 3:
        temp = ("---\nDas Spiel ist beendet und befindet sich in der Auswertungsphase.\nDu kannst jederzeit das Spiel verlassen oder dir die genauen Statistiken\nanschauen.\n---");
      }
      
      send(pClientIP, pClientPort, temp);
      
      return;
    } // end of if
    
    //ANMELDEPHASE  -fertig
    if (g.getAktPhase() == 0) {
      if (pMessage.startsWith("+NAME")) {
        if (g.richtigeSyntaxt(pMessage)) {
          try {
            String[] temp = pMessage.split("<");
            String[] Name = temp[1].split(">");
            /*System.out.println(temp[0]);
            System.out.println(temp[1]);
            System.out.println(Name[0]); 
            System.out.println(Name[1]);       */
            
            if (g.getSpielerAnzahl() == g.getMaxSpielerAnzahl()) {
              send(pClientIP, pClientPort, "Der Server ist voll."); //neu11.02.17
              
              return;
            } // end of if
            if (Name[0].length()>12) {    //Daniel 24.02.17 - GameV18->filtert zu lange Namen
              send(pClientIP, pClientPort,"Dein Name ist zu lang.");
              return;
            } // end of if
            if (!g.erlaubterName(Name[0])) { //Daniel 24.02.17 - GameV18->filtert unerlaubte Zeichen
              send(pClientIP, pClientPort,"Dein Name enthält unerlaubte Zeichen.");
              return;
            } // end of if
            if (g.nameVorhanden(Name[0])) {
              if (g.ipZuSpieler(pClientIP).getName().equals(Name[0])) {   //Kai03.03.
                send(pClientIP, pClientPort,
                "Du bist schon als " + Name[0] + " angemeldet.");
              }else{
                send(pClientIP, pClientPort,
                Name[0] + " ist bereits belegt. Nenn dich anders!");
              }
              // end of if
              
              return;
            } // end of if
            if (!g.ipZuSpieler(pClientIP).getName().equals("Error")) {  //Daniel 19.02.17-> ermöglicht es dem Spieler seinen Namen in der Anmeldephase zu ändern
              sendToAll("---\n" + g.ipZuSpieler(pClientIP).getName()+" hat seinen/ihren Namen zu\n"+Name[0] + " geändert.\n---");
              g.ipZuSpieler(pClientIP).setName(Name[0]);
              sendToAll("Aktuell spielen:" + g.zeigeSpielerliste());
              return;
            } // end of if
            if (!g.nameVorhanden(Name[0])) {
              Spieler neuerSpieler = new Spieler(Name[0], pClientIP, pClientPort);
              g.spielerHinzufuegen(neuerSpieler);
              sendToAll(Name[0] + " hat sich erfolgreich angemeldet.");
              sendToAll("Aktuell spielen: " + g.zeigeSpielerliste());
              
              if (g.getSpielerAnzahl() == g.getMaxSpielerAnzahl()) { //neu11.02.17 -> somit erübrigt sich eigentlich die Nachricht "Server ist voll", da das Spiel gestartet wird sobald das Maximum an Spielern erreicht ist.
                g.starteRanking(pKartenZahl);
                sendToAll("\nDie Rankingphase hat nun begonnen... \nBitte gib ein gültiges Ranking ab.\nWenn dies dein erstes Spiel sein sollte, nutze die Hilfe.");
              } // end of if
              
              return;
            } // end of if
          } catch (Exception e) {
            send(pClientIP, pClientPort,
            "Fehlerhafte Syntax, halte dich ans Protokoll!");
          }
          
          return;
        } // end of if 
        else {
          send(pClientIP, pClientPort,
          "Dein Name enthält unerlaubte Zeichen.");
        } // end of if-else
        return;
      } // end of if
      if (g.playerInGame(pClientIP)) {  //GameV22.8 - Daniel - 08.03.17 -> sorgt dafür, dass ein Spieler der sich nicht angemeldet hat keine Befehle mehr eingeben kann außer +Help, +Aktphase & +Spielerliste
      if (pMessage.startsWith("+VERLASSEN")) {
        g.IPLoeschen(pClientIP); //Kai15.02.  -> IP muss immer gelöscht werden, wenn verlassen, damit Spieler wiederkehrern kann
        if (g.getIPAnzahl() == 0) { //Daniel 18.02.17 - GameV12->startet den Server neu, falls kein Spieler mehr auf dem Server sein sollte
          g = new TheGame();
          return;
        }
        if (!g.ipZuSpieler(pClientIP).getName().equals("Error")) {
          sendToAll(g.ipZuSpieler(pClientIP).getName() +
          " hat soeben das Spiel verlassen. Wie schade!");
          g.spielerVerlassen(g.ipZuSpieler(pClientIP));
          
          return;
        } // end of if
        
        return;
      } // end of if
      
      if (pMessage.startsWith("+SAY")) {
          if (g.richtigeSyntaxt(pMessage)) {
          try {
            String[] temp = pMessage.split("<");
            String[] nachricht = temp[1].split(">");
            sendToAll(g.ipZuSpieler(pClientIP).getName()+": "+ nachricht[0]);
          } catch(Exception e) {
            send(pClientIP, pClientPort,"Fehlerhafte Syntax, halte dich ans Protokoll!");
          }
          }
          else {
            send(pClientIP, pClientPort,"Fehlerhafte Syntax, halte dich ans Protokoll!");
          } // end of if-else
          return; 
        } // end of if
        
      if (pMessage.startsWith("+SPIELSTART")) {
        if (g.getSpielerAnzahl() == 1) { //neu11.02.17 -> überspringt die Rankingphase wenn man alleine spielt.
          
          g.starteRanking(pKartenZahl);
          g.startSpielerFestlegen(g.getAktSpieler(), 10);
          sendToAll(g.starteSpielphase());
          
          return;
        } // end of if
        
        if (g.starteRanking(pKartenZahl)) {
          sendToAll("\nDie Rankingphase hat nun begonnen... \nBitte gib ein gültiges Ranking ab.\nWenn dies dein erstes Spiel sein sollte, nutze die Hilfe."); //neu11.02.17 -> Text aus theGame ausgelagert.
        } // end of if
        else {
          sendToAll("---\nEs haben sich noch nicht genügend Spieler angemeldet, um das Spiel zu\nstarten.\n---");
        } // end of if-else
        
        return;
      } // end of if
      
      if (pMessage.startsWith("+VERLOREN") || pMessage.startsWith("+GEWONNEN") ||
      (pMessage.startsWith("+ZUGBEENDEN")) ||
      (pMessage.startsWith("+ZUGDARFBEENDEN")) ||
      (pMessage.startsWith("+AMZUG")) ||
      (pMessage.startsWith("+ZEIGESTAPEL")) ||
      (pMessage.startsWith("+ZEIGEKARTEN")) ||
      (pMessage.startsWith("+AKTSPIELER"))) { // +HELP Error verhindern.
        
        return;
      } // end of if
    }
    } 
    //RANKINGPHASE      -fertig
    if (g.getAktPhase() == 1 && g.playerInGame(pClientIP)) {
      if (pMessage.startsWith("+RANKING")) {
        if (g.richtigeSyntaxt(pMessage)) {
          try {
            String[] temp = pMessage.split("<");
            String[] Ranking = temp[1].split(">");
            int rank = Integer.valueOf(Ranking[0]);
            
            if ((rank < 1) || (rank > 10)) {
              send(pClientIP, pClientPort, "Gib ein gültiges Ranking an (1-10)!");
            } else {
              g.startSpielerFestlegen(g.ipZuSpieler(pClientIP), rank);
              send(pClientIP, pClientPort,
              "Dein Ranking von " + rank +
              " wurde erfolgreich gespeichert!");
              sendToAll(g.ipZuSpieler(pClientIP).getName() +
              " hat sein/ihr Ranking abgegeben.");
              
              if (g.getSpielerRanking() == g.getSpielerAnzahl()) { //neu11.02.17 -> startet das Spiel sobald alle ihr Ranking abgegeben haben
                sendToAll(g.starteSpielphase());
              } // end of if
            } // end of if-else
          } catch (Exception e) {
            send(pClientIP, pClientPort,
            "Fehlerhafte Syntax, halte dich ans Protokoll!");
          }
        }else{
          send(pClientIP, pClientPort,
          "Fehlerhafte Syntax, halte dich ans Protokoll!");
          return;
        }
        // end of if
        return;
      }
      
      if (pMessage.startsWith("+NAME")) { //Daniel15.02 -> Fügt einen Error hinzu, falls man schon auf dem Server ist, dieser gestartet wird, aber man sich noch nicht angemeldet hat.
        send(pClientIP, pClientPort, "Das Spiel hat schon begonnen.");
        g.IPLoeschen(pClientIP); //Kai15.02.
      }
      
      if (pMessage.startsWith("+VERLASSEN")) {
        g.IPLoeschen(pClientIP); //Kai15.02.
        
        if (g.getIPAnzahl() == 0) { //Daniel 18.02.17 - GameV12->startet den Server neu, falls kein Spieler mehr auf dem Server sein sollte
          g = new TheGame();
        } 
        sendToAll(g.ipZuSpieler(pClientIP).getName() +
        " hat soeben das Spiel verlassen. Wie schade!");
        g.spielerVerlassen(g.ipZuSpieler(pClientIP));
        
        
        return;
      } // end of if
      
      if (pMessage.startsWith("+SPIELSTART")) {
        sendToAll(g.starteSpielphase());
        
        return;
      } // end of if
      
      if (pMessage.startsWith("+ZEIGEKARTEN")) {
        if (g.ipZuSpieler(pClientIP).getName().equals("Error")) {    //Kai25.02.
          send(pClientIP, pClientPort, "Das Spiel hat schon begonnen. Nächstes Mal schneller anmelden!");
          try {
            g.IPLoeschen(pClientIP); //Kai15.02. 
          } catch(Exception e) {
            
          } // end of try
        } // end of if
        
        send(pClientIP, pClientPort,
        "Handkarten: " + g.zeigeHandkarten(g.ipZuSpieler(pClientIP)));
        
        return;
      } // end of if
      
      if (pMessage.startsWith("+GEWONNEN") || pMessage.startsWith("+AMZUG") ||
      pMessage.startsWith("+ZEIGESTAPEL") ||
      pMessage.startsWith("+AKTSPIELER") ||
      pMessage.startsWith("+VERLOREN")) {
        return;
      } // end of if
    }
    
    //SPIELPHASE
    if (g.getAktPhase() == 2 && g.playerInGame(pClientIP)) {  //GameV22.8 - Daniel - 08.03.17 -> sorgt dafür, dass ein Spieler der sich nicht angemeldet hat keine Befehle mehr eingeben kann außer +Help, +Aktphase & +Spielerliste
      if (pMessage.startsWith("+AMZUG")) {
        send(pClientIP, pClientPort, g.werAmZug(pClientIP));
        
        return;
      } // end of if
      
      if (pMessage.startsWith("+AKTSPIELER")) {
        sendToAll(g.amZugName());
        
        return;
      } // end of if
      
      if (pMessage.startsWith("+ZEIGEKARTEN")) {
        if (g.ipZuSpieler(pClientIP).getName().equals("Error")) {    //Kai25.02.
          send(pClientIP, pClientPort, "Das Spiel hat schon begonnen. Nächstes Mal schneller anmelden!");
          try {
            g.IPLoeschen(pClientIP); //Kai15.02.
          } catch(Exception e) {
            
          } // end of try
        } // end of if
        
        send(pClientIP, pClientPort,
        "Handkarten: " + g.zeigeHandkarten(g.ipZuSpieler(pClientIP)));
        
        return;
      } // end of if
      
      if (pMessage.startsWith("+ZEIGESTAPEL")) {
        send(pClientIP, pClientPort, "Stapel: " + g.zeigeStapel());
        
        return;
      } // end of if
      
      if (pMessage.startsWith("+KARTELEGEN")) {
        if (g.amZug(pClientIP)) {
          try {
            String[] temp = pMessage.split("<");
            String[] temp2 = temp[1].split(">");
            String[] temp3 = temp2[0].split(",");
            send(pClientIP, pClientPort,
            g.ablegen(Integer.valueOf(temp3[1]), Integer.valueOf(temp3[0])));
            sendToAll("Stapel: " + g.zeigeStapel());
            send(pClientIP, pClientPort, "Handkarten: " +      //Kai25.02.  Bug mit leeren Handkarten seltener
            g.zeigeHandkarten(g.ipZuSpieler(pClientIP)));
          } catch (Exception e) {
            send(pClientIP, pClientPort,
            "Fehlerhafte Syntax, halte dich ans Protokoll!");
          }
          
          return;
        } else {
          send(pClientIP, pClientPort,
          "Du bist nicht am Zug!\n" + g.werAmZug(pClientIP));
          
          return;
        } // end of if-else
      } // end of if
      
      if (pMessage.startsWith("+ZUGDARFBEENDEN")) {
        if (g.zugDarfBeendetWerden(g.ipZuSpieler(pClientIP))) {
          send(pClientIP, pClientPort, "Du darfst deinen Zug beenden.");
        } else {
          send(pClientIP, pClientPort,
          "Du darfst deinen Zug noch nicht beenden!");
        } // end of if-else
        
        return;
      } // end of if
      
      if (pMessage.startsWith("+ZUGBEENDEN")) {
        if (g.zugDarfBeendetWerden(g.ipZuSpieler(pClientIP))) {
          sendToAll(g.ipZuSpieler(pClientIP).getName() +
          " hat seinen/ihren Zug beendet.");
          g.zugBeenden();
          send(pClientIP, pClientPort, "Handkarten: " +
          g.zeigeHandkarten(g.ipZuSpieler(pClientIP))); //Kai13.02. Bug mit leeren Karten so deutlich seltener   
          
          if ((g.getMinKartenAblegen() == 1) && (alreadyAlert == false)) {                                       //Kai18.02. --> Meldung kommt nur 1x
            sendToAll("Der Nachziehstapel ist leer. Du musst nur noch eine Karte pro Zug ablegen.");
            alreadyAlert = true;
          }
          if (pClientIP.equals(freihalteIP1)) {
            sendToAll("Rot1 bitte weg machen");
            freihalteIP1 = "";
          } // end of if
          if (pClientIP.equals(freihalteIP2)) {
            sendToAll("Rot2 bitte weg machen");
            freihalteIP2 = "";
          } // end of if
          if (pClientIP.equals(freihalteIP3)) {
            sendToAll("Rot3 bitte weg machen");
            freihalteIP3 = "";
          } // end of if
          if (pClientIP.equals(freihalteIP4)) {
            sendToAll("Rot4 bitte weg machen");
            freihalteIP4 = "";
          } // end of if
          //System.out.println(pClientIP + freihalteIP);          //Kai20.02.
        } // end of if
        else {
          send(pClientIP, pClientPort,
          "Du darfst deinen Zug noch nicht beenden!");
        } // end of if-else
        
        return;
      } // end of if
      
      if (pMessage.startsWith("+VERLASSEN")) {
        g.IPLoeschen(pClientIP); //Kai15.02.
        
        if (g.getIPAnzahl() == 0) { //Daniel 18.02.17 - GameV12->startet den Server neu, falls kein Spieler mehr auf dem Server sein sollte
          g = new TheGame();
        }  
        sendToAll(g.ipZuSpieler(pClientIP).getName() +
        " hat soeben das Spiel verlassen. Wie schade!");
        g.spielerVerlassen(g.ipZuSpieler(pClientIP));
        
        
        return;
      } // end of if
      
      if (pMessage.startsWith("+Zeige_ZIEHSTAPEL")) {
        int pRestDeck = g.restDeck();
        
        if (pRestDeck == 0) {
          send(pClientIP, pClientPort, g.nachziehstapelLeer());
        } else {
          if (pRestDeck == 1) {
            send(pClientIP, pClientPort, "Das Deck hat noch 1 Karte.");
          } else {
            send(pClientIP, pClientPort,
            "Das Deck hat noch " + pRestDeck + " Karten.");
          }
          
          // end of if
        } // end of if-else
        
        return;
      } // end of if
      
      if (pMessage.startsWith("+VERLOREN")) {
        if (g.spielVorbei()) {
          sendToAll("\n" + g.ipZuSpieler(pClientIP).getName() +
          " kann nicht genügend Karten ablegen.\nDas Spiel ist leider verloren! Schade!");
          g.setAktPhase(3);
          g.setEndTime();
          sendToAll(g.score());
          sendToAll("Schau dir mithilfe des Menüs deine Statistiken an!");
        } // end of if
        
        return;
      } // end of if
      
      if (pMessage.startsWith("+GEWONNEN")) {
        if (g.getGewonnen()) {
          sendToAll("\nGewonnen! Wow!");
          g.setAktPhase(3);
          g.setEndTime();
          sendToAll(g.score());
          sendToAll("Schau dir mithilfe des Menüs deine Statistiken an!");
          
          return;
        } // end of if
        
        return;
      } // end of if
      
      if (pMessage.startsWith("+NAME")) { //19.02. -> Fügt einen Error hinzu, falls man schon auf dem Server ist, dieser gestartet wird, aber man sich noch nicht angemeldet hat.
        send(pClientIP, pClientPort, "Das Spiel hat schon begonnen.");
        g.IPLoeschen(pClientIP); //Kai15.02.
      }
      
    }
    
    //Auswertungsphase
    if (g.getAktPhase() == 3 && g.playerInGame(pClientIP)) {  //GameV22.8 - Daniel - 08.03.17 -> sorgt dafür, dass ein Spieler der sich nicht angemeldet hat keine Befehle mehr eingeben kann außer +Help, +Aktphase & +Spielerliste
      if (pMessage.startsWith("+RESTKARTEN")) {
        int pKarten = g.restKarten();
        
        if (pKarten > 40) {
          send(pClientIP, pClientPort,
          "---\nDas war leider kein gutes Spiel. Macht euch/mach dir eventuell nochmal\ndie Spielregeln von The Game bewusst.\nAm Ende waren noch " +
          pKarten + " Karten übrig.\n---");
          
          return;
        } else if (pKarten >= 10) {
          send(pClientIP, pClientPort,
          "---\nDu hast/ihr habt bereits sehr viel richtig gemacht, nur das Glück hat leider\ngefehlt.\nAm Ende waren noch " +
          pKarten + " Karten übrig.\n---");
          
          return;
        } else if (pKarten > 1) {
          send(pClientIP, pClientPort,
          "---\nDas war eine sehr gute Runde, mit etwas mehr Glück wäre The Game\nbezwungen!\nAm Ende waren noch " +
          pKarten + " Karten übrig.\n---");
          
          return;
        } else if (pKarten == 1) {
          send(pClientIP, pClientPort,
          "---\nSo close!\nNur noch eine einzige Karte ist übrig!");
          
          return;
        } else if (pKarten == 0) {
          send(pClientIP, pClientPort,
          "---\nDas war eine perfekte Runde!\nAlle Karten wurden abgelegt. Herzlichen Glückwunsch!\n---");
          
          return;
        }
      } // end of if
      
      if (pMessage.startsWith("+VERLASSEN")) {
        g.IPLoeschen(pClientIP); //Kai15.02.
        
        if (g.getIPAnzahl() == 0) { //Daniel 18.02.17 - GameV12->startet den Server neu, falls kein Spieler mehr auf dem Server sein sollte
          g = new TheGame();
        }
        
        sendToAll(g.ipZuSpieler(pClientIP).getName() +
        " hat soeben das Spiel verlassen. Wie schade!");
        g.spielerVerlassen(g.ipZuSpieler(pClientIP));
        
        return;
      } // end of if
      
      if (pMessage.startsWith("+SPIELZEIT")) {
        send(pClientIP, pClientPort, g.spielZeit());
        
        return;
      } // end of if
      
      if (pMessage.startsWith("+SCORE")) {
        send(pClientIP, pClientPort, g.score());
        
        return;
      }
      
      if (pMessage.startsWith("+RESTART")) { //Daniel15.02.17 -> Sorgt dafür, dass man am Ende eines Spiels wieder ein neues mit den gleichen Spielern starten kann. + es können weitere Spieler joinen.
        
        Queue<Spieler> pSpieler = g.neuStart();
        g = new TheGame();
        
        sendToAll("\n" + "Das Spiel wird neu gestartet!" + "\n-----------------------------------------------------------------------------------------------\n\nEs läuft die Anmeldephase.");
        
        while (!pSpieler.isEmpty()) {
          Spieler temp = new Spieler(pSpieler.front().getName(),
          pSpieler.front().getIP(),
          pSpieler.front().getPort());
          g.spielerHinzufuegen(temp);
          g.IPHinzufuegen(pSpieler.front().getIP());
          sendToAll(pSpieler.front().getName() + " ist wieder dabei.");
          pSpieler.dequeu();
        } // end of while
        
        sendToAll("Aktuell spielen: " + g.zeigeSpielerliste());
        
        if (g.getSpielerAnzahl() == g.getMaxSpielerAnzahl()) { //neu11.02.17 -> somit erübrigt sich eigentlich die Nachricht "Server ist voll", da das Spiel gestartet wird sobald das Maximum an Spielern erreicht ist.
          g.starteRanking(pKartenZahl);
          sendToAll("\nDie Rankingphase hat nun begonnen... \nBitte gib ein gültiges Ranking ab.\nWenn dies dein erstes Spiel sein sollte, nutze die Hilfe.");
        } // end of if
        
        return;
      } // end of if
      
      if (pMessage.startsWith("+VERLOREN") || pMessage.startsWith("+GEWONNEN") ||
      (pMessage.startsWith("+ZUGBEENDEN")) ||
      (pMessage.startsWith("+ZUGDARFBEENDEN")) ||
      (pMessage.startsWith("+AMZUG")) ||
      (pMessage.startsWith("+ZEIGESTAPEL")) ||
      (pMessage.startsWith("+ZEIGEKARTEN")) ||
      (pMessage.startsWith("+AKTSPIELER"))) { // +HELP Error verhindern.
        
        return;
      } // end of if
      
      if (pMessage.startsWith("+NAME")) { //Daniel15.02 -> Fügt einen Error hinzu, falls man schon auf dem Server ist, dieser gestartet wird, aber man sich noch nicht angemeldet hat.
        send(pClientIP, pClientPort, "Das Spiel hat schon begonnen.");
        g.IPLoeschen(pClientIP); //Kai15.02.
      }
      if (pMessage.startsWith("+SAY")) {
          if (g.richtigeSyntaxt(pMessage)) {
          try {
            String[] temp = pMessage.split("<");
            String[] nachricht = temp[1].split(">");
            sendToAll(g.ipZuSpieler(pClientIP).getName()+": "+ nachricht[0]);
          } catch(Exception e) {
            send(pClientIP, pClientPort,"Fehlerhafte Syntax, halte dich ans Protokoll!");
          }
          }
          else {
            send(pClientIP, pClientPort,"Fehlerhafte Syntax, halte dich ans Protokoll!");
          } // end of if-else
          return; 
        } // end of if
    } // end of if
    //Kommunikation
    
    if (pMessage.startsWith("+MSG STAPEL")&& g.playerInGame(pClientIP)) {    //GameV22.8 - Daniel - 08.03.17 -> nur angemeldete Spieler können Kommunizieren
      String[] temp = pMessage.split("<");
      String[] Stapel = temp[1].split(">");
      sendToAll(g.ipZuSpieler(pClientIP).getName() + ": Stapel " + Stapel[0] +
      " bitte freihalten! Danke!");
      
      if (Stapel[0].equals("1")) {
        freihalteIP1 = pClientIP;
      } // end of if
      if (Stapel[0].equals("2")) {
        freihalteIP2 = pClientIP;
      } // end of if
      if (Stapel[0].equals("3")) {
        freihalteIP3 = pClientIP;
      } // end of if
      if (Stapel[0].equals("4")) {
        freihalteIP4 = pClientIP;
      } // end of if
      //Kai20.02.
      
      return;
    } // end of if
    
    if (pMessage.startsWith("+MSG !STAPEL")&& g.playerInGame(pClientIP)) {   //GameV22.8 - Daniel - 08.03.17 -> nur angemeldete Spieler können Kommunizieren
      String[] temp = pMessage.split("<");
      String[] Stapel = temp[1].split(">");
      sendToAll(g.ipZuSpieler(pClientIP).getName() + ": Stapel " + Stapel[0] +
      " bitte UNBEDINGT freihalten! Danke!");
      
      if (Stapel[0].equals("1")) {
        freihalteIP1 = pClientIP;
      } // end of if
      if (Stapel[0].equals("2")) {
        freihalteIP2 = pClientIP;
      } // end of if
      if (Stapel[0].equals("3")) {
        freihalteIP3 = pClientIP;
      } // end of if
      if (Stapel[0].equals("4")) {
        freihalteIP4 = pClientIP;
      } // end of if
      //Kai20.02.
      
      return;
    } // end of if
    
    if (pMessage.startsWith("+MSG -STAPEL") && g.playerInGame(pClientIP)) {                  //Kai10.03. --> man kann Reservierungen wieder löschen
      String[] temp = pMessage.split("<");
      String[] Stapel = temp[1].split(">");
      
      if (Stapel[0].equals("1") && freihalteIP1.equals(pClientIP)) {
        sendToAll(g.ipZuSpieler(pClientIP).getName() + " hat Stapel " + Stapel[0] +
        " wieder freigegeben.");
        sendToAll("Rot1 bitte weg machen");
        freihalteIP1 = "";
        return;
      } // end of if
      if (Stapel[0].equals("2") && freihalteIP2.equals(pClientIP)) {
        sendToAll(g.ipZuSpieler(pClientIP).getName() + " hat Stapel " + Stapel[0] +
        " wieder freigegeben.");
        sendToAll("Rot2 bitte weg machen");
        freihalteIP2 = "";
        return;
      } // end of if
      if (Stapel[0].equals("3") && freihalteIP3.equals(pClientIP)) {
        sendToAll(g.ipZuSpieler(pClientIP).getName() + " hat Stapel " + Stapel[0] +
        " wieder freigegeben.");
        sendToAll("Rot3 bitte weg machen");
        freihalteIP3 = "";
        return;
      } // end of if
      if (Stapel[0].equals("4") && freihalteIP4.equals(pClientIP)) {
        sendToAll(g.ipZuSpieler(pClientIP).getName() + " hat Stapel " + Stapel[0] +
        " wieder freigegeben.");
        sendToAll("Rot4 bitte weg machen");
        freihalteIP4 = "";
        return;
      } // end of if
      send(pClientIP, pClientPort, "Du hast diesen Stapel nicht als freizuhalten markiert!" ); //durch returns kommt diese Meldung nur, wenn oben nichts Passendes
      return;
    } // end of if
    
    if (g.playerInGame(pClientIP)) { // normale Error Meldung
      send(pClientIP, pClientPort,"\nFehlerhafte Eingabe!\nMit +HELP kannst du alle Befehle nochmal nachschauen.");
    } // end of if
    if (!g.playerInGame(pClientIP)) {
      if (g.getAktPhase()==0) {
        send(pClientIP, pClientPort,"Du bist noch nicht angemeldet!");   //Gibt den Hinweis zum anmelden wenn die Anmeldephase noch läuft
      } // end of if
      else {
        send(pClientIP, pClientPort,"Das Spiel hat schon begonnen. Nächstes Mal schneller anmelden!");
      } // end of if-else
    } // end of if
    
  }
  
  // Ende Methoden
}
