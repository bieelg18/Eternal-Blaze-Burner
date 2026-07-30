package com.bieelg18.eternalblazeburner.item;

import com.bieelg18.eternalblazeburner.util.IEternalBlazeBurner;
import com.simibubi.create.content.processing.burner.BlazeBurnerBlockEntity;
import net.minecraft.network.chat.Component;
import com.simibubi.create.content.processing.burner.BlazeBurnerBlock;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.nbt.CompoundTag;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.security.auth.callback.Callback;


public class EternalBlazeCoreItem extends Item {

    public EternalBlazeCoreItem(Properties properties){
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context){

        if (context.getLevel().isClientSide)
            return InteractionResult.SUCCESS;

        var blockEntity = context.getLevel().getBlockEntity(context.getClickedPos());

        if (blockEntity instanceof BlazeBurnerBlockEntity burner){
            Object obj = burner;
            IEternalBlazeBurner eternal = (IEternalBlazeBurner) obj;

            if (!eternal.isEternal()){
                eternal.setEternal(true);
                blockEntity.setChanged();

                if (!context.getPlayer().getAbilities().instabuild)
                    context.getItemInHand().shrink(1);
            }



            return InteractionResult.SUCCESS;




        }

        return super.useOn(context);
    }



}
