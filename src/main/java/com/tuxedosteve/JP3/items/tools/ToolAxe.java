package com.tuxedosteve.JP3.items.tools;

import java.util.List;

import javax.annotation.Nullable;

import com.tuxedosteve.JP3.Main;
import com.tuxedosteve.JP3.init.ModItems;
import com.tuxedosteve.JP3.util.IHasModel;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ToolAxe extends ItemAxe implements IHasModel{
	
	public ToolAxe(String name, ToolMaterial material) {

		super(material, 6.0F, -3.0F);
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
		tooltip.add("Money doesn't grow on trees, so go chopping");
	}

}
