package com.bieelg18.eternalblazeburner.mixin;

import com.bieelg18.eternalblazeburner.util.IEternalBlazeBurner;
import com.simibubi.create.content.processing.burner.BlazeBurnerBlockItem;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public class BlockMixin {

    @Unique
    private static boolean wasEternal = false;

    @Unique
    private static int coalProgress = 0;

    @Inject(
            method = "playerDestroy",
            at = @At("HEAD")
    )
    private void eternal$playerDestroy(
            Level level,
            Player player,
            BlockPos pos,
            BlockState state,
            @Nullable BlockEntity blockEntity,
            ItemStack tool,
            CallbackInfo ci
    ) {

        wasEternal = false;
        coalProgress = 0;

        if (!(blockEntity instanceof IEternalBlazeBurner))
            return;

        IEternalBlazeBurner eternal = (IEternalBlazeBurner) blockEntity;

        if (!eternal.isEternal())
            return;

        wasEternal = true;
        coalProgress = eternal.getCoalProgress();
    }

    @Inject(
            method = "popResource(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/item/ItemStack;)V",
            at = @At("HEAD")
    )
    private static void eternal$popResource(
            Level level,
            BlockPos pos,
            ItemStack stack,
            CallbackInfo ci
    ) {

        if (!(stack.getItem() instanceof BlazeBurnerBlockItem))
            return;

        if (!wasEternal)
            return;

        CompoundTag blockEntityTag = new CompoundTag();

        blockEntityTag.putBoolean("EternalBlaze", true);
        blockEntityTag.putInt("CoalProgress", coalProgress);

        stack.addTagElement("BlockEntityTag", blockEntityTag);

        wasEternal = false;
        coalProgress = 0;
    }
}