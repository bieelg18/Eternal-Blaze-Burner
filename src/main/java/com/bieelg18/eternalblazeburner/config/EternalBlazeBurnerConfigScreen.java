package com.bieelg18.eternalblazeburner.config;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class EternalBlazeBurnerConfigScreen extends Screen {

    private final Screen parent;
    private EditBox coalBlocksBox;

    public EternalBlazeBurnerConfigScreen(Screen parent) {
        super(Component.translatable("config.eternalblazeburner.title"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        int centerX = this.width / 2;

        coalBlocksBox = new EditBox(this.font, centerX - 100, 60, 200, 20,
                Component.translatable("config.eternalblazeburner.coal_blocks"));

        if (Config.SPEC.isLoaded()) {
            coalBlocksBox.setValue(String.valueOf(Config.COAL_BLOCKS_REQUIRED.get()));
        } else {
            coalBlocksBox.setValue("20"); // valor padrão de fallback, mesmo do defineInRange
        }

        coalBlocksBox.setFilter(s -> s.matches("\\d*"));
        this.addRenderableWidget(coalBlocksBox);

        this.addRenderableWidget(Button.builder(Component.translatable("gui.done"),
                button -> saveAndClose()
        ).bounds(centerX - 100, 100, 200, 20).build());
    }

    private void saveAndClose() {
        try {
            int value = Integer.parseInt(coalBlocksBox.getValue());
            value = Math.max(1, Math.min(256, value));

            if (Config.SPEC.isLoaded()) {
                Config.COAL_BLOCKS_REQUIRED.set(value);
            }
        } catch (NumberFormatException ignored) {
        }
        this.onClose();
    }

    @Override
    public void onClose() {
        this.minecraft.setScreen(parent);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(graphics);
        super.render(graphics, mouseX, mouseY, partialTick);

        graphics.drawCenteredString(this.font, this.title, this.width / 2, 20, 0xFFFFFF);
        graphics.drawCenteredString(this.font,
                Component.translatable("config.eternalblazeburner.coal_blocks"),
                this.width / 2, 45, 0xAAAAAA);
    }
}