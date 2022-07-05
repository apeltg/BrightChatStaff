package com.github.ojvzinn.chatstaff.bungeecord.commands;

import com.github.ojvzinn.chatstaff.bungeecord.Bungee;
import com.github.ojvzinn.chatstaff.bungeecord.roles.Role;
import com.github.ojvzinn.chatstaff.utils.StringUtils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.Arrays;
import java.util.StringJoiner;

public class ChatStaffCommand extends Commands {

    public ChatStaffCommand() {
        super("chatstaff", "sc");
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) sender;
            if (!player.hasPermission("brightutils.cmd.chatstaff")) {
                player.sendMessage(TextComponent.fromLegacyText("§cPara executar este comando, é ncessário ter o grupo §e§lAJudante §cou superior."));
                return;
            }
            if (args.length < 1) {
                player.sendMessage(TextComponent.fromLegacyText("§cUso incorreto! Tente /sc (mensagem)"));
                return;
            }
            String[] message = args.clone();
            StringJoiner joiner = new StringJoiner(" ");
            for (String s : message) {
                joiner.add(s);
            }
            String str = joiner.toString();
            Bungee.getInstance().getProxy().getPlayers().stream().filter(player1 -> player1.hasPermission("brightutils.cmd.chatstaff")).forEach(player1 -> player1.sendMessage(TextComponent.fromLegacyText("§d[ChatStaff] §6[" + player.getServer().getInfo().getName().toLowerCase() + "] §f" + Role.getRoleForPlayer(player).getPrefix() + "" + player.getName() + "" + "§f: " + StringUtils.formatColors(str))));
        } else {
            if (args.length < 1) {
                sender.sendMessage(TextComponent.fromLegacyText("§cUso incorreto! Tente /sc (mensagem)"));
                return;
            }
            String[] message = args.clone();
            Bungee.getInstance().getProxy().getPlayers().stream().filter(player1 -> player1.hasPermission("brightutils.cmd.chatstaff")).forEach(player1 -> player1.sendMessage(TextComponent.fromLegacyText(Arrays.toString(message))));
        }
    }
}
