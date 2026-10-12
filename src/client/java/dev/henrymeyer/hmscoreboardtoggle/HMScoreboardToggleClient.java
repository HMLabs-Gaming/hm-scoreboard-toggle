package dev.henrymeyer.hmscoreboardtoggle;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;

/**
 * Registers a client-only keybind that hides or restores the vanilla scoreboard
 * sidebar.
 */
public final class HMScoreboardToggleClient implements ClientModInitializer {
    private static final String MOD_ID = "hm-scoreboard-toggle";

    private static final KeyMapping.Category CATEGORY = KeyMapping.Category.register(
            Identifier.fromNamespaceAndPath(MOD_ID, "main"));

    private static final KeyMapping TOGGLE_SCOREBOARD = KeyMappingHelper.registerKeyMapping(
            new KeyMapping(
                    "key.hm-scoreboard-toggle.toggle_scoreboard",
                    InputConstants.Type.KEYSYM,
                    InputConstants.KEY_K,
                    CATEGORY));

    // True by default; the value lasts only for this Minecraft client session.
    private static boolean scoreboardVisible = true;

    @Override
    public void onInitializeClient() {
        // Preserve the original vanilla renderer and skip it only while hidden.
        HudElementRegistry.replaceElement(
                VanillaHudElements.SCOREBOARD,
                vanillaScoreboard -> (graphics, deltaTracker) -> {
                    if (scoreboardVisible) {
                        vanillaScoreboard.extractRenderState(graphics, deltaTracker);
                    }
                });

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (TOGGLE_SCOREBOARD.consumeClick()) {
                // Do not toggle while typing in chat or interacting with a menu.
                if (client.player != null && client.gui.screen() == null) {
                    scoreboardVisible = !scoreboardVisible;
                }
            }
        });
    }
}
