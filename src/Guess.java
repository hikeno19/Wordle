import java.util.*;

public class Guess {
    String guess;
    List<Tile> guessTiles;
    Map<Character, Integer> wordleMap;

    public Guess(String guess, String wordle){
        this.guess = guess;
        this.guessTiles = new ArrayList<Tile>();
        for(int i = 0; i < guess.length(); i++){
            guessTiles.add(i, new Tile(' ', TileState.GREY));
        }
    }
    public List<Tile> getGuess(){
        return this.guessTiles;
    }
    public boolean checkGuess(String wordle, Map<Character, Integer> wordleMap){
        this.wordleMap = wordleMap;
        checkGreen(wordle);
        checkYellowGrey(wordle);
        return wordle.equals(guess);
    }
    public void checkGreen(String wordle){
        for(int i = 0; i < wordle.length(); i++){
            if(wordle.charAt(i)== guess.charAt(i) ){
                guessTiles.set(i, editTile(i, guess.charAt(i), TileState.GREEN));
                subtractCounter(guess.charAt(i));
            }
        }
    }
    public void checkYellowGrey(String wordle){
        for(int i = 0; i < wordle.length(); i++){
            if(guess.charAt(i) != wordle.charAt(i)){
                if(contains(wordle, guess.charAt(i), i)){
                    if(wordleMap.get(guess.charAt(i)) > 0){
                        guessTiles.set(i, editTile(i, guess.charAt(i), TileState.YELLOW));
                        subtractCounter(guess.charAt(i));
                    }
                } else {
                    guessTiles.set(i, editTile(i, guess.charAt(i), TileState.GREY));
                }
            }
        }
    }
    public Tile editTile(int index, char ch, TileState tileState){
        guessTiles.get(index).setTileState(tileState);
        guessTiles.get(index).setChar(ch);
        return guessTiles.get(index);
    }
    public boolean contains(String wordle, char c, int ignore){
        for(int i = 0; i < guess.length(); i++){
            if(i != ignore){
                if(wordle.charAt(i) == c){
                    return true;
                }
            }
        }
        return false;
    }
    public void subtractCounter(char c){
        wordleMap.put(c,wordleMap.get(c)-1);
    }
}
