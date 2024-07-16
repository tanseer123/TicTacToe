package models;
import lombok.Data;
import strategies.ColumnWinningStrategy;
import strategies.RowWinningStrategy;
import strategies.WinningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

@Data
public class Game {
    private Board board;
    private List<Player> players;
    private List<Move> moves;
    private int nextPlayerIndex;
    private Player winner;
    private GameState gameState;
    private List<WinningStrategy> winningStrategies;

    private Game(Builder builder) {
        //if (builder.dimension <3) {
        //    throw new IllegalArgumentException("Dimension must be greater than 0");
        //}
        validateplayer(builder.players);
        this.board = new Board(builder.dimension);
        this.players = builder.players;
        this.winningStrategies = builder.winningStrategies;
        this.moves = new ArrayList<>();
        this.nextPlayerIndex = 0;
        this.gameState=GameState.IN_PROGRESS;

    }

    private static void validateplayer(List<Player> players) {
        Set<Character> characterSet = new HashSet<>();
        int bots=0;
        for (Player player : players) {
            characterSet.add(player.getSymbol());
            if(player.getPlayerType().equals(PlayerType.BOT)) bots++;
        }
        if(bots>1 || characterSet.size()!=players.size()) {
            throw new IllegalArgumentException("You have not enough players");
        }
    }
    public static Builder getBuilder(){
        return new Builder();
    }

    public static class Builder{
        private List<Player> players;
        private List<WinningStrategy> winningStrategies;
        private int dimension;
        private Builder(){
            this.players = new ArrayList<>();
            this.winningStrategies = List.of(new RowWinningStrategy(), new ColumnWinningStrategy());
            this.dimension = 3;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Game build(){
            return new Game(this);
        }

    }

}
