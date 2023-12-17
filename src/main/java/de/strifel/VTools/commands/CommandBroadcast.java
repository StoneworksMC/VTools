package de.strifel.VTools.commands;

import com.crazyhjonk.core.commands.Argument;
import com.crazyhjonk.core.commands.CommandPermission;
import com.crazyhjonk.velocity.commands.VeloCommand;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.proxy.Player;
import de.strifel.VTools.VTools;
import net.kyori.adventure.text.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CommandBroadcast extends VeloCommand<VTools> {

    public CommandBroadcast() {
        super(VTools.getMain(), "proxybroadcast", "Broadcast a message to all players on the network");
    }

    @Override
    public CompletableFuture<Boolean> execute(CommandSource source, String[] args) {
        String message = String.join(" ", args).replace("&", "§");
        for (Player player : getMain().getServer().getAllPlayers()) {
            player.sendMessage(Component.text(message));
        }
        return CompletableFuture.completedFuture(true);
    }

    @Override
    public CommandPermission getPermissionDefault() {
        return CommandPermission.OP;
    }

    @Override
    public List<Argument> defineArgs() {
        return List.of(
            new Argument("message", true)
        );
    }

    @Override
    public List<String> getAliases() {
        return List.of("proxybc");
    }
}
