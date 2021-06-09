package net.onpointcoding.redstone_sapling;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.UniformIntDistribution;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;
import net.onpointcoding.redstone_sapling.blocks.RedstoneSaplingBlock;
import net.onpointcoding.redstone_sapling.blocks.RedstoneSaplingFlowerPotBlock;
import net.onpointcoding.redstone_sapling.generator.RedstoneSaplingGenerator;

public class RedstoneSapling implements ModInitializer {
    public static final Block REDSTONE_SAPLING = new RedstoneSaplingBlock(new RedstoneSaplingGenerator(), AbstractBlock.Settings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS));
    public static final Block POTTED_REDSTONE_SAPLING = new RedstoneSaplingFlowerPotBlock(REDSTONE_SAPLING, AbstractBlock.Settings.of(Material.SUPPORTED).breakInstantly().nonOpaque());

    public static final ConfiguredFeature<TreeFeatureConfig, ?> REDSTONE_TREE = registerConfiguredFeature("redstone_tree", Feature.TREE.configure((new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.REDSTONE_BLOCK.getDefaultState()), new SimpleBlockStateProvider(Blocks.REDSTONE_LAMP.getDefaultState()), new BlobFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0), 3), new StraightTrunkPlacer(4, 2, 0), new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    @Override
    public void onInitialize() {
        Registry.register(Registry.BLOCK, new Identifier("redstone_sapling", "redstone_sapling"), REDSTONE_SAPLING);
        Registry.register(Registry.BLOCK, new Identifier("redstone_sapling", "potted_redstone_sapling"), POTTED_REDSTONE_SAPLING);

        Registry.register(Registry.ITEM, new Identifier("redstone_sapling", "redstone_sapling"), new BlockItem(REDSTONE_SAPLING, new FabricItemSettings().group(ItemGroup.REDSTONE)));

        BlockRenderLayerMap.INSTANCE.putBlock(REDSTONE_SAPLING, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(POTTED_REDSTONE_SAPLING, RenderLayer.getCutout());
    }

    private static <FC extends FeatureConfig> ConfiguredFeature<FC, ?> registerConfiguredFeature(String id, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, id, configuredFeature);
    }
}
