package com.bieelg18.eternalblazeburner.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class Config {

    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec.IntValue COAL_BLOCKS_REQUIRED = BUILDER
            .comment("Quantidade de Blocos de Carvão necessários para acender permanentemente um Eternal Blaze Burner.")
            .defineInRange("eternal_blaze_burner_coal_blocks", 20, 1, 256);

    public static final ForgeConfigSpec SPEC = BUILDER.build();
}