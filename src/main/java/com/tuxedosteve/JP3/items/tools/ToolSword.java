package com.tuxedosteve.JP3.items.tools;

import java.util.List;

import javax.annotation.Nullable;

import com.tuxedosteve.JP3.Main;
import com.tuxedosteve.JP3.init.ModItems;
import com.tuxedosteve.JP3.util.IHasModel;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ToolSword extends ItemSword implements IHasModel{

	public ToolSword(String name, ToolMaterial material) {

		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.javaprojecttab);
		ModItems.ITEMS.add(this);
	}

	@Override
	public void registerModels() {

		Main.proxy.registerItemRenderer(this, 0, "inventory");

	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		tooltip.add("The sword that will cost your enemies their life.");
	}

}



