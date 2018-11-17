package com.tuxedosteve.JP3.util.handlers;

import com.tuxedosteve.JP3.blocks.tileentity.TileEntityBankBlock;
import com.tuxedosteve.JP3.util.Reference;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler {
	
	public static void registerTileEntities() {
		
		GameRegistry.registerTileEntity(TileEntityBankBlock.class, new ResourceLocation(Reference.MOD_ID+ ":bank_block"));
	}

}
