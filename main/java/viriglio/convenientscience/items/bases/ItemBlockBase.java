package viriglio.convenientscience.items.bases;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemBlockBase extends ItemBlock
{	
	private String desc;

	public ItemBlockBase(String desc, Block blockIn, Properties builder)
	{
		super(blockIn, builder);
		this.desc = desc;
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		ITextComponent text = new TextComponentString("");
		ITextComponent text_null = new TextComponentString("");
		
		text.applyTextStyles(TextFormatting.AQUA);
		text_null.applyTextStyles(TextFormatting.ITALIC, TextFormatting.DARK_AQUA);
		
		text.appendText(desc);
		text_null.appendText("Hold Shift");
		
		if(GuiScreen.isShiftKeyDown())
		{
			tooltip.add(text);
		}
		
		else
		{
			tooltip.add(text_null);
		}
	}

}
