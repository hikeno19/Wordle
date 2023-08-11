import javax.swing.*;

public class Wordle extends JFrame{
    
    public Wordle(){

    }
    public static void main(String[] args){
        GameLogic game = new GameLogic();
        game.init();
    }
}
