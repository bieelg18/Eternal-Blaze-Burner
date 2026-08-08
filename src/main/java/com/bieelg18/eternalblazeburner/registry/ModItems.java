package com.bieelg18.eternalblazeburner.registry;

import com.bieelg18.eternalblazeburner.EternalBlazeBurner;
import com.bieelg18.eternalblazeburner.item.EternalBlazeCoreItem;
import com.bieelg18.eternalblazeburner.item.EternalFlameCatalystItem;
import com.bieelg18.eternalblazeburner.item.MoltenBlazeCakeBucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
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

    public static final RegistryObject<Item> SCORCHED_BRASS_SHEET =
            ITEMS.register("scorched_brass_sheet",
                    () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ETERNAL_FLAME_CATALYST =
            ITEMS.register("eternal_flame_catalyst",
                    () -> new EternalFlameCatalystItem(new Item.Properties()));

    public static final RegistryObject<Item> SCORCHED_PRECISION_MECHANISM =
            ITEMS.register("scorched_precision_mechanism",
                    () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MOLTEN_BLAZE_CAKE_BUCKET = ITEMS.register("molten_blaze_cake_bucket",
            () -> new MoltenBlazeCakeBucketItem(ModFluids.MOLTEN_BLAZE_CAKE_SOURCE,
                    new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));

    public static final RegistryObject<Item> ETERNAL_SUPERHEATED_FLAME_CATALYST =
            ITEMS.register("eternal_superheated_flame_catalyst",
                    () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INCOMPLETE_ETERNAL_FLAME_CATALYST =
            ITEMS.register("incomplete_eternal_flame_catalyst",
                    () -> new Item(new Item.Properties()));


    public static final RegistryObject<Item> COMPACTED_BLAZE_CAKE =
            ITEMS.register("compacted_blaze_cake",
                    () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INCOMPLETE_BLAZE_CORE =
            ITEMS.register("incomplete_blaze_core",
                    () -> new Item(new Item.Properties()));






    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

}
