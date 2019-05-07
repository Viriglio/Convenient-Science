package viriglio.convenientscience.items;

import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import viriglio.convenientscience.items.bases.ItemBase;
import viriglio.convenientscience.lists.WrenchBlockList;

public class ItemWrench extends ItemBase
{
	private HashMap<Block, Block> blocks = WrenchBlockList.blocks();

	public ItemWrench(String desc, Properties properties)
	{
		super(desc, properties);
	}
	
	@Override
	public EnumActionResult onItemUse(ItemUseContext context)
	{
		World world = context.getWorld();
		BlockPos pos = context.getPos();
		IBlockState state = world.getBlockState(pos);
		Block block = blocks.get(state.getBlock());
		
		if(block != null)
		{
			EntityPlayer player = context.getPlayer();
			
			world.playSound(player, pos, SoundEvents.BLOCK_ANVIL_FALL, SoundCategory.BLOCKS, 1.0f, 1.0f);
			{
				if(!world.isRemote)
				{
					state.dropBlockAsItem(world, pos, 1);
					world.setBlockState(pos, Blocks.AIR.getDefaultState());
				}
			}
			
			return EnumActionResult.SUCCESS;
		}
		
		else
		{
			return EnumActionResult.PASS;
		}
	}
}
