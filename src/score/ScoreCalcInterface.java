package src.score;

import java.util.ArrayList;

import src.card.CardInterface;
import src.player.PlayerInterface;

public interface ScoreCalcInterface {
    int calculateScore(ArrayList<CardInterface> hand, PlayerInterface thisPlayer, ArrayList<PlayerInterface> players);
}
