package com.tuxedosteve.JP3.util.handlers;

import com.tuxedosteve.JP3.blocks.container.ContainerBankBlock;
import com.tuxedosteve.JP3.blocks.gui.GuiBankBlock;
import com.tuxedosteve.JP3.blocks.tileentity.TileEntityBankBlock;
import com.tuxedosteve.JP3.util.Reference;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		
		if(ID== Reference.GUI_BANK_BLOCK) return new ContainerBankBlock(player.inventory, (TileEntityBankBlock)world.getTileEntity(new BlockPos(x,y,z)), player);
	
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		
		if(ID== Reference.GUI_BANK_BLOCK) return new GuiBankBlock(player.inventory, (TileEntityBankBlock)world.getTileEntity(new BlockPos(x,y,z)), player);
		
		return null;
	}
	public static void registerGUIs() {
		
		
	}

}
