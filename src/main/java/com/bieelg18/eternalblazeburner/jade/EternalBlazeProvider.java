package com.bieelg18.eternalblazeburner.jade;

import com.bieelg18.eternalblazeburner.config.Config;
import com.bieelg18.eternalblazeburner.util.IEternalBlazeBurner;
import com.simibubi.create.content.processing.burner.BlazeBurnerBlockEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

public enum EternalBlazeProvider implements IBlockComponentProvider {

    INSTANCE;

    @Override
    public void appendTooltip(
            ITooltip tooltip,
            BlockAccessor accessor,
            IPluginConfig config
    ) {

        if (!(accessor.getBlockEntity() instanceof BlazeBurnerBlockEntity burner))
            return;

        IEternalBlazeBurner eternal = (IEternalBlazeBurner) (Object) burner;

        if (!eternal.isEternal())
            return;

        // Título
        tooltip.add(
                Component.translatable("jade.eternalblazeburner.title")
                        .withStyle(ChatFormatting.GOLD)
        );

        // Linha em branco
        tooltip.add(Component.empty());

        if (eternal.isSuperheated()) {

            tooltip.add(
                    Component.literal("🔥 ")
                            .withStyle(ChatFormatting.RED)
                            .append(
                                    Component.translatable("jade.eternalblazeburner.permanently_superheated")
                                            .withStyle(ChatFormatting.RED)
                            )
            );

            return;
        }

        if (eternal.getCoalProgress() < Config.COAL_BLOCKS_REQUIRED.get()) {

            tooltip.add(
                    Component.translatable("jade.eternalblazeburner.fuel")
                            .withStyle(ChatFormatting.GRAY)
                            .append(
                                    Component.literal(
                                            eternal.getCoalProgress() + "/" + Config.COAL_BLOCKS_REQUIRED.get()
                                    ).withStyle(ChatFormatting.GOLD)
                            )
                            .append(
                                    Component.translatable("jade.eternalblazeburner.coal_blocks")
                                            .withStyle(ChatFormatting.DARK_GRAY)
                            )
            );

        } else {

            tooltip.add(
                    Component.literal("✓ ")
                            .withStyle(ChatFormatting.GREEN)
                            .append(
                                    Component.translatable("jade.eternalblazeburner.permanently_heated")
                                            .withStyle(ChatFormatting.GREEN)
                            )
            );

        }
    }

    @Override
    public ResourceLocation getUid() {
        return new ResourceLocation(
                "eternalblazeburner",
                "eternal_blaze_provider"
        );
    }
}