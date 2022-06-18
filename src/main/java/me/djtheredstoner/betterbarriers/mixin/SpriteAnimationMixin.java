package me.djtheredstoner.betterbarriers.mixin;

import me.djtheredstoner.betterbarriers.BetterBarriers;
import net.minecraft.client.texture.Sprite;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Sprite.Animation.class)
public abstract class SpriteAnimationMixin {

    @Final @Shadow(aliases = "field_28469") Sprite $outer;
    @Shadow protected abstract void upload(int frameIndex);

    private boolean bb$isRenderingBarrier = false;

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void betterbarriers$onTick(CallbackInfo ci) {
        if ($outer.getId().equals(BetterBarriers.BARRIER)) {
            if (bb$isRenderingBarrier != BetterBarriers.shouldRenderBarriers) {
                bb$isRenderingBarrier = BetterBarriers.shouldRenderBarriers;
                upload(bb$isRenderingBarrier ? 1 : 0);
            }
            ci.cancel();
        }
    }

}
