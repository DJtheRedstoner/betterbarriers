package me.djtheredstoner.betterbarriers.mixin;

import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Set;

@Mixin(ClientWorld.class)
public class WorldClientMixin {

    @Redirect(method = "getBlockParticle", at = @At(value = "INVOKE", target = "Ljava/util/Set;contains(Ljava/lang/Object;)Z"))
    public boolean betterbarriers$stopBarrierParticles(Set<Item> instance, Object item) {
        if (item == Items.BARRIER) return false;
        return instance.contains((Item) item);
    }

}
