package com.github.ojvzinn.chatstaff;

import com.github.ojvzinn.chatstaff.commands.Commands;
import com.github.ojvzinn.chatstaff.roles.Role;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        Role.setupRolesBukkit();
        Commands.setupCommands();

        Bukkit.getConsoleSender().sendMessage("§a[BrightStaffChat] O plugin ligou com sucesso!");
    }

    public static Main getInstance() {
        return instance;
    }
}
