package dev.shadow.client.gui;

import dev.shadow.client.ShadowClient;
import dev.shadow.client.config.ConfigManager;
import dev.shadow.client.module.Module;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;
import java.util.List;

public class ClickGuiScreen extends Screen {
    private static final int PANEL_X = 28;
    private static final int PANEL_Y = 32;
    private static final int PANEL_W = 300;
    private static final int ROW_H = 22;
    private static final int HEADER_H = 32;
    private int scroll;

    public ClickGuiScreen() { super(Text.literal("Shadow Client")); }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context);
        int x = PANEL_X, y = PANEL_Y;
        context.fill(x, y, x + PANEL_W, y + 30, 0xFF171721);
        context.drawTextWithShadow(textRenderer, "SHADOW CLIENT", x + 12, y + 10, 0xFFB58CFF);
        context.fill(x, y + 30, x + PANEL_W, y + 31, 0xFFB58CFF);
        context.fill(x, y + 31, x + PANEL_W, y + 31 + 5 * ROW_H, 0xE5101018);

        List<Module> modules = ShadowClient.MODULES.getModules();
        int row = 0;
        for (Module module : modules) {
            int ry = y + HEADER_H + row * ROW_H - scroll;
            if (ry < y + HEADER_H || ry + ROW_H > y + HEADER_H + 5 * ROW_H) { row++; continue; }
            boolean hover = mouseX >= x && mouseX <= x + PANEL_W && mouseY >= ry && mouseY <= ry + ROW_H;
            context.fill(x + 1, ry, x + PANEL_W - 1, ry + ROW_H - 1,
                    hover ? 0xFF29283A : 0xFF1D1C28);
            int color = module.isEnabled() ? 0xFFB58CFF : 0xFFDEDEE8;
            context.drawTextWithShadow(textRenderer, module.getName(), x + 12, ry + 7, color);
            context.drawTextWithShadow(textRenderer, module.isEnabled() ? "ON" : "OFF",
                    x + PANEL_W - 36, ry + 7, module.isEnabled() ? 0xFF78E6A0 : 0xFF888895);
            row++;
        }
        context.drawTextWithShadow(textRenderer, "Click a module to toggle  |  Right Shift: close",
                x, y + HEADER_H + 5 * ROW_H + 10, 0xFFB9B9C8);
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0 && mouseX >= PANEL_X && mouseX <= PANEL_X + PANEL_W) {
            int relativeY = (int) mouseY - (PANEL_Y + HEADER_H) + scroll;
            if (relativeY >= 0) {
                int index = relativeY / ROW_H;
                List<Module> modules = ShadowClient.MODULES.getModules();
                if (index < modules.size()) { modules.get(index).toggle(); ConfigManager.save(); }
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
        int maxScroll = Math.max(0, ShadowClient.MODULES.getModules().size() * ROW_H - 5 * ROW_H);
        scroll = Math.max(0, Math.min(maxScroll, scroll - (int)(verticalAmount * ROW_H)));
        return true;
    }

    @Override
    public void close() {
        ConfigManager.save();
        super.close();
    }

    @Override
    public boolean shouldPause() { return false; }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == GLFW.GLFW_KEY_ESCAPE || keyCode == GLFW.GLFW_KEY_RIGHT_SHIFT) {
            close();
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }
}
