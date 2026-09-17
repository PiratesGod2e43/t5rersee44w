package com.piratesgaming.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

public final class PiratesClient implements ClientModInitializer {
    public static KeyBinding menuKey;
    public static final ClientSettings SETTINGS = new ClientSettings();

    @Override
    public void onInitializeClient() {
        SETTINGS.load();
        menuKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.piratesclient.menu",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_L,
                KeyBinding.Category.create(Identifier.of("piratesclient", "general"))));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (menuKey.wasPressed()) client.setScreen(new PiratesScreen(client.currentScreen));
        });
    }
}
