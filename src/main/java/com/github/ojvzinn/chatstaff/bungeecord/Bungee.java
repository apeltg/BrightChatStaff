package com.github.ojvzinn.chatstaff.bungeecord;

import com.github.ojvzinn.chatstaff.bungeecord.commands.Commands;
import com.github.ojvzinn.chatstaff.bungeecord.roles.Role;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;

public class Bungee extends Plugin {

    private static Bungee instance;
    private static Configuration roles;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();
        Role.setupRolesBungee();
        Commands.setupCommands();

        BungeeCord.getInstance().getConsole().sendMessage(TextComponent.fromLegacyText("§a[BrightChatStaff] O plugin ligou com sucesso!"));
    }

    @Override
    public void onDisable() {
        BungeeCord.getInstance().getConsole().sendMessage(TextComponent.fromLegacyText("§c[BrightChatStaff] O plugin desligou com sucesso!"));
    }

    public static Bungee getInstance() {
        return instance;
    }

    public static void copyFile(InputStream input, File out) {
        FileOutputStream ou = null;
        try {
            ou = new FileOutputStream(out);
            byte[] buff = new byte[1024];
            int len;
            while ((len = input.read(buff)) > 0) {
                ou.write(buff, 0, len);
            }
        } catch (IOException ex) {
            getInstance().getLogger().log(Level.WARNING, "Failed at copy file " + out.getName() + "!", ex);
        } finally {
            try {
                if (ou != null) {
                    ou.close();
                }
                if (input != null) {
                    input.close();
                }
            } catch (IOException ignore) {
            }
        }
    }

    public void saveDefaultConfig() {
        for (String fileName : new String[]{"roles"}) {
            File file = new File("plugins/BrightChatStaff/" + fileName + ".yml");
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                copyFile(Bungee.getInstance().getResourceAsStream(fileName + ".yml"), file);
            }

            try {
                roles = YamlConfiguration.getProvider(YamlConfiguration.class).load(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
            } catch (IOException ex) {
                this.getLogger().log(Level.WARNING, "Cannot load " + fileName + ".yml: ", ex);
            }
        }
    }

    public static Configuration getRole() {
        return roles;
    }
}
