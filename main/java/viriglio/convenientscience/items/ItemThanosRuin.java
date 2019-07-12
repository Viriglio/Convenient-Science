package viriglio.convenientscience.items;

import java.util.List;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemThanosRuin extends ItemSword
{
	private String desc;

	public ItemThanosRuin(String desc, IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builder)
	{
		super(tier, attackDamageIn, attackSpeedIn, builder);
		
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
