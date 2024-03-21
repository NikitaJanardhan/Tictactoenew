package factories;

import Models.DifficultyLevel;
import Strategy.BotPlayingStrategy;
import Strategy.EasyBotPlayingStrategy;

public class BotPlayingStrategyFactory {

    public static BotPlayingStrategy getBotPlayingstrategy(DifficultyLevel difficultyLevel) {
        return new EasyBotPlayingStrategy();

    }
}
