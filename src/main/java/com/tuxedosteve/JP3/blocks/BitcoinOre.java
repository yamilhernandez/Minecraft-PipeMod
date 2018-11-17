package com.tuxedosteve.JP3.blocks;

import java.util.Random;

import com.tuxedosteve.JP3.init.ModItems;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

public class BitcoinOre extends BlockBase{

	public BitcoinOre(String name, Material material) {
		super(name, material);
		setSoundType(SoundType.METAL);
		setHardness(5.0F);
		setResistance(15.0F);
		setHarvestLevel("pickaxe", 2);
	}
	
//	@Override
//	public Item getItemDropped(IBlockState state, Random rand, int fortune ) {
//		return ModItems.BITCOIN;
//		
//	}
	
//	@Override
//	public int quantityDropped(Random rand) {
//		int max = 100;
//		int min = 1;
//		return rand.nextInt(max) + min;
//	}

}
