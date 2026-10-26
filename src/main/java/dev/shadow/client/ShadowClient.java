package dev.shadow.client;

import dev.shadow.client.config.ConfigManager;
import dev.shadow.client.module.ModuleManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;

public class ShadowClient implements ClientModInitializer {
    public static final String NAME = "Shadow Client";
    public static ModuleManager MODULES;
    private static KeyBinding menuKey;

    @Override
    public void onInitializeClient() {
        MODULES = new ModuleManager();
        ConfigManager.load();
        menuKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.shadowclient.open_menu",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_RIGHT_SHIFT,
                "category.shadowclient"
        ));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (menuKey.wasPressed()) {
                if (client.currentScreen == null) {
                    client.setScreen(new dev.shadow.client.gui.ClickGuiScreen());
                } else if (client.currentScreen instanceof dev.shadow.client.gui.ClickGuiScreen) {
                    client.setScreen(null);
                }
            }
            MODULES.tick(client);
        });
    }
}
