package com.tuxedosteve.JP3.init;

import java.util.ArrayList;
import java.util.List;

import com.tuxedosteve.JP3.blocks.BankBlock;
import com.tuxedosteve.JP3.blocks.BitcoinOre;
import com.tuxedosteve.JP3.blocks.MoneyOre;
import com.tuxedosteve.JP3.blocks.SafeBlock;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class ModBlocks {

	public static final List<Block> BLOCKS = new ArrayList<Block>();

	// Blocks
	public static final Block SAFE_BLOCK = new SafeBlock("safe_block", Material.IRON);
	public static final Block MONEY_ORE = new MoneyOre("money_ore", Material.ROCK);
	public static final Block BITCOIN_ORE = new BitcoinOre("bitcoin_ore", Material.ROCK);
	public static final Block BANK_BLOCK = new BankBlock("bank_block");

}
