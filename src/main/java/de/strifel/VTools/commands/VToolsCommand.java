package de.strifel.VTools.commands;

import com.crazyhjonk.core.commands.CommandPermission;
import com.crazyhjonk.velocity.commands.VeloCommand;
import com.velocitypowered.api.command.CommandSource;
import de.strifel.VTools.VTools;

import java.util.concurrent.CompletableFuture;

public class VToolsCommand extends VeloCommand<VTools> {

    public VToolsCommand() {
        super(VTools.getMain(), "vtools", "VTools main command");
    }

    @Override
    public CompletableFuture<Boolean> execute(CommandSource commandSource, String[] strings) {
        executeHelpCommand(commandSource);
        return CompletableFuture.completedFuture(true);
    }

    @Override
    public CommandPermission getPermissionDefault() {
        return CommandPermission.OP;
    }
}
