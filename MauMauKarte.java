public class MauMauKarte extends Karte{
  private int _Funktion;   //1-> zweiziehen; 2-> aussetzen; 3 -> Farbwunsch; 0 -> keine Funktion;
  private String _nameFunktion;
  
  MauMauKarte(int id){
    super (id);
    setFunktion(id);
    
  }
  private void setFunktion(int id){
    id = id % 8; //Berechnet den Wert der Karte
    int funk = 0;
    String funk2 = "keine" ;
        switch(id){
            case 1:
            funk = 1;
            funk2 = "Zweiziehen";
            break;
            case 2:
            funk = 2;
            funk2 = "Aussetzen";
            break;
            case 5:
            funk = 3;
            funk2 = "Farbwunsch";        
            break;
            default:
            funk = 0;
            funk2 = "keine" ;
            break; 
    }
    _Funktion = funk;
    _nameFunktion = funk2;
    
  }
  public int getFunktion (){
    return _Funktion;
    }
  public String getNameFunktion(){
    return _nameFunktion;
    }  
  
  
    
     
}
