import java.util.*;

public class TrieNode {
    private HashMap<Character, TrieNode> children;
    private boolean isWord; private int index;
    public TrieNode(){
        this.isWord = false;
        this.children = new HashMap<>();
    }
    public HashMap<Character, TrieNode> getChildren(){
        return this.children;
    }
    public void setEndOfWord(boolean word){
        this.isWord = word;
    }
    public boolean isEndOfWord() {
        return this.isWord;
    }
    public int size() {
        int count = 0;
        if (isWord) {
            count++; // Increment count if the current node represents the end of a word
        }
        for (TrieNode child : children.values()) {
            count += child.size(); // Recursively calculate the size of children nodes
        }
        return count;
    }
    public void setIndex(int i){
        this.index = i;
    }
    public int getIndex(){
        return this.index;
    }
}