package com.tuxedosteve.JP3.blocks.tileentity;

import com.tuxedosteve.JP3.blocks.container.ContainerPipeBlock;
import com.tuxedosteve.JP3.init.ModBlocks;
import com.tuxedosteve.JP3.util.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
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
	public int numPlayersUsing, ticksSinceSync;
	public EnumFacing pointer=EnumFacing.SOUTH;
	public EnumFacing inversePointer=EnumFacing.NORTH;




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
		// TODO Auto-generated method stub
		return new ContainerPipeBlock(playerInventory, this, playerIn);
	}

	@Override
	public String getGuiID() {
		// TODO Auto-generated method stub
		return Reference.MOD_ID+ ":pipe_block";
	}

	@Override
	protected NonNullList<ItemStack> getItems() {
		// TODO Auto-generated method stub
		return this.chestContents;
	}

	public void setMode() { 
		if(pointer==EnumFacing.SOUTH) {
			pointer=EnumFacing.WEST;
			inversePointer=EnumFacing.EAST;
			Minecraft.getMinecraft().player.sendChatMessage("WEST");
		}else if(pointer==EnumFacing.WEST) {
			pointer=EnumFacing.NORTH;
			inversePointer=EnumFacing.SOUTH;
			Minecraft.getMinecraft().player.sendChatMessage("NORTH");
		}else if(pointer==EnumFacing.NORTH) {
			pointer=EnumFacing.EAST;
			inversePointer=EnumFacing.WEST;
			Minecraft.getMinecraft().player.sendChatMessage("EAST");
		}else if(pointer==EnumFacing.EAST) {
			pointer=EnumFacing.SOUTH;
			inversePointer=EnumFacing.NORTH;
			Minecraft.getMinecraft().player.sendChatMessage("SOUTH");
		}
	}
	
	public void transporItem() {
		BlockPos mainPos = this.getPos();

		BlockPos neighbourPos = mainPos.offset(pointer); 
		IBlockState neighbourState = world.getBlockState(neighbourPos); 
		Block neighbourBlock = neighbourState.getBlock(); 
		TileEntity te = world.getTileEntity(neighbourPos);
		TileEntityLockableLoot chest = (TileEntityLockableLoot)te;
		
		BlockPos backNeighbourPos = mainPos.offset(inversePointer); 
		IBlockState backNeighbourState = world.getBlockState(backNeighbourPos); 
		Block backNeighbourBlock = backNeighbourState.getBlock(); 
		TileEntity bte = world.getTileEntity(backNeighbourPos);
		TileEntityLockableLoot backChest = (TileEntityLockableLoot)bte;
		
		//Chequea el entity de atras y si es un chest coge su item
		if(bte instanceof TileEntityLockableLoot && backNeighbourBlock != ModBlocks.PIPE_BLOCK && !backChest.isEmpty()) {
			this.setInventorySlotContents(0, backChest.getStackInSlot(0));
			backChest.removeStackFromSlot(0);
		}
		
		//Chequea si el de alfrente es un chest y le entrega el item
		if (te instanceof TileEntityLockableLoot &&  !this.isEmpty()){ 
			chest.setInventorySlotContents(0, this.getStackInSlot(0));
			this.removeStackFromSlot(0);
		}	
		
		//Entrega el item al pipe de alfrente
//		else if(neighbourBlock == ModBlocks.PIPE_BLOCK && !this.isEmpty()) {
//			chest.setInventorySlotContents(0, this.getStackInSlot(0));
//			this.removeStackFromSlot(0);		
//		}
	}


	@Override
	public void update() {
		this.transporItem();
	}

}
