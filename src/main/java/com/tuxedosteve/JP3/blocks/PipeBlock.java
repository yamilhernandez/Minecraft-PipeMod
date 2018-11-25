package com.tuxedosteve.JP3.blocks;

import com.tuxedosteve.JP3.Main;
import com.tuxedosteve.JP3.blocks.tileentity.TileEntityTinyChestBlock;
import com.tuxedosteve.JP3.blocks.tileentity.TileEntityPipeBlock;
import com.tuxedosteve.JP3.init.ModBlocks;
import com.tuxedosteve.JP3.init.ModItems;
import com.tuxedosteve.JP3.util.Reference;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PipeBlock extends BlockContainer {
	public PipeBlock(String name) {
		
		super(Material.IRON);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.javaprojecttab);

		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	
	}
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
			TileEntityPipeBlock tileentity= (TileEntityPipeBlock)worldIn.getTileEntity(pos);
			tileentity.setMode();
			
//		if(!worldIn.isRemote) 
//			playerIn.openGui(Main.Instance, Reference.GUI_BANK_BLOCK, worldIn, pos.getX(), pos.getY(), pos.getZ());
//		
		return true;
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		
		TileEntityPipeBlock tileentity= (TileEntityPipeBlock)worldIn.getTileEntity(pos);
		InventoryHelper.dropInventoryItems(worldIn, pos, tileentity);
		super.breakBlock(worldIn, pos, state);	
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		if(stack.hasDisplayName()) {
			
			TileEntity tileentity = worldIn.getTileEntity(pos);
			if(tileentity instanceof TileEntityPipeBlock) {
			  ((TileEntityPipeBlock) tileentity).setCustomName(stack.getDisplayName());
				
			}
			
		}
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityPipeBlock();
	}
	
	@Override
	public boolean hasTileEntity() {
		return true;
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}
	

	@Override
	public boolean isFullCube(IBlockState state) {
		
		return false;
	}
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		
		return false;
	}
	
	
	
}
