public class Konsole{
    public Konsole(){}
    
    public void leereZeilen(int _count){
        for (int i = 0; i < _count; i++){
            System.out.println();
        }
    }
    public void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }  

}