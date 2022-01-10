import java.util.*;
public class spiel {
    private static Scanner input;
    //Mit dieser Klasse, kann jede Funktion des Decks und der darunter liegenden Karte getestet und validiert werden.
    public static void  main(String[] args) {
        MauMau spiel = new MauMau(5);
        //run(spiel);
        int eingabe = 0;
        String name ="test";
        input = new Scanner(System.in);
        while (true){
            if (spiel.getSpielphase() == 0 ){
                System.out.println("Mit wie vielen Spielern möchtet ihr MauMau spielen? [2 - 5]");
                while ((eingabe < 2) || (eingabe > 5)){
                    eingabe = input.nextInt();
                }
                for (int i = 1; i <= eingabe ; i++){
                    System.out.println(String.format("Bitte gib den Namen, von Spieler%s ein.",i));
                    if (i == 1){
                        name = input.nextLine();
                    }
                    name = input.nextLine();
                    spiel.addSpieler(name);
                }
                spiel.getSpielerNamen(0);
                System.out.println("Wenn alle Spieler bereit sind mit 'ENTER' das Spiel starten.");
                input.nextLine();
                spiel.spielStarten();

            } else if (spiel.getSpielphase() == 1){
                //k.A. was hier rein soll, die Spiellogik läuft ja ausgelagert...
            } else {
                //Hier wäre der Auswertungsteil noch zuergänzen...
            }

        }
    }
    /*public  static void run(MauMau spiel){
        int eingabe = 0;
        String name;
        while (true){
            if (spiel.getSpielphase() == 0 ){
                System.out.println("Mit wie vielen Spielern möchtet ihr MauMau spielen? [2 - 5]");
                while (eingabe < 2 && eingabe > 5){
                    eingabe = input.nextInt();
                }
                for (int i = 0; i < eingabe ; i++){
                    System.out.println(String.format("Bitte gib den Namen, von Spieler%s ein.",i));
                    name = input.nextLine();
                    spiel.addSpieler(name);
                }
                spiel.getSpielerNamen(0);
                System.out.println("Wenn alle Spieler bereit sind mit 'ENTER' das Spiel starten.");
                input.nextLine();
                spiel.spielStarten();

            } else if (spiel.getSpielphase() == 1){
                //k.A. was hier rein soll, die Spiellogik läuft ja ausgelagert...
            } else {
                //Hier wäre der Auswertungsteil noch zuergänzen...
            }

        }
    }*/
}
