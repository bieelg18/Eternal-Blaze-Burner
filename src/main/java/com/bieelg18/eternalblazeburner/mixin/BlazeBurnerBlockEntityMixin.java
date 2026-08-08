package com.bieelg18.eternalblazeburner.mixin;

import com.bieelg18.eternalblazeburner.util.IEternalBlazeBurner;
import com.simibubi.create.content.processing.burner.BlazeBurnerBlockEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import com.simibubi.create.content.processing.burner.BlazeBurnerBlock;
import org.spongepowered.asm.mixin.Shadow;
import com.bieelg18.eternalblazeburner.config.Config;

@Mixin(value = BlazeBurnerBlockEntity.class, remap = false)
public class BlazeBurnerBlockEntityMixin implements IEternalBlazeBurner {

    @Shadow
    protected BlazeBurnerBlockEntity.FuelType activeFuel;
    @Unique
    private boolean eternal = false;

    @Unique
    private int coalProgress = 0;

    @Override
    public boolean isEternal(){
        return eternal;
    }

    @Override
    public void setEternal(boolean eternal){
        this.eternal = eternal;
    }

    @Override
    public int getCoalProgress(){
        return coalProgress;
    }

    @Override
    public void setCoalProgress(int progress){
        this.coalProgress = progress;
    }

    @Inject(method = "write", at = @At("TAIL"))
    private void eternal$write(CompoundTag tag, boolean clientPacket, CallbackInfo ci){
        tag.putBoolean("EternalBlaze", this.eternal);
        tag.putInt("CoalProgress", this.coalProgress);
        tag.putBoolean("EternalBlazeSuperheated", this.superheated);
    }

    @Inject(method = "read", at = @At("TAIL"))
    private void eternal$read(CompoundTag tag, boolean clientPacket, CallbackInfo ci){
        this.eternal = tag.getBoolean("EternalBlaze");
        this.coalProgress = tag.getInt("CoalProgress");
        this.superheated = tag.getBoolean("EternalBlazeSuperheated");
    }

    @Inject(
            method = "tryUpdateFuel",
            at = @At("HEAD"),
            cancellable = true
    )
    private void eternal$tryUpdateFuel(
            ItemStack stack,
            boolean forceOverFlow,
            boolean simulate,
            CallbackInfoReturnable<Boolean> cir
    ){
        Object obj = this;
        IEternalBlazeBurner eternal =
                (IEternalBlazeBurner) obj;

        if (!eternal.isEternal())
            return;

        if (!stack.is(Items.COAL_BLOCK))
            return;

        if (eternal.getCoalProgress() >= Config.COAL_BLOCKS_REQUIRED.get()){
            cir.setReturnValue(false);
            return;
        }

        if (!simulate){
            if (eternal.getCoalProgress() < Config.COAL_BLOCKS_REQUIRED.get()){
                eternal.setCoalProgress(eternal.getCoalProgress() + 1);
                BlockEntity be = (BlockEntity) (Object) this;
                be.setChanged();

                if (be.getLevel() != null && !be.getLevel().isClientSide){
                    be.getLevel().sendBlockUpdated(
                            be.getBlockPos(),
                            be.getBlockState(),
                            be.getBlockState(),
                            Block.UPDATE_ALL
                    );
                }
            }
        }
    cir.setReturnValue(true);

    }


    @Inject(
            method = "setBlockHeat",
            at = @At("HEAD"),
            cancellable = true
    )
    private void eternal$setBlockHeat(
            BlazeBurnerBlock.HeatLevel heat,
            CallbackInfo ci
    ) {



        Object obj = this;
        IEternalBlazeBurner eternal = (IEternalBlazeBurner) obj;

        if (eternal.isSuperheated()) {

            if (heat != BlazeBurnerBlock.HeatLevel.SEETHING) {
                BlockEntity blockEntity = (BlockEntity) (Object) this;
                blockEntity.getLevel().setBlockAndUpdate(
                        blockEntity.getBlockPos(),
                        blockEntity.getBlockState().setValue(
                                BlazeBurnerBlock.HEAT_LEVEL,
                                BlazeBurnerBlock.HeatLevel.SEETHING
                        )
                );
            }
            ci.cancel();
            return;
        }

        if (!eternal.isEternal())
            return;

        if (eternal.getCoalProgress() < Config.COAL_BLOCKS_REQUIRED.get())
            return;

        if (heat == BlazeBurnerBlock.HeatLevel.SMOULDERING
                || heat == BlazeBurnerBlock.HeatLevel.FADING) {

            BlockEntity blockEntity = (BlockEntity) (Object) this;

            blockEntity.getLevel().setBlockAndUpdate(
                    blockEntity.getBlockPos(),
                    blockEntity.getBlockState().setValue(
                            BlazeBurnerBlock.HEAT_LEVEL,
                            BlazeBurnerBlock.HeatLevel.KINDLED
                    )
            );

            ci.cancel();
        }
    }

    @Unique
    private boolean superheated = false;

    @Override
    public boolean isSuperheated() {
        return superheated;
    }

    @Override
    public void setSuperheated(boolean superheated) {
        this.superheated = superheated;
    }


}
