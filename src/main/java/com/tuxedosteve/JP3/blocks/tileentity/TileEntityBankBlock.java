package com.tuxedosteve.JP3.blocks.tileentity;

import com.tuxedosteve.JP3.blocks.BankBlock;
import com.tuxedosteve.JP3.blocks.container.ContainerBankBlock;
import com.tuxedosteve.JP3.init.ModBlocks;
import com.tuxedosteve.JP3.util.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
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
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileEntityBankBlock extends TileEntityLockableLoot implements ITickable {

	private NonNullList<ItemStack> chestContents= NonNullList.<ItemStack>withSize(1,ItemStack.EMPTY);
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

		return new ContainerBankBlock(playerInventory, this, playerIn);
	}

	@Override
	public String getGuiID() {
		return Reference.MOD_ID+ ":bank_block";
	}

	@Override
	public void update()
	{
		this.nearMe();
		
		if (!this.world.isRemote && this.numPlayersUsing != 0 && (this.ticksSinceSync + pos.getX() + pos.getY() + pos.getZ()) % 200 == 0)
		{
			this.numPlayersUsing = 0;
			float f = 5.0F;

			for (EntityPlayer entityplayer : this.world.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB((double)((float)pos.getX() - 5.0F),
					(double)((float)pos.getY() - 5.0F), (double)((float)pos.getZ() - 5.0F), (double)((float)(pos.getX() + 1) + 5.0F),
					(double)((float)(pos.getY() + 1) + 5.0F), (double)((float)(pos.getZ() + 1) + 5.0F))))
			{
				if (entityplayer.openContainer instanceof ContainerBankBlock)
				{
					if (((ContainerBankBlock)entityplayer.openContainer).getChestInventory() == this)
					{
						++this.numPlayersUsing;
					}
				}
			}
		}

		this.prevLidAngle = this.lidAngle;
		float f1 = 0.1F;

		if (this.numPlayersUsing > 0 && this.lidAngle == 0.0F)
		{
			double d1 = (double)pos.getX() + 0.5D;
			double d2 = (double)pos.getZ() + 0.5D;
			this.world.playSound((EntityPlayer)null, d1, (double)pos.getY() + 0.5D, d2, SoundEvents.BLOCK_IRON_TRAPDOOR_OPEN, SoundCategory.BLOCKS, 0.5F, this.world.rand.nextFloat() * 0.1F + 0.9F);
		}

		if (this.numPlayersUsing == 0 && this.lidAngle > 0.0F || this.numPlayersUsing > 0 && this.lidAngle < 1.0F)
		{
			float f2 = this.lidAngle;

			if (this.numPlayersUsing > 0)
			{
				this.lidAngle += 0.1F;
			}
			else
			{
				this.lidAngle -= 0.1F;
			}

			if (this.lidAngle > 1.0F)
			{
				this.lidAngle = 1.0F;
			}

			float f3 = 0.5F;

			if (this.lidAngle < 0.5F && f2 >= 0.5F)
			{
				double d3 = (double)pos.getX() + 0.5D;
				double d0 = (double)pos.getZ() + 0.5D;
				this.world.playSound((EntityPlayer)null, d3, (double)pos.getY() + 0.5D, d0, SoundEvents.BLOCK_IRON_TRAPDOOR_CLOSE, SoundCategory.BLOCKS, 0.5F, this.world.rand.nextFloat() * 0.1F + 0.9F);
			}

			if (this.lidAngle < 0.0F)
			{
				this.lidAngle = 0.0F;
			}
		}      
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

	
//	public void nearMe() {
//		BlockPos mainPos = this.getPos();
//		for (EnumFacing direction : EnumFacing.VALUES){ 
//
//			BlockPos neighbourPos = mainPos.offset(direction); 
//
//			IBlockState neighbourState = world.getBlockState(neighbourPos); 
//
//			Block neighbourBlock = neighbourState.getBlock(); 
//
//			if (neighbourBlock == Blocks.CHEST){ 
//				TileEntity te = world.getTileEntity(neighbourPos);
//			    TileEntityChest chest = (TileEntityChest)te ;
//				this.setInventorySlotContents(0, chest.getStackInSlot(0));
//			}
//			
//		}
	}
	
	

}
