# HM Scoreboard Toggle

[![Curseforge](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/available/curseforge_vector.svg)](https://www.curseforge.com/minecraft/mc-mods/hm-scoreboard-toggle)
[![GitHub](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/available/github_vector.svg)](https://github.com/henrymmey/hm-scoreboard-toggle)

A minimal, client-side Fabric mod for Minecraft Java Edition **26.2**. Press **K** to hide or show the vanilla scoreboard sidebar.

## Features

- Toggle the vanilla scoreboard sidebar with one key.
- Default key: **K**; rebind it in **Options → Controls → Key Binds → HM Scoreboard Toggle**.
- Client-side only. No server installation, commands, configuration files, chat messages, sounds, or extra network packets.
- The scoreboard is visible by default when Minecraft starts. The setting lasts for the current client session.

## Installation

1. Install Fabric Loader for Minecraft 26.2 and Fabric API **0.161.0+26.2**.
2. Download or build `hm-scoreboard-toggle-1.0.0.jar`.
3. Put the JAR in your Minecraft `mods` folder.
4. Start the Fabric 26.2 profile and press **K** in a world.

## Build from source

Install **Java 25**. Then run:

- **Windows:** `gradlew.bat build`
- **Linux/macOS:** `chmod +x ./gradlew && ./gradlew build`

The distributable JAR is written to `build/libs/hm-scoreboard-toggle-1.0.0.jar` (do not use the `-sources.jar` file).

## Implementation

The mod registers a configurable Fabric key mapping and wraps the vanilla `SCOREBOARD` HUD element with Fabric's `HudElementRegistry.replaceElement`. When hidden, only that element's original renderer is skipped; when visible, the original vanilla renderer is called unchanged. No mixins or custom scoreboard rendering are used.

## Compatibility

- Minecraft Java Edition: **26.2**
- Fabric Loader: **0.19.5 or newer**
- Fabric API: **0.161.0+26.2**
- Java: **25 or newer**

## License

MIT. See [LICENSE](LICENSE).
