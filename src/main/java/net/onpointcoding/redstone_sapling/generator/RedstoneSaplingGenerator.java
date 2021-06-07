package net.onpointcoding.redstone_sapling.generator;

import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.onpointcoding.redstone_sapling.RedstoneSapling;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class RedstoneSaplingGenerator extends SaplingGenerator {
    @Nullable
    @Override
    protected ConfiguredFeature<TreeFeatureConfig, ?> createTreeFeature(Random random, boolean bl) {
        return RedstoneSapling.REDSTONE_TREE;
    }
}
