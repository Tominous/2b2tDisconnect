package Disconnect;

import cn.nukkit.*;
import cn.nukkit.event.player.*;
import cn.nukkit.event.player.PlayerGameModeChangeEvent;
import cn.nukkit.Server;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.event.player.PlayerGameModeChangeEvent;
import cn.nukkit.event.player.PlayerEvent;
import cn.nukkit.event.player.PlayerKickEvent;
import cn.nukkit.event.Listener;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.ConfigSection;
import cn.nukkit.utils.TextFormat;
import cn.nukkit.event.EventHandler;

import java.util.LinkedHashMap;
import java.util.Map;

public class Kick extends PluginBase implements Listener {
    public Config config;
    public ConfigSection configSection;
    public Kick() {}
    
    public void onEnable() {
        getDataFolder().mkdirs();
        getLogger().info("Disconnect enabled.");
        
        Map configMap = new LinkedHashMap();
        configMap.put("FLYING_DISABLED", "Disconnected.");
        configMap.put("INVALID_PVE", "Disconnected.");
        configMap.put("IP_BANNED", "Disconnected.");
        configMap.put("KICKED_BY_ADMIN", "Disconnected.");
        configMap.put("LOGIN_TIMEOUT", "Disconnected.");
        configMap.put("NAME_BANNED", "Disconnected.");
        configMap.put("NEW_CONNECTION", "Disconnected.");
        configMap.put("NOT_WHITELISTED", "Disconnected.");
        configMap.put("SERVER_FULL", "Disconnected.");
        configMap.put("UNKNOWN", "Disconnected.");
        configSection = new ConfigSection(configSection);
    }
    
    private String format(Player player, String message) {
        return
        cn.nukkit.utils.TextFormat.colorize('&', message).replaceAll("%player%", player.getPlayer().getName()).replaceAll("%players%", String.valueOf(getServer().getOnlinePlayers().size())).replaceAll("%tps%", String.valueOf(getServer().getTicksPerSecond())).replaceAll("%maxplayers%", String.valueOf(getServer().getMaxPlayers())).replaceAll("%motd%", getServer().getMotd());
    }
    
    @EventHandler
    public void onKick(PlayerKickEvent event, Player player, PlayerKickEvent.Reason reason, String quitMessage) {
        PlayerKickEvent.Reason kReason = null;
        if (reason.equals(reason.FLYING_DISABLED)) {
            player.kick(TextFormat.colorize(getConfig().getString("FLYING_DISABLED").toString().replace("%player%", player.getName())));
        } else if (reason.equals(reason.INVALID_PVE)) {
            player.kick(TextFormat.colorize(getConfig().getString("INVALID_PVE").toString().replace("%player%", player.getName())));
        } else if (reason.equals(reason.IP_BANNED)) {
            player.kick(TextFormat.colorize(getConfig().getString("IP_BANNED").toString().replace("%player%", player.getName())));
        } else if (reason.equals(reason.KICKED_BY_ADMIN)) {
            player.kick(TextFormat.colorize(getConfig().getString("KICKED_BY_ADMIN").toString().replace("%player%", player.getName())));
        } else if (reason.equals(reason.LOGIN_TIMEOUT)) {
            player.kick(TextFormat.colorize(getConfig().getString("LOGIN_TIMEOUT").toString().replace("%player%", player.getName())));
        } else if (reason.equals(reason.NAME_BANNED)) {
            player.kick(TextFormat.colorize(getConfig().getString("NAME_BANNED").toString().replace("%player%", player.getName())));
        } else if (reason.equals(reason.NEW_CONNECTION)) {
            player.kick(TextFormat.colorize(getConfig().getString("NEW_CONNECTION").toString().replace("%player%", player.getName())));
        } else if (reason.equals(reason.NOT_WHITELISTED)) {
            player.kick(TextFormat.colorize(getConfig().getString("NOT_WHITELISTED").toString().replace("%player%", player.getName())));
        } else if (reason.equals(reason.SERVER_FULL)) {
            player.kick(TextFormat.colorize(getConfig().getString("SERVER_FULL").toString().replace("%player%", player.getName())));
        } else if (reason.equals(reason.UNKNOWN)) {
            player.kick(TextFormat.colorize(getConfig().getString("UNKNOWN").toString().replace("%player%", player.getName())));
        }
    }
}