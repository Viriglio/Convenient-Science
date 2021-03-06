package viriglio.convenientscience;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
//import net.minecraft.item.ItemSword;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import viriglio.convenientscience.config.Config;
import viriglio.convenientscience.itemgroups.TerrestrialItemGroup;
import viriglio.convenientscience.items.ItemHamaxe;
import viriglio.convenientscience.items.ItemThanosRuin;
import viriglio.convenientscience.items.ItemWrench;
import viriglio.convenientscience.items.bases.ItemBase;
import viriglio.convenientscience.items.bases.ItemBlockBase;
import viriglio.convenientscience.lists.BlockList;
import viriglio.convenientscience.lists.ItemList;
import viriglio.convenientscience.lists.ToolMaterialList;
import viriglio.convenientscience.world.OreGen;

@Mod("cscience")
public class ConvenientScience
{
	public static ConvenientScience instance;
	public static final String modid = "cscience";
	public static final Logger logger = LogManager.getLogger(modid);
	
	public static final ItemGroup terrestrial = new TerrestrialItemGroup();
	
	public ConvenientScience()
	{
		instance = this;
		
		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.client_config);
		ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.server_config);
		
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientRegistries);
		
		Config.loadConfig(Config.client_config, FMLPaths.CONFIGDIR.get().resolve("convenientscience-client.toml").toString());
		Config.loadConfig(Config.server_config, FMLPaths.CONFIGDIR.get().resolve("convenientscience-server.toml").toString());
		
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	private void setup(final FMLCommonSetupEvent event)
	{
		OreGen.setupOreGen();
		logger.info("Setup method registered.");
	}
	
	private void clientRegistries(final FMLClientSetupEvent event)
	{
		
		logger.info("Client Registries method registered.");
	}
	
	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents
	{
		@SubscribeEvent
		public static void RegisterItems(final RegistryEvent.Register<Item> event)
		{
			event.getRegistry().registerAll
			(
				ItemList.vanadium_ingot = new ItemBase("Particularly resistant to oxidation", new Item.Properties().group(terrestrial)).setRegistryName(location("vanadium_ingot")),
				ItemList.zirconium_ingot = new ItemBase("Particularly resistant to radiation", new Item.Properties().group(terrestrial)).setRegistryName(location("zirconium_ingot")),
				ItemList.pig_iron_ingot = new ItemBase("Particularly resistant to solicitation", new Item.Properties().group(terrestrial)).setRegistryName(location("pig_iron_ingot")),
				ItemList.heavy_alloy_ingot = new ItemBase("Particularly resistant to most agents", new Item.Properties().group(terrestrial)).setRegistryName(location("heavy_alloy_ingot")),
				
				ItemList.heavy_alloy_hamaxe = new ItemHamaxe("Totally not a reference to Terraria", ToolMaterialList.heavy_alloy, -2, 6.0f, new Item.Properties().group(terrestrial)).setRegistryName("heavy_alloy_hamaxe"),
				ItemList.heavy_alloy_wrench = new ItemWrench("Because every tech mod needs a wrench!", new Item.Properties().group(terrestrial).maxStackSize(1)).setRegistryName(location("heavy_alloy_wrench")),
				ItemList.thanos_ruin = new ItemThanosRuin("This is inevitable", ToolMaterialList.heavy_alloy, 50, 10, new Item.Properties().group(terrestrial)).setRegistryName(location("thanos_ruin")),
				
				ItemList.vanadium_block = new ItemBlockBase("Even more resistant to oxidation", BlockList.vanadium_block, new Item.Properties().group(terrestrial)).setRegistryName(BlockList.vanadium_block.getRegistryName()),
				ItemList.zirconium_block = new ItemBlockBase("Even more resistant to radiation", BlockList.zirconium_block, new Item.Properties().group(terrestrial)).setRegistryName(BlockList.zirconium_block.getRegistryName()),
				ItemList.pig_iron_block = new ItemBlockBase("Even more resistant to solicitation", BlockList.pig_iron_block, new Item.Properties().group(terrestrial)).setRegistryName(BlockList.pig_iron_block.getRegistryName()),
				ItemList.heavy_alloy_block = new ItemBlockBase("Even more resistant to most agents", BlockList.heavy_alloy_block, new Item.Properties().group(terrestrial)).setRegistryName(BlockList.heavy_alloy_block.getRegistryName()),
				ItemList.vanadium_ore = new ItemBlockBase("Mostly found in temperate biomes", BlockList.vanadium_ore, new Item.Properties().group(terrestrial)).setRegistryName(BlockList.vanadium_ore.getRegistryName()),
				ItemList.zirconium_ore = new ItemBlockBase("Mostly found in hot biomes", BlockList.zirconium_ore, new Item.Properties().group(terrestrial)).setRegistryName(BlockList.zirconium_ore.getRegistryName())
			);
			
			
			logger.info("Items registered.");
		}
		
		@SubscribeEvent
		public static void RegisterBlocks(final RegistryEvent.Register<Block> event)
		{
			event.getRegistry().registerAll
			(
				BlockList.vanadium_block = new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(4.0f, 5.0f).sound(SoundType.METAL)).setRegistryName(location("vanadium_block")),
				BlockList.zirconium_block = new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(4.5f, 4.0f).sound(SoundType.METAL)).setRegistryName(location("zirconium_block")),
				BlockList.pig_iron_block = new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(6.0f, 5.5f).sound(SoundType.METAL)).setRegistryName(location("pig_iron_block")),
				BlockList.heavy_alloy_block = new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(8.0f, 6.0f).sound(SoundType.METAL)).setRegistryName(location("heavy_alloy_block")),
				BlockList.vanadium_ore = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f, 3.5f).sound(SoundType.STONE)).setRegistryName(location("vanadium_ore")),
				BlockList.zirconium_ore = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f, 3.5f).sound(SoundType.STONE)).setRegistryName(location("zirconium_ore"))
			);
			
			
			logger.info("Blocks registered.");
		}
		
		private static ResourceLocation location(String name)
		{
			return new ResourceLocation(modid, name);
		}
	}
}

