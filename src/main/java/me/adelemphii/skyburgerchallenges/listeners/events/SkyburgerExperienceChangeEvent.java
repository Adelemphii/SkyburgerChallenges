package me.adelemphii.skyburgerchallenges.listeners.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class SkyburgerExperienceChangeEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();

    public SkyburgerExperienceChangeEvent() {
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
