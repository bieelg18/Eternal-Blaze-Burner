package com.bieelg18.eternalblazeburner.registry;


import com.bieelg18.eternalblazeburner.EternalBlazeBurner;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.world.level.block.LiquidBlock;

public class ModBlocks{

    private static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, EternalBlazeBurner.MOD_ID);



    public static final RegistryObject<LiquidBlock> MOLTEN_BLAZE_CAKE =
            BLOCKS.register("molten_blaze_cake",
                    () -> new LiquidBlock(
                            ModFluids.MOLTEN_BLAZE_CAKE_SOURCE,
                            BlockBehaviour.Properties.of()
                                    .noLootTable()
                                    .replaceable()
                                    .noCollission()
                                    .strength(100.0F)
                    ));



    public static void register(IEventBus eventBus){BLOCKS.register(eventBus);}

}
