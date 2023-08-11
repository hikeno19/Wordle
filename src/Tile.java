public class Tile {
    TileState tileState;
    char ch;

    public Tile(char ch, TileState tileState){
        this.ch = ch;
        this.tileState = tileState;
    }
    public TileState getTileState(){
        return this.tileState;
    }
    public void setTileState(TileState tileState){
        this.tileState = tileState;
    }
    public char getChar(){
        return this.ch;
    }
    public void setChar(char ch){
        this.ch = ch;
    }
}
