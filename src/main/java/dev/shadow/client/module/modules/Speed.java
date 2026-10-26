package dev.shadow.client.module.modules;

import dev.shadow.client.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.Vec3d;

public class Speed extends Module {
    private double multiplier = 1.35;

    public Speed() { super("Speed", Category.MOVEMENT); }

    public double getMultiplier() { return multiplier; }
    public void setMultiplier(double value) { multiplier = Math.max(1.0, Math.min(3.0, value)); }

    @Override
    protected void onTick(MinecraftClient client) {
        if (client.player == null || client.world == null || !client.player.isOnGround()) return;
        boolean moving = client.options.forwardKey.isPressed()
                || client.options.backKey.isPressed()
                || client.options.leftKey.isPressed()
                || client.options.rightKey.isPressed();
        if (!moving) return;
        Vec3d velocity = client.player.getVelocity();
        Vec3d horizontal = new Vec3d(velocity.x, 0, velocity.z).multiply(multiplier);
        client.player.setVelocity(horizontal.x, velocity.y, horizontal.z);
    }
}
