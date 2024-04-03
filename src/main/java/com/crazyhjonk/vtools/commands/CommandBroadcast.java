package com.crazyhjonk.vtools.commands;

import com.crazyhjonk.core.commands.Argument;
import com.crazyhjonk.core.commands.CommandData;
import com.crazyhjonk.core.commands.CommandPermission;
import com.crazyhjonk.velocity.commands.VeloCommand;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import com.crazyhjonk.vtools.VTools;
import com.crazyhjonk.vtools.config.VToolsConfigRegistrar;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CommandBroadcast extends VeloCommand<VTools> {

    public CommandBroadcast() {
        super("proxybroadcast", "Broadcast a message to all players on the network");
    }

    public CommandBroadcast(boolean ignored) {
        super(VTools.getMain(), "proxybroadcast", "Broadcast a message to all players on the network");
    }

    @Override
    public CompletableFuture<Boolean> execute(CommandSource source, String[] args, CommandData data) {
        String message = VTools.getMain().getConfigManager().getString(VToolsConfigRegistrar.BROADCAST_PREFIX) + "§r " +
            String.join(" ", args).replace("&", "§");
        for (Player player : getMain().getServer().getAllPlayers()) {
            player.sendMessage(LegacyComponentSerializer.legacySection().deserialize(message));
        }
        if (!(source instanceof Player)) {
            source.sendMessage(LegacyComponentSerializer.legacySection().deserialize(message));
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
