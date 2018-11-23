package com.tuxedosteve.JP3.tabs;

import com.tuxedosteve.JP3.init.ModItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class JP3Tab extends CreativeTabs {
	
	public JP3Tab(String label) {
		
		super("javaprojecttab");
		this.setBackgroundImageName("javaprojecttab.png");
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(ModItems.MONEY_SWORD);
	}

}
