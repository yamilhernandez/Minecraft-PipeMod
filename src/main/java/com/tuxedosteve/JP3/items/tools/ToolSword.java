package com.tuxedosteve.JP3.items.tools;

import com.tuxedosteve.JP3.Main;
import com.tuxedosteve.JP3.init.ModItems;
import com.tuxedosteve.JP3.util.IHasModel;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemSword;

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

}



