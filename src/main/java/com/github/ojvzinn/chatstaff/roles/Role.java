package com.github.ojvzinn.chatstaff.roles;

import com.github.ojvzinn.chatstaff.Main;
import com.github.ojvzinn.chatstaff.bungeecord.Bungee;
import com.github.ojvzinn.chatstaff.utils.StringUtils;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Role {

    private final String key;
    private final String name;
    private final String prefix;
    private final String permission;
    private static final List<Role> roles = new ArrayList<>();

    public Role(String key, String name, String prefix, String permission) {
        this.key = key;
        this.permission = permission;
        this.prefix = prefix;
        this.name = name;
    }

    public static void setupRolesBukkit() {
        for (String key : Main.getInstance().getConfig().getConfigurationSection("roles").getKeys(false)) {
            Role role = new Role(key, Main.getInstance().getConfig().getString("roles." + key + ".name"), StringUtils.formatColors(Main.getInstance().getConfig().getString("roles." + key + ".prefix")), Main.getInstance().getConfig().getString("roles." + key + ".permission"));
            roles.add(role);
        }
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getPermission() {
        return permission;
    }

    public String getPrefix() {
        return prefix;
    }

    public static List<Role> getRoles() {
        return roles;
    }

    public static Role getRoleForPlayer(Player player) {
        return roles.stream().filter(role -> player.hasPermission(role.getPermission())).findFirst().orElse(roles.get(roles.size() - 1));
    }
}
