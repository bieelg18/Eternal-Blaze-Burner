package com.bieelg18.eternalblazeburner.registry;

import com.bieelg18.eternalblazeburner.EternalBlazeBurner;
import com.bieelg18.eternalblazeburner.item.EternalBlazeCoreItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, EternalBlazeBurner.MOD_ID);

    public static final RegistryObject<Item> ETERNAL_BLAZE_CORE =
            ITEMS.register("eternal_blaze_core",
                    () -> new EternalBlazeCoreItem(new Item.Properties()));




    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

}
