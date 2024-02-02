package dev.unowly.oneplugin.listener;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;
import dev.unowly.oneplugin.teams.TeamManager;
import dev.unowly.oneplugin.OnePlugin;

import java.util.Objects;

public class PlayerJoinListener implements Listener {
    private final TeamManager teamManager;
    private final OnePlugin plugin;

    public PlayerJoinListener(OnePlugin plugin, TeamManager teamManager) {
        this.plugin = plugin;
        this.teamManager = teamManager;
    }


    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Team team = teamManager.getTeamForPlayer(player.getUniqueId().toString());

        event.getPlayer().sendTitle(ChatColor.AQUA + "Herzlich Willkommen", ChatColor.YELLOW + "auf unowly.dev", 10, 70,20);
        Component header = Component.text(plugin.getTablistHeader());
        Component footer = Component.text(plugin.getTablistFooter());

        event.getPlayer().sendPlayerListHeader(header);
        event.getPlayer().sendPlayerListFooter(footer);
        if (team != null) {
            team.addEntry(player.getName());
            player.setScoreboard(Objects.requireNonNull(team.getScoreboard()));
        }
    }
}
