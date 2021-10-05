/**
  *
  * Beschreibung
  *
  * @version 1.0 vom 05.12.2016
  * @author Daniel Röthgen
  */

public class Deck {
  //Anfang Attribute:
  private  Stack<Karte> karten = new Stack(); //der Stack beinhaltet den gemischten Ziehstapel von hier wird während des Spiels gezogen.
  //Ende Attribute:
  public Deck(){ //erschaft ein Deck der Standardgröße ( 98 Karten)
    //erzeugt einen Stack mit den Zahlen von 2- zur Grenze
    int zaehler = 2;
    while (zaehler <=99) { 
      Karte spielkarte = new Karte(zaehler);
      karten.push(spielkarte);
      
      zaehler++;
    } // end of while
    mischen();
  }

  public Deck(int grenze){ //erschaft ein Deck von beliebiger Größe. (meistens zum Testen von Dingen)
    //erzeugt einen Stack mit den Zahlen von 2- zur Grenze
    int zaehler = 2;
    while (zaehler <=grenze) { 
      Karte spielkarte = new Karte(zaehler);
      karten.push(spielkarte);
      
      zaehler++;
    } // end of while
    mischen();
  }

  //Anfang Methoden:
  //mischen:
  /**
  * Methode Mischen: erzeugt zuerst einen Stack der sortiert ist. (Zahlen von 2-99).
  * Anschließend werden n leere Stacks erzeugt. Auf diese Stacks werden immer zufällig die erste Zahl des sortierten Stacks verteilt.
  * Am ende werden aus allen Stacks wieder ein Kartenstack zusammengelegt, der nun gemischt ist.
  * Die Methode braucht keine Attribute übermittelt. Sie gibt keinen Returnwert, sie übermittelt das fertige Attribut "karten"
  * @Attribut: karten
  * @Autor: Daniel Röthgen
  * @Version:
  **/
  
  //Anfang Methoden 
  public  void mischen(){    
    //erzeugt z Stapel zum mischen je mehr Stapel desto besser ist das Deck gemischt.
   
    int stackAnzahl = 90;
    Stack<Karte>[] mischStapel = new Stack[stackAnzahl];
    for (int z = 0;z<stackAnzahl ;z++ ) {
      mischStapel[z] = new Stack<Karte>();
    } // end of for


    //solange der sortierte Stapel nicht leer ist, wird die oberste Zahl zufällig auf einen Stapel gelegt.
     
    while (!karten.isEmpty()) {
      int random =(int)(Math.random() * stackAnzahl + 0);
      Karte randomKarte = new Karte (karten.top().getWert()); 
      mischStapel[random].push(randomKarte); //verteilt den gemischten Stack zufällig auf die verschiedenen Stacks
      karten.pop();
    } // end of while
    
    //sortiert die verschienden Stapel auf einen gemeinsamen Finalen
    for (int a = 0;a<stackAnzahl ;a++ ) {
      while (!mischStapel[a].isEmpty()) { 
        karten.push(mischStapel[a].top());
        mischStapel[a].pop(); 
      } // end of while
    } // end of for
        
  }
  //ende mischen
  //gibt den gemischten Stack als Debug aus. Kann in der Finalen Version gelöscht werden.
   public  String ausgebenDebug(){
    String debugString = new String();
    Stack<Karte> debugK = new Stack();
    while (!karten.isEmpty()) { 
      Karte aktuellekarte = karten.top();
      int a = aktuellekarte.getWert();
      debugString += ""+a+" ;";
      debugK.push(aktuellekarte);
      karten.pop();
      
    } // end of while
    //wieder den ursprünglichen Stack herstellen:
    while (!debugK.isEmpty()) { 
      Karte aktuellekarte = debugK.top();
      karten.push(aktuellekarte);
      debugK.pop();
    } // end of while
    return debugString;
  }
  //liefert True oder false, falls der Ziehstapel leer ist oder eben nicht.
  public  boolean istLeer(){
    return karten.isEmpty();
  }
//gibt die erste Karte des Ziehstapel als return zurück. Diese Karte wird automatisch vom Stapel gelöscht.
  public  Karte ziehen(){
      Karte temp = karten.top();
      karten.pop();
      return temp;   
  }
  //legt eine Karte wieder auf das Deck zurück -> benötigt wenn ein Spieler das Spiel verlässt.
  public  void add(Karte pKarte){
    karten.push(pKarte);
    }
  //zählt die aktuellen Karten im Deck
  public int restDeck(){
    int restDeck = 0;
    Stack<Karte> debugK = new Stack();
    while (!karten.isEmpty()) { 
      Karte aktuellekarte = karten.top();
      int a = aktuellekarte.getWert();
      restDeck ++;
      debugK.push(aktuellekarte);
      karten.pop();
      
    } // end of while
    //wieder den ursprünglichen Stack herstellen:
    while (!debugK.isEmpty()) { 
      Karte aktuellekarte = debugK.top();
      karten.push(aktuellekarte);
      debugK.pop();
    } // end of while
    return restDeck;
} // end of class Deck
}