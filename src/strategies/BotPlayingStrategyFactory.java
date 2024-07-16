package strategies;

import models.Level;

public class BotPlayingStrategyFactory {

    public static BotPlayingStrategy getPlayingStrategy(Level level) {
        BotPlayingStrategy botPlayingStrategy= new EasyBotPlayingStrategy();
        switch (level) {
            case EASY -> botPlayingStrategy= new EasyBotPlayingStrategy();
            case MEDIUM -> throw new IllegalArgumentException();
        }
        return botPlayingStrategy;
    }
}
