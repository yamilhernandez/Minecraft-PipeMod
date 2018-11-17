package com.tuxedosteve.JP3.init;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModRecipes {
	
	public static void init() {
		
		GameRegistry.addSmelting(ModBlocks.MONEY_ORE, new ItemStack(ModItems.CASH,1), 3.0F);
		GameRegistry.addSmelting(ModBlocks.BITCOIN_ORE, new ItemStack(ModItems.BITCOIN), 5.0F);
		
	}

}
