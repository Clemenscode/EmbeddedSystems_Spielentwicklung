public class Karte {
    private String _Wert;
    private String _Farbe;
    private int _ID;
    public Karte(int id){
        _ID = id;
        int temp = _ID % 8; //Berechnet den Wert der Karte
        switch(temp){
            case 1:
            _Wert = "7";
            break;
            case 2:
            _Wert = "8";
            break;
            case 3:
            _Wert = "9";
            break;
            case 4:
            _Wert = "10";
            break;
            case 5:
            _Wert = "Bube";
            break;
            case 6: 
            _Wert = "Dame";
            break;
            case 7:
            _Wert = "Koenig";
            break;
            case 0:
            _Wert = "Ass";
            break;
            default:
            _Wert = "Error";
            break;
        }
        temp = _ID / 8; //Berechnet die Farbe der Karte
        switch(temp){
            case 0:
            _Farbe = "Kreuz";
            break;
            case 1:
            _Farbe = "Pic";
            break;
            case 2:
            _Farbe = "Herz";
            break;
            case 3:
            _Farbe = "Karo";
            break;
            default:
            _Farbe = "Error";
            break;
        }
    }
    public String getWert(){
        return _Wert;
    }
    public String getFarbe(){
        return _Farbe;
    }
    public int getID(){
      return _ID;
    }
    
  
}

