package me.djtheredstoner.betterbarriers.mixin;

import net.minecraft.block.BarrierBlock;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(BarrierBlock.class)
public class BarrierBlockMixin {

    /**
     * @author DJtheRedstoner
     * @reason Completely changes method
     */
    @Overwrite
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

}
