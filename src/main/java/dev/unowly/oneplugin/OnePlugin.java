package dev.unowly.oneplugin;

import org.bukkit.plugin.java.JavaPlugin;
import dev.unowly.oneplugin.teams.TeamManager;
import dev.unowly.oneplugin.listener.PlayerJoinListener;

public class OnePlugin extends JavaPlugin {
    private TeamManager teamManager;
    private String tablistHeader;
    private String tablistFooter;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        tablistHeader = getConfig().getString("tablist.header", "Standard Header");
        tablistFooter = getConfig().getString("tablist.footer", "Standard Footer");
        teamManager = new TeamManager(this);
        teamManager.loadTeams();
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this, teamManager), this);
    }

    public TeamManager getTeamManager() {
        return teamManager;
    }
    public String getTablistHeader() {
        return tablistHeader;
    }

    public String getTablistFooter() {
        return tablistFooter;
    }
}
