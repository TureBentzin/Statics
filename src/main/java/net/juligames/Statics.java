package net.juligames;

import io.github.miniplaceholders.api.Expansion;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.Tag;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public final class Statics extends JavaPlugin {

    private @Nullable Expansion expansion;

    @Override
    public void onEnable() {
        saveResource("config.yml", false);

        final MiniMessage miniMessage = MiniMessage.miniMessage();

        Expansion.Builder builder = Expansion.builder("statics");

        AtomicInteger i = new AtomicInteger();
        Objects.requireNonNull(getConfig().getConfigurationSection("placeholders"),
                        "Invalid configuration! Define your placeholders in a \"placeholders\" configuration section")
                .getValues(false).forEach(
                        (key, value) -> {
                            final String placeholder = (String) value;
                            Component component = miniMessage.deserialize(placeholder);
                            i.getAndIncrement();
                            builder.globalPlaceholder(key, (argumentQueue, context) -> Tag.inserting(component));
                        });

        expansion = builder.build();
        expansion.register();

        getLogger().info("Loaded " + i + " placeholders");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        if (expansion != null) {
            expansion.unregister();
        }
    }
}
