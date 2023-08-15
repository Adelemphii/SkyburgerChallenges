package me.adelemphii.skyburgerchallenges.managers;

public class ExperienceManager {

    private int totalExperiencePoints = 0;
    private int levels = 0;

    public ExperienceManager() {

    }

    public void setTotalExperiencePoints(int totalExperiencePoints) {
        this.totalExperiencePoints = totalExperiencePoints;
    }

    public void setLevels(int levels) {
        this.levels = levels;
    }

    public int getTotalExperiencePoints() {
        return totalExperiencePoints;
    }

    public int getLevels() {
        return levels;
    }

    public void addExperience(int experiencePoints) {
        this.totalExperiencePoints += experiencePoints;
    }

    public void addLevels(int levels) {
        this.levels += levels;
    }

    public void removeExperience(int experiencePoints) {
        this.totalExperiencePoints -= experiencePoints;
    }

    public void removeLevels(int levels) {
        this.levels -= levels;
    }
}