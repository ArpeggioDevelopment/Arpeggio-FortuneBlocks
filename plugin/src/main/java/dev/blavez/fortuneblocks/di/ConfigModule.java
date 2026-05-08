package dev.blavez.fortuneblocks.di;

import com.google.inject.AbstractModule;
import dev.blavez.fortuneblocks.config.GeneralConfig;
import eu.okaeri.configs.ConfigManager;
import eu.okaeri.configs.yaml.bukkit.YamlBukkitConfigurer;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class ConfigModule extends AbstractModule {
    private final JavaPlugin plugin;

    public ConfigModule(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    protected void configure() {
        bind(GeneralConfig.class).toProvider(this::createGeneralConfig).asEagerSingleton();
    }

    public GeneralConfig createGeneralConfig() {
        return ConfigManager.create(GeneralConfig.class, config -> {
            config.configure(opt -> {
                opt.configurer(new YamlBukkitConfigurer());
                opt.bindFile(new File(plugin.getDataFolder(), "general.yml"));
                opt.removeOrphans(true);
            });
            config.saveDefaults();
            config.load(true);
        });
    }
}
