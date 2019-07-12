package viriglio.convenientscience.lists;

import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class WrenchBlockList
{
	private static Block[] blocklist = 
	{
			Blocks.PISTON,
			Blocks.STICKY_PISTON,
			BlockList.heavy_alloy_block
	};
	
	public static HashMap<Block, Block> blocks()
	{
		HashMap<Block, Block> blocks = new HashMap<Block, Block>();
		
		for(Block k : blocklist)
		{
			blocks.put(k, Blocks.AIR);
		}
		
		return blocks;
	}
}

