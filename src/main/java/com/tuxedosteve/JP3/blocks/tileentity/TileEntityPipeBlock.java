package com.tuxedosteve.JP3.blocks.tileentity;

import com.tuxedosteve.JP3.blocks.container.ContainerPipeBlock;
import com.tuxedosteve.JP3.init.ModBlocks;
import com.tuxedosteve.JP3.util.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;

public class TileEntityPipeBlock extends TileEntityLockableLoot implements ITickable {


	private NonNullList<ItemStack> chestContents= NonNullList.<ItemStack>withSize(1,ItemStack.EMPTY);
	public int numPlayersUsing, ticksSinceSync, index=2;
	public EnumFacing pointer=EnumFacing.NORTH;


	@Override
	public int getSizeInventory() {
		return 1;
	}

	@Override
	public boolean isEmpty() {
		for(ItemStack stack: this.chestContents) {
			if(!stack.isEmpty()) return false;
		}

		return true;
	}

	@Override
	public int getInventoryStackLimit() { 
		return 64;
	}

	@Override
	public String getName() {

		return this.hasCustomName() ? this.customName: "container.pipe_block";

	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {

		super.readFromNBT(compound);
		this.chestContents= NonNullList.<ItemStack>withSize(this.getSizeInventory(),ItemStack.EMPTY);

		if(!this.checkLootAndRead(compound)) ItemStackHelper.loadAllItems(compound, chestContents);
		if(compound.hasKey("Container", 8)) this.customName= compound.getString("CustomName");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {

		super.writeToNBT(compound);

		if(!this.checkLootAndWrite(compound)) ItemStackHelper.saveAllItems(compound, chestContents);
		if(compound.hasKey("customName",8 )) compound.setString("CustomName", this.customName);
		return compound;


	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
		return new ContainerPipeBlock(playerInventory, this, playerIn);
	}

	@Override
	public String getGuiID() {
		return Reference.MOD_ID+ ":pipe_block";
	}

	@Override
	protected NonNullList<ItemStack> getItems() {
		return this.chestContents;
	}

	public void setMode() { 
		setModeHelper(index++);
	}
		
	//Recursively toggles between directions on player click
	private void setModeHelper(int index2) {
		if(index>=4) {
			index=0;
			this.setModeHelper(index);
		}
		else {
			pointer=EnumFacing.HORIZONTALS[index];
			Minecraft.getMinecraft().player.sendChatMessage(pointer.toString());
		}
		
	}

	public void transportItem() {
		BlockPos mainPos = this.getPos();
		
		//Gets block of opposing direction
		BlockPos backNeighbourPos = mainPos.offset(pointer.getOpposite()); 
		IBlockState backNeighbourState = world.getBlockState(backNeighbourPos); 
		Block backNeighbourBlock = backNeighbourState.getBlock(); 
		TileEntity bte = world.getTileEntity(backNeighbourPos);

		//If opposing direction block contains an item, pipe takes it
		if(bte instanceof IInventory && backNeighbourBlock != ModBlocks.PIPE_BLOCK ) {
			IInventory backChest = (IInventory)bte;
			if(!backChest.isEmpty()) {
				this.setInventorySlotContents(0, backChest.getStackInSlot(0));
				backChest.removeStackFromSlot(0);
			}
		}

		//Gets block in chosen direction
		BlockPos neighbourPos = mainPos.offset(pointer);
		TileEntity te = world.getTileEntity(neighbourPos);

		//If chosen direction block is a container, pipe delivers item
		if (te instanceof IInventory &&  !this.isEmpty()){ 
			IInventory chest = (IInventory)te;
			chest.setInventorySlotContents(0, this.getStackInSlot(0));
			this.removeStackFromSlot(0);
		}
	}

	@Override
	public void update() {
		this.transportItem();
	}


}


