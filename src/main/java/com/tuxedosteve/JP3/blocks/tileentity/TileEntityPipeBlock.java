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
		//this.nearMe();
		//this.transportItems();
	}
	
	public EnumFacing findNextPipe(EnumFacing pastDirection) {
		BlockPos pipePos = this.getPos();
		
		for (EnumFacing direction : EnumFacing.HORIZONTALS){ 
		
			BlockPos neighbourPos = pipePos.offset(direction); 
			
			IBlockState neighbourState = world.getBlockState(neighbourPos); 
			
			Block neighbourBlock = neighbourState.getBlock(); 
			
			if(neighbourBlock == ModBlocks.PIPE_BLOCK && direction!= pastDirection ) {
				return direction;
			}
		}
		return null;
	}
	
	public EnumFacing findChest() {
		BlockPos pipePos = this.getPos();
		
		for (EnumFacing direction : EnumFacing.HORIZONTALS){ 
		
			BlockPos neighbourPos = pipePos.offset(direction); 
			
			IBlockState neighbourState = world.getBlockState(neighbourPos); 
			
			Block neighbourBlock = neighbourState.getBlock(); 
			
			TileEntity te = world.getTileEntity(neighbourPos);
			
			if(te instanceof TileEntityLockableLoot &&  neighbourBlock != ModBlocks.PIPE_BLOCK) {
				return direction;
			}
		}
		return null;
	}
	
	
	public void transportItems() {
		BlockPos pipePos = this.getPos();
		
		EnumFacing direction= findChest();
			
		BlockPos neighbourPos = pipePos.offset(direction); 
			
		//IBlockState neighbourState = world.getBlockState(neighbourPos); 
			
		//Block neighbourBlock = neighbourState.getBlock(); 
			
		TileEntity te = world.getTileEntity(neighbourPos);
			
		TileEntityLockableLoot chest = (TileEntityLockableLoot)te;
		
		if(this.isPull() && !chest.isEmpty() && this.isEmpty() ) {
			
			this.setInventorySlotContents(0, chest.getStackInSlot(0));
			
			chest.removeStackFromSlot(0);
		
			if(findChest() == EnumFacing.EAST) transportHelper(EnumFacing.EAST);
			
			if(findChest() == EnumFacing.WEST) transportHelper(EnumFacing.WEST);
			
			if(findChest() == EnumFacing.NORTH) transportHelper(EnumFacing.NORTH);
			
			if(findChest() == EnumFacing.SOUTH) transportHelper(EnumFacing.SOUTH);
		}
	}
	
	public void transportHelper(EnumFacing dontSeeDirection) {
		BlockPos pipePos = this.getPos();
		
		if(this.isTransport() || this.isPull()) {
			
			EnumFacing newDirection =findNextPipe(dontSeeDirection);
			
			BlockPos neighbourPos = pipePos.offset(newDirection); 
			
			TileEntity te = world.getTileEntity(neighbourPos);
			
			TileEntityLockableLoot pipe = (TileEntityPipeBlock)te;
			
			if(pipe.isEmpty() && !this.isEmpty()) {
			
				this.setInventorySlotContents(0, pipe.getStackInSlot(0));
			
				pipe.removeStackFromSlot(0);
			
				transportHelper(newDirection);
			}
		}
		if(this.isPush()) {
			
			EnumFacing direction= findChest();
			
			BlockPos neighbourPos = pipePos.offset(direction);  
				
			TileEntity te = world.getTileEntity(neighbourPos);
				
			TileEntityLockableLoot chest = (TileEntityLockableLoot)te;
			
			if (!this.isEmpty() && chest.isEmpty()) {
			
				chest.setInventorySlotContents(0, this.getStackInSlot(0));
			
				this.removeStackFromSlot(0);
				
				return;
			}
		}
	}
	
	
	
	
	

//	public void nearMe(EnumFacing[] unChecked) {
//		if(unChecked.length>2) {
//			BlockPos mainPos = this.getPos();
//			for (EnumFacing direction : unChecked){ 
//
//				BlockPos neighbourPos = mainPos.offset(direction); 
//
//				IBlockState neighbourState = world.getBlockState(neighbourPos); 
//
//				Block neighbourBlock = neighbourState.getBlock(); 
//
//				TileEntity te = world.getTileEntity(neighbourPos);
//				TileEntityLockableLoot chest = (TileEntityLockableLoot)te;
//				//TileEntityPipeBlock pb= (TileEntityPipeBlock)te;
//
//				// Del Chest al Pipe(pull)
//				if (neighbourBlock == ModBlocks.BANK_BLOCK && this.isPull() && !chest.isEmpty()){ 
//					this.setInventorySlotContents(0, chest.getStackInSlot(0));
//					chest.removeStackFromSlot(0);
//				}	
//				//Del Pipe(push) al chest	
//
//				if(neighbourBlock == ModBlocks.BANK_BLOCK && this.isPush() && !this.isEmpty()) {
//					chest.setInventorySlotContents(0, this.getStackInSlot(0));
//					this.removeStackFromSlot(0);
//				}	
//				// Del pipe(push) a transport(pipe)
//
//				if(neighbourBlock == ModBlocks.PIPE_BLOCK && this.isPush() && !chest.isEmpty()) {
//					this.setInventorySlotContents(0, chest.getStackInSlot(0));
//					chest.removeStackFromSlot(0);
//					this.nearMe(removeDirection(direction, unChecked));
//				}
//
//				//Del pipe (pull) a pipe(transport)	
//
//				if(neighbourBlock == ModBlocks.PIPE_BLOCK && this.isPull() && !this.isEmpty()) {
//					chest.setInventorySlotContents(0, this.getStackInSlot(0));
//					this.removeStackFromSlot(0);
//					this.nearMe(removeDirection(direction, unChecked));
//				}

				//Del pipe(transport) a pipe(cualquiera)
//				if(neighbourBlock == ModBlocks.PIPE_BLOCK && this.isTransport() && !chest.isEmpty()){
//					this.setInventorySlotContents(0, chest.getStackInSlot(0));
//					chest.removeStackFromSlot(0);
//					this.nearMe(removeDirection(direction, unChecked));
//				}
				
//			}
//
//		}
//	}
//





//	private EnumFacing[] removeDirection(EnumFacing direction, EnumFacing[] unChecked ) {
//		EnumFacing[] recur = new EnumFacing[unChecked.length-1];
//		int j=0;
//		for (int i = 0; i < unChecked.length; i++) {
//			if(direction!=unChecked[i])
//				recur[j++]=unChecked[i];
//		}
//		return recur;
//	}


}
