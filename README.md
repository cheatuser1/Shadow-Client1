# Shadow Client — Minecraft Java 1.20.1

Starter Fabric client mod with a custom ClickGUI, togglable modules, and a small config system.

## Included
- Right Shift opens the ClickGUI.
- Click modules to toggle them.
- Coordinates/FPS HUD.
- Fullbright.
- Speed multiplier (currently default 1.35).
- Experimental local Fly toggle.
- JSON configuration saved in the Minecraft config folder.

## Build
1. Install JDK 17.
2. Open this folder in IntelliJ IDEA as a Gradle project.
3. Let Gradle sync.
4. Run `gradlew build` (Windows: `gradlew.bat build`).
5. The mod jar is created in `build/libs/`.
6. Install Fabric Loader for Minecraft 1.20.1 and put the jar into `.minecraft/mods/`. Fabric API is also required.

## Notes
This is a starter project, not a finished all-feature cheat client. Fly may be overridden by multiplayer servers. Use mods only in worlds/servers where permitted. X-Ray, ESP, KillAura, Aim Assist, full per-module settings UI, and config serialization are not implemented yet in this starter.
