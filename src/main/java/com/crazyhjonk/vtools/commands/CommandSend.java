package com.crazyhjonk.vtools.commands;

import com.crazyhjonk.core.commands.Argument;
import com.crazyhjonk.core.commands.CommandPermission;
import com.crazyhjonk.velocity.commands.VeloCommand;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.server.RegisteredServer;
import com.crazyhjonk.vtools.VTools;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static com.crazyhjonk.vtools.VTools.COLOR_RED;
import static com.crazyhjonk.vtools.VTools.COLOR_YELLOW;

public class CommandSend extends VeloCommand<VTools> {

    public CommandSend() {
        super("send", "Send a player to a specified server");
    }

    public CommandSend(boolean ignored) {
        super(VTools.getMain(), "send", "Send a player to a specified server");
    }

    public CompletableFuture<Boolean> execute(CommandSource source, String[] args) {
        Optional<Player> oPlayer = getMain().getServer().getPlayer(args[0]);
        Optional<RegisteredServer> oServer = getMain().getServer().getServer(args[1]);
        if (oPlayer.isEmpty() || oServer.isEmpty()) {
            getMain().sendMessage(source, Component.text("The server or user does not exist!").color(COLOR_RED));
            return CompletableFuture.completedFuture(true);
        }

        Player player = oPlayer.get();
        RegisteredServer server = oServer.get();
        player.createConnectionRequest(server).connect();
        getMain().sendMessage(source, Component.text("You sent " + player.getUsername() + " to " +
            server.getServerInfo().getName() + "!").color(COLOR_YELLOW));
        getMain().sendMessage(player, Component.text("You got sent to " + server.getServerInfo().getName() + "!").color(COLOR_YELLOW));
        return CompletableFuture.completedFuture(true);
    }

    @Override
    public List<Argument> defineArgs() {
        return List.of(
            new Argument("player", true),
            new Argument("server", true)
        );
    }

    @Override
    public List<String> tabComplete(CommandSource sender, String @NotNull [] args) {
        List<String> arg = new ArrayList<>();
        if (args.length == 1) {
            for (Player player : getMain().getServer().getAllPlayers()) {
                arg.add(player.getUsername());
            }
            return arg;
        } else if (args.length == 2) {
            for (RegisteredServer server : getMain().getServer().getAllServers()) {
                arg.add(server.getServerInfo().getName());
            }
        }
        return arg;
    }

    @Override
    public CommandPermission getPermissionDefault() {
        return CommandPermission.OP;
    }
}
