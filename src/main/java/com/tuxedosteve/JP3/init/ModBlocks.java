package com.tuxedosteve.JP3.init;

import java.util.ArrayList;
import java.util.List;

import com.tuxedosteve.JP3.blocks.BitcoinOre;
import com.tuxedosteve.JP3.blocks.MoneyOre;

import com.tuxedosteve.JP3.blocks.PipeBlock;
import com.tuxedosteve.JP3.blocks.SafeBlock;
import com.tuxedosteve.JP3.blocks.TinyChestBlock;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class ModBlocks {

	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	// Blocks
	public static final Block SAFE_BLOCK = new SafeBlock("safe_block", Material.IRON);
	public static final Block MONEY_ORE = new MoneyOre("money_ore", Material.ROCK);
	public static final Block BITCOIN_ORE = new BitcoinOre("bitcoin_ore", Material.ROCK);
	public static final Block TINYCHEST_BLOCK = new TinyChestBlock("tiny_chest_block");
	public static final Block PIPE_BLOCK = new PipeBlock("pipe_block");
	
}
