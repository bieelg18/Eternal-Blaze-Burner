package com.bieelg18.eternalblazeburner.item;

import com.bieelg18.eternalblazeburner.registry.ModFluids;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandler;

import java.util.function.Supplier;

public class MoltenBlazeCakeBucketItem extends BucketItem {

    public MoltenBlazeCakeBucketItem(Supplier<? extends Fluid> supplier, Properties properties) {
        super(supplier, properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {

        BlockHitResult hit = getPlayerPOVHitResult(level, player, ClipContext.Fluid.NONE);

        if (hit.getType() == HitResult.Type.BLOCK) {

            BlockPos pos = hit.getBlockPos();

            var handler = FluidUtil.getFluidHandler(level, pos, hit.getDirection());

            if (handler.isPresent()) {

                IFluidHandler fluidHandler = handler.orElseThrow(IllegalStateException::new);
                FluidStack fluid = new FluidStack(ModFluids.MOLTEN_BLAZE_CAKE_SOURCE.get(), 1000);

                if (fluidHandler.fill(fluid, IFluidHandler.FluidAction.SIMULATE) == 1000) {

                    fluidHandler.fill(fluid, IFluidHandler.FluidAction.EXECUTE);

                    if (!player.getAbilities().instabuild) {
                        player.setItemInHand(hand, new ItemStack(Items.BUCKET));
                    }

                    return InteractionResultHolder.success(player.getItemInHand(hand));
                }
            }
        }

        return super.use(level, player, hand);
    }
}