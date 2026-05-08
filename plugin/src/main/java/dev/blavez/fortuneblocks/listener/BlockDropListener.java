package dev.blavez.fortuneblocks.listener;

import com.google.inject.Inject;
import dev.blavez.fortuneblocks.config.GeneralConfig;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.atomic.AtomicInteger;

public class BlockDropListener implements Listener {

    private final GeneralConfig config;

    @Inject
    public BlockDropListener(GeneralConfig config) {
        this.config = config;
    }

    @EventHandler
    public void onDrop(BlockDropItemEvent event) {

        ItemStack tool = event.getPlayer().getInventory().getItemInMainHand();

        if (!tool.containsEnchantment(Enchantment.FORTUNE)) {
            return;
        }

        GeneralConfig.ToolConfig toolConfig = config.getTools().get(tool.getType());

        if (toolConfig == null) {
            return;
        }

        if (!toolConfig.getBlocks().contains(event.getBlockState().getType())) {
            return;
        }

        int level = tool.getEnchantmentLevel(Enchantment.FORTUNE);

        int multiplier = fastRandom(level + 1) + 1;

        if (multiplier == 1) {
            return;
        }

        for (Item itemEntity : event.getItems()) {
            ItemStack item = itemEntity.getItemStack();
            item.setAmount(item.getAmount() * multiplier);
        }
    }

    private final AtomicInteger SEED = new AtomicInteger();

    private int fastRandom(int bound) {
        return (SEED.incrementAndGet() & Integer.MAX_VALUE) % bound;
    }
}
