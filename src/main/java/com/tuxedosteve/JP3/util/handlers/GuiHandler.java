package com.tuxedosteve.JP3.util.handlers;

import com.tuxedosteve.JP3.blocks.container.ContainerTinyChestBlock;
import com.tuxedosteve.JP3.blocks.gui.GuiTinyChestBlock;
import com.tuxedosteve.JP3.blocks.tileentity.TileEntityTinyChestBlock;
import com.tuxedosteve.JP3.util.Reference;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		
		if(ID== Reference.GUI_TINYCHEST_BLOCK) return new ContainerTinyChestBlock(player.inventory, (TileEntityTinyChestBlock)world.getTileEntity(new BlockPos(x,y,z)), player);
	
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		
		if(ID== Reference.GUI_TINYCHEST_BLOCK) return new GuiTinyChestBlock(player.inventory, (TileEntityTinyChestBlock)world.getTileEntity(new BlockPos(x,y,z)), player);
		
		return null;
	}
	public static void registerGUIs() {
		
		
	}

}
