package me.djtheredstoner.betterbarriers;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.block.Blocks;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.world.GameMode;

public class BetterBarriers {

    public static final Identifier BARRIER = new Identifier("betterbarriers", "barrier");
    public static boolean shouldRenderBarriers;

    public static void init() {
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.BARRIER, RenderLayer.getCutoutMipped());

        ClientTickEvents.START_CLIENT_TICK.register(client -> {
            shouldRenderBarriers = client.interactionManager != null && client.player != null &&
                client.interactionManager.getCurrentGameMode() == GameMode.CREATIVE &&
                client.player.getMainHandStack().getItem() == Items.BARRIER;
        });
    }

}
