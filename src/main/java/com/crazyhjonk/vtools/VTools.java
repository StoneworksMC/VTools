package com.crazyhjonk.vtools;

import com.crazyhjonk.vtools.commands.*;
import com.crazyhjonk.velocity.VeloCrazyPlugin;
import com.crazyhjonk.velocity.commands.VeloAboutCommand;
import com.crazyhjonk.velocity.commands.VeloReloadCommand;
import com.crazyhjonk.velocity.config.VeloConfigManager;
import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import net.kyori.adventure.text.format.TextColor;
import org.slf4j.Logger;

import java.nio.file.Path;
import java.util.List;

@Plugin(id = "vtools", name="VTools", version=VTools.VERSION, description="Some commands!", authors = "CrazyHjonk")
public class VTools extends VeloCrazyPlugin {

    public static final String VERSION = "1.4.3";

    public static final TextColor COLOR_RED = TextColor.fromCSSHexString("FF5555");
    public static final TextColor COLOR_YELLOW = TextColor.fromCSSHexString("FFFF55");

    /**
     * Constructor for velocity plugins. Add @Inject to this constructor.
     *
     * @param server        Velocity specific proxy server instance.
     * @param logger        Velocity specific logger instance.
     * @param dataDirectory Velocity specific data directory.
     */
    @Inject
    public VTools(ProxyServer server, Logger logger, @DataDirectory Path dataDirectory) {
        super(server, logger, dataDirectory);
    }

    @Override
    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        super.onProxyInitialization(event);
    }

    @Override
    public void initializeConfigManager() {
        this.configManager = new VeloConfigManager(this);
    }

    @Override
    public void initializeDepends() {

    }

    @Override
    public void initializeCommands() {
        new VToolsCommand().registerSubCommands(
            new VeloReloadCommand<>(),
            new VeloAboutCommand<>(),
            new CommandSend(),
            new CommandSendall(),
            new CommandBroadcast(),
            new CommandFind(),
            new CommandTp(),
            new CommandRestart()
        );
        new CommandSend(true);
        new CommandSendall(true);
        new CommandBroadcast(true);
        new CommandFind(true);
        new CommandTp(true);
        new CommandRestart(true);
    }

    @Override
    public void initializeListeners() {

    }

    @Override
    public String getIdentifier() {
        return "vtools";
    }

    @Override
    public String getName() {
        return "VTools";
    }

    @Override
    public List<String> getAuthors() {
        return List.of("CrazyHjonk");
    }

    @Override
    public String getVersion() {
        return VERSION;
    }

    public static VTools getMain() {
        return getMain(VTools.class);
    }
}
