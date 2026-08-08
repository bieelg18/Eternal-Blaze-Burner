package com.bieelg18.eternalblazeburner;


import com.bieelg18.eternalblazeburner.registry.*;
import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.fml.ModLoadingContext;
import com.bieelg18.eternalblazeburner.config.EternalBlazeBurnerConfigScreen;
import net.minecraftforge.fml.config.ModConfig;
import com.bieelg18.eternalblazeburner.config.Config;



@Mod(EternalBlazeBurner.MOD_ID)
public class EternalBlazeBurner {

    public static final String MOD_ID = "eternalblazeburner";
    public static final Logger LOGGER = LogUtils.getLogger();

    public EternalBlazeBurner(){
        LOGGER.info("Eternal Blaze Burner carregado!");

        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();


        ModItems.register(eventBus);
        ModCreativeTabs.register(eventBus);
        ModBlocks.register(eventBus);
        ModFluids.register(eventBus);



        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        ModLoadingContext.get().registerExtensionPoint(
                ConfigScreenHandler.ConfigScreenFactory.class,
                () -> new ConfigScreenHandler.ConfigScreenFactory(
                        (minecraft, screen) -> new EternalBlazeBurnerConfigScreen(screen)
                )
        );




    }




}
