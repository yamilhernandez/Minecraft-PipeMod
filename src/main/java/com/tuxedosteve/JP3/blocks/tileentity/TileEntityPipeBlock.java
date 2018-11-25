package com.tuxedosteve.JP3.blocks.tileentity;

import com.tuxedosteve.JP3.blocks.container.ContainerTinyChestBlock;
import com.tuxedosteve.JP3.blocks.container.ContainerPipeBlock;
import com.tuxedosteve.JP3.init.ModBlocks;
import com.tuxedosteve.JP3.util.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;

public class TileEntityPipeBlock extends TileEntityLockableLoot implements ITickable {


	private NonNullList<ItemStack> chestContents= NonNullList.<ItemStack>withSize(1,ItemStack.EMPTY);
	private boolean push, pull, transport=true;
	public int numPlayersUsing, ticksSinceSync;
	//public float lidAngle, prevLidAngle;



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
		if(isTransport()) {
			setTransport(false);
			setPush(true);
			Minecraft.getMinecraft().player.sendChatMessage("Push");
		}
		else if(isPush()) {
			setPush(false);
			setPull(true);
			Minecraft.getMinecraft().player.sendChatMessage("Pull");
		}else if(isPull()) {
			setPull(false);
			setTransport(true);
			Minecraft.getMinecraft().player.sendChatMessage("Transport");
		}
	}

	public boolean isPush() {
		return push;
	}

	public boolean isPull() {
		return pull;
	}

	public boolean isTransport() {
		return transport;
	}

	public void setPush(boolean push) {
		this.push = push;
	}

	public void setPull(boolean pull) {
		this.pull = pull;
	}

	public void setTransport(boolean transport) {
		this.transport = transport;
	}


	public void nearMe() {
		BlockPos mainPos = this.getPos();
		for (EnumFacing direction : EnumFacing.HORIZONTALS){ 

			BlockPos neighbourPos = mainPos.offset(direction); 

			IBlockState neighbourState = world.getBlockState(neighbourPos); 

			Block neighbourBlock = neighbourState.getBlock(); 

			TileEntity te = world.getTileEntity(neighbourPos);
			TileEntityLockableLoot chest = (TileEntityLockableLoot)te;
			//TileEntityPipeBlock pb= (TileEntityPipeBlock)te;
			
			// Del Chest al Pipe(pull)
			if (neighbourBlock == ModBlocks.TINYCHEST_BLOCK && this.isPull() && !chest.isEmpty()){ 
				this.setInventorySlotContents(0, chest.getStackInSlot(0));
				chest.removeStackFromSlot(0);
			}	
			//Del Pipe(push) al chest	
			
			else if(neighbourBlock == ModBlocks.TINYCHEST_BLOCK && this.isPush() && !this.isEmpty()) {
				chest.setInventorySlotContents(0, this.getStackInSlot(0));
				this.removeStackFromSlot(0);
			}	
			// Del pipe(push) a transport(pipe)
			
//			else if(neighbourBlock == ModBlocks.PIPE_BLOCK && this.isPush() && !chest.isEmpty()) {
//				this.setInventorySlotContents(0, chest.getStackInSlot(0));
//				chest.removeStackFromSlot(0);
//			}
			
			//Del pipe (pull) a pipe(transport)	
			
			else if(neighbourBlock == ModBlocks.PIPE_BLOCK && this.isPull() && !this.isEmpty()) {
				chest.setInventorySlotContents(0, this.getStackInSlot(0));
				this.removeStackFromSlot(0);
			}
			
			//Del pipe(transport) a pipe(cualquiera)
//			else if(neighbourBlock == ModBlocks.PIPE_BLOCK && this.isTransport() && !this.isEmpty()){
//				chest.setInventorySlotContents(0, this.getStackInSlot(0));
//				this.removeStackFromSlot(0);
//			}
		}
	}



	

	@Override
	public void update() {
		this.nearMe();
	}




}
