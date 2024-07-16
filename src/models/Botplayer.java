package models;

import strategies.BotPlayingStrategy;
import strategies.BotPlayingStrategyFactory;

public class Botplayer extends Player{
    private Level level;
    private BotPlayingStrategy botPlayingStrategy;

    public Botplayer(String name, Character symbol, int id, PlayerType playerType, Level level) {
        super(name, symbol, id, playerType);
        this.level = level;
        this.botPlayingStrategy= BotPlayingStrategyFactory.getPlayingStrategy(level);
    }

    @Override
    public Move makeMove(Board board){
        Move suggestedmove = botPlayingStrategy.suggestMove(board);
        suggestedmove.getCell().setPlayer(this);
        return suggestedmove;
    }

}
