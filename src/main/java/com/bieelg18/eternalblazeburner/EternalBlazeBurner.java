package com.bieelg18.eternalblazeburner;


import com.bieelg18.eternalblazeburner.registry.ModBlocks;
import com.bieelg18.eternalblazeburner.registry.ModItems;
import com.mojang.logging.LogUtils;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;

@Mod(EternalBlazeBurner.MOD_ID)
public class EternalBlazeBurner {

    public static final String MOD_ID = "eternalblazeburner";
    public static final Logger LOGGER = LogUtils.getLogger();

    public EternalBlazeBurner(){
        LOGGER.info("Eternal Blaze Burner carregado!");

        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();


        ModItems.register(eventBus);



    }


}
