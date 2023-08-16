package me.adelemphii.skyburgerchallenges.objects.effects;

import org.bukkit.Instrument;
import org.bukkit.Note;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Iterator;
import java.util.Map;

public class JingleSound extends BukkitRunnable {

    private final Player player;

    private final Instrument instrument;
    private final Map<Integer, Note> notes;
    private Iterator<Integer> noteIterator;

    public JingleSound(Map<Integer, Note> notes, Instrument instrument, Player player) {
        this.notes = notes;
        this.noteIterator = notes.keySet().iterator();
        this.instrument = instrument;

        this.player = player;
    }

    @Override
    public void run() {
        if (!noteIterator.hasNext()) {
            this.cancel();
            return;
        }

        Note note = notes.get(noteIterator.next());
        player.playNote(player.getLocation(), instrument, note);
    }
}
