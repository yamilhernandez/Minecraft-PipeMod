package com.tuxedosteve.JP3.init;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModRecipes {
	
	public static void init() {
		
		GameRegistry.addSmelting(ModItems.CASH, new ItemStack(ModBlocks.MONEY_ORE,1), 3.0F);
		
	}

}
