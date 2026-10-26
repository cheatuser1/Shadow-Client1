package dev.shadow.client.module;

import net.minecraft.client.MinecraftClient;

public abstract class Module {
    private final String name;
    private final Category category;
    private boolean enabled;

    public Module(String name, Category category) {
        this.name = name;
        this.category = category;
    }

    public String getName() { return name; }
    public Category getCategory() { return category; }
    public boolean isEnabled() { return enabled; }

    public void setEnabled(boolean enabled) {
        if (this.enabled == enabled) return;
        this.enabled = enabled;
        if (enabled) onEnable(); else onDisable();
    }

    public void toggle() { setEnabled(!enabled); }
    public void tick(MinecraftClient client) { if (enabled) onTick(client); }
    protected void onEnable() {}
    protected void onDisable() {}
    protected void onTick(MinecraftClient client) {}

    public enum Category { COMBAT, RENDER, MOVEMENT, PLAYER, MISC }
}
