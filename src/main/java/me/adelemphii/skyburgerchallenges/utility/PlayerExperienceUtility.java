package me.adelemphii.skyburgerchallenges.utility;

import org.bukkit.entity.Player;

/**
 * Experience Utility from JeffLib
 * <a href="https://github.com/JEFF-Media-GbR/JeffLib/blob/dc52ec64cd56be7bbc1cde5e997fd70523689c52/core/src/main/java/com/jeff_media/jefflib/ExpUtils.java#L31">Source</a>
 */
public class PlayerExperienceUtility {

    private static final float EPSILON_F = 1e-6f;

    /**
     * Gets the total amount of XP required to achieve a certain level when starting from 0 levels
     *
     * @param targetLevel Target level
     * @return Amount of XP required to reach the target level
     */
    public static int getTotalXPRequiredForLevel(final int targetLevel) {
        if (targetLevel <= 16) return squared(targetLevel) + (6 * targetLevel);
        if (targetLevel <= 31) return (int) ((2.5 * squared(targetLevel)) - (40.5 * targetLevel) + 360);
        return (int) ((4.5 * squared(targetLevel)) - (162.5 * targetLevel) + 2220);
    }

    private static int squared(final int a) {
        return a * a;
    }

    /**
     * Gets the total amount of XP required to reach currentLevel+1 from currentLevel
     *
     * @param currentLevel Current level
     * @return Amount of XP required to reach currentLevel+1
     */
    public static int getXPRequiredForNextLevel(final int currentLevel) {
        if (currentLevel <= 15) return (2 * currentLevel) + 7;
        if (currentLevel <= 30) return (5 * currentLevel) - 38;
        return (9 * currentLevel) - 158;
    }

    /**
     * Gets the amount of XP required to reach the next level from the current level and progress
     *
     * @param player Player to check for
     * @return Amount of XP required to reach the next level from the current level and progress
     */
    public static int getXpLeftUntilNextLevel(Player player) {
        int currentLevel = player.getLevel();
        float currentLevelProgress = player.getExp();
        return getXpLeftUntilNextLevel(currentLevel, currentLevelProgress);
    }

    /**
     * Gets the amount of XP required to reach the next level from the current level and progress
     *
     * @param currentLevel         The current level
     * @param currentLevelProgress The current level progress, must be between 0 and 1
     * @return Amount of XP required to reach the next level from the current level and progress
     * @throws IllegalArgumentException If currentLevelProgress is not between 0 and 1
     */
    public static int getXpLeftUntilNextLevel(int currentLevel, float currentLevelProgress) {
        if (currentLevelProgress - PlayerExperienceUtility.EPSILON_F > 1 || currentLevelProgress + PlayerExperienceUtility.EPSILON_F < 0) {
            throw new IllegalArgumentException("currentLevelProgress must be between 0 and 1, but was " + currentLevelProgress);
        }
        int xpRequiredFromCurrentLevelToNextLevel = PlayerExperienceUtility.getXPRequiredForNextLevel(currentLevel);
        int xpTheyAlreadyHaveInThisLevel = (int) (currentLevelProgress * xpRequiredFromCurrentLevelToNextLevel);
        return xpRequiredFromCurrentLevelToNextLevel - xpTheyAlreadyHaveInThisLevel;
    }
}
