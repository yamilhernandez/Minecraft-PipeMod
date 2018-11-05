package main.java.com.tuxedosteve.JP3.items;

import main.java.com.tuxedosteve.JP3.Main;
import main.java.com.tuxedosteve.JP3.init.ModItems;
import main.java.com.tuxedosteve.JP3.util.IHasModel;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item implements IHasModel {
	
	public ItemBase(String name) {
		
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CreativeTabs.MATERIALS);
		
		ModItems.ITEMS.add(this);
	}

	@Override
	public void registerModels() {
		
		Main.proxy.registerItemRenderer(this, 0, "inventory");
		
	}
	
	

}
