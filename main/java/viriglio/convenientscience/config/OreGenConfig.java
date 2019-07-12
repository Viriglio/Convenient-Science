package viriglio.convenientscience.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class OreGenConfig
{
	public static ForgeConfigSpec.IntValue vanadium_chance;
	public static ForgeConfigSpec.IntValue zirconium_chance;
	
	public static ForgeConfigSpec.BooleanValue vanadium_gen;
	public static ForgeConfigSpec.BooleanValue zirconium_gen;
	
	public static void init(ForgeConfigSpec.Builder server, ForgeConfigSpec.Builder client)
	{
		server.comment("Oregen Config");
		
		vanadium_gen = server
				.comment("\nWhether vanadium ore should be generated (default: true)")
				.define("oregen.vanadium_gen", true);
		
		vanadium_chance = server
				.comment("\nMaximum number of vanadium ore veins in one chunk (default: 16, min: 1, max: 32))")
				.defineInRange("oregen.vanadium_chance", 16, 1, 1000);
		
		zirconium_gen = server
				.comment("\nWhether zirconium ore should be generated (default: true)")
				.define("oregen.zirconium_gen", true);
		
		zirconium_chance = server
				.comment("\nMaximum number of zirconium ore veins in one chunk (default: 16, min: 1, max: 32)")
				.defineInRange("oregen.zirconium_chance", 16, 1, 1000);
	}
}

