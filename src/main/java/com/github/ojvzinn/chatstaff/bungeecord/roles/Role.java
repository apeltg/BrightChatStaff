package com.github.ojvzinn.chatstaff.bungeecord.roles;

import com.github.ojvzinn.chatstaff.bungeecord.Bungee;
import com.github.ojvzinn.chatstaff.utils.StringUtils;
import net.md_5.bungee.api.connection.ProxiedPlayer;

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

    public static void setupRolesBungee() {
        for (String key : Bungee.getRole().getSection("roles").getKeys()) {
            Role role = new Role(key, Bungee.getRole().getString("roles." + key + ".name"), StringUtils.formatColors(Bungee.getRole().getString("roles." + key + ".prefix")), Bungee.getRole().getString("roles." + key + ".permission"));
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

    public static Role getRoleForPlayer(ProxiedPlayer player) {
        return roles.stream().filter(role -> player.hasPermission(role.getPermission())).findFirst().orElse(roles.get(roles.size() - 1));
    }
}
