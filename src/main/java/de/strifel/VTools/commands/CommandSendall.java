package de.strifel.VTools.commands;

import com.crazyhjonk.core.commands.Argument;
import com.crazyhjonk.core.commands.CommandPermission;
import com.crazyhjonk.velocity.commands.VeloCommand;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.server.RegisteredServer;
import de.strifel.VTools.VTools;
import net.kyori.adventure.text.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static de.strifel.VTools.VTools.COLOR_RED;
import static de.strifel.VTools.VTools.COLOR_YELLOW;

public class CommandSendall extends VeloCommand<VTools> {

    public CommandSendall() {
        super(VTools.getMain(), "sendall", "Send all players to a specified server");
    }

    @Override
    public CompletableFuture<Boolean> execute(CommandSource source, String[] args) {

        Optional<RegisteredServer> oServer = getMain().getServer().getServer(args[0]);
        if (oServer.isEmpty()) {
            getMain().sendMessage(source, Component.text("That server does not exist!").color(COLOR_RED));
            return CompletableFuture.completedFuture(false);
        }

        for (Player player : getMain().getServer().getAllPlayers()) {
            player.createConnectionRequest(oServer.get()).connect();
            getMain().sendMessage(player, Component.text("You are being sent to " + args[0]).color(COLOR_YELLOW));
        }
        return CompletableFuture.completedFuture(true);
    }

    @Override
    public List<Argument> defineArgs() {
        return List.of(
            new Argument("server", true)
        );
    }

    @Override
    public List<String> tabComplete(CommandSource source, String[] args) {
        List<String> arg = new ArrayList<>();
        if (args.length == 1) {
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
