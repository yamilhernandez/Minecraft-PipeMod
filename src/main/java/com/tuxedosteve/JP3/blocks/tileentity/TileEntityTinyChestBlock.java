package com.tuxedosteve.JP3.blocks.tileentity;

import com.tuxedosteve.JP3.blocks.container.ContainerTinyChestBlock;
import com.tuxedosteve.JP3.util.Reference;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;

public class TileEntityTinyChestBlock extends TileEntityLockableLoot implements ITickable {

	private NonNullList<ItemStack> chestContents= NonNullList.<ItemStack>withSize(1,ItemStack.EMPTY);
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

		return this.hasCustomName() ? this.customName: "container.tiny_chest_block";

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

		return new ContainerTinyChestBlock(playerInventory, this, playerIn);
	}

	@Override
	public String getGuiID() {
		return Reference.MOD_ID+ ":tiny_chest_block";
	}

	@Override
	public void update()
	{
		//this.nearMe();
    
	}

	@Override
	protected NonNullList<ItemStack> getItems() {

		return this.chestContents;
	}

	@Override
	public void openInventory(EntityPlayer player) {

		this.numPlayersUsing++;
		this.world.addBlockEvent(pos, this.getBlockType(), 1, this.numPlayersUsing);
		this.world.notifyNeighborsOfStateChange(pos, this.blockType, false);


	}

	@Override
	public void closeInventory(EntityPlayer player) {
		this.numPlayersUsing--;
		this.world.addBlockEvent(pos, this.getBlockType(), 1, this.numPlayersUsing);
		this.world.notifyNeighborsOfStateChange(pos, this.blockType, false);
	}


//		public void nearMe() {
//			BlockPos mainPos = this.getPos();
//			for (EnumFacing direction : EnumFacing.VALUES){ 
//	
//				BlockPos neighbourPos = mainPos.offset(direction); 
//	
//				IBlockState neighbourState = world.getBlockState(neighbourPos); 
//	
//				Block neighbourBlock = neighbourState.getBlock(); 
//	
//				if (neighbourBlock == Blocks.CHEST){ 
//					TileEntity te = world.getTileEntity(neighbourPos);
//				    TileEntityChest chest = (TileEntityChest)te ;
//					this.setInventorySlotContents(0, chest.getStackInSlot(0));
//				}
//				
//			}
//		}



}
