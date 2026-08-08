package com.bieelg18.eternalblazeburner.event;

import com.bieelg18.eternalblazeburner.EternalBlazeBurner;
import com.bieelg18.eternalblazeburner.registry.ModItems;
import com.simibubi.create.content.logistics.depot.DepotBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EternalBlazeBurner.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SuperheatedCatalystEvents {

    private static final String HOT_UNTIL_TAG = "HotUntil";
    private static final long COOLDOWN_TICKS = 20 * 12;

    @SubscribeEvent
    public static void onRightClickDepot(PlayerInteractEvent.RightClickBlock event) {
        Level level = event.getLevel();
        if (level.isClientSide) return;

        BlockPos pos = event.getPos();
        if (!(level.getBlockEntity(pos) instanceof DepotBlockEntity depot)) return;

        ItemStack heldItem = depot.getHeldItem();
        if (heldItem.isEmpty() || !heldItem.is(ModItems.ETERNAL_SUPERHEATED_FLAME_CATALYST.get())) return;

        CompoundTag tag = heldItem.getOrCreateTag();
        long now = level.getGameTime();

        if (!tag.contains(HOT_UNTIL_TAG)) {
            tag.putLong(HOT_UNTIL_TAG, now + COOLDOWN_TICKS);
        }
        long hotUntil = tag.getLong(HOT_UNTIL_TAG);

        Player player = event.getEntity();

        if (now < hotUntil) {
            event.setCanceled(true);
            event.setCancellationResult(InteractionResult.FAIL);
            player.hurt(level.damageSources().hotFloor(), 2.0F);
            player.displayClientMessage(
                    Component.translatable("message.eternalblazeburner.superheated"), true);
        } else {
            player.displayClientMessage(
                    Component.translatable("message.eternalblazeburner.catalyst_stabilized"),
                    true
            );
            depot.setHeldItem(new ItemStack(ModItems.ETERNAL_FLAME_CATALYST.get()));
            level.playSound(
                    null,
                    pos,
                    SoundEvents.FIRE_EXTINGUISH,
                    SoundSource.BLOCKS,
                    1.0F,
                    0.8F
            );

        }
    }
}