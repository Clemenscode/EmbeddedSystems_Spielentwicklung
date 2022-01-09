public class testDeck {
    //Mit dieser Klasse, kann jede Funktion des Decks und der darunter liegenden Karte getestet und validiert werden.
    public static void  main(String[] args) {
       // testDeckErstellen();
       //testDeckMischen();
       //testDeckCounter(15);
       //testDeckUebergeben();
       //testDeckUbergabe();
       testKartenZiehenAblegen();
     }

    public static void testDeckErstellen(){
        Deck neuesDeck = new Deck(32);
        neuesDeck.zeigeKartenDebug();
    }

    public static void testDeckMischen(){
        Deck neuesDeck = new Deck(32);
        neuesDeck.zeigeKartenDebug();
        System.out.println("-----------------------------");
        neuesDeck.mischen();
        neuesDeck.zeigeKartenDebug();
    }

    public static void testDeckCounter(int _x){
        Deck temp = new Deck(_x);
        System.out.println(temp.countDeck());
        temp.zeigeKartenDebug();
    }

    public static void testDeckUebergeben(){ 
        Deck ablagestapel = new Deck(3);
        Deck ziehstapel = new Deck(0);
        System.out.println("-----------Ablagestapel-----------------");
        ablagestapel.zeigeKartenDebug();
        System.out.println("-------------Ziehstapel----------------");
        ziehstapel.zeigeKartenDebug();
        ziehstapel = ablagestapel;
        ablagestapel = new Deck(0);
        ziehstapel.countDeck();
        ziehstapel.mischen();
        System.out.println("-----------Ziehstapel nach Übergabe------------------");
        ziehstapel.zeigeKartenDebug();
        System.out.println("-----------Ablage nach Übergabe------------------");
        ablagestapel.zeigeKartenDebug();

    }

    public static void testDeckUbergabe(){
        Deck ablagestapel = new Deck(3);
        Deck ziehstapel = new Deck(0);
        System.out.println("-----------Ablagestapel-----------------");
        ablagestapel.zeigeKartenDebug();
        System.out.println("-------------Ziehstapel----------------");
        ziehstapel.zeigeKartenDebug();
        ziehstapel.erhalteDeck(ablagestapel.deckUebergeben()); // Hiermit kann mit einer Zeile jetzt der Ablagestapel in das Deck wieder übergeben werden. /
// Beim richtigen Spiel muss man beachten, dass die erste Karte des Ablagestapels, dem Ablagestapel erhalten bleiben muss!
        System.out.println("-----------Ziehstapel nach Übergabe------------------");
        ziehstapel.zeigeKartenDebug();
        System.out.println("-----------Ablage nach Übergabe------------------");
        ablagestapel.zeigeKartenDebug();
    }

    public static void testKartenZiehenAblegen(){
        Deck test = new Deck(3);
        System.out.println("-----------Deck vor Ziehen-----------------");
        test.zeigeKartenDebug();
        MauMauKarte temp = test.kartenziehen();
        System.out.println("-----------Funktion der gezogenen Karte-----------------");
        System.out.println(temp.getNameFunktion());
        System.out.println("-----------Deck nach Ziehen-----------------");
        test.zeigeKartenDebug();
        test.karteablegen(temp);
        System.out.println("-----------Deck nach Ablage-----------------");
        test.zeigeKartenDebug();
    }






}
