package com.bieelg18.eternalblazeburner.item;

import com.bieelg18.eternalblazeburner.util.IEternalBlazeBurner;
import com.simibubi.create.content.processing.burner.BlazeBurnerBlock;
import com.simibubi.create.content.processing.burner.BlazeBurnerBlockEntity;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;

public class EternalFlameCatalystItem extends Item {

    public EternalFlameCatalystItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {

        if (context.getLevel().isClientSide)
            return InteractionResult.SUCCESS;

        var blockEntity = context.getLevel().getBlockEntity(context.getClickedPos());

        if (blockEntity instanceof BlazeBurnerBlockEntity burner) {
            Object obj = burner;
            IEternalBlazeBurner eternal = (IEternalBlazeBurner) obj;

            if (!eternal.isSuperheated()) {
                eternal.setEternal(true);
                eternal.setSuperheated(true);
                blockEntity.setChanged();


                context.getLevel().setBlockAndUpdate(
                        context.getClickedPos(),
                        context.getLevel().getBlockState(context.getClickedPos())
                                .setValue(BlazeBurnerBlock.HEAT_LEVEL, BlazeBurnerBlock.HeatLevel.SEETHING)
                );

                if (!context.getPlayer().getAbilities().instabuild)
                    context.getItemInHand().shrink(1);
            }

            return InteractionResult.SUCCESS;
        }

        return super.useOn(context);
    }
}
