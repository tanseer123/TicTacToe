package strategies;

import models.Board;
import models.Move;

public interface BotPlayingStrategy {

    Move suggestMove(Board board);

}
