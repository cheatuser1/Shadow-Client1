package dev.shadow.client.module.modules;

import dev.shadow.client.module.Module;
import net.minecraft.client.MinecraftClient;

public class Fullbright extends Module {
    private double previousGamma;

    public Fullbright() { super("Fullbright", Category.RENDER); }

    @Override
    protected void onEnable() {
        MinecraftClient client = MinecraftClient.getInstance();
        previousGamma = client.options.getGamma().getValue();
        client.options.getGamma().setValue(16.0);
    }

    @Override
    protected void onDisable() {
        MinecraftClient.getInstance().options.getGamma().setValue(previousGamma);
    }
}
