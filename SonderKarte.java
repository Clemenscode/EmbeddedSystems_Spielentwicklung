public class SonderKarte{
    private int funktion; // 1 = zweiziehen; 2 = Aussetzen; 3 = Farbwunsch;
    private String farbwunsch;
    public SonderKarte(int _funktion){
        funktion = _funktion;
    }
    public void setFarbe(String _Farbe){
        farbwunsch = _Farbe;
    }

    public String getFarbe(){
        return farbwunsch;
    }

    public int getFunktion(){
        return funktion;
    }

    public void setFunktion(int _funktion){
        funktion = _funktion;
    }
}