package dev.shadow.client.module.modules;

import dev.shadow.client.module.Module;
import net.minecraft.client.MinecraftClient;

public class Fly extends Module {
    private boolean oldAllowFlying;
    private boolean oldFlying;

    public Fly() { super("Fly (local)", Category.MOVEMENT); }

    @Override
    protected void onEnable() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;
        oldAllowFlying = client.player.getAbilities().allowFlying;
        oldFlying = client.player.getAbilities().flying;
        // Client-side ability changes may be overwritten by a server.
        client.player.getAbilities().allowFlying = true;
        client.player.getAbilities().flying = true;
        client.player.sendAbilitiesUpdate();
    }

    @Override
    protected void onDisable() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;
        client.player.getAbilities().allowFlying = oldAllowFlying;
        client.player.getAbilities().flying = oldFlying;
        client.player.sendAbilitiesUpdate();
    }

    @Override
    protected void onTick(MinecraftClient client) {
        if (client.player == null || !client.player.getAbilities().allowFlying) return;
        client.player.getAbilities().flying = true;
    }
}
