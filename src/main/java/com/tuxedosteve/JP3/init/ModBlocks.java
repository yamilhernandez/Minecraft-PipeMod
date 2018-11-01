package com.tuxedosteve.JP3.init;

import java.util.ArrayList;
import java.util.List;

import com.tuxedosteve.JP3.blocks.BlockBase;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class ModBlocks {
	
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	public static final Block  SAFE_BLOCK= new BlockBase("safe_block", Material.IRON);

}
