package dev.blavez.fortuneblocks.config;

import eu.okaeri.configs.OkaeriConfig;
import org.bukkit.Material;

import java.util.*;

public class GeneralConfig extends OkaeriConfig {

    private Map<Material, ToolConfig> tools = new EnumMap<>(Material.class);

    {
        tools.put(
                Material.DIAMOND_PICKAXE,
                new ToolConfig(
                        EnumSet.of(
                                Material.DIAMOND_BLOCK,
                                Material.EMERALD_BLOCK
                        )
                )
        );

        tools.put(
                Material.DIAMOND_HOE,
                new ToolConfig(
                        EnumSet.of(Material.HAY_BLOCK)
                )
        );

        tools.put(
                Material.SHEARS,
                new ToolConfig(
                        EnumSet.of(Material.WHITE_WOOL)
                )
        );
    }

    public Map<Material, ToolConfig> getTools() {
        return tools;
    }

    public static class ToolConfig extends OkaeriConfig {

        private Set<Material> blocks = EnumSet.noneOf(Material.class);

        public ToolConfig() {}

        public ToolConfig(Set<Material> blocks) {
            this.blocks = blocks;
        }

        public Set<Material> getBlocks() {
            return blocks;
        }
    }
}