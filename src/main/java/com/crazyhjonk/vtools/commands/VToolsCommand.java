package com.crazyhjonk.vtools.commands;

import com.crazyhjonk.core.commands.CommandPermission;
import com.crazyhjonk.velocity.commands.VeloCommand;
import com.velocitypowered.api.command.CommandSource;
import com.crazyhjonk.vtools.VTools;

import java.util.concurrent.CompletableFuture;

public class VToolsCommand extends VeloCommand<VTools> {

    public VToolsCommand() {
        super(VTools.getMain(), "vtools", "VTools main command");
    }

    @Override
    public CompletableFuture<Boolean> execute(CommandSource source, String[] args) {
        executeHelpCommand(source);
        return CompletableFuture.completedFuture(true);
    }

    @Override
    public CommandPermission getPermissionDefault() {
        return CommandPermission.OP;
    }
}
