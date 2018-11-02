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
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.player.PlayerLoginEvent;

import java.util.LinkedHashMap;
import java.util.Map;
import java.io.File;
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class Kick extends PluginBase implements Listener {
    public Config config;
    public ConfigSection configSection;
    public Kick() {}
    
    public void onEnable() {
        getDataFolder().mkdirs();
        getLogger().info("Disconnect enabled.");
        
        Map<String, String> configMap = new LinkedHashMap<String, String>();
        ArrayList input = new ArrayList();
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
        input.add(configMap);
        configSection = new ConfigSection();
        configSection.set("messages", configMap);
        config = new Config(new File(this.getDataFolder(), "config.yml"), 2, configSection);
        config.save();
    }
    
    private String format(Player player, String message) {
        return
        cn.nukkit.utils.TextFormat.colorize('&', message).replaceAll("%player%", player.getPlayer().getName()).replaceAll("%players%", String.valueOf(getServer().getOnlinePlayers().size())).replaceAll("%tps%", String.valueOf(getServer().getTicksPerSecond())).replaceAll("%maxplayers%", String.valueOf(getServer().getMaxPlayers())).replaceAll("%motd%", getServer().getMotd());
    }
    
    @EventHandler(priority = EventPriority.HIGH)
    public void onKick(PlayerKickEvent event) {
        Player player = event.getPlayer();
        player.close(player.getLeaveMessage(), (String) config.get("NOT_WHITELISTED", "Disconnected."));
    }

}