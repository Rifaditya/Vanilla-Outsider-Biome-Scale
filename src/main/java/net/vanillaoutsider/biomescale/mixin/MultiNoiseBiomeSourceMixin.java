// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.vanillaoutsider.biomescale.mixin;

// Verified against: MultiNoiseBiomeSource.java (26.2+)

import net.minecraft.world.level.biome.MultiNoiseBiomeSource;
import net.vanillaoutsider.biomescale.BiomeScaleRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(MultiNoiseBiomeSource.class)
public class MultiNoiseBiomeSourceMixin {

    @ModifyVariable(method = "getNoiseBiome(IIILnet/minecraft/world/level/biome/Climate$Sampler;)Lnet/minecraft/core/Holder;", at = @At("HEAD"), argsOnly = true, ordinal = 0)
    private int biomescale$modifyX(int x) {
        double scale = BiomeScaleRules.cachedScaleModifier;
        if (scale <= 0.01) return x;
        return (int) (x / scale);
    }

    @ModifyVariable(method = "getNoiseBiome(IIILnet/minecraft/world/level/biome/Climate$Sampler;)Lnet/minecraft/core/Holder;", at = @At("HEAD"), argsOnly = true, ordinal = 2)
    private int biomescale$modifyZ(int z) {
        double scale = BiomeScaleRules.cachedScaleModifier;
        if (scale <= 0.01) return z;
        return (int) (z / scale);
    }
}
