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

public class CommandFind extends VeloCommand<VTools> {


    public CommandFind() {
        super(VTools.getMain(), "find", "Find a player on the network");
    }

    @Override
    public CompletableFuture<Boolean> execute(CommandSource source, String[] args) {

        Optional<Player> player = getMain().getServer().getPlayer(args[0]);
        if (player.isEmpty() || player.get().getCurrentServer().isEmpty()) {
            source.sendMessage(Component.text("The player is not online!").color(COLOR_YELLOW));
            return CompletableFuture.completedFuture(true);
        }

        source.sendMessage(Component.text("Player " + args[0] + " is on " +
            player.get().getCurrentServer().get().getServerInfo().getName() + "!").color(COLOR_YELLOW));
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
        return List.of("search");
    }
}
