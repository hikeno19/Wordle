import java.util.*;
import java.io.*;

public class GameLogic {
    static final String WORDLE_FILE_PATH = "/Users/hikeno/Desktop/Projects/Wordle/src/wordlewords.txt";
    static final String DICTIONARY_FILE_PATH = "/Users/hikeno/Desktop/Projects/Wordle/src/wordlist.txt";

    String wordle; int guessCounter; boolean guessing;
    List<String> wordleList;
    HashMap<Character, Integer> wordleCharCount;
    List<Guess> guessList;
    Trie wordList;

    public GameLogic(){
        wordList = new Trie();
        wordleCharCount = new HashMap<Character, Integer>();
        guessList = new ArrayList<Guess>();
        wordleList = new ArrayList<String>();
        guessCounter = 0;
        guessing = true;
    }
    public void init(){
        generateWordList();
        generateWordle();
        
        while(gameIsOn()){
            System.out.println(wordle);
            enterGuess(" ");
            printGuesses();
        }
        if(guessing){
            System.out.println("You ran out of guesses!");
        }
    }
    public void generateWordList(){
        try{
            File file = new File(DICTIONARY_FILE_PATH);
            Scanner reader = new Scanner(file);
            while(reader.hasNextLine()){
                String word = reader.nextLine();
                wordList.insert(word);
            }
            reader.close();     
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
    public void generateWordle(){
        try{
            File file = new File(WORDLE_FILE_PATH);
            Scanner reader = new Scanner(file);
            while(reader.hasNextLine()){
                wordleList.add(reader.nextLine());
            }
            reader.close();
            Random rand = new Random();
            wordle = wordleList.get(rand.nextInt(wordleList.size()));
            for(int i = 0; i < wordle.length(); i++){
                if(wordleCharCount.containsKey(wordle.charAt(i))) wordleCharCount.put(wordle.charAt(i), wordleCharCount.get(wordle.charAt(i))+1);
                else wordleCharCount.put(wordle.charAt(i), 1);
            }
            
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
    public String getWordle(){
        return this.wordle;
    }
    public boolean gameIsOn(){
        return guessCounter < 6 ? true : false;
    }
    public void enterGuess(String w){
        if(w==" "){
            Scanner guess = new Scanner(System.in);
        
            /*FOR TESTING. PRINTS WORDLE ANSWER */
            //System.out.println(wordle.toUpperCase());

            System.out.println("Enter Guess: ");
            String word = guess.nextLine();  // Read user input
            if(!wordList.wordExist(word)){
                System.out.println("Guess not a word, re-enter new guess.");
                enterGuess(" ");
            } else if( word.length()!=5 ){
                System.out.println("Word not 5 characters, re-enter new guess.");
                enterGuess(" ");
            } else {
                Guess g = new Guess(word, wordle);
                if(g.checkGuess(wordle, makeCharacterMap(wordle.toCharArray()))){
                    if(++guessCounter == 1){
                        System.out.println("Wow! You won in your first guess!");
                        guessing = false;
                    } else {
                        System.out.println("You win in " + guessCounter + " guesses!");
                        guessing = false;
                    }
                    guessCounter = 6;
                }
                guessList.add(g);
                guessCounter++;
            }
        }  
    }
    public Map<Character,Integer> makeCharacterMap(char[] arr){
        Map<Character,Integer> output = new HashMap<>();
        for(int i=0;i<arr.length;i++){
            if(output.get(arr[i])!= null){
                output.put(arr[i],output.get(arr[i])+1);
            } else{
                output.putIfAbsent(arr[i],1);
            }
        }
        return output;
    }
    public void printGuesses(){
        if(guessList.size() > 0){
            System.out.println("");
            for(Guess guess : this.guessList){
                guess.getGuess().forEach((tile) -> {
                    if(tile.getChar() != ' '){
                        System.out.print( tile.getTileState().getColor() + Character.toUpperCase(tile.getChar()) + "\033[0;37m");
                    }
                });
                System.out.println("");
            }
        }
    }
}
