package me.djtheredstoner.betterbarriers.mixin;

import me.djtheredstoner.betterbarriers.BetterBarriers;
import net.minecraft.client.texture.Animator;
import net.minecraft.client.texture.SpriteContents;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "net.minecraft.client.texture.SpriteContents$Animation")
public abstract class SpriteContentsAnimationMixin {

    @Shadow(aliases = "field_28469") @Final SpriteContents $outer;

    @Shadow abstract void upload(int x, int y, int frame);

    @Inject(method = "createAnimator", at = @At("HEAD"), cancellable = true)
    private void betterbarrier$customAnimator(CallbackInfoReturnable<Animator> cir) {
        if ($outer.getId().equals(BetterBarriers.BARRIER)) {
            cir.setReturnValue(new Animator() {
                private boolean isRenderingBarriers = false;

                @Override
                public void tick(int x, int y) {
                    if (isRenderingBarriers != BetterBarriers.shouldRenderBarriers) {
                        isRenderingBarriers = BetterBarriers.shouldRenderBarriers;
                        upload(x, y, isRenderingBarriers ? 1 : 0);
                    }
                }

                @Override
                public void close() {}
            });
        }
    }

}
