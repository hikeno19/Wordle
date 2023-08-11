public class Trie {
    private TrieNode root; private int index;

    public Trie(){
        root = new TrieNode();
        index = 1;
    }
    public TrieNode getRoot(){
        return this.root;
    }
    public void insert(String word) {
        TrieNode current = root;
        for (char l: word.toLowerCase().toCharArray()) {
            current = current.getChildren().computeIfAbsent(l, c -> new TrieNode());
        }
        current.setEndOfWord(true);
        current.setIndex(index);
        index++;
    }
    public boolean wordExist(String word){
        TrieNode current = root;
        for (char ch : word.toLowerCase().toCharArray()){
            TrieNode nextNode = current.getChildren().get(ch);
            if(nextNode == null){
                return false;
            }
            current = nextNode;
        }
        return current.isEndOfWord();
    }
    public void ifWordExist(String word){
        boolean exists = wordExist(word);
        if (exists) {
            System.out.println(word + " exists in the Trie.");
        } else {
            System.out.println(word + " does not exist in the Trie.");
        }
    }
}
