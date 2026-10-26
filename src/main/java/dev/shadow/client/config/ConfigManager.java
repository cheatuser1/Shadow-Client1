package dev.shadow.client.config;

import dev.shadow.client.ShadowClient;
import dev.shadow.client.module.Module;
import net.fabricmc.loader.api.FabricLoader;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.nio.file.Files;
import java.nio.file.Path;

public final class ConfigManager {
    private static final Path FILE = FabricLoader.getInstance().getConfigDir().resolve("shadowclient.json");
    private ConfigManager() {}

    public static void load() {
        if (!Files.exists(FILE) || ShadowClient.MODULES == null) return;
        try {
            JsonObject root = JsonParser.parseString(Files.readString(FILE)).getAsJsonObject();
            for (Module module : ShadowClient.MODULES.getModules()) {
                if (root.has(module.getName())) module.setEnabled(root.get(module.getName()).getAsBoolean());
            }
        } catch (Exception e) {
            System.err.println("[Shadow Client] Could not load config: " + e.getMessage());
        }
    }

    public static void save() {
        if (ShadowClient.MODULES == null) return;
        try {
            JsonObject root = new JsonObject();
            for (Module module : ShadowClient.MODULES.getModules()) {
                root.addProperty(module.getName(), module.isEnabled());
            }
            Files.createDirectories(FILE.getParent());
            Files.writeString(FILE, root.toString());
        } catch (Exception e) {
            System.err.println("[Shadow Client] Could not save config: " + e.getMessage());
        }
    }
}
