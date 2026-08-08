package com.bieelg18.eternalblazeburner.fluid;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.common.SoundActions;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;

import java.util.function.Consumer;

public class MoltenBlazeCakeFluidType {

    public static final ResourceLocation STILL_TEXTURE =
            new ResourceLocation("eternalblazeburner", "block/molten_blaze_cake_still");
    public static final ResourceLocation FLOWING_TEXTURE =
            new ResourceLocation("eternalblazeburner", "block/molten_blaze_cake_flow");

    public static FluidType create() {
        return new FluidType(FluidType.Properties.create()
                .lightLevel(10)
                .density(2500)
                .viscosity(5000)
                .temperature(900)
                .canSwim(false)
                .canDrown(false)
                .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL_LAVA)
                .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY_LAVA)
        ) {
            @Override
            public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
                consumer.accept(new IClientFluidTypeExtensions() {
                    @Override
                    public ResourceLocation getStillTexture() {
                        return STILL_TEXTURE;
                    }

                    @Override
                    public ResourceLocation getFlowingTexture() {
                        return FLOWING_TEXTURE;
                    }

                    @Override
                    public int getTintColor() {
                        return 0xFFFFFFFF;
                    }
                });
            }

            @Override
            public boolean move(FluidState state,
                             LivingEntity entity,
                             Vec3 movementVector,
                             double gravity){
                super.move(state, entity, movementVector, gravity);

                if (!entity.fireImmune()){
                    entity.hurt(entity.damageSources().lava(), 6.0F);
                    entity.setSecondsOnFire(12);
                }
                return false;
            }

        };
    }
}