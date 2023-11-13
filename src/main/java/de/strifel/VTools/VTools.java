package de.strifel.VTools;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import de.strifel.VTools.commands.*;
import net.kyori.adventure.text.format.TextColor;

import javax.inject.Inject;

@Plugin(id = "vtools", name="VTools", version="1.3", description="Some commands!")
public class VTools {
    private final ProxyServer server;

    public static final TextColor COLOR_RED = TextColor.fromCSSHexString("FF5555");
    public static final TextColor COLOR_YELLOW = TextColor.fromCSSHexString("FFFF55");

    @Inject
    public VTools(ProxyServer server) {
        this.server = server;
    }


    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        server.getCommandManager().register("send", new CommandSend(server));
        server.getCommandManager().register("sendall", new CommandSendall(server));
        server.getCommandManager().register("proxybroadcast", new CommandBroadcast(server), "proxybc");
        server.getCommandManager().register("find", new CommandFind(server), "search");
        server.getCommandManager().register("proxyrestart", new CommandRestart(server));
        server.getCommandManager().register("proxytp", new CommandTp(server), "jump");
    }

}
