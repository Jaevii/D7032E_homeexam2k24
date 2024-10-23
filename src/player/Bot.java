package src.player;

public class Bot extends Player {
    
    public Bot(int playerID) {
        super(playerID, null, null, null, false);
    }

    @Override
    public boolean isBot() {
        return true;
    }
}
