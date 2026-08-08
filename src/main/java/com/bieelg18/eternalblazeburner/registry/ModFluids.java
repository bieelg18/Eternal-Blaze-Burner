package com.bieelg18.eternalblazeburner.registry;

import com.bieelg18.eternalblazeburner.fluid.MoltenBlazeCakeFluidType;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFluids {

    private static final String MODID = "eternalblazeburner";

    public static final DeferredRegister<FluidType> FLUID_TYPES =
            DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, MODID);

    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(ForgeRegistries.Keys.FLUIDS, MODID);

    public static final RegistryObject<FluidType> MOLTEN_BLAZE_CAKE_TYPE =
            FLUID_TYPES.register("molten_blaze_cake", MoltenBlazeCakeFluidType::create);

    public static final RegistryObject<FlowingFluid> MOLTEN_BLAZE_CAKE_SOURCE =
            FLUIDS.register("molten_blaze_cake",
                    () -> new ForgeFlowingFluid.Source(getProperties()));

    public static final RegistryObject<FlowingFluid> MOLTEN_BLAZE_CAKE_FLOWING =
            FLUIDS.register("molten_blaze_cake_flowing",
                    () -> new ForgeFlowingFluid.Flowing(getProperties()));


    private static ForgeFlowingFluid.Properties getProperties() {
        return new ForgeFlowingFluid.Properties(
                MOLTEN_BLAZE_CAKE_TYPE,
                MOLTEN_BLAZE_CAKE_SOURCE,
                MOLTEN_BLAZE_CAKE_FLOWING
        ).bucket(ModItems.MOLTEN_BLAZE_CAKE_BUCKET)
                .block(ModBlocks.MOLTEN_BLAZE_CAKE);
    }

    public static void register(IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);
        FLUIDS.register(eventBus);
    }
}