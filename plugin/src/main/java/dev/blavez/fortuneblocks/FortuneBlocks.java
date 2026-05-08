package dev.blavez.fortuneblocks;

import com.google.inject.Guice;
import com.google.inject.Injector;
import dev.blavez.fortuneblocks.di.PluginModule;
import dev.blavez.fortuneblocks.listener.BlockDropListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class FortuneBlocks extends JavaPlugin {

    @Override
    public void onEnable() {
        Injector injector = Guice.createInjector(new PluginModule(this));

        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(injector.getInstance(BlockDropListener.class), this);

    }
}
