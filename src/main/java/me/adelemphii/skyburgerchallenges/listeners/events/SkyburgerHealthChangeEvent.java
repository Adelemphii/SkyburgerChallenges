package me.adelemphii.skyburgerchallenges.listeners.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Monitor changes to the max health of players.
 */
public class SkyburgerHealthChangeEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();

    private final int oldHealth;
    private final int newHealth;

    public SkyburgerHealthChangeEvent(int oldHealth, int newHealth) {
        this.oldHealth = oldHealth;
        this.newHealth = newHealth;
    }

    /**
     * Get the new health value
     * @return new health value
     */
    public int getNewHealth() {
        return newHealth;
    }

    /**
     * Get the old health value
     * @return old health value
     */
    public int getOldHealth() {
        return oldHealth;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
