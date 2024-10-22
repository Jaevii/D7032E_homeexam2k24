package src.game;

public interface GameSetupInterface {
    
    void parseInput(String[] input);

    void setupGame();

    void setupServer();

    GameStateInterface getState();

}
