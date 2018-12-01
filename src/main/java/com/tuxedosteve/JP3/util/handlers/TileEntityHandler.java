package com.tuxedosteve.JP3.util.handlers;

import com.tuxedosteve.JP3.blocks.tileentity.TileEntityPipeBlock;
import com.tuxedosteve.JP3.blocks.tileentity.TileEntityTinyChestBlock;
import com.tuxedosteve.JP3.util.Reference;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler {
	
	public static void registerTileEntities() {
		
		GameRegistry.registerTileEntity(TileEntityTinyChestBlock.class, new ResourceLocation(Reference.MOD_ID+ ":bank_block"));
		GameRegistry.registerTileEntity(TileEntityPipeBlock.class, new ResourceLocation(Reference.MOD_ID+ ":pipe_block"));
		
	}

}
