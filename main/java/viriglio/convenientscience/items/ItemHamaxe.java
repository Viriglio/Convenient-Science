package viriglio.convenientscience.items;

import java.util.Map;
import java.util.Set;

import com.google.common.collect.ImmutableMap.Builder;
import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import viriglio.convenientscience.items.bases.ItemToolBase;
import viriglio.convenientscience.lists.HamaxeBlockList;

public class ItemHamaxe extends ItemToolBase
{
	private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(HamaxeBlockList.blocks);
	private static final Map<Block, Block> BLOCK_STRIPPING_MAP = (new Builder<Block, Block>()).put(Blocks.OAK_WOOD, Blocks.STRIPPED_OAK_WOOD).put(Blocks.OAK_LOG, Blocks.STRIPPED_OAK_LOG).put(Blocks.DARK_OAK_WOOD, Blocks.STRIPPED_DARK_OAK_WOOD).put(Blocks.DARK_OAK_LOG, Blocks.STRIPPED_DARK_OAK_LOG).put(Blocks.ACACIA_WOOD, Blocks.STRIPPED_ACACIA_WOOD).put(Blocks.ACACIA_LOG, Blocks.STRIPPED_ACACIA_LOG).put(Blocks.BIRCH_WOOD, Blocks.STRIPPED_BIRCH_WOOD).put(Blocks.BIRCH_LOG, Blocks.STRIPPED_BIRCH_LOG).put(Blocks.JUNGLE_WOOD, Blocks.STRIPPED_JUNGLE_WOOD).put(Blocks.JUNGLE_LOG, Blocks.STRIPPED_JUNGLE_LOG).put(Blocks.SPRUCE_WOOD, Blocks.STRIPPED_SPRUCE_WOOD).put(Blocks.SPRUCE_LOG, Blocks.STRIPPED_SPRUCE_LOG).build();
	
	   public ItemHamaxe(String desc, IItemTier tier, int attackDamageIn, float attackSpeedIn, Item.Properties builder)
	   {
	      super(desc, (float)attackDamageIn, attackSpeedIn, tier, EFFECTIVE_ON, builder.addToolType(net.minecraftforge.common.ToolType.PICKAXE, tier.getHarvestLevel()).addToolType(net.minecraftforge.common.ToolType.AXE, tier.getHarvestLevel()).addToolType(net.minecraftforge.common.ToolType.SHOVEL, tier.getHarvestLevel()));
	   }

	   public boolean canHarvestBlock(IBlockState blockIn)
	   {
	      int i = this.getTier().getHarvestLevel();
	      
	      if (blockIn.getHarvestTool() == net.minecraftforge.common.ToolType.PICKAXE)
	      {
	         return i >= blockIn.getHarvestLevel();
	      }
	      
	      Material material = blockIn.getMaterial();
	      return material == Material.ROCK || material == Material.IRON || material == Material.ANVIL;
	   }

	   public float getDestroySpeed(ItemStack stack, IBlockState state)
	   {
	      Material material = state.getMaterial();
	      return material != Material.IRON && material != Material.ANVIL && material != Material.ROCK && material != Material.WOOD && material != Material.PLANTS && material != Material.VINE ? super.getDestroySpeed(stack, state) : this.efficiency;
	   }
	   
	   public EnumActionResult onItemUse(ItemUseContext context)
	   {
		   World world = context.getWorld();
		   BlockPos blockpos = context.getPos();
		   IBlockState iblockstate = world.getBlockState(blockpos);
		   Block block = BLOCK_STRIPPING_MAP.get(iblockstate.getBlock());
		   
		   if (block != null)
		   {
		      EntityPlayer entityplayer = context.getPlayer();
		      world.playSound(entityplayer, blockpos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0F, 1.0F);
		      if (!world.isRemote)
		      {
		         world.setBlockState(blockpos, block.getDefaultState().with(BlockRotatedPillar.AXIS, iblockstate.get(BlockRotatedPillar.AXIS)), 11);
		         if (entityplayer != null)
		         {
		             context.getItem().damageItem(1, entityplayer);
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
