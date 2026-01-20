package net.frozenblock.worldgenv2;

import com.hypixel.hytale.builtin.hytalegenerator.plugin.HandleProvider;
import com.hypixel.hytale.codec.lookup.Priority;
import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.hypixel.hytale.server.core.universe.world.worldgen.provider.IWorldGenProvider;
import org.jspecify.annotations.NonNull;

@SuppressWarnings("unused")
public class WorldGenV2Plugin extends JavaPlugin {

    public static final HytaleLogger LOGGER = HytaleLogger.forEnclosingClass();

    public WorldGenV2Plugin(@NonNull JavaPluginInit init) {
        super(init);
        LOGGER.atInfo().log("Hello from %s version %s", this.getName(), this.getManifest().getVersion().toString());
    }

    @Override
    protected void setup() {
        this.getCommandRegistry().registerCommand(new PoopCommand());
    }

    @Override
    protected void start() {
        LOGGER.atInfo().log("Enabling World Gen V2");

        var V2 = IWorldGenProvider.CODEC.getCodecFor("HytaleGenerator");

        IWorldGenProvider.CODEC.remove(HandleProvider.class);
        IWorldGenProvider.CODEC.register(Priority.DEFAULT.before(2), "HytaleGenerator", HandleProvider.class, V2);
    }
}
