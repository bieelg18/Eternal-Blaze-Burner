package com.bieelg18.eternalblazeburner.registry;

import com.bieelg18.eternalblazeburner.EternalBlazeBurner;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class ModCreativeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, EternalBlazeBurner.MOD_ID);

    public static final RegistryObject<CreativeModeTab> ETERNAL_BLAZE_TAB =
            CREATIVE_MODE_TABS.register("eternal_blaze_tab",
                    () -> CreativeModeTab.builder()
                            .title(Component.translatable("creativetab.eternalblazeburner"))
                            .icon(() -> new ItemStack(ModItems.ETERNAL_BLAZE_CORE.get()))
                            .displayItems((parameters, output) -> {
                                output.accept(ModItems.ETERNAL_BLAZE_CORE.get());
                            })
                            .build());

    public static void register(net.minecraftforge.eventbus.api.IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }

}
