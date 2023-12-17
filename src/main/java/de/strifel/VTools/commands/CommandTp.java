package de.strifel.VTools.commands;

import com.crazyhjonk.core.commands.Argument;
import com.crazyhjonk.core.commands.CommandPermission;
import com.crazyhjonk.velocity.commands.VeloCommand;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import de.strifel.VTools.VTools;
import net.kyori.adventure.text.Component;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static de.strifel.VTools.VTools.COLOR_RED;
import static de.strifel.VTools.VTools.COLOR_YELLOW;

public class CommandTp extends VeloCommand<VTools> {

    public CommandTp() {
        super(VTools.getMain(), "proxytp", "Teleport to another player's server");
    }

    public CommandTp(boolean ignored) {
        super("proxytp", "Teleport to another player's server");
    }

    @Override
    public CompletableFuture<Boolean> execute(CommandSource source, String[] args) {

        if (!(source instanceof Player)) {
            getMain().sendMessage(source, Component.text("Command is only for players.").color(COLOR_RED));
            return CompletableFuture.completedFuture(false);
        }

        Optional<Player> player = getMain().getPlayer(args[0]);
        if (player.isEmpty()) {
            getMain().sendMessage(source, Component.text("Player does not exist.").color(COLOR_RED));
            return CompletableFuture.completedFuture(false);
        }
        player.get().getCurrentServer().ifPresent(serverConnection -> ((Player) source)
            .createConnectionRequest(serverConnection.getServer()).fireAndForget());
        getMain().sendMessage(source, Component.text("Connecting to the server of " + args[0]).color(COLOR_YELLOW));
        return CompletableFuture.completedFuture(true);
    }

    @Override
    public CommandPermission getPermissionDefault() {
        return CommandPermission.OP;
    }

    @Override
    public List<Argument> defineArgs() {
        return List.of(
            new Argument("PLAYERS", true)
        );
    }

    @Override
    public List<String> getAliases() {
        return List.of("jump");
    }
}
