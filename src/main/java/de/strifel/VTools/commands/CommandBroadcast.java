package de.strifel.VTools.commands;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import net.kyori.adventure.text.Component;

import java.util.ArrayList;
import java.util.List;

import static de.strifel.VTools.VTools.COLOR_RED;

public class CommandBroadcast implements SimpleCommand {
    private final ProxyServer server;

    public CommandBroadcast(ProxyServer server) {
        this.server = server;
    }

    @Override
    public void execute(SimpleCommand.Invocation invocation) {
        CommandSource commandSource = invocation.source();
        String[] strings = invocation.arguments();

        if (strings.length == 0) {
            commandSource.sendMessage(Component.text("Usage: /broadcast <message>").color(COLOR_RED));
            return;
        }

        String message = String.join(" ", strings).replace("&", "§");
        for (Player player : server.getAllPlayers()) {
            player.sendMessage(Component.text(message));
        }
    }

    @Override
    public List<String> suggest(SimpleCommand.Invocation invocation) {
        return new ArrayList<>();
    }

    @Override
    public boolean hasPermission(SimpleCommand.Invocation invocation) {
        return invocation.source().hasPermission("vtools.broadcast");
    }
}
