package dev.shadow.client.module.modules;

import dev.shadow.client.module.Module;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;

public class CoordinatesHud extends Module {
    public CoordinatesHud() {
        super("Coordinates HUD", Category.RENDER);
        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> {
            if (!isEnabled()) return;
            MinecraftClient client = MinecraftClient.getInstance();
            if (client.player == null) return;
            String text = String.format("XYZ: %.1f / %.1f / %.1f   FPS: %d",
                    client.player.getX(), client.player.getY(), client.player.getZ(),
                    client.getCurrentFps());
            drawContext.drawTextWithShadow(client.textRenderer, text, 6, 6, 0xFFFFFF);
        });
    }
}
