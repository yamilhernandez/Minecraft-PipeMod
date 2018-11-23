package com.tuxedosteve.JP3.blocks.tileentity;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.util.NonNullList;

public class TileEntityPipeBlock extends TileEntityLockableLoot {
	

	private NonNullList<ItemStack> chestContents= NonNullList.<ItemStack>withSize(1,ItemStack.EMPTY);
	private boolean push, pull, transport=true;
	public int numPlayersUsing, ticksSinceSync;
	public float lidAngle, prevLidAngle;

	

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

		return this.hasCustomName() ? this.customName: "container.bank_block";

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
		return null;
	}

	@Override
	public String getGuiID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected NonNullList<ItemStack> getItems() {
		// TODO Auto-generated method stub
		return null;
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

}
