package dev.shadow.client.module;

import dev.shadow.client.module.modules.CoordinatesHud;
import dev.shadow.client.module.modules.Fullbright;
import dev.shadow.client.module.modules.Speed;
import dev.shadow.client.module.modules.Fly;
import net.minecraft.client.MinecraftClient;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ModuleManager {
    private final List<Module> modules = new ArrayList<>();

    public ModuleManager() {
        modules.add(new CoordinatesHud());
        modules.add(new Fullbright());
        modules.add(new Speed());
        modules.add(new Fly());
    }

    public List<Module> getModules() { return Collections.unmodifiableList(modules); }
    public Module get(String name) {
        return modules.stream().filter(m -> m.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }
    public void tick(MinecraftClient client) { for (Module module : modules) module.tick(client); }
}
