package com.crazyhjonk.vtools.commands;

import com.crazyhjonk.core.commands.Argument;
import com.crazyhjonk.core.commands.CommandData;
import com.crazyhjonk.core.commands.CommandPermission;
import com.crazyhjonk.velocity.commands.VeloCommand;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import com.crazyhjonk.vtools.VTools;
import net.kyori.adventure.text.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CommandRestart extends VeloCommand<VTools> {

    public CommandRestart() {
        super("proxyrestart", "Restart the proxy server");
    }

    public CommandRestart(boolean ignored) {
        super(VTools.getMain(), "proxyrestart", "Restart the proxy server");
    }

    @Override
    public CompletableFuture<Boolean> execute(CommandSource source, String[] args, CommandData data) {

        if (args.length > 0) {
            String message = String.join(" ", args).replace("&", "§");
            for (Player player : getMain().getServer().getAllPlayers()) {
                player.disconnect(Component.text(message));
            }
        }
        getMain().getServer().getCommandManager().executeAsync(
            getMain().getServer().getConsoleCommandSource(), "shutdown");
        return CompletableFuture.completedFuture(true);
    }

    @Override
    public List<Argument> defineArgs() {
        return List.of(
            new Argument("message", false)
        );
    }

    @Override
    public CommandPermission getPermissionDefault() {
        return CommandPermission.OP;
    }
}
