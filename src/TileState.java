public enum TileState {
    YELLOW ("\033[1;33m"),
    GREEN ("\033[1;32m"),
    GREY ("\033[1;30m");
    
    String color;
    
    TileState(String color){
        this.color = color;
    }
    String getColor(){
        return this.color;
    }
}
