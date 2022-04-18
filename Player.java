public interface Player{
    
    public void init(int startingSticks, int playerNum);
    
    public int takeTurn(int remainingSticks);
    
    public void gameOver(boolean didPlayerWin);
    
    public String getPlayerType();
    
    public String getDebugInfo();
    
}