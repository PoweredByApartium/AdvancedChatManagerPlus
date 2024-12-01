package net.ofirtim.advancedchatmanagerplus.apploader.listeners;

import net.ofirtim.advancedchatmanagerplus.ChatPlayer;
import net.ofirtim.advancedchatmanagerplus.apploader.PaperAppLoader;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

public class PlayerLogListener implements Listener {

    private final PaperAppLoader plugin;

    public PlayerLogListener(PaperAppLoader plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerPreLogin(AsyncPlayerPreLoginEvent event) {
        if (event.getLoginResult() != AsyncPlayerPreLoginEvent.Result.ALLOWED) return;

        plugin.getAcmp().markForInit(new ChatPlayer(event.getUniqueId()));
     }
}
