package viriglio.convenientscience.itemgroups;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import viriglio.convenientscience.lists.ItemList;

public class TerrestrialItemGroup extends ItemGroup
{
	public TerrestrialItemGroup() 
	{
		super("terrestrial");
	}

	@Override
	public ItemStack createIcon()
	{
		return new ItemStack(ItemList.vanadium_ingot);
	}
}

