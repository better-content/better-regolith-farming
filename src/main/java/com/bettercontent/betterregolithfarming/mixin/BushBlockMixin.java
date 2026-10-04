package com.bettercontent.betterregolithfarming.mixin;

import com.bettercontent.betterregolithfarming.compat.SourceberryFarmlandCompat;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BushBlock.class)
public abstract class BushBlockMixin {
    @Inject(method = "mayPlaceOn", at = @At("RETURN"), cancellable = true)
    private void betterRegolithFarming$allowSourceberryOnFarmland(
            final BlockState substrate,
            final BlockGetter level,
            final BlockPos substratePos,
            final CallbackInfoReturnable<Boolean> result) {
        result.setReturnValue(SourceberryFarmlandCompat.mayPlaceOn(
                (Block) (Object) this, substrate, result.getReturnValue()));
    }
}
