package dev.unowly.oneplugin.teams;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import dev.unowly.oneplugin.OnePlugin;

public class TeamManager {
    private final OnePlugin plugin;
    private final Map<String, List<String>> teams = new HashMap<>();

    public TeamManager(OnePlugin plugin) {
        this.plugin = plugin;
    }

    public void loadTeams() {
        FileConfiguration config = plugin.getConfig();
        ScoreboardManager manager = plugin.getServer().getScoreboardManager();
        Scoreboard scoreboard = manager.getMainScoreboard();

        for (String teamName : Objects.requireNonNull(config.getConfigurationSection("Teams")).getKeys(false)) {
            List<String> uuids = config.getStringList("Teams." + teamName + ".members");
            String prefix = config.getString("Teams." + teamName + ".prefix", "");
            ChatColor color = ChatColor.valueOf(config.getString("Teams." + teamName + ".color", "WHITE").toUpperCase());

            Team team = scoreboard.getTeam(teamName);
            if (team == null) {
                team = scoreboard.registerNewTeam(teamName);
            }

            team.setPrefix(color + prefix);
            teams.put(teamName, uuids);
        }
    }

    public Team getTeamForPlayer(String uuid) {
        for (Map.Entry<String, List<String>> entry : teams.entrySet()) {
            if (entry.getValue().contains(uuid)) {
                return plugin.getServer().getScoreboardManager().getMainScoreboard().getTeam(entry.getKey());
            }
        }
        return null;
    }
}