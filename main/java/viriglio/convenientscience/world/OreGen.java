package viriglio.convenientscience.world;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.CompositeFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.MinableConfig;
import net.minecraft.world.gen.placement.CountRange;
import net.minecraft.world.gen.placement.CountRangeConfig;
import viriglio.convenientscience.config.OreGenConfig;
import viriglio.convenientscience.lists.BlockList;
import viriglio.convenientscience.world.oregen.VanadiumOreGen;
import viriglio.convenientscience.world.oregen.ZirconiumOreGen;

public class OreGen
{
	public static void setupOreGen()
	{
		if(OreGenConfig.vanadium_gen.get())
		{
			for(Biome biome : VanadiumOreGen.biomes)
			{
				CountRangeConfig vanadium_ore_gen = new CountRangeConfig(OreGenConfig.vanadium_chance.get(), 0, 0, 80);
				biome.addFeature(Decoration.UNDERGROUND_ORES, new CompositeFeature<>(Feature.MINABLE, new MinableConfig(MinableConfig.IS_ROCK, BlockList.vanadium_ore.getDefaultState(), 6), new CountRange(), vanadium_ore_gen));
			}
		}

		if(OreGenConfig.zirconium_gen.get())
		{
			for(Biome biome : ZirconiumOreGen.biomes)
			{
				CountRangeConfig zirconium_ore_gen = new CountRangeConfig(OreGenConfig.zirconium_chance.get(), 0, 0, 80);
				biome.addFeature(Decoration.UNDERGROUND_ORES, new CompositeFeature<>(Feature.MINABLE, new MinableConfig(MinableConfig.IS_ROCK, BlockList.zirconium_ore.getDefaultState(), 6), new CountRange(), zirconium_ore_gen));
			}
		}
	}
}
