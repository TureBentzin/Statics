package net.juligames;

import io.github.miniplaceholders.api.Expansion;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.tag.Tag;
import org.bukkit.plugin.java.JavaPlugin;

public final class Statics extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Plugin is starting up!");

        Expansion.Builder builder = Expansion.builder("statics");

        builder.globalPlaceholder("demo", (argumentQueue, context) -> {
            return Tag.inserting(Component.text("Hello, World!"));
        });

        Expansion expansion = builder.build();
        expansion.register();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
