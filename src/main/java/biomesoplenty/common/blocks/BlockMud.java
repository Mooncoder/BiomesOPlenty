package biomesoplenty.common.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import biomesoplenty.api.content.BOPCItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockMud extends BOPBlock
{
	private static final String[] mud = new String[] { "mud", "quicksand" };
	private IIcon[] textures;
	
	public BlockMud()
	{
		super(Material.sand);

		this.setHardness(0.6F);
		this.setHarvestLevel("shovel", 0);

		this.setStepSound(Block.soundTypeSand);
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		textures = new IIcon[mud.length];

		for (int i = 0; i < mud.length; ++i) 
		{
			textures[i] = iconRegister.registerIcon("biomesoplenty:" + mud[i]);
		}
	}
	
	@Override
	public IIcon getIcon(int side, int meta)
	{
		if (meta < 0 || meta >= textures.length) 
		{
			meta = 0;
		}

		return textures[meta];
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list) 
	{
		for (int i = 0; i < mud.length; ++i)
		{
			list.add(new ItemStack(block, 1, i));
		}
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		if (world.getBlockMetadata(x, y, z) == 0)
		{
			float var5 = 0.35F;
			return AxisAlignedBB.getBoundingBox(x, y, z, x + 1, y + 1 - var5, z + 1);
		}
		else
			return null;
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		if (world.getBlockMetadata(x, y, z) == 0)
		{
			if (entity instanceof EntityPlayer)
			{
				InventoryPlayer inventory = ((EntityPlayer)entity).inventory;

				if (inventory.armorInventory[0] != null && inventory.armorInventory[0].getItem() == BOPCItems.wadingBoots)
				{
					return;
				}
			}

			entity.motionX *= 0.1D;
			entity.motionZ *= 0.1D;
		}
		else
		{
			entity.setInWeb();
		}
	}

	@Override
	public Item getItemDropped(int metadata, Random random, int fortune)
	{
		if (metadata == 0) return BOPCItems.mudball;
		else return super.getItemDropped(metadata, random, fortune);
	}

	@Override
	public int quantityDropped(int meta, int fortune, Random random)
	{
		if (meta == 0)
			return 4;
		else
			return 1;
	}
}