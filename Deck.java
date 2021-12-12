/**
  *
  * Beschreibung
  *
  * @version 1.0 vom 12.12.2021
  * @author Daniel Röthgen
  */

public class Deck {
//Anfang Attribute:
    private  Stack<MauMauKarte> karten = new Stack(); //der Stack beinhaltet den gemischten Ziehstapel von hier wird während des Spiels gezogen.
//Ende Attribute:
public Deck(){ //erschaft ein Deck der Größe 32
    //erzeugt einen Stack mit den Zahlen von 2- zur Grenze
    int zaehler = 0;
    while (zaehler <=31) { 
    MauMauKarte spielkarte = new MauMauKarte(zaehler);
    karten.push(spielkarte);
    
    zaehler++;
    } // end of while
    mischen();
}

//Anfang Methoden:
//mischen:
/**
* Methode Mischen: erzeugt zuerst einen Stack der sortiert ist. 
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
    int stackAnzahl = 32;
    Stack<MauMauKarte>[] mischStapel = new Stack[stackAnzahl];
    for (int z = 0;z<stackAnzahl ;z++ ) {
    mischStapel[z] = new Stack<MauMauKarte>();
    } // end of for
    //solange der sortierte Stapel nicht leer ist, wird die oberste Zahl zufällig auf einen Stapel gelegt.
    while (!karten.isEmpty()) {
    int random =(int)(Math.random() * stackAnzahl + 0);
    MauMauKarte randomKarte = new MauMauKarte (karten.top().getID()); 
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
    Stack<MauMauKarte> debugK = new Stack();
    while (!karten.isEmpty()) { 
    MauMauKarte aktuellekarte = karten.top();
    int a = aktuellekarte.getID();
    debugString += ""+a+" ;";
    debugK.push(aktuellekarte);
    karten.pop();
    
    } // end of while
    //wieder den ursprünglichen Stack herstellen:
    while (!debugK.isEmpty()) { 
    MauMauKarte aktuellekarte = debugK.top();
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
public  MauMauKarte kartenziehen(){
    MauMauKarte temp = karten.top();
    karten.pop();
    return temp;   
}
//legt eine Karte wieder auf das Deck zurück 
public  void karteablegen(MauMauKarte pKarte){
    karten.push(pKarte);
    }
public Stack<MauMauKarte> deckUebergeben(){
    return karten;
  }
public void zeigeKartenDebug(){
  Stack<MauMauKarte> debugK = new Stack();
  System.out.println("Gibt das aktuelle Deck aus:");  
  System.out.println("Zahlenwert:  | Farbe:  |  Funktion:");      
    while (!karten.isEmpty()) { 
    MauMauKarte aktuellekarte = karten.top();
    String temp  = aktuellekarte.getWert();
    String temp2 = aktuellekarte.getFarbe();
    String temp3 = aktuellekarte.getNameFunktion();
      
    System.out.println(String.format("%s |  %s |  %s",temp, temp2, temp3));
      
    debugK.push(aktuellekarte);
    karten.pop();
    
    } // end of while
    //wieder den ursprünglichen Stack herstellen:
    while (!debugK.isEmpty()) { 
    MauMauKarte aktuellekarte = debugK.top();
    karten.push(aktuellekarte);
    debugK.pop();
    } // end of while 
  }
    
}