package com.github.ojvzinn.chatstaff.commands;

import com.github.ojvzinn.chatstaff.roles.Role;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.StringJoiner;

public class ChatStaffCommand extends Commands {

    public ChatStaffCommand() {
        super("staffchat", "sc");
    }

    @Override
    public void perform(CommandSender sender, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cEste comando é exclusivo para jogadores.");
            return;
        }

        Player player = (Player) sender;

        if (!(player.hasPermission("brightutils.cmd.chatstaff"))) {
            player.sendMessage("§cPara executar este comando, é necessário ter o cargo §6Ajudante §cou superior.");
            return;
        }

        if (args.length < 1) {
            player.sendMessage("§cUso incorreto! Tente /sc (mensagem)");
            return;
        }

        String[] message = args.clone();
        StringJoiner joiner = new StringJoiner(" ");
        for (String s : message) {
            joiner.add(s);
        }
        String str = joiner.toString();

        Bukkit.getOnlinePlayers().stream().filter(player1 -> player1.hasPermission("brightutils.cmd.chatstaff")).forEach(player1 -> player1.sendMessage("§d[ChatStaff] " + Role.getRoleForPlayer(player) + "§7: " + str));
    }
}
