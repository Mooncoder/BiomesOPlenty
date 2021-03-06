package biomesoplenty.common.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemColored;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import biomesoplenty.api.content.BOPCBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBlockIvy extends ItemColored
{
	@SideOnly(Side.CLIENT)
	private IIcon texture;

	public ItemBlockIvy(Block block)
	{
		super(block, false);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister)
	{
		texture = iconRegister.registerIcon("biomesoplenty:ivy");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack itemStack, int par2)
	{
		return BOPCBlocks.ivy.getRenderColor(itemStack.getItemDamage());
	}

	@Override
	public IIcon getIconFromDamage(int meta)
	{
		return texture;
	}
}